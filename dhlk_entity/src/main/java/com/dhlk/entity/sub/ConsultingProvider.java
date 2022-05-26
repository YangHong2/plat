package com.dhlk.entity.sub;

import com.dhlk.domain.BaseFile;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 咨询服务商(ConsultingProvider)实体类
 *
 * @author xkliu
 * @since 2021-03-12 09:21:00
 */
@Data
@ApiModel(value = "consultingProvider", description = "咨询服务商")
public class ConsultingProvider implements Serializable {
    private static final long serialVersionUID = 534153252242458112L;

    /**
     * id
     */
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;
    /**
     * 企业名称
     */
    @ApiModelProperty(value = "企业名称")
    private String companyName;
    /**
     * 合同金额
     */
    @ApiModelProperty(value = "合同金额")
    private BigDecimal contractAmount;
    /**
     * 支付方式
     */
    @ApiModelProperty(value = "支付方式")
    private Integer paymentMethod;
    /**
     * 联系人
     */
    @ApiModelProperty(value = "联系人")
    private String linkman;
    /**
     * 联系方式
     */
    @ApiModelProperty(value = "联系方式")
    private String contactWay;
    /**
     * 合同信息
     */
    @ApiModelProperty(value = "合同信息")
    private String contractPic;
    /**
     * 项目id
     */
    @ApiModelProperty(value = "项目id")
    private Integer projectId;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", hidden = true)
    private String createTime;

    /**
     * 企业id
     */
    @ApiModelProperty(value = "企业id")
    private Integer companyId;

    /**
     * 类型
     */
    @ApiModelProperty(value = "类型", hidden = true)
    private String types;


    /*************************以下是项目相关的所有字段**********************************/
    /**
     * 项目名称
     */
    @ApiModelProperty(value = "项目名称", hidden = true)
    private String projectName;
    /**
     * 是否立项
     */
    @ApiModelProperty(value = "是否立项", hidden = true)
    private Integer isApproval;
    /**
     * 项目周期
     */
    @ApiModelProperty(value = "项目周期", hidden = true)
    private Integer projectCycle;
    /**
     * 交付方式
     */
    @ApiModelProperty(value = "交付方式", hidden = true)
    private String deliveryMethod;
    /**
     * 付款方式
     */
    @ApiModelProperty(value = "付款方式", hidden = true)
    private Integer proPaymentMethod;
    /**
     * 其他要求
     */
    @ApiModelProperty(value = "其他要求", hidden = true)
    private String otherRequired;
    /**
     * 项目责任人
     */
    @ApiModelProperty(value = "项目责任人", hidden = true)
    private String projectManagerr;
    /**
     * 交付产物
     */
    @ApiModelProperty(value = "交付产物", hidden = true)
    private String paymentProduct;

    /**
     * 有效期
     */
    @ApiModelProperty(value = "有效期", hidden = true)
    private String validity;
    /**
     * 联系方式
     */
    @ApiModelProperty(value = "联系方式", hidden = true)
    private String projectContactWay;
    /**
     * 项目预算
     */
    @ApiModelProperty(value = "项目预算", hidden = true)
    private Integer projectBudget;
    /**
     * 项目计划开始时间
     */
    @ApiModelProperty(value = "项目计划开始时间", hidden = true)
    private String startTime;
    /**
     * 项目类别
     */
    @ApiModelProperty(value = "项目类别", hidden = true)
    private Integer projectType;
    /**
     * 关联项目
     */
    @ApiModelProperty(value = "项目名称", hidden = true)
    private Integer projectRelated;
    /**
     * 资金类型
     */
    @ApiModelProperty(value = "资金类型", hidden = true)
    private Integer fundsType;
    /**
     * 需求介绍
     */
    @ApiModelProperty(value = "需求介绍", hidden = true)
    private String demandIntroduce;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", hidden = true)
    private String remark;
    /**
     * 附件
     */
    @ApiModelProperty(value = "附件", hidden = true)
    private String attachmentAddr;
    /**
     * 企业id
     */
    @ApiModelProperty(value = "企业id", hidden = true)
    private Integer projectCompanyId;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", hidden = true)
    private String projectCreateTime;
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
     * 不通过理由
     */
    @ApiModelProperty(value = "不通过理由", hidden = true)
    private String noReason;

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
    private List<BaseFile> file;

}
