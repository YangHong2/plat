package com.dhlk.subcontract.web.service.fbk;

import com.dhlk.domain.Result;
import com.dhlk.entity.sub.Advert;
import com.dhlk.enums.ResultEnum;
import com.dhlk.subcontract.web.service.AdvertService;
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
public class AdvertFbk implements AdvertService {
    @Override
    public Result save(Advert advert) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result getAdvert(Integer id) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result FindList(String adressNo, String name, String dataId, String createTime, Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result delete(String ids) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
