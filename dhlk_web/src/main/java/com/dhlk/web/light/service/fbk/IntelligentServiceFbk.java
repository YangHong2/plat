package com.dhlk.web.light.service.fbk;

import com.dhlk.domain.Result;
import com.dhlk.entity.light.IntelligentSwitch;
import com.dhlk.utils.ResultUtils;
import com.dhlk.web.light.service.IntelligentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IntelligentServiceFbk implements IntelligentService {
    @Override
    public Result save(IntelligentSwitch intelligentSwitch) {
        return ResultUtils.failure();
    }

    @Override
    public Result saveBatch(List<IntelligentSwitch> list) {
        return ResultUtils.failure();
    }

    @Override
    public Result findListByAreaId(String areaId) {
        return ResultUtils.failure();
    }

    @Override
    public Result findListByTenantId(String tenantId) {
        return ResultUtils.failure();
    }

    @Override
    public Result findIntelligentBySn(String sn) {
        return ResultUtils.failure();
    }

    @Override
    public Result deleteIntelligent(String id) {
        return ResultUtils.failure();
    }

    @Override
    public Result findButtonBySn(String sn) {
        return ResultUtils.failure();
    }


}
