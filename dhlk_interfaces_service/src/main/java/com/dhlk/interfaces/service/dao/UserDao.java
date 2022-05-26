package com.dhlk.interfaces.service.dao;

import com.dhlk.entity.basicmodule.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserDao {
    /**
     * 查询用户列表
     */
    List<User> findList(@Param("name") String name, @Param("tenantId") Integer tenantId);
    /**
     * 登录校验
     */
    User loginCheck(@Param("loginName") String loginName,@Param("password") String password);
    /**
     * 根据登录名查找用户信息
     */
    User findByLoginName(@Param("loginName") String loginName);

    /**
     * 根据用户id查找用户信息
     */
    User findUserById(@Param("id")String id);

    /*
     * 根据机构id查询机构下的用户
     * @param orgId
     * @return
     */
    List<User> findUserByOrgId(Integer orgId);
}
