package com.dhlk.web.basicmodule.service;

import com.dhlk.domain.Result;
import com.dhlk.entity.app.AppTenant;
import com.dhlk.web.basicmodule.service.fbk.AppMenuServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@FeignClient(value = "basicmodule-service/appMenu", fallback = AppMenuServiceFbk.class)
public interface AppMenuService {
    /**
     * 查询app菜单
     */
    @GetMapping(value = "/findListAppChecked")
    Result findListAppChecked(@RequestParam("tenantId") Integer tenantId);
    /**
     * 租户添加权限
     * @param appTenants
     * @return result
     */
    @PostMapping(value = "/addAppTenant")
    Result addAppTenant(@RequestBody List<AppTenant> appTenants);

    /**
     * 租户管理员查询app菜单
     */
    @GetMapping("/findListApp")
    Result findListApp(@RequestParam(value = "tenantId") Integer tenantId);
    /**
     * 租户管理员点开app权限菜单
     */
    @PostMapping("/findListRoleChecked")
    Result findListRoleChecked(@RequestParam(value = "roleId")Integer roleId,@RequestParam(value = "tenantId")Integer tenantId);

    /**
     * 租户管理员给角色分配权限
     */
    @PostMapping("/addAppPermissions")
    Result addAppPermissions(@RequestParam(value = "roleId")Integer roleId,@RequestParam(value = "menuIds")String menuIds);
}
