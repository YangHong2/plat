package com.dhlk.web.basicmodule.controller;

import com.dhlk.domain.Result;
import com.dhlk.entity.app.AppTenant;
import com.dhlk.web.basicmodule.service.AppMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
* app菜单
*/
@RestController
@RequestMapping(value = "/appMenu")
@Api(value = "app菜单权限")
public class AppMenuController {
    @Autowired
    private AppMenuService appMenuService;

    /**
     * 查询app菜单
     * @param
     * @return
     */
    @GetMapping(value = "/findListAppChecked")
    @ApiOperation(value = "查询app菜单")
    public Result findListAppChecked(@RequestParam("tenantId") Integer tenantId) {
        return appMenuService.findListAppChecked(tenantId);
    }
    /**
     * 租户添加权限
     * @param appTenants
     * @return result
     */
    @PostMapping(value = "/addAppTenant")
    @ApiOperation(value = "租户添加权限")
    public Result addAppTenant(@RequestBody List<AppTenant> appTenants) {
        return appMenuService.addAppTenant(appTenants);
    }

    /**
    * 租户管理员查询app菜单
    */
    @GetMapping("/findListApp")
    @ApiOperation(value = "租户管理员查询app菜单")
    public Result findListApp(@RequestParam(value = "tenantId") Integer tenantId) {
        return  appMenuService.findListApp(tenantId);
    }
    /**
     * 租户管理员点开app权限菜单
     * @return
     */
    @PostMapping("/findListRoleChecked")
    @ApiOperation(value = "租户管理员点开app权限菜单")
    public Result findListRoleChecked(@RequestParam(value = "roleId")Integer roleId,@RequestParam(value = "tenantId")Integer tenantId) {
        return  appMenuService.findListRoleChecked(roleId,tenantId);
    }

    /**
     * 租户管理员给角色分配权限
     * @return
     */
    @PostMapping("/addAppPermissions")
    @ApiOperation(value = "租户管理员给角色分配权限")
    public Result addAppPermissions(@RequestParam(value = "roleId")Integer roleId,@RequestParam(value = "menuIds")String menuIds) {
        return  appMenuService.addAppPermissions(roleId,menuIds);
    }
}
