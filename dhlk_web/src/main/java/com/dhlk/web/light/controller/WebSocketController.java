package com.dhlk.web.light.controller;

import com.dhlk.domain.Result;
import com.dhlk.web.light.service.WebSocketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/websocked")
@Api(tags ="websocked工具", value = "websocked")
public class WebSocketController {
    @Autowired
    private WebSocketService webSocketService;
    @GetMapping("/getIpPort")
    @ApiOperation("获取ID")
    public Result getIpPort(){
        return webSocketService.getIpPort();
    }
}
