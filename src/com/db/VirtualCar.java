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
    private Date activeTime;

    public VirtualCar(int id, String obdCode, String sn, String license, Date activeTime) {
        this.id = id;
        this.obdCode = obdCode;
        this.sn = sn;
        this.license = license;
        this.activeTime = activeTime;
    }
    /**
     * 激活
     */
    private void active(){
        //todo 激活车辆
        System.out.println("激活:" + obdCode + " " + new Date().toLocaleString());
    }

    /**
     * 正常行驶
     */
    private void drive(){
        //todo 发送行驶数据
        //todo 发送定位数据
        System.out.println("启动:"+obdCode+" "+sn+" " +new Date().toLocaleString());
        System.out.println("行驶:" + obdCode + " " + sn + " " + new Date().toLocaleString());
        System.out.println("行驶:" + obdCode + " " + sn + " " + new Date().toLocaleString());
        System.out.println("行驶:" + obdCode + " " + sn + " " + new Date().toLocaleString());
        System.out.println("熄火:"+obdCode+" "+sn+" " +new Date().toLocaleString());
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

    public static  void main(String[] args){
        VirtualCar vr=new VirtualCar(1,"IN123456789","HUGY3671234567890","京N12345",new Date());
        vr.active();
        vr.drive();
    }
}
