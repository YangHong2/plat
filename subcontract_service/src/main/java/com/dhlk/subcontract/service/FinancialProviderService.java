package com.dhlk.subcontract.service;

import com.dhlk.domain.Result;
import com.dhlk.entity.sub.FinancialProvider;

import java.util.List;

/**
 * 金融服务商(FinancialProvider)表服务接口
 *
 * @author makejava
 * @since 2021-03-15 09:27:23
 */
public interface FinancialProviderService {


    /**
     * 新增数据
     *
     * @param financialProvider 实例对象
     * @return 实例对象
     */
    Result save(FinancialProvider financialProvider);


    /**
     * 通过主键删除数据
     *
     * @param ids 主键
     * @return 是否成功
     */
    Result delete(String ids);

}
