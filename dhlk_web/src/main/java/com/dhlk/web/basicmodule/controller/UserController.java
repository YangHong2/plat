package com.dhlk.web.basicmodule.controller;

import com.dhlk.entity.basicmodule.User;
import com.dhlk.web.basicmodule.service.UserService;
import com.dhlk.domain.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.dhlk.utils.ResultUtils;

import javax.validation.Valid;


/**
* 用户管理
*/
@RestController
@RequestMapping(value = "/user")
@Api(value = "UserController",description = "用户管理")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 保存
     * @param
     * @return
     */
    @ApiOperation("新增/修改")
    @PostMapping(value = "/save")
    public Result save(@ApiParam(name="用户对象",value="传入json格式") @Valid @RequestBody User user, BindingResult bindingResult) {
        Result result = ResultUtils.loadResult(bindingResult);
        if (result==null) {
            return userService.save(user);
        }
        return result;
    }
    /**
     * 删除
     * @param ids
     * @return result
     */
    @ApiOperation("批量删除")
    @GetMapping(value = "/delete")
    public Result delete(@ApiParam(name="ids",value="数据id,多个时用逗号隔开，如 1,2",required=true) @RequestParam(value = "ids") String ids) {
        return userService.delete(ids);
    }

    /**
    * 列表查询
     * @param name name为模糊查询，name如果没有或者为空就是全表扫描
    * @return result
    */
    @ApiOperation("根据用户名称查询")
    @GetMapping("/findList")
    public Result findList(@ApiParam(name="name",value="用户名称模糊查询") @RequestParam(value = "name", required = false) String name) {
        return  userService.findList(name);
    }
    /**
     * 修改密码
     * @param id
     * @param password
     * @return result
     */
    @ApiOperation("修改密码")
    @GetMapping("/updatePassword")
    public Result updatePassword(@RequestParam(value = "id") Integer id,
                                 @RequestParam(value = "password") String password) {
        return  userService.updatePassword(id,password);
    }

    /**
     * 查询用户所属的机构
     * @param id
     * @return result
     */
    @ApiOperation("查询用户所属的机构")
    @GetMapping("/findOrg")
    public Result updatePassword(@RequestParam(value = "id") Integer id) {
        return  userService.findOrg(id);
    }

    /**
     * 改变用户状态
     * @param id
     * @return result
     */
    @ApiOperation("禁用/启用")
    @GetMapping("/isEnable")
    public Result isEnable(@RequestParam(value = "id") Integer id,
                           @RequestParam(value = "status") Integer status) {
        return  userService.isEnable(id,status);
    }

    /**
     * 根据租户Id查询列表
     * @return result
     */
    @ApiOperation("根据租户Id查询")
    @GetMapping("/findListByTenantId")
    public Result findListByTenantId(@RequestParam(value = "tenantId", required = false) String tenantId) {
        return  userService.findListByTenantId(tenantId);
    }

    /**
     * 获取用户角色机构信息
     * @param userId
     * @param token
     * @return
     */
    @ApiOperation("获取用户角色机构信息")
    @GetMapping("/findUserRoleOrg")
    public Result findUserRoleOrg(@RequestParam(value = "userId",required = true) Integer userId,
                                  @RequestParam(value = "token",required = true) String token){
        return userService.findUserRoleOrg(userId,token);
    }
}
