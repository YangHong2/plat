package com.dhlk.apis.entity;


import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA
 *
 * @Auther :yangwang
 * Data:2021/3/24
 * Time:11:33
 * @Description:
 */
@Data
public class Energy implements Serializable {

    private String energy_ts;
    private String energy_id;
    private String t_act_energy;
    private String t_react_energy;
    private String t_act_pwr;
    private String t_react_pwr;
    private String t_act_energy_A;
    private String t_react_energy_A;
    private String E_V_A;
    private String E_I_A;
    private String act_pwr_A;
    private String react_pwr_A;
    private String pwr_rate_A;
    private String t_act_energy_B;
    private String t_react_energy_B;
    private String E_V_B;
    private String E_I_B;
    private String act_pwr_B;
    private String react_pwr_B;
    private String pwr_rate_B;
    private String t_act_energy_C;
    private String t_react_energy_C;
    private String E_V_C;
    private String E_I_C;
    private String act_pwr_C;
    private String react_pwr_C;
    private String pwr_rate_C;
    private String freq;
    private String deviceCode;

    public Energy() {
    }

    public Energy(String energy_ts, String energy_id, String t_act_energy, String t_react_energy, String t_act_pwr, String t_react_pwr, String t_act_energy_A, String t_react_energy_A, String e_V_A, String e_I_A, String act_pwr_A, String react_pwr_A, String pwr_rate_A, String t_act_energy_B, String t_react_energy_B, String e_V_B, String e_I_B, String act_pwr_B, String react_pwr_B, String pwr_rate_B, String t_act_energy_C, String t_react_energy_C, String e_V_C, String e_I_C, String act_pwr_C, String react_pwr_C, String pwr_rate_C, String freq, String deviceCode) {

        this.energy_ts = energy_ts;
        this.energy_id = energy_id;
        this.t_act_energy = t_act_energy;
        this.t_react_energy = t_react_energy;
        this.t_act_pwr = t_act_pwr;
        this.t_react_pwr = t_react_pwr;
        this.t_act_energy_A = t_act_energy_A;
        this.t_react_energy_A = t_react_energy_A;
        E_V_A = e_V_A;
        E_I_A = e_I_A;
        this.act_pwr_A = act_pwr_A;
        this.react_pwr_A = react_pwr_A;
        this.pwr_rate_A = pwr_rate_A;
        this.t_act_energy_B = t_act_energy_B;
        this.t_react_energy_B = t_react_energy_B;
        E_V_B = e_V_B;
        E_I_B = e_I_B;
        this.act_pwr_B = act_pwr_B;
        this.react_pwr_B = react_pwr_B;
        this.pwr_rate_B = pwr_rate_B;
        this.t_act_energy_C = t_act_energy_C;
        this.t_react_energy_C = t_react_energy_C;
        E_V_C = e_V_C;
        E_I_C = e_I_C;
        this.act_pwr_C = act_pwr_C;
        this.react_pwr_C = react_pwr_C;
        this.pwr_rate_C = pwr_rate_C;
        this.freq = freq;
        this.deviceCode = deviceCode;
    }
}
