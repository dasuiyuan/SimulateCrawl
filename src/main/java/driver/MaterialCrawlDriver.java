package driver;

import model.EnvironmentInfo;
import oper.MaterialsCrawlOper;
import task.CrawlTask;

import java.util.Timer;

import static model.EnvironmentInfo.DEFAULT_PERIOD;

/**
 * @author : suiyuan
 * @description :
 * @date : Created in 2020-02-01 11:59
 * @modified by :
 **/
public class MaterialCrawlDriver {
    public static void main(String[] args) {
        System.out.println("MaterialCrawlDriver is running!");
        if (!EnvironmentInfo.Init()) {
            System.exit(-1);
        }
        long timePeriod = DEFAULT_PERIOD;
        if (args != null && args.length == 1) {
            long tp = Long.parseLong(args[0]);
            timePeriod = tp;
        }
        new Timer("materialCrawlTimer").schedule(new CrawlTask(new MaterialsCrawlOper()), 0, timePeriod);
    }
}
