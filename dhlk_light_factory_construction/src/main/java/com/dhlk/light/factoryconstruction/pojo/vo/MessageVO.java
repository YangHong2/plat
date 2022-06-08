package com.dhlk.light.factoryconstruction.pojo.vo;


import com.dhlk.light.factoryconstruction.enums.MessageTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * websocket消息
 * @author wzx
 * @since 2021-08-11
 */
@Builder
@Data
@ApiModel(value = "MessageVO", description = "websocket消息")
public class MessageVO {
    /**
     * 端口号
     */
    @ApiModelProperty(value = "端口号")
    private String port;
    /**
     * 类型：0 定时推送设备数据，1 实时推送设备数据， 2实时推送执行反馈数据
     * @see MessageTypeEnum
     */
    @ApiModelProperty(value = "消息类型：0 定时所有设备消息，1 实时多条设备消息， 2 实时单条设备消息 3 实时单条执行消息 " +
            "4 DEBUG 上报命令消息推送 5 离线设备")
    private Integer type;
    /**
     * 消息内容
     */
    @ApiModelProperty(value = "消息内容")
    private Object content;
    /**
     * websocket会话id
     */
    @ApiModelProperty(value = "会话id")
    private String sessionId;


}
