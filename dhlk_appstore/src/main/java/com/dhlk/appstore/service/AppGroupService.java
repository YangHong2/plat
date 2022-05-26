package com.dhlk.appstore.service;

import com.dhlk.domain.Result;
import com.dhlk.entity.app.StoreGroupInfo;


/**
 * @Description 应用分组管理
 */
public interface AppGroupService {

    /**
     * 新增/修改
     */
    Result save(StoreGroupInfo storeGroupInfo);

    /**
     * 修改
     * @param storeGroupInfo
     * @return
     */
    Result update(StoreGroupInfo storeGroupInfo);



    /**
     * 物理删除
     * @param id
     */
    Result delete(int id);


    /**
     * 列表查询
     */
    Result findList();

}
