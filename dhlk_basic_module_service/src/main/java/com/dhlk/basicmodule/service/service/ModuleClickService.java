package com.dhlk.basicmodule.service.service;

import com.dhlk.domain.Result;

/**
 * @author xkliu
 * @date 2020/8/24
 */
public interface ModuleClickService {

    /**
     * 记录用户点击模块次数
     * @param moduleName
     * @return
     */
    Result recordNum(String moduleName);

    /**
     * 列表查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    Result findList(Integer pageNum, Integer pageSize);
}
