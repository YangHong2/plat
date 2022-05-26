package com.dhlk.web.basicmodule.service.fbk;

import com.dhlk.domain.Result;
import com.dhlk.enums.ResultEnum;
import com.dhlk.utils.ResultUtils;
import com.dhlk.web.basicmodule.service.ModuleClickService;
import org.springframework.stereotype.Service;

/**
 * @author xkliu
 * @date 2020/8/24
 */
@Service
public class ModuleClickServiceFbk implements ModuleClickService {

    @Override
    public Result recordNum(String moduleName) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findList(Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
