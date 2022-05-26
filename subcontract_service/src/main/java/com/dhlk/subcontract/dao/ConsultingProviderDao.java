package com.dhlk.subcontract.dao;


import com.dhlk.entity.sub.ConsultingProvider;
import com.dhlk.entity.sub.FinancialProvider;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 咨询服务商(ConsultingProvider)表数据库访问层
 *
 * @author xkliu
 * @since 2021-03-12 09:21:00
 */
@Repository
public interface ConsultingProviderDao {

    /**
     * 新增数据
     *
     * @param consultingProvider 实例对象
     * @return 影响行数
     */
    int insert(ConsultingProvider consultingProvider);


    /**
     * 修改数据
     *
     * @param consultingProvider 实例对象
     * @return 影响行数
     */
    int update(ConsultingProvider consultingProvider);

    /**
     * 通过主键删除数据
     *
     * @param ids 主键
     * @return 影响行数
     */
    int delete(@Param(value = "ids") List<String> ids);

    /**
     * 我咨询的
     *
     * @param name
     * @param companyId
     * @return
     */
    List<ConsultingProvider> findAdvisory(@Param("name") String name, @Param("companyId") Integer companyId);

    /**
     * 根据项目id查询
     * @param projectId
     * @return
     */
    List<ConsultingProvider> findByProjectId(@Param("projectId") Integer projectId);
}

