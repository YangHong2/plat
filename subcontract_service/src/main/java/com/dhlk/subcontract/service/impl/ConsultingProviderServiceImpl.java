package com.dhlk.subcontract.service.impl;

import com.dhlk.domain.Result;
import com.dhlk.entity.sub.ConsultingProvider;
import com.dhlk.subcontract.dao.ConsultingProviderDao;
import com.dhlk.subcontract.service.ConsultingProviderService;
import com.dhlk.utils.CheckUtils;
import com.dhlk.utils.ResultUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;


/**
 * 咨询服务商(ConsultingProvider)表服务实现类
 *
 * @author xkliu
 * @since 2021-03-12 09:21:00
 */
@Service
public class ConsultingProviderServiceImpl implements ConsultingProviderService {

    @Resource
    private ConsultingProviderDao consultingProviderDao;


    /**
     * 新增数据
     *
     * @param consultingProvider 实例对象
     * @return 实例对象
     */
    @Override
    public Result save(ConsultingProvider consultingProvider) {

        int flag = 0;
        if (consultingProvider != null && consultingProvider.getId() != null) {
            flag = consultingProviderDao.update(consultingProvider);

        } else {
            flag = consultingProviderDao.insert(consultingProvider);
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
        return consultingProviderDao.delete(Arrays.asList(ids.split(","))) > 0 ? ResultUtils.success() : ResultUtils.failure();

    }
}
