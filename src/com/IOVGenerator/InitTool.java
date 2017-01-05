package com.IOVGenerator;




import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jack lu on 2016/3/25.
 */
public class InitTool {
    private DBManager dbManager;
    private String obdListFile="";
    private String jdbcUrl="jdbc:mysql://localhost:3306/incarobd?user=root&password=123456&useUnicode=true&characterEncoding=UTF8";
    private Tools tools;
    private String logPath;
    private int batchSize;
    private int intervalSeconds;
    public InitTool(String obdListFile, String logPath, String jdbcUrl, DBManager dbManager,int batchSize,int intervalSeconds){
        this.obdListFile=obdListFile;
        this.dbManager=dbManager;
        this.logPath=logPath;
        this.batchSize=batchSize;
        this.intervalSeconds=intervalSeconds;
        tools=new Tools(logPath,"");
    }

    //从一个文本文件中读取需要处理的车辆信息列表,格式如下，一行一辆车
    //id,imei,vin
    public List<VirtualThreadCar> getVehicleListFromTxt() {
        Date driveDeadLine = new Date();
        List<VirtualThreadCar> re = new ArrayList<VirtualThreadCar>();
        try {
            tools.writeGloablTxt("loading obd list from file:"+obdListFile);
            FileReader fr = new FileReader(obdListFile);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            String[] arrs = null;
            int i = 0;
            while ((line = br.readLine()) != null) {
                i++;
                arrs = line.split(",");
                if (arrs[0] != null && arrs[1] != null && !arrs[0].equals("null") && !arrs[1].equals("null")) {
                    re.add(new VirtualThreadCar(Integer.valueOf(arrs[0]).intValue(), arrs[1],  arrs[2], driveDeadLine,dbManager,logPath,batchSize,intervalSeconds));
                }
            }
            br.close();
            fr.close();
        } catch (IOException e) {

        }
        tools.writeGloablTxt("loaded " + re.size() + " vehicle from file.");
        return re;
    }


}
