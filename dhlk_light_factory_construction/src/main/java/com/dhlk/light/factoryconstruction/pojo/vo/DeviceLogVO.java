package com.dhlk.light.factoryconstruction.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 命令日志VO
 * @author yangfan
 * @since 2021-08-24
 */
@Data
public class DeviceLogVO {

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Long id;

    /**
     * 设备sn号
     */
    @ApiModelProperty(value = "设备sn号")
    private String sn;

    /**
     * 消息类型
     */
    @ApiModelProperty(value = "消息类型")
    private String messageType;


    /**
     * 命令类型
     */
    @ApiModelProperty(value = "命令类型")
    private String commandType;

    /**
     * 命令
     */
    @ApiModelProperty(value = "消息")
    private String message;

    /**
     * 时间
     */
    @ApiModelProperty(value = "时间")
    private LocalDateTime time;


}
