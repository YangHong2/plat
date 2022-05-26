package com.dhlk.subcontract.web.service;

import com.dhlk.domain.Result;
import com.dhlk.entity.sub.Advert;
import com.dhlk.subcontract.web.service.fbk.AdvertFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created with IntelliJ IDEA
 *
 * @Auther :yangwang
 * Data:2021/3/15
 * Time:10:50
 * @Description:
 */
@FeignClient(value = "subcontract-service/advert", fallback = AdvertFbk.class)
public interface AdvertService {

    /**
     * 新增和修改
     *
     * @param
     * @return
     */
    @PostMapping(value = "/save")
    public Result save(@RequestBody Advert advert);

    /**
     * 查看广告
     *
     * @param id
     * @return
     */
    @GetMapping("/getAdvert")
    public Result getAdvert(@RequestParam(value = "id") Integer id);

    /**
     * 广告管理
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findList")
    public Result FindList(
            // (String adressNo, String name, String dataId, String createTime);
            @RequestParam(value = "adressNo", required = false) String adressNo,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "dataId", required = false) String dataId,
            @RequestParam(value = "createTime", required = false) String createTime,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize);

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @GetMapping(value = "/delete")
    public Result delete(@RequestParam(value = "ids") String ids);
}
