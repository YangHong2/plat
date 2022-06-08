package com.dhlk.light.factoryconstruction.pojo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 设备数据信息
 * @author yangfan
 * @since 2021-08-16
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeviceDataInfo {

    /**
     * 主键ID
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
     * wifi配置
     */
    @ApiModelProperty(value = "wifi配置")
    @TableField("wifi_config")
    private String wifiConfig;

    /**
     * 人感配置
     */
    @ApiModelProperty(value = "人感配置")
    @TableField("human_config")
    private String humanConfig;

    /**
     * 光感配置
     */
    @ApiModelProperty(value = "光感配置")
    @TableField("light_config")
    private String lightConfig;

    /**
     * 上报时隙
     */
    @ApiModelProperty(value = "上报时隙")
    @TableField("time_slot")
    private String timeSlot;

    /**
     * 固件版本
     */
    @ApiModelProperty(value = "固件版本")
    @TableField("version")
    private String version;

    /**
     * 亮度值
     */
    @ApiModelProperty(value = "亮度值")
    @TableField("brightness")
    private String brightness;

    /**
     * 绑定AP 0 未绑定 1 已绑定
     * 默认未绑定
     */
    @ApiModelProperty(value = "绑定AP")
    @TableField("ap")
    private Boolean ap;

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
