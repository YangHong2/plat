package com.dhlk.light.factoryconstruction.pojo.dto;

import lombok.Data;

/**
 * 获取固件版本请求DTO
 * @author yangfan
 * @since 2021-08-19
 */
@Data
public class GetFirmwareVersionDTO {

    /**
     * 监听端口
     */
    private String port;

    /**
     * 设备sn号
     */
    private String sn;


}
