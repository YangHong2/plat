package com.dhlk.entity.sub;

import java.io.Serializable;

/**
 * 发票地址信息(InvoiceInfo)实体类
 *
 * @author xkliu
 * @since 2021-03-12 09:21:11
 */
public class InvoiceInfo implements Serializable {
    private static final long serialVersionUID = 666692811240118349L;
    /**
     * 主键
     */
    private Integer id;
    /**
     * 企业名称
     */
    private String companyName;
    /**
     * 企业地址
     */
    private String companyAddress;
    /**
     * 企业电话
     */
    private String companyPhone;
    /**
     * 银行账号
     */
    private String bankAccount;
    /**
     * 开户行
     */
    private String openBank;
    /**
     * 税务登记号
     */
    private String taxNo;
    /**
     * 收件人
     */
    private String consignee;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * 收件地址
     */
    private String consigneeAddress;
    /**
     * 用户
     */
    private Integer userId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getOpenBank() {
        return openBank;
    }

    public void setOpenBank(String openBank) {
        this.openBank = openBank;
    }

    public String getTaxNo() {
        return taxNo;
    }

    public void setTaxNo(String taxNo) {
        this.taxNo = taxNo;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getConsigneeAddress() {
        return consigneeAddress;
    }

    public void setConsigneeAddress(String consigneeAddress) {
        this.consigneeAddress = consigneeAddress;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}
