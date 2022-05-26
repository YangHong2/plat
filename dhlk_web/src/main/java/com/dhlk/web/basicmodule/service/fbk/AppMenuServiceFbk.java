package com.dhlk.web.basicmodule.service.fbk;

import com.dhlk.domain.Result;
import com.dhlk.entity.app.AppTenant;
import com.dhlk.enums.ResultEnum;
import com.dhlk.utils.ResultUtils;
import com.dhlk.web.basicmodule.service.AppMenuService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppMenuServiceFbk implements AppMenuService {
    @Override
    public Result findListAppChecked(Integer tenantId) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result addAppTenant(List<AppTenant> appTenants) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findListApp(Integer tenantId) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findListRoleChecked(Integer roleId, Integer tenantId) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result addAppPermissions(Integer roleId, String menuIds) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
