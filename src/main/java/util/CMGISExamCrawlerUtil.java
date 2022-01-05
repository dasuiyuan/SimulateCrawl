package util;

import com.alibaba.fastjson.JSONArray;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import model.EpidemicMaterialRequirement;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
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
public class CMGISExamCrawlerUtil {
    /**
     * 网站地址
     */
    private static final String URL = "http://dxbmpx.tongliannet.com/index.php?exam-app-lesson-paper";

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
        webDriver.get(URL);
//        for (int j = 1; j <6; j++) {
//            webDriver.manage().deleteAllCookies();
//            webDriver.manage().addCookie(new Cookie("PHPSESSID", "i0e3vthukcm216v8k8gkaoahg7", "dxbmpx.tongliannet.com", "/", null));
//            webDriver.manage().addCookie(new Cookie("exam_currentuser", "%25D9%25A8r%259E%25F4%25DF%25AD%259DckT%25A3%2595%25A5%25EB%25D7%25A8%25D2%25EE%25DF%25D8%25DE%2599%2595Tk%25A3l%25AD%25A8%255B%2595%25AB%25A5%25A5%25A0Rl%25A5jag%25B2%2590%25AC%25C9%25EC%25DF%25DC%25DB%259E%25A1%2593%25A3%25A3%25A9%25E7%25E0%259D%2586%25B4%25DF%25AD%259FbkTed%2597%25B1%25D0k%2597%25AB%25D0%25A9%25A3c%2592cd%2594%2596%25AD%25D2k%25C6%25AE%25D2%25A6%25D2%2560hfie%2598%25A8%2590t%25D7%25B3%259D%25A9%25A6R%25A4%2597%25A3%25A3%259B%25E7%25DC%25A9%25C5%25EC%25DF%25EA%25DB%25A2%2595%25A5Rk%25A5%25B2%25A7s%2586%25E3%25D0%25E6%25E1%2599%25AA%25A7%2591%259ET%25B3%25E1s%259D%25B3%258E%25E6%25D1%25A3%25A4%259B%259F%259E%259B%25E8%2590t%25D7%25B3%259D%25A6%25A6Rbbf%255Ee%25B0%259Cj%2595%25AE%259A%25A5%25A0Rl%25A5jaf%25B2%2590%25AC%25C9%25EC%25DF%25DC%25DB%259E%2598%25A4%259F%25A5%25A2%25E1%25D2%255B%259F%25EC%25A6%25A4%25A6RiTk%25A3l%25A9%25A4s%2586%25EC%25D1%25E6%25DF%2599%25A0%25A0%259C%259F%2599%25E1%25DC%25AD%25CD%25E6%25D1%2595%25A7%2599kcfbk%25AB%25A4o%2594%25B0%25A3%25AE%25DFjbgjR%25A5%25DD%25E1%25AC%25CD%25E8%25DA%25E8%25DF%2595%25A3%25A0%2591%259D%2597%259A%25A9%25AC%259E%25AA%25A4%25AD%258Eafbdcb%25A9%25A7q%259D%25A9%259E%25A3%25A1%2560aceRm%25EB%25A8j%259A%25B3%258E%25E6%25D1%25A3%25A4%259B%259F%259E%25A6%25E1%25DB%259E%25D0%25E2%25D9%25DC%25E0Rl%259Bjah%25AA%25A7l%259A%25AF%259C%25AA%25A3k%25A4lijT%25EB%25D3%25AC%25D7%25E2%25DB%25E1%25D5%2594Sm%25A3jd%25AE%25A8%255B%25CD%25A9%25D1%25A6%25E2%25A4%2599%25A7%259B%2593%259F%25AA%259Fo%25DA%25B1%25D7%25AB%25D3%259B%2592%25A1%2591%2598%2599%25AF%2590t%25E1", "dxbmpx.tongliannet.com", "/", null));
//            webDriver.manage().addCookie(new Cookie("exam_knowsid", "4", "dxbmpx.tongliannet.com", "/", null));
//            webDriver.manage().addCookie(new Cookie("exam_number", "1", "dxbmpx.tongliannet.com", "/", null));//题号
//            webDriver.manage().addCookie(new Cookie("exam_questype", "1", "dxbmpx.tongliannet.com", "/", null));//题型号{1：单选题（257） 2：多选题（261） 3：判断题（229） 4：填空（66） 5：必对题（28）}
//
//        }
        List<ExamItem> examItems = new ArrayList<>();
        for (int i = 1; i <= 28; i++) {
            System.out.println("current question num:  " + i);
            webDriver.manage().deleteAllCookies();
            webDriver.manage().addCookie(new Cookie("PHPSESSID", "i0e3vthukcm216v8k8gkaoahg7", "dxbmpx.tongliannet.com", "/", null));
            webDriver.manage().addCookie(new Cookie("exam_currentuser", "%25D9%25A8r%259E%25F4%25DF%25AD%259DckT%25A3%2595%25A5%25EB%25D7%25A8%25D2%25EE%25DF%25D8%25DE%2599%2595Tk%25A3l%25AD%25A8%255B%2595%25AB%25A5%25A5%25A0Rl%25A5jag%25B2%2590%25AC%25C9%25EC%25DF%25DC%25DB%259E%25A1%2593%25A3%25A3%25A9%25E7%25E0%259D%2586%25B4%25DF%25AD%259FbkTed%2597%25B1%25D0k%2597%25AB%25D0%25A9%25A3c%2592cd%2594%2596%25AD%25D2k%25C6%25AE%25D2%25A6%25D2%2560hfie%2598%25A8%2590t%25D7%25B3%259D%25A9%25A6R%25A4%2597%25A3%25A3%259B%25E7%25DC%25A9%25C5%25EC%25DF%25EA%25DB%25A2%2595%25A5Rk%25A5%25B2%25A7s%2586%25E3%25D0%25E6%25E1%2599%25AA%25A7%2591%259ET%25B3%25E1s%259D%25B3%258E%25E6%25D1%25A3%25A4%259B%259F%259E%259B%25E8%2590t%25D7%25B3%259D%25A6%25A6Rbbf%255Ee%25B0%259Cj%2595%25AE%259A%25A5%25A0Rl%25A5jaf%25B2%2590%25AC%25C9%25EC%25DF%25DC%25DB%259E%2598%25A4%259F%25A5%25A2%25E1%25D2%255B%259F%25EC%25A6%25A4%25A6RiTk%25A3l%25A9%25A4s%2586%25EC%25D1%25E6%25DF%2599%25A0%25A0%259C%259F%2599%25E1%25DC%25AD%25CD%25E6%25D1%2595%25A7%2599kcfbk%25AB%25A4o%2594%25B0%25A3%25AE%25DFjbgjR%25A5%25DD%25E1%25AC%25CD%25E8%25DA%25E8%25DF%2595%25A3%25A0%2591%259D%2597%259A%25A9%25AC%259E%25AA%25A4%25AD%258Eafbdcb%25A9%25A7q%259D%25A9%259E%25A3%25A1%2560aceRm%25EB%25A8j%259A%25B3%258E%25E6%25D1%25A3%25A4%259B%259F%259E%25A6%25E1%25DB%259E%25D0%25E2%25D9%25DC%25E0Rl%259Bjah%25AA%25A7l%259A%25AF%259C%25AA%25A3k%25A4lijT%25EB%25D3%25AC%25D7%25E2%25DB%25E1%25D5%2594Sm%25A3jd%25AE%25A8%255B%25CD%25A9%25D1%25A6%25E2%25A4%2599%25A7%259B%2593%259F%25AA%259Fo%25DA%25B1%25D7%25AB%25D3%259B%2592%25A1%2591%2598%2599%25AF%2590t%25E1", "dxbmpx.tongliannet.com", "/", null));
            webDriver.manage().addCookie(new Cookie("exam_knowsid", "4", "dxbmpx.tongliannet.com", "/", null));
            webDriver.manage().addCookie(new Cookie("exam_number", String.valueOf(i), "dxbmpx.tongliannet.com", "/", null));//题号
            webDriver.manage().addCookie(new Cookie("exam_questype", "5", "dxbmpx.tongliannet.com", "/", null));//题型号{1：单选题（257） 2：多选题（261） 3：判断题（229） 4：填空（66） 5：必对题（28）}
            try {
                webDriver.get(URL);
                Actions action = new Actions(webDriver);
                WebDriverWait wait = new WebDriverWait(webDriver, SELENIUM_TIME_SECONDS);
                List<WebElement> choiceElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("choice")));
                List<WebElement> answerElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("td")));
                WebElement btnElem = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.linkText("查看答案"))).get(0);
                btnElem.click();
                WebElement body = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("body"))).get(0);

                List<WebElement> main = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("main")));


                StringBuilder curQuestion = new StringBuilder();
                curQuestion.append(main.get(2).getText());
                StringBuilder curAnswer = new StringBuilder();
                curAnswer.append(answerElements.get(1).getText());
                curAnswer.append("\n");
                examItems.add(new ExamItem(curQuestion.toString(), curAnswer.toString()));

//                WebElement quesElem = choiceElements.get(0);
//                curQuestion.append(quesElem.getText());
//                curQuestion.append("\n");
//                WebElement choiceElem = choiceElements.get(1);
//                curQuestion.append(choiceElem.getText());
//                curQuestion.append("\n");


//                WebElement infoElement = answerElements.get(0);
//                List<WebElement> elements = infoElement.findElements(By.tagName("td"));
//                curAnswer.append(elements.get(1).getText());
            } catch (
                    Exception ex) {
            } finally {
//                webDriver.close();
//                webDriver.quit();
            }
//            System.out.println(examItems);
        }
        FileUtil.writeText(examItems, "E:\\1-JUST\\5-common\\2-doc\\5-部门\\4-资质申请\\保密制度考试\\考题\\必对.txt");
        return "mJson";
    }

    @Data
    @AllArgsConstructor
    static class ExamItem {
        private String question;
        private String answer;

        @Override
        public String toString() {
            return question + answer;
        }
    }
}
