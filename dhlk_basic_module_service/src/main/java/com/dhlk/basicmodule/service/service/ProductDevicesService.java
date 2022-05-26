package com.dhlk.basicmodule.service.service;

import com.dhlk.entity.basicmodule.ProductDevices;
import com.dhlk.domain.Result;

/**
 * 生产设备管理
 */
public interface ProductDevicesService {
        /**
         * 新增/修改
         */
        Result save(ProductDevices productDevices) throws Exception;

        /**
         * 逻辑删除，更改status为2
         * @param ids
         */
        Result delete(String ids) throws Exception;
        /**
         * 列表查询
         */
        Result findList(String name,String classifyId);


        /**
         * 按机构查询设备
         */
        Result findTreeList();
        /**
         * 根据dhlk_id查询tb设备信息
         * @param id
         */
        Result findTbDeviceByDhlkId(Integer id) throws Exception;
        /**
         * 根据id修改设备信息 逻辑删除，更改status为2 tb_id为2
         * @param id
         */
        Result deleteById(Integer id);

        Result findAttrByClassifyById(String classifyId);

        /**
         * 根据租户Id查询设备列表
         * @param tenantId
         * @param token
         * @return
         */
        Result findListByTenantId(Integer tenantId, String token);

        /**
         * 根据Id获取设备详情
         * @param id
         * @return
         */
        Result getDevice(Integer id);
}
