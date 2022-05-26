package com.dhlk.entity.basicmodule;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 模块点击
 * </p>
 *
 * @author xkliu
 * @since 2020-08-24
 */
@Data
public class ModuleClick implements Serializable {

    /**
     * id
     */
    @ApiModelProperty(value = "id", hidden = true)
    private Integer id;

    /**
     * 点击模块名称
     */
    @ApiModelProperty(value = "模块名称")
    private String moduleName;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String loginName;

    /**
     * 点击时间
     */
    @ApiModelProperty(value = "点击时间", hidden = true)
    private String clickTime;

    /**
     * 点击次数
     */
    @ApiModelProperty(value = "点击次数", hidden = true)
    private Integer clickNum;


}
