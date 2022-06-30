package com.dhlk.subcontract.service;


import com.dhlk.domain.Result;
import com.dhlk.entity.sub.ProjectIssue;
import org.springframework.stereotype.Service;

/**
 * 项目发布(ProjectIssue)表服务接口
 *
 * @author xkliu
 * @since 2021-03-12 09:21:17
 */
@Service
public interface ProjectIssueService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ProjectIssue queryById(Integer id);


    /**
     * 新增/修改
     *
     * @param projectIssue
     * @return
     */
    Result save(ProjectIssue projectIssue);


    /**
     * 列表查询
     *
     * @param name
     * @param type
     * @param pageNum
     * @param pageSize
     * @return
     */
    Result findList(String name, Integer type, Integer companyId, Integer pageNum, Integer pageSize);


    /**
     * 查询项目关联数据
     *
     * @param id
     * @param companyId
     * @return
     */
    Result findRelevance(Integer id, Integer companyId);

    /**
     * 项目管理列表查询
     *
     * @param name
     * @param pageNum
     * @param pageSize
     * @return
     */
    Result findProjectManage(String name, Integer pageNum, Integer pageSize);

    /**
     * 我的项目数量
     *
     * @param companyId
     * @return
     */
    Result getProjectCount(Integer companyId);

    /**
     * 获取关联项目
     *
     * @param companyId
     * @return
     */
    Result getRelevance(Integer companyId);

    /**
     * 首页搜索
     *
     * @param projectName
     * @param projectType
     * @return
     */
    Result search(String projectName, Integer projectType,Integer pageNum,Integer pageSize);

    /**
     * 根据id获取项目详情
     * @param id
     * @return
     */
    Result getProjectIssue(Integer id);

    /**
     * 项目审批
     * @param name
     * @return
     */
    Result findName(String name,Integer pageNum,Integer pageSize);
}
