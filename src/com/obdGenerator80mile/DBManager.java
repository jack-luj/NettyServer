package com.obdGenerator80mile;



import java.sql.*;


public class DBManager {

    Connection conn = null;
    private String url = "";
    private String logPath="";
    private Tools tools;
    public DBManager(String logPath,String jdbsUrl){
        this.logPath=logPath;
        this.url=jdbsUrl;
        tools=new Tools(logPath,"");
    }
    public void buildConnection() {
        tools.writeGloablTxt("buildConnection..." + url.substring(0,url.indexOf("password")));
        try {
            Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            tools.writeGloablTxt("MySQL exception " + e.getMessage());
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
            tools.writeGloablTxt("MySQL exception "+e.getMessage());
            tools.writeGloablTxt("try reconnect to mysql server ");
            buildConnection();
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
            tools.writeGloablTxt("MySQL exception "+e.getMessage());
            tools.writeGloablTxt("try reconnect to mysql server ");
            buildConnection();
            return null;
        }
    }

    public void closeConnection() {
        tools.writeGloablTxt("closeConnection..." + url);
        try {
            conn.close();
        } catch (SQLException e) {
            tools.writeGloablTxt("MySQL exception "+e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}