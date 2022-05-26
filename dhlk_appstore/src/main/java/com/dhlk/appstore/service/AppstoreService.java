package com.dhlk.appstore.service;

import com.dhlk.domain.Result;
import com.dhlk.entity.app.StoreAppInfo;


/**
 * @Description 应用市场应用管理
 */
public interface AppstoreService {
    /**
     * 新增/修改
     */
    Result save(StoreAppInfo storeAppInfo);

    /**
     * 修改
     * @param storeAppInfo
     * @return
     */
    Result update(StoreAppInfo storeAppInfo);



    /**
     * 物理删除
     * @param id
     */
    Result delete(int id);


    /**
     * 列表查询
     */
    Result findList(int groupId);
}
