package com.dhlk.subcontract.controller;


import com.dhlk.entity.sub.InvoiceInfo;
import com.dhlk.subcontract.service.InvoiceInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 发票地址信息(InvoiceInfo)表控制层
 *
 * @author xkliu
 * @since 2021-03-12 09:21:11
 */
@RestController
@RequestMapping("invoiceInfo")
public class InvoiceInfoController {
    /**
     * 服务对象
     */
    @Resource
    private InvoiceInfoService invoiceInfoService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public InvoiceInfo selectOne(Integer id) {
        return this.invoiceInfoService.queryById(id);
    }

}
