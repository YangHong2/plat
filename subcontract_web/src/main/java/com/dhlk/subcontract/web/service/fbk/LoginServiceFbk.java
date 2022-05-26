package com.dhlk.subcontract.web.service.fbk;

import com.dhlk.domain.Result;
import com.dhlk.entity.sub.SubpackageUser;
import com.dhlk.enums.ResultEnum;
import com.dhlk.subcontract.web.service.LoginService;
import com.dhlk.utils.ResultUtils;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceFbk implements LoginService {
    @Override
    public Result login(SubpackageUser subpackageUser) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result register(SubpackageUser subpackageUser) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result acquireAuthCode(String companyEmail) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result verifyAuthCode(String companyEmail, String authCode) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result logout() {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
