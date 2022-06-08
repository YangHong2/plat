package com.dhlk.light.factoryconstruction.pojo.entity.smartlamps;


import com.dhlk.light.factoryconstruction.pojo.entity.ReportDetailData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 智能灯具 设备数据 执行反馈上报
 * @author yangfan
 * @since 2021-08-10
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "ExecuteCallBackData", description = "执行反馈")
public class ExecuteCallBackData extends ReportDetailData {

    /**
     * 执行命令
     */
    @ApiModelProperty(value = "执行命令")
    private String commandType;

    /**
     * 执行结果
     */
    @ApiModelProperty(value = "执行结果")
    private String executeReuslt;
}
