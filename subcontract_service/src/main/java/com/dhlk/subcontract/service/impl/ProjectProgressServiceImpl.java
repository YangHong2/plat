package com.dhlk.subcontract.service.impl;

import com.dhlk.domain.Result;
import com.dhlk.entity.basicmodule.ProjectCheck;
import com.dhlk.entity.basicmodule.ProjectDeliveryVo;
import com.dhlk.entity.basicmodule.ProjectProgressDto;
import com.dhlk.entity.sub.ProjectDelivery;
import com.dhlk.entity.sub.ProjectProgress;
import com.dhlk.subcontract.dao.ProjectDeliveryDao;
import com.dhlk.subcontract.dao.ProjectProgressDao;
import com.dhlk.subcontract.dao.vo.ProjectProgressVo;
import com.dhlk.subcontract.service.ProjectProgressService;
import com.dhlk.utils.CheckUtils;
import com.dhlk.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class ProjectProgressServiceImpl implements ProjectProgressService {

    @Autowired
    private ProjectProgressDao projectProgressDao;

    @Autowired
    private ProjectDeliveryDao projectDeliveryDao;


    @Override
    public Result findList(Integer projectId) {
        if (CheckUtils.isNull(projectId)){
            return ResultUtils.error("参数异常");
        }
        List<ProjectProgress> all = projectProgressDao.findAll(projectId);
        if (all!=null){
            List<ProjectProgressDto> allDto = new ArrayList<>();
            for (ProjectProgress projectProgress : all) {
                ProjectProgressDto projectProgressDto = new ProjectProgressDto();
                projectProgressDto.setId(projectProgress.getId());
                projectProgressDto.setProjectId(projectProgress.getProjectId());
                projectProgressDto.setProjectDescribed(projectProgress.getProjectDescribed());
                projectProgressDto.setPath(projectProgress.getPath());
                projectProgressDto.setPlan(projectProgress.getPlan());
                Date createTime = projectProgress.getCreateTime();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String format = dateFormat.format(createTime);
                projectProgressDto.setCreateTime(format);
                allDto.add(projectProgressDto);
            }
            return ResultUtils.success(allDto);
        }
        return ResultUtils.error("项目无进度");
    }

    @Override
    public Result addOne(ProjectProgressVo projectProgressVo) {
        Integer projectId = projectProgressVo.getProjectId();
        if (projectId==null){
            return ResultUtils.error("参数异常");
        }
        ProjectProgress projectProgress = new ProjectProgress();
        projectProgress.setProjectId(projectProgressVo.getProjectId());
        projectProgress.setProjectDescribed(projectProgressVo.getProjectDescribed());
        projectProgress.setPath(projectProgressVo.getPath());
        projectProgress.setPlan(projectProgressVo.getPlan());
        projectProgress.setCreateTime(new Date());
        Integer addOne = projectProgressDao.addOne(projectProgress);
        if (addOne>=1){
            return ResultUtils.success();
        }
        return ResultUtils.error("添加失败");
    }

    @Override
    public Result addDelivery(ProjectDeliveryVo projectDeliveryVo) {
        if (projectDeliveryVo.getProjectId()==null){
            return ResultUtils.error("参数错误");
        }
        List<ProjectDelivery> allByProjectId = projectDeliveryDao.findAllByProjectId(projectDeliveryVo.getProjectId());
        if (allByProjectId.size()!=0){
            int i = projectDeliveryDao.deleteById(projectDeliveryVo.getProjectId());
            if (i==0){
                return ResultUtils.error("参数异常");
            }
        }
        ProjectDelivery projectDelivery =new ProjectDelivery();
        projectDelivery.setProjectId(projectDeliveryVo.getProjectId());
        projectDelivery.setProjectName(projectDeliveryVo.getProjectName());
        projectDelivery.setProjectPath(projectDeliveryVo.getProjectPath());
        projectDelivery.setProjectRemark(projectDeliveryVo.getProjectRemark());
        projectDelivery.setProjectStatus(0);
        int save = projectDeliveryDao.save(projectDelivery);
        if (save==0){
            return ResultUtils.error("添加失败");
        }

        return ResultUtils.success();
    }

    @Override
    public Result check(ProjectCheck projectCheck) {
        if (projectCheck.getProjectId()==null){
            return ResultUtils.error("参数错误");
        }
        if (projectCheck.getProjectStatus()==0||projectCheck.getProjectStatus()>3){
            return ResultUtils.error("审核结果有误");
        }
        int i = projectDeliveryDao.updateByProjectId(projectCheck);
        if (i>0){
            return ResultUtils.success();
        }
        return ResultUtils.error("审核操作失败");
    }

    @Override
    public Result findAllById(Integer projectId) {
        if (projectId==null){
            return ResultUtils.error("参数异常");
        }
        List<ProjectDelivery> allByProjectId = projectDeliveryDao.findAllByProjectId(projectId);
        return ResultUtils.success(allByProjectId);
    }


}
