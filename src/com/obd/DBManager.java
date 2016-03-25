package com.obd;


import java.sql.*;


public class DBManager {
    Connection conn = null;
    String url = "jdbc:mysql://localhost:3306/incarobd?"
            + "user=root&password=123456&useUnicode=true&characterEncoding=UTF8";
    public void buildConnection() {
        System.out.println("buildConnection..."+url);
        try {
            Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println("MySQL操作错误");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public int executeUpdate(String sql) {
        int result=-1;
        if (conn == null) {
            buildConnection();
        }
        try {
            Statement stmt = conn.createStatement();
            result = stmt.executeUpdate(sql);// executeUpdate语句会返回一个受影响的行数，如果返回-1就没有成功
        } catch (SQLException e) {
            System.out.println("MySQL操作错误");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }
        return result;
    }

    public ResultSet executeQuery(String sql){
        if (conn == null) {
            buildConnection();
        }
        try{
            Statement stmt = conn.createStatement() ;
            PreparedStatement pstmt = conn.prepareStatement(sql) ;
            ResultSet rs = pstmt.executeQuery();
            return rs;
        }catch (SQLException e){
            return null;
        }
    }

    public void closeConnection() {
        System.out.println("closeConnection..."+url);
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("MySQL操作错误");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}