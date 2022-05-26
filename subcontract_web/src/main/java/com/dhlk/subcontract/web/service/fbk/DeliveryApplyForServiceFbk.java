package com.dhlk.subcontract.web.service.fbk;

import com.dhlk.domain.Result;
import com.dhlk.entity.sub.DeliveryApplyFor;
import com.dhlk.enums.ResultEnum;
import com.dhlk.subcontract.web.service.DeliveryApplyForService;
import com.dhlk.utils.ResultUtils;
import org.springframework.stereotype.Service;

/**
 * @des:
 * @author: xkliu
 * @date: 2021/03/16
 */
@Service
public class DeliveryApplyForServiceFbk implements DeliveryApplyForService {

    @Override
    public Result save(DeliveryApplyFor deliveryApplyFor) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
