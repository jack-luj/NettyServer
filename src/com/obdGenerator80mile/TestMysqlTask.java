package com.obdGenerator80mile;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TimerTask;

/**
 * Created by jack lu on 2016/3/29.
 */
public class TestMysqlTask extends TimerTask {
    private DBManager dbManager;
    private UtilTools tools;
    public TestMysqlTask(DBManager dbManager,UtilTools tools){
        this.dbManager=dbManager;
        this.tools=tools;

    }

    @Override
    public void run() {
        String testSql="select now() as now;";

        try {
            ResultSet rs =  dbManager.executeQuery(testSql);
            while (rs.next()) {
                String now = rs.getString("now");
                tools.writeGloablTxt("test mysql..."+now);
            }
        } catch (SQLException e) {
        }
    }
}
