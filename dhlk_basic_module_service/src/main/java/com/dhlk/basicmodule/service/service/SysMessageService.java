package com.dhlk.basicmodule.service.service;

import com.dhlk.domain.Result;
import com.dhlk.entity.basicmodule.SysMessage;

/**
 * 系统消息管理
 */
public interface SysMessageService {
    /**
     * 新增/修改
     */
    Result save(SysMessage sysMessage);

    /**
     * 物理删除
     * @param ids
     */
    Result delete(String ids);
    /**
     * 分页查询
     */
    Result findPageList(Integer pageNum, Integer pageSize);
}
