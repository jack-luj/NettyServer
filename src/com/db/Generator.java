package com.db;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jack lu on 2016/3/11.
 */
public class Generator {

    public static  void main(String[] args){
        File log=new File("d:\\sql.txt");
        log.delete();
        int startObdcode=1001000;

        int count20156=Tools.getNoBetween(20,30);
        List<Integer> countList=new ArrayList<Integer>();
        List<String> preDateStrList=new ArrayList<String>();
        countList.add(Tools.getNoBetween(20,30));
        countList.add(Tools.getNoBetween(20,30));
        countList.add(Tools.getNoBetween(20,30));
        countList.add(Tools.getNoBetween(30,40));
        countList.add(Tools.getNoBetween(30,40));
        countList.add(Tools.getNoBetween(40,60));
        countList.add(Tools.getNoBetween(40,60));
        countList.add(Tools.getNoBetween(50,70));
        countList.add(Tools.getNoBetween(50,70));
        countList.add(Tools.getNoBetween(50,70));
        preDateStrList.add("2015-06-");
        preDateStrList.add("2015-07-");
        preDateStrList.add("2015-08-");
        preDateStrList.add("2015-09-");
        preDateStrList.add("2015-10-");
        preDateStrList.add("2015-11-");
        preDateStrList.add("2015-12-");
        preDateStrList.add("2016-01-");
        preDateStrList.add("2016-02-");
        preDateStrList.add("2016-03-");



        for (int i = 0; i < 1; i++) {//preDateStrList.size();
            System.out.println("-- "+preDateStrList.get(i)+"共计激活：" + countList.get(i) + "辆");
            for (int j = 0; j < 1; j++) {//countList.get(i)
                String obdCode="INCAR"+String.valueOf(startObdcode+1)+""+Tools.getRandom4int();
                String sn="WBCD123456123"+Tools.getWidthNo(1000,9999,4);
                String license="京NC"+Tools.getWidthNo(1000,9999,4);
                String simNumber="199"+Tools.getWidthNo(10000000,99999999,8);
                Date activeTime=DateUtil.parseStrToDate(preDateStrList.get(i) + Tools.getWidthNo(1, 30, 2) + " " + Tools.getWidthNo(8, 19, 2) + ":" + Tools.getWidthNo(1, 59, 2) + ":" + Tools.getWidthNo(1, 59, 2) + "");
                VirtualCar vr=new VirtualCar(1,obdCode,sn,license,simNumber,activeTime);
                vr.enable();
                startObdcode++;
            }
        }




    }
}
