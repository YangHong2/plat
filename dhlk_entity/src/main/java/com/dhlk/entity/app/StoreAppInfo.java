package com.dhlk.entity.app;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

@Data
@ApiModel(value = "store_app", description = "商城app信息")
public class StoreAppInfo {

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
     * 分组id
     */
    @ApiModelProperty(value = "分组信息")
    int groupId;
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
     * 系统支持
     */
    @ApiModelProperty(value = "系统支持")
    String systemSupport;

    /**
     * 软件特色
     */
    @ApiModelProperty(value = "软件特色")
    String softFeatures;


    /**
     * 软件功能
     */
    @ApiModelProperty(value = "软件功能")
    String softFunction;


    /**
     * 系统截图
     */
    @ApiModelProperty(value = "系统截图")
    String systemScreenshot;


    /**
     * 下载次数
     */
    @ApiModelProperty(value = "下载次数",hidden = true)
    Integer downloadCount;


    /**
     * 应用类型
     */
    @ApiModelProperty(value = "应用类型")
    String appType;


    /**
     * 应用行业
     */
    @ApiModelProperty(value = "应用行业")
    String appIndustry;

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

    /**
     * 年费
     */
    @ApiModelProperty(value = "年费")
    Double yearFee;

    @ApiModelProperty(value = "app编码")
    String appCode;
    private Boolean checked;
}
