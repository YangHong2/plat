package com.dhlk.light.factoryconstruction.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 消息类型列表查询VO
 * @author yangfan
 * @since 2021-08-25
 */
@Data
public class MessageTypeVO {

    /**
     * 消息类型
     */
    @ApiModelProperty(value = "消息类型")
    private String messageType;

    /**
     * 命令描述
     */
    @ApiModelProperty(value = "消息描述")
    private String messageDesc;
}
