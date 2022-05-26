package com.dhlk.entity.basicmodule;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
* @Description:    系统消息实体类
* @Author:         gchen
* @CreateDate:     2020/8/20 11:03
*/
@Data
public class SysMessage {
    @ApiModelProperty(value = "新增为空/修改传值")
    private Integer id;
    @ApiModelProperty(value = "标题")
    private String title;
    @ApiModelProperty(value = "描述")
    private String describes;
    @ApiModelProperty(value = "内容")
    private String content;
    @ApiModelProperty(value = "创建时间")
    private String createTime;
    @ApiModelProperty(value = "租户id")
    private Integer tenantId;
    @ApiModelProperty(value = "记录已读的租户id  用逗号隔开")
    private String isRead;
}
