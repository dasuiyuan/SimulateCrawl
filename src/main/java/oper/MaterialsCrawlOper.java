package oper;

import lombok.extern.log4j.Log4j;
import util.MaterialsCrawlerUtil;

/**
 * @author : suiyuan
 * @description : 疫情物资需求信息类
 * @date : Created in 2020-02-01 11:42
 * @modified by :
 **/
@Log4j
public class MaterialsCrawlOper implements ICrawlOper {
    @Override
    public void doCrawl() {
        try {
            MaterialsCrawlerUtil.getMaterialRequirements();
        } catch (Exception ex) {
            log.error(ex, ex);
        }
    }
}
