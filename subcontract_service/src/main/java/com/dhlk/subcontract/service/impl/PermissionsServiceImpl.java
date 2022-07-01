package com.dhlk.subcontract.service.impl;

import com.dhlk.domain.Result;
import com.dhlk.entity.sub.Role;
import com.dhlk.entity.sub.SubpackageUser;
import com.dhlk.entity.sub.UserRole;
import com.dhlk.enums.ResultEnum;
import com.dhlk.subcontract.dao.PermissionsDao;
import com.dhlk.subcontract.dao.RoleDao;
import com.dhlk.subcontract.dao.SubpackageUserDao;
import com.dhlk.subcontract.dao.vo.UserRoleVo;
import com.dhlk.subcontract.service.PermissionsService;
import com.dhlk.utils.CheckUtils;
import com.dhlk.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service("permissionsService")
public class PermissionsServiceImpl implements PermissionsService {

    @Autowired
    private PermissionsDao permissionsDao;
    
    
    @Autowired
    private RoleDao roleDao;
    
    @Autowired
    private SubpackageUserDao subpackageUserDao;
    
    @Override
    public Result insert(Integer userId,Integer roleId ) {
        // 非空判断
        if (CheckUtils.isNull(userId)&&CheckUtils.isNull(roleId)){
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        // 查询是否有赋予的角色 如果有删除
        UserRole userRole = permissionsDao.selectRoleByUserId(userId);
        if (userRole!=null){
            permissionsDao.deleteByUserId(userId,roleId);
        }
        permissionsDao.saveUserRole(userId,roleId);
        return ResultUtils.success();
    }

    @Override
    public Result findList() {
        List<UserRole> userRoles = permissionsDao.selectByAll();
        List<UserRoleVo> userRoleVos = new ArrayList<>();
        for (UserRole userRole : userRoles) {
            Integer userId = userRole.getUserId();
            SubpackageUser subpackageUser = subpackageUserDao.queryById(userId);
            String loginName = subpackageUser.getLoginName();
            Integer roleId = userRole.getRoleId();
            Role role = roleDao.queryById(roleId);
            String name = role.getName();
            UserRoleVo userRoleVo = new UserRoleVo();
            userRoleVo.setUserId(userId);
            userRoleVo.setUserName(loginName);
            userRoleVo.setRoleId(roleId);
            userRoleVo.setRoleName(name);
            userRoleVos.add(userRoleVo);
        }
        return ResultUtils.success(userRoleVos);
    }

    @Override
    public Result delete(Integer userId, Integer roleId) {
        // 非空判断
        if (CheckUtils.isNull(userId)||CheckUtils.isNull(roleId)){
            return ResultUtils.error("参数错误");
        }
        Integer integer = permissionsDao.deleteByUserId(userId, roleId);
        if (integer>0){
            return ResultUtils.success();
        }
        return ResultUtils.error("该用户没有该角色");
    }


}
