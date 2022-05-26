package com.dhlk.subcontract.dao;

import com.dhlk.entity.sub.ArticlePub;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 文章发布(ArticlePub)表数据库访问层
 *
 * @author xkliu
 * @since 2021-03-23 11:14:04
 */
public interface ArticlePubDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ArticlePub queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<ArticlePub> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param articlePub 实例对象
     * @return 对象列表
     */
    List<ArticlePub> queryAll(ArticlePub articlePub);

    /**
     * 新增数据
     *
     * @param articlePub 实例对象
     * @return 影响行数
     */
    int insert(ArticlePub articlePub);


    /**
     * 修改数据
     *
     * @param articlePub 实例对象
     * @return 影响行数
     */
    int update(ArticlePub articlePub);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);


    /**
     * 列表查询
     *
     * @param title
     * @return
     */
    List<ArticlePub> findList(@Param("title") String title);

}

