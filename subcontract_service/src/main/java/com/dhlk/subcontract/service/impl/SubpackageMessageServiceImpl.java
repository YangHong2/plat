package com.dhlk.subcontract.service.impl;

import com.dhlk.entity.sub.SubpackageMessage;
import com.dhlk.subcontract.dao.SubpackageMessageDao;
import com.dhlk.subcontract.service.SubpackageMessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 消息(SubpackageMessage)表服务实现类
 *
 * @author xkliu
 * @since 2021-03-12 09:23:59
 */
@Service("subpackageMessageService")
public class SubpackageMessageServiceImpl implements SubpackageMessageService {
    @Resource
    private SubpackageMessageDao subpackageMessageDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SubpackageMessage queryById(Integer id) {
        return this.subpackageMessageDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<SubpackageMessage> queryAllByLimit(int offset, int limit) {
        return this.subpackageMessageDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param subpackageMessage 实例对象
     * @return 实例对象
     */
    @Override
    public SubpackageMessage insert(SubpackageMessage subpackageMessage) {
        this.subpackageMessageDao.insert(subpackageMessage);
        return subpackageMessage;
    }

    /**
     * 修改数据
     *
     * @param subpackageMessage 实例对象
     * @return 实例对象
     */
    @Override
    public SubpackageMessage update(SubpackageMessage subpackageMessage) {
        this.subpackageMessageDao.update(subpackageMessage);
        return this.queryById(subpackageMessage.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.subpackageMessageDao.deleteById(id) > 0;
    }
}
