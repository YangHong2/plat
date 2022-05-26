package com.dhlk.subcontract.dao;


import com.dhlk.entity.sub.ReceivingInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 收付款信息(ReceivingInfo)表数据库访问层
 *
 * @author xkliu
 * @since 2021-03-12 09:23:24
 */
@Repository
public interface ReceivingInfoDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ReceivingInfo queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<ReceivingInfo> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param receivingInfo 实例对象
     * @return 对象列表
     */
    List<ReceivingInfo> queryAll(ReceivingInfo receivingInfo);

    /**
     * 新增数据
     *
     * @param receivingInfo 实例对象
     * @return 影响行数
     */
    int insert(ReceivingInfo receivingInfo);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<ReceivingInfo> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<ReceivingInfo> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<ReceivingInfo> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<ReceivingInfo> entities);

    /**
     * 修改数据
     *
     * @param receivingInfo 实例对象
     * @return 影响行数
     */
    int update(ReceivingInfo receivingInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

