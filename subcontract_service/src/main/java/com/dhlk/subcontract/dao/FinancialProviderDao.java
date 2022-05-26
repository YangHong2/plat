package com.dhlk.subcontract.dao;

import com.dhlk.entity.sub.FinancialProvider;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 金融服务商(FinancialProvider)表数据库访问层
 *
 * @author makejava
 * @since 2021-03-15 09:27:23
 */
@Repository
public interface FinancialProviderDao {

    /**
     * 新增数据
     *
     * @param financialProvider 实例对象
     * @return 影响行数
     */
    int insert(FinancialProvider financialProvider);


    /**
     * 修改数据
     *
     * @param financialProvider 实例对象
     * @return 影响行数
     */
    int update(FinancialProvider financialProvider);

    /**
     * 通过主键删除数据
     *
     * @param ids 主键
     * @return 影响行数
     */
    int delete(@Param(value = "ids") List<String> ids);

    /**
     * 查询我投资的
     *
     * @param name
     * @return
     */
    List<FinancialProvider> findInvest(@Param("name") String name, @Param("companyId") Integer companyId);

    /**
     * 根据项目id查询
     * @param projectId
     * @return
     */
    List<FinancialProvider> findByProjectId(@Param("projectId") Integer projectId);
}

