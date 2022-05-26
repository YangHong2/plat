package com.dhlk.entity.sub;

import java.io.Serializable;

/**
 * 角色菜单关系(RoleMenu)实体类
 *
 * @author xkliu
 * @since 2021-03-12 09:23:48
 */
public class RoleMenu implements Serializable {
    private static final long serialVersionUID = 406212377255605012L;
    /**
     * 主键
     */
    private Integer id;
    /**
     * 角色
     */
    private Integer roleId;
    /**
     * 菜单
     */
    private Integer menuId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

}
