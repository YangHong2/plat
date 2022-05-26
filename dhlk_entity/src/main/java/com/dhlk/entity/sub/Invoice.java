package com.dhlk.entity.sub;

import java.io.Serializable;
import java.util.Date;

/**
 * 发票管理(Invoice)实体类
 *
 * @author xkliu
 * @since 2021-03-12 09:21:10
 */
public class Invoice implements Serializable {
    private static final long serialVersionUID = 868472294367788493L;
    /**
     * 主键
     */
    private Integer id;
    /**
     * 订单
     */
    private Integer orderId;
    /**
     * 提交时间
     */
    private Date submitTime;
    /**
     * 开票张数
     */
    private Integer amount;
    /**
     * 总金额
     */
    private Double totalMoney;
    /**
     * 发票信息
     */
    private Integer invoiceInfoId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Integer getInvoiceInfoId() {
        return invoiceInfoId;
    }

    public void setInvoiceInfoId(Integer invoiceInfoId) {
        this.invoiceInfoId = invoiceInfoId;
    }

}
