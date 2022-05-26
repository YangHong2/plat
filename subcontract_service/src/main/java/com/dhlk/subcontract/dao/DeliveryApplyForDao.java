package com.dhlk.subcontract.dao;


import com.dhlk.entity.sub.DeliveryApplyFor;
import com.dhlk.entity.sub.DevProduce;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 交付申请(DeliveryApplyFor)表数据库访问层
 *
 * @author xkliu
 * @since 2021-03-12 09:21:02
 */
@Repository
public interface DeliveryApplyForDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    DeliveryApplyFor queryById(Integer id);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param deliveryApplyFor 实例对象
     * @return 对象列表
     */
    List<DeliveryApplyFor> queryAll(DeliveryApplyFor deliveryApplyFor);

    /**
     * 新增数据
     *
     * @param deliveryApplyFor 实例对象
     * @return 影响行数
     */
    int insert(DeliveryApplyFor deliveryApplyFor);


    /**
     * 修改数据
     *
     * @param deliveryApplyFor 实例对象
     * @return 影响行数
     */
    int update(DeliveryApplyFor deliveryApplyFor);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 根据项目id查询
     * @param projectId
     * @return
     */
    List<DeliveryApplyFor> findByProjectId(@Param("projectId") Integer projectId);


    /**
     * 根据项目id查询
     * @param projectId
     * @return
     */
    List<DeliveryApplyFor> findInstallApply(@Param("projectId") Integer projectId);

}


