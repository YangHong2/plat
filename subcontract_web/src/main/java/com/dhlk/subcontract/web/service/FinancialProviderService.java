package com.dhlk.subcontract.web.service;


import com.dhlk.domain.Result;
import com.dhlk.entity.sub.FinancialProvider;
import com.dhlk.subcontract.web.service.fbk.FinancialProviderServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "subcontract-service/financialProvider", fallback = FinancialProviderServiceFbk.class)
public interface FinancialProviderService {

    @PostMapping("/save")
    public Result save(FinancialProvider financialProvider);


    @GetMapping("/delete")
    public Result delete(@RequestParam(value = "ids", required = true) String ids);
}
