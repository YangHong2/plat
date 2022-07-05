package com.dhlk.subcontract.service.impl;

import com.dhlk.domain.Result;
import com.dhlk.entity.sub.ReceivingInfo;
import com.dhlk.subcontract.dao.ReceivingInfoDao;
import com.dhlk.subcontract.dao.vo.ReceivingInfoVo;
import com.dhlk.subcontract.service.CompanyService;
import com.dhlk.subcontract.service.ReceivingInfoService;
import com.dhlk.utils.ResultUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 收付款信息(ReceivingInfo)表服务实现类
 *
 * @author xkliu
 * @since 2021-03-12 09:23:28
 */
@Service("receivingInfoService")
public class ReceivingInfoServiceImpl implements ReceivingInfoService {
    @Resource
    private ReceivingInfoDao receivingInfoDao;
    @Resource
    private CompanyService companyService;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Result queryById(Integer id) {
        ReceivingInfo receivingInfo = this.receivingInfoDao.queryById(id);
        return ResultUtils.success(receivingInfo);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<ReceivingInfo> queryAllByLimit(int offset, int limit) {
        return this.receivingInfoDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param receivingInfo 实例对象
     * @return 实例对象
     */
    @Override
    public Result insert(ReceivingInfo receivingInfo) {
//        receivingInfo.setTime(new Date(System.currentTimeMillis()));
        int insert = receivingInfoDao.insert(receivingInfo);
        return insert > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    /**
     * 修改数据
     *
     * @param receivingInfo 实例对象
     * @return 实例对象
     */
    @Override
    public Result update(ReceivingInfo receivingInfo) {
        int update = receivingInfoDao.update(receivingInfo);
        return update > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.receivingInfoDao.deleteById(id) > 0;
    }

    /**
     * 通过项目ID查询数据
     *
     * @param id
     * @return
     */
    @Override
    public Result selectByProjectId(Integer id) {
        List<ReceivingInfo> list = receivingInfoDao.selectByProjectId(id);
        if (list == null && list.isEmpty()) {
           return ResultUtils.success("暂无数据");
        }
        List<ReceivingInfoVo> voList = new ArrayList<>();
        for (ReceivingInfo receivingInfo : list) {
            ReceivingInfoVo receivingInfoVo = new ReceivingInfoVo();
            BeanUtils.copyProperties(receivingInfo, receivingInfoVo);
            receivingInfoVo.setTime(receivingInfo.getTime().toString());
            receivingInfoVo.setPayer(companyService.queryById1(receivingInfo.getPayer()).getCompanyName());//付款方
            receivingInfoVo.setPayee(companyService.queryById1(receivingInfo.getPayee()).getCompanyName());//收款方
            voList.add(receivingInfoVo);
        }
        return list.size() == 0 ? ResultUtils.success("暂无数据") : ResultUtils.success(voList);
    }
}
