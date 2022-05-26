package com.dhlk.subcontract.web.service.fbk;

import com.dhlk.domain.Result;
import com.dhlk.entity.sub.ArticlePub;
import com.dhlk.enums.ResultEnum;
import com.dhlk.subcontract.web.service.ArticlePubService;
import com.dhlk.utils.ResultUtils;
import org.springframework.stereotype.Service;

/**
 * @des:
 * @author: xkliu
 * @date: 2021/03/23
 */
@Service
public class ArticlePubServiceFbk implements ArticlePubService {

    @Override
    public Result selectOne(Integer id) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result save(ArticlePub rticlePub) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findList(String title, Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
