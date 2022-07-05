package com.dhlk.subcontract.service.impl;

import com.dhlk.domain.Result;
import com.dhlk.entity.sub.ProjectProgress;
import com.dhlk.subcontract.dao.ProjectProgressDao;
import com.dhlk.subcontract.dao.vo.ProjectProgressVo;
import com.dhlk.subcontract.service.ProjectProgressService;
import com.dhlk.utils.CheckUtils;
import com.dhlk.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class ProjectProgressServiceImpl implements ProjectProgressService {

    @Autowired
    private ProjectProgressDao projectProgressDao;

    @Override
    public Result findList(Integer projectId) {
        if (CheckUtils.isNull(projectId)){
            return ResultUtils.error("参数异常");
        }
        List<ProjectProgress> all = projectProgressDao.findAll(projectId);
        return ResultUtils.success(all);
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
}
