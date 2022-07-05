package com.dhlk.subcontract.service;

import com.dhlk.domain.Result;
import com.dhlk.entity.sub.ProjectProgress;
import com.dhlk.subcontract.dao.vo.ProjectProgressVo;

public interface ProjectProgressService {


    Result findList(Integer projectId);

    Result addOne(ProjectProgressVo projectProgressVo);


}
