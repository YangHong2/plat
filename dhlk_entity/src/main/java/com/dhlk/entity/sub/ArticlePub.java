package com.dhlk.entity.sub;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 文章发布(ArticlePub)实体类
 *
 * @author xkliu
 * @since 2021-03-23 11:14:02
 */
@Data
@ApiModel(value = "articlePub", description = "文章发布")
public class ArticlePub implements Serializable {
    private static final long serialVersionUID = 338897443217204534L;

    /**
     * id
     */
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;
    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    private String title;
    /**
     * 内容
     */
    @ApiModelProperty(value = "内容")
    private String content;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间",hidden = true)
    private String createTime;
    /**
     * 发布人
     */
    @ApiModelProperty(value = "发布人",hidden = true)
    private Integer pubUser;

    @ApiModelProperty(value = "发布人名字",hidden = true)
    private String userName;

}
