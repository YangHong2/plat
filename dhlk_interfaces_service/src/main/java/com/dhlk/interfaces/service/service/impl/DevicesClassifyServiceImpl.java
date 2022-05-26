package com.dhlk.interfaces.service.service.impl;

import com.dhlk.domain.Result;
import com.dhlk.entity.basicmodule.DevicesClassify;
import com.dhlk.interfaces.service.dao.DevicesClassifyDao;
import com.dhlk.interfaces.service.service.DevicesClassifyService;
import com.dhlk.interfaces.service.util.HeaderUtil;
import com.dhlk.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @Author:         gchen
* @CreateDate:     2020/3/31 10:19
*/
@Service
@Transactional
public class DevicesClassifyServiceImpl implements DevicesClassifyService {
    @Autowired
    private DevicesClassifyDao devicesClassifyDao;
    @Autowired
    private HeaderUtil headerUtil;
    @Override
    public Result findList(String classifyName) {
        List<DevicesClassify> list = devicesClassifyDao.findList(classifyName,headerUtil.tenantId());
        return ResultUtils.success(list);
    }
}
