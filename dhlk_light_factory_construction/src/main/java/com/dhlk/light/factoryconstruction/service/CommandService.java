package com.dhlk.light.factoryconstruction.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dhlk.light.factoryconstruction.pojo.dto.CommandLogDTO;
import com.dhlk.light.factoryconstruction.pojo.dto.SendCommandDTO;
import com.dhlk.light.factoryconstruction.pojo.dto.UpdateRecordListDTO;
import com.dhlk.light.factoryconstruction.pojo.entity.smartlamps.WifiConfigData;
import com.dhlk.light.factoryconstruction.pojo.vo.SendCommandVO;
import com.dhlk.light.factoryconstruction.pojo.vo.UpdateRecordListVO;

import java.util.List;

/**
 * 指令服务
 * @author yangfan
 * @since 2021-08-11
 */
public interface CommandService {

    /**
     * 发送指令
     * @param sendCommandDTO 发送指令请求DTO
     */
    List<SendCommandVO> sendCommand(SendCommandDTO sendCommandDTO);


    /**
     * 查询更新记录列表
     * @param updateRecordListDTO 更新记录列表请求DTO
     * @return 分页列表
     */
    Page<UpdateRecordListVO> updateRecordList(UpdateRecordListDTO updateRecordListDTO);


    /**
     * 历史ip查询
     * @param sn 设备sn
     * @return 历史ip列表
     */
    List<String> hisIpList(String sn);


    /**
     * 获取wifi默认配置
     * @return  wifi默认配置
     */
    WifiConfigData getDefaultWifiConfig();


}
