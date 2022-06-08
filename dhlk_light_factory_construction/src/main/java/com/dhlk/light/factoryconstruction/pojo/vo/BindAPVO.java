package com.dhlk.light.factoryconstruction.pojo.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 绑定AP VO
 * @author wzx
 * @since 2021-08-17
 */
@Builder
@Data
@ApiModel(value = "BindAPVO", description = "绑定AP")
public class BindAPVO {
    /**
     * 端口号
     */
    @ApiModelProperty(value = "端口号",required = true)
    @NotEmpty(message = "监听端口号不能为空")
    private String port;
    /**
     * 设备sn集合
     */
    @ApiModelProperty(value = "设备sn集合",required = true)
    @NotEmpty(message = "设备sn集合不能为空")
    private List<String> snList;
    /**
     * 绑定AP 0 未绑定 1 已绑定
     */
    @ApiModelProperty(value = "是否绑定",hidden = true)
    private Boolean ap;
}
