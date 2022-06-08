package com.dhlk.feign;

import com.dhlk.config.FeignConfiguration;
import com.dhlk.domain.Result;
import com.dhlk.feign.fbk.CloudFeignFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author xmdeng
 * @date 2021/8/2 11:43
 */
@FeignClient(name = "CloudFeign", url = "${cloud.host}",fallback = CloudFeignFbk.class,configuration = FeignConfiguration.class)
public interface CloudFeign {

    /**
     * 一体机消息发送至云端
     * @param result
     * @return
     */
    @RequestMapping(value = "/report/service/info",method = RequestMethod.POST)
    Result sendServiceInfo(@RequestBody Result result);
}
