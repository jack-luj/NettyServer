package com.IOVGenerator;




import java.util.Date;
import java.util.TimerTask;

/**
 * Created by Administrator on 2016/3/10.
 */
public class VirtualThreadCar extends TimerTask {

    private static int uuid=1;
    private int id;
    private String imei;
    private String vin;
    private String license;
    private String simNumber;
    private Date activeTime;
    private Date currentDate;
    private Date driveDeadLine;
    private int tripId;
    private int totalMileage;
    private Tools tools;
    private DBManager dbManager;
    private double centerLon=116.303875;//中心点经度
    private double centerLat=39.855168;//中心点纬度


    private int batchSize=3;
    private int intervalSeconds=5;
    //北京 116.403875,39.915168  39.855168,116.303875
    //常州 119.965034，31.8097941

    public VirtualThreadCar(int id, String imei, String vin, Date driveDeadLine, DBManager dbManager, String logPath,int batchSize,int intervalSeconds) {
        this.id = id;
        this.vin = vin;
        this.imei=imei;
        this.driveDeadLine=driveDeadLine;
        tripId=1;
        totalMileage= Tools.getNoBetween(3000, 12000);//车辆初始里程 km
        tools=new Tools(logPath,"");
        this.dbManager=dbManager;
        this.batchSize=batchSize;
        this.intervalSeconds=intervalSeconds;
    }


    public void run(){
        tools.writeGloablTxt(new Date().toLocaleString() + " start drive >>>" + this.getId() + "," + this.getVin() + "," + this.getTripId() + "," + this.getTotalMileage());
        driveOneDay();
    }


    /**
     * 车一天的使用情况
     */
    public void driveOneDay(){
        //todo 发送实时数据
        //todo 发送定位数据
        int sqlCount=0;
        StringBuilder gpsSb=new StringBuilder();
        gpsSb.append("INSERT INTO t_data_gps VALUES ");

        StringBuilder realDataSb=new StringBuilder();
        realDataSb.append("INSERT INTO t_data_realtime_report VALUES ");
        while(true){
            String sending_time=DateUtil.format(getCurrentDate());
            String gpsSql="(null, '{vin}', '{imei}', '34', '1', '{sending_time}', '0', 'N', 'E', '{lat}', '{lon}', '{speed}', '{heading}'),";
            gpsSql = gpsSql.replace("{vin}", vin);
            gpsSql = gpsSql.replace("{imei}", imei);
            gpsSql = gpsSql.replace("{sending_time}",sending_time);
            gpsSql = gpsSql.replace("{lat}", tools.cutString(String.valueOf(centerLat + Tools.getNoBetween(1000, 14000) * 0.000001), 11));
            gpsSql = gpsSql.replace("{lon}",tools.cutString(String.valueOf(centerLon + Tools.getNoBetween(1000, 12000) * 0.000001), 10));
            gpsSql = gpsSql.replace("{speed}", String.valueOf(tools.getNoBetween(0, 20)));
            gpsSql = gpsSql.replace("{heading}","0");
            gpsSb.append(gpsSql);

            ////实时数据
            String realDataSql="(null, '{vin}', '{imei}', '34', '1', '{sending_time}', '0', '0', '0', '-200', '{avg_oil_a}', '{avg_oil_b}', '-200', '0', '-200', '-200', '-200', '-200', '-200', '2', '2', '2', '2', '{vehicle_temperature}', '{vehicle_outer_temperature}', '1', '1', '0', '1', '1', '1', '1', '0', '{voltage}', '{average_speed_a}', '{average_speed_b}'),";
            realDataSql = realDataSql.replace("{vin}", vin);
            realDataSql = realDataSql.replace("{imei}", imei);
            realDataSql = realDataSql.replace("{sending_time}",sending_time);
            realDataSql = realDataSql.replace("{avg_oil_a}","7.8");
            realDataSql = realDataSql.replace("{avg_oil_b}","8.1");
            realDataSql = realDataSql.replace("{vehicle_temperature}","1");
            realDataSql = realDataSql.replace("{vehicle_outer_temperature}","0");
            realDataSql = realDataSql.replace("{voltage}","13."+tools.getWidthNo(1111,8888,4));
            realDataSql = realDataSql.replace("{average_speed_a}","43");
            realDataSql = realDataSql.replace("{average_speed_b}","33");


            realDataSql = realDataSql.replace("{heading}","0");
            sqlCount++;//合并执行
            realDataSb.append(realDataSql);
            if(sqlCount>=batchSize){

                gpsSb.delete(gpsSb.length() - 1, gpsSb.length());
                dbManager.executeUpdate(gpsSb.toString());
                tools.writeLocationTxt("合并执行:" + gpsSb.toString());
                gpsSb.delete(0, gpsSb.length());
                gpsSb.append("INSERT INTO t_data_gps VALUES ");

                realDataSb.delete(realDataSb.length() - 1, realDataSb.length());
                dbManager.executeUpdate(realDataSb.toString());
                tools.writeLocationTxt("合并执行:" + realDataSb.toString());
                sqlCount=0;
                realDataSb.delete(0, realDataSb.length());
                realDataSb.append("INSERT INTO t_data_realtime_report VALUES ");

            }

            addSeconds(intervalSeconds);//
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

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
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
