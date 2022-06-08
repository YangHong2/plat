package com.dhlk.web.light.service;

import com.dhlk.domain.Result;
import com.dhlk.entity.dto.SwitchHumanSenseDTO;
import com.dhlk.entity.light.Switch;
import com.dhlk.web.light.service.fbk.FastServiceFbk;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author xmdeng
 * @date 2022/2/28 10:47
 */
@FeignClient(value = "light-service/fast", fallback = FastServiceFbk.class)
public interface FastService {

    @GetMapping("/switch/list")
    Result<List<Switch>> getSwitchList();

    @PostMapping("/switch/open")
    Result openSwitchLed(@RequestBody List<Integer> switchIds);

    @PostMapping("/switch/close")
    Result closeLed(@RequestBody List<Integer> switchIds);

    @PostMapping("/switch/humanSense")
    Result openOrCloseHumanSense(@RequestBody SwitchHumanSenseDTO dto);
}
