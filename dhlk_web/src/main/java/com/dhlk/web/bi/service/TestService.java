package com.dhlk.web.bi.service;

import com.dhlk.domain.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "bi-tools/test")
public interface TestService {
    @GetMapping("/hello")
    Result hello();
}
