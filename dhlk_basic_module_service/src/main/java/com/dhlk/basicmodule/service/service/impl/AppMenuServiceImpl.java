package com.dhlk.basicmodule.service.service.impl;

import com.dhlk.basicmodule.service.dao.AppMenuDao;
import com.dhlk.basicmodule.service.dao.AppPermissionsDao;
import com.dhlk.basicmodule.service.dao.AppTenantDao;
import com.dhlk.basicmodule.service.service.AppMenuService;
import com.dhlk.domain.Result;
import com.dhlk.domain.Tree;
import com.dhlk.entity.app.AppMenu;
import com.dhlk.entity.app.AppPermissions;
import com.dhlk.entity.app.AppTenant;
import com.dhlk.entity.app.StoreAppInfo;
import com.dhlk.entity.basicmodule.User;
import com.dhlk.utils.CheckUtils;
import com.dhlk.utils.ResultUtils;
import com.dhlk.utils.TreeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AppMenuServiceImpl implements AppMenuService {
    @Autowired
    private AppTenantDao appTenantDao;
    @Autowired
    private AppMenuDao appMenuDao;
    @Autowired
    private AppPermissionsDao appPermissionsDao;

    @Override
    public Result addAppTenant(List<AppTenant> appTenants) {
        if(CheckUtils.isNull(appTenants)){
            return ResultUtils.error("参数错误");
        }
        Set<Integer> set = new HashSet<>();
        for (AppTenant appTenant:appTenants) {
            set.add(appTenant.getTenantId());
        }
        appTenantDao.delete(set);
        Integer insert = appTenantDao.insert(appTenants);
        return insert >= 0?ResultUtils.success():ResultUtils.failure();
    }

    @Override
    public Result findListAppChecked(Integer tenantId) {
        return ResultUtils.success(appTenantDao.findListChecked(tenantId));
    }

    @Override
    public Result findListApp(Integer tenantId) {
        return  ResultUtils.success(appTenantDao.findList(tenantId));
    }

    @Override
    public Result findListByCode(Integer tenantId) {
        return ResultUtils.success(appMenuDao.findListByCode(tenantId));
    }

    @Override
    public Result findListRoleChecked(Integer roleId, Integer tenantId) {
        List<StoreAppInfo> apps = appTenantDao.findList(tenantId);
        List<AppMenu> appMenus = appMenuDao.findListChecked(roleId);
        List<Map<String,Object>> resultList = new ArrayList<>();
        for (StoreAppInfo storeAppInfo:apps) {
            List<AppMenu> appMenuList = new ArrayList<>();
            for (AppMenu appMenu:appMenus) {
                if(!CheckUtils.isNull(appMenu.getAppCode()) && appMenu.getAppCode().equals(storeAppInfo.getAppCode())){
                    appMenuList.add(appMenu);
                }
            }
            //过滤选中的菜单id
            List<String> ids = appMenuList.stream().filter(AppMenu::getChecked).map(menu -> menu.getId().toString()).collect(Collectors.toList());
            //构建菜单树
            List<Tree<AppMenu>> trees = new ArrayList<>();
            buildTrees(trees, appMenuList);
            Tree<AppMenu> menuTree = TreeUtil.build(trees);
            //封装结果集并返回前端
            Map<String,Object> result=new HashMap<String,Object>();
            result.put("total",appMenuList.size());
            result.put("ids",ids);
            result.put("rows",menuTree);
            result.put("appName",storeAppInfo.getAppName());
            resultList.add(result);
        }
        return ResultUtils.success(resultList);
    }

    @Override
    public Result addAppPermissions(Integer roleId,String menuIds) {
        if(CheckUtils.isNull(roleId)){
            return ResultUtils.error("参数错误");
        }
        //默认超级管理员的角色id为1
        if (roleId == 1) {
            return ResultUtils.error("超级管理员权限禁止修改!");
        } else {
            //先根据角色id删除对应的app权限
            Integer res = appPermissionsDao.deleteByRoleId(roleId);
            //保存角色拥有的权限
            if (res >= 0 && !CheckUtils.isNull(menuIds)) {
                List<AppPermissions> permissionsList = new ArrayList<>();
                Arrays.stream(menuIds.split(",")).forEach(s -> permissionsList.add(new AppPermissions(roleId, Integer.parseInt(s))));
                Integer res1 = appPermissionsDao.insert(permissionsList);
            }
            return ResultUtils.success();
        }
    }


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
