package com.dhlk.light.factoryconstruction.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dhlk.light.factoryconstruction.common.result.ResultVO;
import com.dhlk.light.factoryconstruction.datamap.LedPortMap;
import com.dhlk.light.factoryconstruction.datamap.PrintReportCommandDeviceMap;
import com.dhlk.light.factoryconstruction.enums.UpdateRecordCommandTypeEnum;
import com.dhlk.light.factoryconstruction.handler.SendCommandHelper;
import com.dhlk.light.factoryconstruction.pojo.dto.*;
import com.dhlk.light.factoryconstruction.pojo.entity.LedData;
import com.dhlk.light.factoryconstruction.pojo.entity.smartlamps.WifiConfigData;
import com.dhlk.light.factoryconstruction.pojo.vo.BindAPVO;
import com.dhlk.light.factoryconstruction.pojo.vo.SendCommandVO;
import com.dhlk.light.factoryconstruction.pojo.vo.UpdateRecordCommandTypeListVO;
import com.dhlk.light.factoryconstruction.pojo.vo.UpdateRecordListVO;
import com.dhlk.light.factoryconstruction.service.CommandService;
import com.dhlk.light.factoryconstruction.service.DeviceDateInfoService;
import com.dhlk.light.factoryconstruction.service.FirmwareUpdateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

/**
 * 灯指令控制器
 * @author yangfan
 * @since 2021-08-11
 */
@RestController
@Slf4j
@RequestMapping("/command")
@Api(tags = "命令控制器")
public class CommandController {

    @Autowired
    private CommandService commandService;

    @Autowired
    private FirmwareUpdateService firmwareUpdateService;

    @Autowired
    private DeviceDateInfoService deviceDateInfoService;

    @PostMapping(value = "/sendCommand")
    @ApiOperation("发送指令")
    public ResultVO<List<SendCommandVO>> sendCommand(@ApiParam(required = true, value = "发送指令请求DTO") @RequestBody SendCommandDTO sendCommandDTO) {
        return ResultVO.ok(commandService.sendCommand(sendCommandDTO));
    }

    @PostMapping(value = "/upload/firmware")
    @ApiOperation("固件升级")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "file", value = "固件包（.bin）", required = true,dataType = "__File"),
