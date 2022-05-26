package com.dhlk.basicmodule.service.service.impl;

import com.dhlk.basicmodule.service.dao.SysMessageDao;
import com.dhlk.basicmodule.service.service.SysMessageService;
import com.dhlk.domain.Result;
import com.dhlk.entity.basicmodule.Role;
import com.dhlk.entity.basicmodule.SysMessage;
import com.dhlk.enums.ResultEnum;
import com.dhlk.util.AuthUserUtil;
import com.dhlk.utils.CheckUtils;
import com.dhlk.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysMessageServiceImpl implements SysMessageService {

    @Autowired
    private AuthUserUtil authUserUtil;
    @Autowired
    private SysMessageDao sysMessageDao;

    @Override
    public Result save(SysMessage sysMessage) {
        Integer flag = -1;
        if(CheckUtils.isNull(sysMessage.getId())){
            if(sysMessage.getTenantId() == null){
                sysMessage.setTenantId(authUserUtil.tenantId());
            }
            flag = sysMessageDao.insert(sysMessage);
        }else{
            flag = sysMessageDao.update(sysMessage);
        }
        return flag>0? ResultUtils.success(sysMessage):ResultUtils.failure();
    }

    @Override
    public Result delete(String ids) {
        if (CheckUtils.isNull(ids)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR.getStateInfo());
        }
        String[] split = ids.split(",");
        return sysMessageDao.delete(split) > 0?ResultUtils.success():ResultUtils.failure();
    }

    @Override
    public Result findPageList(Integer pageNum, Integer pageSize) {
        if (CheckUtils.isIntNumeric(pageNum) && CheckUtils.isIntNumeric(pageSize)) {
            PageHelper.startPage(pageNum, pageSize);
            PageInfo<SysMessage> sysMessageInfo = new PageInfo<>(sysMessageDao.findList(authUserUtil.tenantId()));
            return ResultUtils.success(sysMessageInfo);
        } else return ResultUtils.error(ResultEnum.PARAM_ERR.getStateInfo());
    }
}
