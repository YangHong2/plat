package com.dhlk.entity.app;

import lombok.Data;

@Data
public class AppTenant {
    private Integer id;

    /** app应用标识 */
    private String appCode;

    /** 租户id */
    private Integer tenantId;

    /** 应用id */
    private Integer appId;

    public AppTenant() {
    }

    public AppTenant(String appCode, Integer tenantId, Integer appId) {
        this.appCode = appCode;
        this.tenantId = tenantId;
        this.appId = appId;
    }
}
