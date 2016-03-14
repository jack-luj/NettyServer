package com.db;

import javax.tools.Tool;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by jack lu on 2016/3/4.
 */
public class BuildLanduData {

    public static  void main(String[] args){

        TimeZone tz = TimeZone.getTimeZone("America/Los_Angeles");

        Date a=DateUtil.parseStrToDate("2015-10-11 06:30:00");
        Date b=DateUtil.parseStrToDate("2015-10-13 06:31:00");
        System.out.println(DateUtil.diffDate(a,b));

    }

    public void buildData(){
       // initCar();
        fireData();
        locationData("INCAR10014436446");
    }

    public void fireData(){
        //行车数据
        String Stringtmp="INSERT INTO t_obd_drive VALUES (null, 'INCAR10014436446', '18', '5', 'LSVFV6183D2200932', '2014-06-07 17:02:09', '8.9', '0', '0', 'W000.000000', 'S00.000000', '0', '1970-01-02 00:00:00', '0', '248', '571', '2037', '2', '2353', '[{\\\"speed\\\":1,\\\"time\\\":44,\\\"distance\\\":1},{\\\"speed\\\":45,\\\"time\\\":208,\\\"distance\\\":570},{\\\"speed\\\":90,\\\"time\\\":0,\\\"distance\\\":0},{\\\"speed\\\":255,\\\"time\\\":0,\\\"distance\\\":0}]', '0', '0', '0', '0', '27', '0', '0', 'W0.000000', 'S0.000000', '0', '2014-07-07 17:06:22', '0', null, '3', '2014-06-07 17:06:17', '2014-07-07 17:06:45');";
    }

    public void locationData(String obdId){
        //定位数据
        int startTripId=Tools.getNoBetween(10,100);
        int startDistance=0;
        int lastSpeed=0;
        int vid=0;
        Date startLocatinoTime=DateUtil.parseStrToDate("2015-06-07 17:02:09");
        Date startRecordTime=DateUtil.parseStrToDate("2015-06-07 17:03:09");

        int countEveryDay=startTripId=Tools.getNoBetween(1,7)-1;//每天0-6
        int plus=0;
        for (int i = 0; i < 20; i++) {
            String s="INSERT INTO t_obd_location VALUES (null, '{0}', '{1}', '53', 'LSVFV6183D2200933', '{locationSpeed}', '{distance}', 'E{lon}', 'N{lat}', '{direction}', '{locationTime}', '2', '{recordTime}');";
            s = s.replace("{0}", obdId);
            s = s.replace("{1}", String.valueOf(startTripId));
            int a=Tools.getNoBetween(1,1000);
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
            s = s.replace("{locationTime}", DateUtil.format(new Date(startLocatinoTime.getTime() + i * 10 * 1000)));

            if((i+1)%8==0){
             plus=90;
            }
            s = s.replace("{recordTime}", DateUtil.format(new Date(startRecordTime.getTime()+i*plus*1000)));
            startTripId++;
            startDistance=startDistance+Tools.getNoBetween(2,70);
            vid++;
            System.out.println(s);
        }
    }

