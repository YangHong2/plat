package com.dhlk.interfaces.service.dao;

import com.dhlk.entity.basicmodule.Org;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrgDao {

    List<Org> treeList(@Param("status") Integer status, @Param("tenantId") Integer tenantId);
}
