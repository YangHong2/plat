package com.dhlk.appstore.controller;

import com.dhlk.appstore.service.AppLocalService;
import com.dhlk.domain.Result;
import com.dhlk.entity.app.LocalAppInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(value = "/app/local")
public class AppLocalController {

    @Autowired
    private AppLocalService appLocalService;

    @PostMapping(value = "/save")
    public Result save(@RequestBody LocalAppInfo localAppInfo) {
        return appLocalService.save(localAppInfo);
    }

    @PostMapping(value = "/update")
    public Result update(@RequestBody LocalAppInfo localAppInfo) {
        return appLocalService.update(localAppInfo);
    }

    @PostMapping(value = "/delete")
    public Result delete(@RequestParam("id") int id) {
        return  appLocalService.delete(id);
    }

    @GetMapping("/findList")
    public Result findList(@RequestParam("tenantId") int tenantId){
        return appLocalService.findList(tenantId);
    }
}
