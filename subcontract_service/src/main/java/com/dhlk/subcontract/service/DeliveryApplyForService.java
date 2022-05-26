package com.dhlk.subcontract.service;


import com.dhlk.domain.Result;
import com.dhlk.entity.sub.DeliveryApplyFor;

/**
 * 交付申请(DeliveryApplyFor)表服务接口
 *
 * @author xkliu
 * @since 2021-03-12 09:21:02
 */
public interface DeliveryApplyForService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    DeliveryApplyFor queryById(Integer id);


    /**
     * 新增数据
     *
     * @param deliveryApplyFor 实例对象
     * @return 实例对象
     */
    DeliveryApplyFor insert(DeliveryApplyFor deliveryApplyFor);

    /**
     * 修改数据
     *
     * @param deliveryApplyFor 实例对象
     * @return 实例对象
     */
    DeliveryApplyFor update(DeliveryApplyFor deliveryApplyFor);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 新增/修改
     *
     * @param deliveryApplyFor
     * @return
     */
    Result save(DeliveryApplyFor deliveryApplyFor);
}