    public String getSql(String s,int id,String date,int minDate,int maxDate,int minHour,int maxHour){
        String obdId="INCAR"+id+""+Tools.getRandom4int();
        s = s.replace("{0}", obdId);
        s = s.replace("{1}",DateUtil.format(DateUtil.parseStrToDate(date+ Tools.getWidthNo(minDate,maxDate,2)+" "+Tools.getWidthNo(8,19,2)+":"+Tools.getWidthNo(minHour,maxHour,2)+":"+Tools.getWidthNo(1,59,2)+"")));
        s=s.replace("{2}","京NX"+Tools.getRandom4int());
        return s;
    }
    public List<String> initCar(){
        //生成
        int startObdcode=1001000;
        String Stringtmp="INSERT INTO t_car VALUES (null, '1', '{2}', '{0}', '1', '{1}', '15827289341', '94', '5', '2014', 'L', '1.40', '0', '2013-09-05', '', '2014-09-05', 'V1.50.00', 'V0.00.00', 'V3.13.15', '255', '0', 'service.incardata.com.cn', '9005', '0', '0', null);";
        List<String> sqls=new ArrayList<String>();
        int count20156=Tools.getNoBetween(20,30);
        int count20157=Tools.getNoBetween(20,30);
        int count20158=Tools.getNoBetween(20,30);
        int count20159=Tools.getNoBetween(30,40);
        int count201510=Tools.getNoBetween(30,40);
        int count201511=Tools.getNoBetween(40,60);
        int count201512=Tools.getNoBetween(40,60);
        int count20161=Tools.getNoBetween(50,70);
        int count20162=Tools.getNoBetween(50,70);
        int count20163=Tools.getNoBetween(50,70);
        System.out.println("-- "+count20156+count20157+count20158+count20159+count201510+count201511+count201512+count20161+count20162+count20163);
        System.out.println("-- 常州2015年6月："+count20156+"个");
        for (int i = 0; i < count20156; i++) {
            String s=Stringtmp+"";
            int id=startObdcode+1;
            String sql=getSql(s,id,"2015-06-",1,30,8,19);
            System.out.println(sql);
            sqls.add(sql);
            startObdcode++;
        }
        System.out.println("-- 常州2015年7月："+count20157+"个");
        for (int i = 0; i < count20157; i++) {
            String s=Stringtmp+"";
            int id=startObdcode+1;
            String sql=getSql(s,id,"2015-07-",1,30,8,19);
            System.out.println(sql);
            sqls.add(sql);
            startObdcode++;
        }
        System.out.println("-- 常州2015年8月："+count20158+"个");
        for (int i = 0; i < count20158; i++) {
            String s=Stringtmp+"";
            int id=startObdcode+1;
            String sql=getSql(s,id,"2015-08-",1,30,8,19);
            System.out.println(sql);
            sqls.add(sql);
            startObdcode++;
        }
        System.out.println("-- 常州2015年9月："+count20159+"个");
        for (int i = 0; i < count20159; i++) {
            String s=Stringtmp+"";
            int id=startObdcode+1;
            String sql=getSql(s,id,"2015-09-",1,30,8,19);
            System.out.println(sql);
            sqls.add(sql);
            startObdcode++;
        }
        System.out.println("-- 常州2015年10月："+count201510+"个");
        for (int i = 0; i < count201510; i++) {
            String s=Stringtmp+"";
            int id=startObdcode+1;
            String sql=getSql(s,id,"2015-10-",1,30,8,19);
            System.out.println(sql);
            sqls.add(sql);
            startObdcode++;
        }
        System.out.println("-- 常州2015年11月："+count201511+"个");
        for (int i = 0; i < count201511; i++) {
            String s=Stringtmp+"";
            int id=startObdcode+1;
            String sql=getSql(s,id,"2015-11-",1,30,8,19);
            System.out.println(sql);
            sqls.add(sql);
            startObdcode++;
        }
        System.out.println("-- 常州2015年12月："+count201512+"个");
        for (int i = 0; i < count201512; i++) {
            String s=Stringtmp+"";
            int id=startObdcode+1;
            String sql=getSql(s,id,"2015-12-",1,30,8,19);
            System.out.println(sql);
            sqls.add(sql);
            startObdcode++;
        }
        System.out.println("-- 常州2016年1月："+count20161+"个");
        for (int i = 0; i < count20161; i++) {
            String s=Stringtmp+"";
            int id=startObdcode+1;
            String sql=getSql(s,id,"2016-01-",1,30,8,19);
            System.out.println(sql);
            sqls.add(sql);
            startObdcode++;
        }
        System.out.println("-- 常州2016年2月："+count20162+"个");
        for (int i = 0; i < count20162; i++) {
            String s=Stringtmp+"";
            int id=startObdcode+1;
            String sql=getSql(s,id,"2016-02-",1,30,8,19);
            System.out.println(sql);
            sqls.add(sql);
            startObdcode++;
        }
        System.out.println("-- 常州2016年3月："+count20163+"个");
        for (int i = 0; i < count20163; i++) {
            String s=Stringtmp+"";
            int id=startObdcode+1;
            String sql=getSql(s,id,"2016-03-",1,12,8,19);
            System.out.println(sql);
            sqls.add(sql);
            startObdcode++;
        }

        //
        System.out.println("-- 共计："+sqls.size()+"个");
        return sqls;
    }





}
