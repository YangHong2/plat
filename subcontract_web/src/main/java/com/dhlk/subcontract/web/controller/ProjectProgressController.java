package com.dhlk.subcontract.web.controller;


import com.dhlk.domain.Result;
import com.dhlk.entity.basicmodule.ProjectCheck;
import com.dhlk.entity.basicmodule.ProjectDeliveryVo;
import com.dhlk.entity.basicmodule.ProjectProgressVo;
import com.dhlk.subcontract.web.service.ProjectProgressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/progress")
@Api(description = "项目进度更新" ,value = "/ProjectProgressController")
public class ProjectProgressController {

    @Resource
    private ProjectProgressService projectProgressService;



    /*
     * 根据项目id查询项目进度
     * */
    @GetMapping(value = "/findList")
    @ApiOperation("查询该项目进度")
    public Result findList(@RequestParam(value = "projectId") Integer projectId){
        return projectProgressService.findList(projectId);
    }

    @PostMapping(value = "/addOne")
    @ApiOperation("添加该项目进度")
    public Result addOne(@RequestBody ProjectProgressVo projectProgressVo){
        return projectProgressService.addOne(projectProgressVo);
    }

    @PostMapping(value = "/addDelivery")
    @ApiOperation("提交项目")
    public Result addDelivery(@RequestBody ProjectDeliveryVo projectDeliveryVo){
        return projectProgressService.addDelivery(projectDeliveryVo);
    }

    @GetMapping(value = "/findAllById")
    @ApiOperation("查询已提交项目")
    public Result findAllById(@RequestParam(value = "projectId") Integer projectId){
        return projectProgressService.findAllById(projectId);
    }

    @PostMapping(value = "/check")
    @ApiOperation("审核")
    public Result check(@RequestBody ProjectCheck projectCheck){
        return projectProgressService.check(projectCheck);
    }

}
