package com.dhlk.web.bi.controller;

import com.dhlk.domain.Result;
import com.dhlk.entity.bi.BiPanel;
import com.dhlk.entity.bi.ChartParams;
import com.dhlk.web.bi.service.BiPanelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @des: 报表面板控制器
 * @author: xkliu
 * @date: 2021/02/22
 */
@RestController
@RequestMapping(value = "/biPanel")
@Api(value = "BiPanelController", description = "报表面板控制器")
public class BiPanelController {

    @Autowired
    private BiPanelService biPanelService;

    /**
     * 保存/修改
     *
     * @param biPanel
     * @return
     */
    @PostMapping(value = "/save")
    @ApiOperation("保存/修改")
    public Result save(@RequestBody BiPanel biPanel) {
        return biPanelService.save(biPanel);
    }

    /**
     * 删除
     *
     * @param ids
     * @return result
     */
    @GetMapping(value = "/delete")
    @ApiOperation("删除")
    public Result delete(@RequestParam(value = "ids") String ids) {
        return biPanelService.delete(ids);
    }

    /**
     * 列表查询
     */
    @GetMapping(value = "/findList")
    @ApiOperation("列表查询")
    public Result findList(@RequestParam(value = "name", required = false) String name,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return biPanelService.findList(name, pageNum, pageSize);
    }

    /**
     * 获取BiPanel
     */
    @GetMapping(value = "/findByBiPanel")
    @ApiOperation("获取BiPanel")
    public Result findByBiPanel(@RequestParam(value = "id") Integer id) {
        return biPanelService.findByBiPanel(id);
    }
    /**
     * 获取图表数据
     */
    @PostMapping(value = "/findChartData")
    @ApiOperation("获取图表数据")
    public Result findChartData(@RequestBody ChartParams chartParams) {
        return biPanelService.findChartData(chartParams);
    }

}
