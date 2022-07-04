package com.dhlk.subcontract.web.controller;


import com.dhlk.domain.Result;
import com.dhlk.entity.sub.ReceivingInfo;
import com.dhlk.subcontract.web.service.ReceivingInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(description = "首付款信息", value = "ReceivingInfoController")
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
    @ApiOperation("通过主键查询单条数据")
    public Result selectOne(@RequestParam(value = "id") Integer id) {
        return this.receivingInfoService.queryById(id);
    }

    /**
     * 通过项目ID查询单条数据
     * @param id
     * @return
     */
    @GetMapping("selectByProjectId")
    @ApiOperation("通过项目ID查询单条数据")
    public Result selectByProjectId(@RequestParam(value = "id")Integer id) {
        return this.receivingInfoService.selectByProjectId(id);
    }

    /**
     * 保存付款信息
     * @param receivingInfo
     * @return
     */
    @PostMapping("save")
    @ApiOperation("保存付款信息")
    public Result saveReceivingInfo(@RequestBody ReceivingInfo receivingInfo) {
        return this.receivingInfoService.insert(receivingInfo);
    }

    /**
     * 修改付款信息
     * @param receivingInfo
     * @return
     */
    @PostMapping("updataByid")
    @ApiOperation("修改付款信息")
    public Result updataByid(@RequestBody ReceivingInfo receivingInfo) {
        return this.receivingInfoService.update(receivingInfo);
    }
}
