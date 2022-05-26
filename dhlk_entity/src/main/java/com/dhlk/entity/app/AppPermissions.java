package com.dhlk.entity.app;

import lombok.Data;

@Data
public class AppPermissions {
    private Integer id;

    /** 菜单id */
    private Integer menuId;

    /** 角色id */
    private Integer roleId;

    public AppPermissions( Integer roleId,Integer menuId) {
        this.menuId = menuId;
        this.roleId = roleId;
    }
}
