package com.dhlk.subcontract.controller;

import com.dhlk.domain.Result;
import com.dhlk.entity.sub.ProjectIssue;
import com.dhlk.subcontract.service.ProjectIssueService;
import com.dhlk.utils.ResultUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 项目发布(ProjectIssue)表控制层
 *
 * @author xkliu
 * @since 2021-03-12 09:21:17
 */

//                                                          _ooOoo_
//                                                         o8888888o
//                                                         88" . "88
//                                                         (| -_- |)
//                                                          O\ = /O
//                                                      ____/`---'\____
//                                                    .   ' \\| |// `.
//                                                     / \\||| : |||// \
//                                                   / _||||| -:- |||||- \
//                                                     | | \\\ - /// | |
//                                                   | \_| ''\---/'' | |
//                                                    \ .-\__ `-` ___/-. /
//                                                 ___`. .' /--.--\ `. . __
//                                              ."" '< `.___\_<|>_/___.' >'"".
//                                             | | : `- \`.;`\ _ /`;.`/ - ` : | |
//                                               \ \ `-. \_ __\ /__ _/ .-` / /
//                                       ======`-.____`-.___\_____/___.-`____.-'======
//                                                          `=---='
//                                       .............................................
//                                        佛祖保佑     此核心代码永无BUG，万一有Bug反正你也找不到我
//                                      佛曰:
//                                              写字楼里写字间，写字间里程序员；
//                                              程序人员写程序，又拿程序换酒钱。
//                                              酒醒只在网上坐，酒醉还来网下眠；
//                                              酒醉酒醒日复日，网上网下年复年。
//                                              但愿老死电脑间，不愿鞠躬老板前；
//                                              奔驰宝马贵者趣，公交自行程序员。
//                                              别人笑我忒疯癫，我笑自己命太贱；
//                                              不见满街漂亮妹，哪个归得程序员？
@RestController
@RequestMapping("/projectIssue")
public class ProjectIssueController {

    @Resource
    private ProjectIssueService projectIssueService;

    /**
     * 需要审批的项目查询
     *
     * @param name
     * @return
     */
    @GetMapping("/findName")
    public Result findName(@RequestParam(value = "name", required = false) String name,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return ResultUtils.success(projectIssueService.findName(name, pageNum, pageSize));
    }

    /**
     * 新增/修改
     *
     * @param projectIssue
     * @return
     */
    @PostMapping("/save")
    public Result save(@RequestBody ProjectIssue projectIssue) {
        return projectIssueService.save(projectIssue);
    }


    /**
     * 列表查询
     *
     * @param name
     * @param type
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findList")
    public Result findList(@RequestParam(value = "name", required = false) String name,
                           @RequestParam(value = "type") Integer type,
                           @RequestParam(value = "companyId") Integer companyId,
                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return projectIssueService.findList(name, type, companyId, pageNum, pageSize);
    }


    /**
     * 查询项目关联数据
     *
     * @param id
     * @return
     */
    @GetMapping("/findRelevance")
    public Result findRelevance(@RequestParam(value = "id") Integer id,
                                @RequestParam(value = "companyId") Integer companyId) {
        return projectIssueService.findRelevance(id, companyId);
    }

    /**
     * 项目管理列表查询
     *
     * @param name
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findProjectManage")
    public Result findProjectManage(@RequestParam(value = "name", required = false) String name,
                                    @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                    @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return projectIssueService.findProjectManage(name, pageNum, pageSize);
    }

    /**
     * 搜索框模糊查询
     * @param name
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/findProjectName")
    public Result findProjectName(@RequestParam(value = "name", required = false) String name,
                                    @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                    @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return projectIssueService.findProjectName(name, pageNum, pageSize);
    }

    /**
     * 我的项目数量
     *
     * @return
     */
    @GetMapping("/getProjectCount")
    public Result getProjectCount(@RequestParam(value = "companyId") Integer companyId) {
        return projectIssueService.getProjectCount(companyId);
    }

    /**
     * 获取关联项目
     *
     * @return
     */
    @GetMapping("/getRelevance")
    public Result getRelevance(@RequestParam(value = "companyId") Integer companyId) {
        return projectIssueService.getRelevance(companyId);
    }


    /**
     * 首页搜索
     *
     * @param
     * @return
     */
    @GetMapping("/search")
    public Result search(@RequestParam(value = "projectName", required = false) String projectName,
                         @RequestParam(value = "projectType", required = false) Integer projectType,
                         @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                         @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return projectIssueService.search(projectName, projectType, pageNum, pageSize);
    }


    /**
     * 根据id获取项目详情
     *
     * @return
     */
    @GetMapping("/getProjectIssue")
    public Result getProjectIssue(@RequestParam(value = "id") Integer id) {
        return projectIssueService.getProjectIssue(id);
    }

    /**
     * 查询项目流程
     * @param projectName
     * @return
     */
    @GetMapping("/projectRecords")
    public  Result projectRecords(@RequestParam(value = "projectName") String projectName,
                                  @RequestParam(value = "id") Integer id){
        return projectIssueService.projectRecords(projectName,id);
    }
}
