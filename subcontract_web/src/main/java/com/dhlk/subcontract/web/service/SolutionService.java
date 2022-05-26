package com.dhlk.subcontract.web.service;


import com.dhlk.domain.Result;
import com.dhlk.entity.sub.Solution;
import com.dhlk.subcontract.web.service.fbk.SolutionServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "subcontract-service/solution", fallback = SolutionServiceFbk.class)
public interface SolutionService {

    @GetMapping("/selectOne")
    public Result selectOne(@RequestParam(value = "id",required = true) Integer id);

    @PostMapping("/saveOrUpdate")
    public Result insert(Solution solution);

    @PostMapping("/findList")
    public Result findList(@RequestParam (value = "projectTheme") String projectTheme,
                           @RequestParam(value = "companyId", required = false) Integer companyId,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize);

    @GetMapping("/delete")
    public Result delete(@RequestParam(value = "ids", required = true) String ids);
}
