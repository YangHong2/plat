package com.dhlk.subcontract.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhlk.domain.Result;
import com.dhlk.entity.sub.SubpackageMessage;
import com.dhlk.entity.sub.SubpackageUser;
import com.dhlk.subcontract.dao.SubpackageMessageDao;
import com.dhlk.subcontract.dao.SubpackageUserDao;
import com.dhlk.subcontract.dao.vo.SubpackageMessageVo;
import com.dhlk.subcontract.service.SubpackageMessageService;
import com.dhlk.subcontract.util.HeaderUtil;
import com.dhlk.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 消息(SubpackageMessage)表服务实现类
 *
 * @author xkliu
 * @since 2021-03-12 09:23:59
 */
@Service("subpackageMessageService")
public class SubpackageMessageServiceImpl extends ServiceImpl<SubpackageMessageDao, SubpackageMessage> implements SubpackageMessageService {
    @Resource
    private SubpackageMessageDao subpackageMessageDao;
    @Autowired
    private HeaderUtil headerUtil;
    @Autowired
    private SubpackageUserDao subpackageUserDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Result queryById(Integer id) {
        return ResultUtils.success(this.subpackageMessageDao.queryById(id));
    }

    /**
     * 查询多条数据
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public Result queryAllByLimit(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<SubpackageMessage> subpackageMessages = subpackageMessageDao.checkAll();
        List<SubpackageMessageVo> voList=new ArrayList<>();
        for (SubpackageMessage subpackageMessage : subpackageMessages) {
            SubpackageMessageVo subpackageMessageVo = new SubpackageMessageVo();
            subpackageMessageVo.setId(subpackageMessage.getId());
            subpackageMessageVo.setContent(subpackageMessage.getContent());
            subpackageMessageVo.setCreateTime(subpackageMessage.getCreateTime());
            subpackageMessageVo.setType(subpackageMessage.getType());
            subpackageMessageVo.setUserName(subpackageUserDao.queryById(subpackageMessage.getUserId()).getLoginName());
            subpackageMessageVo.setTitle(subpackageMessage.getTitle());
            voList.add(subpackageMessageVo);
        }
        PageInfo<SubpackageMessageVo> pageInfo = new PageInfo<SubpackageMessageVo>(voList);
        return ResultUtils.success(pageInfo);
//        return ResultUtils.success(this.subpackageMessageDao.queryAllByLimit(offset, limit));
    }

    /**
     * 新增数据
     *
     * @param subpackageMessage 实例对象
     * @return 实例对象
     */
    @Override
    public Result insert(SubpackageMessage subpackageMessage) {
        SubpackageUser userinfo = headerUtil.getUserinfo();
        subpackageMessage.setUserId(userinfo.getId());
        int insert = subpackageMessageDao.insert(subpackageMessage);
        return insert == 1 ? ResultUtils.success(subpackageMessage) : ResultUtils.error("保存失败");
    }

    /**
     * 修改数据
     *
     * @param subpackageMessage 实例对象
     * @return 实例对象
     */
    @Override
    public Result update(SubpackageMessage subpackageMessage) {
        int update = subpackageMessageDao.update(subpackageMessage);
        return update == 1 ? ResultUtils.success(subpackageMessage) : ResultUtils.error("修改失败");
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public Result deleteById(Integer id) {
        int i = subpackageMessageDao.deleteById(id);
        return i == 1 ? ResultUtils.success("删除成功") : ResultUtils.error("删除失败");
    }
}
