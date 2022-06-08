package com.dhlk.light.factoryconstruction.pojo.entity.smartlamps;


import com.dhlk.light.factoryconstruction.enums.HumanFeelTriggerStatusEnum;
import com.dhlk.light.factoryconstruction.pojo.entity.ReportDetailData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 智能灯具 设备数据 人感状态上报
 * @author yangfan
 * @since 2021-08-10
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "HumanFeelData", description = "人感状态")
public class HumanFeelData extends ReportDetailData {

    /**
     * 人感状态 0未触发 1触发
     * @see HumanFeelTriggerStatusEnum
     */
    @ApiModelProperty(value = "人感状态 0未触发 1触发")
    private String humanFeelStatus;


}
