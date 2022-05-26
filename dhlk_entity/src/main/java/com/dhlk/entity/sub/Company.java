package com.dhlk.entity.sub;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 企业管理(Company)实体类
 *
 * @author xkliu
 * @since 2021-03-12 09:20:57
 */
@Data
public class Company implements Serializable {
    private static final long serialVersionUID = 766680904185800009L;
    /**
     * 主键
     */
    private Integer id;
    /**
     * 企业名称
     */
    private String companyName;
    /**
     * 注册资金
     */
    private String capital;
    /**
     * 公司详细地址
     */
    private String companyAddress;
    /**
     * 企业性质
     */
    private String companyNature;
    /**
     * 每年营业额
     */
    private String turnoverYear;
    /**
     * 法人
     */
    private String legalPerson;
    /**
     * 法人证件号
     */
    private String legalNum;
    /**
     * 企业规模
     */
    private String companyScale;
    /**
     * 行业
     */
    private String business;
    /**
     * 企业类型
     */
    private String companyType;
    /**
     * 工商营业执照(附件id,逗号隔开)
     */
    private String companyLicense;
    /**
     * 工商执照有效期
     */
    private String licenseValidity;
    /**
     * 社会统一信用代码
     */
    private String creditCode;
    /**
     * 法人身份证(附件id,逗号隔开)
     */
    private String identityCard;
    /**
     * 公司授权书(附件id,逗号隔开)
     */
    private String companyAuthorization;
    /**
     * 审核状态 0 待审核 1 审核通过 2 审核不通过
     */
    private Integer auditStatus;
    /**
     * 审核理由
     */
    private String auditReason;
    /**
     * 是否拉入黑名单(0 正常 1 黑名单)
     */
    private Integer isBlacklist;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 审核时间
     */
    private String auditTime;
    /**
     * 拉黑原因
     */
    private String blacklistReason;

}
