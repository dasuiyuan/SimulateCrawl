package util;

import com.alibaba.fastjson.JSONArray;
import lombok.extern.log4j.Log4j;
import model.EpidemicMaterialRequirement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author : suiyuan
 * @description : 物资挖掘工具
 * @date : Created in 2020-01-31 11:09
 * @modified by :
 **/
@Log4j
public class MaterialsCrawlerUtil {

    /**
     * 网站地址
     */
    private static final String URL = "https://wuhan2020.kaiyuanshe.cn/#hospital";

    /**
     * selenium超时时间
     */
    private final static int SELENIUM_TIME_SECONDS = 60;

    public static void main(String[] args) throws IOException {
        getMaterialRequirements();
    }

    /**
     * 获取物资需求信息
     *
     * @return 物资需求信息
     */
    public static String getMaterialRequirements() {
        WebDriver webDriver = new ChromeDriver();
        webDriver.manage().timeouts().pageLoadTimeout(SELENIUM_TIME_SECONDS, TimeUnit.SECONDS);
        webDriver.manage().timeouts().setScriptTimeout(SELENIUM_TIME_SECONDS, TimeUnit.SECONDS);
        webDriver.manage().timeouts().implicitlyWait(SELENIUM_TIME_SECONDS, TimeUnit.SECONDS);
        String mJson = "";
        try {
            webDriver.get(URL);
            Actions action = new Actions(webDriver);
            WebDriverWait wait = new WebDriverWait(webDriver, SELENIUM_TIME_SECONDS);
            List<WebElement> webElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("card-body")));
            List<EpidemicMaterialRequirement> requirements = new ArrayList<>(webElements.size());

            for (WebElement webElement : webElements) {
                EpidemicMaterialRequirement emr = new EpidemicMaterialRequirement();
                //医院名
                String hospital = webElement.findElement(By.className("card-title")).getText();
                //获取物资列表
                List<WebElement> liElems = webElement.findElements(By.tagName("li"));
                List<String> materials = new ArrayList<>(liElems.size());
                for (WebElement liElem : liElems) {
                    String mName = liElem.getText();
                    materials.add(mName);
                }
                //获取发布源
                WebElement footer = webElement.findElement(By.tagName("footer"));
                String pubPhone = footer.getText();
                //获取联系方式、地址
                WebElement phoneElem = webElement.findElement(By.className("dropdown"));
                phoneElem.click();
                String phone = webElement.findElement(By.className("dropdown-item")).getText();
                phoneElem.click();
//            String xpath = "/html/body/page-router/main/hospital-page/div/edge-detector/div/div[" + i + "]/div/div/button";
//            WebElement addrElem = ((ChromeDriver) webDriver).findElement(By.xpath(xpath));//webElement.findElement(By.cssSelector("body > page-router > main > hospital-page > div > edge-detector > div > div:nth-child(1) > div > div > button"));
//            //addrElem.click();
//            String addr = getClipboardString();


                emr.setHospitalName(hospital).setMaterialList(materials).setPhone(phone).setSource(pubPhone);
                requirements.add(emr);
            }
            mJson = JSONArray.toJSONString(requirements);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        } finally {
            webDriver.close();
            webDriver.quit();
        }
        System.out.println(mJson);
        return mJson;
    }

    public static String getClipboardString() {
        Clipboard sysClip = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable clipTf = sysClip.getContents(null);
        if (clipTf.isDataFlavorSupported(DataFlavor.stringFlavor)) {

            try {
                return (String) clipTf.getTransferData(DataFlavor.stringFlavor);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
