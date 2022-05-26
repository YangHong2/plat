package com.dhlk.interfaces.service.controller;

import com.dhlk.domain.Result;
import com.dhlk.entity.basicmodule.DevicesClassify;
import com.dhlk.interfaces.service.service.DevicesClassifyService;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
* @Description:    设备分类管理
* @Author:         gchen
* @CreateDate:     2020/3/31 11:10
*/
@RestController
@Api(value = "DevicesClassifyController", description = "设备分类管理")
@RequestMapping(value = "/devicesClassify")
public class DevicesClassifyController {

    @Autowired
    private DevicesClassifyService devicesClassifyService;

    /**
     * 查询
     * @param name 设备分配名称
     */
    @GetMapping("/findList")
    @RequiresAuthentication
    public Result findList(@RequestParam(value = "name", required = false) String name) {
        return  devicesClassifyService.findList(name);
    }
}
