package com.dhlk.subcontract.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.dhlk.domain.Result;
import com.dhlk.entity.sub.SubpackageMessage;

/**
 * 消息(SubpackageMessage)表服务接口
 *
 * @author xkliu
 * @since 2021-03-12 09:23:58
 */
public interface SubpackageMessageService extends IService<SubpackageMessage> {

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

    Result queryAllByLimit(int pageNum, int pageSize);

    /**
     * 新增数据
     *
     * @param subpackageMessage 实例对象
     * @return 实例对象
     */
    Result insert(SubpackageMessage subpackageMessage);

    /**
     * 修改数据
     *
     * @param subpackageMessage 实例对象
     * @return 实例对象
     */
    Result update(SubpackageMessage subpackageMessage);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    Result deleteById(Integer id);

    /**
     * 根据文字模糊查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    Result queryBymessageName(String messageName,int pageNum, int pageSize);
}
