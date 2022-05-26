package com.dhlk.web.basicmodule.service.fbk;

import com.dhlk.domain.Result;
import com.dhlk.entity.basicmodule.InfoBox;
import com.dhlk.entity.basicmodule.SysMessage;
import com.dhlk.enums.ResultEnum;
import com.dhlk.utils.ResultUtils;
import com.dhlk.web.basicmodule.service.SysMessageService;
import org.springframework.stereotype.Service;

@Service
public class SysMessageServiceFbk implements SysMessageService {
    @Override
    public Result save(SysMessage sysMessage) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result delete(InfoBox infoBox) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findPageList(Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }
}
