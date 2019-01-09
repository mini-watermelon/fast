package com.wisdomelon.base.code.utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBUtils {

    private static String DRIVER;
    private static String URL;
    private static String USERNAME;
    private static String PASSWORD;

    public static final void init(String driverClass, String jdbcUrl, String username, String password) {
        DRIVER = driverClass;
        URL = jdbcUrl;
        USERNAME = username;
        PASSWORD = password;
    }

    public static Connection getConnection(){
        try {
            Class.forName(DRIVER);
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 更新数据库操作（包括增删改操作）
     */
    public static int execUpdate(String sql, Object ...objs) throws Exception{
        Connection conn = getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        if(objs != null && objs.length > 0){
            for(int i = 0; i < objs.length; i++){
                pstmt.setObject(i+1, objs[i]);
            }
        }
        int result = pstmt.executeUpdate();
        close(null, pstmt, conn);
        return result;
    }

    /**
     * 数据库查询操作
     */
    public static List<Map<String, Object>> execQuery(String sql, Object ...objs) throws Exception {
        Connection conn = getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        if(objs != null && objs.length > 0){
            for(int i = 0; i < objs.length; i++){
                pstmt.setObject(i+1, objs[i]);
            }
        }
        ResultSet rs = pstmt.executeQuery();
        List<Map<String, Object>> list = populate(rs);
        close(rs, pstmt, conn);
        return list;
    }

    public static void close(ResultSet rs, PreparedStatement pre, Connection conn) {
        if(rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                rs = null;
            }
        }
        if(pre != null) {
            try {
                pre.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                pre = null;
            }
        }
        if(conn != null) {
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conn = null;
            }
        }
    }

    /**
     * 将数据库查询结果转换成list
     * @param rs
     * @return
     * @throws Exception
     */
    public static List<Map<String, Object>> populate(ResultSet rs) throws Exception {
        ResultSetMetaData rsmd = rs.getMetaData();
        int colCount = rsmd.getColumnCount();
        List<Map<String, Object>> list = new ArrayList<>();
        while(rs.next()) {
            Map<String, Object> rowData = new HashMap<>();
            for(int i=1;i <= colCount; i++) {
                if(rs.getObject(i) == null)
                    rowData.put(rsmd.getColumnName(i), "");
                else
                    rowData.put(rsmd.getColumnName(i), rs.getObject(i));
            }
            list.add(rowData);
        }
        return list;
    }

}
