package com.dhlk.subcontract.dao;


import com.dhlk.entity.sub.DevProduce;
import com.dhlk.entity.sub.FinancialProvider;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 开发生产(DevProduce)表数据库访问层
 *
 * @author xkliu
 * @since 2021-03-12 09:21:03
 */
@Repository
public interface DevProduceDao {


    /**
     * 新增数据
     *
     * @param devProduce 实例对象
     * @return 影响行数
     */
    int insert(DevProduce devProduce);


    /**
     * 修改数据
     *
     * @param devProduce 实例对象
     * @return 影响行数
     */
    int update(DevProduce devProduce);

    /**
     * 通过主键删除数据
     *
     * @param ids 主键
     * @return 影响行数
     */
    int delete(List<String> ids);

    /**
     * 我施工的
     *
     * @param name
     * @param companyId
     * @return
     */
    List<DevProduce> findConstruction(String name, Integer companyId);

    /**
     * 根据项目id查询
     * @param projectId
     * @return
     */
    List<DevProduce> findByProjectId(@Param("projectId") Integer projectId);

    /**
     * 根据项目id查询
     * @param projectId
     * @return
     */
    List<DevProduce> findInstallation(@Param("projectId") Integer projectId);
}

