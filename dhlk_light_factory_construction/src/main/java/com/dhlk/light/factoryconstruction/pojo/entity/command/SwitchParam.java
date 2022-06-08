package com.dhlk.light.factoryconstruction.pojo.entity.command;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 继电器开关参数
 * @author yangfan
 * @since 2021-08-12
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("继电器开关参数")
public class SwitchParam extends CommandParam{
    /**
     * 开关标识
     * @see com.dhlk.light.factoryconstruction.enums.SwitchFlagEnum
     */
    @ApiModelProperty("开关标识")
    private String switchFlag;
}
