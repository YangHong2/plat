package com.dhlk.subcontract.service;

import com.dhlk.domain.Result;
import com.dhlk.entity.sub.ArticlePub;

/**
 * 文章发布(ArticlePub)表服务接口
 *
 * @author xkliu
 * @since 2021-03-23 11:14:04
 */
public interface ArticlePubService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Result queryById(Integer id);


    /**
     * 新增数据
     *
     * @param articlePub 实例对象
     * @return 实例对象
     */
    Result insert(ArticlePub articlePub);


    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 列表查询
     *
     * @param title
     * @param pageNum
     * @param pageSize
     * @return
     */
    Result findList(String title, Integer pageNum, Integer pageSize);
}
