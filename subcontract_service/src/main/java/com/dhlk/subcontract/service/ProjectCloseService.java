package com.dhlk.subcontract.service;


import com.dhlk.domain.Result;
import com.dhlk.entity.sub.ProjectClose;

/**
 * 关闭项目(ProjectClose)表服务接口
 *
 * @author xkliu
 * @since 2021-03-12 09:21:15
 */
public interface ProjectCloseService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ProjectClose queryById(Integer id);

    /**
     * 新增数据
     *
     * @param projectClose 实例对象
     * @return 实例对象
     */
    ProjectClose insert(ProjectClose projectClose);

    /**
     * 修改数据
     *
     * @param projectClose 实例对象
     * @return 实例对象
     */
    ProjectClose update(ProjectClose projectClose);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 新增/修改
     *
     * @param projectClose
     * @return
     */
    Result save(ProjectClose projectClose);

    /**
     * 项目关闭列表查询
     *
     * @param name
     * @param pageNum
     * @param pageSize
     * @return
     */
    Result findList(String name, Integer pageNum, Integer pageSize);
}
