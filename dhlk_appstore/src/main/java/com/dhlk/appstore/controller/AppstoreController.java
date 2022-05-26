package com.dhlk.appstore.controller;

import com.dhlk.appstore.service.AppstoreService;
import com.dhlk.domain.Result;
import com.dhlk.entity.app.StoreAppInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/app/store")
public class AppstoreController {

    @Autowired
    private AppstoreService appstoreService;

    @PostMapping(value = "/save")
    public Result save(@RequestBody StoreAppInfo storeAppInfo) {
        return appstoreService.save(storeAppInfo);
    }

    @PostMapping(value = "/update")
    public Result update(@RequestBody StoreAppInfo storeAppInfo) {
        return appstoreService.update(storeAppInfo);
    }

    @PostMapping(value = "/delete")
    public Result delete(@RequestParam("id") int id) {
        return  appstoreService.delete(id);
    }

    @GetMapping("/findList")
    public Result findList(@RequestParam("groupId") int groupId){
        return appstoreService.findList(groupId);
    }
}
