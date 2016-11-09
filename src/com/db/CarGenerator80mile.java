package com.db;

import com.db.everyDay.Account;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jack lu on 2016/3/11.
 */
public class CarGenerator80mile {

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
        //licensePre.add("H"); licensePre.add("J"); licensePre.add("K"); licensePre.add("L"); licensePre.add("M");
        //licensePre.add("N"); licensePre.add("P"); licensePre.add("R"); licensePre.add("S"); licensePre.add("U");
        //licensePre.add("V"); licensePre.add("W"); licensePre.add("X"); licensePre.add("Y"); licensePre.add("Z");
        licensePre.add("G");

        NamesTool namesTool=new NamesTool();
        List<String> names=namesTool.getNames();
        int nameIndex=0;

        List<String> phonePre=new ArrayList<String>();
        phonePre.add("1891018"); phonePre.add("1350121"); phonePre.add("1390128");

        long start=new Date().getTime();

        File log=new File("d:\\sql.txt");
        log.delete();
        int startObdcode=1001000;
        Date driveDeadLine=DateUtil.parseStrToDate("2016-03-20 08:30:00");

        List<Integer> countList=new ArrayList<Integer>();
        List<String> preDateStrList=new ArrayList<String>();
        countList.add(Tools.getNoBetween(80,100));
        countList.add(Tools.getNoBetween(100,120));
        countList.add(Tools.getNoBetween(100,130));
        countList.add(Tools.getNoBetween(100,140));
        countList.add(Tools.getNoBetween(10,50));
        preDateStrList.add("2016-07-");
        preDateStrList.add("2016-08-");
        preDateStrList.add("2016-09-");
        preDateStrList.add("2016-10-");
        preDateStrList.add("2016-11-");


        int carId=14000;//自增主键
        int accountId=1000;//自增主键
        int relationId=300;//自增主键
        int s4_id=20;//自增主键


        for (int i = 0; i < preDateStrList.size(); i++) {//preDateStrList.size();
            Tools.writeGloablTxt("-- " + preDateStrList.get(i) + "共计激活：" + countList.get(i) + "辆");
            for (int j = 0; j <countList.get(i); j++) {//countList.get(i)
                //String obdCode="INCAR"+String.valueOf(startObdcode+1)+""+Tools.getRandom4int();
                String obdCode="";
                String sn="";
                String license="京N"+licensePre.get(Tools.getNoBetween(0,licensePre.size()-1))+ Tools.getWidthNo(1001,9998,4);
                String simNumber=phonePre.get(Tools.getNoBetween(0,phonePre.size()-1))+Tools.getWidthNo(10000000,99999999,8);

                Date activeTime=DateUtil.parseStrToDate(preDateStrList.get(i) + Tools.getWidthNo(1, 30, 2) + " " + Tools.getWidthNo(8, 19, 2) + ":" + Tools.getWidthNo(1, 59, 2) + ":" + Tools.getWidthNo(1, 59, 2) + "");
                if(i==preDateStrList.size()-1){
                    activeTime=DateUtil.parseStrToDate(preDateStrList.get(i) + Tools.getWidthNo(1, 7, 2) + " " + Tools.getWidthNo(8, 19, 2) + ":" + Tools.getWidthNo(1, 59, 2) + ":" + Tools.getWidthNo(1, 59, 2) + "");

                }



                CarModel carModel=carModelList.get(Tools.getNoBetween(0,carModelList.size()-1));
                VirtualCar vr=new VirtualCar(preDateStrList.get(i),carId,s4_id,obdCode,sn,license,simNumber,activeTime,driveDeadLine,carModel);

                vr.enable();
                startObdcode++;
                String name=names.get(nameIndex);

                String phone=""+phonePre.get(Tools.getNoBetween(0, phonePre.size() - 1)) + Tools.getWidthNo(0000, 9999, 4);
                Account account=new Account(accountId,carId,relationId,s4_id,name,"",phone);
                account.addAccount();
                carId++;
                accountId++;
                account.addRelation();
                relationId++;
                nameIndex++;

            }
        }

        long end=new Date().getTime();
        Tools.writeGloablTxt("-- 耗时:" + (end - start) + "ms");
    }
}
