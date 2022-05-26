package com.dhlk.entity.sub;

import java.io.Serializable;

/**
 * 用户角色关系(UserRole)实体类
 *
 * @author xkliu
 * @since 2021-03-12 09:29:08
 */
public class UserRole implements Serializable {
    private static final long serialVersionUID = -95591210870287480L;
    /**
     * 主键
     */
    private Integer id;
    /**
     * 用户
     */
    private Integer userId;
    /**
     * 角色
     */
    private Integer roleId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

}
