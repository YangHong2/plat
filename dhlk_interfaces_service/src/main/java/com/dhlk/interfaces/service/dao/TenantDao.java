package com.dhlk.interfaces.service.dao;

import com.dhlk.entity.basicmodule.Tenant;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 租户DAO
 */
@Repository
public interface TenantDao {

    Tenant selectTenantById(@Param("id") Integer id);



    Tenant  selectTenantBySign(@Param("sign") String sign);
}