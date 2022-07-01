package com.dhlk.subcontract.web.controller;


import com.dhlk.domain.Result;
import com.dhlk.subcontract.web.service.PermissionsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/permissions")
@Api(description = "用户与角色" ,value = "PermissionsController")
public class PermissionsController {
    @Resource
    private PermissionsService permissionsService;

    // 增加用户角色

    @GetMapping(value = "/save")
    @ApiOperation("通过用户Id,角色Id添加关联")
    public Result save(@RequestParam(value = "userId",required = false)Integer userId,@RequestParam(value = "roleId",required = false)Integer roleId) {
        return permissionsService.insert(userId,roleId);
    }

    // 查询所有角色用户绑定角色
    @GetMapping(value = "/findList")
    @ApiOperation("查询所有角色用户绑定角色")
    public Result findList(){
        return permissionsService.findList();
    }

    //删除用户角色
    @GetMapping(value = "/deleteByUserIdAndRoleId")
    @ApiOperation("删除用户角色")
    public Result delete(@RequestParam(value = "userId",required = false)Integer userId,@RequestParam(value = "roleId",required = false)Integer roleId){
        return permissionsService.delete(userId,roleId);
    }
}
