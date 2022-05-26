package com.dhlk.appstore.service.impl;


import com.dhlk.appstore.dao.LocalAppInfoDao;
import com.dhlk.appstore.dao.StoreAppInfoDao;
import com.dhlk.appstore.service.AppLocalService;
import com.dhlk.domain.Result;
import com.dhlk.entity.app.LocalAppInfo;
import com.dhlk.entity.app.StoreAppInfo;
import com.dhlk.entity.app.StoreGroupInfo;
import com.dhlk.util.AuthUserUtil;
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
public class AppLocalServiceImpl implements AppLocalService {

    @Autowired
    LocalAppInfoDao localAppInfoDao;
    @Autowired
    private StoreAppInfoDao storeAppInfoDao;
    @Autowired
    private AuthUserUtil authUserUtil;

    @Override
    public Result save(LocalAppInfo localAppInfo) {

        //APP根据租户下载，改为用户下载
        localAppInfo.setTenantId(authUserUtil.userId());
        LocalAppInfo storeGroupInfo1 = localAppInfoDao.selectByName(localAppInfo);
        if (storeGroupInfo1!=null){
            return ResultUtils.error("该应用已下载");
        }
        StoreAppInfo storeAppInfo = storeAppInfoDao.selectById(localAppInfo.getStoreAppId());
        if(storeAppInfo != null ){
            //更新下载次数
            Integer downLoadCount = storeAppInfo.getDownloadCount()+1;
            storeAppInfo.setDownloadCount(downLoadCount);
            storeAppInfoDao.updateDownloadCount(storeAppInfo);
        }

        //新增
        Integer flag =  localAppInfoDao.insert(localAppInfo);
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    public Result update(LocalAppInfo localAppInfo) {
        Integer flag = localAppInfoDao.update(localAppInfo);
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();

    }

    @Override
    public Result delete(int id) {

        if (CheckUtils.isNumeric(id)) {

            LocalAppInfo storeAppInfo = localAppInfoDao.selectById(id);
            if (storeAppInfo==null){
                return ResultUtils.error("该应用已被其他用户删除，请刷新页面重试");
            }

            if (localAppInfoDao.delete(id) > 0) {
                return ResultUtils.success();
            }
        }
        return ResultUtils.failure();
    }

    @Override
    public Result findList(int tenantId) {

        tenantId = authUserUtil.userId();
        List<LocalAppInfo> list = localAppInfoDao.findList(tenantId);
        return ResultUtils.success(list);
    }
}