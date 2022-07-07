package com.dhlk.subcontract.dao;

import com.dhlk.entity.basicmodule.ProjectCheck;
import com.dhlk.entity.sub.ProjectDelivery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectDeliveryDao {


    int save(ProjectDelivery projectDelivery);

    List<ProjectDelivery> findAllByProjectId(Integer projectId);

    int updateByProjectId(ProjectCheck projectCheck);

    int deleteById(Integer projectId);
}
