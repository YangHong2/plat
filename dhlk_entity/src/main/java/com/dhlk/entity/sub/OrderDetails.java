package com.dhlk.entity.sub;

import java.io.Serializable;

/**
 * 订单详情(OrderDetails)实体类
 *
 * @author xkliu
 * @since 2021-03-12 09:21:13
 */
public class OrderDetails implements Serializable {
    private static final long serialVersionUID = -11053941737259593L;
    /**
     * 主键
     */
    private Integer id;
    /**
     * APP
     */
    private Integer appId;
    /**
     * 订单
     */
    private Integer orderId;
    /**
     * 优惠券
     */
    private Integer userCouponsId;
    /**
     * 操作 (0 购买)
     */
    private Integer handle;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getUserCouponsId() {
        return userCouponsId;
    }

    public void setUserCouponsId(Integer userCouponsId) {
        this.userCouponsId = userCouponsId;
    }

    public Integer getHandle() {
        return handle;
    }

    public void setHandle(Integer handle) {
        this.handle = handle;
    }

}
