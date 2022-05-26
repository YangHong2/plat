package com.dhlk.apis.controller;

import com.dhlk.apis.service.ReadHdfs;
import com.dhlk.apis.util.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA
 *
 * @Auther :yangwang
 * Data:2021/3/23
 * Time:13:40
 * @Description:
 */
@RestController
@RequestMapping("/readHdfs")
public class ReadHdfsController {

    @GetMapping("/findList")
    public Result getData(
            @RequestParam(value = "hdfsUrl") String hdfsUrl,
            @RequestParam(value = "tenant", required = false) String tenant,
            @RequestParam(value = "deviceId", required = false) String deviceId,
            @RequestParam(value = "dt", required = false) String dt,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize
    ) throws Exception {
        ReadHdfs readHdfs = new ReadHdfs();
        return readHdfs.readHdfsDate(hdfsUrl, tenant, deviceId, dt, pageNum, pageSize);
    }

}
