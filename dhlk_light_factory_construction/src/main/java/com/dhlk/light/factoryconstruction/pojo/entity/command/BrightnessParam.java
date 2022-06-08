package com.dhlk.light.factoryconstruction.pojo.entity.command;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 设置灯亮度
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "设置灯亮度")
public class BrightnessParam  extends CommandParam{

    /**
     * 亮度值
     */
    @ApiModelProperty("亮度值")
    private String brightness;
}
