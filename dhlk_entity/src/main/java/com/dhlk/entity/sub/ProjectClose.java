package com.dhlk.entity.sub;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 关闭项目(ProjectClose)实体类
 *
 * @author xkliu
 * @since 2021-03-12 09:21:14
 */
@Data
@ApiModel(value = "projectClose", description = "关闭项目")
public class ProjectClose implements Serializable {
    private static final long serialVersionUID = 865256082177062016L;

    /**
     * id
     */
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;
    /**
     * 类型 0终止项目 1项目完成
     */
    @ApiModelProperty(value = "类型")
    private Integer type;
    /**
     * 原由
     */
    @ApiModelProperty(value = "原由")
    private String reason;
    /**
     * 审批建议
     */
    @ApiModelProperty(value = "审批建议")
    private String approvalSuggest;
    /**
     * 申请时间
     */
    @ApiModelProperty(value = "申请时间", hidden = true)
    private Date applyTime;
    /**
     * 审批时间
     */
    @ApiModelProperty(value = "审批时间")
    private String approvalTime;

    /**
     * 项目id
     */
    @ApiModelProperty(value = "项目id")
    private Integer projectId;
    /**
     * 审批结果 0不通过 1通过
     */
    @ApiModelProperty(value = "审批结果")
    private Integer auditResult;

    /**
     * 企业id
     */
    @ApiModelProperty(value = "企业id", hidden = true)
    private Integer companyId;

    /**
     * 项目名
     */
    @ApiModelProperty(value = "项目名")
    private String projectName;

    /**
     * 企业名
     */
    @ApiModelProperty(value = "企业名")
    private String companyName;

    /**
     * 项目类型
     */
    @ApiModelProperty(value = "项目类型")
    private String companyType;
}
