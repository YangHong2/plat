package com.dhlk.subcontract.controller;


import com.dhlk.entity.sub.OrderDetails;
import com.dhlk.subcontract.service.OrderDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 订单详情(OrderDetails)表控制层
 *
 * @author xkliu
 * @since 2021-03-12 09:21:14
 */
@RestController
@RequestMapping("orderDetails")
public class OrderDetailsController {
    /**
     * 服务对象
     */
    @Resource
    private OrderDetailsService orderDetailsService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public OrderDetails selectOne(Integer id) {
        return this.orderDetailsService.queryById(id);
    }

}
