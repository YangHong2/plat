package com.dhlk.subcontract.web.service.fbk;

import com.dhlk.domain.Result;
import com.dhlk.entity.sub.ReceivingInfo;
import com.dhlk.enums.ResultEnum;
import com.dhlk.subcontract.web.service.ReceivingInfoService;
import com.dhlk.utils.ResultUtils;
import org.springframework.stereotype.Service;

@Service
public class ReceivingInfoServiceFbk implements ReceivingInfoService {
    @Override
    public Result queryById(Integer id) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result selectByProjectId(Integer id) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result insert(ReceivingInfo receivingInfo) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result update(ReceivingInfo receivingInfo) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
