package com.dhlk.entity.sub;

import java.io.Serializable;

/**
 * 金币表(Gold)实体类
 *
 * @author xkliu
 * @since 2021-03-16 09:34:14
 */
public class Gold implements Serializable {
    private static final long serialVersionUID = -92450753007871093L;

    private Integer id;
    /**
     * 来源类型
     */
    private String sourceType;
    /**
     * 金币数量
     */
    private Double goldNum;
    /**
     * 余额
     */
    private Double balance;
    /**
     * 企业id
     */
    private Integer userId;
    /**
     * 创建时间
     */
    private String createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public Double getGold() {
        return goldNum;
    }

    public void setGold(Double gold) {
        this.goldNum = gold;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

}
