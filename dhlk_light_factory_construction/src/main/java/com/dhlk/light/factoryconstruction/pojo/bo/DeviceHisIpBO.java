package com.dhlk.light.factoryconstruction.pojo.bo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;


@Data
public class DeviceHisIpBO {

    /**
     * 设备sn号
     */
    @TableField("sn")
    private String sn;

    /**
     * 历史ip
     */
    private String hisIp;
}
