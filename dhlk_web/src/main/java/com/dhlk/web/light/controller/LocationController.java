package com.dhlk.web.light.controller;

import com.dhlk.domain.Result;
import com.dhlk.entity.light.LightLocation;
import com.dhlk.web.ai.service.LightLocationService;
import com.dhlk.web.light.service.LocationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author xmdeng
 * @date 2021/7/26 14:44
 */
@RestController
@RequestMapping("/location")
@Api("定位配置")
public class LocationController {

    @Resource
    private LocationService locationService;

    @ApiOperation("获取定位配置信息")
    @GetMapping("/conf")
    public Result<LightLocation> getLightLocation(){
        return locationService.getLightLocation();
    }

    @ApiOperation("更新配置信息")
    @PostMapping("/sync")
    public Result syncLocation(@RequestBody @Validated LightLocation lightLocation){
        return locationService.syncLocation(lightLocation);
    }
}
