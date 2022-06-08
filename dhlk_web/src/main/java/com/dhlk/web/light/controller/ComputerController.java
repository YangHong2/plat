package com.dhlk.web.light.controller;

import com.alibaba.fastjson.JSON;
import com.dhlk.domain.Result;
import com.dhlk.entity.light.Computer;
import com.dhlk.web.light.service.ComputerService;
import com.dhlk.web.proxy.service.ProxyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description 一体机管理
 * @Author gchen
 * @Date 2020/6/4
 */
@RestController
@RequestMapping("/computer")
@Api(tags ="一体机管理", value = "computer")
@Slf4j
public class ComputerController {
    @Autowired
    private ComputerService computerService;
    @Autowired
    private ProxyService proxyService;
    /**
    * 保存/修改
    * @author      gchen
    * @param computer
    * @return
    * @date        2020/6/4 15:50
    */
    @PostMapping("/save")
    @ApiOperation("新增/修改")
    public Result save(@RequestBody Computer computer){
        return computerService.save(computer);
    }

    /**
     * 批量删除
     * @author gchen
     * @param ids
     * @return
     * @date        2020/6/4 15:50
     */
    @GetMapping("/delete")
    @ApiOperation("删除")
    public Result delete(@RequestParam("ids") String ids){
        return computerService.delete(ids);
    }

    /**
     * 列表查询
     * @author      gchen
     * @param name pageNum pageSize
     * @return
     * @date        2020/6/4 15:50
     */
    @GetMapping("/findList")
    @ApiOperation("列表查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name",value = "一体机名称",dataType = "String"),
            @ApiImplicitParam(name = "pageNum",value = "页码",dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize",value = "页大小",dataType = "Integer"),
    })
    public Result findList(@RequestParam(value = "name",required = false) String name,
                                               @RequestParam(value = "pageNum",required = false) Integer pageNum,
                                               @RequestParam(value = "pageSize",required = false) Integer pageSize){
        return computerService.findList(name,pageNum,pageSize);
    }

    /**
     * 添加代理
     * @author      gchen
     */
    @GetMapping("/addReseller")
    @ApiOperation("添加代理")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deviceId",value = "一体机ID",dataType = "String"),
            @ApiImplicitParam(name = "mac",value = "mac地址",dataType = "String")
    })
    public Result addReseller(@RequestParam("deviceId") String deviceId,
                              @RequestParam("mac") String mac){
        log.info("开始添加反向代理...");
        Result add = proxyService.add(deviceId);
        log.info("代理服务添加成功...");
        if(add.getCode() == 0){
            return computerService.addReseller(JSON.toJSONString(add.getData()),mac);
        }
        return add;
    }

    /**
     * 代理跳转
     * @author      gchen
     */
    @GetMapping("/aquireResellerUrl")
    @ApiOperation("代理跳转")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deviceId",value = "一体机ID",dataType = "String"),
            @ApiImplicitParam(name = "mac",value = "mac地址",dataType = "String")
    })
    public Result aquireResellerUrl(@RequestParam("deviceId") String deviceId,
                                    @RequestParam("mac") String mac){
        Result add = proxyService.add(deviceId);
        if(add.getCode() == 0){
            Result result = computerService.addReseller(JSON.toJSONString(add.getData()), mac);
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
