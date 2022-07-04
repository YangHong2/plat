package com.dhlk.subcontract.dao.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "ProjectRecordsVO", description = "项目流程")
public class ProjectRecordsVO implements Serializable {
    private static final long serialVersionUID = -21991938212329893L;

    /**
     * 项目名称
     */
    @ApiModelProperty(value = "项目名称")
    private String projectName;

    /**
     * 项目创建时间
     */
    @ApiModelProperty(value = "项目创建时间")
    private String projectCreateTime;

    /**
     * 金融服务商
     */
    @ApiModelProperty(value = "金融服务商")
    private String providerName;

    /**
     * 投资时间
     */
    @ApiModelProperty(value = "投资时间")
    private String providerCreateTime;

    /**
     * 咨询服务商
     */
    @ApiModelProperty(value = "咨询服务商")
    private String companyName;

    /**
     * 咨询服务商确认时间
     */
    @ApiModelProperty(value = "咨询服务商确认时间")

    private String companyCreateTime;
}
