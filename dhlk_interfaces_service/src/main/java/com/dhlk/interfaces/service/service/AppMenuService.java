package com.dhlk.interfaces.service.service;

import com.dhlk.domain.Result;
import com.dhlk.entity.app.AppTenant;
import com.dhlk.entity.basicmodule.User;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
* @Description:    app应用权限菜单
* @Author:         gchen
* @CreateDate:     2020/10/22 9:36
*/
public interface AppMenuService {
    /**
     * 根据用户查询app应用权限
     */
    Map<String, Set> getPermissionsByLoginName(User user);
}
