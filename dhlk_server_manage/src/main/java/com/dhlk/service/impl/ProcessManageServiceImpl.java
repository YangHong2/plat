package com.dhlk.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.dhlk.domain.ProcessInfoEntity;
import com.dhlk.domain.Result;
import com.dhlk.service.ProcessManageService;
import com.dhlk.service.RedisService;
import com.dhlk.systemconst.Const;
import com.dhlk.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: dhlk_server_manage
 * @description:
 * @author: wqiang
 * @create: 2020-07-13 10:40
 **/
@Service
@Slf4j
public class ProcessManageServiceImpl implements ProcessManageService {
    @Autowired
    private RedisService redisService;
    @Autowired
    private ParseStringUtils parseStringUtils;


    @Value("${mysql.userName}")
    String userName;

    @Value("${mysql.password}")
    String password;

    @Value("${mysql.url}")
    String url;


    @Value("${linux.webLog.dhlk_light_factory}")
    String dhlk_light_factory_logPath;

    @Value("${linux.webLog.dhlk_server_manage}")
    String dhlk_server_manage_logPath;

    @Value("${linux.webLog.dhlk_server_guard}")
    String dhlk_server_guard_logPath;

    /**
     * 获取所有进程信息
     *
     * @param processName
     * @param runStatus
     * @param flag        1null正常查询 2 陈定时任务查询 为了避免数据交叉
     * @return
     */
    @Override
    public Result getAllProcessInfo(String processName, String runStatus, String flag) {

        //所有服务list集合
        List<ProcessInfoEntity> allList = new ArrayList<>();

        //前台服务固定
        allList.addAll(parseStringUtils.getFrontCloudAndLocal());

        //获取Java后台所有服务
        String[] wedCmds = {"/bin/sh", "-c", "ps -eo pid,lstart,cmd | grep dhlk | grep -v grep"};
        List<String> processDhlkList = LinuxCommandUtils.exec(wedCmds);
        if(processDhlkList!=null && processDhlkList.size()>0){
            List<ProcessInfoEntity> webList = parseStringUtils.parseProcessString(processDhlkList, "java -jar", flag);
            if (webList != null && webList.size() > 0) {
                allList.addAll(webList);
            }
        }


        //获取硬件服务
//        String[] momCmds = {"/bin/sh", "-c", "ps -eo pid,lstart,cmd | grep dhlk_mom | grep -v grep"};
//        List<String> momDhlkList = LinuxCommandUtils.exec(momCmds);
//        if(momDhlkList!=null && momDhlkList.size()>0){
//            List<ProcessInfoEntity> momList = parseStringUtils.parseProcessString(momDhlkList, "/", flag);
//            if (momList != null && momList.size() > 0) {
//                allList.addAll(momList);
//            }
//        }


        //获取redis服务
        String[] redisCmds = {"/bin/sh", "-c", "ps -eo pid,lstart,cmd | grep redis | grep -v grep"};
        List<String> processRedisList = LinuxCommandUtils.exec(redisCmds);
        if(processRedisList!=null && processRedisList.size()>0){
            List<ProcessInfoEntity> redisList = parseStringUtils.parseProcessString(processRedisList, "redis", flag);
            if (redisList != null && redisList.size() > 0) {
                allList.addAll(redisList);
            }
        }



        //获取mysql服务
        String[] mysqlCmds = {"/bin/sh", "-c", "ps -eo pid,lstart,cmd | grep mysqld | grep -v grep | grep -v 'mysqld_safe'"};
        List<String> processMysqlList = LinuxCommandUtils.exec(mysqlCmds);
        if(processMysqlList!=null && processMysqlList.size()>0){
            List<ProcessInfoEntity> mysqlList = parseStringUtils.parseProcessString(processMysqlList, "/", flag);
            if (mysqlList != null && mysqlList.size() > 0) {
                allList.addAll(mysqlList);
            }
        }



        //获取mosquitto服务
//        String[] mosquittoCmds = {"/bin/sh", "-c", "ps -eo pid,lstart,cmd | grep mosquitto | grep -v grep"};
//        List<String> processMosquittoList = LinuxCommandUtils.exec(mosquittoCmds);
//        if(processMosquittoList!=null && processMosquittoList.size()>0){
//            List<ProcessInfoEntity> mosquittoList = parseStringUtils.parseProcessString(processMosquittoList, "/", flag);
//            if (mosquittoList != null && mosquittoList.size() > 0) {
//                allList.addAll(mosquittoList);
//            }
//        }


        //获取nginx服务，只取主服务nginx：master
        String[] nginxCmds = {"/bin/sh", "-c", "ps -eo pid,lstart,cmd | grep nginx | grep -v grep"};
        List<String> processNginxList = LinuxCommandUtils.exec(nginxCmds);
        if(processNginxList !=null && processNginxList.size()>0){
            List<ProcessInfoEntity> nginxList = parseStringUtils.parseProcessString(processNginxList, "nginx", flag);
            if (nginxList != null && nginxList.size() > 0) {
                allList.addAll(nginxList);
            }
        }

        if (allList != null && allList.size() > 0) {
            //查询全部服务
            if (StringUtils.isNullOrEmpty(processName) && StringUtils.isNullOrEmpty(runStatus)) {
                ResultUtils.success(allList);
            }
            //根据名称查询
            if (!StringUtils.isNullOrEmpty(processName) && StringUtils.isNullOrEmpty(runStatus)) {
                List<ProcessInfoEntity> filterListByNameList = new ArrayList<>();
                for (ProcessInfoEntity processInfoEntity : allList) {
                    if (processInfoEntity.getProcessName().contains(processName)) {
                        filterListByNameList.add(processInfoEntity);
                    }
                }
                return ResultUtils.success(filterListByNameList);
            }
        }

       /* //测试服务-测试后将会删除
        String[] testCmds = {"/bin/sh", "-c", "ps -eo pid,lstart,cmd | grep testlinuxserver | grep -v grep"};
        List<String> processTestList = LinuxCommandUtils.exec(testCmds);
        if(processTestList!=null && processTestList.size()>0){
            List<ProcessInfoEntity> testList = parseStringUtils.parseProcessString(processTestList, "java -jar", flag);
            if (testList != null && testList.size() > 0) {
                allList.addAll(testList);
            }
        }*/

        return ResultUtils.success(allList);
    }

