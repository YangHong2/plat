package com.dhlk.subcontract.controller;


import com.dhlk.entity.sub.SubpackageMessage;
import com.dhlk.subcontract.service.SubpackageMessageService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 消息(SubpackageMessage)表控制层
 *
 * @author xkliu
 * @since 2021-03-12 09:24:13
 */
@RestController
@RequestMapping("subpackageMessage")
public class SubpackageMessageController {
    /**
     * 服务对象
     */
    @Resource
    private SubpackageMessageService subpackageMessageService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public SubpackageMessage selectOne(Integer id) {
        return this.subpackageMessageService.queryById(id);
    }

}
