package com.dhlk.entity.sub;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import org.springframework.context.annotation.ComponentScan;

import java.io.Serializable;

/**
 * 企业主页(CompanyPages)实体类
 *
 * @author xkliu
 * @since 2021-03-12 09:20:58
 */
@Data
public class CompanyPages implements Serializable {
    private static final long serialVersionUID = -31143241859212179L;
    /**
     * 主键
     */
    private Integer id;
    /**
     * 广告
     */
    private Object homePage;
    /**
     * 企业介绍
     */
    private Object companyIntroduce;
    /**
     * 企业信用值
     */
    private Double creditValue;
    /**
     * 荣誉证书
     */
    private Object certificate;
    /**
     * 案例
     */
    private Object companyCase;
    /**
     * 解决方案
     */
    private Object solution;
    /**
     * 企业
     */
    private Integer companyId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Object getHomePage() {
        return homePage;
    }

    public void setHomePage(Object homePage) {
        this.homePage = homePage;
    }

    public Object getCompanyIntroduce() {
        return companyIntroduce;
    }

    public void setCompanyIntroduce(Object companyIntroduce) {
        this.companyIntroduce = companyIntroduce;
    }

    public Double getCreditValue() {
        return creditValue;
    }

    public void setCreditValue(Double creditValue) {
        this.creditValue = creditValue;
    }

    public Object getCertificate() {
        return certificate;
    }

    public void setCertificate(Object certificate) {
        this.certificate = certificate;
    }

    public Object getCompanyCase() {
        return companyCase;
    }

    public void setCompanyCase(Object companyCase) {
        this.companyCase = companyCase;
    }

    public Object getSolution() {
        return solution;
    }

    public void setSolution(Object solution) {
        this.solution = solution;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

}
