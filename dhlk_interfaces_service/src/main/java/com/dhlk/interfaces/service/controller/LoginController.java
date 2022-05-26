package com.dhlk.interfaces.service.controller;

import com.dhlk.domain.Result;
import com.dhlk.entity.basicmodule.User;
import com.dhlk.interfaces.service.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Api(value = "LoginController", description = "登录")
public class LoginController {
    @Resource
    private LoginService loginServiceImpl;
    @PostMapping("/login")
    public Result login(@RequestBody User user){
        return loginServiceImpl.login(user.getLoginName(),user.getPassword());
    }

    @ApiOperation(value = "校验token")
    @GetMapping("/checkToken")
    @RequiresAuthentication
    public Result checkToken() {
        return loginServiceImpl.checkToken();
    }
}
