package com.dhlk.subcontract.controller;


import com.dhlk.domain.Result;
import com.dhlk.entity.sub.Coupons;
import com.dhlk.subcontract.service.CouponsService;
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
public class CouponsController {
    /**
     * 服务对象
     */
    @Resource
    private CouponsService couponsService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Coupons selectOne(Integer id) {
        return this.couponsService.queryById(id);
    }

    /**
     * 列表查询
     * @param couponNo 优惠卷编号
     * @return 单条数据
     */
    @GetMapping("findList")
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
    public Result save(@RequestBody Coupons coupons) {
        return this.couponsService.save(coupons);
    }

}