    @Override
    public Result stopProcess(String pid) {

        if (!StringUtils.isNullOrEmpty(pid)) {
            try {
                String[] stopCmds = {"/bin/sh", "-c", Const.KILL_PROCESS_COMMAND + pid};
                LinuxCommandUtils.exec(stopCmds);
                //exeCommandServer.exec(Const.KILL_PROCESS_COMMAND + pid);
                return ResultUtils.success();
            } catch (Exception e) {
                return ResultUtils.failure();
            }
        }

        return ResultUtils.failure();
    }

    @Override
    public Result startProcess(String processName) {

        if (!StringUtils.isNullOrEmpty(processName)) {
            try {
                //获取执行命令，
                System.out.println("---------------------------执行命令之前");
                String[] startCmds = {"/bin/sh", "-c", getStartCommand(processName)};
                System.out.println("命令------------->" + startCmds[2]);
                LinuxCommandUtils.exec(startCmds);
                System.out.println("---------------------------执行命令之后");
                return ResultUtils.success();
            } catch (Exception e) {
                return ResultUtils.failure();
            }

        }
        return ResultUtils.failure();
    }

    @Override
    public Result serverUpdate(String processName, String fileAddress) {
        return null;
    }


    /**
     * 获取开启命令
     *
     * @param processName
     * @return
     */
    public String getStartCommand(String processName) {
        //Const.WEB_START_PROCESS+redisService.get(processName).toString() = java -jar xxxxxxx.jar
        //nohup java -jar /home/software/java/dhlk_web-1.0-SNAPSHOT.jar 1>/home/software/java/logs/dhlk_web.log 2>&1 &
        if (!StringUtils.isNullOrEmpty(processName)) {
            if ("dhlk_light_factory".equals(processName)) {
                return Const.START_WEB_PROCESS_COMMAND + redisService.get(processName).toString() + " " + dhlk_light_factory_logPath;
            } else if ("dhlk_server_manage".equals(processName)) {
                return Const.START_WEB_PROCESS_COMMAND + redisService.get(processName).toString() + " " + dhlk_server_manage_logPath;
            } else if ("testlinuxserver".equals(processName)) {  //测试服务，测试之后将会删除
                //System.out.println(Const.START_WEB_PROCESS_COMMAND + redisService.get(processName).toString() + " >/dev/null 2>&1 &");
                return Const.START_WEB_PROCESS_COMMAND + redisService.get(processName).toString() + " >/dev/null 2>&1 &";
            } else if ("dhlk_mom".equals(processName)) {
                if(redisService.get(processName).toString().contains("zip")){
                    String sub = org.apache.commons.lang3.StringUtils.substringBeforeLast(redisService.get(processName).toString(), "/");
                    System.out.println("sub======="+sub);
                    return "cd  "+sub+"; "
                            + "chmod 777 "+  processName  + ";"
                            +  sub+"/"+processName+ " &";
                }else{
                    System.out.println("=========="+redisService.get(processName).toString()+"/"+processName+ " &");
                    return "cd  "+redisService.get(processName).toString()+"; "
                            + "chmod 777 "+  processName  + ";"
                            +  redisService.get(processName).toString()+"/"+processName+ " &";
                }

            } else if ("redis".equals(processName)) {
                return Const.START_REDIS_PROCESS_COMMAND;
            } else if ("mysql".equals(processName)) {
                return Const.START_MYSQL_PROCESS_COMMAND;
            } else if ("mosquitto".equals(processName)) {
                return Const.START_MOSQUITTO_PROCESS_COMMAND;
            } else if ("nginx".equals(processName)) {
                return Const.START_NGINX_PROCESS_COMMAND;
            }else if("dhlk_server_guard".equals(processName)){
                return dhlk_server_guard_logPath;
            }
        }
        return "";
    }


