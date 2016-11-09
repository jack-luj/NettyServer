package com.obdGenerator80mile;


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
        List<VirtualThreadCar> virtualCarList=initTool.getObdListFromTxt();
        long start=new Date().getTime();
        Tools tools=new Tools(logPath,"");
        tools.writeGloablTxt("-- available car count:" + virtualCarList.size());
        tools.writeGloablTxt("-- 从23:10:00开始生成当天行程数据" );
        /////////////////////////////////////////////////////////////////
        Timer timer=new Timer();//正式模式从23:10:00开始工作
        WorkTask workTask=new WorkTask(virtualCarList,dbManager,logPath);
        Date firstStartDate=DateUtil.parseStrToDate(DateUtil.format(new Date(),"yyyy-MM-dd") + " 13:10:00");
        timer.scheduleAtFixedRate(workTask, firstStartDate, 1000 * 60 * 60 * 24);//每天执行一次
        /////////////////////////////////////////////////////////////////////


        ////////////////////////////////////////////////////////////////////////
       /* List<Date> startDateList=new ArrayList<Date>();
        long _start=DateUtil.parseStrToDate("2016-07-03 08:10:07").getTime();
        long _end=DateUtil.parseStrToDate("2016-11-08 08:10:07").getTime();
        while (_start<_end){
           startDateList.add(new Date(_start));
            _start=_start+1000*60*60*24;
        }
        HisDataTask hisDataTask=new HisDataTask(virtualCarList,tools,dbManager,startDateList);
        hisDataTask.makeData();*/
        ///////////////////////////////////////////////////////////////////


        Timer testMysqlTimer=new Timer();//从次日6点30开始工作
        TestMysqlTask testMysqlTask=new TestMysqlTask(dbManager,tools);
        testMysqlTimer.scheduleAtFixedRate(testMysqlTask, 10000, 1000 * 60 *60 );//10s后开始 每小时一次 1000 * 60 *60
        tools.writeGloablTxt("testMysqlTimer started.");
        long end=new Date().getTime();

    }
}

