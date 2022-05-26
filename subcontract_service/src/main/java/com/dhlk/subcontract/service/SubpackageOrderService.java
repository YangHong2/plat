package com.dhlk.subcontract.service;


import com.dhlk.entity.sub.SubpackageOrder;

import java.util.List;

/**
 * 订单(SubpackageOrder)表服务接口
 *
 * @author xkliu
 * @since 2021-03-12 09:24:20
 */
public interface SubpackageOrderService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubpackageOrder queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<SubpackageOrder> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param subpackageOrder 实例对象
     * @return 实例对象
     */
    SubpackageOrder insert(SubpackageOrder subpackageOrder);

    /**
     * 修改数据
     *
     * @param subpackageOrder 实例对象
     * @return 实例对象
     */
    SubpackageOrder update(SubpackageOrder subpackageOrder);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
