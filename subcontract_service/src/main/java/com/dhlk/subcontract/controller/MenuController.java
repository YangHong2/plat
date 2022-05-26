package com.dhlk.subcontract.controller;


import com.dhlk.entity.sub.Menu;
import com.dhlk.subcontract.service.MenuService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 系统菜单(Menu)表控制层
 *
 * @author xkliu
 * @since 2021-03-12 09:21:13
 */
@RestController
@RequestMapping("menu")
public class MenuController {
    /**
     * 服务对象
     */
    @Resource
    private MenuService menuService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Menu selectOne(Integer id) {
        return this.menuService.queryById(id);
    }

}
