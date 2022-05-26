package com.dhlk.entity.app;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

@Data
@ApiModel(value = "local_app", description = "本地app信息")
public class LocalAppInfo {
    /**
     * Id
     */
    @ApiModelProperty(value = "id")
    int id;

    /**
     * app名称
     */
    @Length(min = 2, max = 50, message = "应用名称,最大长度为2-50位的字符")
    @ApiModelProperty(value = "应用名称")
    String appName;
    /**
     * app地址
     */
    @Length(min = 2, max = 50, message = "应用地址,最大长度为2-50位的字符")
    @ApiModelProperty(value = "应用地址")
    String appAddress;
    /**
     * app logo
     */
    @ApiModelProperty(value = "应用logo")
    String appLogo;
    /**
     * 其他信息
     */
    @ApiModelProperty(value = "其他信息")
    String other;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    String createTime;
    /**
     * 租户id
     */
    @ApiModelProperty(value = "租户信息")
    int tenantId;

    /**
     * 云appId
     */
    @ApiModelProperty(value = "云appId")
    int storeAppId;

    /**
     * 是否token传参数
     */
    @ApiModelProperty(value = "是否token传参 0传 1不传")
    Integer tokenParameters;


    /**
     * 是否投放广告
     */
    @ApiModelProperty(value = "是否投屏广告 0投 1不投")
    Integer putAdvertising;
}
