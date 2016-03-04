package com.db;

import java.util.Date;

/**
 * Created by jack lu on 2016/3/4.
 */
public class BuildLanduData {

    public static  void main(String[] args){
        DBManager dbManager=new DBManager();
        long start=new Date().getTime();
        String sql="insert into t_user_device(id,user_id,device_type,device_id)values(null,1,1,'f7f27b5b 8d98160e 0c962f90 c7ddaf04 3eb85403 7cdeb81f')";
        for (int i = 0; i < 10; i++) {
            System.out.println("execute sql:"+dbManager.executeUpdate(sql));
        }
        long end=new Date().getTime();
        System.out.println("耗时:"+(end-start)+"ms");

    }
}
