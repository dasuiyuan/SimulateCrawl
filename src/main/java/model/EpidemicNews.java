package model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author : suiyuan
 * @description : 疫情新闻
 * @date : Created in 2020-01-30 16:17
 * @modified by :
 **/
@Accessors(chain = true)
@Data
@NoArgsConstructor
@ToString
public class EpidemicNews {

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 时间
     */
    private String time;

    /**
     * 地区
     */
    private String region;

}
