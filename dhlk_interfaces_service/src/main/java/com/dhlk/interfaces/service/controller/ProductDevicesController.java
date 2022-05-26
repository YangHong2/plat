package com.dhlk.interfaces.service.controller;

import com.alibaba.fastjson.JSONObject;
import com.dhlk.domain.Result;
import com.dhlk.entity.basicmodule.NetDevices;
import com.dhlk.entity.basicmodule.ProductDevices;
import com.dhlk.interfaces.service.service.ProductDevicesService;
import com.dhlk.utils.ResultUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
* 生产设备管理
*/
@RestController
@RequestMapping(value = "/productDevices")
public class ProductDevicesController {
    @Autowired
    private ProductDevicesService productDevicesService;


    /**
    * 列表查询
    */
    @GetMapping("/findList")
    @RequiresAuthentication
    public Result findList(@RequestParam(value = "name", required = false) String name,
                           @RequestParam(value = "classifyId", required = false) String classifyId) {
        return  productDevicesService.findList(name,classifyId);
    }

    /**
     * 按机构查询设备
     */
    @GetMapping("/findTreeList")
    @RequiresAuthentication
    public Result findTreeList() {
        return  productDevicesService.findTreeList();
    }

    /**
     * 查询租户正常设备数量
     */
    @GetMapping("/findDevicesCount")
    public Result findDevicesCount(@RequestParam(value = "tenantId", required = false) Integer tenantId) {
        return  productDevicesService.findDevicesCount(tenantId);
    }
}
