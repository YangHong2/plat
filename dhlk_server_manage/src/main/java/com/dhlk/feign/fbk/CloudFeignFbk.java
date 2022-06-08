package com.dhlk.feign.fbk;

import com.dhlk.domain.Result;
import com.dhlk.feign.CloudFeign;
import com.dhlk.utils.ResultUtils;
import org.springframework.stereotype.Service;

/**
 * @author xmdeng
 * @date 2021/8/2 12:09
 */
@Service
public class CloudFeignFbk implements CloudFeign {
    @Override
    public Result sendServiceInfo(Result result) {
        return ResultUtils.failure();
    }
}
