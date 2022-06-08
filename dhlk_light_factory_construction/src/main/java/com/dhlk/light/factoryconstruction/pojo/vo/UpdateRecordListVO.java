package com.dhlk.light.factoryconstruction.pojo.vo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 */
@Data
public class UpdateRecordListVO {

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 设备sn号
     */
    @ApiModelProperty(value = "设备sn号")
    @TableField("sn")
    private String sn;

    /**
     * 命令类型
     */
    @ApiModelProperty(value = "命令类型")
    private String commandType;

    /**
     * 修改前
     */
    @ApiModelProperty(value = "修改前")
    private String before;

    /**
     * 修改后
     */
    @ApiModelProperty(value = "修改后")
    private String after;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    /**
     * 存在变化的数据列表
     */
    @ApiModelProperty(value = "存在变化的数据列表")
    private List<String> differentFieldList;

}
