package com.dhlk.bi.dao;

import com.dhlk.entity.bi.BiDatabase;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @des: 数据库连接 dao
 * @author: xkliu
 * @date: 2021/02/22
 */
@Repository
public interface BiDatabaseDao {

    /**
     * 新增
     *
     * @param biDatabase
     * @return
     */
    Integer insert(BiDatabase biDatabase);

    /**
     * 修改
     *
     * @param biDatabase
     * @return
     */
    Integer update(BiDatabase biDatabase);

    /**
     * 列表查询
     *
     * @return
     */
    List<BiDatabase> findList();

    /**
     * 删除
     *
     * @param lists
     * @return
     */
    Integer delete(List<String> lists);

    /**
     * 根据id查询
     */
    BiDatabase findById(@Param("biDataBaseId") Integer biDataBaseId);
}

