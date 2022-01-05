package util;

import com.sun.deploy.util.ArrayUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Sui Yuan
 * @Description:
 * @Date: 2021/8/19 17:22
 * @Modified by:
 **/
@Log4j
public class GitCrawlerUtil {
    /**
     * 网站地址
     */
    private static final String URL = "https://git.jd.com/";

    private static final ArrayList<String> members = new ArrayList<String>();

    static {
        members.add("yuzisheng");
//        members.add("hehuajun1");
//        members.add("liujunwen8");
//        members.add("hujian109");
//        members.add("shengmeng");
//        members.add("wangrubin1");
//        members.add("liujinghui9");
//        members.add("zhuhaowen");
//        members.add("wangpeng417");
//        members.add("chenqin127");
//        members.add("wangxu649");
//        members.add("liujun513");
//        members.add("tangjuncheng3");
//        members.add("zhangjianan13");
    }


    /**
     * selenium超时时间
     */
    private final static int SELENIUM_TIME_SECONDS = 60;

    public static void main(String[] args) throws IOException {
        getTK();
    }

    /**
     * 获取物资需求信息
     *
     * @return 物资需求信息
     */
    public static String getTK() {
        WebDriver webDriver = new ChromeDriver();
        webDriver.manage().timeouts().pageLoadTimeout(SELENIUM_TIME_SECONDS, TimeUnit.SECONDS);
        webDriver.manage().timeouts().setScriptTimeout(SELENIUM_TIME_SECONDS, TimeUnit.SECONDS);
        webDriver.manage().timeouts().implicitlyWait(SELENIUM_TIME_SECONDS, TimeUnit.SECONDS);
        Set<Cookie> cookies = webDriver.manage().getCookies();
//        for (int j = 1; j <6; j++) {
//            webDriver.manage().deleteAllCookies();
//            webDriver.manage().addCookie(new Cookie("PHPSESSID", "i0e3vthukcm216v8k8gkaoahg7", "dxbmpx.tongliannet.com", "/", null));
//            webDriver.manage().addCookie(new Cookie("exam_currentuser", "%25D9%25A8r%259E%25F4%25DF%25AD%259DckT%25A3%2595%25A5%25EB%25D7%25A8%25D2%25EE%25DF%25D8%25DE%2599%2595Tk%25A3l%25AD%25A8%255B%2595%25AB%25A5%25A5%25A0Rl%25A5jag%25B2%2590%25AC%25C9%25EC%25DF%25DC%25DB%259E%25A1%2593%25A3%25A3%25A9%25E7%25E0%259D%2586%25B4%25DF%25AD%259FbkTed%2597%25B1%25D0k%2597%25AB%25D0%25A9%25A3c%2592cd%2594%2596%25AD%25D2k%25C6%25AE%25D2%25A6%25D2%2560hfie%2598%25A8%2590t%25D7%25B3%259D%25A9%25A6R%25A4%2597%25A3%25A3%259B%25E7%25DC%25A9%25C5%25EC%25DF%25EA%25DB%25A2%2595%25A5Rk%25A5%25B2%25A7s%2586%25E3%25D0%25E6%25E1%2599%25AA%25A7%2591%259ET%25B3%25E1s%259D%25B3%258E%25E6%25D1%25A3%25A4%259B%259F%259E%259B%25E8%2590t%25D7%25B3%259D%25A6%25A6Rbbf%255Ee%25B0%259Cj%2595%25AE%259A%25A5%25A0Rl%25A5jaf%25B2%2590%25AC%25C9%25EC%25DF%25DC%25DB%259E%2598%25A4%259F%25A5%25A2%25E1%25D2%255B%259F%25EC%25A6%25A4%25A6RiTk%25A3l%25A9%25A4s%2586%25EC%25D1%25E6%25DF%2599%25A0%25A0%259C%259F%2599%25E1%25DC%25AD%25CD%25E6%25D1%2595%25A7%2599kcfbk%25AB%25A4o%2594%25B0%25A3%25AE%25DFjbgjR%25A5%25DD%25E1%25AC%25CD%25E8%25DA%25E8%25DF%2595%25A3%25A0%2591%259D%2597%259A%25A9%25AC%259E%25AA%25A4%25AD%258Eafbdcb%25A9%25A7q%259D%25A9%259E%25A3%25A1%2560aceRm%25EB%25A8j%259A%25B3%258E%25E6%25D1%25A3%25A4%259B%259F%259E%25A6%25E1%25DB%259E%25D0%25E2%25D9%25DC%25E0Rl%259Bjah%25AA%25A7l%259A%25AF%259C%25AA%25A3k%25A4lijT%25EB%25D3%25AC%25D7%25E2%25DB%25E1%25D5%2594Sm%25A3jd%25AE%25A8%255B%25CD%25A9%25D1%25A6%25E2%25A4%2599%25A7%259B%2593%259F%25AA%259Fo%25DA%25B1%25D7%25AB%25D3%259B%2592%25A1%2591%2598%2599%25AF%2590t%25E1", "dxbmpx.tongliannet.com", "/", null));
//            webDriver.manage().addCookie(new Cookie("exam_knowsid", "4", "dxbmpx.tongliannet.com", "/", null));
//            webDriver.manage().addCookie(new Cookie("exam_number", "1", "dxbmpx.tongliannet.com", "/", null));//题号
//            webDriver.manage().addCookie(new Cookie("exam_questype", "1", "dxbmpx.tongliannet.com", "/", null));//题型号{1：单选题（257） 2：多选题（261） 3：判断题（229） 4：填空（66） 5：必对题（28）}
//
//        }
        for (String member : members) {
            String url = URL + member;
            webDriver.get(url);
            System.out.println("current question num:  " + member);

            StateElem stateElem = new StateElem();
            stateElem.name = member;
            stateElem.total = 0;
            stateElem.maxNum = 0;
            webDriver.manage().deleteAllCookies();
            webDriver.manage().addCookie(new Cookie("uasLogin", "7CtDr9o74KEeiTw+De9QG4E3d065m6dyuOzoyHiAhnEVDLuTOUVOvQNNk5VSFsZR1kMqSjy681WQy+T8HXu9b++OHq2BYUGsEfuy0Vitj55a8IYtSNCuft0jfUNalyVInTMOSjtr4gYMRhzkag2pKlz7PXjL1UsvcBmNC2rA8+HLG/ZoaQ74gRB1T11Ew8Qf4VvJqg6CWbeF2g9qqqJsMKU+se2Pb+HxO0bohLMBhKFYAEI8tUe0rS8Or2J/PzRdWIdEqbAt+rv5N94L0YKCzL+JBlaeCS8zasJA/W/i1eE9eK5LZOg8V/jwvksSC4lPFg/CjXVgt8JcQ5fKtUz2QWSWCddLJ+pG5PlZiimwPrbuFK7lMZswH9biRxUJqvWzxGJXdKL7ceVtZM/m8CAc6t3aqwrfhvM6Lk8GTvQzRKOHBkPh+zezNNMURlbSqBZfaSTfglg59T/1h2ROFCuzvSjALuA8Re2Ewvnzy51jyRGufEWpJXuhh7egxaf/fh1DJh+ZXNzPfMItm8kdfRxz/S/yebu+drpOZ0Z+B4Ka57FJ9shocYhsW5kup6PIm/gOU+pr0xtCTJoN6C5M58WKxAH0fGtwJBRn6Ecq+hRzFs7FrbgBm9ttvRmgAzYUuXnHnJlCyseEVkaiNhM6ZiEYTB5QozMP/N/J4qv/hG6Uf7zoNhKm/EVycN+oVjsY9he73duWm/JFr/ACHCrs1b5Ps159V8ik8HWQdXktuLImgatottj12EX1/Goqer7QbGkyHCenYbzkkKOX7hRAcVsUrA7nMU+z8fteUL4IDNNXyZ7u43EmVezCA2j/yHVXtH5uGQ34bwEe5rBKYhQTU1Nb+My6KS82nTKqukgBWkNuOQ/KdJ+ZpIbP7gHXNl324SO6kkSg3LzSip1fxnl83kgLd7ccU1icTUSaqLzkyAQ4gWGkAam5H5ntedRsQT8NRgGzy37/ELOh8nQVRqeu4FB7RCowJhdJUKiVsZpT7w==", ".jd.com", "/", null));
            webDriver.manage().addCookie(new Cookie("token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IllhNklZQk1IZE9qNFN4SmUxZ2dFIiwiaWF0IjoxNjQwODYwNzI5LCJleHAiOjE2NDM0NTI3Mjl9.1nyj2Oj-GRenMNSYKtqumERrIsX1VGvxtoyPdl61D_s", ".jd.com", "/", null));
            webDriver.manage().addCookie(new Cookie("sso.jd.com", "BJ.D48EC6922713CE3A3DAC1AC20B05E5198120211231094922", ".jd.com", "/", null));
            webDriver.manage().addCookie(new Cookie("_gitlab_session", "ad4ab87c7924103851fe511742b436d9", "git.jd.com", "/", null));//题号
//            webDriver.manage().addCookie(new Cookie("exam_questype", "5", "dxbmpx.tongliannet.com", "/", null));//题型号{1：单选题（257） 2：多选题（261） 3：判断题（229） 4：填空（66） 5：必对题（28）}
            try {
                webDriver.get(url);
                Actions action = new Actions(webDriver);
                WebDriverWait wait = new WebDriverWait(webDriver, SELENIUM_TIME_SECONDS);
                List<WebElement> choiceElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("contrib-calendar")));
                List<WebElement> contributions = choiceElements.get(0).findElements(By.tagName("rect"));
//            for (WebElement blockElem : blockElems) {
//                List<WebElement> contributions = blockElem.findElements(By.tagName("rect"));
                for (WebElement contribution : contributions) {
                    String contrNum = contribution.getAttribute("data-original-title");
                    if (contrNum.toLowerCase().startsWith("no")
                            || contrNum.toLowerCase().startsWith("1-9")
                            || contrNum.toLowerCase().startsWith("10-19")
                            || contrNum.toLowerCase().startsWith("20-29")
                            || contrNum.toLowerCase().startsWith("30+"))
                        continue;
                    int num = 0;
                    try {
                        num = Integer.parseInt(contrNum.split(" ")[0]);
                    } catch (Exception ex) {
                        throw ex;
                    }
                    stateElem.total += num;
                    if (stateElem.maxNum < num) {
                        stateElem.maxNum = num;
                    }
                    //展开贡献值
                    contribution.click();
//                    List<WebElement> borderedList = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("bordered-list")));
//                    List<WebElement> liList = borderedList.get(0).findElements(By.tagName("li"));
//                    for (WebElement webElement : liList) {
//                        WebElement time = webElement.findElement(By.className("light"));
//                        String timeStr = time.getText();
//
//                    }
                }
                System.out.println(stateElem);
            } catch (Exception ex) {
                throw ex;
            } finally {
//                webDriver.close();
//                webDriver.quit();
            }
        }

        return "mJson";
    }

    @Data
    @NoArgsConstructor
    static class StateElem {
        public String name;
        public String earliestTime;
        public String earliestComment;
        public String latestTime;
        public String latestComment;
        public int maxNum;
        public int total;

        @Override
        public String toString() {
            return name + "," + maxNum + "," + total;
        }
    }
}
