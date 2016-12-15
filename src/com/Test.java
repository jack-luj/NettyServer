package com;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * Created by jackl on 2016/11/11.
 */
public class Test {



    public static void main(String[] args){
        //02 22 5A 00 = 35805696
        //06 AC A3 00 = 111977216

        //02 4B 64 00 = 38495232
        //06 C5 41 00 = 113590528
        long lat=35805696;
        long lon=111977216;
        for (int i = 0; i <10000 ; i++) {
            System.out.println(getTrueLatAndLon(lat)+","+getTrueLatAndLon(lon));
        }

    }



    public static double getTrueLatAndLon(long a){
      /*  //经纬度除以1000000得到真实值
        String  num = a/1000000+"."+a%1000000;
        return Double.valueOf(num);*/
        //按照0.619协议变更经纬度取值方式
        //double  num = a * 0.00390625*3600;
        // 2016.5.24变化
        double num=a * 0.00390625 /3600;
        BigDecimal _a  =   new  BigDecimal(String.valueOf(a));
        BigDecimal _b  =   new  BigDecimal("0.00390625");
        BigDecimal _c  =   new  BigDecimal("3600");

        BigDecimal _num =_a.multiply(_b);
        MathContext mc = new MathContext(15, RoundingMode.HALF_DOWN);
        BigDecimal bd  =  _num.divide(_c,mc);

        bd   =  bd.setScale(6,BigDecimal.ROUND_HALF_DOWN);//四舍五入保留6位小数
        return bd.doubleValue();
    }



    public int getValueFromBytes(char[] datas,int length){
        if(datas.length<length){
            return 0;
        }
        int re=0;
        for(int i=0;i<length;i++){
            int a=(int)(Math.pow((double)2,(double)i));
            re+=a*Integer.parseInt(String.valueOf((datas[datas.length-1-i])));
        }
        return re;
    }

    public  char[] getBitsFromInteger(int value){
        //双字节转二进制
        char[] array=new char[16];
        for (int j = 15; j >= 0; j--)
        {
            if (((1 << j) & value) != 0)
            {
                array[15-j]='1';
            }
            else
            {
                array[15-j]='0';
            }
        }
        return array;
    }
}
