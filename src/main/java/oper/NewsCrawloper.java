package oper;

import lombok.extern.log4j.Log4j;
import util.NewsCrawlerUtil;

/**
 * @author : suiyuan
 * @description : 疫情新闻爬虫类
 * @date : Created in 2020-02-01 11:44
 * @modified by :
 **/
@Log4j
public class NewsCrawloper implements ICrawlOper {
    @Override
    public void doCrawl() {
        try {
            NewsCrawlerUtil.getEpidemicNews();
        } catch (Exception ex) {
            log.error(ex, ex);
        }
    }
}
