package oper;

import lombok.extern.log4j.Log4j;
import util.PatientCrawlerUtil;

/**
 * @author : suiyuan
 * @description : 患者数量信息爬取类
 * @date : Created in 2020-02-01 11:39
 * @modified by :
 **/
@Log4j
public class PatientNumCrawlOper implements ICrawlOper {
    @Override
    public void doCrawl() {
        try {
            PatientCrawlerUtil.getChinaEpidemicDXYInfo();
            PatientCrawlerUtil.getWorldEpidemicDXYinfo();
        } catch (Exception e) {
            log.error(e, e);
        }
    }
}
