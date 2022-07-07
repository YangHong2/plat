package com.dhlk.subcontract.service;

import com.dhlk.domain.Result;
import com.dhlk.entity.basicmodule.ProjectCheck;
import com.dhlk.entity.basicmodule.ProjectDeliveryVo;
import com.dhlk.entity.sub.ProjectDelivery;
import com.dhlk.entity.sub.ProjectProgress;
import com.dhlk.subcontract.dao.vo.ProjectProgressVo;

public interface ProjectProgressService {


    Result findAllById(Integer projectId);

    Result findList(Integer projectId);

    Result addOne(ProjectProgressVo projectProgressVo);


    Result addDelivery(ProjectDeliveryVo projectDeliveryVo);

    Result check(ProjectCheck projectCheck);

}
