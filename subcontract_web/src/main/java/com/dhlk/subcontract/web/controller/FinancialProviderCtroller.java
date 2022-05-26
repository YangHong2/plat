package com.dhlk.subcontract.web.controller;

import com.dhlk.domain.Result;
import com.dhlk.entity.sub.FinancialProvider;
import com.dhlk.subcontract.web.service.FinancialProviderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @program: dhlk.tenant.plat
 * @description:
 * @author: wqiang
 * @create: 2021-03-15 10:03
 **/
@RestController
@RequestMapping("/financialProvider")
@Api(description = "金融服务商", value = "FinancialProviderCtroller")
public class FinancialProviderCtroller {

    /**
     * 服务对象
     */
    @Resource
    private FinancialProviderService financialProviderService;


    @PostMapping(value = "/save")
    @ApiOperation("新增或更新")
    public Result save(@RequestBody FinancialProvider financialProvider) {
        return financialProviderService.save(financialProvider);
    }

    @ApiOperation("删除")
    @GetMapping(value = "/delete")
    public Result delete(@RequestParam(value = "ids", required = true) String ids) {
        return financialProviderService.delete(ids);
    }


}
