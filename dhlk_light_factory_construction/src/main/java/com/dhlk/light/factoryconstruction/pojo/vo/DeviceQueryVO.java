package com.dhlk.light.factoryconstruction.pojo.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

/**
 * 设备查询实体
 * @author wzx
 * @since 2021-08-11
 */
@Builder
@Data
public class DeviceQueryVO {
    /**
     * 监听端口号
     */
    @ApiModelProperty(value = "监听端口号",required = true)
    @NotEmpty(message = "监听端口号不能为空")
    private String port;
    /**
     * 设备类型 参考
     * {@link com.dhlk.light.factoryconstruction.enums.DeviceTypeEnum}
     */
    @ApiModelProperty(value = "设备类型")
    private String deviceType;
    /**
     * sn号
     */
    @ApiModelProperty(value = "sn号")
    private String sn;
    /**
     * 开关
     */
    @ApiModelProperty(value = "开关 0 关 1 开")
    private String onOff;
    /**
     * 人感开关
     */
    @ApiModelProperty(value = "人感开关 00关 01开")
    private String peopleOnOff;
    /**
     * 人感状态 0无人 1有人
     */
    @ApiModelProperty(value = "人感状态 0无人 1有人")
    private String peopleStatus;
    /**
     * 光感开关
     */
    @ApiModelProperty(value = "光感开关 0关 1开")
    private String lightOnOff;
    /**
     * 功率起
     */
    @ApiModelProperty(value = "功率起")
    private BigDecimal powerStart;
    /**
     * 功率止
     */
    @ApiModelProperty(value = "功率止")
    private BigDecimal powerEnd;
    /**
     * 固件版本
     */
    @ApiModelProperty(value = "固件版本")
    private String version;
    /**
     * websocket会话id
     */
    @ApiModelProperty(value = "会话id",required = true)
    private String sessionId;
}
