package com.dhlk.subcontract.controller;


import com.dhlk.entity.sub.UserCoupons;
import com.dhlk.subcontract.service.UserCouponsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户优惠券(UserCoupons)表控制层
 *
 * @author xkliu
 * @since 2021-03-12 09:29:07
 */
@RestController
@RequestMapping("userCoupons")
public class UserCouponsController {
    /**
     * 服务对象
     */
    @Resource
    private UserCouponsService userCouponsService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public UserCoupons selectOne(Integer id) {
        return this.userCouponsService.queryById(id);
    }

}
