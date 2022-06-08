package com.dhlk.feign;

import com.dhlk.config.FeignConfiguration;
import com.dhlk.domain.Result;
import com.dhlk.feign.fbk.LocalFeignFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author xmdeng
 * @date 2021/9/26 14:54
 */
@FeignClient(name = "LocalFeign", url = "${local.host}",fallback = LocalFeignFbk.class,configuration = FeignConfiguration.class)
public interface LocalFeign {

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    Result login(@RequestParam("loginName") String loginName,
                        @RequestParam("password") String password,
                        @RequestParam("tenantCode") String tenantCode);

    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    Result logout();

    @RequestMapping(value = "getTenantCode",method = RequestMethod.GET)
    public Result getTenantCode();
}
