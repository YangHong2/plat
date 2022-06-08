package com.dhlk.web.light.controller;

import com.dhlk.domain.Result;
import com.dhlk.entity.light.IntelligentSwitch;
import com.dhlk.web.light.service.IntelligentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description 智能开关 与 智能开关和分组关联
 */
@RestController
@RequestMapping("/intelligent")
@Api(tags = "智能开关")
public class IntelligentSwitchController {
    @Autowired
    private IntelligentService intelligentService;

    /**
     * 新增智能开关数据
     * @param intelligentSwitch
     */
    @ApiOperation("智能开关插入")
    @PostMapping("/insert")
    public Result insert(@RequestBody IntelligentSwitch intelligentSwitch){
        return intelligentService.save(intelligentSwitch);
    }

    /**
     * 新增智能开关数据
     * @param list
     */
    @ApiOperation("智能开关批量插入")
    @PostMapping("/insertBatch")
    public Result insertBatch(@RequestBody List<IntelligentSwitch> list){
        return intelligentService.saveBatch(list);
    }

    /**
     * 根据区域id,租户id查询智能开关
     * @param areaId
     */
    @ApiOperation("根据区域id,租户id查询智能开关")
    @GetMapping("/findListByAreaId")
    public Result findListByAreaId(@RequestParam String areaId){
        return intelligentService.findListByAreaId(areaId);
    }

    /**
     * 根据租户id查询智能开关
     * @param tenantId
     */
    @ApiOperation("根据租户id查询智能开关")
    @GetMapping("/findListByTenantId")
    public Result findListByTenantId(@RequestParam String tenantId){
        return intelligentService.findListByTenantId(tenantId);
    }

    /**
     * 根据sn查询智能开关配置信息
     * @param sn
     */
    @ApiOperation("根据sn查询智能开关配置信息")
    @GetMapping("/findIntelligentBySn")
    public Result findIntelligentBySn(@RequestParam String sn){
        return intelligentService.findIntelligentBySn(sn);
    }

    /**
     * 根据区域id删除智能开关
     * @param sn
     */
    @ApiOperation("根据SN删除智能开关")
    @GetMapping("/deleteIntelligent")
    public Result deleteIntelligent(@RequestParam String sn){
        return intelligentService.deleteIntelligent(sn);
    }


    /**
     * 根据智能开关sn查询按键信息
     * @param sn
     */
    @ApiOperation("根据智能开关sn查询按键信息")
    @GetMapping ("/findButtonBySn")
    public Result findButtonBySn(@RequestParam String sn){
        return intelligentService.findButtonBySn(sn);
    }



}
