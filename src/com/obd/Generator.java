package com.obd;


import java.util.Date;
import java.util.List;
import java.util.Timer;

/**
 * Created by jack lu on 2016/3/11.
 */
public class Generator {

    public static  void main(String[] args){
        //需要运行时提供的参数
        //obd_list path: d:\db\obd_list.txt
        // logPath: d:\db\
        //jdbc url:   jdbc:mysql://localhost:3306/incarobd?useUnicode=true&characterEncoding=UTF8&user=root&password=123456
        //user:  root
        //password: 123456
        //
        if(args.length<5){
            System.out.println("需要指定5个参数（obdListFile，logPath，jdbcUrl，jdbcUser，jdbcPwd），参数异常,无法运行!");
            return;
        }
        String obdListFile=args[0];
        String logPath=args[1];
        String jdbc=args[2];//jdbc:mysql://localhost:3306/incarobd?useUnicode=true&characterEncoding=UTF8
        String jdbcUser=args[3];
        String jdbcPwd=args[4];
        String jdbcUrl=jdbc+"?useUnicode=true&characterEncoding=UTF8&user="+jdbcUser+"&password="+jdbcPwd+"";
        System.out.println("运行参数obdListPath "+obdListFile);
        System.out.println("运行参数logPath "+logPath);
        System.out.println("运行参数jdbcUrl "+jdbcUrl);

        System.out.println("---------------------------------------------");


        InitTool initTool=new InitTool(obdListFile,logPath,jdbcUrl);
        //初始化车辆
        List<VirtualThreadCar> virtualCarList=initTool.initTripId(initTool.getObdListFromTxt());
        long start=new Date().getTime();
        Tools tools=new Tools(logPath,"");
        tools.writeGloablTxt("available car count:" + virtualCarList.size());
        for (int i = 0; i < virtualCarList.size(); i++) {
            VirtualThreadCar virtualCar=virtualCarList.get(i);
            Timer timer=new Timer();//从次日6点30开始工作
            Date firstStartDate=DateUtil.parseStrToDate(DateUtil.format(new Date(new Date().getTime()+24*60*60*1000),"yyyy-MM-dd") + " 06:30:00");
            tools.writeGloablTxt(virtualCar.getObdCode()+" 程序启动时间 " + DateUtil.format(firstStartDate));
            timer.scheduleAtFixedRate(virtualCar,firstStartDate,1000*60*60*24);//每天
        }
        long end=new Date().getTime();
        tools.writeGloablTxt("-- 耗时:" + (end - start) + "ms");
    }
}
