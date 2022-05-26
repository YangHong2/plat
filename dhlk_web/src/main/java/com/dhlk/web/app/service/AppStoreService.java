package com.dhlk.web.app.service;

import com.dhlk.domain.Result;
import com.dhlk.entity.app.StoreAppInfo;
import com.dhlk.web.app.service.fbk.AppStoreServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author ztang
 * @date 2020/8/4
 * <p>
 * 商城应用信息service
 */
@FeignClient(value = "app-service/app/store", fallback = AppStoreServiceFbk.class)
public interface AppStoreService {

    @PostMapping(value = "/save")
    Result save(@RequestBody StoreAppInfo storeAppInfo);

    @PostMapping(value = "/update")
    Result update(@RequestBody StoreAppInfo storeAppInfo);

    @GetMapping("/findList")
    Result findList(@RequestParam("groupId") int groupId);

    @PostMapping(value = "/delete")
    Result delete(@RequestParam("id") int id);

}
