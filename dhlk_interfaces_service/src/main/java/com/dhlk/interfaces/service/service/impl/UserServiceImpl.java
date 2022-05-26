package com.dhlk.interfaces.service.service.impl;

import com.dhlk.domain.Result;
import com.dhlk.entity.basicmodule.User;
import com.dhlk.interfaces.service.dao.UserDao;
import com.dhlk.interfaces.service.service.UserService;
import com.dhlk.interfaces.service.util.HeaderUtil;
import com.dhlk.utils.CheckUtils;
import com.dhlk.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private HeaderUtil headerUtil;

    @Override
    public Result findList(String name) {
        if(CheckUtils.isNull(headerUtil.tenantId())){
            return ResultUtils.success(new ArrayList<>());
        }else{
            return ResultUtils.success(userDao.findList(name, headerUtil.tenantId()));
        }
    }

    @Override
    public Result findUserById(String id) {
        if(CheckUtils.isNull(id)){
            return ResultUtils.error("参数错误");
        }
        return ResultUtils.success(userDao.findUserById(id));
    }
}
