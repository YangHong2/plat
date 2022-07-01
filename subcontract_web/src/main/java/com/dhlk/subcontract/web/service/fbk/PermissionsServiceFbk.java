package com.dhlk.subcontract.web.service.fbk;

import com.dhlk.domain.Result;
import com.dhlk.subcontract.web.service.PermissionsService;
import com.dhlk.utils.ResultUtils;
import org.springframework.stereotype.Service;

@Service
public class PermissionsServiceFbk implements PermissionsService {
    @Override
    public Result insert(Integer menuIds, Integer roleId) {
        return ResultUtils.error("请刷新页面");
    }

    @Override
    public Result findList() {
        return ResultUtils.error("请刷新页面");
    }

    @Override
    public Result delete(Integer userId, Integer roleId) {
        return ResultUtils.error("请刷新页面");
    }
}
