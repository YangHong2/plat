package com.dhlk.web.light.controller;

import com.dhlk.domain.Result;
import com.dhlk.entity.light.Led;
import com.dhlk.entity.light.SyncDataResult;
import com.dhlk.web.light.service.DataSyncService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author xkliu
 * @date 2020/6/18
 */

@RestController
@RequestMapping(value = "/dataSync")
@Api(tags ="云端数据同步接口", value = "DataSyncController")
public class DataSyncController {

    @Autowired
    private DataSyncService dataSyncService;

    /**
     * 数据同步
     * @return result
     */
    @ApiOperation("数据同步")
    @GetMapping("/sync")
    public Result sync(@RequestParam(value = "code", required = false) String code) {
        return  dataSyncService.sync(code);
    }

    @ApiOperation("云端数据同步本地(支持选择同步)")
    @GetMapping("/syncChoose")
    public Result dataSyncChoose(@RequestParam(value = "code", required = false) String code,@RequestParam(value = "type", required = false) String type) {
        return dataSyncService.syncChoose(code,type);
    }
    /**
     * 同步数据到本地
     * @return result
     */
    @ApiOperation("同步数据到本地")
    @PostMapping("/syncDataToLocal")
    public Result syncDataToLocal(@RequestBody SyncDataResult syncDataResult) {
        return  dataSyncService.syncDataToLocal(syncDataResult);
    }
    /**
     * 本地同步数据到云端
     * @return
     */
    @ApiOperation("本地同步数据到云端")
    @PostMapping(value = "/syscLocalToCloud")
    public Result syscLocalToCloud(@RequestBody Map<String, String> param) {
        return dataSyncService.syscLocalToCloud(param);
    }
}
