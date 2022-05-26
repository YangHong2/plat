package com.dhlk.entity.sub;

import java.io.Serializable;
import java.util.Date;

/**
 * 收付款信息(ReceivingInfo)实体类
 *
 * @author xkliu
 * @since 2021-03-12 09:23:21
 */
public class ReceivingInfo implements Serializable {
    private static final long serialVersionUID = -83014449679380228L;

    private Integer id;
    /**
     * 收款方
     */
    private Integer payee;
    /**
     * 收款方账号
     */
    private Integer payeeNum;
    /**
     * 开户行
     */
    private String openBank;
    /**
     * 收款账户名称
     */
    private String accountName;
    /**
     * 金额
     */
    private Double money;
    /**
     * 付款方
     */
    private Integer payer;
    /**
     * 时间
     */
    private Date time;
    /**
     * 备注
     */
    private String remark;
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 银行卡号
     */
    private Integer cardNo;
    /**
     * 凭据
     */
    private String evidence;
    /**
     * 项目id
     */
    private Integer projectId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPayee() {
        return payee;
    }

    public void setPayee(Integer payee) {
        this.payee = payee;
    }

    public Integer getPayeeNum() {
        return payeeNum;
    }

    public void setPayeeNum(Integer payeeNum) {
        this.payeeNum = payeeNum;
    }

    public String getOpenBank() {
        return openBank;
    }

    public void setOpenBank(String openBank) {
        this.openBank = openBank;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Integer getPayer() {
        return payer;
    }

    public void setPayer(Integer payer) {
        this.payer = payer;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getCardNo() {
        return cardNo;
    }

    public void setCardNo(Integer cardNo) {
        this.cardNo = cardNo;
    }

    public String getEvidence() {
        return evidence;
    }

    public void setEvidence(String evidence) {
        this.evidence = evidence;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

}
