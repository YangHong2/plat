package com.dhlk.subcontract.web.service;

import com.dhlk.domain.Result;
import com.dhlk.entity.sub.ProjectClose;
import com.dhlk.subcontract.web.service.fbk.ProjectCloseServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @des:
 * @author: xkliu
 * @date: 2021/03/16
 */
@FeignClient(value = "subcontract-service/projectClose", fallback = ProjectCloseServiceFbk.class)
public interface ProjectCloseService {
    /**
     * 新增/修改
     *
     * @param projectClose
     * @return
     */
    @PostMapping("/save")
    Result save(@RequestBody ProjectClose projectClose);

    /**
     * 项目关闭列表查询
     *
     * @param name
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findList")
    Result findList(@RequestParam(value = "name", required = false) String name,
                    @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                    @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize);

}
