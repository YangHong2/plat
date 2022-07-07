package com.dhlk.subcontract.dao.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ReceivingInfoVo implements Serializable {
    private static final long serialVersionUID = -93014449679380228L;

    private Integer id;
    /**
     * 收款方
     */
    private String payee;
    /**
     * 收款方账号
     */
    private String payeeNum;
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
    private String money;
    /**
     * 付款方
     */
    private String payer;
    /**
     * 时间
     */
    private String time;
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
    private String cardNo;
    /**
     * 凭据
     */
    private String evidence;
    /**
     * 项目id
     */
    private Integer projectId;
    /**
     * 付款类型   首款  尾款  全款  回款
     */
    private String  type;


}