package com.dhlk.light.factoryconstruction.pojo.entity;


import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 修改记录
 * @author wzx
 * @date 2021/8/16
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("device_his_ip")
@ApiModel(value = "DeviceHisIp", description = "设备历史ip实体类")
public class DeviceHisIp {
    /**
     * id
     */
    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 设备sn号
     */
    @ApiModelProperty(value = "设备sn号")
    @TableField("sn")
    private String sn;

    /**
     * 历史ip
     */
    @ApiModelProperty(value = "历史ip")
    @TableField("his_ip")
    private String hisIp;


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
