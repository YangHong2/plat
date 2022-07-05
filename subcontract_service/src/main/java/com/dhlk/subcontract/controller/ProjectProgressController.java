package com.dhlk.subcontract.controller;


import com.dhlk.domain.Result;
import com.dhlk.subcontract.dao.vo.ProjectProgressVo;
import com.dhlk.subcontract.service.ProjectProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/progress")
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
