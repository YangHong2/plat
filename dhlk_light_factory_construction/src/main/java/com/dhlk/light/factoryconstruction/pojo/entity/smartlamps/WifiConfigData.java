package com.dhlk.light.factoryconstruction.pojo.entity.smartlamps;

import com.dhlk.light.factoryconstruction.pojo.entity.ReportDetailData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 智能灯具 设备数据 wifi配置上报
 * @author yangfan
 * @since 2021-08-13
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "WifiConfigData", description = "wifi配置")
public class WifiConfigData extends ReportDetailData {

    /**
     * 模块
     * @see com.dhlk.light.factoryconstruction.enums.WifiModuleEnum
     */
    @ApiModelProperty(value = "wifi模块")
    private String module;

    /**
     * 双频
     * @see com.dhlk.light.factoryconstruction.enums.DualFrequencyEnum
     */
    @ApiModelProperty(value = "双频")
    private String dualFrequency;

    /**
     * ssid
     */
    @ApiModelProperty(value = "ssid")
    private String ssId;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String password;

    /**
     * ip
     */
    @ApiModelProperty(value = "ip")
    private String ip;
}
