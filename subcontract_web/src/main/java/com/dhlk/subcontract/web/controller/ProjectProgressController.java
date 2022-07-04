package com.dhlk.subcontract.web.controller;


import com.dhlk.domain.Result;
import com.dhlk.entity.basicmodule.ProjectProgressVo;
import com.dhlk.subcontract.web.service.ProjectProgressService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/progress")
@Api(description = "项目进度更新" ,value = "/ProjectProgressController")
public class ProjectProgressController {

    @Autowired
    private ProjectProgressService projectProgressService;



    /*
     * 根据项目id查询项目进度
     * */
    @GetMapping(value = "/findList")
    public Result findList(@RequestParam(value = "projectId") Integer projectId){
        return projectProgressService.findList(projectId);
    }

    @PostMapping(value = "/addOne")
    public Result addOne(@RequestBody ProjectProgressVo projectProgressVo){
        return projectProgressService.addOne(projectProgressVo);
    }

}
