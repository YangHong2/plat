package com.dhlk.subcontract.service;


import com.dhlk.entity.sub.SubpackageMessage;

import java.util.List;

/**
 * 消息(SubpackageMessage)表服务接口
 *
 * @author xkliu
 * @since 2021-03-12 09:23:58
 */
public interface SubpackageMessageService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubpackageMessage queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<SubpackageMessage> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param subpackageMessage 实例对象
     * @return 实例对象
     */
    SubpackageMessage insert(SubpackageMessage subpackageMessage);

    /**
     * 修改数据
     *
     * @param subpackageMessage 实例对象
     * @return 实例对象
     */
    SubpackageMessage update(SubpackageMessage subpackageMessage);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
