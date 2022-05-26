package com.dhlk.interfaces.service.dao;

import com.dhlk.entity.app.AppMenu;
import com.dhlk.entity.basicmodule.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AppMenuDao {
    /**
     * 查询app的权限
     */
    List<AppMenu> findListByCode(@Param("tenantId") Integer tenantId);

    /**
     * 查看角色权限菜单
     */
    List<AppMenu> findListChecked(@Param("roleId") Integer roleId);

    /**
     * 根据用户查询app权限
     */
    List<AppMenu> findMenusByLoginName(User user);
}
