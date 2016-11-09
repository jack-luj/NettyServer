package com.obdGenerator80mile;

import java.util.Date;
import java.util.List;

/**
 * Created by jackl on 2016/11/9.
 */
public class HisDataTask {
    private List<VirtualThreadCar> virtualCarList;
    private UtilTools tools;
    private DBManager dbManager;
    private List<Date> startDateList;
    int count=0;

    public HisDataTask(List<VirtualThreadCar> virtualCarList, UtilTools tools, DBManager dbManager,  List<Date> startDateList) {
        this.virtualCarList = virtualCarList;
        this.tools = tools;
        this.dbManager = dbManager;
        this.startDateList = startDateList;
    }

    public void makeData(){
        tools.writeGloablTxt("-- 开始生成历史数据:" + " " + DateUtil.format(new Date()));

        for (int j = 0; j < startDateList.size(); j++) {
        //for (int j = 0; j < 1; j++) {
            Date startDate=startDateList.get(j);//每一天

            for(int i=0;i<virtualCarList.size();i++){
                VirtualThreadCar car=virtualCarList.get(i);
                if(car.getCreateDate().getTime()>startDate.getTime()){
                    continue;
                }
                int startCount= UtilTools.getNoBetween(1, 4)-1;//车一天启动0~3次
                Date _startDate=startDate;
                tools.writeGloablTxt(DateUtil.format(startDate, "-- 【yyyy-MM-dd】") + "-----" + car.getId() + " " + " 启动次数 " + startCount);
                for (int k = 0; k < startCount; k++) {
                    if(k==0){
                        _startDate=startDate;
                    }

                    String Stringtmp="INSERT INTO t_bt_drive VALUES (null, '{carId}', '{fireTime}', '{hotCarTime}', '{idlingTime}', '{drivingTime}', '{mileage}', '{totalMileage}', '{idlingOilUsed}', '{drivingOilUsed}', '{highestSpeed}', '{highestRotation}', '{speedUp}', '{speedDown}', '{costOfOil}', '{frameoutTime}');";
                    Stringtmp = Stringtmp.replace("{carId}", String.valueOf(car.getId()));
                    Date fireTime=addTime(_startDate, UtilTools.getNoBetween(0, 4), UtilTools.getNoBetween(1, 50), UtilTools.getNoBetween(10, 30));
                    _startDate=fireTime;


                    Stringtmp = Stringtmp.replace("{fireTime}", DateUtil.format(fireTime));
                    float hotCarTime= UtilTools.getNoBetween(2, 40);
                    Stringtmp = Stringtmp.replace("{hotCarTime}", String.valueOf(hotCarTime));
                    float idlingTime= UtilTools.getFloatNoBetween(3, 30, 1);
                    Stringtmp = Stringtmp.replace("{idlingTime}", String.valueOf(idlingTime));
                    float drivingTime= UtilTools.getFloatNoBetween(3, 62, 1);
                    Stringtmp = Stringtmp.replace("{drivingTime}", String.valueOf(drivingTime));
                    int _mileage= UtilTools.getNoBetween((int) drivingTime / 3, (int) drivingTime);
                    Stringtmp = Stringtmp.replace("{mileage}", String.valueOf(_mileage));
                    Stringtmp = Stringtmp.replace("{totalMileage}", String.valueOf(_mileage));

                    float idlingOilUsed=idlingTime*0.02f+0.01f;
                    Stringtmp = Stringtmp.replace("{idlingOilUsed}", String.valueOf(idlingOilUsed).substring(0,3));

                    float drivingOilUsed=_mileage*0.09f+0.02f;
                    Stringtmp = Stringtmp.replace("{drivingOilUsed}", String.valueOf(drivingOilUsed).substring(0,3));
                    Stringtmp = Stringtmp.replace("{highestSpeed}", String.valueOf(UtilTools.getNoBetween(35, 80)));
                    Stringtmp = Stringtmp.replace("{highestRotation}", String.valueOf(UtilTools.getNoBetween(2200, 4000)));
                    Stringtmp = Stringtmp.replace("{speedUp}", String.valueOf(UtilTools.getNoBetween(1, 3)-1));
                    Stringtmp = Stringtmp.replace("{speedDown}", String.valueOf(UtilTools.getNoBetween(1, 2)-1));
                    Stringtmp = Stringtmp.replace("{costOfOil}", String.valueOf(idlingOilUsed+drivingOilUsed).substring(0, 3));
                    Date frameoutTime = addTime(fireTime, 0, (int)(hotCarTime+idlingTime+drivingTime), UtilTools.getNoBetween(20, 50));
                    _startDate=frameoutTime;
                    Stringtmp = Stringtmp.replace("{frameoutTime}", DateUtil.format(frameoutTime));
                    tools.writeGloablTxt(Stringtmp);
                    count++;

                }

            }

        }


        tools.writeGloablTxt("-- 生成历史数据结束:" + " " + DateUtil.format(new Date())+"    共计生成记录数:"+count);

    }



    public Date addTime(Date date,int i,int j,int k){

        Date re=new Date(date.getTime()+1000*60*60*i+1000*60*j+1000*k);
        return re;
    }
}
