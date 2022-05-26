package com.dhlk.subcontract.service.impl;

import com.dhlk.domain.Result;
import com.dhlk.entity.sub.ArticlePub;
import com.dhlk.entity.sub.ProjectClose;
import com.dhlk.enums.ResultEnum;
import com.dhlk.subcontract.dao.ArticlePubDao;
import com.dhlk.subcontract.service.ArticlePubService;
import com.dhlk.utils.CheckUtils;
import com.dhlk.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 文章发布(ArticlePub)表服务实现类
 *
 * @author xkliu
 * @since 2021-03-23 11:14:05
 */
@Service("articlePubService")
public class ArticlePubServiceImpl implements ArticlePubService {
    @Resource
    private ArticlePubDao articlePubDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Result queryById(Integer id) {
        return ResultUtils.success(articlePubDao.queryById(id));
    }


    /**
     * 新增数据
     *
     * @param articlePub 实例对象
     * @return 实例对象
     */
    @Override
    public Result insert(ArticlePub articlePub) {
        Integer flag = 0;
        //新增
        if (CheckUtils.isNull(articlePub.getId())) {
            flag = articlePubDao.insert(articlePub);
        } else {
            ArticlePub pub = articlePubDao.queryById(articlePub.getId());
            if (pub == null) {
                return ResultUtils.error("数据不存在!");
            }
            //修改
            flag = articlePubDao.update(articlePub);
        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }


    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.articlePubDao.deleteById(id) > 0;
    }

    @Override
    public Result findList(String title, Integer pageNum, Integer pageSize) {
        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<ArticlePub> lists = articlePubDao.findList(title);
        PageInfo<ArticlePub> projectClose = new PageInfo<>(lists);
        return ResultUtils.success(projectClose);
    }
}
