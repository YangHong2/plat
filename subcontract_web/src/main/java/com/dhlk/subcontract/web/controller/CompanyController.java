package com.dhlk.subcontract.web.controller;

import com.dhlk.domain.Result;
import com.dhlk.entity.sub.Company;
import com.dhlk.subcontract.web.service.CompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 企业管理(Company)表控制层
 *
 * @author xkliu
 * @since 2021-03-12 09:20:58
 */
@RestController
@RequestMapping("/company")
@Api(description = "企业管理", value = "CompanyController")
public class CompanyController {
    /**
     * 服务对象
     */
    @Resource
    private CompanyService companyService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Result selectOne(@RequestParam(value = "id") Integer id) {
        return this.companyService.selectOne(id);
    }

    /**
     * 修改
     */
    @PostMapping("update")
    public Result update(@RequestBody Company company) {
        return this.companyService.update(company);
    }


    /**
     * 获取金融服务商的名字
     *
     * @param name
     * @return
     */
    @GetMapping("/getFinancial")
    @ApiOperation("获取金融服务商的名字")
    public Result getFinancial(@RequestParam(value = "name", required = false) String name) {
        return companyService.getFinancial(name);
    }


    /**
     * 获取咨询服务商的名字
     *
     * @param name
     * @return
     */
    @GetMapping("/getConsult")
    @ApiOperation("获取咨询服务商的名字")
    public Result getConsult(@RequestParam(value = "name", required = false) String name) {
        return companyService.getConsult(name);
    }
}
