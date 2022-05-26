package com.dhlk.subcontract.service.impl;

import com.dhlk.domain.Result;
import com.dhlk.entity.sub.Coupons;
import com.dhlk.subcontract.dao.CouponsDao;
import com.dhlk.subcontract.service.CouponsService;
import com.dhlk.util.AuthUserUtil;
import com.dhlk.utils.CheckUtils;
import com.dhlk.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * 优惠卷(Coupons)表服务实现类
 *
 * @author xkliu
 * @since 2021-03-12 09:21:01
 */
@Service("couponsService")
public class CouponsServiceImpl implements CouponsService {
    @Resource
    private CouponsDao couponsDao;

    @Resource
    private AuthUserUtil authUserUtil;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Coupons queryById(Integer id) {
        return this.couponsDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Coupons> queryAllByLimit(int offset, int limit) {
        return this.couponsDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param coupons 实例对象
     * @return 实例对象
     */
    @Override
    public Coupons insert(Coupons coupons) {
        this.couponsDao.insert(coupons);
        return coupons;
    }

    /**
     * 修改数据
     *
     * @param coupons 实例对象
     * @return 实例对象
     */
    @Override
    public Coupons update(Coupons coupons) {
        this.couponsDao.update(coupons);
        return this.queryById(coupons.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.couponsDao.deleteById(id) > 0;
    }

    @Override
    public Result findList(String couponNo, String createStartTime, String createEndTime, String expireStartTime, String expireEndTime, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        PageInfo<Coupons> pageInfo = new PageInfo<>(this.couponsDao.findList(couponNo,createStartTime,createEndTime,expireStartTime,expireEndTime));
        return ResultUtils.success(pageInfo);
    }

    @Override
    public Result save(Coupons coupons) {
        int flag = -1;
        if(CheckUtils.isNull(coupons.getId())){
            coupons.setCouponNo(UUID.randomUUID().toString());
            coupons.setUserId(authUserUtil.userId());
            flag = this.couponsDao.insert(coupons);
        }else{
            Coupons coupons1 = this.couponsDao.queryById(coupons.getId());
            if(!(coupons1 != null && coupons1.getIsDelete() == 0)){
                return ResultUtils.error("该优惠卷不存在");
            }
            flag = this.couponsDao.update(coupons);
        }
        return flag > 0?ResultUtils.success():ResultUtils.failure();
    }
}
