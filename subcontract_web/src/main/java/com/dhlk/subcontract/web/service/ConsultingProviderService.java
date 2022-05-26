package com.dhlk.subcontract.web.service;


import com.dhlk.domain.Result;
import com.dhlk.entity.sub.ConsultingProvider;
import com.dhlk.subcontract.web.service.fbk.ConsultingProviderServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "subcontract-service/consultingProvider", fallback = ConsultingProviderServiceFbk.class)
public interface ConsultingProviderService {

    @GetMapping("/delete")
    public Result delete(@RequestParam(value = "ids", required = true) String ids);

    @PostMapping("/save")
    public Result save(@RequestBody ConsultingProvider consultingProvider);
}
