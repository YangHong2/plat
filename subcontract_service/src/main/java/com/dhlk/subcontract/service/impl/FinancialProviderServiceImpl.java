package com.dhlk.subcontract.service.impl;

import com.dhlk.domain.Result;
import com.dhlk.entity.sub.FinancialProvider;
import com.dhlk.subcontract.dao.FinancialProviderDao;
import com.dhlk.subcontract.service.FinancialProviderService;
import com.dhlk.subcontract.service.ProjectIssueService;
import com.dhlk.utils.CheckUtils;
import com.dhlk.utils.ResultUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * 金融服务商(FinancialProvider)表服务实现类
 *
 * @author makejava
 * @since 2021-03-15 09:27:23
 */
@Service("financialProviderService")
public class FinancialProviderServiceImpl implements FinancialProviderService {
    @Resource
    private FinancialProviderDao financialProviderDao;
@Resource
private ProjectIssueService projectIssueService;
    /**
     * 新增数据
     *
     * @param financialProvider 实例对象
     * @return 实例对象
     */
    @Override
    public Result save(FinancialProvider financialProvider) {
        int flag = 0;
        if (financialProvider != null && financialProvider.getId() != null) {
            flag = financialProviderDao.update(financialProvider);
        } else {
            flag = financialProviderDao.insert(financialProvider);
        }
        // 进行下一个 招募施工阶段
        if (flag > 0) {
            projectIssueService.upDataByprogress(financialProvider.getProjectId(),3);
        } else {
            projectIssueService.upDataByprogress(financialProvider.getProjectId(),2);
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
        return financialProviderDao.delete(Arrays.asList(ids.split(","))) > 0 ? ResultUtils.success() : ResultUtils.failure();
    }
}
