package util;

import com.alibaba.fastjson.JSONArray;
import lombok.extern.log4j.Log4j;
import model.EpidemicNews;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author : suiyuan
 * @description : 新闻挖掘工具
 * @date : Created in 2020-01-30 16:19
 * @modified by :
 **/
@Log4j
public class NewsCrawlerUtil {

    /**
     * 网站地址
     */
    private static final String URL = "http://2019ncov.tk/";

    /**
     * selenium超时时间
     */
    private final static int SELENIUM_TIME_SECONDS = 60;

    public static void main(String[] args) throws IOException {
        getEpidemicNews();
    }

    /**
     * 获取疫情新闻
     *
     * @return 疫情新闻
     */
    public static String getEpidemicNews() {
        WebDriver webDriver = new ChromeDriver();
        webDriver.manage().timeouts().pageLoadTimeout(SELENIUM_TIME_SECONDS, TimeUnit.SECONDS);
        webDriver.manage().timeouts().setScriptTimeout(SELENIUM_TIME_SECONDS, TimeUnit.SECONDS);
        try {
            webDriver.get(URL);
            Actions action = new Actions(webDriver);
            WebDriverWait wait = new WebDriverWait(webDriver, SELENIUM_TIME_SECONDS);
            List<WebElement> webElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("card")));
            List<EpidemicNews> news = new ArrayList<>(webElements.size());
            for (WebElement webElement : webElements) {
                String cardText = webElement.getText();
                List<String> texts = Arrays.asList(cardText.split("\n"));
                List<String> validateTexts = new ArrayList<>();
                for (String text : texts) {
                    if (!text.trim().isEmpty()) {
                        validateTexts.add(text.trim());
                    }
                }
                EpidemicNews epn = getNews(validateTexts);
                if (epn == null) {
                    continue;
                }
                news.add(epn);
            }

            String newsJson = JSONArray.toJSONString(news);
            System.out.println(newsJson);
        } catch (Exception ex) {
            ex.printStackTrace();
            log.info(ex.getMessage(), ex);
        } finally {
            webDriver.close();
            webDriver.quit();
        }
        return "";
    }

    /**
     * 获取疫情新闻
     *
     * @param validateTexts 疫情文本
     * @return 疫情新闻对象
     */
    private static EpidemicNews getNews(List<String> validateTexts) {
        String newsTag = validateTexts.get(0);
        if (newsTag.contains("疫情动态")) {
            return getEpidemicSituations(validateTexts);
        }

        if (newsTag.contains("疫情通报")) {
            //如果tag里面只有1个#，则按疫情动态格式解析
            if (newsTag.replace("#", "").trim().length() == newsTag.length() - 1) {
                return getEpidemicSituations(validateTexts);
            } else {
                return getEpidemicReports(validateTexts);
            }
        }

        return null;
    }

    /**
     * 通过新闻通报构造新闻对象
     *
     * @param validateTexts 新闻完整内容
     * @return 新闻对象
     */
    private static EpidemicNews getEpidemicReports(List<String> validateTexts) {
        String titleOri = validateTexts.get(0);
        String region = "";
        String title = "疫情通报";
        //如果是区域新闻，则提取区域
        if (titleOri.startsWith("#")) {
            region = titleOri.substring(titleOri.lastIndexOf("#") + 1, titleOri.length()).trim();
        }

        String content = "";
        for (int i = 1; i < validateTexts.size() - 1; i++) {
            content += validateTexts.get(i) + "\n";
        }
        content = content.trim();
        String date = validateTexts.get(validateTexts.size() - 1);
        return new EpidemicNews().setTitle(title).setContent(content).setTime(date).setRegion(region);
    }

    /**
     * 通过新闻动态通报新闻对象
     *
     * @param validateTexts 新闻完整内容
     * @return 新闻对象
     */
    private static EpidemicNews getEpidemicSituations(List<String> validateTexts) {
        String titleOri = validateTexts.get(1).replace("【", "").replace("】", "").trim();
        String region = "";
        String title = titleOri;
        //如果是区域新闻，则提取区域
        if (titleOri.startsWith("#")) {
            region = titleOri.substring(titleOri.indexOf("#") + 1, titleOri.indexOf(" "));
            title = titleOri.substring(titleOri.indexOf(region) + region.length(), titleOri.length() - 1).trim();
        }
        //无法提取区域返回空
        if (region.trim().isEmpty() || "全国".equals(region.trim())) {
            return null;
        }

        String content = "";
        for (int i = 2; i < validateTexts.size() - 1; i++) {
            content += validateTexts.get(i) + "\n";
        }
        content = content.trim();
        String date = validateTexts.get(validateTexts.size() - 1);
        return new EpidemicNews().setTitle(title).setContent(content).setTime(date).setRegion(region);
    }
}
