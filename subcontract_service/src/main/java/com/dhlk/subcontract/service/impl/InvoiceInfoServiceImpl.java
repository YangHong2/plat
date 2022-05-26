package com.dhlk.subcontract.service.impl;

import com.dhlk.entity.sub.InvoiceInfo;
import com.dhlk.subcontract.dao.InvoiceInfoDao;
import com.dhlk.subcontract.service.InvoiceInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 发票地址信息(InvoiceInfo)表服务实现类
 *
 * @author xkliu
 * @since 2021-03-12 09:21:11
 */
@Service("invoiceInfoService")
public class InvoiceInfoServiceImpl implements InvoiceInfoService {
    @Resource
    private InvoiceInfoDao invoiceInfoDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public InvoiceInfo queryById(Integer id) {
        return this.invoiceInfoDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<InvoiceInfo> queryAllByLimit(int offset, int limit) {
        return this.invoiceInfoDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param invoiceInfo 实例对象
     * @return 实例对象
     */
    @Override
    public InvoiceInfo insert(InvoiceInfo invoiceInfo) {
        this.invoiceInfoDao.insert(invoiceInfo);
        return invoiceInfo;
    }

    /**
     * 修改数据
     *
     * @param invoiceInfo 实例对象
     * @return 实例对象
     */
    @Override
    public InvoiceInfo update(InvoiceInfo invoiceInfo) {
        this.invoiceInfoDao.update(invoiceInfo);
        return this.queryById(invoiceInfo.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.invoiceInfoDao.deleteById(id) > 0;
    }
}
