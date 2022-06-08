package com.dhlk.light.factoryconstruction.service;

import com.dhlk.light.factoryconstruction.pojo.dto.CommandLogDTO;

import java.util.List;

/**
 * 设备服务接口定义
 * @author yangfan
 * @since 2021-08-25
 */
public interface DeviceService {

    /**
     * 查询命令日志列表
     * @param commandLogDTO 请求DTO
     * @return 分页列表
     */
    List<String> deviceLog(CommandLogDTO commandLogDTO);
}
