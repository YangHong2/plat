package com.dhlk.web.app.service.fbk;

import com.dhlk.domain.Result;
import com.dhlk.entity.app.LocalAppInfo;
import com.dhlk.enums.ResultEnum;
import com.dhlk.utils.ResultUtils;
import com.dhlk.web.app.service.AppLocalService;
import org.springframework.stereotype.Service;

/**
 * @author ztang
 * @date 2020/7/10
 * <p>
 * 代理服务调用失败时的实现类
 */

@Service
public class AppLocalServiceFbk implements AppLocalService {
    @Override
    public Result save(LocalAppInfo loaclAppInfo) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result update(LocalAppInfo loaclAppInfo) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findList(int tenantId) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result delete(int id) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

  }
