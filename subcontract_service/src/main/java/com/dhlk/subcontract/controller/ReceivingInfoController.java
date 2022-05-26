package com.dhlk.subcontract.controller;


import com.dhlk.entity.sub.ReceivingInfo;
import com.dhlk.subcontract.service.ReceivingInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 收付款信息(ReceivingInfo)表控制层
 *
 * @author xkliu
 * @since 2021-03-12 09:23:30
 */
@RestController
@RequestMapping("receivingInfo")
public class ReceivingInfoController {
    /**
     * 服务对象
     */
    @Resource
    private ReceivingInfoService receivingInfoService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public ReceivingInfo selectOne(Integer id) {
        return this.receivingInfoService.queryById(id);
    }

}
