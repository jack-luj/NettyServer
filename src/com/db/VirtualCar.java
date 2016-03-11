package com.db;

import java.util.Date;

/**
 * Created by Administrator on 2016/3/10.
 */
public class VirtualCar {

    private int id;
    private String obdCode;
    private String sn;
    private String license;
    private String simNumber;
    private Date activeTime;
    private Date currentDate;

    public VirtualCar(int id, String obdCode, String sn, String license,String simNumber, Date activeTime) {
        this.id = id;
        this.obdCode = obdCode;
        this.sn = sn;
        this.license = license;
        this.simNumber=simNumber;
        this.activeTime = activeTime;
    }


    public void enable(){
        active();
        initDate(activeTime);
        /*for (int i = 0; i <1 ; i++) {//持续的天数
           drive();
        }*/
        System.out.println();
    }
    /**
     * 激活
     */

    public void active(){
        //todo 激活车辆
        System.out.println("-- 激活车辆:" + " "+license+" "+obdCode + " "+sn+" "+ " "+simNumber+" "+ DateUtil.format(activeTime));
        String Stringtmp="INSERT INTO t_car VALUES (null, '1', '{2}', '{0}', '1', '{1}', {3}, '94', '5', '2014', 'L', '1.40', '0', '2013-09-05', '', '2014-09-05', 'V1.50.00', 'V0.00.00', 'V3.13.15', '255', '0', 'service.incardata.com.cn', '9005', '0', '0', null);";
        Stringtmp = Stringtmp.replace("{0}", obdCode);
        Stringtmp = Stringtmp.replace("{1}",DateUtil.format(activeTime));
        Stringtmp=Stringtmp.replace("{2}",license);
        Stringtmp=Stringtmp.replace("{3}",simNumber);
        System.out.println(Stringtmp);
    }

    /**
     * 车一天的使用情况
     */
    public void drive(){
        //todo 发送行驶数据
        //todo 发送定位数据
        addDate(1);
        resetTime();
        int startCount=Tools.getNoBetween(1, 7)-1;
        System.out.println(DateUtil.format(currentDate, "【yyyy-MM-dd】")+"-----"+obdCode+" "+" 启动次数 "+startCount);
        for (int i = 0; i < startCount; i++) {
            addHours(Tools.getNoBetween(1, 3) - 1);addMinutes(Tools.getNoBetween(1, 30)); addSeconds(Tools.getNoBetween(1, 30));
            System.out.println(">>>启动:"+obdCode+" "+sn+" " +DateUtil.format(currentDate));
            int driveTimeSeconds=Tools.getNoBetween(15, 5340);//每次行驶时间：0.25分钟——89分钟
            int driveCount=Tools.getNoBetween(2, 5);
            System.out.println("本次启动后行驶时间:" + obdCode + " "+driveTimeSeconds+"   次数:"+driveCount);
            for (int j = 0; j < driveCount; j++) {
                addMinutes(Tools.getNoBetween(1, 30)); addSeconds(Tools.getNoBetween(1, 30));
                System.out.println("行驶:" + obdCode + " " + sn + " " + DateUtil.format(currentDate));
            }
            System.out.println("熄火:"+obdCode+" "+sn+" " +DateUtil.format(currentDate));
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
        resetTime("06:18:19");
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

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
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
