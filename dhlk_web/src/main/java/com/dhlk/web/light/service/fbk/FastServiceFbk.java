package com.dhlk.web.light.service.fbk;

import com.dhlk.domain.Result;
import com.dhlk.entity.dto.SwitchHumanSenseDTO;
import com.dhlk.entity.light.Switch;
import com.dhlk.enums.ResultEnum;
import com.dhlk.utils.ResultUtils;
import com.dhlk.web.light.service.FastService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xmdeng
 * @date 2022/2/28 10:47
 */
@Service
public class FastServiceFbk implements FastService {


    @Override
    public Result<List<Switch>> getSwitchList() {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result openSwitchLed(List<Integer> switchIds) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result closeLed(List<Integer> switchIds) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result openOrCloseHumanSense(SwitchHumanSenseDTO dto) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
