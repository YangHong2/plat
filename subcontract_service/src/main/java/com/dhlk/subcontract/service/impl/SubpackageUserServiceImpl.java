package com.dhlk.subcontract.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhlk.domain.Result;
import com.dhlk.entity.basicmodule.User;
import com.dhlk.entity.sub.Company;
import com.dhlk.entity.sub.SubpackageUser;
import com.dhlk.service.RedisService;
import com.dhlk.subcontract.dao.CompanyDao;
import com.dhlk.subcontract.dao.SubpackageUserDao;
import com.dhlk.subcontract.service.SubpackageUserService;
import com.dhlk.subcontract.util.HeaderUtil;
import com.dhlk.systemconst.Const;
import com.dhlk.utils.CheckUtils;
import com.dhlk.utils.EncryUtils;
import com.dhlk.utils.ResultUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.lang.System.out;

/**
 * 用户表(SubpackageUser)表服务实现类
 *
 * @author xkliu
 * @since 2021-03-12 09:28:47
 */
@Service("subpackageUserService")
public class SubpackageUserServiceImpl extends ServiceImpl<SubpackageUserDao, SubpackageUser> implements SubpackageUserService {
    @Resource
    private SubpackageUserDao subpackageUserDao;
    @Autowired
    private CompanyDao companyDao;
    @Autowired
    private RedisService redisService;
    @Autowired
    private HeaderUtil headerUtil;

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
    public Result save1(SubpackageUser subpackageUser) {
         SubpackageUser userinfo = headerUtil.getUserinfo();

        if (subpackageUserDao.isRepeatLoginName(subpackageUser) > 0) {
            return ResultUtils.error("账号已存在");
        }
        // redisServiceImpl.set(Const.TOKEN_CACHE_ITEM_PREFIX +token,userInfo,loseTime);
        if (subpackageUserDao.isRepeatCompanyEmail(subpackageUser) > 0) {
            return ResultUtils.error("邮箱已存在");
        }
        int flag = -1;
        if (!CheckUtils.isNull(subpackageUser.getPassword())) {
            subpackageUser.setPassword(EncryUtils.md5(subpackageUser.getPassword(), Const.SIGN));
        }
        if (CheckUtils.isNull(subpackageUser.getId())) {
            flag = subpackageUserDao.insert(subpackageUser);

        } else {
//            flag = subpackageUserDao.update(subpackageUser);
            flag = companyDao.update(subpackageUser.getCompany());
//            flag =  companyDao.updateById(subpackageUser.getCompany());
        }
//        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
        subpackageUser.setCompanyId(userinfo.getCompanyId());
        return flag > 0 ? ResultUtils.success(subpackageUser) : ResultUtils.failure();

    }

    @Override
    public Result findList(String companyName, Integer auditStatus, Integer isBlacklist, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<SubpackageUser> subpackageUsers = subpackageUserDao.findList(companyName, auditStatus, isBlacklist);
        PageInfo<SubpackageUser> pageList = new PageInfo<>(subpackageUsers);
        return ResultUtils.success(pageList);
    }

    @Override
    public Map<String, Set> getPermissionsByLoginName(User user) {
        return null;
    }
}
