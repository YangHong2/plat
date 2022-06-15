package com.dhlk.subcontract.web.service;

import com.dhlk.domain.Result;
import com.dhlk.entity.sub.CompanyPages;
import com.dhlk.subcontract.web.service.fbk.CompanyPagesServiceFbk;
import org.springframework.web.bind.annotation.*;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "subcontract-service/companyPages", fallback = CompanyPagesServiceFbk.class)
public interface CompanyPagesService {
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne")
    Result queryById(Integer id);

    /**
     * 修改企业主页
     *
     * @param companyPages
     * @return
     */
    @PostMapping("/update")
    Result update(CompanyPages companyPages);
}
