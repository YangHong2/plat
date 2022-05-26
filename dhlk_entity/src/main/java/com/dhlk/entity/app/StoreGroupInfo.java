package com.dhlk.entity.app;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

@Data
@ApiModel(value = "store_app_group", description = "商城app分组信息")
public class StoreGroupInfo {
    /**
     * Id
     */
    @ApiModelProperty(value = "id")
    int id;

    /**
     * app名称
     */
    @Length(min = 2, max = 50, message = "分组名称,最大长度为2-50位的字符")
    @ApiModelProperty(value = "分组名称")
    String groupName;
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

}
