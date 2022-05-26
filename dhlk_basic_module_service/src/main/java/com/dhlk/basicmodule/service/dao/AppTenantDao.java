package com.dhlk.basicmodule.service.dao;

import com.dhlk.entity.app.AppTenant;
import com.dhlk.entity.app.StoreAppInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


@Repository
public interface AppTenantDao {
    /**
     * 添加
     */
    Integer insert(List<AppTenant> appTenants);

    /**
     * 删除
     */
    Integer delete(@Param("set") Set<Integer> set);

    /**
     * 查询
     */
    List<StoreAppInfo> findListChecked(@Param("tenantId")Integer tenantId);

    /**
     * 查询给租户分配的所有app应用
     */
    List<StoreAppInfo> findList(@Param("tenantId")Integer tenantId);
}
