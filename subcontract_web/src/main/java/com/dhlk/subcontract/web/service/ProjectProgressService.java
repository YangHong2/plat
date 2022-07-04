package com.dhlk.subcontract.web.service;

import com.dhlk.domain.Result;
import com.dhlk.entity.basicmodule.ProjectProgressVo;

public interface ProjectProgressService {
    Result findList(Integer projectId);

    Result addOne(ProjectProgressVo projectProgressVo);

}
