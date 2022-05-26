package com.dhlk.entity.sub;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户表(SubpackageUser)实体类
 *
 * @author xkliu
 * @since 2021-03-12 09:24:29
 */
@Data
public class SubpackageUser implements Serializable {
    private static final long serialVersionUID = 431530440795607501L;
    /**
     * 主键
     */
    private Integer id;
    /**
     * 用户名
     */
    private String loginName;
    /**
     * 密码
     */
    private String password;
    /**
     * 企业
     */
    private Integer companyId;
    /**
     * 企业邮箱
     */
    private String companyEmail;

    /**
     * 企业信息
     */
    private Company company;

    /**
     * 状态0 正常 1 禁用 2 删除
     */
    private Integer status;

    /**
     * 企业名称
     */
    private String companyName;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 拉黑原因
     */
    private String blacklistReason;
}
