package com.dhlk.light.test.entity;
import lombok.Data;

/**
 * 光感配置
 */
@Data
public class IntensityInfo {
    private Integer on_off =1; //光感开关
    private Integer illumi_top; //照度上限值
    private Integer illumi_top_min=40; //照度上限值对应亮度最小值
    private Integer illumi_flr=1; //照度下限值
    private Integer illumi_flr_max=1; //照度下限值对应亮度亮度最大值
    private Integer illumi_mode =0;


}
