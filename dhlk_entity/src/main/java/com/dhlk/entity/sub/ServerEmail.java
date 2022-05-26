package com.dhlk.entity.sub;

import java.util.Date;
import java.io.Serializable;

/**
 * 服务端邮箱(ServerEmail)实体类
 *
 * @author makejava
 * @since 2021-03-15 14:13:16
 */
public class ServerEmail implements Serializable {
    private static final long serialVersionUID = 458470924134934088L;

    private Integer id;
    /**
     * 服务端邮箱地址
     */
    private String emailAddress;
    /**
     * 服务端邮箱密码
     */
    private String emailPassword;
    /**
     * SMYP服务端地址
     */
    private String smtpAddress;
    /**
     * 邮件内容
     */
    private String content;
    /**
     * 创建时间
     */
    private Date createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getEmailPassword() {
        return emailPassword;
    }

    public void setEmailPassword(String emailPassword) {
        this.emailPassword = emailPassword;
    }

    public String getSmtpAddress() {
        return smtpAddress;
    }

    public void setSmtpAddress(String smtpAddress) {
        this.smtpAddress = smtpAddress;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
