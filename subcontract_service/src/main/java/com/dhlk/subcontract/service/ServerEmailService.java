package com.dhlk.subcontract.service;

import com.dhlk.domain.Result;
import com.dhlk.entity.sub.ServerEmail;

import java.util.List;

/**
 * 服务端邮箱(ServerEmail)表服务接口
 *
 * @author makejava
 * @since 2021-03-15 14:13:21
 */
public interface ServerEmailService {

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
    List<ServerEmail> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param serverEmail 实例对象
     * @return 实例对象
     */
    ServerEmail insert(ServerEmail serverEmail);

    /**
     * 修改数据
     *
     * @param serverEmail 实例对象
     * @return 实例对象
     */
    Result update(ServerEmail serverEmail);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
