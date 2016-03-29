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
            System.out.println("need 5 runtime args(obdListFile，logPath，jdbcUrl，jdbcUser，jdbcPwd)，exit!");
            return;
        }
        String obdListFile=args[0];
        String logPath=args[1];
        String jdbc=args[2];
        String jdbcUser=args[3];
        String jdbcPwd=args[4];
        String jdbcUrl=jdbc+"?autoReconnect=true&useUnicode=true&characterEncoding=UTF8&user="+jdbcUser+"&password="+jdbcPwd+"";
        System.out.println("runtime args obdListPath "+obdListFile);
        System.out.println("runtime args logPath "+logPath);
        System.out.println("runtime args jdbcUrl "+jdbcUrl);

        System.out.println("---------------------------------------------");

        DBManager dbManager=new DBManager(logPath,jdbcUrl);
        InitTool initTool=new InitTool(obdListFile,logPath,jdbcUrl,dbManager);
        //初始化车辆
        List<VirtualThreadCar> virtualCarList=initTool.initTripId(initTool.getObdListFromTxt());
        long start=new Date().getTime();
        Tools tools=new Tools(logPath,"");
        tools.writeGloablTxt("available car count:" + virtualCarList.size());
        for (int i = 0; i < virtualCarList.size(); i++) {
            VirtualThreadCar virtualCar=virtualCarList.get(i);
            Timer timer=new Timer();//正式模式从次日6点30开始工作
            Date firstStartDate=DateUtil.parseStrToDate(DateUtil.format(new Date(new Date().getTime() + 24 * 60 * 60 * 1000), "yyyy-MM-dd") + " 06:30:00");
            if(obdListFile.contains("test")){
                //测试模式即日运行
                firstStartDate=DateUtil.parseStrToDate(DateUtil.format(new Date(),"yyyy-MM-dd") + " 06:30:00");
            }
            tools.writeGloablTxt(virtualCar.getObdCode() + " will start at " + DateUtil.format(firstStartDate));
            timer.scheduleAtFixedRate(virtualCar, firstStartDate, 1000 * 60 * 60 * 24);//每天
        }

        Timer testMysqlTimer=new Timer();//从次日6点30开始工作
        TestMysqlTask testMysqlTask=new TestMysqlTask(dbManager,tools);
        testMysqlTimer.scheduleAtFixedRate(testMysqlTask, 10000, 1000 * 60 *60 );//10s后开始 每小时一次 1000 * 60 *60
        tools.writeGloablTxt("testMysqlTimer started.");
        long end=new Date().getTime();

    }
}
