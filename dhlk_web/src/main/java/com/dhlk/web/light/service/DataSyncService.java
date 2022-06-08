package com.dhlk.web.light.service;

import com.dhlk.domain.Result;
import com.dhlk.entity.light.Led;
import com.dhlk.entity.light.SyncDataResult;
import com.dhlk.web.light.service.fbk.DataSyncServiceFbk;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * @author xkliu
 * @date 2020/6/18
 */
@FeignClient(value = "light-service/dataSync", fallback = DataSyncServiceFbk.class)
public interface DataSyncService {

    /**
     * 数据同步
     * @param code
     * @return
     */
    @GetMapping("/sync")
    Result sync(@RequestParam(value = "code", required = false) String code);

    /**
     * 云端同步数据到本地(支持选择同步)
     * @param code
     * @return
     */
    @GetMapping("/syncChoose")
    Result syncChoose(@RequestParam(value = "code", required = false) String code,@RequestParam(value = "type", required = false) String type);
    /**
     * 同步数据到本地
     * @return result
     */
    @PostMapping("/syncDataToLocal")
    Result syncDataToLocal(@RequestBody SyncDataResult syncDataResult);


    /**
     * 本地同步数据到云端
     * @return
     */
    @PostMapping(value = "/syscLocalToCloud")
    Result syscLocalToCloud(@RequestBody Map<String, String> param);
}
