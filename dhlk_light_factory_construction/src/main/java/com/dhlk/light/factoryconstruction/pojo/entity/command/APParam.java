package com.dhlk.light.factoryconstruction.pojo.entity.command;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Value;

/**
 * AP复位
 * @date 2021/8/16
 * @author lzhang
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("AP复位")
public class APParam extends CommandParam{
    // AP 参数
    @ApiModelProperty(value = "AP参数")
    private String paramTime;
}
