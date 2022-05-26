package com.dhlk.web.app.service;

import com.dhlk.domain.Result;
import com.dhlk.entity.app.LocalAppInfo;
import com.dhlk.web.app.service.fbk.AppLocalServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author ztang
 * @date 2020/8/4
 * <p>
 * 本地应用信息service
 */
@FeignClient(value = "app-service/app/local", fallback = AppLocalServiceFbk.class)
public interface AppLocalService {

    @PostMapping(value = "/save")
    Result save(@RequestBody LocalAppInfo localAppInfo);

    @PostMapping(value = "/update")
    Result update(@RequestBody LocalAppInfo localAppInfo);

    @GetMapping("/findList")
    Result findList(@RequestParam("tenantId") int tenantId);

    @PostMapping(value = "/delete")
    Result delete(@RequestParam("id") int id);

}
