package com.dhlk.appstore.dao;

import com.dhlk.entity.app.StoreAppInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * <p>
 * 商城应用Dao
 */
@Repository
public interface StoreAppInfoDao {


    StoreAppInfo selectByName(@Param(value = "name") String name);

    StoreAppInfo selectById(@Param(value = "id") int id);


    /**
     * 新增
     * @param storeAppInfo
     * @return
     */
    Integer insert(StoreAppInfo storeAppInfo);

    /**
     * 修改
     * @param storeAppInfo
     * @return
     */
    Integer update(StoreAppInfo storeAppInfo);

    /**
     * 修改
     * @param storeAppInfo
     * @return
     */
    Integer updateDownloadCount(StoreAppInfo storeAppInfo);

    /**
     * 列表查询
     * @return
     */
    List<StoreAppInfo> findList(@Param(value = "groupId") int groupId);

    /**
     * 删除
     * @param id
     * @return
     */
    Integer delete(@Param(value = "id") int id);

    /**
     * 查询除自己之外的appName是否存在
     * @param storeAppInfo
     * @return
     */
    Integer selectAppById(StoreAppInfo storeAppInfo);
}
