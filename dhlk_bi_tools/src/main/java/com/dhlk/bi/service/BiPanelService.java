package com.dhlk.bi.service;

import com.dhlk.domain.Result;
import com.dhlk.entity.bi.BiPanel;
import com.dhlk.entity.bi.ChartParams;

/**
 * @des: 报表面板 service
 * @author: xkliu
 * @date: 2021/02/22
 */
public interface BiPanelService {

    /**
     * 新增修改
     *
     * @param biPanel
     * @return
     */
    Result save(BiPanel biPanel);

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    Result delete(String ids);

    /**
     * 获取图标的数据
     */
    Result findChartData(ChartParams chartParams);

    /**
     * 列表查询
     *
     * @return
     */
    Result findList(String name, Integer pageNum, Integer pageSize);

    /**
     * 获取BiPanel
     *
     * @param id
     * @return
     */
    Result findByBiPanel(Integer id);
}
