package com.dhlk.entity.bi;

import lombok.Data;

/**
 * 度量和条件封装类
 */
@Data
public class Measure {
    /**
     * 度量
     */
    private String measures;
    /**
     * 算法 SUM 求和 COUNT 总数 MAX 最大值 MIN 最小值 AVG 平均值
     */
    private String algorithm;
}
