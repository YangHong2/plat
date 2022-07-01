package com.dhlk.subcontract.controller;


import com.dhlk.domain.Result;
import com.dhlk.subcontract.service.PermissionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/permissions")
public class PermissionsController {
    @Autowired
    private PermissionsService permissionsService;

    // 增加用户角色

    @GetMapping(value = "/save")
    public Result save(@RequestParam(value = "userId")Integer userId,@RequestParam(value = "roleId")Integer roleId) {
        return permissionsService.insert(userId,roleId);
    }

    // 查询所有角色用户绑定角色
    @GetMapping(value = "/findList")
    public Result findList(){
        return permissionsService.findList();
    }

    //删除用户角色
    @GetMapping(value = "/deleteByUserIdAndRoleId")
    public Result delete(@RequestParam(value = "userId")Integer userId,@RequestParam(value = "roleId")Integer roleId){
        return permissionsService.delete(userId,roleId);
    }
}
