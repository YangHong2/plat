package com.dhlk.subcontract.controller;


import com.dhlk.domain.Result;
import com.dhlk.entity.sub.SubpackageUser;
import com.dhlk.subcontract.service.SubpackageUserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户表(SubpackageUser)表控制层
 *
 * @author xkliu
 * @since 2021-03-12 09:28:47
 */
@RestController
@RequestMapping("/subpackageUser")
public class SubpackageUserController {
    /**
     * 服务对象
     */
    @Resource
    private SubpackageUserService subpackageUserService;

    /**
     * 新增/修改
     */
    @PostMapping("/save")
    public Result save(@RequestBody SubpackageUser subpackageUser) {
        return this.subpackageUserService.save1(subpackageUser);
    }

    /**
     * 列表查询
     */
    @GetMapping("/findList")
    public Result findList(@RequestParam(value = "companyName",required = false) String companyName,
                           @RequestParam(value = "auditStatus",required = false) Integer auditStatus,
                           @RequestParam(value = "isBlacklist",required = false) Integer isBlacklist,
                           @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize) {
        return this.subpackageUserService.findList(companyName,auditStatus,isBlacklist,pageNum,pageSize);
    }
}
