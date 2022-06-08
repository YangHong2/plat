package com.dhlk.light.factoryconstruction.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.dhlk.light.factoryconstruction.common.exception.BaseException;
import com.dhlk.light.factoryconstruction.common.result.ParamErrorResultCodeEnum;
import com.dhlk.light.factoryconstruction.datamap.LedPortMap;
import com.dhlk.light.factoryconstruction.enums.MessageTypeEnum;
import com.dhlk.light.factoryconstruction.mapper.DeviceDataInfoMapper;
import com.dhlk.light.factoryconstruction.pojo.entity.DeviceDataInfo;
import com.dhlk.light.factoryconstruction.pojo.entity.LedData;
import com.dhlk.light.factoryconstruction.pojo.vo.BindAPVO;
import com.dhlk.light.factoryconstruction.service.DeviceDateInfoService;
import com.dhlk.light.factoryconstruction.websocket.WebsocketServerUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 设备信息service实现
 *
 * @author wzx
 * @since 2021-08-17
 */
@Service
public class DeviceDateInfoServiceImpl implements DeviceDateInfoService {

    @Resource
    private DeviceDataInfoMapper deviceDataInfoMapper;

    @Override
    public void resetTables() {
        deviceDataInfoMapper.resetTables();
    }

    /**
     * 修改AP绑定
     * @param  vo  端口号 sn集合 ap
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateAP(BindAPVO vo) {
        if (CollUtil.isNotEmpty(vo.getSnList())) {
            List<DeviceDataInfo> deviceList = new ArrayList<>();
            List<Object> ledList = new ArrayList<>();
            //用于回滚内存中的设备数据
            ConcurrentMap<String, LedData> backupMap = new ConcurrentHashMap<>(LedPortMap.get(vo.getPort()));
            try {
                vo.getSnList().forEach(s -> {
                    deviceList.add(DeviceDataInfo.builder().sn(s).ap(vo.getAp()).build());
                    LedData ledData;
                    if (LedPortMap.get(vo.getPort()).containsKey(s)) {
                        ledData = LedPortMap.get(vo.getPort()).get(s);
                        ledData.setAp(vo.getAp());
                        //更新内存map
                        LedPortMap.get(vo.getPort()).put(s, ledData);
                        ledList.add(ledData);
                    }
                });
                //更新数据库
                deviceDataInfoMapper.batchSaveAP(deviceList);

            } catch (Exception e) {
                //回滚map
                LedPortMap.put(vo.getPort(), backupMap);
                throw new BaseException(ParamErrorResultCodeEnum.PARAM_INVALID,"修改AP绑定失败");
            }
            //发送websocket实时消息
            WebsocketServerUtil.sendRealTimeMessages(vo.getPort(), MessageTypeEnum.TYPE_REAL_TIME_DEVICES.getType(), ledList);
        }
    }
}
