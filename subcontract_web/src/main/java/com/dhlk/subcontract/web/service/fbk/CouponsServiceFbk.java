package com.dhlk.subcontract.web.service.fbk;

import com.dhlk.domain.Result;
import com.dhlk.entity.sub.Coupons;
import com.dhlk.enums.ResultEnum;
import com.dhlk.subcontract.web.service.CouponsService;
import com.dhlk.utils.ResultUtils;
import org.springframework.stereotype.Service;

@Service
public class CouponsServiceFbk implements CouponsService {
    @Override
    public Result findList(String couponNo, String createStartTime, String createEndTime, String expireStartTime, String expireEndTime, Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result save(Coupons coupons) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
