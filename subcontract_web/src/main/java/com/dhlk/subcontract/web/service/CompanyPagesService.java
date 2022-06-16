package com.dhlk.subcontract.web.service;

import com.dhlk.domain.Result;
import com.dhlk.entity.sub.CompanyPages;
import com.dhlk.subcontract.web.service.fbk.CompanyPagesServiceFbk;
import org.springframework.web.bind.annotation.*;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "subcontract-service/companyPages", fallback = CompanyPagesServiceFbk.class)
public interface CompanyPagesService {
    /**
     * 通过企业ID查询单条数据
     *
     * @param companyId 企业ID
     * @return 单条数据
     */
    @GetMapping("/selectOneByCompanyId")
    Result selectOneByCompanyId(@RequestParam(value = "companyId", required = true) Integer companyId);

    /**
     * 修改企业主页
     *
     * @param companyPages
     * @return
     */
    @PostMapping("/update")
    Result update(@RequestBody CompanyPages companyPages);
}
