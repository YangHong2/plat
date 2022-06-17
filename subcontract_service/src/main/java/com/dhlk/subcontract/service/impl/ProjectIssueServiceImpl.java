package com.dhlk.subcontract.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dhlk.domain.Result;
import com.dhlk.entity.sub.ConsultingProvider;
import com.dhlk.entity.sub.DevProduce;
import com.dhlk.entity.sub.FinancialProvider;
import com.dhlk.entity.sub.ProjectIssue;
import com.dhlk.enums.ResultEnum;
import com.dhlk.subcontract.dao.ConsultingProviderDao;
import com.dhlk.subcontract.dao.DevProduceDao;
import com.dhlk.subcontract.dao.FinancialProviderDao;
import com.dhlk.subcontract.dao.ProjectIssueDao;
import com.dhlk.subcontract.service.GoldService;
import com.dhlk.subcontract.service.ProjectIssueService;
import com.dhlk.utils.CheckUtils;
import com.dhlk.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目发布(ProjectIssue)表服务实现类
 *
 * @author xkliu
 * @since 2021-03-12 09:21:17
 */
@Service("projectIssueService")
public class ProjectIssueServiceImpl implements ProjectIssueService {

    @Autowired
    private ProjectIssueDao projectIssueDao;

    @Autowired
    private FinancialProviderDao financialProviderDao;

    @Autowired
    private ConsultingProviderDao consultingProviderDao;

    @Autowired
    private DevProduceDao devProduceDao;

    @Autowired
    private GoldService goldService;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ProjectIssue queryById(Integer id) {
        return this.projectIssueDao.queryById(id);
    }


    @Override
    @Transactional
    public Result save(ProjectIssue projectIssue) {
        Integer flag = 0;
        //新增
        if (CheckUtils.isNull(projectIssue.getId())) {
            flag = projectIssueDao.insert(projectIssue);
        } else {
            ProjectIssue issue = projectIssueDao.queryById(projectIssue.getId());
            if (issue == null) {
                return ResultUtils.error("项目不存在!");
            }
            //修改
            flag = projectIssueDao.update(projectIssue);
        }
        if (flag > 0) {
            //项目发布完扣减1000金币
            goldService.updateGoldByUserId(projectIssue.getCompanyId(), -1000.0);
        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }


    @Override
    public Result findList(String name, Integer type, Integer companyId, Integer pageNum, Integer pageSize) {
        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize) || !CheckUtils.checkId(type) || !CheckUtils.checkId(companyId)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        PageHelper.startPage(pageNum, pageSize);
        //全部
        if (type.equals(0)) {
            List<ProjectIssue> lists = projectIssueDao.findAll(name, companyId);
            lists.forEach(item -> {
                if (CheckUtils.isNull(item.getFinancial())) {
                    item.setGress("招募投资");
                    return;
                }
                if (CheckUtils.isNull(item.getConsulting())) {
                    item.setGress("咨询服务中");
                    return;
                }
                if (CheckUtils.isNull(item.getDelivery())) {
                    item.setGress("开发中");
                    return;
                }
                if (!CheckUtils.isNull(item.getDelivery())) {
                    item.setGress("交付中");
                    return;
                }
                if (!CheckUtils.isNull(item.getProClose())) {
                    item.setGress("已完成");
                    return;
                }
            });
            PageInfo<ProjectIssue> projectIssue = new PageInfo<>(lists);
            return ResultUtils.success(projectIssue);
        }
        //我投资的
        if (type.equals(1)) {
            List<FinancialProvider> lists = financialProviderDao.findInvest(name, companyId);
            lists.forEach(item -> {
                if (CheckUtils.isNull(item.getFinancial())) {
                    item.setGress("招募投资");
                    return;
                }
                if (CheckUtils.isNull(item.getConsulting())) {
                    item.setGress("咨询服务中");
                    return;
                }
                if (CheckUtils.isNull(item.getDelivery())) {
                    item.setGress("开发中");
                    return;
                }
                if (!CheckUtils.isNull(item.getDelivery())) {
                    item.setGress("交付中");
                    return;
                }
                if (!CheckUtils.isNull(item.getProClose())) {
                    item.setGress("已完成");
                    return;
                }
            });
            PageInfo<FinancialProvider> financialProvider = new PageInfo<>(lists);
            return ResultUtils.success(financialProvider);
        }
        //我发布的
        if (type.equals(2)) {
            List<ProjectIssue> lists = projectIssueDao.findIssue(name, companyId);
            lists.forEach(item -> {
                if (CheckUtils.isNull(item.getFinancial())) {
                    item.setGress("招募投资");
                    return;
                }
                if (CheckUtils.isNull(item.getConsulting())) {
                    item.setGress("咨询服务中");
                    return;
                }
                if (CheckUtils.isNull(item.getDelivery())) {
                    item.setGress("开发中");
                    return;
                }
                if (!CheckUtils.isNull(item.getDelivery())) {
                    item.setGress("交付中");
                    return;
                }
                if (!CheckUtils.isNull(item.getProClose())) {
                    item.setGress("已完成");
                    return;
                }
            });
            PageInfo<ProjectIssue> projectIssue = new PageInfo<>(lists);
            return ResultUtils.success(projectIssue);
        }
        //我咨询的
        if (type.equals(3)) {
            List<ConsultingProvider> lists = consultingProviderDao.findAdvisory(name, companyId);
            lists.forEach(item -> {
                if (CheckUtils.isNull(item.getFinancial())) {
                    item.setGress("招募投资");
                    return;
                }
                if (CheckUtils.isNull(item.getConsulting())) {
                    item.setGress("咨询服务中");
                    return;
                }
                if (CheckUtils.isNull(item.getDelivery())) {
                    item.setGress("开发中");
                    return;
                }
                if (!CheckUtils.isNull(item.getDelivery())) {
                    item.setGress("交付中");
                    return;
                }
                if (!CheckUtils.isNull(item.getProClose())) {
                    item.setGress("已完成");
                    return;
                }
            });
            PageInfo<ConsultingProvider> consultingProvider = new PageInfo<>(lists);
            return ResultUtils.success(consultingProvider);
        }
        //我施工的
        if (type.equals(4)) {
            List<DevProduce> lists = devProduceDao.findConstruction(name, companyId);
            lists.forEach(item -> {
                if (CheckUtils.isNull(item.getFinancial())) {
                    item.setGress("招募投资");
                    return;
                }
                if (CheckUtils.isNull(item.getConsulting())) {
                    item.setGress("咨询服务中");
                    return;
                }
                if (CheckUtils.isNull(item.getDelivery())) {
                    item.setGress("开发中");
                    return;
                }
                if (!CheckUtils.isNull(item.getDelivery())) {
                    item.setGress("交付中");
                    return;
                }
                if (!CheckUtils.isNull(item.getProClose())) {
                    item.setGress("已完成");
                    return;
                }
            });
            PageInfo<DevProduce> devProduce = new PageInfo<>(lists);
            return ResultUtils.success(devProduce);
        }
        return ResultUtils.success(new PageInfo<>(new ArrayList<>()));
    }

