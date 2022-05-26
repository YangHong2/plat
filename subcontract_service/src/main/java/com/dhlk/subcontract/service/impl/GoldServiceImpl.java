package com.dhlk.subcontract.service.impl;

import com.dhlk.domain.Result;
import com.dhlk.entity.sub.Gold;
import com.dhlk.enums.ResultEnum;
import com.dhlk.subcontract.dao.GoldDao;
import com.dhlk.subcontract.service.GoldService;
import com.dhlk.utils.CheckUtils;
import com.dhlk.utils.ResultUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 金币表(Gold)表服务实现类
 *
 * @author xkliu
 * @since 2021-03-16 09:34:17
 */
@Service("goldService")
public class GoldServiceImpl implements GoldService {
    @Resource
    private GoldDao goldDao;

    @Override
    public Result updateGoldByUserId(Integer id, Double addNum) {

        if (!CheckUtils.checkId(id) || CheckUtils.isNull(addNum)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        } else {
            Gold gold = goldDao.queryByUserId(id);
            double v = gold.getGold() + addNum;
            goldDao.addGold(id, v);
            return ResultUtils.success();
        }
    }

    @Override
    public Result updateBalabceByUserId(Integer id, Double addNum) {

        if (!CheckUtils.checkId(id) || CheckUtils.isNull(addNum)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        } else {
            Gold gold = goldDao.queryByUserId(id);
            double v = gold.getBalance() + addNum;

            goldDao.addBalance(id, v);
            return ResultUtils.success();
        }
    }

    @Override
    public Result insert(Gold gold) {
        int flag = 0;

        if (gold != null) {
            flag = goldDao.insert(gold);
        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    public Result queryByUserId(Integer id) {
        return ResultUtils.success(goldDao.queryByUserId(id));
    }

}
