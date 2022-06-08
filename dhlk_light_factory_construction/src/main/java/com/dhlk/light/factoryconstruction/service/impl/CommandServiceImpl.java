package com.dhlk.light.factoryconstruction.service.impl;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dhlk.light.factoryconstruction.common.exception.BaseException;
import com.dhlk.light.factoryconstruction.common.result.ParamErrorResultCodeEnum;
import com.dhlk.light.factoryconstruction.datamap.LedPortMap;
import com.dhlk.light.factoryconstruction.enums.CommandRequestEnum;
import com.dhlk.light.factoryconstruction.enums.UpdateRecordCommandTypeEnum;
import com.dhlk.light.factoryconstruction.handler.SendCommandHelper;
import com.dhlk.light.factoryconstruction.mapper.DefaultWifiConfigMapper;
import com.dhlk.light.factoryconstruction.mapper.DeviceHisIpMapper;
import com.dhlk.light.factoryconstruction.mapper.UpdateRecordMapper;
import com.dhlk.light.factoryconstruction.pojo.dto.SendCommandDTO;
import com.dhlk.light.factoryconstruction.pojo.dto.UpdateRecordListDTO;
import com.dhlk.light.factoryconstruction.pojo.entity.DefaultWifiConfig;
import com.dhlk.light.factoryconstruction.pojo.entity.LedData;
import com.dhlk.light.factoryconstruction.pojo.entity.command.LightFeelParam;
import com.dhlk.light.factoryconstruction.pojo.entity.smartlamps.HumanFeelConfigData;
import com.dhlk.light.factoryconstruction.pojo.entity.smartlamps.LightFeelConfigData;
import com.dhlk.light.factoryconstruction.pojo.entity.smartlamps.WifiConfigData;
import com.dhlk.light.factoryconstruction.pojo.vo.SendCommandVO;
import com.dhlk.light.factoryconstruction.pojo.vo.UpdateRecordListVO;
import com.dhlk.light.factoryconstruction.service.CommandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 指令服务实现
 * @author yangfan
 * @since 2021-08-11
 */
@Slf4j
@Service
public class CommandServiceImpl implements CommandService {

    @Resource
    private UpdateRecordMapper updateRecordMapper;

    @Resource
    private DeviceHisIpMapper deviceHisIpMapper;

    @Resource
    private DefaultWifiConfigMapper defaultWifiConfigMapper;



