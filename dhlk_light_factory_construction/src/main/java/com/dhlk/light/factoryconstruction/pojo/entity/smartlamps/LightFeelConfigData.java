package com.dhlk.light.factoryconstruction.pojo.entity.smartlamps;

import com.dhlk.light.factoryconstruction.pojo.entity.ReportDetailData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 光感配置处理
 * @date 2021/8/16
 * @author lzhang
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "LightFeelConfigData", description = "光感配置")
public class LightFeelConfigData extends ReportDetailData {

    @ApiModelProperty("光感开关 0关 1开")
    private String onOff;

    @ApiModelProperty("照度上限值")
    private String illumiHighest;

    @ApiModelProperty("照度上限值对应亮度最小值")
    private String illumiHighestMin;

    @ApiModelProperty("照度下限值")
    private String illumiLowest;

    @ApiModelProperty("照度下限值对应亮度亮度最大值")
    private String illumiLowestMax;
    /**
     * 应用模式选择
     */
    @ApiModelProperty("应用模式选择")
    private String illumiMode;
}
