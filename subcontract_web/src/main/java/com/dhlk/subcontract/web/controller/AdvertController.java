package com.dhlk.subcontract.web.controller;

import com.dhlk.domain.Result;
import com.dhlk.entity.sub.Advert;
import com.dhlk.subcontract.web.service.AdvertService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IntelliJ IDEA
 *
 * @Auther :yangwang
 * Data:2021/3/15
 * Time:10:50
 * @Description:
 */
@RestController
@RequestMapping("/advert")
@Api(description = "广告", value = "AdvertController")
public class AdvertController {

    @Autowired
    private AdvertService advertService;

    /**
     * 新增和修改
     *
     * @param
     * @return
     */
    @PostMapping(value = "/save")
    public Result save(@RequestBody Advert advert) {
        return advertService.save(advert);
    }

    /**
     * 查看广告
     *
     * @param id
     * @return
     */
    @GetMapping("/getAdvert")
    public Result getAdvert(@RequestParam(value = "id") Integer id) {
        return advertService.getAdvert(id);
    }

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
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return advertService.FindList(adressNo, name, dataId, createTime, pageNum, pageSize);
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @GetMapping(value = "/delete")
    public Result delete(@RequestParam(value = "ids") String ids) {
        return advertService.delete(ids);
    }
}