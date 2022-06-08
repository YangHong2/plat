package com.dhlk.light.factoryconstruction.pojo.entity.smartlamps;

import com.dhlk.light.factoryconstruction.pojo.entity.ReportDetailData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author lzhang
 * @Description 设置实时上报时隙
 * @date 2021/8/17
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "TimeSlotData", description = "设置实时上报时隙")
public class TimeSlotData extends ReportDetailData {
    /**
     * 数据：1byte（单位：s,范围：1~9）
     */
    @ApiModelProperty(value = "数据")
    private String timeValue;

}
