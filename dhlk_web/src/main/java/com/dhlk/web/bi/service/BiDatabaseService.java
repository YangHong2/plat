package com.dhlk.web.bi.service;

import com.dhlk.domain.Result;
import com.dhlk.entity.bi.BiDatabase;
import com.dhlk.web.bi.service.fbk.BiDatabaseServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @des: 数据库连接Feign接口
 * @author: xkliu
 * @date: 2021/02/22
 */
@FeignClient(value = "bi-tools/biDatabase", fallback = BiDatabaseServiceFbk.class)
public interface BiDatabaseService {

    /**
     * 保存/修改
     *
     * @param biDatabase
     * @return
     */
    @PostMapping(value = "/save")
    Result save(@RequestBody BiDatabase biDatabase);


    /**
     * 列表查询
     *
     * @return
     */
    @GetMapping("/findList")
    Result findList();

    /**
     * 删除
     *
     * @param ids
     * @return result
     */
    @GetMapping(value = "/delete")
    Result delete(@RequestParam(value = "ids") String ids);


    /**
     * 测试连接
     *
     * @param biDatabase
     * @return
     */
    @PostMapping(value = "/testConnection")
    Result testConnection(@RequestBody BiDatabase biDatabase);


    /**
     * 获取数据表名
     *
     * @param biDatabase
     * @return
     */
    @PostMapping(value = "/getTableNames")
    Result getTableNames(@RequestBody BiDatabase biDatabase);

    /**
     * 获取表字段
     *
     * @param biDatabase
     * @return
     */
    @PostMapping(value = "/getTableColumns")
    Result getTableColumns(@RequestBody BiDatabase biDatabase);
}
