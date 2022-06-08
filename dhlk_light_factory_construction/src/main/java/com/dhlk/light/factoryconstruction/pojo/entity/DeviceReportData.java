package com.dhlk.light.factoryconstruction.pojo.entity;

import com.dhlk.light.factoryconstruction.enums.CommandTypeEnum;
import com.dhlk.light.factoryconstruction.enums.DeviceTypeEnum;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 设备上报数据
 * @author yangfan
 * @since 2021-08-10
 */
@Data
public class DeviceReportData implements Serializable {
    /**
     *  设备唯一标识id
     */
    private String id;

    /**
     * 设备类型 1byte
     * @see DeviceTypeEnum
     */
    private String deviceType;

    /**
     * 命令类型
     * @see CommandTypeEnum
     */
    private String commandType;

    /**
     * 数据时间
     */
    private LocalDateTime dataTime;

    /**
     * 设备上报数据
     */
    private ReportDetailData reportDetailData;

}
