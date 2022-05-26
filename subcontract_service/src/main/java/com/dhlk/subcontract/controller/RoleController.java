package com.dhlk.subcontract.controller;


import com.dhlk.entity.sub.Role;
import com.dhlk.subcontract.service.RoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 角色管理(Role)表控制层
 *
 * @author xkliu
 * @since 2021-03-12 09:23:45
 */
@RestController
@RequestMapping("role")
public class RoleController {
    /**
     * 服务对象
     */
    @Resource
    private RoleService roleService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Role selectOne(Integer id) {
        return this.roleService.queryById(id);
    }

}
