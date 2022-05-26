package com.dhlk.entity.bi;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @des: 数据库连接
 * @author: xkliu
 * @date: 2021/02/22
 */
@Data
@ApiModel(value = "biDatabase", description = "数据库连接")
public class BiDatabase {

    /**
     * id
     */
    @ApiModelProperty(value = "新增为空/修改传值", hidden = true)
    private Integer id;

    /**
     * 连接名称
     */
    @ApiModelProperty(value = "连接名称")
    private String name;


    /**
     * 连接地址
     */
    @ApiModelProperty(value = "连接地址")
    private String url;

    /**
     * 端口
     */
    @ApiModelProperty(value = "端口")
    private Integer port;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String password;

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
     * 数据表名
     */
    @ApiModelProperty(value = "数据表名", hidden = true)
    private String tableName;

    /**
     * sql文件生成地址
     */
    @ApiModelProperty(value = "sql文件生成地址", hidden = true)
    private String sqlFileAddress;

}
