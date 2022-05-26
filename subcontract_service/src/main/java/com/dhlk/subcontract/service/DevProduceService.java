package com.dhlk.subcontract.service;


import com.dhlk.domain.Result;
import com.dhlk.entity.sub.DevProduce;

import java.util.List;

/**
 * 开发生产(DevProduce)表服务接口
 *
 * @author xkliu
 * @since 2021-03-12 09:21:03
 */
public interface DevProduceService {



    /**
     * 新增数据
     *
     * @param devProduce 实例对象
     * @return 实例对象
     */
    Result save(DevProduce devProduce);

    /**
     * 通过主键删除数据
     *
     * @param ids 主键
     * @return 是否成功
     */
    Result delete(String ids);

}
