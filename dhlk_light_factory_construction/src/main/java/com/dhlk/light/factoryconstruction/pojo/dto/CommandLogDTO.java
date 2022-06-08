package com.dhlk.light.factoryconstruction.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 命令日志列表请求DTO
 * @author yangfan
 * @since 2021-08-24
 */
@Data
public class CommandLogDTO {

    /**
     * 查询开始时间
     */
    @ApiModelProperty(value = "查询开始时间(yyyy-MM-dd)")
    private String queryStartTime;

    /**
     * 查询开始时间
     */
    @ApiModelProperty(hidden = true)
    private String startTime;

    /**
     * 查询结束时间
     */
    @ApiModelProperty(hidden = true)
    private String endTime;

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
     * websocket会话id
     */
    @ApiModelProperty(value = "会话id",required = true)
    private String sessionId;
}
