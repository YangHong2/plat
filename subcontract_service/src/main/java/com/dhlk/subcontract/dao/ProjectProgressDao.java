package com.dhlk.subcontract.dao;


import com.dhlk.entity.sub.ProjectProgress;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectProgressDao {



    List<ProjectProgress> findAll(Integer projectId);

    Integer addOne(ProjectProgress projectProgress);
}
