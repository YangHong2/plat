package com.dhlk.subcontract.web.service.fbk;

import com.dhlk.domain.Result;
import com.dhlk.entity.sub.Gold;
import com.dhlk.enums.ResultEnum;
import com.dhlk.subcontract.web.service.GoldService;
import com.dhlk.utils.ResultUtils;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA
 *
 * @Auther :yangwang
 * Data:2021/3/24
 * Time:8:59
 * @Description:
 */
@Service
public class GoldFbk implements GoldService {
    @Override
    public Result updateGoldByUserId(Integer id, Double addNum) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result updateBalabceByUserId(Integer id, Double addNum) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result insert(Gold gold) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result queryByUserId(Integer id) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
