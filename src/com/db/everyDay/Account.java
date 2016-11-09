package com.db.everyDay;

import com.db.*;
import com.db.Tools;

import java.util.Date;

/**
 * Created by jackl on 2016/11/7.
 */
public class Account {
    private int accountId;
    private int carId;
    private int relationId;
    private int s4_id=809;
    private int status=1;
    private String name;
    private String pwd="";
    private String nick;
    private String phone;
    private int sex=0;
    private String last_login_time="";
    private String last_login_src="";


    public Account(int accountId, int carId, int relationId, int s4_id, String name,  String nick, String phone) {
        this.accountId = accountId;
        this.carId=carId;
        this.relationId = relationId;
        this.s4_id = s4_id;
        this.name = name;
        this.nick = nick;
        this.phone = phone;
    }

    public void addAccount(){
        //todo 激活车辆
        Tools.writeGloablTxt("-- 建立用户:" + " " + s4_id + " " + status + " " + name + " " + " " + pwd + " " +nick+ " " +phone);
        String Stringtmp="INSERT INTO t_account VALUES ({id}, '{s4_id}', '{status}', '{name}', '{pwd}', null,null,'','{nick}', null, '{phone}', '0', null, null, null, null, null, null, null);";
        Stringtmp = Stringtmp.replace("{id}", String.valueOf(accountId));
        Stringtmp = Stringtmp.replace("{s4_id}", String.valueOf(s4_id));
        Stringtmp = Stringtmp.replace("{status}", String.valueOf(status));
        Stringtmp=Stringtmp.replace("{name}",name);
        Stringtmp=Stringtmp.replace("{pwd}",pwd);
        Stringtmp=Stringtmp.replace("{nick}",name);
        Stringtmp=Stringtmp.replace("{phone}",phone);
        //Stringtmp=Stringtmp.replace("{last_login_time}",last_login_time);
        //Stringtmp=Stringtmp.replace("{last_login_src}",last_login_src);
        Tools.writeGloablTxt(Stringtmp);
    }


    public  void addRelation(){
        Tools.writeGloablTxt("-- 建立车辆车主关系:" + " " + s4_id + " " + status + " " + name + " " + " " + pwd + " " +nick+ " " +phone);
        String Stringtmp="INSERT INTO t_car_user VALUES ( '{s4_id}',{acc_id},{car_id},1,null,{id});";
        Stringtmp = Stringtmp.replace("{s4_id}", String.valueOf(s4_id));
        Stringtmp = Stringtmp.replace("{acc_id}", String.valueOf(accountId));
        Stringtmp = Stringtmp.replace("{car_id}", String.valueOf(carId));
        //Stringtmp = Stringtmp.replace("{join_time}", "");
        Stringtmp = Stringtmp.replace("{id}", String.valueOf(relationId));
        Tools.writeGloablTxt(Stringtmp);
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getRelationId() {
        return relationId;
    }

    public void setRelationId(int relationId) {
        this.relationId = relationId;
    }

    public int getS4_id() {
        return s4_id;
    }

    public void setS4_id(int s4_id) {
        this.s4_id = s4_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getLast_login_time() {
        return last_login_time;
    }

    public void setLast_login_time(String last_login_time) {
        this.last_login_time = last_login_time;
    }

    public String getLast_login_src() {
        return last_login_src;
    }

    public void setLast_login_src(String last_login_src) {
        this.last_login_src = last_login_src;
    }
}
