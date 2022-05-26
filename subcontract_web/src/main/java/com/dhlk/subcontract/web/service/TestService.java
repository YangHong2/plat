package com.dhlk.subcontract.web.service;

import com.dhlk.domain.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "subcontract-service/test")
public interface TestService {
    @GetMapping("/helloWorld")
    Result helloWorld();
}
