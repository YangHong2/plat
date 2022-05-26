package com.dhlk.appstore.controller;

import com.dhlk.appstore.service.AppGroupService;
import com.dhlk.domain.Result;
import com.dhlk.entity.app.StoreGroupInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/app/group")
public class AppGroupController {

    @Autowired
    private AppGroupService appGroupService;

    @PostMapping(value = "/save")
    public Result save(@RequestBody StoreGroupInfo storeGroupInfo) {
        return appGroupService.save(storeGroupInfo);
    }

    @PostMapping(value = "/update")
    public Result update(@RequestBody StoreGroupInfo storeGroupInfo) {
        return appGroupService.update(storeGroupInfo);
    }

    @PostMapping(value = "/delete")
    public Result delete(@RequestParam("id") int id) {
        return  appGroupService.delete(id);
    }

    @GetMapping("/findList")
    public Result findList(){
        return appGroupService.findList();
    }
}
