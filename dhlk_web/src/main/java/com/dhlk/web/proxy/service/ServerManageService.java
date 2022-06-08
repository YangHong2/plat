package com.dhlk.web.proxy.service;

import com.dhlk.domain.Result;
import com.dhlk.web.proxy.service.fbk.ServerManageServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 云端接收本地推送过来的服务器信息类
 */
@FeignClient(value = "light-service/report/service", fallback = ServerManageServiceFbk.class)
public interface ServerManageService {
    /**
     * 接收所有服务信息
     * @param result
     * @return
     */
    @PostMapping(value = "/info")
    Result saveServiceInfo(@RequestBody Result result);

    @PostMapping(value = "/initial")
    Result initialServiceInfo();
}
