package com.obdGenerator80mile;


import java.io.*;
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
    public InitTool(String obdListFile,String logPath,String jdbcUrl,DBManager dbManager){
        this.obdListFile=obdListFile;
        this.dbManager=dbManager;
        this.logPath=logPath;
        tools=new Tools(logPath,"");
    }


    public List<VirtualThreadCar> getObdListFromTxt() {
        Date driveDeadLine = new Date();
        List<VirtualThreadCar> re = new ArrayList<VirtualThreadCar>();
        try {
            tools.writeGloablTxt("-- loading obd list from file:"+obdListFile);
            FileReader fr = new FileReader(obdListFile);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            String[] arrs = null;
            int i = 0;
            while ((line = br.readLine()) != null) {
                i++;
                arrs = line.split(",");
                if (arrs[0] != null && arrs[1] != null && !arrs[0].equals("null") && !arrs[1].equals("null")) {

                    re.add(new VirtualThreadCar(Integer.parseInt(arrs[0].trim()), arrs[1], DateUtil.parseDateStrToDate(arrs[2]),dbManager,logPath));
                }
            }
            br.close();
            fr.close();
        } catch (IOException e) {

        }
        tools.writeGloablTxt("-- loaded " + re.size() + " obd from file.");
    return re;
    }


}
