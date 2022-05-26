package com.dhlk.entity.sub;

import java.io.Serializable;
import java.util.Date;

/**
 * 消息(SubpackageMessage)实体类
 *
 * @author xkliu
 * @since 2021-03-12 09:23:56
 */
public class SubpackageMessage implements Serializable {
    private static final long serialVersionUID = -21991939823666793L;
    /**
     * 主键
     */
    private Integer id;
    /**
     * 消息内容
     */
    private Object content;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 消息类型 (0 咨询 1 金融 2 工程 3 企业 4 项目)
     */
    private Integer type;
    /**
     * 用户
     */
    private Integer userId;
    /**
     * 标题
     */
    private String title;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
