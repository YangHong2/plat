package com.dhlk.subcontract.controller;


import com.dhlk.domain.Result;
import com.dhlk.entity.sub.DeliveryApplyFor;
import com.dhlk.subcontract.service.DeliveryApplyForService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 交付申请(DeliveryApplyFor)表控制层
 *
 * @author xkliu
 * @since 2021-03-12 09:21:03
 */
@RestController
@RequestMapping("/deliveryApplyFor")
public class DeliveryApplyForController {
    /**
     * 服务对象
     */
    @Autowired
    private DeliveryApplyForService deliveryApplyForService;

    /**
     * 新增/修改
     * @param deliveryApplyFor
     * @return
     */
    @PostMapping(value = "/save")
    public Result save(@RequestBody DeliveryApplyFor deliveryApplyFor) {
        return deliveryApplyForService.save(deliveryApplyFor);
    }

}
