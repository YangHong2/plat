package com.dhlk.feign.fbk;

import com.dhlk.domain.Result;
import com.dhlk.feign.LocalFeign;
import com.dhlk.utils.ResultUtils;

/**
 * @author xmdeng
 * @date 2021/9/26 14:54
 */

public class LocalFeignFbk implements LocalFeign {
    @Override
    public Result login(String loginName, String password, String tenantCode) {
        return ResultUtils.failure();
    }

    @Override
    public Result logout() {
        return ResultUtils.failure();
    }

    @Override
    public Result getTenantCode() {
        return ResultUtils.failure();
    }
}
