package com.dhlk.subcontract.service;


import com.dhlk.domain.Result;
import com.dhlk.entity.sub.ReceivingInfo;

import java.util.List;

/**
 * 收付款信息(ReceivingInfo)表服务接口
 *
 * @author xkliu
 * @since 2021-03-12 09:23:26
 */
public interface ReceivingInfoService {

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
    List<ReceivingInfo> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param receivingInfo 实例对象
     * @return 实例对象
     */
    Result insert(ReceivingInfo receivingInfo);

    /**
     * 修改数据
     *
     * @param receivingInfo 实例对象
     * @return 实例对象
     */
    Result update(ReceivingInfo receivingInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 通过项目ID查询单条数据
     * @param id
     * @return
     */
    Result selectByProjectId(Integer id);
}
