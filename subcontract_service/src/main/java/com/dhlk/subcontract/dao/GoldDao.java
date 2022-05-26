package com.dhlk.subcontract.dao;

import com.dhlk.entity.sub.Gold;

/**
 * 金币表(Gold)表数据库访问层
 *
 * @author xkliu
 * @since 2021-03-16 09:34:16
 */
public interface GoldDao {

    /**
     * 通过企业ID增加余额
     * 充值直接加
     * 如果消费 就  加 负数
     *
     * @param id 企业id
     * @return 实例对象
     */
    int addBalance(Integer id, Double addNum);

    /**
     * 通过企业ID增加金币
     *
     * @param id 企业id
     * @return 实例对象
     */
    int addGold(Integer id, Double addNum);

    /**
     * 新增
     *
     * @param gold
     * @return
     */
    int insert(Gold gold);

    Gold queryByUserId(Integer id);

}

