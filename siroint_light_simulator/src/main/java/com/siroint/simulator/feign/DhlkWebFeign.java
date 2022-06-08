package com.siroint.simulator.feign;

import com.dhlk.domain.Result;
import com.dhlk.entity.light.LedPower;
import com.siroint.simulator.feign.fbk.DhlkWebFeignFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xmdeng
 * @date 2021/7/23 12:26
 */
@FeignClient(name = "DhlkWebFeignClient", url = "http://192.168.64.56:90/",fallback = DhlkWebFeignFbk.class)
public interface DhlkWebFeign {

    @RequestMapping(value = "/dhlk-web/led/savePower",method = RequestMethod.POST)
    Result savePower(@RequestBody List<LedPower> list);


    @RequestMapping(value = "/dhlk-web/kaptcha", method = RequestMethod.GET)
    Result kaptcha();
}
