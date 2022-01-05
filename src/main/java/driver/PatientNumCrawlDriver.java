package driver;

import model.EnvironmentInfo;
import oper.PatientNumCrawlOper;
import task.CrawlTask;

import java.util.Timer;

import static model.EnvironmentInfo.DEFAULT_PERIOD;

/**
 * @author : suiyuan
 * @description :
 * @date : Created in 2020-02-01 11:57
 * @modified by :
 **/
public class PatientNumCrawlDriver {
    public static void main(String[] args) {
        System.out.println("PatientNumCrawlDriver is running!");
        if (!EnvironmentInfo.Init()) {
            System.exit(-1);
        }
        long timePeriod = DEFAULT_PERIOD;
        if (args != null && args.length == 1) {
            long tp = Long.parseLong(args[0]);
            timePeriod = tp;
        }
        new Timer("patientNumTimer").schedule(new CrawlTask(new PatientNumCrawlOper()), 0, timePeriod);
    }
}
