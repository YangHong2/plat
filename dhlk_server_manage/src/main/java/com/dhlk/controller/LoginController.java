package com.dhlk.controller;

import com.dhlk.domain.Result;
import com.dhlk.feign.LocalFeign;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "LoginController",tags = "登录登录")
@CrossOrigin
public class LoginController {
    @Autowired
    private LocalFeign localFeign;
    @ApiOperation(value = "app登录")
    @PostMapping("/login")
    public Result login(@RequestParam("loginName") String loginName,
                        @RequestParam("password") String password,
                        @RequestParam("tenantCode") String tenantCode) {
        return localFeign.login(loginName,password,tenantCode);
    }

    @ApiOperation(value = "登出")
    @GetMapping("/logout")
    @CrossOrigin
    public Result logout() {
        return localFeign.logout();
    }

    @ApiOperation(value = "获取客户编码")
    @GetMapping("/getTenantCode")
    @CrossOrigin
    public Result getTenantCode() {
        return localFeign.getTenantCode();
    }
}
