package com.dhlk.subcontract.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhlk.domain.Result;
import com.dhlk.entity.sub.CompanyPages;
import com.dhlk.subcontract.dao.CompanyPagesDao;
import com.dhlk.subcontract.service.CompanyPagesService;
import com.dhlk.utils.ResultUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static java.lang.System.out;

/**
 * 企业主页(CompanyPages)表服务实现类
 *
 * @author xkliu
 * @since 2021-03-12 09:20:59
 */
@Service()
public class CompanyPagesServiceImpl extends ServiceImpl<CompanyPagesDao, CompanyPages> implements CompanyPagesService {
    @Resource
    private CompanyPagesDao companyPagesDao;

    /**
     * 通过CompanyId查询单条数据
     *
     * @param id
     * @return 实例对象
     */
    @Override
    public CompanyPages selectOneByCompanyId(Integer companyId) {
        return this.companyPagesDao.selectOneByCompanyId(companyId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<CompanyPages> queryAllByLimit(int offset, int limit) {
        return this.companyPagesDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param companyPages 实例对象
     * @return 实例对象
     */
    @Override
    public CompanyPages insert(CompanyPages companyPages) {
        this.companyPagesDao.insert(companyPages);
        return companyPages;
    }

    /**
     * 修改数据
     *
     * @param companyPages 实例对象
     * @return 实例对象
     */
    @Override
    public Result update(CompanyPages companyPages) {
        out.println(companyPages);/*换行输出*/
/*        LambdaQueryWrapper<CompanyPages> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(CompanyPages::getCompanyId,companyPages.getCompanyId());
        
         int update = companyPagesDao.update(companyPages, queryWrapper);*/
        final int update1 = this.companyPagesDao.update(companyPages);
        return update1 > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.companyPagesDao.deleteById(id) > 0;
    }
}
