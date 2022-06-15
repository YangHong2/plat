package com.dhlk.subcontract.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dhlk.entity.sub.CompanyPages;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 企业主页(CompanyPages)表数据库访问层
 *
 * @author xkliu
 * @since 2021-03-12 09:20:59
 */
@Repository
public interface CompanyPagesDao extends BaseMapper<CompanyPages> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    CompanyPages queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<CompanyPages> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param companyPages 实例对象
     * @return 对象列表
     */
    List<CompanyPages> queryAll(CompanyPages companyPages);

    /**
     * 新增数据
     *
     * @param companyPages 实例对象
     * @return 影响行数
     */
    int insert(CompanyPages companyPages);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<CompanyPages> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<CompanyPages> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<CompanyPages> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<CompanyPages> entities);

    /**
     * 修改数据
     *
     * @param companyPages 实例对象
     * @return 影响行数
     */
    int update(CompanyPages companyPages);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

