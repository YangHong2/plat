package com.dhlk.subcontract.web.service;


import com.dhlk.domain.Result;
import com.dhlk.entity.sub.DevProduce;
import com.dhlk.subcontract.web.service.fbk.ConsultingProviderServiceFbk;
import com.dhlk.subcontract.web.service.fbk.DevProduceServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "subcontract-service/devProduce", fallback = DevProduceServiceFbk.class)
public interface DevProduceService {

    @PostMapping("/save")
    public Result save(@RequestBody DevProduce devProduce);


    @GetMapping(value = "/delete")
    public Result delete(@RequestParam(value = "ids",required = true) String ids);
}
