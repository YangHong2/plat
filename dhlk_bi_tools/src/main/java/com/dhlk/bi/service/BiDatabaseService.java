package com.dhlk.bi.service;

import com.dhlk.domain.Result;
import com.dhlk.entity.bi.BiDatabase;

/**
 * @des: 数据库连接 service
 * @author: xkliu
 * @date: 2021/02/22
 */
public interface BiDatabaseService {

    /**
     * 新增/修改
     *
     * @param biDatabase
     * @return
     */
    Result save(BiDatabase biDatabase);

    /**
     * 列表查询
     *
     * @return
     */
    Result findList();

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    Result delete(String ids);

    /**
     * 测试连接
     *
     * @param biDatabase
     * @return
     */
    Result testConnection(BiDatabase biDatabase);

    /**
     * 获取数据表名
     *
     * @param biDatabase
     * @return
     */
    Result getTableNames(BiDatabase biDatabase);

    /**
     * 获取表字端
     *
     * @param biDatabase
     * @return
     */
    Result getTableColumns(BiDatabase biDatabase);

    /**
     * 获取数据名称
     *
     * @param biDatabase
     * @return
     */
    Result getSchema(BiDatabase biDatabase);

    /**
     * 获取数据名称
     */
    Result createSql(BiDatabase biDatabase);
}
