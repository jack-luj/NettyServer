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

    public VirtualCar(){

    }

    /**
     * 激活
     */
    private void active(){
        //todo 激活车辆
    }

    /**
     * 正常行驶
     */
    private void drive(){
        //todo 发送行驶数据
        //todo 发送定位数据
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
}
