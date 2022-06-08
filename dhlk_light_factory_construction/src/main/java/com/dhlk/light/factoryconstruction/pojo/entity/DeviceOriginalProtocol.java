package com.dhlk.light.factoryconstruction.pojo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 设备协议原始数据
 * @author yangfan
 * @since 2021-08-10
 */
@Data
public class DeviceOriginalProtocol implements Serializable {

    /**
     * 包头 2byte
     */
    private String head;

    /**
     * 协议版本 1byte
     */
    private String version;

    /**
     * 寻址类型 1byte
     */
    private String addressingType;

    /**
     * 组id 1byte
     */
    private String groupId;

    /**
     * id 7byte
     */
    private String id;

    /**
     * 设备类型 1byte
     */
    private String deviceType;


    /**
     * 命令类型 1byte
     */
    private String commandType;

    /**
     *时间戳 6byte
     */
    private String timestamp;

    /**
     *预留 4byte
     */
    private String reserved;

    /**
     *数据长度 1byte
     */
    private String dataLength;

    /**
     *数据 n*byte
     */

    private String data;

    /**
     * 校验位 1byte
     */
    private String checkDigit;

    /**
     *包尾 3byte
     */
    private String packageEnd;



}
