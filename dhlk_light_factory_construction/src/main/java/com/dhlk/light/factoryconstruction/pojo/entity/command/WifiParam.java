package com.dhlk.light.factoryconstruction.pojo.entity.command;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author lzhang
 * @Description 设置wifi配置
 * @date 2021/8/16
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("设置wifi配置")
public class WifiParam extends CommandParam{
    /**
     * 模块
     */
    @ApiModelProperty(value = "wifi模块")
    private String module;

    /**
     * 双频
     */
    @ApiModelProperty(value = "双频")
    private String dualFrequency;

    /**
     * ssid
     */
    @ApiModelProperty(value = "ssid")
    private String ssId;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String password;

    /**
     * ip
     */
    @ApiModelProperty(value = "ip")
    private String ip;
}
