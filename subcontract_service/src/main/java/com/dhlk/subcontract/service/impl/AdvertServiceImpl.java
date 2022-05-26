package com.dhlk.subcontract.service.impl;

import com.dhlk.domain.Result;
import com.dhlk.entity.sub.Advert;
import com.dhlk.enums.ResultEnum;
import com.dhlk.subcontract.dao.AdvertDao;
import com.dhlk.subcontract.service.AdvertService;
import com.dhlk.utils.CheckUtils;
import com.dhlk.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * 广告(Advert)表服务实现类
 *
 * @author wyang
 * @since 2021-03-12 09:20:55
 */
@Service("advertService")
public class AdvertServiceImpl implements AdvertService {
    @Autowired
    private AdvertDao advertDao;

    @Value("${attachment.path}")
    private String attachmentPath;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Result queryById(Integer id) {
        if (CheckUtils.isNull(id)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        Advert advert = advertDao.queryById(id);

        advert.setPath(StringUtils.replace(advert.getPath(), attachmentPath, "/attach/"));

        return ResultUtils.success(advert);
    }

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    @Override
    public Result queryAll(String adressNo, String name, String dataId, String createTime, Integer pageNum, Integer pageSize) {

        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        PageHelper.startPage(pageNum, pageSize);

        List<Advert> adverts = advertDao.queryAll(adressNo, name, dataId, createTime);

        adverts.forEach(item -> {
            if (item.getPath() != null) {
                // 替换path
                String s = StringUtils.replace(item.getPath(), attachmentPath, "/attach/");
                item.setPath(s);
            }

        });

        PageInfo<Advert> advertPageInfo = new PageInfo<>(adverts);

        return ResultUtils.success(advertPageInfo);
    }

    /**
     * 新增和修改数据
     *
     * @param advert 实例对象
     * @return 实例对象
     */
    @Override
    public Result save(Advert advert) {

        Integer flag = 0;
        if (CheckUtils.isNull(advert.getId())) {
            // id 为空 新增数据
            flag = advertDao.insert(advert);

        } else {
            // 修改
            Advert advert1 = advertDao.queryById(advert.getId());
            if (advert1 == null) {
                return ResultUtils.error("修改失败,请刷新页面");
            }
            flag = advertDao.update(advert);

        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    /**
     * 伪删除
     *
     * @param ids 主键
     * @return 是否成功
     */
    @Override
    public Result deleteById(String ids) {
        Integer flag = 0;
        if (CheckUtils.isNull(ids)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }

        List<String> list = Arrays.asList(ids.split(","));

        for (String s : list) {
            Advert advert = advertDao.queryById(Integer.parseInt(s));
            if (advert == null) {
                return ResultUtils.error("删除失败，请刷新页面");
            } else
                flag = advertDao.deleteById(Integer.parseInt(s));
        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }
}
