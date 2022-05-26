package com.dhlk.entity.sub;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单(SubpackageOrder)实体类
 *
 * @author xkliu
 * @since 2021-03-12 09:24:17
 */
public class SubpackageOrder implements Serializable {
    private static final long serialVersionUID = 663636926287074084L;
    /**
     * 主键
     */
    private Integer id;
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 状态
     */
    private Integer status;
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

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}
