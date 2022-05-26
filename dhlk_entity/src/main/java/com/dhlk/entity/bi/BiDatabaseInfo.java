package com.dhlk.entity.bi;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @des: 数据库连接返回字段信息实体
 * @author: xkliu
 * @date: 2021/02/25
 */
@Data
@ApiModel(value = "biDatabaseInfo", description = "数据库连接返回字段信息实体")
public class BiDatabaseInfo {

    /**
     * 字段
     */
    @ApiModelProperty(value = "字段")
    private String field;


    /**
     * 类型
     */
    @ApiModelProperty(value = "类型")
    private String type;

    /**
     * 注释
     */
    @ApiModelProperty(value = "注释")
    private String comment;

}