//            @ApiImplicitParam(name = "devIds" ,value = "设备ID（逗号分隔）",dataType = "String"),
//            @ApiImplicitParam(name = "threadSize",value = "最大并发数",dataType = "Integer")
//    })
    public ResultVO uploadFirmware(@RequestParam(value = "file") MultipartFile file,
                                   @RequestParam("devIds") String devIds,
                                   @RequestParam("threadSize") Integer threadSize){
        firmwareUpdateService.uploadFirmware(file,devIds,threadSize);
        return ResultVO.ok();
    }

    @GetMapping("/updateRecordCommandTypeList")
    @ApiOperation(value = "命令修改记录命令列表查询")
    public ResultVO<List<UpdateRecordCommandTypeListVO>> updateRecordCommandTypeList(){
        List<UpdateRecordCommandTypeListVO> resultVoList = new ArrayList<>();
        for(UpdateRecordCommandTypeEnum commandTypeEnum:UpdateRecordCommandTypeEnum.values()){
            UpdateRecordCommandTypeListVO vo = new UpdateRecordCommandTypeListVO();
            vo.setCommandType(commandTypeEnum.getCommandType());
            vo.setCommandDesc(commandTypeEnum.getDesc());
            resultVoList.add(vo);
        }
        return ResultVO.ok(resultVoList);
    }

    @GetMapping("/updateRecordList")
    @ApiOperation("更新记录列表查询")
    public ResultVO<Page<UpdateRecordListVO>> updateRecordList(@ApiParam(required = true, value = "更新记录列表请求DTO") UpdateRecordListDTO updateRecordListDTO) {
        return ResultVO.ok(commandService.updateRecordList(updateRecordListDTO));
    }

    @PostMapping(value = "/bindAP")
    @ApiOperation("绑定AP")
    public ResultVO bindAP(@ApiParam(required = true, value = "绑定AP VO") @RequestBody @Validated BindAPVO vo) {
        vo.setAp(true);
        deviceDateInfoService.updateAP(vo);
        return ResultVO.ok();
    }

    @PostMapping(value = "/untieAP")
    @ApiOperation("解绑AP")
    public ResultVO untieAP(@ApiParam(required = true, value = "解绑AP VO") @RequestBody @Validated BindAPVO vo) {
        vo.setAp(false);
        deviceDateInfoService.updateAP(vo);
        return ResultVO.ok();
    }


    @GetMapping("/hisIpList/{sn}")
    @ApiOperation(value = "设备历史ip列表")
    public ResultVO<List<String>> hisIpList(@ApiParam(required = true, value = "设备sn号") @PathVariable String sn){
        return ResultVO.ok(commandService.hisIpList(sn));
    }

    @GetMapping("/clearData")
    @ApiOperation(value = "清除数据")
    public ResultVO clearData(){
        deviceDateInfoService.resetTables();
        return ResultVO.ok();
    }


    @PostMapping("/printReportCommand")
    @ApiOperation(value = "打印上报命令")
    public ResultVO<?> printReportCommand(@ApiParam(required = true, value = "打印上报命令请求DTO") @RequestBody PrintReportCommandDTO printReportCommandDTO){
        List<String> snList = printReportCommandDTO.getSnList();
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime expireTime = localDateTime.plusMinutes(1);

        Integer port = printReportCommandDTO.getPort();
        for(String sn : snList){
            PrintReportCommandDeviceMap.put(port, sn,expireTime);
        }

        return ResultVO.ok();
    }


    @PostMapping("/wifiDefaultConfig")
    @ApiOperation(value = "获取默认wifi配置")
    public ResultVO<WifiConfigData> wifiDefaultConfig(){
        return ResultVO.ok(commandService.getDefaultWifiConfig());
    }

    @PostMapping("/getWifiConfig/{sn}")
    @ApiOperation(value = "读取wifi配置")
    public ResultVO<WifiConfigData> getWifiConfig(@ApiParam(required = true, value = "读取wifi配置请求DTO") @RequestBody GetWifiConfigDTO getWifiConfigDTO){
        ConcurrentMap<String, LedData> concurrentMap = LedPortMap.get(getWifiConfigDTO.getPort());
        WifiConfigData wifiConfigData = null;
        if(concurrentMap!=null){
            LedData ledData = concurrentMap.get(getWifiConfigDTO.getSn());
            if(ledData!=null){
                wifiConfigData = ledData.getWifiConfigData();
            }
        }
        List<SendCommandVO> firmwareVersion = SendCommandHelper.getWifiConfig(getWifiConfigDTO.getSn());
        log.debug("读取wifi配置命令结果:{}",JSON.toJSONString(firmwareVersion.get(0)));
        return ResultVO.ok(wifiConfigData);
    }

    @PostMapping("/getFirmwareVersion")
    @ApiOperation(value = "获取固件版本")
    public ResultVO<String> getFirmwareVersion(@ApiParam(required = true, value = "获取固件版本请求DTO") @RequestBody GetFirmwareVersionDTO firmwareVersionDTO){
        ConcurrentMap<String, LedData> concurrentMap = LedPortMap.get(firmwareVersionDTO.getPort());
        String version = null;
        if(concurrentMap!=null){
            LedData ledData = concurrentMap.get(firmwareVersionDTO.getSn());
            if(ledData!=null){
                version = ledData.getVersion();
            }
        }
        List<SendCommandVO> firmwareVersion = SendCommandHelper.getFirmwareVersion(firmwareVersionDTO.getSn());
        log.debug("获取固件版本命令结果:{}",JSON.toJSONString(firmwareVersion.get(0)));
        return ResultVO.ok(version);
    }

    @PostMapping("/firmware/stop")
    @ApiOperation("停止固件升级")
    public ResultVO stopUpgrade(@RequestBody List<String> sns){
        firmwareUpdateService.stopUpgrade(sns);
        return ResultVO.ok();
    }

}
