package com.dhlk.web.basicmodule.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dhlk.entity.basicmodule.NetDevices;
import com.dhlk.systemconst.Const;
import com.dhlk.web.basicmodule.service.NetDevicesService;
import com.dhlk.domain.Result;
import com.dhlk.web.mqtt.service.MqttSendService;
import com.dhlk.web.proxy.service.ProxyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
* 网络设备管理
*/
@RestController
@RequestMapping(value = "/netDevices")
@Api(description = "网络设备管理")
public class NetDevicesController {
    @Autowired
    private NetDevicesService netDevicesService;
    @Autowired
    private ProxyService proxyService;
    @Autowired
    private MqttSendService mqttSendService;

    /**
     * 保存
     * @param
     * @return
     */
    @ApiOperation("新增/修改")
    @PostMapping(value = "/save")
    public Result save(@RequestBody NetDevices netDevices) throws Exception{

        return netDevicesService.save(netDevices);
    }
    /**
     * 删除
     * @param ids
     * @return result
     */
    @ApiOperation("删除")
    @GetMapping(value = "/delete")
    public Result delete(@RequestParam(value = "ids") String ids) throws Exception {
        return netDevicesService.delete(ids);
    }

    /**
    * 列表查询
    * @return
    */
    @ApiOperation("列表查询")
    @GetMapping("/findList")
    public Result findList(@RequestParam(value = "name", required = false) String name) {
        return  netDevicesService.findList(name);
    }
    @ApiOperation("网络设备关联的生产设备查询")
    @GetMapping("/findPruductDevicesList")
    public Result findPruductDevicesList(@RequestParam(value = "netDevicesId", required = true) Integer netDevicesId) {
        return  netDevicesService.findPruductDevicesList(netDevicesId);
    }
    /**
     * 状态修改启用禁用
     */
    @PostMapping(value = "/isEnable")
    public Result isEnable(@RequestParam(value = "id", required = true) Integer id,
                       @RequestParam(value = "status", required = true) Integer status) {
        return netDevicesService.isEnable(id,status);
    }
    @PostMapping("/findOnLineNetDevices")
    public Result findOnLineNetDevices(@RequestBody List<JSONObject>  jsonParam){
        return  netDevicesService.findOnLineNetDevices(jsonParam);
    }

    /**
     * 添加代理
     * @author      gchen
     */
    @GetMapping("/addReseller")
    public Result addReseller(@RequestParam("deviceId") String deviceId,
                              @RequestParam("tenantId") String tenantId,
                              @RequestParam("mac") String mac){
        Result add = proxyService.add(deviceId);
        if(add.getCode() == 0){
            Result addReseller = netDevicesService.addReseller(JSON.toJSONString(add.getData()), tenantId, mac);
            if(addReseller.getCode()!=0) return addReseller;
            return mqttSendService.sendMessage(Const.TOPIC_CLOUDTOLOCAL_PROXY_DATA,JSON.toJSONString(addReseller.getData()));
        }

        add.setCode(1000);
        add.setMsg(add.getData()+"");
        return add;
    }

    /**
     * 代理跳转
     * @author      gchen
     */
    @GetMapping("/aquireResellerUrl")
    public Result aquireResellerUrl(@RequestParam("deviceId") String deviceId,
                                    @RequestParam("tenantId") String tenantId,
                                    @RequestParam("mac") String mac){
        Result add = proxyService.add(deviceId);
        if(add.getCode() == 0){
            Result result = netDevicesService.addReseller(JSON.toJSONString(add.getData()), tenantId, mac);
            if(result.getCode()==0){
                return proxyService.requestIndexUrl(deviceId);
            }
            return result;
        }
        add.setCode(1000);
        add.setMsg(add.getData()+"");
        return add;
    }
}
