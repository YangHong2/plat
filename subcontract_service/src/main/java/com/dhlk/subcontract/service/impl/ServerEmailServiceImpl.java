package com.dhlk.subcontract.service.impl;

import com.dhlk.domain.Result;
import com.dhlk.entity.sub.ServerEmail;
import com.dhlk.subcontract.dao.ServerEmailDao;
import com.dhlk.subcontract.service.ServerEmailService;
import com.dhlk.utils.ResultUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 服务端邮箱(ServerEmail)表服务实现类
 *
 * @author makejava
 * @since 2021-03-15 14:13:21
 */
@Service("serverEmailService")
public class ServerEmailServiceImpl implements ServerEmailService {
    @Resource
    private ServerEmailDao serverEmailDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Result queryById(Integer id) {
        return ResultUtils.success(this.serverEmailDao.queryById(id));
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<ServerEmail> queryAllByLimit(int offset, int limit) {
        return this.serverEmailDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param serverEmail 实例对象
     * @return 实例对象
     */
    @Override
    public ServerEmail insert(ServerEmail serverEmail) {
        this.serverEmailDao.insert(serverEmail);
        return serverEmail;
    }

    /**
     * 修改数据
     *
     * @param serverEmail 实例对象
     * @return 实例对象
     */
    @Override
    public Result update(ServerEmail serverEmail) {
        int update = this.serverEmailDao.update(serverEmail);
        return update>0?ResultUtils.success():ResultUtils.failure();
    }

    /**
     * 通过主键删除数据
     *
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.serverEmailDao.deleteById(id) > 0;
    }
}
