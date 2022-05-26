package com.dhlk.appstore.dao;

import com.dhlk.entity.app.LocalAppInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 本地应用Dao
 */
@Repository
public interface LocalAppInfoDao {


    LocalAppInfo selectById(int id);


    LocalAppInfo selectByName(LocalAppInfo localAppInfo);

    /**
     * 新增
     * @param localAppInfo
     * @return
     */
    Integer insert(LocalAppInfo localAppInfo);

    /**
     * 修改
     * @param localAppInfo
     * @return
     */
    Integer update(LocalAppInfo localAppInfo);

    /**
     * 列表查询
     * @param tenantId
     * @return
     */
    List<LocalAppInfo> findList(Integer tenantId);

    /**
     * 删除
     * @param id
     * @return
     */
    Integer delete(int id);

   }
