package com.dhlk.subcontract.service;


import com.dhlk.entity.sub.CompanyPages;

import java.util.List;

/**
 * 企业主页(CompanyPages)表服务接口
 *
 * @author xkliu
 * @since 2021-03-12 09:20:59
 */
public interface CompanyPagesService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    CompanyPages queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<CompanyPages> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param companyPages 实例对象
     * @return 实例对象
     */
    CompanyPages insert(CompanyPages companyPages);

    /**
     * 修改数据
     *
     * @param companyPages 实例对象
     * @return 实例对象
     */
    CompanyPages update(CompanyPages companyPages);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
