package com.dhlk.subcontract.service;


import com.dhlk.domain.Result;
import com.dhlk.entity.sub.Coupons;

import java.util.List;

/**
 * 优惠卷(Coupons)表服务接口
 *
 * @author xkliu
 * @since 2021-03-12 09:21:01
 */
public interface CouponsService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Coupons queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Coupons> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param coupons 实例对象
     * @return 实例对象
     */
    Coupons insert(Coupons coupons);

    /**
     * 修改数据
     *
     * @param coupons 实例对象
     * @return 实例对象
     */
    Coupons update(Coupons coupons);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 列表查询
     */
    Result findList(String couponNo, String createStartTime, String createEndTime, String expireStartTime, String expireEndTime, Integer pageNum, Integer pageSize);

    /**
     * 新增/修改
     */
    Result save(Coupons coupons);
}
