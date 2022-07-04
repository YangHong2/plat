package com.dhlk.subcontract.web.service;

import com.dhlk.domain.Result;
import com.dhlk.entity.sub.ReceivingInfo;
import com.dhlk.subcontract.web.service.fbk.ReceivingInfoServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "subcontract-service/receivingInfo", fallback = ReceivingInfoServiceFbk.class)
public interface ReceivingInfoService {

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    Result queryById(@RequestParam(value = "id")Integer id);

    /**
     * 通过项目ID查询单条数据
     * @param id
     * @return
     */
    @GetMapping("selectByProjectId")
     Result selectByProjectId(@RequestParam(value = "id")Integer id);

    /**
     * 保存付款信息
     * @param receivingInfo
     * @return
     */
    @PostMapping("save")
     Result insert(@RequestBody ReceivingInfo receivingInfo) ;


    /**
     * 修改付款信息
     * @param receivingInfo
     * @return
     */
    @PostMapping("updataByid")
     Result update(@RequestBody ReceivingInfo receivingInfo) ;

}
