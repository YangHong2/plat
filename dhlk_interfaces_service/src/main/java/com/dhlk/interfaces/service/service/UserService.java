package com.dhlk.interfaces.service.service;

import com.dhlk.domain.Result;
import com.dhlk.entity.basicmodule.User;

import java.util.Map;
import java.util.Set;

/**
 * 用户管理
 */
public interface UserService {
    /**
     * 分页查询
     * 只查询普通用户
     * @param name 姓名
     */
    Result findList(String name);


    /**
     * 根据用户id查用户信息
     * @param id
     */
    Result findUserById(String id);
}
