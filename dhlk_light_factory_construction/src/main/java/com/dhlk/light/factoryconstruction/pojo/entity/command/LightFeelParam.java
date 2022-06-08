package com.dhlk.light.factoryconstruction.pojo.entity.command;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 光感设置
 * @author lzhang
 * @date 2021/8/16
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("光感设置")
public class LightFeelParam extends CommandParam{
    //开关
    @ApiModelProperty("开关")
    private String onOff;
    //照度上限值
    @ApiModelProperty("照度上限值")
    private String illumiHighest;
    //照度上限值对应亮度最小值
    @ApiModelProperty("照度上限值对应亮度最小值")
    private String illumiHighestMin;
    //照度下限值
    @ApiModelProperty("照度下限值")
    private String illumiLowest;
    //照度下限值对应亮度亮度最大值
    @ApiModelProperty("照度下限值对应亮度亮度最大值")
    private String illumiLowestMax;
    //应用模式选择
    @ApiModelProperty("应用模式选择")
    private String illumiMode;
}
