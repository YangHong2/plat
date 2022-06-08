package com.dhlk.light.factoryconstruction.pojo.dto;

import com.dhlk.light.factoryconstruction.enums.CommandRequestEnum;
import com.dhlk.light.factoryconstruction.pojo.entity.command.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 发送指令请求DTO
 * @author yangfan
 * @since 2021-08-11
 */
@Data
@ApiModel("发送指令请求DTO")
public class SendCommandDTO {

    /**
     * 操作命令
     * @see CommandRequestEnum
     */
    @ApiModelProperty("命令类型ID")
    private Integer commandId;

    /**
     * 唯一标识列表
     */
    @ApiModelProperty("智能灯具ID集合")
    private List<String> idList;

    /**
     * 灯亮度设置
     * @see BrightnessParam
     */
    @ApiModelProperty("灯亮度设置")
    private BrightnessParam brightnessParam;

    /**
     * AP 复位
     * @see APParam
     */
    @ApiModelProperty("AP复位")
    private APParam apParam;

    /**
     * 设置实时上报时隙
     * @see ReportTimeParam
     */
    @ApiModelProperty("设置实时上报间隙")
    private ReportTimeParam reportTimeParam;

    /**
     * 设置人感
     * @see HumanFeelParam
     */
    @ApiModelProperty("设置人感")
    private HumanFeelParam humanFeelParam;

    /**
     * 设置光感
     * @see LightFeelParam
     */
    @ApiModelProperty("设置光感")
    private LightFeelParam lightFeelParam;

    /**
     * 设置wifi
     * @see WifiParam
     */
    @ApiModelProperty("设置wifi")
    private WifiParam wifiParam;


}
