package com.dhlk.subcontract.web.service;

import com.dhlk.domain.Result;
import com.dhlk.entity.sub.DeliveryApplyFor;
import com.dhlk.subcontract.web.service.fbk.DeliveryApplyForServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @des:
 * @author: xkliu
 * @date: 2021/03/16
 */
@FeignClient(value = "subcontract-service/deliveryApplyFor", fallback = DeliveryApplyForServiceFbk.class)
public interface DeliveryApplyForService {


    /**
     * 新增/修改
     *
     * @param deliveryApplyFor
     * @return
     */
    @PostMapping(value = "/save")
    Result save(@RequestBody DeliveryApplyFor deliveryApplyFor);
}
