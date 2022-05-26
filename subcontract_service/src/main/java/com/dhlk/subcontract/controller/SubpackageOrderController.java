package com.dhlk.subcontract.controller;


import com.dhlk.entity.sub.SubpackageOrder;
import com.dhlk.subcontract.service.SubpackageOrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 订单(SubpackageOrder)表控制层
 *
 * @author xkliu
 * @since 2021-03-12 09:24:23
 */
@RestController
@RequestMapping("subpackageOrder")
public class SubpackageOrderController {
    /**
     * 服务对象
     */
    @Resource
    private SubpackageOrderService subpackageOrderService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public SubpackageOrder selectOne(Integer id) {
        return this.subpackageOrderService.queryById(id);
    }

}
