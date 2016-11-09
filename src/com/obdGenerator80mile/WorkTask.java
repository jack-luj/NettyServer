package com.obdGenerator80mile;

import java.util.Date;
import java.util.List;
import java.util.TimerTask;

/**
 * Created by jackl on 2016/11/8.
 */
public class WorkTask extends TimerTask {
    List<VirtualThreadCar> virtualCarList;
    private UtilTools tools;
    private DBManager dbManager;
    private Date currentDate;
    public WorkTask(List<VirtualThreadCar> virtualCarList,DBManager dbManager,String logPath) {
        this.virtualCarList = virtualCarList;
        tools=new UtilTools(logPath,"");
        this.dbManager=dbManager;
    }

    @Override
    public void run() {
        tools.writeGloablTxt("--开始生成数据，现在时间:" + " " + DateUtil.format(new Date()));
        Date startDate=DateUtil.parseStrToDate(DateUtil.format(new Date(), "yyyy-MM-dd") + " 07:11:00");
        tools.writeGloablTxt("--模拟开始时间:" + " " + DateUtil.format(startDate));
        makeData(startDate);
    }




    public void makeData(Date startDate){
        int count=0;
        tools.writeGloablTxt("-- 开始生成一天历史数据:" + " " + DateUtil.format(startDate));

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
                    Stringtmp = Stringtmp.replace("{drivingOilUsed}", String.valueOf(drivingOilUsed).substring(0, 3));
                    Stringtmp = Stringtmp.replace("{highestSpeed}", String.valueOf(UtilTools.getNoBetween(35, 80)));
                    Stringtmp = Stringtmp.replace("{highestRotation}", String.valueOf(UtilTools.getNoBetween(2200, 4000)));
                    Stringtmp = Stringtmp.replace("{speedUp}", String.valueOf(UtilTools.getNoBetween(1, 3)-1));
                    Stringtmp = Stringtmp.replace("{speedDown}", String.valueOf(UtilTools.getNoBetween(1, 2) - 1));
                    Stringtmp = Stringtmp.replace("{costOfOil}", String.valueOf(idlingOilUsed+drivingOilUsed).substring(0, 3));
                    Date frameoutTime = addTime(fireTime, 0, (int)(hotCarTime+idlingTime+drivingTime), UtilTools.getNoBetween(20, 50));
                    _startDate=frameoutTime;
                    Stringtmp = Stringtmp.replace("{frameoutTime}", DateUtil.format(frameoutTime));
                    tools.writeGloablTxt(Stringtmp);
                    dbManager.executeUpdate(Stringtmp);
                    count++;

                }
            }
        tools.writeGloablTxt("-- 生成一天历史数据结束:" + " " + DateUtil.format(startDate)+"    共计生成记录数:"+count);

    }




    public List<VirtualThreadCar> getVirtualCarList() {
        return virtualCarList;
    }

    public void setVirtualCarList(List<VirtualThreadCar> virtualCarList) {
        this.virtualCarList = virtualCarList;
    }

    public UtilTools getTools() {
        return tools;
    }

    public void setTools(UtilTools tools) {
        this.tools = tools;
    }

    public DBManager getDbManager() {
        return dbManager;
    }

    public void setDbManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    public Date getCurrentDate() {
        if(currentDate==null){
            return new Date();
        }
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }



    public Date addTime(Date date,int i,int j,int k){

        Date re=new Date(date.getTime()+1000*60*60*i+1000*60*j+1000*k);
        return re;
    }

    /**
     * 时钟加小时
     */
    public Date addHours(Date date,int i){

        Date re=new Date(date.getTime()+1000*60*60*i);
        return re;
    }
    /**
     * 时钟加分钟
     */
    public Date addMinutes(Date date,int i){

        Date re=new Date(date.getTime()+1000*60*i);
        return re;
    }
    /**
     * 时钟加秒
     */
    public Date addSeconds(Date date,int i){

        Date re=new Date(date.getTime()+1000*i);
        return re;
    }


}
