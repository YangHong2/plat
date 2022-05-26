package com.dhlk.interfaces.service.service;

import com.dhlk.domain.Result;
import com.dhlk.entity.basicmodule.ProductDevices;

/**
 * 生产设备管理
 */
public interface ProductDevicesService {
        /**
         * 列表查询
         */
        Result findList(String name, String classifyId);

        /**
         * 按机构查询设备
         */
        Result findTreeList();

    /**
     * 查询租户正常设备数量
     */
    Result findDevicesCount(Integer tenantId);
}
