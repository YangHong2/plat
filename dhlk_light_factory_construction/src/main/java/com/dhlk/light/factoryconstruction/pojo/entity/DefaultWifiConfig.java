package com.dhlk.light.factoryconstruction.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * wifi默认配置实体
 * @author yangfan
 * @since 2021-08-19
 */
@Data
public class DefaultWifiConfig {

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * wifi模块
     */
    @ApiModelProperty(value = "wifi模块")
    @TableField("wifi_module")
    private String wifiModule;


    /**
     * 频段
     */
    @ApiModelProperty(value = "频段")
    @TableField("frequency_band")
    private String frequencyBand;

    /**
     * ssid
     */
    @ApiModelProperty(value = "ssid")
    @TableField("ssid")
    private String ssid;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    @TableField("password")
    private String password;

    /**
     * service ip"
     */
    @ApiModelProperty(value = "service ip")
    @TableField("service_ip")
    private String serviceIp;



    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    @TableField(value = "update_time")
    private LocalDateTime updateTime;
}
