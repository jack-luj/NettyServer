package com.db.everyDay;


import java.util.Date;
import java.util.List;

/**
 * Created by jack lu on 2016/3/11.
 */
public class Generator {

    public static  void main(String[] args){

        InitTool initTool=new InitTool();
        //初始化车辆
        List<VirtualThreadCar> virtualCarList=initTool.initTripId(initTool.getObdListFromTxt());

        long start=new Date().getTime();
        System.out.println("available car count:"+virtualCarList.size());
        for (int i = 0; i < 1; i++) {
          VirtualThreadCar virtualCar=virtualCarList.get(i);
            System.out.println(virtualCar.getId()+","+virtualCar.getObdCode()+","+virtualCar.getTripId());
            virtualCar.initDate(new Date());//初始化时间
            virtualCar.driveOneDay();//
        }
        long end=new Date().getTime();
        Tools.writeGloablTxt("-- 耗时:" + (end - start) + "ms");
    }
}
