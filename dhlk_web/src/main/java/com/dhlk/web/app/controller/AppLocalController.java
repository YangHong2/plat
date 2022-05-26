package com.dhlk.web.app.controller;

import com.dhlk.domain.Result;
import com.dhlk.entity.app.LocalAppInfo;
import com.dhlk.web.app.service.AppLocalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/app/local")
@Api(description = "本地应用管理", value = "AppLocalController")
public class AppLocalController {

    @Autowired
    private AppLocalService appLocalService;

    @PostMapping(value = "/save")
    @ApiOperation("添加本地应用")
    public Result save(@RequestBody LocalAppInfo localAppInfo) {
        return appLocalService.save(localAppInfo);
    }

    @PostMapping(value = "/update")
    @ApiOperation("修改本地应用")
    public Result update(@RequestBody LocalAppInfo localAppInfo) {
        return appLocalService.update(localAppInfo);
    }

    @PostMapping(value = "/delete")
    @ApiOperation("删除本地应用")
    public Result delete(@RequestParam("id") int id) {
        return  appLocalService.delete(id);
    }

    @GetMapping("/findList")
    @ApiOperation("查询本地应用")
    public Result findList(@RequestParam("tenantId") int tenantId){
        return appLocalService.findList(tenantId);
    }
}
