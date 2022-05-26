package com.dhlk.entity.sub;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 解决方案(Solution)实体类
 *
 * @author makejava
 * @since 2021-03-12 09:54:16
 */
@Data
public class Solution implements Serializable {

    private static final long serialVersionUID = 111623297974643989L;

    @ApiModelProperty(hidden = true)
    private Integer id;
    /**
     * 方案主题
     */
    @ApiModelProperty(value = "方案主题")
    private String projectTheme;
    /**
     * 方案详情
     */
    @ApiModelProperty(value = "方案详情")
    private String projectDetail;
    /**
     * 项目id
     */
    @ApiModelProperty(value = "企业id")
    private Integer companyId;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private String createTime;


    @ApiModelProperty(value = "企业名称")
    private String companyName;

}
