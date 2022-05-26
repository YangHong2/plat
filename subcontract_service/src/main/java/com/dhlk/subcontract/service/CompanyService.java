package com.dhlk.subcontract.service;


import com.dhlk.domain.Result;
import com.dhlk.entity.sub.Company;

import java.util.List;

/**
 * 企业管理(Company)表服务接口
 *
 * @author xkliu
 * @since 2021-03-12 09:20:57
 */
public interface CompanyService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Result queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Company> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param company 实例对象
     * @return 实例对象
     */
    Company insert(Company company);

    /**
     * 修改数据
     *
     * @param company 实例对象
     * @return 实例对象
     */
    Result update(Company company);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 获取金融服务商的名字
     *
     * @param name
     * @return
     */
    Result getFinancial(String name);

    /**
     * 获取咨询服务商的名字
     *
     * @param name
     * @return
     */
    Result getConsult(String name);
}
