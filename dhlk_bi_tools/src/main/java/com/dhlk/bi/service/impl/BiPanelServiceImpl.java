package com.dhlk.bi.service.impl;

import com.dhlk.bi.dao.BiDatabaseDao;
import com.dhlk.bi.dao.BiPanelDao;
import com.dhlk.bi.service.BiPanelService;
import com.dhlk.bi.util.PageUtil;
import com.dhlk.domain.Result;
import com.dhlk.entity.bi.*;
import com.dhlk.enums.ResultEnum;
import com.dhlk.utils.CheckUtils;
import com.dhlk.utils.JDBCUtils;
import com.dhlk.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @des: 报表面板 service 实现
 * @author: xkliu
 * @date: 2021/02/22
 */
@Service
public class BiPanelServiceImpl implements BiPanelService {

    @Autowired
    private BiPanelDao biPanelDao;
    @Autowired
    private BiDatabaseDao biDatabaseDao;

    @Override
    public Result save(BiPanel biPanel) {
        Integer flag = 0;
        //id为空新增
        if (CheckUtils.isNull(biPanel.getId())) {
            boolean res = biPanelDao.verifyName(biPanel.getId(), biPanel.getName());
            if (res) {
                return ResultUtils.error("报表面板名称已存在");
            }
            flag = biPanelDao.insert(biPanel);
        } else {
            boolean res = biPanelDao.verifyName(biPanel.getId(), biPanel.getName());
            if (res) {
                return ResultUtils.error("报表面板名称已存在");
            }
            flag = biPanelDao.update(biPanel);
        }
        return flag > 0 ? ResultUtils.success(biPanel) : ResultUtils.failure();
    }

    @Override
    @Transactional
    public Result delete(String ids) {
        Integer flag = 0;
        if (CheckUtils.isNull(ids)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        List<String> lists = Arrays.asList(ids.split(","));
        flag = biPanelDao.delete(lists);
        return flag > 0 ? ResultUtils.success() : ResultUtils.failure();
    }

    @Override
    public Result findChartData(ChartParams chartParams) {

        //查询数据库连接信息
        if (chartParams.getBiDataBaseId() == null) {
            return ResultUtils.error("请填写数据库连接信息");
        }
        BiDatabase biDatabase = biDatabaseDao.findById(chartParams.getBiDataBaseId());
        if (biDatabase == null) {
            return ResultUtils.error("没有找到数据库连接信息");
        }
        //建立数据库连接
        Connection conn = JDBCUtils.getConnection(biDatabase.getUrl(), biDatabase.getUsername(), biDatabase.getPassword());
        if (conn == null) {
            return ResultUtils.error("数据库连接失败");
        }

        if (chartParams.getTableName() == null || "".equals(chartParams.getTableName())) {
            return ResultUtils.error("表名不能为空");
        }

        //获取开头sql
        String preSql = "SELECT ";
        //获取维度的sql
        StringBuilder dimensionsSql = null;
        if (chartParams.getDimensions() != null && !"".equals(chartParams.getDimensions())) {
            dimensionsSql = new StringBuilder();
            String[] dimensions = chartParams.getDimensions().split(",");
            for (String dimension : dimensions) {
                dimensionsSql.append("`").append(chartParams.getTableName()).append("`.`").append(dimension).append("` AS ").append(dimension).append(",");
            }
        } else {
            return ResultUtils.error("维度不能为空");
        }
        //获取度量的sql 分组的sql
        StringBuilder measuresSql = null;
        String groupSql = "";
        if (chartParams.getMeasures() != null && chartParams.getMeasures().size() != 0) {
            measuresSql = new StringBuilder();
            for (Measure measure : chartParams.getMeasures()) {
                measuresSql.append(measure.getAlgorithm()).append("(`").append(chartParams.getTableName()).append("`.`").append(measure.getMeasures()).append("`) AS ").append(measure.getMeasures()).append(",");
            }
            groupSql = "GROUP BY " + chartParams.getDimensions();
        }

        String tableSql = " FROM" + "  `" + chartParams.getTableName() + "` ";

        //获取排序sql
        String orderSql = " ORDER BY " + chartParams.getDimensions().split(",")[0] + " ASC";
        if (chartParams.getOrderColumn() != null && !"".equals(chartParams.getOrderColumn())) {
            orderSql = " ORDER BY " + chartParams.getOrderColumn() + " " + chartParams.getOrderMode();
        }

        String sql = "";
        if (measuresSql != null) {
            sql = preSql + dimensionsSql + measuresSql.substring(0, measuresSql.length() - 1) + tableSql + groupSql + orderSql;
        } else {
            sql = preSql + dimensionsSql.substring(0, dimensionsSql.length() - 1) + tableSql + orderSql;
        }

        if ("".equals(sql)) {
            return ResultUtils.error("SQL拼接错误");
        }

        PreparedStatement preparedStatement = null;
        PreparedStatement countStatement = null;
        ResultSet resultSet = null;
        ResultSet countSet = null;
        List<Map<Object, Object>> resultMap = new ArrayList<>();
        PageBean pageBean = new PageBean<>();
        pageBean.setLimit(chartParams.getLimit());
        pageBean.setPageNow(chartParams.getPageNum());
        try {
            //获取分页sql
            String limitSql = "";
            if (chartParams.getLimit() != null && chartParams.getPageNum() != null) {
                String countSql = "SELECT COUNT(*) count FROM " + chartParams.getTableName();
                countStatement = conn.prepareStatement(countSql);
                countSet = countStatement.executeQuery();
                int anInt = 0;
                while (countSet.next()) {
                    anInt = countSet.getInt(1);
                }
                pageBean.setCount(anInt);
                pageBean = PageUtil.checkPagenow(pageBean);
                limitSql = " LIMIT " + chartParams.getLimit() + " OFFSET " + pageBean.getOffset();
                sql += limitSql;
            }
            //获取结果集
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            resultMap = JDBCUtils.oneRowToObject(resultSet);
            pageBean.setList(resultMap);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(resultSet, preparedStatement, null);
            JDBCUtils.close(countSet, countStatement, conn);
        }
        System.out.println(sql + "----------------------------------------------------------------");
        return ResultUtils.success(pageBean);
    }

    @Override
    public Result findList(String name, Integer pageNum, Integer pageSize) {

        if (!CheckUtils.checkId(pageNum) || !CheckUtils.checkId(pageSize)) {
            return ResultUtils.error(ResultEnum.PARAM_ERR);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<BiPanel> lists = biPanelDao.findList(name);
        PageInfo<BiPanel> page = new PageInfo<>(lists);
        return ResultUtils.success(page);
    }

    @Override
    public Result findByBiPanel(Integer id) {
        return ResultUtils.success(biPanelDao.findByPanel(id));
    }
}
