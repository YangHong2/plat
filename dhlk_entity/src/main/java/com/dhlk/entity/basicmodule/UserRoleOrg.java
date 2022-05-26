package com.dhlk.entity.basicmodule;

import lombok.Data;

import java.util.List;

/**
 * @program: dhlk.tenant.plat
 * @description: 用户信息、用户角色信息、用户机构信息
 * @author: wqiang
 * @create: 2020-09-23 09:43
 **/
@Data
public class UserRoleOrg {

    private User user;
    private List<Role> rolelist;
    private List<Org> orglist;
}
