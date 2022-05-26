package com.dhlk.subcontract.web.service.fbk;

import com.dhlk.domain.Result;
import com.dhlk.entity.sub.Company;
import com.dhlk.enums.ResultEnum;
import com.dhlk.subcontract.web.service.CompanyService;
import com.dhlk.utils.ResultUtils;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceFbk implements CompanyService {
    @Override
    public Result selectOne(Integer id) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result update(Company company) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result getFinancial(String name) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result getConsult(String name) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
