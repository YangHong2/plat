package com.dhlk.light.test.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="InfoBox",description="包装实体")
public class InfoBox<T> {
    @ApiModelProperty(value="sns")
    private String sns;

    @ApiModelProperty(value="对象")
    private T t;
}
