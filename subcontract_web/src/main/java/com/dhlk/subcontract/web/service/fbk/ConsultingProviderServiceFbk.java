package com.dhlk.subcontract.web.service.fbk;

import com.dhlk.domain.Result;
import com.dhlk.entity.sub.ConsultingProvider;
import com.dhlk.enums.ResultEnum;
import com.dhlk.subcontract.web.service.ConsultingProviderService;
import com.dhlk.utils.ResultUtils;
import org.springframework.stereotype.Service;

/**
 * @program: dhlk.tenant.plat
 * @description:
 * @author: wqiang
 * @create: 2021-03-15 14:12
 **/

@Service
public class ConsultingProviderServiceFbk implements ConsultingProviderService {
    @Override
    public Result delete(String ids) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result save(ConsultingProvider consultingProvider) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
