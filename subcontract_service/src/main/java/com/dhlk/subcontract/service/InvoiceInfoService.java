package com.dhlk.subcontract.service;


import com.dhlk.entity.sub.InvoiceInfo;

import java.util.List;

/**
 * 发票地址信息(InvoiceInfo)表服务接口
 *
 * @author xkliu
 * @since 2021-03-12 09:21:11
 */
public interface InvoiceInfoService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    InvoiceInfo queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<InvoiceInfo> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param invoiceInfo 实例对象
     * @return 实例对象
     */
    InvoiceInfo insert(InvoiceInfo invoiceInfo);

    /**
     * 修改数据
     *
     * @param invoiceInfo 实例对象
     * @return 实例对象
     */
    InvoiceInfo update(InvoiceInfo invoiceInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
