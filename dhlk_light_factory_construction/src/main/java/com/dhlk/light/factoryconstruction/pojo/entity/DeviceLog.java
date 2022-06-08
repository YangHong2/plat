package com.dhlk.light.factoryconstruction.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 命令日志实体
 * @author yangfan
 * @since 2021-08-24
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("device_log")
public class DeviceLog {

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 设备sn号
     */
    @ApiModelProperty(value = "设备sn号")
    @TableField("sn")
    private String sn;

    /**
     * 消息类型
     */
    @TableField("message_type")
    private String messageType;


    /**
     * 消息类型
     */
    @TableField("command_type")
    private String commandType;

    /**
     * 消息
     */
    @TableField("message")
    private String message;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
