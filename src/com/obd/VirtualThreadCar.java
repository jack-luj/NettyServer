package com.obd;


import com.db.*;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.Date;
import java.util.TimerTask;

/**
 * Created by Administrator on 2016/3/10.
 */
public class VirtualThreadCar extends TimerTask {

    private static int uuid=1;
    private int id;
    private String obdCode;
    private String vin;
    private String license;
    private String simNumber;
    private Date activeTime;
    private Date currentDate;
    private Date driveDeadLine;
    private int tripId;
    private int totalMileage;
    private CarModel carModel;
    private Tools tools;
    private DBManager dbManager;

    public VirtualThreadCar(int id, String obdCode, String vin, Date driveDeadLine,DBManager dbManager) {
        this.id = id;
        this.obdCode = obdCode;
        this.vin = vin;
        this.driveDeadLine=driveDeadLine;
        tripId=1;
        totalMileage= Tools.getNoBetween(3000, 12000);//车辆初始里程 km
        tools=new Tools("");
        this.dbManager=dbManager;
    }


    public void run(){
        Tools.writeGloablTxt(new Date().toLocaleString() + " drive one day>>>" + this.getId() + "," + this.getObdCode() + "," + this.getTripId() + "," + this.getTotalMileage());
        driveOneDay();
    }


