package com.dhlk.subcontract.service.impl;

import com.dhlk.domain.Result;
import com.dhlk.entity.sub.SubpackageUser;
import com.dhlk.subcontract.dao.SubpackageUserDao;
import com.dhlk.subcontract.service.SubpackageUserService;
import com.dhlk.systemconst.Const;
import com.dhlk.utils.CheckUtils;
import com.dhlk.utils.EncryUtils;
import com.dhlk.utils.ResultUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户表(SubpackageUser)表服务实现类
 *
 * @author xkliu
 * @since 2021-03-12 09:28:47
 */
@Service("subpackageUserService")
public class SubpackageUserServiceImpl implements SubpackageUserService {
    @Resource
    private SubpackageUserDao subpackageUserDao;
    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.subpackageUserDao.deleteById(id) > 0;
    }

    @Override
    public Result save(SubpackageUser subpackageUser) {
        if(subpackageUserDao.isRepeatLoginName(subpackageUser) > 0){
            return ResultUtils.error("账号已存在");
        }
        if(subpackageUserDao.isRepeatCompanyEmail(subpackageUser) > 0){
            return ResultUtils.error("邮箱已存在");
        }
        int flag = -1;
        if(!CheckUtils.isNull(subpackageUser.getPassword())){
            subpackageUser.setPassword(EncryUtils.md5(subpackageUser.getPassword(), Const.SIGN));
        }
        if(CheckUtils.isNull(subpackageUser.getId())){
            flag = subpackageUserDao.insert(subpackageUser);
        }else{
            flag = subpackageUserDao.update(subpackageUser);
        }
        return flag > 0?ResultUtils.success():ResultUtils.failure();
    }

    @Override
    public Result findList(String companyName, Integer auditStatus,Integer isBlacklist, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<SubpackageUser> subpackageUsers = subpackageUserDao.findList(companyName,auditStatus,isBlacklist);
        PageInfo<SubpackageUser> pageList = new PageInfo<>(subpackageUsers);
        return ResultUtils.success(pageList);
    }
}
