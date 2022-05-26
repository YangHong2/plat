package com.dhlk.bi.controller;

import com.dhlk.bi.service.BiPanelService;
import com.dhlk.domain.Result;
import com.dhlk.entity.bi.BiPanel;
import com.dhlk.entity.bi.ChartParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @des: 报表面板控制器
 * @author: xkliu
 * @date: 2021/02/22
 */
@RestController
@RequestMapping(value = "/biPanel")
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
    public Result delete(@RequestParam(value = "ids") String ids) {
        return biPanelService.delete(ids);
    }

    /**
     * 获取图表数据
     */
    @PostMapping(value = "/findChartData")
    public Result findChartData(@RequestBody ChartParams chartParams) {
        return biPanelService.findChartData(chartParams);
    }

    /**
     * 列表查询
     */
    @GetMapping(value = "/findList")
    public Result findList(@RequestParam(value = "name", required = false) String name,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return biPanelService.findList(name, pageNum, pageSize);
    }

    /**
     * 获取BiPanel
     */
    @GetMapping(value = "/findByBiPanel")
    public Result findByBiPanel(@RequestParam(value = "id") Integer id) {
        return biPanelService.findByBiPanel(id);
    }

}
