package com.dhlk.basicmodule.service.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dhlk.basicmodule.service.dao.ModuleClickDao;
import com.dhlk.basicmodule.service.service.ModuleClickService;
import com.dhlk.domain.Result;
import com.dhlk.entity.basicmodule.ModuleClick;
import com.dhlk.entity.basicmodule.Tenant;
import com.dhlk.entity.basicmodule.User;
import com.dhlk.enums.ResultEnum;
import com.dhlk.util.AuthUserUtil;
import com.dhlk.utils.CheckUtils;
import com.dhlk.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.util.List;

/**
 * @author xkliu
 * @date 2020/8/24
 */
@Service
@Transactional
public class ModuleClickServiceImpl implements ModuleClickService {

    @Autowired
    private ModuleClickDao ModuleClickDao;

    @Autowired
    private AuthUserUtil authUserUtil;

    @Override
    public Result recordNum(String moduleName) {
        if(CheckUtils.isNull(moduleName)){
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        Integer flag = 0;
        ModuleClick moduleItem = new  ModuleClick();
        //获取当前用户的登录信息
        JSONObject jsonObject = authUserUtil.currentUser();
        if(jsonObject == null){
            return ResultUtils.error("未获取到用户信息");
        }
        User user = jsonObject.toJavaObject(User.class);
        if(CheckUtils.isNull(user.getLoginName())){
            return ResultUtils.error("loginName不存在");
        }
        ModuleClick moduleClick = ModuleClickDao.selectModuleClick(user.getLoginName(), moduleName);
        //moduleClick不为null就给ModuleClick的num加1
        if(moduleClick != null){
            moduleClick.setClickNum(moduleClick.getClickNum() + 1);
            flag = ModuleClickDao.update(moduleClick);
        }else{//新增一条数据
            moduleItem.setClickNum(1);
            moduleItem.setLoginName(user.getLoginName());
            moduleItem.setModuleName(moduleName);
            flag = ModuleClickDao.insert(moduleItem);
        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    public Result findList(Integer pageNum, Integer pageSize) {
        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        PageHelper.startPage(pageNum, pageSize);
        JSONObject jsonObject = authUserUtil.currentUser();
        if(jsonObject == null) {
            return ResultUtils.error("未获取到用户信息");
        }
        User user = jsonObject.toJavaObject(User.class);
        if (CheckUtils.isNull(user.getLoginName())) {
            return ResultUtils.error("loginName不存在");
        }
        List<ModuleClick> moduleClicks = ModuleClickDao.findListByLoginName(user.getLoginName());
        PageInfo<ModuleClick> moduleClickPageInfo = new PageInfo<>(moduleClicks);
        return ResultUtils.success(moduleClickPageInfo);
    }
}
