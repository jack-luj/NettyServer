package com.db.everyDay;

import com.db.*;
import com.db.CarModel;
import com.db.DateUtil;
import com.db.Tools;
import com.db.VirtualCar;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jack lu on 2016/3/11.
 */
public class Generator {

    public static  void main(String[] args){
        //车型信息
        List<CarModel> carModelList=new ArrayList<CarModel>();
        carModelList.add(new CarModel(0,12,2011,"L",String.valueOf(5.7)));//丰田
        carModelList.add(new CarModel(0,12,2012,"L",String.valueOf(4.0)));
        carModelList.add(new CarModel(0,13,2014,"L",String.valueOf(2.7)));

        carModelList.add(new CarModel(9,2,2015,"T",String.valueOf(1.6)));//奔驰
        carModelList.add(new CarModel(9,6,2013,"T",String.valueOf(1.8)));
        carModelList.add(new CarModel(9,7,2015,"T",String.valueOf(2.0)));
        carModelList.add(new CarModel(9,7,2015,"T",String.valueOf(3.0)));

        carModelList.add(new CarModel(8,4,2014,"T",String.valueOf(2.0)));//宝马
        carModelList.add(new CarModel(8,4,2014,"T",String.valueOf(3.0)));

        carModelList.add(new CarModel(8,7,2016,"T",String.valueOf(2.0)));
        carModelList.add(new CarModel(8,7,2016,"T",String.valueOf(3.0)));
        carModelList.add(new CarModel(8,7,2016,"T",String.valueOf(4.4)));

        carModelList.add(new CarModel(8,9,2014,"T",String.valueOf(2.0)));
        carModelList.add(new CarModel(8,9,2014,"T",String.valueOf(3.0)));

        carModelList.add(new CarModel(8,11,2015,"T",String.valueOf(2.0)));
        carModelList.add(new CarModel(8,11,2015,"T",String.valueOf(3.0)));
        carModelList.add(new CarModel(8,11,2015,"T",String.valueOf(4.0)));

        carModelList.add(new CarModel(52,4,2014,"L",String.valueOf(2.0)));//JEEP
        carModelList.add(new CarModel(52,4,2014,"L",String.valueOf(2.4)));

        carModelList.add(new CarModel(3,4,2016,"L",String.valueOf(2.0)));//三菱
        carModelList.add(new CarModel(3,4,2016,"L",String.valueOf(2.4)));


        List<String> licensePre=new ArrayList<String>();
        licensePre.add("H"); licensePre.add("J"); licensePre.add("K"); licensePre.add("L"); licensePre.add("M");
        licensePre.add("N"); licensePre.add("P"); licensePre.add("R"); licensePre.add("S"); licensePre.add("U");
        licensePre.add("V"); licensePre.add("W"); licensePre.add("X"); licensePre.add("Y"); licensePre.add("Z");

        List<String> phonePre=new ArrayList<String>();
        phonePre.add("137"); phonePre.add("135"); phonePre.add("134");

        long start=new Date().getTime();

        File log=new File("d:\\sql.txt");
        log.delete();
        int startObdcode=1001000;
        Date driveDeadLine= com.db.DateUtil.parseStrToDate("2016-03-20 08:30:00");

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

        int id=1;//自增主键


        for (int i = 0; i < preDateStrList.size(); i++) {//preDateStrList.size();
            Tools.writeGloablTxt("-- " + preDateStrList.get(i) + "共计激活：" + countList.get(i) + "辆");
            for (int j = 0; j < countList.get(i); j++) {//countList.get(i)
                String obdCode="INCAR"+String.valueOf(startObdcode+1)+""+Tools.getRandom4int();
                String sn="";
                String license="苏D"+licensePre.get(Tools.getNoBetween(0,licensePre.size()-1))+ Tools.getWidthNo(1001,9998,4);
                String simNumber=phonePre.get(Tools.getNoBetween(0,phonePre.size()-1))+Tools.getWidthNo(10000000,99999999,8);
                Date activeTime= com.db.DateUtil.parseStrToDate(preDateStrList.get(i) + Tools.getWidthNo(1, 30, 2) + " " + Tools.getWidthNo(8, 19, 2) + ":" + Tools.getWidthNo(1, 59, 2) + ":" + Tools.getWidthNo(1, 59, 2) + "");
                if(i==preDateStrList.size()-1){
                    activeTime= DateUtil.parseStrToDate(preDateStrList.get(i) + Tools.getWidthNo(1, 13, 2) + " " + Tools.getWidthNo(8, 19, 2) + ":" + Tools.getWidthNo(1, 59, 2) + ":" + Tools.getWidthNo(1, 59, 2) + "");

                }
                CarModel carModel=carModelList.get(Tools.getNoBetween(0,carModelList.size()-1));
                VirtualCar vr=new VirtualCar(preDateStrList.get(i),id,obdCode,sn,license,simNumber,activeTime,driveDeadLine,carModel);
                id++;
                vr.enable();
                startObdcode++;
            }
        }

        long end=new Date().getTime();
        Tools.writeGloablTxt("-- 耗时:" + (end - start) + "ms");
    }
}