    /**
     * 发送指令
     * @param sendCommandDTO 发送指令请求DTO
     */
    @Override
    public List<SendCommandVO> sendCommand(SendCommandDTO sendCommandDTO) {
        //操作命令
        Integer commandId = sendCommandDTO.getCommandId();
        CommandRequestEnum commandRequestEnum = CommandRequestEnum.getCommandRequestEnum(commandId);

        if(commandRequestEnum==null){
            throw new BaseException(ParamErrorResultCodeEnum.PARAM_INVALID,"请求命令类型错误");
        }
        List<String> idList = sendCommandDTO.getIdList();
        List<SendCommandVO> sendCommandResultList = new ArrayList<>();
        switch (commandRequestEnum){
            case OPEN_LED:
                sendCommandResultList = SendCommandHelper.openLed(idList.toArray(new String[0]));
                break;
            case CLOSE_LED:
                sendCommandResultList = SendCommandHelper.closeLed(idList.toArray(new String[0]));
                break;
            case FLASHING_LIGHT:
                sendCommandResultList = SendCommandHelper.flashingLight(idList.toArray(new String[0]));
                break;
            case HUMAN_FEEL_CONFIG:
                sendCommandResultList = SendCommandHelper.humanFellConfig(idList.toArray(new String[0]));
                break;
            case GET_WIFI_CONFIG:
                sendCommandResultList = SendCommandHelper.getWifiConfig(idList.toArray(new String[0]));
                break;
            case REBOOT_LED:
                sendCommandResultList = SendCommandHelper.rebootLed(idList.toArray(new String[0]));
                break;
            case BRIGHTNESS:
                sendCommandResultList = SendCommandHelper.brightness(sendCommandDTO.getBrightnessParam(),idList.toArray(new String[0]));
                break;
            case ENERGY_CLEARED:
                sendCommandResultList = SendCommandHelper.energyCleared(idList.toArray(new String[0]));
                break;
            case AP_RESET:
                sendCommandResultList = SendCommandHelper.apReset(sendCommandDTO.getApParam(),idList.toArray(new String[0]));
                break;
            case REPORT_TIME_SLOT:
                sendCommandResultList = SendCommandHelper.reportTimeSlot(sendCommandDTO.getReportTimeParam(),idList.toArray(new String[0]));
                break;
            case HUMAN_FEEL:
                sendCommandResultList = SendCommandHelper.humanFeel(sendCommandDTO.getHumanFeelParam(),idList.toArray(new String[0]));
                break;
            case LIGHT_FEEL:
                for (String id : idList){
                    LightFeelParam lightFeelParam= sendCommandDTO.getLightFeelParam();
                    String illumiMode = lightFeelParam.getIllumiMode();
                    //读取内存数据
                    LedData ledData= LedPortMap.getLedDataById(id);
                    if (!ObjectUtils.isEmpty(ledData) && !ObjectUtils.isEmpty(ledData.getLightFeelConfigData())){
                        LightFeelConfigData lightFeelConfigData = ledData.getLightFeelConfigData();
                        lightFeelParam.setIllumiLowest(lightFeelConfigData.getIllumiLowest());
                        lightFeelParam.setIllumiLowestMax(lightFeelConfigData.getIllumiLowestMax());
                        // 若模式为开关控灯  则照度上限值对应亮度最小值读取内存中的值
                        if ("0".equals(illumiMode)){
                            lightFeelParam.setIllumiHighestMin(lightFeelConfigData.getIllumiHighestMin());
                        }
                    }else {
                        log.info("内存中获取光感为空!");
                        throw new BaseException(ParamErrorResultCodeEnum.PARAM_INVALID,"内存中获取光感设置数据为空!");
                    }
                    List<SendCommandVO> lightFeelList= SendCommandHelper.lightFeel(lightFeelParam,id);
                    sendCommandResultList.addAll(lightFeelList);
                }
                break;
            case SET_WIFI_CONFIG:
                sendCommandResultList = SendCommandHelper.wifi(sendCommandDTO.getWifiParam(),idList.toArray(new String[0]));
                break;
            case OBTAIN_LIGHT_FEEL:
                sendCommandResultList = SendCommandHelper.obtainLightFeel(idList.toArray(new String[0]));
                break;
            case OBTAIN_TIME_SLOT:
                sendCommandResultList = SendCommandHelper.obtainTimeSlot(idList.toArray(new String[0]));
                break;
            default:
                throw new BaseException(ParamErrorResultCodeEnum.PARAM_INVALID,"请求命令类型错误");
        }
        return sendCommandResultList;
    }


    /**
     * 查询更新记录列表
     * @param updateRecordListDTO 更新记录列表请求DTO
     * @return 分页列表
     */
    @Override
    public Page<UpdateRecordListVO> updateRecordList(UpdateRecordListDTO updateRecordListDTO) {
        int pageNum = updateRecordListDTO.getPageNumber() == null ? 1 : updateRecordListDTO.getPageNumber();
        int pageSize = updateRecordListDTO.getPageSize() ==null ? 10: updateRecordListDTO.getPageSize() ;
        Page<UpdateRecordListVO> page = new Page<>(pageNum, pageSize);


        String queryStartTime = updateRecordListDTO.getQueryStartTime();
        if(StrUtil.isNotEmpty(queryStartTime)){
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate queryDate = LocalDate.parse(queryStartTime,dateTimeFormatter);
            LocalDateTime startTime = LocalDateTime.of(queryDate, LocalTime.MIN);
            LocalDateTime endTime = LocalDateTime.of(queryDate, LocalTime.MAX);

            dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            updateRecordListDTO.setStartTime(dateTimeFormatter.format(startTime));
            updateRecordListDTO.setEndTime(dateTimeFormatter.format(endTime));
        }

        Page<UpdateRecordListVO> updateRecordListVoPage = updateRecordMapper.selectUpdateRecordList(page, updateRecordListDTO);
        List<UpdateRecordListVO> records = updateRecordListVoPage.getRecords();
        buildUpdateRecordListVoList(records);
        return updateRecordListVoPage;
    }

