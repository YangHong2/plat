package com.dhlk.light.factoryconstruction.pojo.entity.smartlamps;

import com.dhlk.light.factoryconstruction.pojo.entity.ReportDetailData;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 固件版本信息
 * @author yangfan
 * @since 2021-08-19
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class FirmwareVersionData extends ReportDetailData {

    /**
     * 固件版本
     */
    private String version;
}
