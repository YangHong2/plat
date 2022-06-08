package com.dhlk.light.factoryconstruction.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dhlk.light.factoryconstruction.pojo.bo.DeviceHisIpBO;
import com.dhlk.light.factoryconstruction.pojo.entity.DeviceHisIp;

import java.util.List;

/**
 * 设备历史ip mapper接口
 * @author yangfan
 * @since 2021-08-18
 */
public interface DeviceHisIpMapper extends BaseMapper<DeviceHisIp> {


    /**
     * 查询设备最新的ip
     * @return 设备最新的ip列表
     */
    List<DeviceHisIpBO> selectDeviceLastIp();


    /**
     * 查询设备历史ip列表
     * @param sn 设备号
     * @return 设备历史ip列表
     */
    List<String> hisIpListBySn(String sn);
}
