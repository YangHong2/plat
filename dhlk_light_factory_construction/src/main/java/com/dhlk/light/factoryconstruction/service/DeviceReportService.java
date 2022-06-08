package com.dhlk.light.factoryconstruction.service;

import com.dhlk.light.factoryconstruction.pojo.entity.DeviceReportData;
import com.dhlk.light.factoryconstruction.pojo.entity.LedData;

/**
 * 设备上报信息处理接口
 * @author wzx
 * @since 2021-08-17
 */
public interface DeviceReportService {

    /**
     * 处理上报数据
     * @param ledData 设备信息
     * @param deviceReportData 上报信息
     * @param port 端口号
     */
    void handle(LedData ledData, DeviceReportData deviceReportData, String port);

}
