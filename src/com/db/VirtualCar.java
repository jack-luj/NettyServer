package com.db;

import java.util.Date;

/**
 * Created by Administrator on 2016/3/10.
 */
public class VirtualCar {

    private int id;
    private String obdCode;
    private String vin;
    private String license;
    private String simNumber;
    private Date activeTime;
    private Date currentDate;
    private int tripId;
    private int totalMileage;

    public VirtualCar(int id, String obdCode, String vin, String license,String simNumber, Date activeTime) {
        this.id = id;
        this.obdCode = obdCode;
        this.vin = vin;
        this.license = license;
        this.simNumber=simNumber;
        this.activeTime = activeTime;
        tripId=1;
        totalMileage=Tools.getNoBetween(8000,16000);
    }


    public void enable(){
        active();
        initDate(activeTime);
        for (int i = 0; i <3 ; i++) {//持续的天数 2015-6-2016-3
           driveOneDay();
        }
        Tools.writeTxt("");
    }
    /**
     * 激活
     */

    public void active(){
        //todo 激活车辆
        Tools.writeTxt("-- 激活车辆:" + " " + license + " " + obdCode + " " + vin + " " + " " + simNumber + " " + DateUtil.format(activeTime));
        String Stringtmp="INSERT INTO t_car VALUES (null, '1', '{2}', '{0}', '1', '{1}', {3}, '94', '5', '2014', 'L', '1.40', '0', '2013-09-05', '', '2014-09-05', 'V1.50.00', 'V0.00.00', 'V3.13.15', '255', '0', 'service.incardata.com.cn', '9005', '0', '0', null);";
        Stringtmp = Stringtmp.replace("{0}", obdCode);
        Stringtmp = Stringtmp.replace("{1}",DateUtil.format(activeTime));
        Stringtmp=Stringtmp.replace("{2}",license);
        Stringtmp=Stringtmp.replace("{3}",simNumber);
        Tools.writeTxt(Stringtmp);
    }

