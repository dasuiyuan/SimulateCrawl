package util;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * @author : suiyuan
 * @description : 配置文件帮助类
 * @date : Created in 2020-01-30 10:49
 * @modified by :
 **/
public class ConfigHelper {
    private Properties prop;

    public ConfigHelper(String configFile) {
        prop = new Properties();
        try (FileReader reader = new FileReader(configFile)) {
            getProp().load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Properties getProp() {
        return prop;
    }
}
