package com.dhlk.web.proxy.controller;

import com.dhlk.domain.Result;
import com.dhlk.web.proxy.service.ServerManageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/report")
@Api(tags ="服务器服务管理", value = "ServerManageController")
public class ServerManageController {
    @Autowired
    private ServerManageService serverManageService;

    @ApiOperation("初始返回服务器默认信息接口")
    @PostMapping("/service/initial")
    public Result initialServiceInfo(){
        return serverManageService.initialServiceInfo();
    }
    @ApiOperation("本地推送服务关联信息到云端")
    @PostMapping("/service/info")
    public Result saveServiceInfo(@RequestBody Result result){
        return serverManageService.saveServiceInfo(result);
    }
}
