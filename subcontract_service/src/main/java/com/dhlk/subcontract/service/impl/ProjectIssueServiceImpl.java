package com.dhlk.subcontract.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dhlk.domain.Result;
import com.dhlk.entity.sub.ConsultingProvider;
import com.dhlk.entity.sub.DevProduce;
import com.dhlk.entity.sub.FinancialProvider;
import com.dhlk.entity.sub.ProjectIssue;
import com.dhlk.enums.ResultEnum;
import com.dhlk.subcontract.dao.*;
import com.dhlk.subcontract.dao.vo.ProjectIssueVO;
import com.dhlk.subcontract.dao.vo.ProjectRecordsVO;
import com.dhlk.subcontract.service.GoldService;
import com.dhlk.subcontract.service.ProjectIssueService;
import com.dhlk.utils.CheckUtils;
import com.dhlk.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
    @Resource
    private CompanyDao companyDao;

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
            projectIssue.setProgressInt(2);
            projectIssue.setProgressString("项目发布");
            flag = projectIssueDao.insert(projectIssue);
        } else {
            ProjectIssue issue = projectIssueDao.queryById(projectIssue.getId());
            if (issue == null) {
                return ResultUtils.error("项目不存在!");
            }
            //修改
            flag = projectIssueDao.update(projectIssue);
        }
/*        if (flag > 0) {
            //项目发布完扣减1000金币
            goldService.updateGoldByUserId(projectIssue.getCompanyId(), -1000.0);
        }*/
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
        List<ProjectIssue> relevance = projectIssueDao.findRelevance(id, companyId);
        return ResultUtils.success(relevance);
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

    @Override
    public Result findName(String name, Integer pageNum, Integer pageSize) {
        if (name == null || name == "") {
            PageHelper.startPage(pageNum, pageSize);
            List<ProjectIssue> list = projectIssueDao.findName(name);
            List<ProjectIssueVO> listVo = new ArrayList<>();
            for (ProjectIssue projectIssue : list) {
                ProjectIssueVO projectIssueVO = new ProjectIssueVO();
                BeanUtils.copyProperties(projectIssue, projectIssueVO);
                projectIssueVO.setCompanyName(companyDao.queryById(projectIssue.getCompanyId()).getCompanyName());
                listVo.add(projectIssueVO);
            }
            PageInfo<ProjectIssueVO> projectCloseVo = new PageInfo<ProjectIssueVO>(listVo);
            return ResultUtils.success(projectCloseVo);
        } else {
            PageHelper.startPage(pageNum, pageSize);
            List<ProjectIssue> list = projectIssueDao.findName(name);
            List<ProjectIssueVO> listVo = new ArrayList<>();
            for (ProjectIssue projectIssue : list) {
                ProjectIssueVO projectIssueVO = new ProjectIssueVO();
                BeanUtils.copyProperties(projectIssue, projectIssueVO);
                projectIssueVO.setCompanyName(companyDao.queryById(projectIssue.getCompanyId()).getCompanyName());
                listVo.add(projectIssueVO);
            }
            PageInfo<ProjectIssueVO> projectCloseVo = new PageInfo<ProjectIssueVO>(listVo);
            return ResultUtils.success(projectCloseVo);
        }
    }

    /**
     * 查询项目流程
     *
     * @param projectName
     * @return
     */
    @Override
    public Result projectRecords(String projectName, Integer id) {
        if (projectName == "" || projectName == null) {
            return ResultUtils.failure();
        }
        ProjectIssue projectIssue = projectIssueDao.queryById(id);
        ProjectRecordsVO projectRecordsVO = new ProjectRecordsVO();
        projectRecordsVO.setProjectName(projectName);
        projectRecordsVO.setProjectCreateTime(projectIssue.getCreateTime());
        List<FinancialProvider> financialProvider = financialProviderDao.findByProjectId(id);
        if (financialProvider != null && !(financialProvider.size() == 0)) {
            projectRecordsVO.setProviderName(financialProvider.get(0).getProviderName());
            projectRecordsVO.setProviderCreateTime(financialProvider.get(0).getCreateTime());
        }
        List<ConsultingProvider> consultingProvider = consultingProviderDao.findByProjectId(id);
        if (consultingProvider != null && !(consultingProvider.size() == 0)) {
            projectRecordsVO.setCompanyName(consultingProvider.get(0).getCompanyName());
            projectRecordsVO.setCompanyCreateTime(consultingProvider.get(0).getCreateTime());
        }
        return ResultUtils.success(projectRecordsVO);
    }

    /**
     * 修改项目进度
     *
     * @return
     */
    @Override
    public boolean upDataByprogress(Integer id,Integer progressInt) {
        ProjectIssue projectIssue = new ProjectIssue();
        projectIssue.setId(id);
        String progressString ="";
        if (progressInt==1){
            progressString="项目发布";
        }else if (progressInt==2){
            progressString="招募投资中";
        }else if (progressInt==3){
            progressString="招募施工中";
        }else if (progressInt==4){
            progressString="首付款中";
        }else if (progressInt==5){
            progressString="正在施工中";
        }else if (progressInt==6){
            progressString="正在交付中";
        }else if (progressInt==7){
            progressString="正在交易中";
        }else if (progressInt==8){
            progressString="付尾款中";
        }else if (progressInt==9){
            progressString="进行回款中";
        }else if (progressInt==10){
            progressString="项目已关闭";
        }else if(progressInt==11){
            progressString="项目关闭中";
        }
        projectIssue.setProgressInt(progressInt);
        projectIssue.setProgressString(progressString);
        Integer update = projectIssueDao.update(projectIssue);
        return update > 0;
    }
}
