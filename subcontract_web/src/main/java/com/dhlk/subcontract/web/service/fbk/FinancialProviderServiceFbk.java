package com.dhlk.subcontract.web.service.fbk;

import com.dhlk.domain.Result;
import com.dhlk.entity.sub.FinancialProvider;
import com.dhlk.enums.ResultEnum;
import com.dhlk.subcontract.web.service.FinancialProviderService;
import com.dhlk.utils.ResultUtils;
import org.springframework.stereotype.Service;

/**
 * @program: dhlk.tenant.plat
 * @description:
 * @author: wqiang
 * @create: 2021-03-15 10:02
 **/
@Service
public class FinancialProviderServiceFbk implements FinancialProviderService {
    @Override
    public Result save(FinancialProvider financialProvider) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result delete(String ids) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
