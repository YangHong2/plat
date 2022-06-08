package com.dhlk.light.factoryconstruction.pojo.entity.smartlamps;

import com.dhlk.light.factoryconstruction.enums.HumanFeelTriggerStatusEnum;
import com.dhlk.light.factoryconstruction.pojo.entity.ReportDetailData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author lzhang
 * @Description 光感状态上报
 * @date 2021/8/17
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "LightFeelData", description = "光感状态")
public class LightFeelData extends ReportDetailData {
    /**
     * 光感状态 单位:lx勒克斯,取值范围:0~65535
     */
    @ApiModelProperty(value = "光感数据")
    private String lightFeelValue;

}
