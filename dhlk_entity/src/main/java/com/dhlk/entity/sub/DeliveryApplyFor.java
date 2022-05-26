package com.dhlk.entity.sub;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 交付申请(DeliveryApplyFor)实体类
 *
 * @author xkliu
 * @since 2021-03-12 09:21:02
 */
@Data
@ApiModel(value = "deliveryApplyFor", description = "交付申请")
public class DeliveryApplyFor implements Serializable {
    private static final long serialVersionUID = -22208256875704384L;

    /**
     * id
     */
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;
    /**
     * 交付项目
     */
    @ApiModelProperty(value = "交付项目")
    private String deliveryProject;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
     * 交付产物
     */
    @ApiModelProperty(value = "交付产物")
    private String deliveryProduct;
    /**
     * 时间
     */
    @ApiModelProperty(value = "时间",hidden = true)
    private String createTime;
    /**
     * 确认状态 0拒绝 1确认
     */
    @ApiModelProperty(value = "确认状态")
    private Integer status;

    /**
     * 项目id
     */
    @ApiModelProperty(value = "项目id")
    private Integer projectId;

    /**
     * 企业id
     */
    @ApiModelProperty(value = "企业id")
    private Integer companyId;

    /**
     * 类型 0开发生产 1安装施工
     */
    @ApiModelProperty(value = "类型")
    private Integer type;


}
