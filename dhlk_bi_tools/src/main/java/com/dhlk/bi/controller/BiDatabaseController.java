package com.dhlk.bi.controller;

import com.dhlk.bi.service.BiDatabaseService;
import com.dhlk.domain.Result;
import com.dhlk.entity.bi.BiDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @des: 数据库连接控制器
 * @author: xkliu
 * @date: 2021/02/22
 */
@RestController
@RequestMapping(value = "/biDatabase")
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
    @CrossOrigin
    public Result save(@RequestBody BiDatabase biDatabase) {
        return biDatabaseService.save(biDatabase);
    }


    /**
     * 列表查询
     *
     * @return
     */
    @GetMapping("/findList")
    @CrossOrigin
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
    public Result testConnection(@RequestBody BiDatabase biDatabase) {
        return biDatabaseService.testConnection(biDatabase);
    }

    /**
     * 获取数据库名称
     *
     * @param biDatabase
     * @return
     */
    @PostMapping(value = "/getSchema")
    @CrossOrigin
    public Result getSchema(@RequestBody BiDatabase biDatabase) {
        return biDatabaseService.getSchema(biDatabase);
    }

    /**
     * 获取数据表名
     *
     * @param biDatabase
     * @return
     */
    @PostMapping(value = "/getTableNames")
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
    public Result getTableColumns(@RequestBody BiDatabase biDatabase) {
        return biDatabaseService.getTableColumns(biDatabase);
    }


    /**
     * 创建sql
     *
     * @param biDatabase
     * @return
     */
    @PostMapping(value = "/createSql")
    public Result createSql(@RequestBody BiDatabase biDatabase) {
        return biDatabaseService.createSql(biDatabase);
    }

}
