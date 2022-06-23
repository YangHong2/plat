package com.dhlk.subcontract.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.dhlk.domain.Result;
import com.dhlk.entity.basicmodule.User;
import com.dhlk.entity.sub.SubpackageUser;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 用户表(SubpackageUser)表服务接口
 *
 * @author xkliu
 * @since 2021-03-12 09:28:46
 */
public interface SubpackageUserService extends IService<SubpackageUser> {

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 新增/修改
     */
    Result save1(SubpackageUser subpackageUser);

    /**
     * 列表查询
     */
    Result findList(String companyName, Integer auditStatus,Integer isBlacklist, Integer pageNum, Integer pageSize);

    Map<String, Set> getPermissionsByLoginName(User user);
}
