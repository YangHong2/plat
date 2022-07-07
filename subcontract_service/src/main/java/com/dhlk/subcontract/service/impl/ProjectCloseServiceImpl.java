package com.dhlk.subcontract.service.impl;

import cn.hutool.core.date.DateTime;
import com.dhlk.domain.Result;
import com.dhlk.entity.sub.ProjectClose;
import com.dhlk.enums.ResultEnum;
import com.dhlk.subcontract.dao.ProjectCloseDao;
import com.dhlk.subcontract.service.ProjectCloseService;
import com.dhlk.subcontract.service.ProjectIssueService;
import com.dhlk.utils.CheckUtils;
import com.dhlk.utils.DateUtils;
import com.dhlk.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 关闭项目(ProjectClose)表服务实现类
 *
 * @author xkliu
 * @since 2021-03-12 09:21:15
 */
@Service("projectCloseService")
public class ProjectCloseServiceImpl  implements ProjectCloseService {

    @Autowired
    private ProjectCloseDao projectCloseDao;
    @Resource
    private ProjectIssueService projectIssueService;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ProjectClose queryById(Integer id) {
        return this.projectCloseDao.queryById(id);
    }



    /**
     * 新增数据
     *
     * @param projectClose 实例对象
     * @return 实例对象
     */
    @Override
    public ProjectClose insert(ProjectClose projectClose) {
        this.projectCloseDao.insert(projectClose);
        return projectClose;
    }

    /**
     * 修改数据
     *
     * @param projectClose 实例对象
     * @return 实例对象
     */
    @Override
    public ProjectClose update(ProjectClose projectClose) {
        this.projectCloseDao.update(projectClose);
        return this.queryById(projectClose.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.projectCloseDao.deleteById(id) > 0;
    }

    @Override
    @Transactional
    public Result save(ProjectClose projectClose) {
        Integer flag = 0;
        //新增
        if (CheckUtils.isNull(projectClose.getId())) {
            projectClose.setApplyTime(new DateTime());
            flag = projectCloseDao.insert(projectClose);
        } else {
            //修改
            projectClose.setApprovalTime(DateUtils.getCurrentTime());
            flag = projectCloseDao.update(projectClose);
        }
        if (flag > 0){
            if (projectClose.getAuditResult()==1){
                //将项目修改为关闭状态
                projectIssueService.upDataByprogress(projectClose.getProjectId(),10);
            }    if (projectClose.getAuditResult()==0){
                //将项目修改为关闭状态
                projectIssueService.upDataByprogress(projectClose.getProjectId(),11);
            }
        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    public Result findList(String name, Integer pageNum, Integer pageSize) {
        if (name ==null||name==""){
            if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
                return ResultUtils.error(ResultEnum.PARAM_ERR);
            }
            PageHelper.startPage(pageNum, pageSize);
            List<ProjectClose> lists = projectCloseDao.findAll();
            PageInfo<ProjectClose> projectClose = new PageInfo<>(lists);
            return ResultUtils.success(projectClose);
        }else {
            if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
                return ResultUtils.error(ResultEnum.PARAM_ERR);
            }
            PageHelper.startPage(pageNum, pageSize);
            List<ProjectClose> lists = projectCloseDao.findList(name);
//            List<ProjectClose> lists = projectCloseDao.findAll();
            PageInfo<ProjectClose> projectClose = new PageInfo<>(lists);
            return ResultUtils.success(projectClose);
        }
    }


}
