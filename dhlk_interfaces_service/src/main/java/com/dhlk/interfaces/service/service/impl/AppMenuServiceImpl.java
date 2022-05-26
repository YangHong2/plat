package com.dhlk.interfaces.service.service.impl;

import com.dhlk.domain.Tree;
import com.dhlk.entity.app.AppMenu;
import com.dhlk.entity.basicmodule.User;
import com.dhlk.interfaces.service.dao.AppMenuDao;
import com.dhlk.interfaces.service.service.AppMenuService;
import com.dhlk.utils.CheckUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AppMenuServiceImpl implements AppMenuService {
    @Autowired
    private AppMenuDao appMenuDao;
    @Override
    public Map<String, Set> getPermissionsByLoginName(User user) {
        List<AppMenu> menus = appMenuDao.findMenusByLoginName(user);
        Set<String> perms = new HashSet<>();
        Set<String> codes = new HashSet<>();
        if (!CheckUtils.isNull(menus)) {
            menus.stream().forEach(menu -> {
                perms.add(menu.getPerms());
                codes.add(menu.getCode());
            });
        }
        Map<String, Set> map = new HashMap();
        map.put("perms", perms);
        map.put("codes", codes);
        return map;
    }


    private void buildTrees(List<Tree<AppMenu>> trees, List<AppMenu> menus) {
        menus.forEach(menu->{
            Tree<AppMenu> tree = new Tree<>();
            tree.setId(menu.getId().toString());
            tree.setParentId(menu.getParentId().toString());
            tree.setTitle(menu.getName());
            tree.setChecked(menu.getChecked());
            tree.setType(menu.getType());
            trees.add(tree);
        });
    }
}
