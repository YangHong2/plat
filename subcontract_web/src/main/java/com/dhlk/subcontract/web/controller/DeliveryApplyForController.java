package com.dhlk.subcontract.web.controller;


import com.dhlk.domain.Result;
import com.dhlk.entity.sub.DeliveryApplyFor;
import com.dhlk.subcontract.web.service.DeliveryApplyForService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(description = "交付申请", value = "DeliveryApplyForController")
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
    @ApiOperation("新增/修改")
    public Result save(@RequestBody DeliveryApplyFor deliveryApplyFor) {
        return deliveryApplyForService.save(deliveryApplyFor);
    }

}
