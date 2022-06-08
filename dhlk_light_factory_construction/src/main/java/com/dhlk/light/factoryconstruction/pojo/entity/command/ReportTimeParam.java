package com.dhlk.light.factoryconstruction.pojo.entity.command;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 设置实时上报时隙
 * @author lzhang
 * @date 2021/8/16
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("设置实时上报时隙")
public class ReportTimeParam extends CommandParam{
    // 时隙参数
    @ApiModelProperty("时隙参数")
    private String TimeSlot;
}
