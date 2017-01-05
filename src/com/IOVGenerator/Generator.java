package com.IOVGenerator;



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
        if(args.length<7){
            System.out.println("need 7 runtime args(obdListFile，logPath，jdbcUrl，jdbcUser，jdbcPwd,batchSize,intervalSeconds)，exit!");
            return;
        }
        String obdListFile=args[0];
        String logPath=args[1];
        String jdbc=args[2];
        String jdbcUser=args[3];
        String jdbcPwd=args[4];

        int batchSize=Integer.parseInt(args[5]);
        int intervalSeconds=Integer.parseInt(args[6]);

        String jdbcUrl=jdbc+"?autoReconnect=true&useUnicode=true&characterEncoding=UTF8&user="+jdbcUser+"&password="+jdbcPwd+"";
        System.out.println("runtime args obdListPath "+obdListFile);
        System.out.println("runtime args logPath "+logPath);
        System.out.println("runtime args jdbcUrl "+jdbcUrl);
        System.out.println("runtime args batchSize "+batchSize);
        System.out.println("runtime args intervalSeconds "+intervalSeconds);

        System.out.println("---------------------------------------------");

        DBManager dbManager=new DBManager(logPath,jdbcUrl);
        dbManager.buildConnection();
        InitTool initTool=new InitTool(obdListFile,logPath,jdbcUrl,dbManager,batchSize,intervalSeconds);
        //初始化车辆
        List<VirtualThreadCar> virtualCarList=initTool.getVehicleListFromTxt();
        long start=new Date().getTime();
        Tools tools=new Tools(logPath,"");
        tools.writeGloablTxt("available car count:" + virtualCarList.size());

        for (int i = 0; i < virtualCarList.size(); i++) {
            VirtualThreadCar virtualCar=virtualCarList.get(i);
            Timer timer=new Timer();//
            timer.schedule(virtualCar,0);
        }

        Timer testMysqlTimer=new Timer();//从次日6点30开始工作
        TestMysqlTask testMysqlTask=new TestMysqlTask(dbManager,tools);
        testMysqlTimer.scheduleAtFixedRate(testMysqlTask, 10000, 1000 * 60 *60 );//10s后开始 每小时一次 1000 * 60 *60
        tools.writeGloablTxt("testMysqlTimer started.");
        long end=new Date().getTime();

    }
}

