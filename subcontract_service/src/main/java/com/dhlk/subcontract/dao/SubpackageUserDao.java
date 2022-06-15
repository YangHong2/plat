package com.dhlk.subcontract.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dhlk.entity.sub.SubpackageUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户表(SubpackageUser)表数据库访问层
 *
 * @author xkliu
 * @since 2021-03-12 09:24:31
 */
@Repository
@Mapper
public interface SubpackageUserDao extends BaseMapper<SubpackageUser> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubpackageUser queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<SubpackageUser> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param subpackageUser 实例对象
     * @return 对象列表
     */
    List<SubpackageUser> queryAll(SubpackageUser subpackageUser);

    /**
     * 新增数据
     *
     * @param subpackageUser 实例对象
     * @return 影响行数
     */
    int insert(SubpackageUser subpackageUser);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SubpackageUser> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SubpackageUser> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SubpackageUser> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<SubpackageUser> entities);

    /**
     * 修改数据
     *
     * @param subpackageUser 实例对象
     * @return 影响行数
     */
//    int update(SubpackageUser subpackageUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);


    /**
     * 通过登录名查询用户信息
    * @param loginName 用户登录名
    * @return 用户信息
     */
    SubpackageUser findByLoginName(@Param("loginName") String loginName);

    /**
     * 判断登录名是否重复
     * @param subpackageUser 用户信息
     * @return 数量
     */
    Integer isRepeatLoginName(SubpackageUser subpackageUser);

    /**
     * 判断注册邮箱是否重复
     * @param subpackageUser 用户信息
     * @return 数量
     */
    Integer isRepeatCompanyEmail(SubpackageUser subpackageUser);

    /**
     * 根据企业邮箱查询用户
     * @param companyEmail 邮箱
     * @return 用户信息
     */
    SubpackageUser findByEmail(@Param("companyEmail") String companyEmail);

    /**
     * 列表查询
     */
    List<SubpackageUser> findList(@Param("companyName")String companyName,
                                  @Param("auditStatus")Integer auditStatus,
                                  @Param("isBlacklist")Integer isBlacklist);
}

