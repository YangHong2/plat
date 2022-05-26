package com.dhlk.entity.sub;

import java.io.Serializable;

/**
 * 系统菜单(Menu)实体类
 *
 * @author xkliu
 * @since 2021-03-12 09:21:12
 */
public class Menu implements Serializable {
    private static final long serialVersionUID = -72037119663591800L;

    private Integer id;
    /**
     * 编码
     */
    private String code;
    /**
     * 名称
     */
    private String name;
    /**
     * 请求连接
     */
    private String url;
    /**
     * 状态  0正常 1禁用 2删除
     */
    private Integer status;
    /**
     * 父节点
     */
    private Integer parentId;
    /**
     * 多个用逗号分隔，如：user:list,user:create
     */
    private String perms;
    /**
     * 类型(0 菜单 1 按钮)
     */
    private Integer type;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

}
