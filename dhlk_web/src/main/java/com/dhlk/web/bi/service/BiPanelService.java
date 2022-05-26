package com.dhlk.web.bi.service;

import com.dhlk.domain.Result;
import com.dhlk.entity.bi.BiPanel;
import com.dhlk.entity.bi.ChartParams;
import com.dhlk.web.bi.service.fbk.BiPanelServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @des: 报表面板Feign接口
 * @author: xkliu
 * @date: 2021/02/22
 */
@FeignClient(value = "bi-tools/biPanel", fallback = BiPanelServiceFbk.class)
public interface BiPanelService {

    /**
     * 保存/修改
     *
     * @param biPanel
     * @return
     */
    @PostMapping(value = "/save")
    Result save(@RequestBody BiPanel biPanel);

    /**
     * 删除
     *
     * @param ids
     * @return result
     */
    @GetMapping(value = "/delete")
    Result delete(@RequestParam(value = "ids") String ids);

    /**
     * 列表查询
     */
    @GetMapping(value = "/findList")
    Result findList(@RequestParam(value = "name", required = false) String name,
                    @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                    @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize);

    /**
     * 获取BiPanel
     */
    @GetMapping(value = "/findByBiPanel")
    Result findByBiPanel(@RequestParam(value = "id") Integer id);

    /**
     * 获取图表数据
     */
    @PostMapping(value = "/findChartData")
    Result findChartData(@RequestBody ChartParams chartParams);
}
