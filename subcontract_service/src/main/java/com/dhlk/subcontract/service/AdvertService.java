package com.dhlk.subcontract.service;


import com.dhlk.domain.Result;
import com.dhlk.entity.sub.Advert;

import java.util.List;

/**
 * 广告(Advert)表服务接口
 *
 * @author wyang
 * @since 2021-03-12 09:20:53
 */
public interface AdvertService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Result queryById(Integer id);

    /**
     * 查询多条数据
     * @return 对象列表
     */
    Result queryAll(String adressNo, String name, String dataId, String createTime,Integer pageNum, Integer pageSize);

    /**
     * 新增数据
     *
     * @param advert 实例对象
     * @return 实例对象
     */
    Result save(Advert advert);


    /**
     * 通过主键删除数据
     *
     * @param ids 主键
     * @return 是否成功
     */
    Result deleteById(String ids);

}
