package com.dhlk.light.factoryconstruction.pojo.dto;

import lombok.Data;

/**
 * 读取wifi配置请求DTO
 * @author yangfan
 * @since 2021-08-19
 */
@Data
public class GetWifiConfigDTO {

    /**
     * 监听端口
     */
    private String port;

    /**
     * 设备sn号
     */
    private String sn;


}
