package com.dhlk.service;

import com.dhlk.domain.Result;


public interface ProcessManageService {

    /**
     * 获取本地一体机服务相关信息
     * @param processName
     * @param runStatus
     * @param flag
     * @return
     */
    Result getAllProcessInfo(String processName, String runStatus, String flag);

    Result stopProcess(String pid);

    Result startProcess(String processName);

    Result serverUpdate(String processName, String fileAddress);

    Result executeSQL(String sqlPath);

    Result stopMom();

    Result startMom();
}
