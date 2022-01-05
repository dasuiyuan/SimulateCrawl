package model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author : suiyuan
 * @description : 疫情物资
 * @date : Created in 2020-01-31 15:35
 * @modified by :
 **/
@Accessors(chain = true)
@Data
@NoArgsConstructor
@ToString
public class EpidemicMaterialRequirement {

    /**
     * 医院名称
     */
    private String hospitalName;

    /**
     * 物资列表
     */
    private List<String> materialList;

    /**
     * 医院地址
     */
    private String address;

    /**
     * 医院电话
     */
    private String phone;

    /**
     * 消息来源
     */
    private String source;

}
