package com.dhlk.subcontract.service.impl;

import com.dhlk.entity.sub.Invoice;
import com.dhlk.subcontract.dao.InvoiceDao;
import com.dhlk.subcontract.service.InvoiceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 发票管理(Invoice)表服务实现类
 *
 * @author xkliu
 * @since 2021-03-12 09:21:10
 */
@Service("invoiceService")
public class InvoiceServiceImpl implements InvoiceService {
    @Resource
    private InvoiceDao invoiceDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Invoice queryById(Integer id) {
        return this.invoiceDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Invoice> queryAllByLimit(int offset, int limit) {
        return this.invoiceDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param invoice 实例对象
     * @return 实例对象
     */
    @Override
    public Invoice insert(Invoice invoice) {
        this.invoiceDao.insert(invoice);
        return invoice;
    }

    /**
     * 修改数据
     *
     * @param invoice 实例对象
     * @return 实例对象
     */
    @Override
    public Invoice update(Invoice invoice) {
        this.invoiceDao.update(invoice);
        return this.queryById(invoice.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.invoiceDao.deleteById(id) > 0;
    }
}