    /**
     * 构建返回数据
     * @param records 查询结果
     */
    private void buildUpdateRecordListVoList(List<UpdateRecordListVO> records) {
        if(!CollectionUtils.isEmpty(records)){
            for(UpdateRecordListVO vo : records){
                List<String> differentFieldList = new ArrayList<>();
                String commandType = vo.getCommandType();
                String after = vo.getAfter();
                String before = vo.getBefore();
                if(UpdateRecordCommandTypeEnum.SET_WIFI_CONFIG.getCommandType().equals(commandType)){
                    WifiConfigData wifiConfigDataBefore = JSON.parseObject(before, WifiConfigData.class);
                    WifiConfigData wifiConfigDataAfter = JSON.parseObject(after, WifiConfigData.class);
                    Field[] fields = ReflectUtil.getFields(WifiConfigData.class);
                    for(Field field : fields){
                        Object fieldValueBefore = ReflectUtil.getFieldValue(wifiConfigDataBefore, field.getName());
                        Object fieldValueAfter = ReflectUtil.getFieldValue(wifiConfigDataAfter, field.getName());
                        if(!Objects.equals(fieldValueAfter,fieldValueBefore) ){
                            differentFieldList.add(field.getName());
                        }
                    }
                }else if(UpdateRecordCommandTypeEnum.BRIGHTNESS.getCommandType().equals(commandType)){
                    if(!Objects.equals(after,before) ){
                        differentFieldList.add("light");
                    }
                }else if(UpdateRecordCommandTypeEnum.SET_TIME_SLOT.getCommandType().equals(commandType)){
                    if(!Objects.equals(after,before) ){
                        differentFieldList.add("timeSlot");
                    }
                }else if(UpdateRecordCommandTypeEnum.HUMAN_FEEL.getCommandType().equals(commandType)){
                    HumanFeelConfigData beforeHumanFeel = JSON.parseObject(before, HumanFeelConfigData.class);
                    HumanFeelConfigData afterHumanFeel = JSON.parseObject(after, HumanFeelConfigData.class);

                    Field[] fields = ReflectUtil.getFields(HumanFeelConfigData.class);

                    for(Field field : fields){
                        Object fieldValueBefore = ReflectUtil.getFieldValue(beforeHumanFeel, field.getName());
                        Object fieldValueAfter = ReflectUtil.getFieldValue(afterHumanFeel, field.getName());
                        if(!Objects.equals(fieldValueAfter,fieldValueBefore) ){
                            differentFieldList.add(field.getName());
                        }
                    }
                }else if(UpdateRecordCommandTypeEnum.LIGHT_FEEL.getCommandType().equals(commandType)){
                    LightFeelConfigData beforeLightFeel = JSON.parseObject(before, LightFeelConfigData.class);
                    LightFeelConfigData afterLightFeel = JSON.parseObject(after, LightFeelConfigData.class);
                    Field[] fields = ReflectUtil.getFields(LightFeelConfigData.class);
                    for(Field field : fields){
                        Object fieldValueBefore = ReflectUtil.getFieldValue(beforeLightFeel, field.getName());
                        Object fieldValueAfter = ReflectUtil.getFieldValue(afterLightFeel, field.getName());
                        if(!Objects.equals(fieldValueAfter,fieldValueBefore) ){
                            differentFieldList.add(field.getName());
                        }
                    }
                }
                vo.setDifferentFieldList(differentFieldList);
            }
        }
    }

    /**
     * 历史ip查询
     * @param sn 设备sn
     * @return 历史ip列表
     */
    @Override
    public List<String> hisIpList(String sn) {
        return deviceHisIpMapper.hisIpListBySn(sn);
    }


    /**
     * 获取wifi默认配置
     * @return  wifi默认配置
     */
    @Override
    public WifiConfigData getDefaultWifiConfig() {
        List<DefaultWifiConfig> defaultWifiConfigList = defaultWifiConfigMapper.selectList(null);
        WifiConfigData wifiConfigData = new WifiConfigData();
        DefaultWifiConfig defaultWifiConfig = defaultWifiConfigList.get(0);
        wifiConfigData.setModule(defaultWifiConfig.getWifiModule());
        wifiConfigData.setDualFrequency(defaultWifiConfig.getFrequencyBand());
        wifiConfigData.setSsId(defaultWifiConfig.getSsid());
        wifiConfigData.setPassword(defaultWifiConfig.getPassword());
        wifiConfigData.setIp(defaultWifiConfig.getServiceIp());

        return wifiConfigData;
    }


}
