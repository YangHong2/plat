package com.dhlk.web.bi.controller;

import com.dhlk.domain.Result;
import com.dhlk.entity.bi.BiDatabase;
import com.dhlk.web.bi.service.BiDatabaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @des: 数据库连接控制器
 * @author: xkliu
 * @date: 2021/02/22
 */
@RestController
@RequestMapping(value = "/biDatabase")
@Api(value = "BiDatabaseController", description = "数据库连接控制器")
public class BiDatabaseController {

    @Autowired
    private BiDatabaseService biDatabaseService;

    /**
     * 保存/修改
     *
     * @param biDatabase
     * @return
     */
    @PostMapping(value = "/save")
    @ApiOperation("保存/修改")
    public Result save(@RequestBody BiDatabase biDatabase) {
        return biDatabaseService.save(biDatabase);
    }


    /**
     * 列表查询
     *
     * @return
     */
    @GetMapping("/findList")
    @ApiOperation("列表查询")
    public Result findList() {
        return biDatabaseService.findList();
    }


    /**
     * 删除
     *
     * @param ids
     * @return result
     */
    @GetMapping(value = "/delete")
    @ApiOperation("删除")
    public Result delete(@RequestParam(value = "ids") String ids) {
        return biDatabaseService.delete(ids);
    }


    /**
     * 测试连接
     *
     * @param biDatabase
     * @return
     */
    @PostMapping(value = "/testConnection")
    @ApiOperation("测试连接")
    public Result testConnection(@RequestBody BiDatabase biDatabase) {
        return biDatabaseService.testConnection(biDatabase);
    }


    /**
     * 获取数据表名
     *
     * @param biDatabase
     * @return
     */
    @PostMapping(value = "/getTableNames")
    @ApiOperation("获取数据表名")
    public Result getTableNames(@RequestBody BiDatabase biDatabase) {
        return biDatabaseService.getTableNames(biDatabase);
    }

    /**
     * 获取表字段
     *
     * @param biDatabase
     * @return
     */
    @PostMapping(value = "/getTableColumns")
    @ApiOperation("获取表字段")
    public Result getTableColumns(@RequestBody BiDatabase biDatabase) {
        return biDatabaseService.getTableColumns(biDatabase);
    }

}
