package com.dhlk.subcontract.web.service.fbk;

import com.dhlk.domain.Result;
import com.dhlk.entity.sub.DevProduce;
import com.dhlk.enums.ResultEnum;
import com.dhlk.subcontract.web.service.DevProduceService;
import com.dhlk.utils.ResultUtils;
import org.springframework.stereotype.Service;

/**
 * @program: dhlk.tenant.plat
 * @description:
 * @author: wqiang
 * @create: 2021-03-15 14:55
 **/

@Service
public class DevProduceServiceFbk implements DevProduceService {
    @Override
    public Result save(DevProduce devProduce) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result delete(String ids) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
