package com.dhlk.web.light.service;

import com.dhlk.domain.Result;
import com.dhlk.entity.light.Coordinate;
import com.dhlk.entity.light.LightLocation;
import com.dhlk.web.light.service.fbk.LightQueryServiceFbk;
import com.dhlk.web.light.service.fbk.LocationServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author xmdeng
 * @date 2021/7/26 14:41
 */
@FeignClient(value = "light-service/location", fallback = LocationServiceFbk.class)
public interface LocationService {

    /**
     * 获取定位信息
     * @return
     */
    @GetMapping("/conf")
    Result getLightLocation();

    /**
     * 更新定位配置信息
     * @param lightLocation 定位配置信息
     * @return
     */
    @PostMapping("/sync")
    Result syncLocation(@RequestBody LightLocation lightLocation);

    /**
     * 是否在范围内
     * @return
     */
    @GetMapping("/range")
    Result isInRange(@RequestBody Coordinate coordinate);
}
