package com.dhlk.subcontract.service;


import com.dhlk.domain.Result;
import com.dhlk.entity.sub.ConsultingProvider;


import java.util.List;

/**
 * 咨询服务商(ConsultingProvider)表服务接口
 *
 * @author xkliu
 * @since 2021-03-12 09:21:00
 */
public interface ConsultingProviderService {


    /**
     * 新增数据
     *
     * @param consultingProvider 实例对象
     * @return 实例对象
     */
    Result save(ConsultingProvider consultingProvider);


    /**
     * 通过主键删除数据
     *
     * @param ids 主键
     * @return 是否成功
     */
    Result delete(String ids);

}
