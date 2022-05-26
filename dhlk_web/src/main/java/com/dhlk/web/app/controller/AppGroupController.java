package com.dhlk.web.app.controller;


import com.dhlk.domain.Result;
import com.dhlk.entity.app.StoreGroupInfo;
import com.dhlk.web.app.service.AppGroupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/app/group")
@Api(description = "应用市场分组管理", value = "AppGroupController")
public class AppGroupController {

    @Autowired
    private AppGroupService appGroupService;


    @ApiOperation("添加分组")
    @PostMapping("/save")
    public Result save(@RequestBody StoreGroupInfo storeGroupInfo) {
        return appGroupService.save(storeGroupInfo);
    }


    @ApiOperation("修改分组")
    @PostMapping("/update")
    public Result update(@RequestBody StoreGroupInfo storeGroupInfo) {
        return appGroupService.update(storeGroupInfo);
    }


    @ApiOperation("删除分组")
    @PostMapping("/delete")
    public Result delete(@RequestParam("id") int id) {
        return appGroupService.delete(id);
    }

    @ApiOperation("查询所有分组")
    @GetMapping("/findList")
    public Result findList() {
        return appGroupService.findList();
    }
}
