package com.dhlk.subcontract.service;

import com.dhlk.domain.Result;
import com.dhlk.entity.sub.Gold;

import java.util.List;

/**
 * 金币表(Gold)表服务接口
 *
 * @author xkliu
 * @since 2021-03-16 09:34:17
 */
public interface GoldService {

    /**
     * 通过企业id修改金币数量（消费 or 充值）
     *
     * @param id 企业id
     * @return
     */
    Result updateGoldByUserId(Integer id,Double addNum);


    /**
     * 通过企业id修改余额数量（消费 or 充值）
     *
     * @param id 企业id
     * @return
     */
    Result updateBalabceByUserId(Integer id,Double addNum);


    /**
     * 新增数据
     * @param gold
     * @return
     */
    Result insert(Gold gold);

    Result queryByUserId(Integer id);
}
