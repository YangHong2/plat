package com.dhlk.bi.service.impl;

import com.dhlk.bi.dao.BiDatabaseDao;
import com.dhlk.bi.service.BiDatabaseService;
import com.dhlk.domain.Result;
import com.dhlk.entity.bi.BiDatabase;
import com.dhlk.entity.bi.BiDatabaseInfo;
import com.dhlk.enums.ResultEnum;
import com.dhlk.utils.CheckUtils;
import com.dhlk.utils.JDBCUtils;
import com.dhlk.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.*;


/**
 * @des: 数据库连接 service 实现
 * @author: xkliu
 * @date: 2021/02/22
 */
@Service
@Slf4j
public class BiDatabaseServiceImpl implements BiDatabaseService {

    @Autowired
    private BiDatabaseDao biDatabaseDao;

    @Override
    @Transactional
    public Result save(BiDatabase biDatabase) {
        Integer flag = 0;
        //id为空新增
        if (CheckUtils.isNull(biDatabase.getId())) {
            flag = biDatabaseDao.insert(biDatabase);
        } else {
            flag = biDatabaseDao.update(biDatabase);
        }
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    public Result findList() {
        List<BiDatabase> lists = biDatabaseDao.findList();
        PageInfo<BiDatabase> CheckItemPage = new PageInfo<>(lists);
        return ResultUtils.success(CheckItemPage);
    }

    @Override
    @Transactional
    public Result delete(String ids) {
        Integer flag = 0;
        if (CheckUtils.isNull(ids)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        List<String> lists = Arrays.asList(ids.split(","));
        flag = biDatabaseDao.delete(lists);
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    public Result testConnection(BiDatabase biDatabase) {
        Connection connection = JDBCUtils.getConnection(biDatabase.getUrl(), biDatabase.getUsername(), biDatabase.getPassword());
        if (connection == null) {
            return ResultUtils.error("测试连接失败");
        }
        return ResultUtils.success();
    }

    @Override
    public Result getTableNames(BiDatabase biDatabase) {
        List<String> tableNames = new ArrayList<>();
        Connection conn = JDBCUtils.getConnection(biDatabase.getUrl(), biDatabase.getUsername(), biDatabase.getPassword());
        ResultSet rs = null;
        try {
            //获取数据库的元数据
            DatabaseMetaData db = conn.getMetaData();
            //截取数据库名字
            String name = StringUtils.substringAfterLast(biDatabase.getUrl(), "/");
            //从元数据中获取到所有的表名
            rs = db.getTables(name, null, null, new String[]{"TABLE"});
            while (rs.next()) {
                tableNames.add(rs.getString("TABLE_NAME"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("获取数据库表信息失败", e);
        } finally {
            JDBCUtils.close(rs, null, conn);
        }
        return ResultUtils.success(tableNames);
    }

    @Override
    public Result getTableColumns(BiDatabase biDatabase) {
        List<BiDatabaseInfo> list = new ArrayList<>();
        //与数据库的连接
        Connection conn = JDBCUtils.getConnection(biDatabase.getUrl(), biDatabase.getUsername(), biDatabase.getPassword());
        PreparedStatement ps = null;
        String tableSql = "select * from " + biDatabase.getTableName();
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(tableSql);
            rs = ps.executeQuery("show full columns from " + biDatabase.getTableName());
            while (rs.next()) {
                BiDatabaseInfo info = new BiDatabaseInfo();
                info.setField(rs.getString("Field"));
                info.setType(rs.getString("Type"));
                info.setComment(rs.getString("Comment"));
                list.add(info);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("获取数据库表字段失败", e);
        } finally {
            JDBCUtils.close(rs, ps, conn);
        }
        return ResultUtils.success(list);
    }

    @Override
    public Result getSchema(BiDatabase biDatabase) {
        List<String> list = new ArrayList<>();
        //与数据库的连接
        Connection conn = JDBCUtils.getConnection(biDatabase.getUrl(), biDatabase.getUsername(), biDatabase.getPassword());
        ResultSet rs = null;
        try {
            rs = conn.getMetaData().getCatalogs();
            while (rs.next()) {
                list.add(rs.getString("TABLE_CAT"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs, null, conn);
        }
        return ResultUtils.success(list);
    }

    @Override
    public Result createSql(BiDatabase biDatabase) {

        //与数据库的连接
        Connection conn = JDBCUtils.getConnection(biDatabase.getUrl(), biDatabase.getUsername(), biDatabase.getPassword());
        PreparedStatement ps = null;
        ResultSet rs = null;

        PreparedStatement comPs = null;
        ResultSet comRs = null;
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(biDatabase.getSqlFileAddress()));

            for (String tableName:biDatabase.getTableName().split(",")) {
                ps = conn.prepareStatement("show full columns from " + tableName);
                rs = ps.executeQuery();

                //获取表注释
                //SELECT TABLE_COMMENT FROM information_schema.TABLES WHERE table_schema='prj' AND TABLE_NAME = 'company';
                String comSql = "SELECT TABLE_COMMENT FROM information_schema.TABLES WHERE table_schema='"+biDatabase.getUrl().substring(biDatabase.getUrl().lastIndexOf("/")+1)
                        +"' AND TABLE_NAME = '"+tableName+"';";
                comPs = conn.prepareStatement(comSql);
                comRs = comPs.executeQuery();
                String comString = "";
                while (comRs.next()){
                    comString = comRs.getString("TABLE_COMMENT");
                }

                writer.println("/*==================================================*/");
                writer.println("/*Table:"+comString+"*/");
                writer.println("/*==================================================*/");
                //生成创建表的sql
                writer.println("drop table if exists "+tableName+";");
                writer.println("create table "+tableName+"(");
                boolean flag = false;
                while (rs.next()) {
                    StringBuilder sql = new StringBuilder();
                    if("id".equals(rs.getString("Field"))){
                        flag = true;
                        sql.append(rs.getString("Field")).append(" ").append(rs.getString("Type")).append(" NOT NULL AUTO_INCREMENT ").append(" COMMENT '").append(rs.getString("Comment")).append("',");
                    }else{
                        if("timestamp".equals(rs.getString("Type"))){
                            if(("0000-00-00 00:00:00").equals(rs.getString("Default"))){
                                sql.append(rs.getString("Field")).append(" ").append(rs.getString("Type")).append(" NUll").append(" COMMENT '").append(rs.getString("Comment")).append("',");
                            }else if (rs.getString("Default") == null){
                                sql.append(rs.getString("Field")).append(" ").append(rs.getString("Type")).append(" NUll ").append(" COMMENT '").append(rs.getString("Comment")).append("',");
                            }else{
                                sql.append(rs.getString("Field")).append(" ").append(rs.getString("Type")).append(" DEFAULT ").append(rs.getString("Default")).append(" COMMENT '").append(rs.getString("Comment")).append("',");
                            }
                        }else{
                            if(rs.getString("Default") == null){
                                sql.append(rs.getString("Field")).append(" ").append(rs.getString("Type")).append(" DEFAULT ").append(rs.getString("Default")).append(" COMMENT '").append(rs.getString("Comment")).append("',");
                            }else{
                                sql.append(rs.getString("Field")).append(" ").append(rs.getString("Type")).append(" DEFAULT '").append(rs.getString("Default")).append("' COMMENT '").append(rs.getString("Comment")).append("',");
                            }
                        }
                    }
                    writer.println("    "+sql);
                }
                if(flag){
                    writer.println("    primary key  (id)");
                }else{
                    writer.println("/*请删除前面的一个逗号*/");
                }
                writer.println(")ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='"+comString+"';");
            }
            writer.close();
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("获取数据库表字段失败", e);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("写入文件失败", e);
        }finally {
            JDBCUtils.close(comRs,comPs,null);
            JDBCUtils.close(rs, ps, conn);
        }

        return ResultUtils.success("请到填写的地址文件获取相关sql");
    }


}
