package com.dhlk.light.factoryconstruction.pojo.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 命令修改记录命令列表查询VO
 * @author yangfan
 * @since 2021-08-18
 */
@Data
public class UpdateRecordCommandTypeListVO {

    /**
     * 命令类型
     */
    @ApiModelProperty(value = "命令类型")
    private String commandType;

    /**
     * 命令描述
     */
    @ApiModelProperty(value = "命令描述")
    private String commandDesc;
}
