package com.dhlk.appstore.service.impl;


import com.dhlk.appstore.dao.StoreAppInfoDao;
import com.dhlk.appstore.dao.StoreGroupInfoDao;
import com.dhlk.appstore.service.AppstoreService;
import com.dhlk.domain.Result;
import com.dhlk.entity.app.LocalAppInfo;
import com.dhlk.entity.app.StoreAppInfo;
import com.dhlk.entity.app.StoreGroupInfo;
import com.dhlk.enums.ResultEnum;
import com.dhlk.utils.CheckUtils;
import com.dhlk.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Description

 */
@Service
public class AppstoreServiceImpl implements AppstoreService {

    @Autowired
    StoreAppInfoDao storeAppInfoDao;

    @Override
    public Result save(StoreAppInfo storeAppInfo) {


        StoreAppInfo storeGroupInfo1 = storeAppInfoDao.selectByName(storeAppInfo.getAppName());
        if (storeGroupInfo1!=null){
            return ResultUtils.error(ResultEnum.APP_NAME_ERR);
        }

        if(storeAppInfo != null){
            storeAppInfo.setDownloadCount(112);
        }
        //新增
        Integer flag = storeAppInfoDao.insert(storeAppInfo);
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    public Result update(StoreAppInfo storeAppInfo) {
        Integer count = storeAppInfoDao.selectAppById(storeAppInfo);
        if(count > 0){
            return ResultUtils.error(ResultEnum.APP_NAME_ERR);
        }
        Integer flag = storeAppInfoDao.update(storeAppInfo);
        return flag >= 0 ? ResultUtils.success() : ResultUtils.failure();

    }

    @Override
    public Result delete(int id) {

        if (CheckUtils.isNumeric(id)) {
            StoreAppInfo storeAppInfo = storeAppInfoDao.selectById(id);
            if (storeAppInfo==null){
                return ResultUtils.error("该应用已被其他用户删除，请刷新页面重试");
            }


            if (storeAppInfoDao.delete(id) > 0) {
                return ResultUtils.success();
            }
        }
        return ResultUtils.failure();
    }

    @Override
    public Result findList(int groupId) {
        List<StoreAppInfo> list = storeAppInfoDao.findList(groupId);
        return ResultUtils.success(list);
    }
}