    @Override
    public Result findRelevance(Integer id, Integer companyId) {
        return ResultUtils.success(projectIssueDao.findRelevance(id, companyId));
    }

    @Override
    public Result findProjectManage(String name, Integer pageNum, Integer pageSize) {
        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<ProjectIssue> lists = projectIssueDao.findProjectManage(name);
        PageInfo<ProjectIssue> projectIssue = new PageInfo<>(lists);
        return ResultUtils.success(projectIssue);
    }

    @Override
    public Result getProjectCount(Integer companyId) {
        if (CheckUtils.isNull(companyId)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        JSONObject json = new JSONObject();
        //投资数量
        List<FinancialProvider> financial = financialProviderDao.findInvest(null, companyId);
        json.put("financial", financial.size());
        //发布数量
        List<ProjectIssue> issue = projectIssueDao.findIssue(null, companyId);
        json.put("issue", issue.size());
        //咨询数量
        List<ConsultingProvider> consulting = consultingProviderDao.findAdvisory(null, companyId);
        json.put("consulting", consulting.size());
        //施工数量
        List<DevProduce> devProduce = devProduceDao.findConstruction(null, companyId);
        json.put("devProduce", devProduce.size());
        return ResultUtils.success(json);

    }


    @Override
    public Result getRelevance(Integer companyId) {
        if (CheckUtils.isNull(companyId)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        return ResultUtils.success(projectIssueDao.getRelevance(companyId));
    }

    @Override
    public Result search(String projectName, Integer projectType, Integer pageNum, Integer pageSize) {
        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<ProjectIssue> lists = projectIssueDao.search(projectName, projectType);
        PageInfo<ProjectIssue> projectIssue = new PageInfo<>(lists);
        return ResultUtils.success(projectIssue);
    }

    @Override
    public Result getProjectIssue(Integer id) {
        if (CheckUtils.isNull(id)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        ProjectIssue projectIssue = projectIssueDao.getProjectIssue(id);
        return ResultUtils.success(projectIssue);
    }
}