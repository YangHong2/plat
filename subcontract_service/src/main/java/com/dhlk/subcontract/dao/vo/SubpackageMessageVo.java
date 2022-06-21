package com.dhlk.subcontract.dao.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SubpackageMessageVo implements Serializable {
    private static final long serialVersionUID = -21991939821232193L;
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
    private String userName;
    /**
     * 标题
     */
    private String title;
}
