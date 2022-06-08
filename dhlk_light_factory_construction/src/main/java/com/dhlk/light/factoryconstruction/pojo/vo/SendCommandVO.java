package com.dhlk.light.factoryconstruction.pojo.vo;


import lombok.Data;

/**
 * 发送命令VO
 * @author yangfan
 * @since 2021-08-11
 */
@Data
public class SendCommandVO {

    /**
     * 唯一标识列表
     */
    private String id;

    /**
     * 执行结果代码
     */
    private Integer code;

    /**
     * 执行结果消息
     */
    private String message;
}
