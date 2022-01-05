package model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author : suiyuan
 * @description : 疫情总数信息
 * @date : Created in 2020-02-01 22:49
 * @modified by :
 **/
@Accessors(chain = true)
@Data
@NoArgsConstructor
@ToString
public class EpidemicSumInfo {
    /**
     * 确诊人数
     */
    private Integer confirmedCount;

    /**
     * 增加确诊人数
     */
    private Integer increaseConfirmedCount;

    /**
     * 疑似人数
     */
    private Integer suspectedCount;

    /**
     * 增加疑似人数
     */
    private Integer increaseSuspectedCount;

    /**
     * 重症人数
     */
    private Integer serviousCount;

    /**
     * 增加重症人数
     */
    private Integer increaseServiousCount;

    /**
     * 治愈人数
     */
    private Integer curedCount;

    /**
     * 增加治愈人数
     */
    private Integer increaseCuredCount;

    /**
     * 死亡人数
     */
    private Integer deadCount;

    /**
     * 增加死亡人数
     */
    private Integer increaseDeadCount;

}
