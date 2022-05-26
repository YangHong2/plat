package com.dhlk.entity.bi;

import lombok.Data;

import java.util.List;

/**
 * 图标参数
 */
@Data
public class ChartParams {
    /**
     * 表名
     */
    private String tableName;
    /**
     * 维度
     */
    private String dimensions;


    /**
     * 当前页
     */
    private Integer pageNum;

    /**
     * 条数
     */
    private Integer limit;

    /**
     * 数据库连接
     */
    private Integer biDataBaseId;

    /**
     * 度量和条件集合
     */
    private List<Measure> measures;

    /**
     * 排序列
     */
    private String orderColumn;

    /**
     * 排序方式
     */
    private String orderMode;
}
