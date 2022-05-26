package com.dhlk.subcontract.dao;


import com.dhlk.entity.sub.Invoice;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 发票管理(Invoice)表数据库访问层
 *
 * @author xkliu
 * @since 2021-03-12 09:21:10
 */
@Repository
public interface InvoiceDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Invoice queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Invoice> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param invoice 实例对象
     * @return 对象列表
     */
    List<Invoice> queryAll(Invoice invoice);

    /**
     * 新增数据
     *
     * @param invoice 实例对象
     * @return 影响行数
     */
    int insert(Invoice invoice);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Invoice> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Invoice> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Invoice> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<Invoice> entities);

    /**
     * 修改数据
     *
     * @param invoice 实例对象
     * @return 影响行数
     */
    int update(Invoice invoice);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

