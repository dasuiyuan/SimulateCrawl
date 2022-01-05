package driver;

import model.EnvironmentInfo;
import oper.PatientTripCrawlOper;
import task.CrawlTask;

import java.util.Timer;

import static model.EnvironmentInfo.DEFAULT_PERIOD;

/**
 * @author : suiyuan
 * @description :
 * @date : Created in 2020-02-01 11:59
 * @modified by :
 **/
public class PatientTripCrawlDriver {
    public static void main(String[] args) {
        System.out.println("PatientTripCrawlDriver is running!");
        if (!EnvironmentInfo.Init()) {
            System.exit(-1);
        }
        long timePeriod = DEFAULT_PERIOD;
        if (args != null && args.length == 1) {
            long tp = Long.parseLong(args[0]);
            timePeriod = tp;
        }
        new Timer("patientTripTimer").schedule(new CrawlTask(new PatientTripCrawlOper()), 0, timePeriod);
    }
}
