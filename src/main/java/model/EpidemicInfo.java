package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @author : suiyuan
 * @description : 疫情信息类
 * @date : Created in 2020-01-29 17:46
 * @modified by :
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EpidemicInfo {
    /**
     * 省份
     */
    private String provinceName;
    /**
     *  省份简称
     */
    private String provinceShortName;

    /**
     *  确诊人数
     */
    private Integer confirmedCount;

    /**
     *  疑似人数
     */
    private Integer suspectedCount;

    /**
     *  治愈人数
     */
    private Integer curedCount;

    /**
     *  死亡人数
     */
    private Integer deadCount;

    /**
     *  备注
     */
    private String comment;

    /**
     *  城市信息
     */
    List<CityEpidemicInfo> cities;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    private class CityEpidemicInfo {

        /**
         *  城市名称
         */
        private String cityName;
        /**
         *  确诊人数
         */
        private Integer confirmedCount;

        /**
         *  疑似人数
         */
        private Integer suspectedCount;

        /**
         *  治愈人数
         */
        private Integer curedCount;

        /**
         *  死亡人数
         */
        private Integer deadCount;
    }
}