    @Override
    public Result executeSQL(String sqlPath) {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, userName, password);
           /* ScriptRunner runner = new ScriptRunner(conn);
            Resources.setCharset(Charset.forName("UTF-8")); //设置字符集,不然中文乱码插入错误
            runner.setLogWriter(null);//设置是否输出日志
            // 绝对路径读取
            Reader read = new FileReader(new File(sqlPath));
            // 从class目录下直接读取
            //Reader read = Resources.getResourceAsReader(sqlPath);
            runner.runScript(read);
            runner.closeConnection();
            conn.close();*/
            String sql = FileUtils.fileParseStr(sqlPath);
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
            conn.close();
            redisService.set("sql", sqlPath.substring(0, sqlPath.lastIndexOf("/")));
            return ResultUtils.success("sql脚本执行完毕!");
        } catch (Exception e) {
            return ResultUtils.error("sql脚本执行发生异常!" + "错误信息：" + e.getMessage());
        }
    }

    @Override
    public Result stopMom() {
        try {
           //停止 damoen
            try {
                stopCmd("damoen");
            }catch (Exception e){
                log.info("damoen.sh未启动或其他原因：{}",e);
            }

            //停止MOM
            stopCmd(Const.DHLK_MOM);
        }catch (Exception e){
            return ResultUtils.error("停止MOM失败，原因："+e.getMessage());
        }
        return ResultUtils.success();
    }

    @Override
    public Result startMom() {
        try {
            //启动mom守护进程
            startCmd("damoen.sh");
        }catch (Exception e){
            log.error("启动mom守护进程失败");
        }
        try {
            startCmd("dhlk_mom");
        }catch (Exception e){
            return ResultUtils.error("启动MOM失败，原因："+e.getMessage());
        }
        return ResultUtils.success();
    }

    private void stopCmd(String execStr){
        String[] grepMomCmd = {"/bin/sh", "-c", "ps -eo pid,lstart,cmd | grep " + execStr + " | grep -v grep"};
        log.info("命令：{}",JSONArray.toJSONString(grepMomCmd));
        List<String> processMomList = LinuxCommandUtils.exec(grepMomCmd);
        log.info("命令返回:{}",JSONArray.toJSONString(processMomList));
        if(!CollectionUtils.isEmpty(processMomList)){
            String momStr = processMomList.get(0);
            String[] s = momStr.trim().split(" ");
            String[] stopCmds = {"/bin/sh", "-c", Const.KILL_PROCESS_COMMAND + s[0]};
            log.info("执行命令成功：{}",JSONArray.toJSONString(stopCmds));
            LinuxCommandUtils.exec(stopCmds);
        }else {
            log.error("{}应用不存在",execStr);
        }
    }


    private void startCmd(String execName){
        String [] readlinkCmd = {"/bin/sh", "-c"," locate " + execName};
        List<String> processList = LinuxCommandUtils.exec(readlinkCmd);
        if(!CollectionUtils.isEmpty(processList)){
            String path = processList.get(0);
            log.info("文件路径：{}",path);
            String [] startCmd = {"/bin/sh", "-c",""};
            if(execName.contains(".jar")){
                 startCmd[2] =  Const.START_WEB_PROCESS_COMMAND + path + ">> " + path+".log &" ;
            }else {
                String substring = path.substring(0, path.lastIndexOf("/"));
                if(org.apache.commons.lang.StringUtils.isBlank(substring)){
                    substring = "/";
                }
                startCmd[2] = "cd " + substring + "; chmod 777 "+ execName +";  ./" + execName + " &";
                if(!execName.contains("dhlk_mom")){
                    startCmd[2] = "cd " + substring + "; chmod 777 "+ execName +"; ./" + execName + " &";
                }else {
                    startCmd[2] = "cd " + substring + "; chmod 777 "+ execName +";  ./" + execName + " 2 &";
                }
            }
            log.info("开始执行命令：{}", JSONArray.toJSONString(startCmd));
            LinuxCommandUtils.exec(startCmd);
            log.info("执行命令结束：{}", JSONArray.toJSONString(startCmd));

        }
    }

}
