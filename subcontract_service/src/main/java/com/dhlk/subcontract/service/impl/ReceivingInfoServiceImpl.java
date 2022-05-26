package com.dhlk.subcontract.service.impl;

import com.dhlk.entity.sub.ReceivingInfo;
import com.dhlk.subcontract.dao.ReceivingInfoDao;
import com.dhlk.subcontract.service.ReceivingInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 收付款信息(ReceivingInfo)表服务实现类
 *
 * @author xkliu
 * @since 2021-03-12 09:23:28
 */
@Service("receivingInfoService")
public class ReceivingInfoServiceImpl implements ReceivingInfoService {
    @Resource
    private ReceivingInfoDao receivingInfoDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ReceivingInfo queryById(Integer id) {
        return this.receivingInfoDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<ReceivingInfo> queryAllByLimit(int offset, int limit) {
        return this.receivingInfoDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param receivingInfo 实例对象
     * @return 实例对象
     */
    @Override
    public ReceivingInfo insert(ReceivingInfo receivingInfo) {
        this.receivingInfoDao.insert(receivingInfo);
        return receivingInfo;
    }

    /**
     * 修改数据
     *
     * @param receivingInfo 实例对象
     * @return 实例对象
     */
    @Override
    public ReceivingInfo update(ReceivingInfo receivingInfo) {
        this.receivingInfoDao.update(receivingInfo);
        return this.queryById(receivingInfo.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.receivingInfoDao.deleteById(id) > 0;
    }
}
