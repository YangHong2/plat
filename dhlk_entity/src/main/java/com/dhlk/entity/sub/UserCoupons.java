package com.dhlk.entity.sub;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户优惠券(UserCoupons)实体类
 *
 * @author xkliu
 * @since 2021-03-12 09:29:02
 */
public class UserCoupons implements Serializable {
    private static final long serialVersionUID = -88692318826749979L;
    /**
     * 主键
     */
    private Integer id;
    /**
     * 用户
     */
    private Integer userId;
    /**
     * 优惠券
     */
    private Integer couponsId;
    /**
     * 状态(0 未使用 1 已使用 2 已过期)
     */
    private Integer status;
    /**
     * 发放时间
     */
    private Date createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCouponsId() {
        return couponsId;
    }

    public void setCouponsId(Integer couponsId) {
        this.couponsId = couponsId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
