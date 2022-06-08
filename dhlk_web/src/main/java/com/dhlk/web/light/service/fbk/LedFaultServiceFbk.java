package com.dhlk.web.light.service.fbk;

import com.dhlk.domain.Result;
import com.dhlk.entity.dto.LedFaultDTO;
import com.dhlk.utils.ResultUtils;
import com.dhlk.web.light.service.LedFaultService;
import org.springframework.stereotype.Service;

/**
 * @author xmdeng
 * @date 2021/8/4 15:00
 */
@Service
public class LedFaultServiceFbk implements LedFaultService {

    @Override
    public Result getPage(LedFaultDTO dto) {
        return ResultUtils.failure();
    }
}
