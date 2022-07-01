package com.dhlk.subcontract.service;


import com.dhlk.domain.Result;

public interface PermissionsService {


    Result insert(Integer userId,Integer roleId);


    Result findList();

    Result delete(Integer userId, Integer roleId);
}
