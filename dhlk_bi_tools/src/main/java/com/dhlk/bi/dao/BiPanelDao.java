package com.dhlk.bi.dao;

import com.dhlk.entity.bi.BiPanel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @des: 报表面板dao
 * @author: xkliu
 * @date: 2021/02/22
 */
@Repository
public interface BiPanelDao {

    /**
     * 新增
     *
     * @param biPanel
     * @return
     */
    Integer insert(BiPanel biPanel);

    /**
     * 修改
     *
     * @param biPanel
     * @return
     */
    Integer update(BiPanel biPanel);

    /**
     * 删除
     *
     * @param lists
     * @return
     */
    Integer delete(List<String> lists);

    /**
     * 执行sql
     */
    List<LinkedHashMap<String, String>> findChartData(@Param("sql") String sql);

    /**
     * 列表查询
     *
     * @return
     */
    List<BiPanel> findList(@Param("name") String name);

    /**
     * 根据ID查询 BiPanel
     *
     * @param id
     * @return
     */
    BiPanel findByPanel(@Param("id") Integer id);

    /**
     * 校验 产前 在线 产后 保养是否存在
     *
     * @param name
     * @return
     */
    boolean verifyName(@Param("id") Integer id, @Param("name") String name);
}
