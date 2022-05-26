package com.dhlk.basicmodule.service.service;

import com.dhlk.domain.Result;
import com.dhlk.entity.app.AppTenant;
import com.dhlk.entity.basicmodule.User;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
* @Description:    app应用权限菜单
* @Author:         gchen
* @CreateDate:     2020/10/22 9:36
*/
public interface AppMenuService {

    /**
     * 添加
     */
    Result addAppTenant(List<AppTenant> appTenants);

    /**
     * 查询
     */
    Result findListAppChecked(Integer tenantId);

    /**
     * 查询给租户分配的所有app应用
     */
    Result findListApp(Integer tenantId);

    /**
     * 查询app的权限
     */
    Result findListByCode(Integer tenantId);

    /**
     * 查看角色权限菜单
     */
    Result findListRoleChecked(Integer roleId,Integer tenantId);

    /**
     * 批量插入
     * @param roleId,menuIds
     */
    Result addAppPermissions(Integer roleId,String menuIds);

    /**
     * 根据用户查询app应用权限
     */
    Map<String, Set> getPermissionsByLoginName(User user);
}
