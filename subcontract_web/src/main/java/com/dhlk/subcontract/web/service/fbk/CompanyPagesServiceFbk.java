package com.dhlk.subcontract.web.service.fbk;

import com.dhlk.domain.Result;
import com.dhlk.entity.sub.CompanyPages;
import com.dhlk.enums.ResultEnum;
import com.dhlk.subcontract.web.service.CompanyPagesService;
import com.dhlk.utils.ResultUtils;
import org.springframework.stereotype.Service;

@Service
public class CompanyPagesServiceFbk implements CompanyPagesService {
    @Override
    public Result queryById(Integer id) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result update(CompanyPages companyPages) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
//     return ResultUtils.error(ResultEnum.NETWORK_ERR)