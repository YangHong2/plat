package com.dhlk.subcontract.service;


import com.dhlk.entity.sub.UserCoupons;

import java.util.List;

/**
 * 用户优惠券(UserCoupons)表服务接口
 *
 * @author xkliu
 * @since 2021-03-12 09:29:04
 */
public interface UserCouponsService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserCoupons queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<UserCoupons> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param userCoupons 实例对象
     * @return 实例对象
     */
    UserCoupons insert(UserCoupons userCoupons);

    /**
     * 修改数据
     *
     * @param userCoupons 实例对象
     * @return 实例对象
     */
    UserCoupons update(UserCoupons userCoupons);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
