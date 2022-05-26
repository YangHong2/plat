package com.dhlk.subcontract.controller;

import com.dhlk.domain.Result;
import com.dhlk.entity.sub.FinancialProvider;
import com.dhlk.subcontract.service.FinancialProviderService;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import net.bytebuddy.asm.Advice;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 金融服务商(FinancialProvider)表控制层
 *
 * @author makejava
 * @since 2021-03-15 09:27:24
 */
@RestController
@RequestMapping("/financialProvider")
public class FinancialProviderController {
    /**
     * 服务对象
     */
    @Resource
    private FinancialProviderService financialProviderService;


    @PostMapping(value = "/save")
    public Result save(@RequestBody FinancialProvider financialProvider) {
        return financialProviderService.save(financialProvider);
    }

    @GetMapping(value = "/delete")
    public Result delete(@RequestParam(value = "ids", required = true) String ids) {
        return financialProviderService.delete(ids);
    }
}
