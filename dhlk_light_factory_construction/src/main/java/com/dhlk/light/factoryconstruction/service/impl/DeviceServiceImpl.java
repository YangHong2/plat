package com.dhlk.light.factoryconstruction.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dhlk.light.factoryconstruction.mapper.DeviceLogMapper;
import com.dhlk.light.factoryconstruction.pojo.dto.CommandLogDTO;
import com.dhlk.light.factoryconstruction.pojo.vo.DebugMessageVO;
import com.dhlk.light.factoryconstruction.pojo.vo.DeviceLogVO;
import com.dhlk.light.factoryconstruction.service.DeviceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


/**
 * 设备服务接口实现
 * @author yangfan
 * @since 2021-08-25
 */
@Service
public class DeviceServiceImpl implements DeviceService {

    @Resource
    private DeviceLogMapper deviceLogMapper;

    /**
     * 查询命令日志列表
     * @param commandLogDTO 请求DTO
     * @return 分页列表
     */
    @Override
    public List<String> deviceLog(CommandLogDTO commandLogDTO) {
        String queryStartTime = commandLogDTO.getQueryStartTime();
        if(StrUtil.isNotEmpty(queryStartTime)){
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate queryDate = LocalDate.parse(queryStartTime,dateTimeFormatter);
            LocalDateTime startTime = LocalDateTime.of(queryDate, LocalTime.MIN);
            LocalDateTime endTime = LocalDateTime.of(queryDate, LocalTime.MAX);
            dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            commandLogDTO.setStartTime(dateTimeFormatter.format(startTime));
            commandLogDTO.setEndTime(dateTimeFormatter.format(endTime));
        }

        List<String> resultList = new ArrayList<>();
        int pageNum = 1;
        int pageSize =100 ;
        Page<DeviceLogVO> page = new Page<>(pageNum, pageSize);
        Page<DeviceLogVO> commandLogVoPage = deviceLogMapper.selectCommandLogList(page, commandLogDTO);
        List<DeviceLogVO> records = commandLogVoPage.getRecords();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        if(records!=null){
            for(DeviceLogVO deviceLogVO : records){
                DebugMessageVO debugMessageVO = new DebugMessageVO();
                debugMessageVO.setSn(deviceLogVO.getSn());
                debugMessageVO.setTime(dateTimeFormatter.format(deviceLogVO.getTime()));
                debugMessageVO.setMessage(deviceLogVO.getMessage());
                debugMessageVO.setCommandType(deviceLogVO.getCommandType());
                debugMessageVO.setMessageType(deviceLogVO.getMessageType());
                resultList.add(debugMessageVO.toString());
            }
        }
        return resultList;
    }
}
