package com.dhlk.subcontract.web.service;

import com.dhlk.domain.Result;
import com.dhlk.entity.sub.SubpackageUser;
import com.dhlk.subcontract.web.service.fbk.SubpackageUserServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created with IntelliJ IDEA
 *
 * @Auther :yangwang
 * Data:2021/3/15
 * Time:10:50
 * @Description:
 */
@FeignClient(value = "subcontract-service/subpackageUser", fallback = SubpackageUserServiceFbk.class)
public interface SubpackageUserService {

    /**
     * 新增/修改
     */
    @PostMapping("/save")
    Result save(@RequestBody SubpackageUser subpackageUser);

    /**
     * 列表查询
     */
    @GetMapping("/findList")
    Result findList(@RequestParam(value = "companyName",required = false) String companyName,
                           @RequestParam(value = "auditStatus",required = false) Integer auditStatus,
                           @RequestParam(value = "isBlacklist",required = false) Integer isBlacklist,
                           @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize);
}
