package com.dhlk.light.factoryconstruction.pojo.dto;

import lombok.Data;

import java.util.List;

/**
 * 打印上报命令行请求DTO
 * @author yangfan
 * @since 2021-08-19
 */
@Data
public class PrintReportCommandDTO {

    /**
     * 监听端口
     */
    private Integer port;

    /**
     * 设备sn号列表
     */
    private List<String> snList;
}
