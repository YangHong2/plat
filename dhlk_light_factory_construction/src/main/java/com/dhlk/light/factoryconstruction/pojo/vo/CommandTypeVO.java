package com.dhlk.light.factoryconstruction.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 命令类型列表查询VO
 * @author yangfan
 * @since 2021-08-25
 */
@Data
public class CommandTypeVO {

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
     * 命令描述
     */
    @ApiModelProperty(value = "命令描述")
    private String commandDesc;
}
