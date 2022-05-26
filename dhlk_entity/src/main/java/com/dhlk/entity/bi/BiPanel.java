package com.dhlk.entity.bi;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @des: 报表面板
 * @author: xkliu
 * @date: 2021/02/22
 */
@Data
@ApiModel(value = "biPanel", description = "报表面板")
public class BiPanel {
    /**
     * id
     */
    @ApiModelProperty(value = "新增为空/修改传值", hidden = true)
    private Integer id;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;

    /**
     * 内容
     */
    @ApiModelProperty(value = "内容")
    private String content;

    /**
     * 添加人
     */
    @ApiModelProperty(value = "添加人", hidden = true)
    private Integer createUser;


    /**
     * 添加时间
     */
    @ApiModelProperty(value = "添加时间", hidden = true)
    private String createTime;

    /**
     * 发布 0 未发布 1 已发布
     */
    @ApiModelProperty(value = "发布")
    private Integer issue;
}
