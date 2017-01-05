package com.IOVGenerator;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jackl on 2017/1/5.
 */
public class CarGenerator {

    public static void main(String[] args){
        long start=new Date().getTime();
        System.out.println("-- --------------------开始生成车辆-------------------");
        List<String> labela=new ArrayList<String>();
        labela.add("A"); labela.add("B"); labela.add("C"); labela.add("D"); labela.add("E");labela.add("F"); labela.add("G");

        List<String> labelb=new ArrayList<String>();
        labelb.add("A"); labelb.add("B");labelb.add("C"); labelb.add("D"); labelb.add("H"); labelb.add("J"); labelb.add("K"); labelb.add("L"); labelb.add("M");

        List<String> labelc=new ArrayList<String>();
        labelc.add("A"); labelc.add("B"); labelc.add("C"); labelc.add("D"); labelc.add("E");
        labelc.add("F"); labelc.add("G"); labelc.add("H"); labelc.add("I");
        labelc.add("K"); labelc.add("W");

        for (int i = 0; i < 10000; i++) {

            String _vin="LFV3"+ labela.get(Tools.getNoBetween(0, labela.size() - 1)) +"28"+ labelb.get(Tools.getNoBetween(0, labelb.size() - 1)) +"8"+ labelc.get(Tools.getNoBetween(0, labelc.size() - 1)) +""+ Tools.getWidthNo(1000001, 9999998, 7);;
            String _imei="358"+Tools.getWidthNo(111111111111l, 999999999999l, 12);
            String sql="INSERT into  t_iov_vehicle(id,vin,imei) VALUES("+(i+1)+",'"+_vin+"','"+_imei+"');";
            System.out.println(sql);
            //Tools.writeGloablTxt("-- 耗时:" + (end - start) + "ms");

        }
        long end=new Date().getTime();
        System.out.println("-- --------------------结束生成车辆，耗时:"+ (end-start)+"ms -------------------");
    }
}
