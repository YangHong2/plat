package com.dhlk.entity.sub;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 收付款信息(ReceivingInfo)实体类
 *
 * @author xkliu
 * @since 2021-03-12 09:23:21
 */
@Data
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
     * 付款类型   首款  尾款  全款
     */
    private String  type;



}
