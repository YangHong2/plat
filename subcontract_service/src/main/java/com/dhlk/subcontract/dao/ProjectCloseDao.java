package com.dhlk.subcontract.dao;


import com.dhlk.entity.sub.ProjectClose;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 关闭项目(ProjectClose)表数据库访问层
 *
 * @author xkliu
 * @since 2021-03-12 09:21:14
 */
@Repository
public interface ProjectCloseDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ProjectClose queryById(Integer id);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param projectClose 实例对象
     * @return 对象列表
     */
    List<ProjectClose> queryAll(ProjectClose projectClose);

    /**
     * 新增数据
     *
     * @param projectClose 实例对象
     * @return 影响行数
     */
    int insert(ProjectClose projectClose);


    /**
     * 修改数据
     *
     * @param projectClose 实例对象
     * @return 影响行数
     */
    int update(ProjectClose projectClose);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);


    /**
     * 根据项目id查询
     *
     * @param projectId
     * @return
     */
    List<ProjectClose> findByProjectId(@Param("projectId") Integer projectId);

    /**
     * 项目关闭列表查询
     *
     * @param name
     * @return
     */
    List<ProjectClose> findList(@Param("name") String name);
}

