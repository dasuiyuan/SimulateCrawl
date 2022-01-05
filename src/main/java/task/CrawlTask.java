package task;

import oper.ICrawlOper;
import java.util.TimerTask;

/**
 * @author : suiyuan
 * @description : 爬虫任务
 * @date : Created in 2020-01-29 22:04
 * @modified by :
 **/
public class CrawlTask extends TimerTask {

    /**
     *  爬虫操作类
     */
    private ICrawlOper crawlOper;

    public CrawlTask(ICrawlOper crawlOper) {
        this.crawlOper = crawlOper;
    }

    @Override
    public void run() {
        crawlOper.doCrawl();
    }
}
