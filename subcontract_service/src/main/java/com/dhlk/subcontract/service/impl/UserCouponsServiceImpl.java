package com.dhlk.subcontract.service.impl;

import com.dhlk.entity.sub.UserCoupons;
import com.dhlk.subcontract.dao.UserCouponsDao;
import com.dhlk.subcontract.service.UserCouponsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户优惠券(UserCoupons)表服务实现类
 *
 * @author xkliu
 * @since 2021-03-12 09:29:06
 */
@Service("userCouponsService")
public class UserCouponsServiceImpl implements UserCouponsService {
    @Resource
    private UserCouponsDao userCouponsDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public UserCoupons queryById(Integer id) {
        return this.userCouponsDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<UserCoupons> queryAllByLimit(int offset, int limit) {
        return this.userCouponsDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param userCoupons 实例对象
     * @return 实例对象
     */
    @Override
    public UserCoupons insert(UserCoupons userCoupons) {
        this.userCouponsDao.insert(userCoupons);
        return userCoupons;
    }

    /**
     * 修改数据
     *
     * @param userCoupons 实例对象
     * @return 实例对象
     */
    @Override
    public UserCoupons update(UserCoupons userCoupons) {
        this.userCouponsDao.update(userCoupons);
        return this.queryById(userCoupons.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.userCouponsDao.deleteById(id) > 0;
    }
}
