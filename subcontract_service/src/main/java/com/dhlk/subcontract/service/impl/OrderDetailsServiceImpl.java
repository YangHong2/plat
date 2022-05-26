package com.dhlk.subcontract.service.impl;

import com.dhlk.entity.sub.OrderDetails;
import com.dhlk.subcontract.dao.OrderDetailsDao;
import com.dhlk.subcontract.service.OrderDetailsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 订单详情(OrderDetails)表服务实现类
 *
 * @author xkliu
 * @since 2021-03-12 09:21:14
 */
@Service("orderDetailsService")
public class OrderDetailsServiceImpl implements OrderDetailsService {
    @Resource
    private OrderDetailsDao orderDetailsDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public OrderDetails queryById(Integer id) {
        return this.orderDetailsDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<OrderDetails> queryAllByLimit(int offset, int limit) {
        return this.orderDetailsDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param orderDetails 实例对象
     * @return 实例对象
     */
    @Override
    public OrderDetails insert(OrderDetails orderDetails) {
        this.orderDetailsDao.insert(orderDetails);
        return orderDetails;
    }

    /**
     * 修改数据
     *
     * @param orderDetails 实例对象
     * @return 实例对象
     */
    @Override
    public OrderDetails update(OrderDetails orderDetails) {
        this.orderDetailsDao.update(orderDetails);
        return this.queryById(orderDetails.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.orderDetailsDao.deleteById(id) > 0;
    }
}
