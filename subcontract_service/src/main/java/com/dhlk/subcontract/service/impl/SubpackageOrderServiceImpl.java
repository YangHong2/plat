package com.dhlk.subcontract.service.impl;

import com.dhlk.entity.sub.SubpackageOrder;
import com.dhlk.subcontract.dao.SubpackageOrderDao;
import com.dhlk.subcontract.service.SubpackageOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 订单(SubpackageOrder)表服务实现类
 *
 * @author xkliu
 * @since 2021-03-12 09:24:21
 */
@Service("subpackageOrderService")
public class SubpackageOrderServiceImpl implements SubpackageOrderService {
    @Resource
    private SubpackageOrderDao subpackageOrderDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SubpackageOrder queryById(Integer id) {
        return this.subpackageOrderDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<SubpackageOrder> queryAllByLimit(int offset, int limit) {
        return this.subpackageOrderDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param subpackageOrder 实例对象
     * @return 实例对象
     */
    @Override
    public SubpackageOrder insert(SubpackageOrder subpackageOrder) {
        this.subpackageOrderDao.insert(subpackageOrder);
        return subpackageOrder;
    }

    /**
     * 修改数据
     *
     * @param subpackageOrder 实例对象
     * @return 实例对象
     */
    @Override
    public SubpackageOrder update(SubpackageOrder subpackageOrder) {
        this.subpackageOrderDao.update(subpackageOrder);
        return this.queryById(subpackageOrder.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.subpackageOrderDao.deleteById(id) > 0;
    }
}
