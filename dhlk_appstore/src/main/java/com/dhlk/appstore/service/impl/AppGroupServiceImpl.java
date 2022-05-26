package com.dhlk.appstore.service.impl;


import com.dhlk.appstore.dao.StoreGroupInfoDao;
import com.dhlk.appstore.service.AppGroupService;
import com.dhlk.domain.Result;
import com.dhlk.entity.app.StoreGroupInfo;
import com.dhlk.utils.CheckUtils;
import com.dhlk.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 */
@Service
public class AppGroupServiceImpl implements AppGroupService {

    @Autowired
    StoreGroupInfoDao storeGroupInfoDao;

    @Override
    public Result save(StoreGroupInfo storeGroupInfo) {

        StoreGroupInfo storeGroupInfo1 = storeGroupInfoDao.selectByName(storeGroupInfo.getGroupName());
        if (storeGroupInfo1!=null){
            return ResultUtils.error("该分组已存在");
        }

        //新增
        Integer flag = storeGroupInfoDao.insert(storeGroupInfo);
        return flag > 0 ? ResultUtils.success() : ResultUtils.error("添加分组失败");
    }

    @Override
    public Result update(StoreGroupInfo storeGroupInfo) {
        Integer flag = storeGroupInfoDao.update(storeGroupInfo);
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();

    }

    @Override
    public Result delete(int id) {
        if (CheckUtils.isNumeric(id)) {
            if (storeGroupInfoDao.delete(id) > 0) {
                return ResultUtils.success();
            }
        }
        return ResultUtils.failure();
    }

    @Override
    public Result findList() {

        List<StoreGroupInfo> list = storeGroupInfoDao.findList();
        return ResultUtils.success(list);
    }
}