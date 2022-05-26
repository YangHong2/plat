package com.dhlk.subcontract.web.service;

import com.dhlk.domain.Result;
import com.dhlk.entity.sub.Advert;
import com.dhlk.entity.sub.Coupons;
import com.dhlk.subcontract.web.service.fbk.AdvertFbk;
import com.dhlk.subcontract.web.service.fbk.CouponsServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created with IntelliJ IDEA
 *
 * @Auther :yangwang
 * Data:2021/3/15
 * Time:10:50
 * @Description:
 */
@FeignClient(value = "subcontract-service/coupons", fallback = CouponsServiceFbk.class)
public interface CouponsService {

    /**
     * 列表查询
     * @param couponNo 优惠卷编号
     * @return 单条数据
     */
    @GetMapping("findList")
    Result findList(@RequestParam(value = "couponNo",required = false) String couponNo,
                   @RequestParam(value = "createStartTime",required = false) String createStartTime,
                   @RequestParam(value = "createEndTime",required = false) String createEndTime,
                   @RequestParam(value = "expireStartTime",required = false) String expireStartTime,
                   @RequestParam(value = "expireEndTime",required = false) String expireEndTime,
                   @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                   @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize);

    /**
     * 新增/修改
     * @param coupons 优惠卷对象
     * @return 单条数据
     */
    @PostMapping("save")
    Result save(@RequestBody Coupons coupons);
}
