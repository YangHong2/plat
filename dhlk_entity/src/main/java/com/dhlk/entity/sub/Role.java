package com.dhlk.entity.sub;

import java.io.Serializable;

/**
 * 角色管理(Role)实体类
 *
 * @author xkliu
 * @since 2021-03-12 09:23:38
 */
public class Role implements Serializable {
    private static final long serialVersionUID = -81371456880638220L;
    /**
     * 主键
     */
    private Integer id;
    /**
     * 角色名称
     */
    private String roleName;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}
