package com.dhlk.web.app.controller;

import com.dhlk.domain.Result;
import com.dhlk.entity.app.StoreAppInfo;
import com.dhlk.web.app.service.AppStoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/app/store")
@Api(description = "商城应用管理", value = "AppStoreController")
public class AppstoreController {

    @Autowired
    private AppStoreService appstoreService;

    @PostMapping(value = "/save")
    @ApiOperation("添加本地应用")
    public Result save(@RequestBody StoreAppInfo storeAppInfo) {
        return appstoreService.save(storeAppInfo);
    }

    @PostMapping(value = "/update")
    @ApiOperation("修改本地应用")
    public Result update(@RequestBody StoreAppInfo storeAppInfo) {
        return appstoreService.update(storeAppInfo);
    }

    @PostMapping(value = "/delete")
    @ApiOperation("删除本地应用")
    public Result delete(@RequestParam("id") int id) {
        return  appstoreService.delete(id);
    }

    @GetMapping("/findList")
    @ApiOperation("查询本地应用")
    public Result findList(@RequestParam("groupId") int groupId){
        return appstoreService.findList(groupId);
    }
}
