package com.dhlk.entity.sub;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 优惠卷(Coupons)实体类
 *
 * @author xkliu
 * @since 2021-03-12 09:21:01
 */
@Data
public class Coupons implements Serializable {
    private static final long serialVersionUID = -57358073064076880L;
    /**
     * 主键
     */
    private Integer id;
    /**
     * 开始时间
     */
    private String startTime;
    /**
     * 结束时间
     */
    private String endTime;
    /**
     * 面额/折扣
     */
    private Double faceValue;
    /**
     * 图片
     */
    private String dataId;
    /**
     * APP
     */
    private String appIds;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 数量
     */
    private Integer count;
    /**
     * 类型 (0 面额 1 折扣)
     */
    private Integer type;
    /**
     * 优惠卷编号
     */
    private String couponNo;
    /**
     * 生成人
     */
    private Integer userId;
    /**
     * 伪删除
     */
    private Integer isDelete;
    /**
     * 优惠卷名称
     */
    private String couponName;

}
