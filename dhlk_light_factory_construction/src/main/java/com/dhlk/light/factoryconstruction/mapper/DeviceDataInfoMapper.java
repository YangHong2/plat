package com.dhlk.light.factoryconstruction.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dhlk.light.factoryconstruction.pojo.entity.DeviceDataInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 设备信息mapper
 * @author yangfan
 * @since 2021-08-16
 */
public interface DeviceDataInfoMapper  extends BaseMapper<DeviceDataInfo> {

    /**
     * 插入或更新一条记录
     *
     * @param entity 实体对象
     */
    int insertOrUpdate(DeviceDataInfo entity);

    /**
     * 插入或更新多条设备ap
     *
     * @param deviceList 集合
     */
    int batchSaveAP(@Param("list") List<DeviceDataInfo> deviceList);

    /**
     * 重置三张表的数据
     */
    void resetTables();
}
