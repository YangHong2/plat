package com.dhlk.web.light.controller;

import com.dhlk.domain.Result;
import com.dhlk.entity.dto.ScheduledCronDTO;
import com.dhlk.entity.dto.SyncScheduledCronDTO;
import com.dhlk.utils.ResultUtils;
import com.dhlk.web.light.service.ScheduledCronService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author xmdeng
 * @date 2021/8/3 13:28
 */
@RestController
@RequestMapping("/schedule")
@Api(tags = "定时任务控制")
public class ScheduledCronController {

    @Resource
    private ScheduledCronService scheduledCronService;

    @GetMapping("/list/page")
    @ApiOperation("分页查询")
    public Result getPage(ScheduledCronDTO dto){
        return scheduledCronService.getPage(dto);
    }

    @PostMapping("/sync")
    @ApiOperation("更新")
    public Result syncData(@RequestBody SyncScheduledCronDTO dto){
        return scheduledCronService.syncData(dto);
    }

    @GetMapping("/{id}")
    @ApiOperation("删除")
    public Result delete(@PathVariable("id") Integer id){
        return  scheduledCronService.delete(id);
    }

    @GetMapping("/one")
    @ApiOperation("查询配置")
    public Result getOne(){
        return scheduledCronService.getOne();
    }

    @PostMapping("/list")
    @ApiOperation("根据租户ID查询定时任务集合")
    public Result getList(ScheduledCronDTO dto){
        return scheduledCronService.getList(dto);
    }
}
