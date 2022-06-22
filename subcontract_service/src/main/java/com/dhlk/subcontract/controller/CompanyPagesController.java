package com.dhlk.subcontract.controller;

import com.dhlk.domain.Result;
import com.dhlk.entity.sub.CompanyPages;
import com.dhlk.subcontract.service.CompanyPagesService;
import com.dhlk.utils.ResultUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 企业主页(CompanyPages)表控制层
 *
 * @author xkliu
 * @since 2021-03-12 09:20:59
 */
@RestController
@RequestMapping("/companyPages")
public class CompanyPagesController {
    /**
     * 服务对象
     */
    @Resource
    private CompanyPagesService companyPagesService;

    /**
     * 通过企业ID查询单条数据
     *
     * @param companyId 企业ID
     * @return 单条数据
     */
    @GetMapping("/selectOneByCompanyId")
    public Result selectOne(@RequestParam(value = "companyId", required = true) Integer companyId) {
        return ResultUtils.success(this.companyPagesService.selectOneByCompanyId(companyId));
    }

    /**
     * 修改企业主页
     * @param companyPages
     *
     *
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody CompanyPages companyPages) {
        return  companyPagesService.update(companyPages);
    }
}
