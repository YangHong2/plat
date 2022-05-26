package com.dhlk.web.bi.service.fbk;

import com.dhlk.domain.Result;
import com.dhlk.entity.bi.BiPanel;
import com.dhlk.entity.bi.ChartParams;
import com.dhlk.enums.ResultEnum;
import com.dhlk.utils.ResultUtils;
import com.dhlk.web.bi.service.BiPanelService;
import org.springframework.stereotype.Service;

/**
 * @des: 报表面板Feign接口调用失败
 * @author: xkliu
 * @date: 2021/02/22
 */
@Service
public class BiPanelServiceFbk implements BiPanelService {

    @Override
    public Result save(BiPanel biPanel) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result delete(String ids) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findChartData(ChartParams chartParams) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findList(String name, Integer pageNum, Integer pageSize) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }

    @Override
    public Result findByBiPanel(Integer id) {
        return ResultUtils.error(ResultEnum.NETWORK_ERR);
    }


}
