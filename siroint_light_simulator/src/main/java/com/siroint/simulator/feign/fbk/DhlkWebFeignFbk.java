package com.siroint.simulator.feign.fbk;

import com.dhlk.domain.Result;
import com.dhlk.entity.light.LedPower;
import com.dhlk.utils.ResultUtils;
import com.siroint.simulator.feign.DhlkWebFeign;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xmdeng
 * @date 2021/7/23 12:27
 */
@Service
public class DhlkWebFeignFbk implements DhlkWebFeign {
    @Override
    public Result savePower(List<LedPower> list) {
        return ResultUtils.failure();
    }

    @Override
    public Result kaptcha() {
        return ResultUtils.failure();
    }
}
