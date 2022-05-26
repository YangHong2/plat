package com.dhlk.subcontract.dao;


import com.dhlk.entity.sub.Coupons;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 优惠卷(Coupons)表数据库访问层
 *
 * @author xkliu
 * @since 2021-03-12 09:21:01
 */
@Repository
public interface CouponsDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Coupons queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Coupons> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param coupons 实例对象
     * @return 对象列表
     */
    List<Coupons> queryAll(Coupons coupons);

    /**
     * 新增数据
     *
     * @param coupons 实例对象
     * @return 影响行数
     */
    int insert(Coupons coupons);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Coupons> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Coupons> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Coupons> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<Coupons> entities);

    /**
     * 修改数据
     *
     * @param coupons 实例对象
     * @return 影响行数
     */
    int update(Coupons coupons);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 列表查询
     */
    List<Coupons> findList(@Param("couponNo")String couponNo,
                           @Param("createStartTime")String createStartTime,
                           @Param("createEndTime")String createEndTime,
                           @Param("expireStartTime")String expireStartTime,
                           @Param("expireEndTime")String expireEndTime);
}

