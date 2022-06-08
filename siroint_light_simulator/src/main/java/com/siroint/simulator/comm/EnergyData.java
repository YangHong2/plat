package com.siroint.simulator.comm;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author xmdeng
 * @date 2021/6/2 16:20
 */
@Data
public class EnergyData {

    private BigDecimal E_V;

    private BigDecimal E_P;

    private BigDecimal E_E;

    private BigDecimal E_I;

    private Integer on_off;

    private Integer brt_ness;
}