    /**
     * 车一天的使用情况
     */
    public void driveOneDay(){
        //todo 发送行驶数据
        //todo 发送定位数据

        int startCount= Tools.getNoBetween(1, 7)-1;//车一天启动0~6次
        startCount=1;
        Tools.writeGloablTxt(DateUtil.format(getCurrentDate(), "-- 【yyyy-MM-dd】") + "-----" + obdCode + " " + " 启动次数 " + startCount);
        for (int i = 0; i < startCount; i++) {
            int drive_id=0;
            int lastSpeed=0;
            int startDistance=0;
            int alreadyDriveSeconds=0;
            Date startRecordTime=getCurrentDate();
            //addHours(Tools.getNoBetween(1, 3) - 1);
            //addMinutes(Tools.getNoBetween(1, 50));
            addSeconds(Tools.getNoBetween(1, 50));

            Tools.writeGloablTxt("-- >>>启动一次:" + obdCode + " " + vin + " " + DateUtil.format(getCurrentDate()));
            //String Stringtmp="{drive_iid}#{obdCode}#{tripId}#{id}#{vin}#{fireTime}#8.9#0#0#W000.000000#S00.000000#0#1970-01-02 00:00:00#0#{driveTime}#{currentMileage}# {currentAvgOil}# {mileage}# {avgOil}# '[{\\\"speed\\\":1,\\\"time\\\":{time1},\\\"distance\\\":{distance1}},{\\\"speed\\\":45,\\\"time\\\":{time2},\\\"distance\\\":{distance2}},{\\\"speed\\\":90,\\\"time\\\":{time3},\\\"distance\\\":{distance3}},{\\\"speed\\\":255,\\\"time\\\":{time4},\\\"distance\\\":{distance4}}]'#0#{speedUp}#0#0#{maxSpeed}#0#{currentMileage}#E{lon}#N{lat}#0#{flameOutLocationTime}#0#10.2#3#{flameOutTime}#{lastUpdateTime}";
            String Stringtmp="INSERT INTO t_obd_drive VALUES (null, '{obdCode}', '{tripId}', (select id from t_car where obd_code='{obdCode}' ), '{vin}', '{fireTime}', '8.9', '0', '0', 'W000.000000', 'S00.000000', '0', '1970-01-02 00:00:00', '0', '{driveTime}', '{currentMileage}', '{currentAvgOil}', '{mileage}', '{avgOil}', '[{\\\"speed\\\":1,\\\"time\\\":{time1},\\\"distance\\\":{distance1}},{\\\"speed\\\":45,\\\"time\\\":{time2},\\\"distance\\\":{distance2}},{\\\"speed\\\":90,\\\"time\\\":{time3},\\\"distance\\\":{distance3}},{\\\"speed\\\":255,\\\"time\\\":{time4},\\\"distance\\\":{distance4}}]', '0', '{speedUp}', '0', '0', '{maxSpeed}', '0', '{currentMileage}', 'E{lon}', 'N{lat}', '0', '{flameOutLocationTime}', '0', '10.2', '3', '{flameOutTime}', '{lastUpdateTime}');";

            drive_id=uuid;
            Stringtmp = Stringtmp.replace("{drive_iid}", String.valueOf(drive_id));
            uuid++;
            Stringtmp = Stringtmp.replace("{obdCode}", obdCode);
            Stringtmp = Stringtmp.replace("{tripId}", String.valueOf(tripId));
            Stringtmp = Stringtmp.replace("{id}", String.valueOf(id));
            Stringtmp = Stringtmp.replace("{vin}", vin);
            Stringtmp = Stringtmp.replace("{fireTime}",DateUtil.format(getCurrentDate()));
            Stringtmp = Stringtmp.replace("{currentAvgOil}",String.valueOf(Tools.getNoBetween(800, 1800)));
            Stringtmp = Stringtmp.replace("{avgOil}",String.valueOf(Tools.getNoBetween(1000, 2500)));
            Stringtmp = Stringtmp.replace("{speedUp}",String.valueOf(Tools.getNoBetween(1, 2) - 1));
            Stringtmp = Stringtmp.replace("{maxSpeed}",String.valueOf(Tools.getNoBetween(10, 80)));

            Stringtmp = Stringtmp.replace("{lon}", Tools.cutString(String.valueOf(119.965034 + Tools.getNoBetween(1000, 8000) * 0.000001), 11));
            Stringtmp = Stringtmp.replace("{lat}", Tools.cutString(String.valueOf(31.8097941 + Tools.getNoBetween(1000, 8000) * 0.000001), 10));

            startRecordTime=getCurrentDate();
            int countPerTrip= Tools.getNoBetween(10, 30);

            //需要记录依次行程的累计行驶时间

            Date tripStartDate=getCurrentDate();//每次行程的开始时间
            Date tripendDate=getCurrentDate();//每次行程的结束时间
            for (int j = 0; j < countPerTrip; j++) {
                int second= Tools.getNoBetween(5, 13);//
                addSeconds(second);

                String s="INSERT INTO t_obd_location VALUES (null, '{obdCode}', '{tripId}', (select id from t_car where obd_code='{obdCode}' ), '{vin}', '{locationSpeed}', '{distance}', 'E{lon}', 'N{lat}', '{direction}', '{locationTime}', '2', '{recordTime}');";
                s = s.replace("{location_iid}", String.valueOf(uuid));
                uuid++;
                s = s.replace("{obdCode}", obdCode);
                s = s.replace("{tripId}", String.valueOf(tripId));
                s = s.replace("{id}", String.valueOf(id));
                s=s.replace("{vin}", vin);
                int speed= Tools.getNoBetween(1, 90);
                if(Math.abs(speed-lastSpeed)>36){
                    speed=speed/2;
                }
                lastSpeed=speed;
                s = s.replace("{locationSpeed}", String.valueOf(speed));
                s = s.replace("{distance}", String.valueOf(startDistance));
                s = s.replace("{lon}", Tools.cutString(String.valueOf(119.965034 + Tools.getNoBetween(1000, 8000) * 0.000001), 11));
                s = s.replace("{lat}", Tools.cutString(String.valueOf(31.8097941 + Tools.getNoBetween(1000, 8000) * 0.000001), 10));
                s = s.replace("{direction}", String.valueOf(Tools.getNoBetween(1, 300)));
                s = s.replace("{locationTime}", DateUtil.format(DateUtil.gmt8toUTC(getCurrentDate())));
                if(j%5==0){
                    startRecordTime=new Date(getCurrentDate().getTime()+1000* Tools.getNoBetween(40, 150));
                }
                s = s.replace("{recordTime}", DateUtil.format(startRecordTime));
                startDistance=startDistance+ Tools.getNoBetween(20, 90);
                tools.writeLocationTxt(s);
                dbManager.executeUpdate(s);
                alreadyDriveSeconds=alreadyDriveSeconds+second;
                if (alreadyDriveSeconds>500){
                    break;
                }
            }
            tripendDate=getCurrentDate();
            totalMileage=totalMileage+startDistance/1000;
            Stringtmp = Stringtmp.replace("{currentMileage}",String.valueOf(startDistance));//本次行驶里程
            Stringtmp = Stringtmp.replace("{mileage}",String.valueOf(totalMileage));
            Stringtmp = Stringtmp.replace("{driveTime}",String.valueOf(alreadyDriveSeconds));

            int time1= Tools.getNoBetween(alreadyDriveSeconds / 4, alreadyDriveSeconds / 3);
            int time2= Tools.getNoBetween(alreadyDriveSeconds / 3, alreadyDriveSeconds / 2);
            int time3=alreadyDriveSeconds-time1-time2;
            int time4=0;
            int distance1= Tools.getNoBetween(startDistance / 4, startDistance / 3);
            int distance2= Tools.getNoBetween(startDistance / 3, startDistance / 2);
            int distance3=startDistance-distance1-distance2;
            int distance4=0;
            Stringtmp = Stringtmp.replace("{time1}",String.valueOf(time1));
            Stringtmp = Stringtmp.replace("{distance1}",String.valueOf(distance1));
            Stringtmp = Stringtmp.replace("{time2}",String.valueOf(time2));
            Stringtmp = Stringtmp.replace("{distance2}",String.valueOf(distance2));
            Stringtmp = Stringtmp.replace("{time3}",String.valueOf(time3));
            Stringtmp = Stringtmp.replace("{distance3}",String.valueOf(distance3));
            Stringtmp = Stringtmp.replace("{time4}",String.valueOf(time4));
            Stringtmp = Stringtmp.replace("{distance4}",String.valueOf(distance4));

            Stringtmp = Stringtmp.replace("{flameOutTime}",DateUtil.format(new Date(getCurrentDate().getTime() + 1000 * alreadyDriveSeconds)));
            Stringtmp = Stringtmp.replace("{lastUpdateTime}",DateUtil.format(new Date(getCurrentDate().getTime() + 3 + 1000 * alreadyDriveSeconds)));
            Stringtmp = Stringtmp.replace("{flameOutLocationTime}",DateUtil.format(DateUtil.gmt8toUTC(new Date(getCurrentDate().getTime() + 1000 * 60 * Tools.getNoBetween(5, 10)))));

            tools.writeDriveTxt(Stringtmp);
            dbManager.executeUpdate(Stringtmp);
            String condition="INSERT INTO t_obd_condition VALUES (null, '{obdCode}', '{tripId}', (select id from t_car where obd_code='{obdCode}' ), '{vin}', '[]', '[{\\\"id\\\":0,\\\"value\\\":\\\"13.39\\\"},{\\\"id\\\":1,\\\"value\\\":\\\"0\\\"},{\\\"id\\\":2,\\\"value\\\":\\\"关\\\"},{\\\"id\\\":4,\\\"value\\\":\\\"CL\\\"},{\\\"id\\\":5,\\\"value\\\":\\\"---\\\"},{\\\"id\\\":6,\\\"value\\\":\\\"63.5\\\"},{\\\"id\\\":7,\\\"value\\\":\\\"85\\\"},{\\\"id\\\":8,\\\"value\\\":\\\"-2.3\\\"},{\\\"id\\\":10,\\\"value\\\":\\\"3.1\\\"},{\\\"id\\\":17,\\\"value\\\":\\\"29\\\"},{\\\"id\\\":18,\\\"value\\\":\\\"816\\\"},{\\\"id\\\":19,\\\"value\\\":\\\"0\\\"},{\\\"id\\\":20,\\\"value\\\":\\\"12\\\"},{\\\"id\\\":21,\\\"value\\\":\\\"48\\\"},{\\\"id\\\":23,\\\"value\\\":\\\"14.5\\\"},{\\\"id\\\":25,\\\"value\\\":\\\"O2S12 | O2S11\\\"},{\\\"id\\\":26,\\\"value\\\":\\\"0.090\\\"},{\\\"id\\\":27,\\\"value\\\":\\\"-2.3\\\"},{\\\"id\\\":28,\\\"value\\\":\\\"0.700\\\"},{\\\"id\\\":74,\\\"value\\\":\\\"EOBD\\\"},{\\\"id\\\":125,\\\"value\\\":\\\"280\\\"},{\\\"id\\\":126,\\\"value\\\":\\\"0\\\"},{\\\"id\\\":133,\\\"value\\\":\\\"255\\\"},{\\\"id\\\":134,\\\"value\\\":\\\"30434\\\"},{\\\"id\\\":141,\\\"value\\\":\\\"无效\\\"},{\\\"id\\\":142,\\\"value\\\":\\\"无效\\\"},{\\\"id\\\":143,\\\"value\\\":\\\"有效\\\"},{\\\"id\\\":144,\\\"value\\\":\\\"完成\\\"},{\\\"id\\\":145,\\\"value\\\":\\\"完成\\\"},{\\\"id\\\":146,\\\"value\\\":\\\"完成\\\"},{\\\"id\\\":147,\\\"value\\\":\\\"无效\\\"},{\\\"id\\\":148,\\\"value\\\":\\\"无效\\\"},{\\\"id\\\":149,\\\"value\\\":\\\"无效\\\"},{\\\"id\\\":150,\\\"value\\\":\\\"无效\\\"},{\\\"id\\\":151,\\\"value\\\":\\\"无效\\\"},{\\\"id\\\":152,\\\"value\\\":\\\"无效\\\"},{\\\"id\\\":153,\\\"value\\\":\\\"无效\\\"},{\\\"id\\\":154,\\\"value\\\":\\\"无效\\\"},{\\\"id\\\":155,\\\"value\\\":\\\"未完成\\\"},{\\\"id\\\":156,\\\"value\\\":\\\"未完成\\\"},{\\\"id\\\":157,\\\"value\\\":\\\"未完成\\\"},{\\\"id\\\":158,\\\"value\\\":\\\"未完成\\\"},{\\\"id\\\":159,\\\"value\\\":\\\"未完成\\\"},{\\\"id\\\":160,\\\"value\\\":\\\"未完成\\\"},{\\\"id\\\":161,\\\"value\\\":\\\"未完成\\\"},{\\\"id\\\":162,\\\"value\\\":\\\"未完成\\\"},{\\\"id\\\":163,\\\"value\\\":\\\"13.444\\\"},{\\\"id\\\":167,\\\"value\\\":\\\"36\\\"}]', '{recordTime}');";
            condition = condition.replace("{condition_iid}", String.valueOf(uuid));
            uuid++;
            condition = condition.replace("{obdCode}", obdCode);
            condition = condition.replace("{tripId}", String.valueOf(tripId));
            condition = condition.replace("{id}", String.valueOf(id));
            condition = condition.replace("{vin}", vin);
            condition = condition.replace("{recordTime}", DateUtil.format(new Date(getCurrentDate().getTime() + 1000 * Tools.getNoBetween(40, 150))));
            tools.writeConditionTxt(condition);
            dbManager.executeUpdate(condition);


            long startMillseconds=tripStartDate.getTime();//确保行程开始后才产生detail数据
            long endMillseconds=tripendDate.getTime();
            while (startMillseconds<endMillseconds){
                String driveDetail="INSERT INTO t_drive_detail VALUES (null, '{obdCode}', (select id from t_obd_drive where obdCode='{obdCode}' and tripId={tripId}), '[{\"id\":257,\"value\":\"305\"},{\"id\":258,\"value\":\"2012\"},{\"id\":259,\"value\":\"3850\"},{\"id\":260,\"value\":\"12.5\"},{\"id\":262,\"value\":\"2461\"},{\"id\":263,\"value\":\"200.2\"},{\"id\":264,\"value\":\"8.1\"},{\"id\":0,\"value\":\"1.4\"},{\"id\":1,\"value\":\"0\"},{\"id\":2,\"value\":\"关\"},{\"id\":4,\"value\":\"CL\"},{\"id\":5,\"value\":\"CL\"},{\"id\":6,\"value\":\"65.1\"},{\"id\":7,\"value\":\"77\"},{\"id\":8,\"value\":\"-1.6\"},{\"id\":10,\"value\":\"-4.7\"},{\"id\":12,\"value\":\"0.8\"},{\"id\":14,\"value\":\"-4.7\"},{\"id\":18,\"value\":\"1588\"},{\"id\":19,\"value\":\"45\"},{\"id\":20,\"value\":\"19\"},{\"id\":21,\"value\":\"17\"},{\"id\":22,\"value\":\"14.05\"},{\"id\":23,\"value\":\"21.2\"},{\"id\":25,\"value\":\"O2S22 | O2S21 | O2S12 | O2S11\"},{\"id\":26,\"value\":\"0.150\"},{\"id\":27,\"value\":\"-1.6\"},{\"id\":28,\"value\":\"0.735\"},{\"id\":34,\"value\":\"0.085\"},{\"id\":35,\"value\":\"-3.9\"},{\"id\":36,\"value\":\"0.770\"},{\"id\":74,\"value\":\"EOBD\"},{\"id\":125,\"value\":\"307\"},{\"id\":126,\"value\":\"0\"},{\"id\":131,\"value\":\"0.0\"},{\"id\":132,\"value\":\"0.0\"},{\"id\":133,\"value\":\"255\"},{\"id\":134,\"value\":\"22082\"},{\"id\":137,\"value\":\"483.9\"},{\"id\":138,\"value\":\"483.9\"},{\"id\":139,\"value\":\"500.8\"},{\"id\":140,\"value\":\"500.8\"},{\"id\":163,\"value\":\"14.147\"},{\"id\":165,\"value\":\"1.000\"},{\"id\":167,\"value\":\"13\"},{\"id\":168,\"value\":\"16.1\"},{\"id\":170,\"value\":\"15.3\"},{\"id\":171,\"value\":\"15.7\"},{\"id\":173,\"value\":\"3.9\"}]', '{recordTime}');";
                driveDetail = driveDetail.replace("{detail_iid}", String.valueOf(uuid));
                uuid++;
                driveDetail = driveDetail.replace("{obdCode}", obdCode);
                driveDetail = driveDetail.replace("{drive_id}", String.valueOf(drive_id));
                driveDetail = driveDetail.replace("{tripId}", String.valueOf(tripId));
                driveDetail = driveDetail.replace("{recordTime}", DateUtil.format(new Date(startMillseconds)));
                tools.writeDetailTxt(driveDetail);
                dbManager.executeUpdate(driveDetail);
                startMillseconds=startMillseconds+1*60000;//间隔5分钟发送一次detail数据
            }

            Tools.writeGloablTxt("-- 本次启动后行驶时间:" + obdCode + " " + alreadyDriveSeconds + " 秒:");
            Tools.writeGloablTxt("-- 全天行驶结束熄火:" + obdCode + " " + vin + " " + DateUtil.format(getCurrentDate()));
            tripId++;
        }

    }


    /**
     * 初始化时钟
     */
    public void initDate(Date date){
        currentDate=date;
    }

    /**
     * 时钟加小时
     */
    public void addHours(int i){
        //currentDate=new Date(currentDate.getTime()+1000*60*60*i);
        try{
            Thread.sleep(1000*60*60*i);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    /**
     * 时钟加分钟
     */
    public void addMinutes(int i){
        //currentDate=new Date(currentDate.getTime()+1000*60*i);
        try{
            Thread.sleep(1000*60*i);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    /**
     * 时钟加秒
     */
    public void addSeconds(int i){
        //currentDate=new Date(currentDate.getTime()+1000*i);
        try{
            Thread.sleep(1000*i);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
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

        return new Date();
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

    public Date getDriveDeadLine() {
        return driveDeadLine;
    }

    public void setDriveDeadLine(Date driveDeadLine) {
        this.driveDeadLine = driveDeadLine;
    }

    public CarModel getCarModel() {
        return carModel;
    }

    public void setCarModel(CarModel carModel) {
        this.carModel = carModel;
    }

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public int getId() {
        return id;
    }

    public int getTotalMileage() {
        return totalMileage;
    }

    public void setTotalMileage(int totalMileage) {
        this.totalMileage = totalMileage;
    }
}
