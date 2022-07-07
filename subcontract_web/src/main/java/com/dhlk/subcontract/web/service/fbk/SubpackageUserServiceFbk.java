package com.dhlk.subcontract.web.service.fbk;

import com.dhlk.domain.Result;
import com.dhlk.entity.sub.Advert;
import com.dhlk.entity.sub.SubpackageUser;
import com.dhlk.enums.ResultEnum;
import com.dhlk.subcontract.web.service.AdvertService;
import com.dhlk.subcontract.web.service.SubpackageUserService;
import com.dhlk.utils.ResultUtils;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA
 *
 * @Auther :yangwang
 * Data:2021/3/15
 * Time:10:50
 * @Description:
 */
@Service
public class SubpackageUserServiceFbk implements SubpackageUserService {

    @Override
    public Result save(SubpackageUser subpackageUser) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
        public Result findList(String companyName, Integer auditStatus, Integer isBlacklist, Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result countUser() {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
