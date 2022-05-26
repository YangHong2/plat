package com.dhlk.appstore.service;

import com.dhlk.domain.Result;
import com.dhlk.entity.app.LocalAppInfo;


/**
 * @Description 本地应用
 * @Author lpsong
 * @Date 2020/6/4
 */
public interface AppLocalService {
    /**
     * 新增/修改
     */
    Result save(LocalAppInfo localAppInfo);

    /**
     * 修改
     * @param localAppInfo
     * @return
     */
    Result update(LocalAppInfo localAppInfo);



    /**
     * 物理删除
     * @param id
     */
    Result delete(int id);


    /**
     * 列表查询
     */
    Result findList(int tenantId);

}
