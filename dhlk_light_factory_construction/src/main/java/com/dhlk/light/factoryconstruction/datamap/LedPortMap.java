package com.dhlk.light.factoryconstruction.datamap;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.dhlk.light.factoryconstruction.handler.SocketHandler;
import com.dhlk.light.factoryconstruction.pojo.entity.LedData;
import com.dhlk.light.factoryconstruction.pojo.entity.smartlamps.HumanFeelConfigData;
import com.dhlk.light.factoryconstruction.pojo.entity.smartlamps.HumanFeelData;
import com.dhlk.light.factoryconstruction.pojo.entity.smartlamps.LightFeelConfigData;
import com.dhlk.light.factoryconstruction.pojo.entity.smartlamps.LightingStatusData;
import com.dhlk.light.factoryconstruction.pojo.vo.DeviceQueryVO;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;


/**
 * 所有端口的灯泡信息map key:服务端端口号 value：设备列表map (key:设备唯一标识 sn  value:设备对象)
 *
 * @author wzx
 * @since 2021-08-10
 */
public enum LedPortMap {
    /**
     * 实例
     */
    INSTANCE;
    /**
     * 存放服务端端口号 设备 map
     */
    private static final ConcurrentMap<String, ConcurrentMap<String, LedData>> LED_PORT_MAP = new ConcurrentHashMap<>();

    /**
     * 放入一个设备信息
     *
     * @param port 服务端端口号
     * @param map  设备列表map
     */
    public static void put(String port, ConcurrentMap<String, LedData> map) {
        LED_PORT_MAP.put(port, map);
    }

    /**
     * 删除一个服务端端口号对应的所有设备列表map
     *
     * @param port 端口号
     */
    public static void remove(String port) {
        if (port != null) {
            LED_PORT_MAP.remove(port);
        }
    }

    /**
     * 获取一个端口号对应的设备列表map，不存在就新建一个
     *
     * @param port 端口号
     * @return 设备列表map
     */
    public static ConcurrentMap<String, LedData> get(String port) {
        //如果该端口号尚未存入，则put一个新map
        LED_PORT_MAP.putIfAbsent(port, new ConcurrentHashMap<>());
        return LED_PORT_MAP.get(port);

    }

    /**
     * 返回map
     *
     * @return 灯泡端口号设备列表map
     */
    public ConcurrentMap getInstance() {
        return LED_PORT_MAP;
    }


    /**
     * 通过设备唯一标识获取灯数据
     *
     * @param id 设备唯一标识
     * @return 灯数据
     */
    public static LedData getLedDataById(String id) {
        Collection<ConcurrentMap<String, LedData>> values = LED_PORT_MAP.values();
        for (ConcurrentMap<String, LedData> concurrentMap : values) {
            LedData ledData = concurrentMap.get(id);
            if (ledData != null) {
                SocketHandler socketHandler = SocketClinetMap.get(id);
                if (socketHandler.isRunning()) {
                    return ledData;
                }
            }
        }
        return null;
    }

    /**
     * 筛选设备集合
     *
     * @param vo      查询条件
     * @param ledList 设备集合
     * @return 结果集合
     */
    public static List<LedData> queryLedData(DeviceQueryVO vo, List<LedData> ledList) {
        if (CollUtil.isEmpty(ledList)){
            return null;
        }
        if (BeanUtil.isEmpty(vo, "port")) {
            return ledList;
        }
        return ledList.stream().filter(l ->
                (StrUtil.isEmpty(vo.getDeviceType()) || vo.getDeviceType().equals(l.getType()))
                        && (StrUtil.isEmpty(vo.getSn()) || (StrUtil.isNotEmpty(l.getSn()) && l.getSn().contains(vo.getSn())))
                        && (StrUtil.isEmpty(vo.getPeopleOnOff()) ||
                        vo.getPeopleOnOff().equals(Optional.ofNullable(l.getHumanFeelConfigData()).map(HumanFeelConfigData::getStatus).orElse(null)))
                        && (StrUtil.isEmpty(vo.getPeopleStatus()) ||
                        vo.getPeopleStatus().equals(Optional.ofNullable(l.getHumanFeelData()).map(HumanFeelData::getHumanFeelStatus).orElse(null)))
                        && (StrUtil.isEmpty(vo.getLightOnOff()) ||
                        vo.getLightOnOff().equals(Optional.ofNullable(l.getLightFeelConfigData()).map(LightFeelConfigData::getOnOff).orElse(null)))
                        && (StrUtil.isEmpty(vo.getOnOff()) ||
                        vo.getOnOff().equals(Optional.ofNullable(l.getLightingStatusData()).map(LightingStatusData::getRelayStatus).orElse(null)))
                        && (vo.getPowerStart() == null ||
                        vo.getPowerStart().compareTo(new BigDecimal(Optional.ofNullable(l.getLightingStatusData()).map(LightingStatusData::getPower).orElse("0"))) <= 0)
                        && (vo.getPowerEnd() == null ||
                        vo.getPowerEnd().compareTo(new BigDecimal(Optional.ofNullable(l.getLightingStatusData()).map(LightingStatusData::getPower).orElse("0"))) >= 0)
                        && (StrUtil.isEmpty(vo.getVersion()) || vo.getVersion().equals(l.getVersion()))
        ).collect(Collectors.toList());
    }

}
