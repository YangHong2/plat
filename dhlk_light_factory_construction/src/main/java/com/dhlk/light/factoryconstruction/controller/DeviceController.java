package com.dhlk.light.factoryconstruction.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.dhlk.light.factoryconstruction.common.result.ResultVO;
import com.dhlk.light.factoryconstruction.constants.SocketConstant;
import com.dhlk.light.factoryconstruction.datamap.LedPortMap;
import com.dhlk.light.factoryconstruction.enums.CommandTypeEnum;
import com.dhlk.light.factoryconstruction.enums.MessageEnum;
import com.dhlk.light.factoryconstruction.handler.SendCommandHelper;
import com.dhlk.light.factoryconstruction.pojo.dto.CommandLogDTO;
import com.dhlk.light.factoryconstruction.pojo.entity.LedData;
import com.dhlk.light.factoryconstruction.pojo.entity.smartlamps.HumanFeelConfigData;
import com.dhlk.light.factoryconstruction.pojo.entity.smartlamps.LightFeelConfigData;
import com.dhlk.light.factoryconstruction.pojo.vo.CommandTypeVO;
import com.dhlk.light.factoryconstruction.pojo.vo.DeviceQueryVO;
import com.dhlk.light.factoryconstruction.pojo.vo.MessageTypeVO;
import com.dhlk.light.factoryconstruction.service.DeviceService;
import com.dhlk.light.factoryconstruction.websocket.WebsocketServerUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author wzx
 * @date 2021/8/9 17:04
 */
@RestController
@Api(tags = "设备控制器")
@Slf4j
@RequestMapping("/device")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @PostMapping(value = "/query")
    @ApiOperation("条件查询")
    public ResultVO<List<LedData>> select(@RequestBody @Validated DeviceQueryVO vo) {
        if (StrUtil.isEmpty(vo.getSessionId())){
            return ResultVO.ok();
        }
        List<LedData> ledList = new ArrayList<>(LedPortMap.get(vo.getPort()).values());
        List<LedData> result;
        if (BeanUtil.isEmpty(vo, SocketConstant.PORT,SocketConstant.SESSION_ID)) {
            result = ledList;
            WebsocketServerUtil.QUERY.remove(vo.getSessionId());
        }else {
            result = LedPortMap.queryLedData(vo, ledList);
            WebsocketServerUtil.QUERY.put(vo.getSessionId(),vo);
        }
        return ResultVO.ok(result);
    }

    @GetMapping("/readHuman")
    @ApiOperation(value = "读取人感")
    public ResultVO<HumanFeelConfigData> readHuman(@ApiParam(required = true, value = "端口号") @RequestParam String port,
                                                   @ApiParam(required = true, value = "设备sn号") @RequestParam String sn) {
        SendCommandHelper.humanFellConfig(sn);
        return ResultVO.ok(Optional.ofNullable(LedPortMap.get(port).get(sn)).map(LedData::getHumanFeelConfigData).orElse(null));
    }

    @GetMapping("/readLight")
    @ApiOperation(value = "读取光感")
    public ResultVO<LightFeelConfigData> readLight(@ApiParam(required = true, value = "端口号") @RequestParam String port,
                                                   @ApiParam(required = true, value = "设备sn号") @RequestParam String sn) {
        SendCommandHelper.obtainLightFeel(sn);
        return ResultVO.ok(Optional.ofNullable(LedPortMap.get(port).get(sn)).map(LedData::getLightFeelConfigData).orElse(null));
    }


    @GetMapping("/deviceLog")
    @ApiOperation("设备日志列表")
    public ResultVO<List<String>> deviceLog(@ApiParam(required = true, value = "设备日志列表请求DTO") CommandLogDTO commandLogDTO) {
        String sessionId = commandLogDTO.getSessionId();
        if (BeanUtil.isEmpty(commandLogDTO, "sessionId")) {
            WebsocketServerUtil.DEBUG_QUERY_CONDITION_MAP.remove(sessionId);
        }else {
            if(StrUtil.isNotEmpty(sessionId)){
                WebsocketServerUtil.DEBUG_QUERY_CONDITION_MAP.put(sessionId,commandLogDTO);
            }
        }
        return ResultVO.ok(deviceService.deviceLog(commandLogDTO));
    }

    @GetMapping("/commandTypeList")
    @ApiOperation("获取命令类型列表")
    public ResultVO<List<CommandTypeVO>> getCommandTypeList() {
        List<CommandTypeVO> resultVoList = new ArrayList<>();
        for(CommandTypeEnum commandTypeEnum:CommandTypeEnum.values()){
            CommandTypeVO vo = new CommandTypeVO();
            vo.setMessageType(commandTypeEnum.getMessageType());
            vo.setCommandType(commandTypeEnum.getCommandType());
            vo.setCommandDesc(commandTypeEnum.getDesc());
            resultVoList.add(vo);
        }
        return ResultVO.ok(resultVoList);
    }

    @GetMapping("/messageTypeList")
    @ApiOperation("获取消息类型列表")
    public ResultVO<List<MessageTypeVO>> getMessageTypeList() {
        List<MessageTypeVO> resultVoList = new ArrayList<>();
        for(MessageEnum messageEnum:MessageEnum.values()){
            MessageTypeVO vo = new MessageTypeVO();
            vo.setMessageType(messageEnum.getType());
            vo.setMessageDesc(messageEnum.getDesc());
            resultVoList.add(vo);
        }
        return ResultVO.ok(resultVoList);
    }
}
