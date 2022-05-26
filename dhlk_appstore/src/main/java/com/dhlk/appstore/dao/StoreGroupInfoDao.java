package com.dhlk.appstore.dao;

import com.dhlk.entity.app.StoreGroupInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 商城分组Dao
 */
@Repository
public interface StoreGroupInfoDao {


    StoreGroupInfo selectByName(String name);

    /**
     * 新增
     * @param storeGroupInfo
     * @return
     */
    Integer insert(StoreGroupInfo storeGroupInfo);

    /**
     * 修改
     * @param storeGroupInfo
     * @return
     */
    Integer update(StoreGroupInfo storeGroupInfo);

    /**
     * 列表查询
     * @return
     */
    List<StoreGroupInfo> findList();

    /**
     * 删除
     * @param id
     * @return
     */
    Integer delete(int id);
}
