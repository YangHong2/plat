package com.dhlk.basicmodule.service.dao;

import com.dhlk.entity.app.AppPermissions;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AppPermissionsDao {

    /**
     * 批量插入
     * @param appPermissions
     */
    Integer insert(List<AppPermissions> appPermissions);
    /**
     * 根据角色删除角色和权限关系
     * @param roleId
     * @return
     */
    Integer deleteByRoleId(@Param("roleId") Integer roleId);

}
