package model;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.WebDriver;
import util.ConfigHelper;

import java.io.File;

import static util.SysPathUtil.getPath;

/**
 * @author : suiyuan
 * @description : 环境变量
 * @date : Created in 2020-01-30 10:46
 * @modified by :
 **/
@Log4j
public class EnvironmentInfo {

    public static ConfigHelper configHelper;

    public static final long DEFAULT_PERIOD = 1 * 6 * 1000;

    public static boolean Init() {
        String configPath = (getPath() + "/env.properties").replace("//", "/");
        String ChromeDriverPath = (getPath() + "/chromedriver.exe").replace("//", "/");
        if (!(new File(configPath)).exists()) {
            log.info("未找到配置文件：" + configPath);
            return false;
        }
        if (!(new File(ChromeDriverPath)).exists()) {
            log.info("未找到ChromeDriver文件：" + ChromeDriverPath);
            return false;
        }

        System.getProperties().setProperty("webdriver.chrome.driver", ChromeDriverPath);
        EnvironmentInfo.configHelper = new ConfigHelper(configPath);
        return true;
    }

}
