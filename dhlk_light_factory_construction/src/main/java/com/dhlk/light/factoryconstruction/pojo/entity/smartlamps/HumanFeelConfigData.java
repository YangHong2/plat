package com.dhlk.light.factoryconstruction.pojo.entity.smartlamps;

import com.dhlk.light.factoryconstruction.enums.HumanFeelStatusEnum;
import com.dhlk.light.factoryconstruction.pojo.entity.ReportDetailData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 智能灯具 设备数据 人感配置上报
 * @author yangfan
 * @since 2021-08-13
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "HumanFeelConfigData", description = "人感配置")
public class HumanFeelConfigData extends ReportDetailData {

    /**
     * 人感开关状态 00关 01开
     * @see HumanFeelStatusEnum
     */
    @ApiModelProperty(value = "人感开关 0关 1开")
    private String status;

    /**
     * 触发延迟时间 单位秒
     */
    @ApiModelProperty(value = "触发延迟时间")
    private String triggerDelayTime ;

    /**
     * 未触发渐变时间 单位秒
     */
    @ApiModelProperty(value = "渐变时间")
    private String noTriggereFadeTime ;

    /**
     * 人感未触发最低亮度
     */
    @ApiModelProperty(value = "最低亮度")
    private String minBrightness ;

    /**
     * 人感触发最高亮度
     */
    @ApiModelProperty(value = "最高亮度")
    private  String maxBrightness ;
}
