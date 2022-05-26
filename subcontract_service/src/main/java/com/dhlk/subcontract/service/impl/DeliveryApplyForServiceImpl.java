package com.dhlk.subcontract.service.impl;

import com.dhlk.domain.Result;
import com.dhlk.entity.sub.DeliveryApplyFor;
import com.dhlk.subcontract.dao.DeliveryApplyForDao;
import com.dhlk.subcontract.service.DeliveryApplyForService;
import com.dhlk.utils.CheckUtils;
import com.dhlk.utils.ResultUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 交付申请(DeliveryApplyFor)表服务实现类
 *
 * @author xkliu
 * @since 2021-03-12 09:21:03
 */
@Service("deliveryApplyForService")
public class DeliveryApplyForServiceImpl implements DeliveryApplyForService {
    @Resource
    private DeliveryApplyForDao deliveryApplyForDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public DeliveryApplyFor queryById(Integer id) {
        return this.deliveryApplyForDao.queryById(id);
    }


    /**
     * 新增数据
     *
     * @param deliveryApplyFor 实例对象
     * @return 实例对象
     */
    @Override
    public DeliveryApplyFor insert(DeliveryApplyFor deliveryApplyFor) {
        this.deliveryApplyForDao.insert(deliveryApplyFor);
        return deliveryApplyFor;
    }

    /**
     * 修改数据
     *
     * @param deliveryApplyFor 实例对象
     * @return 实例对象
     */
    @Override
    public DeliveryApplyFor update(DeliveryApplyFor deliveryApplyFor) {
        this.deliveryApplyForDao.update(deliveryApplyFor);
        return this.queryById(deliveryApplyFor.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.deliveryApplyForDao.deleteById(id) > 0;
    }

    @Override
    @Transactional
    public Result save(DeliveryApplyFor deliveryApplyFor) {
        Integer flag = 0;
        //新增
        if (CheckUtils.isNull(deliveryApplyFor.getId())) {
            flag = deliveryApplyForDao.insert(deliveryApplyFor);
        } else {
            //修改
            flag = deliveryApplyForDao.update(deliveryApplyFor);
        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }
}
