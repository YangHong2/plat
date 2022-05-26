package com.dhlk.subcontract.controller;


import com.dhlk.entity.sub.RoleMenu;
import com.dhlk.subcontract.service.RoleMenuService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 角色菜单关系(RoleMenu)表控制层
 *
 * @author xkliu
 * @since 2021-03-12 09:23:53
 */
@RestController
@RequestMapping("roleMenu")
public class RoleMenuController {
    /**
     * 服务对象
     */
    @Resource
    private RoleMenuService roleMenuService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public RoleMenu selectOne(Integer id) {
        return this.roleMenuService.queryById(id);
    }

}
