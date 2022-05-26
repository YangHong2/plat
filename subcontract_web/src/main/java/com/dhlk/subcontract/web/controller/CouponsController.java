package com.dhlk.subcontract.web.controller;


import com.dhlk.domain.Result;
import com.dhlk.entity.sub.Coupons;
import com.dhlk.subcontract.web.service.CouponsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 优惠卷(Coupons)表控制层
 *
 * @author xkliu
 * @since 2021-03-12 09:21:01
 */
@RestController
@RequestMapping("coupons")
@Api(value = "couponsController",description = "优惠卷")
public class CouponsController {
    /**
     * 服务对象
     */
    @Resource
    private CouponsService couponsService;
    /**
     * 列表查询
     * @param couponNo 优惠卷编号
     * @return 单条数据
     */
    @GetMapping("findList")
    @ApiOperation("列表查询")
    public Result findList(@RequestParam(value = "couponNo",required = false) String couponNo,
                           @RequestParam(value = "createStartTime",required = false) String createStartTime,
                           @RequestParam(value = "createEndTime",required = false) String createEndTime,
                           @RequestParam(value = "expireStartTime",required = false) String expireStartTime,
                           @RequestParam(value = "expireEndTime",required = false) String expireEndTime,
                           @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize) {
        return this.couponsService.findList(couponNo,createStartTime,createEndTime,expireStartTime,expireEndTime,pageNum,pageSize);
    }

    /**
     * 新增/修改
     * @param coupons 优惠卷对象
     * @return 单条数据
     */
    @PostMapping("save")
    @ApiOperation("新增/修改")
    public Result save(@RequestBody Coupons coupons) {
        return this.couponsService.save(coupons);
    }

}
