package com.dhlk.web.proxy.service.fbk;

import com.dhlk.domain.Result;
import com.dhlk.enums.ResultEnum;
import com.dhlk.utils.ResultUtils;
import com.dhlk.web.proxy.service.ServerManageService;
import org.springframework.stereotype.Service;

@Service
public class ServerManageServiceFbk implements ServerManageService {
    @Override
    public Result saveServiceInfo(Result result) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result initialServiceInfo() {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
