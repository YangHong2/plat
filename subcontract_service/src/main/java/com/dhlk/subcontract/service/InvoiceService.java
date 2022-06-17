package com.dhlk.subcontract.service;


import com.dhlk.entity.sub.Invoice;

import java.util.List;

/**
 * 发票管理(Invoice)表服务接口
 *
 * @author xkliu
 * @since 2021-03-12 09:21:10
 */
public interface InvoiceService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Invoice queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Invoice> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param invoice 实例对象
     * @return 实例对象
     */
    Invoice insert(Invoice invoice);

    /**
     * 修改数据
     *
     * @param invoice 实例对象
     * @return 实例对象
     */
    Invoice update(Invoice invoice);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}