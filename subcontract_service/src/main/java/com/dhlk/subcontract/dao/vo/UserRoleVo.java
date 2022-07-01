package com.dhlk.subcontract.dao.vo;


import lombok.Data;

import java.io.Serializable;

@Data
public class UserRoleVo implements Serializable {

    private Integer userId;

    private String userName;

    private Integer roleId;

    private String roleName;

}
