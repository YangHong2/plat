package com.dhlk.subcontract.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dhlk.entity.sub.Solution;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 解决方案(Solution)表数据库访问层
 *
 * @author makejava
 * @since 2021-03-12 09:54:21
 */
@Repository
public interface SolutionDao extends BaseMapper<Solution> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Solution queryById(Integer id);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param projectTheme 实例对象
     * @return 对象列表
     */
    List<Solution> queryAll(@Param(value = "projectTheme") String projectTheme,@Param(value = "companyId") Integer companyId);

    /**
     * 新增数据
     *
     * @param solution 实例对象
     * @return 影响行数
     */
    int insert(Solution solution);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Solution> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Solution> entities);

    /**
     * 修改数据
     *
     * @param solution 实例对象
     * @return 影响行数
     */
    int update(Solution solution);

    /**
     * 通过主键删除数据
     *
     * @param ids 主键
     * @return 影响行数
     */
    int delete(List<String> ids);

}

