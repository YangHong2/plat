package com.dhlk.web.light.service;

import com.dhlk.domain.Result;
import com.dhlk.entity.dto.LedFaultDTO;
import com.dhlk.web.light.service.fbk.LedFaultServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author xmdeng
 * @date 2021/8/4 14:59
 */
@FeignClient(value = "light-service/ledFault", fallback = LedFaultServiceFbk.class)
public interface LedFaultService {

    @PostMapping("/page")
    Result getPage(@RequestBody LedFaultDTO dto);
}
