package com.dhlk.subcontract.dao;


import com.dhlk.entity.sub.Advert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 广告(Advert)表数据库访问层
 *
 * @author wyang
 * @since 2021-03-12 09:20:52
 */
@Repository
public interface AdvertDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Advert queryById(Integer id);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param
     * @return 对象列表
     */
    List<Advert> queryAll(String addressNo, String name, String dataId, String createTime);

    /**
     * 新增数据
     *
     * @param advert 实例对象
     * @return 影响行数
     */
    int insert(Advert advert);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 修改数据
     *
     * @param advert 实例对象
     * @return 影响行数
     */
    int update(Advert advert);



}

