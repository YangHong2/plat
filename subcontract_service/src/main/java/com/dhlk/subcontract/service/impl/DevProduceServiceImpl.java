package com.dhlk.subcontract.service.impl;

import com.dhlk.domain.Result;
import com.dhlk.entity.sub.DevProduce;
import com.dhlk.subcontract.dao.DevProduceDao;
import com.dhlk.subcontract.service.DevProduceService;
import com.dhlk.utils.CheckUtils;
import com.dhlk.utils.ResultUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * 开发生产(DevProduce)表服务实现类
 *
 * @author xkliu
 * @since 2021-03-12 09:21:04
 */
@Service
public class DevProduceServiceImpl implements DevProduceService {
    @Resource
    private DevProduceDao devProduceDao;


    /**
     * 新增数据
     *
     * @param devProduce 实例对象
     * @return 实例对象
     */
    @Override
    public Result save(DevProduce devProduce) {
        int flag = 0;
        if (devProduce != null && devProduce.getId() != null) {
            flag = devProduceDao.update(devProduce);
        } else {
            flag = devProduceDao.insert(devProduce);
        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }


    /**
     * 通过主键删除数据
     *
     * @param ids 主键
     * @return 是否成功
     */
    @Override
    public Result delete(String ids) {
        if (CheckUtils.isNull(ids)) {
            return ResultUtils.failure();
        }
        return this.devProduceDao.delete(Arrays.asList(ids.split(","))) > 0 ? ResultUtils.success() : ResultUtils.failure();
    }
}
