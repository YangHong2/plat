package com.dhlk.subcontract.dao;



import com.dhlk.entity.sub.UserRole;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PermissionsDao extends Repository {

    UserRole selectRoleByUserId(Integer userId);
    Integer deleteByUserId(Integer userId,Integer roleId);

    Integer saveUserRole(Integer userId,Integer roleId);

    List<UserRole> selectByAll();

}
