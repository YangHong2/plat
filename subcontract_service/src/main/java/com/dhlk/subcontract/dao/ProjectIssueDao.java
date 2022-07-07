package com.dhlk.subcontract.dao;


import com.dhlk.entity.sub.ProjectIssue;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 项目发布(ProjectIssue)表数据库访问层
 *
 * @author xkliu
 * @since 2021-03-12 09:21:17
 */
@Repository
public interface ProjectIssueDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ProjectIssue queryById(Integer id);


    /**
     * 新增数据
     *
     * @param projectIssue 实例对象
     * @return 影响行数
     */
    Integer insert(ProjectIssue projectIssue);


    /**
     * 修改数据
     *
     * @param projectIssue 实例对象
     * @return 影响行数
     */
    Integer update(ProjectIssue projectIssue);


    /**
     * 我发布的
     *
     * @param name
     * @param companyId
     * @return
     */
    List<ProjectIssue> findIssue(@Param("name") String name, @Param("companyId") Integer companyId);


    /**
     * 查询全部
     *
     * @param name
     * @param companyId
     * @return
     */
    List<ProjectIssue> findAll(@Param("name") String name, @Param("companyId") Integer companyId);


    /**
     * 查询项目关联数据
     *
     * @param id
     * @param companyId
     * @return
     */
    List<ProjectIssue> findRelevance(@Param("id") Integer id, @Param("companyId") Integer companyId);


    /**
     * 项目管理列表查询
     *
     * @param name
     * @return
     */
    List<ProjectIssue> findProjectManage(@Param("name") String name);

    /**
     * 获取关联项目
     *
     * @param companyId
     * @return
     */
    List<ProjectIssue> getRelevance(@Param("companyId") Integer companyId);

    /**
     * 首页搜索
     *
     * @param projectName
     * @param projectType
     * @return
     */
    List<ProjectIssue> search(@Param("projectName") String projectName, @Param("projectType") Integer projectType);

    /**
     * 根据id获取项目详情
     *
     * @param id
     * @return
     */
    ProjectIssue getProjectIssue(@Param("id") Integer id);

    /**
     * 查询未审批的项目
     * @param name
     * @return
     */
    List<ProjectIssue> findName(String name);

    int findCount();
}

