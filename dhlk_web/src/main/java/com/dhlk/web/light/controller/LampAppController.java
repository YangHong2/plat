package com.dhlk.web.light.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dhlk.domain.Result;
import com.dhlk.entity.light.AppOverview;
import com.dhlk.entity.vo.ChartBarGraphVO;
import com.dhlk.entity.vo.ChartPieVO;
import com.dhlk.enums.GranEnums;
import com.dhlk.utils.ResultUtils;
import com.dhlk.web.light.service.GraphicStatisticsService;
import com.dhlk.web.light.service.TaskSchedulerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.spring.web.json.Json;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Map;

/**
 * @author xmdeng
 * @date 2021/5/24 13:41
 */
@RestController
@RequestMapping(value = "/app/lamp")
@Api(tags = "智慧照明灯控App接口")
@Slf4j
public class LampAppController {

    @Resource
    GraphicStatisticsService graphicStatisticsService;

    @Resource
    TaskSchedulerService taskSchedulerService;

    @GetMapping("/overview")
    @ApiOperation("获取app指标总览信息")
    public Result<AppOverview> getAppOverview(){

        AppOverview appOverview = new AppOverview();
        try {
            JSONObject onlineJson = objectToJson(graphicStatisticsService.getLightTotalAndOnlineCount());
            appOverview.setLightTotal(onlineJson.getInteger("lightTotal"));
            appOverview.setLightOnLineCount(onlineJson.getInteger("lightOnLineCount"));
        }catch (Exception e){
            log.error("获取灯总数和开灯数失败，失败原因：{}",e);
        }
        try {
            appOverview.setTodayEnergy(objectToJson(graphicStatisticsService.getTodayEnergyAndYesdayEnergy()).getBigDecimal("todayEnergy").setScale(2, BigDecimal.ROUND_HALF_UP));
        }catch (Exception e){
            log.error("获取能耗总数失败，失败原因：{}",e);
        }

        try {
            appOverview.setEnergyConservation(objectToJson(graphicStatisticsService.getEnergyConservation()).getBigDecimal("energyConservation").setScale(2, BigDecimal.ROUND_HALF_UP));
        }catch (Exception e){
            log.error("获取累计节能数失败，失败原因：{}",e);
        }

        try {
            appOverview.setSceneNumber(objectToJson(taskSchedulerService.selectTaskSchedulerList("", 1, 10)).getInteger("total"));
        }catch (Exception e){
            log.error("获取情景数失败，失败原因：{}",e);
        }

        return ResultUtils.success(appOverview);
    }


    private JSONObject objectToJson(Result result){
        return JSON.parseObject(JSONObject.toJSONString(result.getData()));
    }

    @ApiOperation("APP能耗趋势分析(柱图)")
    @GetMapping("/trendChart")
    @ApiImplicitParam(name = "gran",value = "粒度;月=Month,日=Day,小时=Hour",dataType = "String")
    public Result<ChartBarGraphVO> getTrendChart(@RequestParam("gran") String gran){
        return graphicStatisticsService.getTrendChart(gran);
    }

    @ApiOperation("APP趋势统计分析（饼图）")
    @GetMapping("/getEnergyStatistics")
    @ApiImplicitParam(name = "date",value = "YYYY-MM-DD",dataType = "String")
    public Result<ChartPieVO> getEnergyStatistics(@RequestParam("date") String date){
        return graphicStatisticsService.getEnergyStatistics(date);
    }

}
