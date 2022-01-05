package util;

import com.alibaba.fastjson.JSONObject;
import model.EnvironmentInfo;
import model.EpidemicSumInfo;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.List;


/**
 * @author : suiyuan
 * @description : 爬虫工具
 * @date : Created in 2020-01-29 16:42
 * @modified by :
 **/
public class PatientCrawlerUtil {

    /**
     * 日志
     */
    private final static Logger log = Logger.getLogger(PatientCrawlerUtil.class);

    /**
     * 网易疫情地址
     */
    private final static String URL_163 = "http://news.163.com/special/epidemic/";

    /**
     * 丁香园疫情地址
     */
    private final static String URL_DXY = "https://3g.dxy.cn/newh5/view/pneumonia";//"https://www.google.com";

    /**
     * Nosugartech疫情地址
     */
    private final static String URL_NOSUGARTECH = "http://2019ncov.nosugartech.com";

    /**
     * 确诊
     */
    private final static String CONFIRMED_TITAL = "累计确诊";

    /**
     * 疑似
     */
    private final static String SUSPECTED_TITAL = "现存疑似";

    /**
     * 重症
     */
    private final static String SERVIOUS_TITAL = "现存重症";

    /**
     * 治愈
     */
    private final static String CURED_TITAL = "治愈";

    /**
     * 死亡
     */
    private final static String DEAD_TITAL = "死亡";

    /**
     * selenium超时时间
     */
    private final static int SELENIUM_TIME_SECONDS = 60;

    public static void main(String[] args) throws IOException {
        getSumDataDXY();
        getDangerTripNosugartech();
        getWorldEpidemicDXYinfo();
//        String epidemicJson = getChinaEpidemicDXYInfo();
//        List<EpidemicInfo> epidemicInfos = JSONObject.parseArray(epidemicJson, EpidemicInfo.class);
//        System.out.println(epidemicInfos);
    }


    /**
     * 丁香园中国疫情
     *
     * @return 丁香园中国疫情
     * @throws IOException
     */
    public static String getChinaEpidemicDXYInfo() throws IOException {
        String sumInfo = getSumDataDXY();
        String epidemic = getEpidemicDXYInfo("getAreaStat", "window.getAreaStat = ", "]");
        String url = EnvironmentInfo.configHelper.getProp().getProperty("mysql_host") + "/epidemicInfo/insertEpidemicInfo";
        String finalData = "{\"total\":" + sumInfo + ",\"data\":" + epidemic + "}";
        log.info(url);
        log.info("start insert");
        String result = HttpClientHelper.postJson(url, finalData);
        log.info(result);
        log.info("finish insert");
        return epidemic;
    }