    /**
     * 车一天的使用情况
     */
    public void driveOneDay(){
        //todo 发送行驶数据
        //todo 发送定位数据
        addDate(1);
        resetTime();
        if(currentDate.getTime()>new Date().getTime()){
            return;
        }

        int startCount=Tools.getNoBetween(1, 7)-1;
        Tools.writeTxt(DateUtil.format(currentDate, "-- 【yyyy-MM-dd】") + "-----" + obdCode + " " + " 启动次数 " + startCount);
        for (int i = 0; i < startCount; i++) {
            int lastSpeed=0;
            int startDistance=0;
            int alreadyDriveSeconds=0;
            Date startRecordTime=currentDate;
            addHours(Tools.getNoBetween(1, 3) - 1);addMinutes(Tools.getNoBetween(1, 50)); addSeconds(Tools.getNoBetween(1, 50));
           // int driveTimeSeconds=Tools.getNoBetween(15, 5340);//每次行驶时间：0.25分钟——89分钟
            Tools.writeTxt("-- >>>启动一次:" + obdCode + " " + vin + " " + DateUtil.format(currentDate));
                        String Stringtmp="INSERT INTO t_obd_drive VALUES (null, '{obdCode}', '{tripId}', (select id from t_car where obd_code='{obdCode}' ), '{vin}', '{fireTime}', '8.9', '0', '0', 'W000.000000', 'S00.000000', '0', '1970-01-02 00:00:00', '0', '{driveTime}', '{currentMileage}', '{currentAvgOil}', '{mileage}', '{avgOil}', '[{\\\"speed\\\":1,\\\"time\\\":44,\\\"distance\\\":1},{\\\"speed\\\":45,\\\"time\\\":208,\\\"distance\\\":570},{\\\"speed\\\":90,\\\"time\\\":0,\\\"distance\\\":0},{\\\"speed\\\":255,\\\"time\\\":0,\\\"distance\\\":0}]', '0', '{speedUp}', '0', '0', '{maxSpeed}', '0', '{currentMileage}', 'E{lon}', 'N{lat}', '0', '{flameOutLocationTime}', '0', '10.2', '3', '{flameOutTime}', '{lastUpdateTime}');";
            Stringtmp = Stringtmp.replace("{obdCode}", obdCode);
            Stringtmp = Stringtmp.replace("{tripId}", String.valueOf(tripId));
            Stringtmp = Stringtmp.replace("{vin}", vin);
            Stringtmp = Stringtmp.replace("{fireTime}",DateUtil.format(currentDate));
            Stringtmp = Stringtmp.replace("{currentAvgOil}",String.valueOf(Tools.getNoBetween(800, 1800)));
            Stringtmp = Stringtmp.replace("{avgOil}",String.valueOf(Tools.getNoBetween(1000,2500)));
            Stringtmp = Stringtmp.replace("{speedUp}",String.valueOf(Tools.getNoBetween(1, 2) - 1));
            Stringtmp = Stringtmp.replace("{maxSpeed}",String.valueOf(Tools.getNoBetween(10, 80)));
            Stringtmp = Stringtmp.replace("{lon}", Tools.cutString(String.valueOf(119.965034 + Tools.getNoBetween(1000, 8000) * 0.000001), 11));
            Stringtmp = Stringtmp.replace("{lat}", Tools.cutString(String.valueOf(31.8097941 + Tools.getNoBetween(1000, 8000) * 0.000001), 10));

            startRecordTime=currentDate;
            int countPerTrip=Tools.getNoBetween(10,300);

            //需要记录依次行程的累计行驶时间
            for (int j = 0; j < countPerTrip; j++) {
                int second=Tools.getNoBetween(10, 18);
                addSeconds(second);
                String s="INSERT INTO t_obd_location VALUES (null, '{obdCode}', '{tripId}', (select id from t_car where obd_code='{obdCode}' ), '{vin}', '{locationSpeed}', '{distance}', 'E{lon}', 'N{lat}', '{direction}', '{locationTime}', '2', '{recordTime}');";
                s = s.replace("{obdCode}", obdCode);
                s = s.replace("{tripId}", String.valueOf(tripId));
                s=s.replace("{vin}", vin);
                int speed=Tools.getNoBetween(1,90);
                if(Math.abs(speed-lastSpeed)>36){
                    speed=speed/2;
                }
                lastSpeed=speed;
                s = s.replace("{locationSpeed}", String.valueOf(speed));
                s = s.replace("{distance}", String.valueOf(startDistance));
                s = s.replace("{lon}", Tools.cutString(String.valueOf(119.965034 + Tools.getNoBetween(1000, 8000) * 0.000001), 11));
                s = s.replace("{lat}", Tools.cutString(String.valueOf(31.8097941 + Tools.getNoBetween(1000, 8000) * 0.000001), 10));
                s = s.replace("{direction}", String.valueOf(Tools.getNoBetween(1, 300)));
                s = s.replace("{locationTime}", DateUtil.format(DateUtil.gmt8toUTC(currentDate)));
                if(j%5==0){
                    startRecordTime=new Date(currentDate.getTime()+1000*Tools.getNoBetween(40,150));
                }
                s = s.replace("{recordTime}", DateUtil.format(startRecordTime));
                startDistance=startDistance+Tools.getNoBetween(20,90);
                Tools.writeTxt(s);
                alreadyDriveSeconds=alreadyDriveSeconds+second;
                if (alreadyDriveSeconds>5000){
                    break;
                }
            }
            totalMileage=totalMileage+startDistance;
            Stringtmp = Stringtmp.replace("{currentMileage}",String.valueOf(totalMileage));
            Stringtmp = Stringtmp.replace("{mileage}",String.valueOf(startDistance));
            Stringtmp = Stringtmp.replace("{driveTime}",String.valueOf(alreadyDriveSeconds));
            Stringtmp = Stringtmp.replace("{flameOutTime}",DateUtil.format(new Date(currentDate.getTime() + 1000 * alreadyDriveSeconds)));
            Stringtmp = Stringtmp.replace("{lastUpdateTime}",DateUtil.format(new Date(currentDate.getTime() + 3 + 1000 * alreadyDriveSeconds)));
            Stringtmp = Stringtmp.replace("{flameOutLocationTime}",DateUtil.format(DateUtil.gmt8toUTC(new Date(currentDate.getTime() + 2 + 1000 * alreadyDriveSeconds))));

            Tools.writeTxt(Stringtmp);
            Tools.writeTxt("-- 本次启动后行驶时间:" + obdCode + " " + alreadyDriveSeconds + " 秒:");
            Tools.writeTxt("-- 熄火:" + obdCode + " " + vin + " " + DateUtil.format(currentDate));

            tripId++;
        }

    }


    /**
     * 初始化时钟至指定时分秒
     */
    public void resetTime(String time){
        currentDate=DateUtil.parseStrToDate(DateUtil.format(currentDate, "yyyy-MM-dd" + " "+time));
    }
    /**
     * 初始化时钟至每天06:18:19
     */
    public void resetTime(){
        resetTime("06:30:00");
    }
    /**
     * 初始化时钟
     */
    public void initDate(Date date){
        currentDate=date;
    }
    /**
     * 时钟加天
     */
    public void addDate(int i){
        currentDate=new Date(currentDate.getTime()+1000*60*60*24*i);
    }
    /**
     * 时钟加小时
     */
    public void addHours(int i){
        currentDate=new Date(currentDate.getTime()+1000*60*60*i);
    }
    /**
     * 时钟加分钟
     */
    public void addMinutes(int i){
        currentDate=new Date(currentDate.getTime()+1000*60*i);
    }
    /**
     * 时钟加秒
     */
    public void addSeconds(int i){
        currentDate=new Date(currentDate.getTime()+1000*i);
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getObdCode() {
        return obdCode;
    }

    public void setObdCode(String obdCode) {
        this.obdCode = obdCode;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public Date getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(Date activeTime) {
        this.activeTime = activeTime;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public String getSimNumber() {
        return simNumber;
    }

    public void setSimNumber(String simNumber) {
        this.simNumber = simNumber;
    }
}
