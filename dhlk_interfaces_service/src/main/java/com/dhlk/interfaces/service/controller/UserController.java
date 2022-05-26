package com.dhlk.interfaces.service.controller;

import com.dhlk.domain.Result;
import com.dhlk.entity.basicmodule.User;
import com.dhlk.interfaces.service.service.UserService;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
* 用户管理
*/
@RestController
@Api(value = "UserController", description = "用户管理")
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;


    /**
    * 列表查询
     * @param name name为模糊查询，name如果为null或者为空就是全表扫描
    * @return
    */
    @GetMapping("/findList")
    @RequiresAuthentication
    public Result findList(@RequestParam(value = "name", required = false) String name) {
        return userService.findList(name);
    }
    /**
     * 根据用户id查用户
     * @param id
     */
    @GetMapping("/findUserById")
    @RequiresAuthentication
    public Result findUserById(@RequestParam(value = "id", required = false) String id) {
        return userService.findUserById(id);
    }
}
