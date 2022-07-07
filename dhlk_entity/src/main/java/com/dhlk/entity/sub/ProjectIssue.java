package com.dhlk.entity.sub;

import com.dhlk.domain.BaseFile;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 项目发布(ProjectIssue)实体类
 *
 * @author xkliu
 * @since 2021-03-12 09:21:15
 */
@Data
@ApiModel(value = "projectIssue", description = "项目发布")
public class ProjectIssue implements Serializable {
    private static final long serialVersionUID = 606543280658214105L;

    /**
     * id
     */
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;

    /**
     * 项目名称
     */
    @ApiModelProperty(value = "项目名称")
    private String projectName;
    /**
     * 是否立项
     */
    @ApiModelProperty(value = "是否立项")
    private Integer isApproval;
    /**
     * 项目周期
     */
    @ApiModelProperty(value = "项目周期")
    private Integer projectCycle;
    /**
     * 交付方式
     */
    @ApiModelProperty(value = "交付方式")
    private String deliveryMethod;
    /**
     * 付款方式
     */
    @ApiModelProperty(value = "付款方式")
    private Integer paymentMethod;
    /**
     * 其他要求
     */
    @ApiModelProperty(value = "其他要求")
    private String otherRequired;
    /**
     * 项目责任人
     */
    @ApiModelProperty(value = "项目责任人")
    private String projectManagerr;
    /**
     * 交付产物
     */
    @ApiModelProperty(value = "交付产物")
    private String paymentProduct;

    /**
     * 有效期
     */
    @ApiModelProperty(value = "有效期")
    private String validity;
    /**
     * 联系方式
     */
    @ApiModelProperty(value = "联系方式")
    private String contactWay;
    /**
     * 项目预算
     */
    @ApiModelProperty(value = "项目预算")
    private Integer projectBudget;
    /**
     * 项目计划开始时间
     */
    @ApiModelProperty(value = "项目计划开始时间")
    private String startTime;
    /**
     * 项目类别
     */
    @ApiModelProperty(value = "项目类别")
    private Integer projectType;
    /**
     * 关联项目
     */
    @ApiModelProperty(value = "项目名称")
    private Integer projectRelated;
    /**
     * 资金类型
     */
    @ApiModelProperty(value = "资金类型")
    private Integer fundsType;
    /**
     * 需求介绍
     */
    @ApiModelProperty(value = "需求介绍")
    private String demandIntroduce;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
     * 附件
     */
    @ApiModelProperty(value = "附件")
    private String attachmentAddr;
    /**
     * 企业id
     */
    @ApiModelProperty(value = "企业id", hidden = true)
    private Integer companyId;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", hidden = true)
    private String createTime;
    /**
     * 评分
     */
    @ApiModelProperty(value = "评分", hidden = true)
    private Integer score;
    /**
     * 审核结果 0未审核 1通过 2不通过
     */
    @ApiModelProperty(value = "审核结果", hidden = true)
    private Integer auditResult;

    /**
     * 类型
     */
    @ApiModelProperty(value = "类型", hidden = true)
    private String types;

    /**
     * 不通过理由
     */
    @ApiModelProperty(value = "不通过理由", hidden = true)
    private String noReason;

    /**
     * 公司名称
     */
    @ApiModelProperty(value = "公司名称", hidden = true)
    private String companyName;
    /**
     *项目进度
     */
    @ApiModelProperty(value = "项目进度", hidden = true)
    private Integer progressInt;
    /**
     * 项目进度说明
     */
    @ApiModelProperty(value = "项目进度说明", hidden = true)
    private String progressString;

    @ApiModelProperty(hidden = true)
    private Integer financial;

    @ApiModelProperty(hidden = true)
    private Integer consulting;

    @ApiModelProperty(hidden = true)
    private Integer delivery;

    @ApiModelProperty(hidden = true)
    private Integer proClose;

    @ApiModelProperty(hidden = true)
    private String gress;

    @ApiModelProperty(hidden = true)
    private List<FinancialProvider> financialProvider;

    @ApiModelProperty(hidden = true)
    private List<ConsultingProvider> consultingProvider;

    @ApiModelProperty(hidden = true)
    private List<DevProduce> devProduce;

    @ApiModelProperty(hidden = true)
    private List<DevProduce> installation;

    @ApiModelProperty(hidden = true)
    private List<DeliveryApplyFor> deliveryApplyFor;

    @ApiModelProperty(hidden = true)
    private List<DeliveryApplyFor> installApply;

    @ApiModelProperty(hidden = true)
    private List<ProjectClose> projectClose;

    @ApiModelProperty(hidden = true)
    private List<BaseFile> file;


}
