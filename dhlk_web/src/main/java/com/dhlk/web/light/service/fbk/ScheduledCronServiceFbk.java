package com.dhlk.web.light.service.fbk;

import com.dhlk.domain.Result;
import com.dhlk.entity.dto.ScheduledCronDTO;
import com.dhlk.entity.dto.SyncScheduledCronDTO;
import com.dhlk.enums.ResultEnum;
import com.dhlk.utils.ResultUtils;
import com.dhlk.web.light.service.ScheduledCronService;
import org.springframework.stereotype.Service;

/**
 * @author xmdeng
 * @date 2021/8/3 12:28
 */
@Service
public class ScheduledCronServiceFbk implements ScheduledCronService {


    @Override
    public Result getPage(ScheduledCronDTO dto) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result syncData(SyncScheduledCronDTO dto) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result delete(Integer id) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result getOne() {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result getList(ScheduledCronDTO dto) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
