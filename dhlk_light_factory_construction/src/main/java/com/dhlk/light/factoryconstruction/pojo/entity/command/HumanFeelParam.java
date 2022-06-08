package com.dhlk.light.factoryconstruction.pojo.entity.command;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 设置人感
 * @author lzhang
 * @date 2021/8/16
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("设置人感")
public class HumanFeelParam extends CommandParam{
    //开关
    @ApiModelProperty("开关")
    private String humanFlag;
    //触发延迟时间
    @ApiModelProperty("触发延迟时间")
    private String continuedTime;
    //未触发渐变时间
    @ApiModelProperty("未触发渐变时间")
    private String gradientTime;
    //人感触发最高亮度
    @ApiModelProperty("人感触发最高亮度")
    private String brightnessHighest;
    //人感未触发最低亮度
    @ApiModelProperty("人感未触发最低亮度")
    private String brightnessLowest;

}
