package com.dhlk.basicmodule.service.dao;

import com.dhlk.entity.basicmodule.SysMessage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description 系统消息
 * @Author gchen
 * @Date 2020/8/20
 */
@Repository
public interface SysMessageDao {

    Integer insert(SysMessage sysMessage);

    Integer update(SysMessage sysMessage);

    Integer delete(@Param("ids") String[] ids);

    List<SysMessage> findList(@Param("tenantId")Integer tenantId);
}