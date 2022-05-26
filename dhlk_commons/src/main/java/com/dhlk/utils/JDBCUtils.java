package com.dhlk.utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @des: JDBC工具
 * @author: xkliu
 * @date: 2021/02/22
 */
public class JDBCUtils {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://";

    /**
     * 获取jdbc连接的方法
     **/
    public static Connection getConnection(String url, String username, String password) {
        Connection conn = null;
        try {
            //加载驱动
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL + url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }


    /**
     * 关闭释放资源的方法
     */
    public static void close(ResultSet rs, Statement stmt, Connection connection) {
        try {//关闭结果集
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {//关闭sql语句
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {//关闭连接
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static List<Map<Object,Object>> oneRowToObject(ResultSet rs) throws SQLException, SecurityException, IllegalArgumentException{

        List<Map<Object,Object>> resultList = new ArrayList<>();

        //获取结果集元数据(获取此 ResultSet 对象的列的编号、类型和属性。)
        ResultSetMetaData rd=rs.getMetaData();
        int columnCount = rd.getColumnCount();
        while (rs.next()){
            Map<Object,Object> obj = new HashMap<>();
            for (int i = 1; i <= columnCount; i++) {
                switch (rd.getColumnType(i)){
                    case Types.VARCHAR:
                    case Types.TIMESTAMP:
                        obj.put(rd.getColumnName(i),rs.getString(i));
                        break;
                    case Types.BIGINT:
                    case Types.INTEGER:
                        obj.put(rd.getColumnName(i),rs.getInt(i));
                        break;
                    case Types.DECIMAL:
                        obj.put(rd.getColumnName(i),rs.getBigDecimal(i));
                        break;
                }
            }
            resultList.add(obj);
        }
        return resultList;
    }

}
