package com.dhlk.subcontract.dao;


import com.dhlk.entity.sub.UserCoupons;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户优惠券(UserCoupons)表数据库访问层
 *
 * @author xkliu
 * @since 2021-03-12 09:29:03
 */
@Repository
public interface UserCouponsDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserCoupons queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<UserCoupons> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param userCoupons 实例对象
     * @return 对象列表
     */
    List<UserCoupons> queryAll(UserCoupons userCoupons);

    /**
     * 新增数据
     *
     * @param userCoupons 实例对象
     * @return 影响行数
     */
    int insert(UserCoupons userCoupons);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<UserCoupons> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<UserCoupons> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<UserCoupons> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<UserCoupons> entities);

    /**
     * 修改数据
     *
     * @param userCoupons 实例对象
     * @return 影响行数
     */
    int update(UserCoupons userCoupons);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

