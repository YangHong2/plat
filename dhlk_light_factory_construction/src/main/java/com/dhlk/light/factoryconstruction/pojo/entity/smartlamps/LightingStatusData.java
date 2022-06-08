package com.dhlk.light.factoryconstruction.pojo.entity.smartlamps;


import com.dhlk.light.factoryconstruction.pojo.entity.ReportDetailData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 智能灯具 设备数据 照明状态上报
 * @author yangfan
 * @since 2021-08-10
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "LightingStatusData", description = "照明状态")
public class LightingStatusData extends ReportDetailData {

    /**
     * 电压
     */
    @ApiModelProperty(value = "电压")
    private String voltage;

    /**
     * 电流
     */
    @ApiModelProperty(value = "电流")
    private String electricCurrent;

    /**
     * 功率
     */
    @ApiModelProperty(value = "功率")
    private String power;

    /**
     * 累计电能
     */
    @ApiModelProperty(value = "累计电能")
    private String totalEnergy;

    /**
     * 继电器状态
     */
    @ApiModelProperty(value = "开关 0 关 1 开")
    private String relayStatus;

    /**
     * 灯亮度
     */
    @ApiModelProperty(value = "灯亮度")
    private String lampBrightness;
}
