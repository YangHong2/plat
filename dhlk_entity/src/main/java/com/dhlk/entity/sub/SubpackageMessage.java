package com.dhlk.entity.sub;

import lombok.Data;

import java.io.Serializable;

/**
 * 消息(SubpackageMessage)实体类
 *
 * @author xkliu
 * @since 2021-03-12 09:23:56
 */
@Data
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
    private String createTime;
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



}
