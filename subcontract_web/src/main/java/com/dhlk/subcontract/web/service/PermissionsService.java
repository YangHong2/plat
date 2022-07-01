package com.dhlk.subcontract.web.service;


import com.dhlk.domain.Result;
import com.dhlk.subcontract.web.service.fbk.PermissionsServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(value = "subcontract-service/permissions", fallback = PermissionsServiceFbk.class)
public interface PermissionsService {

    @GetMapping(value = "/save")
    Result insert(@RequestParam(value = "userId", required = false)Integer userId, @RequestParam(value = "roleId", required = false)Integer roleId);

    @GetMapping(value = "/findList")
    Result findList();
    @GetMapping(value = "/deleteByUserIdAndRoleId")
    Result delete(@RequestParam(value = "userId", required = false)Integer userId, @RequestParam(value = "roleId", required = false)Integer roleId);
}
