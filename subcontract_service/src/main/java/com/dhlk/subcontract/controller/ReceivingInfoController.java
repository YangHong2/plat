package com.dhlk.subcontract.controller;


import com.dhlk.domain.Result;
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
@RequestMapping("/receivingInfo")
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
    public Result selectOne(@RequestParam(value = "id")Integer id) {
        return this.receivingInfoService.queryById(id);
    }

    /**
     * 通过项目ID查询数据
     * @param id
     * @return
     */
    @GetMapping("selectByProjectId")
    public Result selectByProjectId(@RequestParam(value = "id")Integer id) {
        return this.receivingInfoService.selectByProjectId(id);
    }

    /**
     * 保存付款信息
     * @param receivingInfo
     * @return
     */
    @PostMapping("save")
    public Result saveReceivingInfo(@RequestBody ReceivingInfo receivingInfo) {
        return this.receivingInfoService.insert(receivingInfo);
    }

    /**
     * 修改付款信息
     * @param receivingInfo
     * @return
     */
    @PostMapping("updataByid")
    public Result updataByid(@RequestBody ReceivingInfo receivingInfo) {
        return this.receivingInfoService.update(receivingInfo);
    }
}
