package com.obd;


import java.util.Date;
import java.util.List;
import java.util.Timer;

/**
 * Created by jack lu on 2016/3/11.
 */
public class Generator {

    public static  void main(String[] args){

        InitTool initTool=new InitTool();
        //初始化车辆
        List<VirtualThreadCar> virtualCarList=initTool.initTripId(initTool.getObdListFromTxt());
        long start=new Date().getTime();
        Tools.writeGloablTxt("available car count:" + virtualCarList.size());
        for (int i = 0; i < 1; i++) {
          VirtualThreadCar virtualCar=virtualCarList.get(i);
         Timer timer=new Timer();//DateUtil.parseStrToDate("2016-03-25-16:51:20")
         timer.scheduleAtFixedRate(virtualCar,DateUtil.parseStrToDate("2016-03-25 17:19:40"),1000*60*60*24);//每天
        }
        long end=new Date().getTime();
        Tools.writeGloablTxt("-- 耗时:" + (end - start) + "ms");
    }
}
