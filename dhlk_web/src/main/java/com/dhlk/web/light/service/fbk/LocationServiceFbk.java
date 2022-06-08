package com.dhlk.web.light.service.fbk;

import com.dhlk.domain.Result;
import com.dhlk.entity.light.Coordinate;
import com.dhlk.entity.light.LightLocation;
import com.dhlk.enums.ResultEnum;
import com.dhlk.utils.ResultUtils;
import com.dhlk.web.light.service.LocationService;
import org.springframework.stereotype.Service;

/**
 * @author xmdeng
 * @date 2021/7/26 14:41
 */
@Service
public class LocationServiceFbk implements LocationService {
    @Override
    public Result getLightLocation() {
        return ResultUtils.failure();
    }

    @Override
    public Result syncLocation(LightLocation lightLocation) {
        return ResultUtils.failure();
    }

    @Override
    public Result isInRange(Coordinate coordinate) {
        return ResultUtils.error(ResultEnum.LOCATION_NOT_RANGE);
    }
}
