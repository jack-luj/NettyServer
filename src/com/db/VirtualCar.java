package com.db;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2016/3/10.
 */
public class VirtualCar {

    private static int uuid=1;
    private int id;
    private int s4_id;
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

    public VirtualCar(String groupId,int id,int s4_id, String obdCode, String vin, String license,String simNumber, Date activeTime, Date driveDeadLine,CarModel carModel) {
        this.id = id;
        this.s4_id=s4_id;
        this.obdCode = obdCode;
        this.vin = vin;
        this.license = license;
        this.simNumber=simNumber;
        this.activeTime = activeTime;
        this.driveDeadLine=driveDeadLine;
        tripId=1;
        totalMileage=Tools.getNoBetween(3000,12000);//车辆初始里程 km
        this.carModel=carModel;
        tools=new Tools(groupId);
    }


    public void enable(){
        active();
        initDate(activeTime);
        int driveDays=DateUtil.diffDate(activeTime,driveDeadLine);
        for (int i = 0; i <driveDays-1 ; i++) {//持续的天数 2015-6-2016-3
         driveOneDay();
        }
       // tools.writeTxt("");
    }
    /**
     * 激活
     */

    public void active(){
        //todo 激活车辆
        Tools.writeGloablTxt("-- 激活车辆:" + " " + license + " " + obdCode + " " + vin + " " + " " + simNumber + " " + DateUtil.format(activeTime));
        String Stringtmp="INSERT INTO t_car VALUES ({id}, '{s4_id}', '{2}', '{0}', '0', null, {3}, '{brandId}', '{seriesId}', '{yearModel}', '{engineType}', '{disp}', '0', null, '', '{1}', 'V1.50.00', 'V0.00.00', 'V3.13.15', '255', '0', 'service.incardata.com.cn', '9005', '0', '0', null, null);";

        String Stringfile="{id},1,{2},{0},1,null,{3},{brandId},{seriesId},{yearModel},{engineType},{disp},0,2013-09-05,,2014-09-05,V1.50.00,V0.00.00,V3.13.15,255,0,service.incardata.com.cn,9005,0,0,0";


        Stringtmp = Stringtmp.replace("{id}", String.valueOf(id));
        Stringtmp = Stringtmp.replace("{s4_id}", String.valueOf(s4_id));
        Stringfile = Stringfile.replace("{id}", String.valueOf(id));
    if(obdCode.equals("")){
        Stringtmp = Stringtmp.replace("'{0}'", "null"); Stringfile = Stringfile.replace("'{0}'", "null");
    }else{
        Stringtmp = Stringtmp.replace("{0}", obdCode); Stringfile = Stringfile.replace("{0}", obdCode);
    }

        Stringtmp = Stringtmp.replace("{1}",DateUtil.formatDate(activeTime)); Stringfile = Stringfile.replace("{1}",DateUtil.format(activeTime));
        Stringtmp=Stringtmp.replace("{2}",license);  Stringfile=Stringfile.replace("{2}",license);
        Stringtmp=Stringtmp.replace("{3}","null"); Stringfile=Stringfile.replace("{3}",simNumber);

        Stringtmp=Stringtmp.replace("{brandId}",String.valueOf(carModel.getBrand())); Stringfile=Stringfile.replace("{brandId}",String.valueOf(carModel.getBrand()));
        Stringtmp=Stringtmp.replace("{seriesId}",String.valueOf(carModel.getSeries())); Stringfile=Stringfile.replace("{seriesId}",String.valueOf(carModel.getSeries()));
        Stringtmp=Stringtmp.replace("{yearModel}",String.valueOf(carModel.getModelYear())); Stringfile=Stringfile.replace("{yearModel}",String.valueOf(carModel.getModelYear()));
        Stringtmp=Stringtmp.replace("{engineType}",String.valueOf(carModel.getEnginrType()));Stringfile=Stringfile.replace("{engineType}",String.valueOf(carModel.getEnginrType()));
        Stringtmp=Stringtmp.replace("{disp}",carModel.getDisp());   Stringfile=Stringfile.replace("{disp}",carModel.getDisp());
        tools.writeCarTxt(Stringfile);
        Tools.writeGloablTxt(Stringtmp);
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

        int startCount=Tools.getNoBetween(1, 7)-1;//车一天启动0~6次
        Tools.writeGloablTxt(DateUtil.format(currentDate, "-- 【yyyy-MM-dd】") + "-----" + obdCode + " " + " 启动次数 " + startCount);
        for (int i = 0; i < startCount; i++) {
            int drive_id=0;
            int lastSpeed=0;
            int startDistance=0;
            int alreadyDriveSeconds=0;
            Date startRecordTime=currentDate;
            addHours(Tools.getNoBetween(1, 3) - 1);addMinutes(Tools.getNoBetween(1, 50)); addSeconds(Tools.getNoBetween(1, 50));

            Tools.writeGloablTxt("-- >>>启动一次:" + obdCode + " " + vin + " " + DateUtil.format(currentDate));
            String Stringtmp="{drive_iid}#{obdCode}#{tripId}#{id}#{vin}#{fireTime}#8.9#0#0#W000.000000#S00.000000#0#1970-01-02 00:00:00#0#{driveTime}#{currentMileage}# {currentAvgOil}# {mileage}# {avgOil}# '[{\\\"speed\\\":1,\\\"time\\\":{time1},\\\"distance\\\":{distance1}},{\\\"speed\\\":45,\\\"time\\\":{time2},\\\"distance\\\":{distance2}},{\\\"speed\\\":90,\\\"time\\\":{time3},\\\"distance\\\":{distance3}},{\\\"speed\\\":255,\\\"time\\\":{time4},\\\"distance\\\":{distance4}}]'#0#{speedUp}#0#0#{maxSpeed}#0#{currentMileage}#E{lon}#N{lat}#0#{flameOutLocationTime}#0#10.2#3#{flameOutTime}#{lastUpdateTime}";
          //  String Stringfile="null, {obdCode}, {tripId}, {id}, {vin}, {fireTime}, 8.9, 0, 0, W000.000000, S00.000000, 0, 1970-01-02 00:00:00, 0, {driveTime}, {currentMileage}, {currentAvgOil}, {mileage}, {avgOil}, [{\\\"speed\\\":1,\\\"time\\\":{time1},\\\"distance\\\":{distance1}},{\\\"speed\\\":45,\\\"time\\\":{time2},\\\"distance\\\":{distance2}},{\\\"speed\\\":90,\\\"time\\\":{time3},\\\"distance\\\":{distance3}},{\\\"speed\\\":255,\\\"time\\\":{time4},\\\"distance\\\":{distance4}}], 0, {speedUp}, 0, 0, {maxSpeed}, 0, {currentMileage}, E{lon}, N{lat}, 0, {flameOutLocationTime}, 0, 10.2, 3, {flameOutTime}, {lastUpdateTime})";
            drive_id=uuid;
            Stringtmp = Stringtmp.replace("{drive_iid}", String.valueOf(drive_id));
            uuid++;
            Stringtmp = Stringtmp.replace("{obdCode}", obdCode);
            Stringtmp = Stringtmp.replace("{tripId}", String.valueOf(tripId));
            Stringtmp = Stringtmp.replace("{id}", String.valueOf(id));
            Stringtmp = Stringtmp.replace("{vin}", vin);
            Stringtmp = Stringtmp.replace("{fireTime}",DateUtil.format(currentDate));
            Stringtmp = Stringtmp.replace("{currentAvgOil}",String.valueOf(Tools.getNoBetween(800, 1800)));
            Stringtmp = Stringtmp.replace("{avgOil}",String.valueOf(Tools.getNoBetween(1000, 2500)));
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
                String s="{location_iid},{obdCode},{tripId},{id},{vin},{locationSpeed},{distance},E{lon},N{lat},{direction},{locationTime},2,{recordTime}";
                s = s.replace("{location_iid}", String.valueOf(uuid));
                uuid++;
                s = s.replace("{obdCode}", obdCode);
                s = s.replace("{tripId}", String.valueOf(tripId));
                s = s.replace("{id}", String.valueOf(id));
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
                tools.writeLocationTxt(s);
                alreadyDriveSeconds=alreadyDriveSeconds+second;
                if (alreadyDriveSeconds>5000){
                    break;
                }
            }
            totalMileage=totalMileage+startDistance/1000;
            Stringtmp = Stringtmp.replace("{currentMileage}",String.valueOf(startDistance));//本次行驶里程
            Stringtmp = Stringtmp.replace("{mileage}",String.valueOf(totalMileage));
            Stringtmp = Stringtmp.replace("{driveTime}",String.valueOf(alreadyDriveSeconds));

            int time1=Tools.getNoBetween(alreadyDriveSeconds/4,alreadyDriveSeconds/3);
            int time2=Tools.getNoBetween(alreadyDriveSeconds/3,alreadyDriveSeconds/2);
            int time3=alreadyDriveSeconds-time1-time2;
            int time4=0;
            int distance1=Tools.getNoBetween(startDistance/4,startDistance/3);
            int distance2=Tools.getNoBetween(startDistance/3,startDistance/2);
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

            Stringtmp = Stringtmp.replace("{flameOutTime}",DateUtil.format(new Date(currentDate.getTime() + 1000 * alreadyDriveSeconds)));
            Stringtmp = Stringtmp.replace("{lastUpdateTime}",DateUtil.format(new Date(currentDate.getTime() + 3 + 1000 * alreadyDriveSeconds)));
            Stringtmp = Stringtmp.replace("{flameOutLocationTime}",DateUtil.format(DateUtil.gmt8toUTC(new Date(currentDate.getTime() + 1000*60 * Tools.getNoBetween(5,10)))));

            tools.writeDriveTxt(Stringtmp);

            String condition="{condition_iid}#{obdCode}#{tripId}#{id}#{vin}#[]#[{\\\"id\\\":0,\\\"value\\\":\\\"13.39\\\"},{\\\"id\\\":1,\\\"value\\\":\\\"0\\\"},{\\\"id\\\":2,\\\"value\\\":\\\"关\\\"},{\\\"id\\\":4,\\\"value\\\":\\\"CL\\\"},{\\\"id\\\":5,\\\"value\\\":\\\"---\\\"},{\\\"id\\\":6,\\\"value\\\":\\\"63.5\\\"},{\\\"id\\\":7,\\\"value\\\":\\\"85\\\"},{\\\"id\\\":8,\\\"value\\\":\\\"-2.3\\\"},{\\\"id\\\":10,\\\"value\\\":\\\"3.1\\\"},{\\\"id\\\":17,\\\"value\\\":\\\"29\\\"},{\\\"id\\\":18,\\\"value\\\":\\\"816\\\"},{\\\"id\\\":19,\\\"value\\\":\\\"0\\\"},{\\\"id\\\":20,\\\"value\\\":\\\"12\\\"},{\\\"id\\\":21,\\\"value\\\":\\\"48\\\"},{\\\"id\\\":23,\\\"value\\\":\\\"14.5\\\"},{\\\"id\\\":25,\\\"value\\\":\\\"O2S12 | O2S11\\\"},{\\\"id\\\":26,\\\"value\\\":\\\"0.090\\\"},{\\\"id\\\":27,\\\"value\\\":\\\"-2.3\\\"},{\\\"id\\\":28,\\\"value\\\":\\\"0.700\\\"},{\\\"id\\\":74,\\\"value\\\":\\\"EOBD\\\"},{\\\"id\\\":125,\\\"value\\\":\\\"280\\\"},{\\\"id\\\":126,\\\"value\\\":\\\"0\\\"},{\\\"id\\\":133,\\\"value\\\":\\\"255\\\"},{\\\"id\\\":134,\\\"value\\\":\\\"30434\\\"},{\\\"id\\\":141,\\\"value\\\":\\\"无效\\\"},{\\\"id\\\":142,\\\"value\\\":\\\"无效\\\"},{\\\"id\\\":143,\\\"value\\\":\\\"有效\\\"},{\\\"id\\\":144,\\\"value\\\":\\\"完成\\\"},{\\\"id\\\":145,\\\"value\\\":\\\"完成\\\"},{\\\"id\\\":146,\\\"value\\\":\\\"完成\\\"},{\\\"id\\\":147,\\\"value\\\":\\\"无效\\\"},{\\\"id\\\":148,\\\"value\\\":\\\"无效\\\"},{\\\"id\\\":149,\\\"value\\\":\\\"无效\\\"},{\\\"id\\\":150,\\\"value\\\":\\\"无效\\\"},{\\\"id\\\":151,\\\"value\\\":\\\"无效\\\"},{\\\"id\\\":152,\\\"value\\\":\\\"无效\\\"},{\\\"id\\\":153,\\\"value\\\":\\\"无效\\\"},{\\\"id\\\":154,\\\"value\\\":\\\"无效\\\"},{\\\"id\\\":155,\\\"value\\\":\\\"未完成\\\"},{\\\"id\\\":156,\\\"value\\\":\\\"未完成\\\"},{\\\"id\\\":157,\\\"value\\\":\\\"未完成\\\"},{\\\"id\\\":158,\\\"value\\\":\\\"未完成\\\"},{\\\"id\\\":159,\\\"value\\\":\\\"未完成\\\"},{\\\"id\\\":160,\\\"value\\\":\\\"未完成\\\"},{\\\"id\\\":161,\\\"value\\\":\\\"未完成\\\"},{\\\"id\\\":162,\\\"value\\\":\\\"未完成\\\"},{\\\"id\\\":163,\\\"value\\\":\\\"13.444\\\"},{\\\"id\\\":167,\\\"value\\\":\\\"36\\\"}]#{recordTime}";
            condition = condition.replace("{condition_iid}", String.valueOf(uuid));
            uuid++;
            condition = condition.replace("{obdCode}", obdCode);
            condition = condition.replace("{tripId}", String.valueOf(tripId));
            condition = condition.replace("{id}", String.valueOf(id));
            condition = condition.replace("{vin}", vin);
            condition = condition.replace("{recordTime}", DateUtil.format(new Date(currentDate.getTime() + 1000 * Tools.getNoBetween(40, 150))));
            tools.writeConditionTxt(condition);

            String driveDetail="{detail_iid}#{obdCode}#{drive_id}#[{\"id\":257,\"value\":\"305\"},{\"id\":258,\"value\":\"2012\"},{\"id\":259,\"value\":\"3850\"},{\"id\":260,\"value\":\"12.5\"},{\"id\":262,\"value\":\"2461\"},{\"id\":263,\"value\":\"200.2\"},{\"id\":264,\"value\":\"8.1\"},{\"id\":0,\"value\":\"1.4\"},{\"id\":1,\"value\":\"0\"},{\"id\":2,\"value\":\"关\"},{\"id\":4,\"value\":\"CL\"},{\"id\":5,\"value\":\"CL\"},{\"id\":6,\"value\":\"65.1\"},{\"id\":7,\"value\":\"77\"},{\"id\":8,\"value\":\"-1.6\"},{\"id\":10,\"value\":\"-4.7\"},{\"id\":12,\"value\":\"0.8\"},{\"id\":14,\"value\":\"-4.7\"},{\"id\":18,\"value\":\"1588\"},{\"id\":19,\"value\":\"45\"},{\"id\":20,\"value\":\"19\"},{\"id\":21,\"value\":\"17\"},{\"id\":22,\"value\":\"14.05\"},{\"id\":23,\"value\":\"21.2\"},{\"id\":25,\"value\":\"O2S22 | O2S21 | O2S12 | O2S11\"},{\"id\":26,\"value\":\"0.150\"},{\"id\":27,\"value\":\"-1.6\"},{\"id\":28,\"value\":\"0.735\"},{\"id\":34,\"value\":\"0.085\"},{\"id\":35,\"value\":\"-3.9\"},{\"id\":36,\"value\":\"0.770\"},{\"id\":74,\"value\":\"EOBD\"},{\"id\":125,\"value\":\"307\"},{\"id\":126,\"value\":\"0\"},{\"id\":131,\"value\":\"0.0\"},{\"id\":132,\"value\":\"0.0\"},{\"id\":133,\"value\":\"255\"},{\"id\":134,\"value\":\"22082\"},{\"id\":137,\"value\":\"483.9\"},{\"id\":138,\"value\":\"483.9\"},{\"id\":139,\"value\":\"500.8\"},{\"id\":140,\"value\":\"500.8\"},{\"id\":163,\"value\":\"14.147\"},{\"id\":165,\"value\":\"1.000\"},{\"id\":167,\"value\":\"13\"},{\"id\":168,\"value\":\"16.1\"},{\"id\":170,\"value\":\"15.3\"},{\"id\":171,\"value\":\"15.7\"},{\"id\":173,\"value\":\"3.9\"}]#{recordTime}";
            driveDetail = driveDetail.replace("{detail_iid}", String.valueOf(uuid));
            uuid++;
            driveDetail = driveDetail.replace("{obdCode}", obdCode);
            driveDetail = driveDetail.replace("{drive_id}", String.valueOf(drive_id));
            driveDetail = driveDetail.replace("{tripId}", String.valueOf(tripId));
            driveDetail = driveDetail.replace("{recordTime}", DateUtil.format(new Date(currentDate.getTime() + 1000 * Tools.getNoBetween(40, 150))));

            tools.writeDetailTxt(driveDetail);
            Tools.writeGloablTxt("-- 本次启动后行驶时间:" + obdCode + " " + alreadyDriveSeconds + " 秒:");
            Tools.writeGloablTxt("-- 熄火:" + obdCode + " " + vin + " " + DateUtil.format(currentDate));

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
}
