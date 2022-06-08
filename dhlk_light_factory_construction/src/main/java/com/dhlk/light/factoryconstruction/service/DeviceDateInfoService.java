package com.dhlk.light.factoryconstruction.service;

import com.dhlk.light.factoryconstruction.pojo.vo.BindAPVO;

import java.util.List;

/**
 * 设备信息service接口
 * @author wzx
 * @since 2021-08-17
 */
public interface DeviceDateInfoService {

    void updateAP(BindAPVO vo);

    void resetTables();

}
