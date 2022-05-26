package com.dhlk.interfaces.service.service;

import com.dhlk.domain.Result;
import com.dhlk.entity.basicmodule.DevicesClassify;

/**
 * @Description 设备分类管理
 * @Author lpsong
 * @Date 2020/3/26
 */
public interface DevicesClassifyService {
    /*
    * 查询
     * @param name 设备分配名称
    * @return
    */
    Result findList(String name);
}