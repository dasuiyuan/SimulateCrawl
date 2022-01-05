package oper;

import lombok.extern.log4j.Log4j;
import util.PatientCrawlerUtil;

import java.io.IOException;

/**
 * @author : suiyuan
 * @description : 患者行程信息爬取类
 * @date : Created in 2020-02-01 11:40
 * @modified by :
 **/
@Log4j
public class PatientTripCrawlOper implements ICrawlOper {
    @Override
    public void doCrawl() {
        try {
            PatientCrawlerUtil.getDangerTripNosugartech();
        } catch (Exception e) {
            log.info(e, e);
        }
    }
}
