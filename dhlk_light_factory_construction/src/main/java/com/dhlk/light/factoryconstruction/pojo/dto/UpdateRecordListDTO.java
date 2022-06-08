package com.dhlk.light.factoryconstruction.pojo.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 更新记录列表请求DTO
 * @author yangfan
 * @since 2021-08-18
 */
@Data
public class UpdateRecordListDTO {

    @ApiModelProperty(value = "页码",notes = "默认第一页,从1开始")
    private Integer pageNumber;

    @ApiModelProperty(value = "页大小",notes = "默认十条")
    private Integer pageSize;

    /**
     * 查询开始时间
     */
    @ApiModelProperty(value = "查询开始时间(yyyy-MM-dd)")
    private String queryStartTime;

    /**
     * 查询开始时间
     */
    @ApiModelProperty(hidden = true)
    private String startTime;

    /**
     * 查询结束时间
     */
    @ApiModelProperty(hidden = true)
    private String endTime;


    /**
     * 设备sn号
     */
    @ApiModelProperty(value = "设备sn号")
    private String sn;

    /**
     * 命令类型
     */
    @ApiModelProperty(value = "命令类型")
    private String commandType;
}