    /**
     * 获取总体信息
     *
     * @return 总体信息
     */
    private static String getSumDataDXY() {
        WebDriver webDriver = new ChromeDriver();
        try {
            webDriver.get(URL_DXY);
            WebDriverWait wait = new WebDriverWait(webDriver, SELENIUM_TIME_SECONDS);
            WebElement totalElem = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("count___3GCdh")));
            List<WebElement> webElems = totalElem.findElements(By.tagName("li"));
            EpidemicSumInfo sumInfo = new EpidemicSumInfo();
            for (WebElement webElem : webElems) {
                if (webElem.getText().contains(CONFIRMED_TITAL)) {
                    sumInfo.setConfirmedCount(Integer.parseInt(webElem.findElement(By.tagName("strong")).getText()));
                    try {
                        String confIncrease = webElem.findElement(By.tagName("em")).getText();
                        sumInfo.setIncreaseConfirmedCount(Integer.parseInt(confIncrease.substring(1)));
                    } catch (Exception ex) {
                    }
                } else if (webElem.getText().contains(SUSPECTED_TITAL)) {
                    sumInfo.setSuspectedCount(Integer.parseInt(webElem.findElement(By.tagName("strong")).getText()));
                    try {
                        String confIncrease = webElem.findElement(By.tagName("em")).getText();
                        sumInfo.setIncreaseSuspectedCount(Integer.parseInt(confIncrease.substring(1)));
                    } catch (Exception ex) {
                    }
                } else if (webElem.getText().contains(CURED_TITAL)) {
                    sumInfo.setCuredCount(Integer.parseInt(webElem.findElement(By.tagName("strong")).getText()));
                    try {
                        String confIncrease = webElem.findElement(By.tagName("em")).getText();
                        sumInfo.setIncreaseCuredCount(Integer.parseInt(confIncrease.substring(1)));
                    } catch (Exception ex) {
                    }
                } else if (webElem.getText().contains(DEAD_TITAL)) {
                    sumInfo.setDeadCount(Integer.parseInt(webElem.findElement(By.tagName("strong")).getText()));
                    try {
                        String confIncrease = webElem.findElement(By.tagName("em")).getText();
                        sumInfo.setIncreaseDeadCount(Integer.parseInt(confIncrease.substring(1)));
                    } catch (Exception ex) {
                    }
                } else if (webElem.getText().contains(SERVIOUS_TITAL)) {
                    sumInfo.setServiousCount(Integer.parseInt(webElem.findElement(By.tagName("strong")).getText()));
                    try {
                        String confIncrease = webElem.findElement(By.tagName("em")).getText();
                        sumInfo.setIncreaseServiousCount(Integer.parseInt(confIncrease.substring(1)));
                    } catch (Exception ex) {
                    }
                }
            }
            return JSONObject.toJSONString(sumInfo);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        } finally {
            webDriver.close();
            webDriver.quit();
        }
        return "";
    }

    /**
     * 丁香园世界疫情
     *
     * @return 丁香园世界疫情
     * @throws IOException
     */
    public static String getWorldEpidemicDXYinfo() throws IOException {
        String epidemic = getEpidemicDXYInfo("getListByCountryTypeService2", "window.getListByCountryTypeService2 = ", "]");
        String url = EnvironmentInfo.configHelper.getProp().getProperty("mysql_host") + "/epidemicInfo/insertGlobalEpidemicInfo";
        log.info(url);
        log.info("start insert");
        String result = HttpClientHelper.postJson(url, "{data:" + epidemic + "}");
        log.info(result);
        log.info("finish insert");
        return epidemic;
    }

    /**
     * 丁香园疫情信息
     *
     * @param scriptId 信息所在脚本ID
     * @param startStr 信息内容头
     * @param endStr   信息内容尾
     * @return
     * @throws IOException
     */
    public static String getEpidemicDXYInfo(String scriptId, String startStr, String endStr) throws IOException {
        Document document = Jsoup.connect(URL_DXY).get();
        Element element = document.getElementById(scriptId);
        return element.toString().substring(element.toString().indexOf(startStr) + startStr.length(), element.toString().lastIndexOf(endStr) + 1);
    }

    /**
     * 确诊患者同行班次
     *
     * @return 班次信息
     * @throws IOException
     */
    public static String getDangerTripNosugartech() throws IOException {
        String tripJson = HttpClientHelper.get(URL_NOSUGARTECH + "/data.json");
        String url = EnvironmentInfo.configHelper.getProp().getProperty("mysql_host") + "/tripInfo/insertTripInfo";
//        System.out.println(tripJson);
        log.info(url);
        log.info("start insert");
        String result = HttpClientHelper.postJson(url, tripJson);
        log.info(result);
        log.info("finish insert");
        return tripJson;
    }

    /**
     * 网易疫情信息
     *
     * @return 疫情结果
     * @throws IOException
     */
    public static String getEpidemic163Info() throws IOException {
        Document document = Jsoup.connect(URL_163).get();
        String epidemic = "";
        Elements elements = document.getElementsByTag("Script");
        for (Element element : elements) {
            if (element.toString().contains("window.epidemic")) {
                int indexStart = element.toString().indexOf("window.epidemic") + 18;
                int indexEnd = element.toString().indexOf("window.data_by_date");
                epidemic = element.toString().substring(indexStart, indexEnd);
                log.info(epidemic);
                log.info("start insert");

                //todo: insert by httpclient

                log.info("finish insert");
            }
        }
        return epidemic;
    }
}
