package com.dhlk.subcontract.dao;


import com.dhlk.entity.sub.OrderDetails;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 订单详情(OrderDetails)表数据库访问层
 *
 * @author xkliu
 * @since 2021-03-12 09:21:13
 */
@Repository
public interface OrderDetailsDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    OrderDetails queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<OrderDetails> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param orderDetails 实例对象
     * @return 对象列表
     */
    List<OrderDetails> queryAll(OrderDetails orderDetails);

    /**
     * 新增数据
     *
     * @param orderDetails 实例对象
     * @return 影响行数
     */
    int insert(OrderDetails orderDetails);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<OrderDetails> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<OrderDetails> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<OrderDetails> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<OrderDetails> entities);

    /**
     * 修改数据
     *
     * @param orderDetails 实例对象
     * @return 影响行数
     */
    int update(OrderDetails orderDetails);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

