package com.dhlk.subcontract.web.service.fbk;


import com.dhlk.domain.Result;
import com.dhlk.entity.basicmodule.ProjectCheck;
import com.dhlk.entity.basicmodule.ProjectDeliveryVo;
import com.dhlk.entity.basicmodule.ProjectProgressVo;
import com.dhlk.subcontract.web.service.ProjectProgressService;
import com.dhlk.utils.ResultUtils;
import org.springframework.stereotype.Service;

@Service
public class ProjectProgressServiceFbk implements ProjectProgressService {
    @Override
    public Result findList(Integer projectId) {
        return ResultUtils.error("请刷新页面");
    }

    @Override
    public Result addOne(ProjectProgressVo projectProgressVo) {
        return ResultUtils.error("请刷新页面");
    }

    @Override
    public Result addDelivery(ProjectDeliveryVo projectDeliveryVo) {
        return ResultUtils.error("请刷新页面");
    }

    @Override
    public Result findAllById(Integer projectId) {
        return ResultUtils.error("请刷新页面");
    }

    @Override
    public Result check(ProjectCheck projectCheck) {
        return ResultUtils.error("请刷新页面");
    }
}
