package com.dhlk.subcontract.web.service.fbk;

import com.dhlk.domain.Result;
import com.dhlk.entity.sub.ProjectClose;
import com.dhlk.enums.ResultEnum;
import com.dhlk.subcontract.web.service.ProjectCloseService;
import com.dhlk.utils.ResultUtils;
import org.springframework.stereotype.Service;

/**
 * @des:
 * @author: xkliu
 * @date: 2021/03/16
 */
@Service
public class ProjectCloseServiceFbk implements ProjectCloseService {

    @Override
    public Result save(ProjectClose projectClose) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findList(String name, Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
