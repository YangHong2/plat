package com.dhlk.subcontract.controller;


import com.dhlk.entity.sub.Invoice;
import com.dhlk.subcontract.service.InvoiceService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 发票管理(Invoice)表控制层
 *
 * @author xkliu
 * @since 2021-03-12 09:21:10
 */
@RestController
@RequestMapping("invoice")
public class InvoiceController {
    /**
     * 服务对象
     */
    @Resource
    private InvoiceService invoiceService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Invoice selectOne(Integer id) {
        return this.invoiceService.queryById(id);
    }

}
