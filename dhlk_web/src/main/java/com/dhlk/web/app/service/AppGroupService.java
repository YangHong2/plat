package com.dhlk.web.app.service;

import com.dhlk.domain.Result;
import com.dhlk.entity.app.StoreGroupInfo;
import com.dhlk.web.app.service.fbk.AppGroupServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author ztang
 * @date 2020/8/4
 * <p>
 * 分组信息service
 */
@FeignClient(value = "app-service/app/group", fallback = AppGroupServiceFbk.class)
public interface AppGroupService {

    @PostMapping(value = "/save")
    Result save(@RequestBody StoreGroupInfo storeGroupInfo);

    @PostMapping(value = "/update")
    Result update(@RequestBody StoreGroupInfo storeGroupInfo);

    @GetMapping("/findList")
    Result findList();

    @PostMapping(value = "/delete")
    Result delete(@RequestParam("id") int id);

}
