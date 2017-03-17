package com.iov;

import java.util.Arrays;

/**
 * Created by jackl on 2017/3/16.
 */
public class Test {
    public static void main (String[] args) {
        //   numToIp2("13511223344");
        System.out.println("************************");
        //numToIp2("15811223344");
        System.out.println("************************");
        //ipToNum2(new Integer[]{0x08,  0xC7,  0xC5,  0xA0});
        ipToNum2(new byte[]{(byte)0x08,(byte)0xC7,(byte)0xC5,(byte)0xA0});
        //ipToNum2(new Integer[]{0x08,0xC7,0xC5,0xA0});
        System.out.println("************************");



        byte a =(byte)0xC7;
        int aaa=a & 0xFF;
        System.out.println(aaa);
    }

    public static String ipToNum2(byte[] ips){
    //public static String ipToNum2(byte[] ips){
        System.out.println(Arrays.toString(ips));
        String telHeaderBin = "";
        String[] newIpArr = new String[4];
        for(int i = 0 , len = ips.length ; i < len ; i++ ){
            if ((ips[i]&0xff) < 80){
                //当数据<80时
                telHeaderBin += "0";
                newIpArr[i] = String.valueOf((ips[i]&0xff));
            }else{
                //当数据>80时
                telHeaderBin += "1";
                newIpArr[i] = Integer.toHexString((ips[i]&0xff) - 0x80);
            }
        }
        String tel = "1" + (30 + Integer.valueOf(telHeaderBin,2));
        String tmp ;
        for (int i = 0 , len = newIpArr.length; i< len ; i++){
            tmp = Integer.valueOf(newIpArr[i].toString(),16).toString();
            tmp = tmp.length() < 2 ? "0"+tmp : tmp;
            tel += tmp;
        }
        System.out.println("tel:"+tel);
        return tel;
    }

    /**
     * 手机号转成伪ip
     * @param num
     * @return
     */
    public static String[] numToIp2(String num){
        num = num.substring(1);
        System.out.println(num);
        //号码截取成5块 2个数字一组
        String[] tel = new String[]{num.substring(0,2),num.substring(2,4),num.substring(4,6),num.substring(6,8),num.substring(8)};

        //把a转换成二进制;
        Integer aInt = Integer.parseInt(tel[0]);
        Integer aResult ;
        if(aInt - 30 < 10){
            //为13开头的号码
            aResult = aInt - 30;
            System.out.println("号码为13开头的号码!");
        }else{
            //为15开头的号码
            System.out.println("号码为15开头的号码!");
            if(aInt - 30 - 16 < 16){
                aResult = aInt - 30 - 16;
            }else{
                System.out.println("号码不符合规定...");
                return null;
            }
        }

        //把a转换成二进制
        String aBin = Integer.toBinaryString(aResult);
        String tmp = "";
        //补0
        switch (aBin.length()) {
            case 0 :
                tmp = "0000";
                break;
            case 1 :
                tmp = "000";
                break;
            case 2 :
                tmp = "00";
                break;
            case 3 :
                tmp = "0";
                break;
        }
        aBin = tmp+aBin;

        //进行最后的计算
        char[] aChars = aBin.toCharArray();

        String[] ip = new String[4];
        for ( int i = 0 , len = aChars.length ; i < len ; i++ ){
            ip[i] = Integer.toHexString(Integer.parseInt(tel[i+1]) + 128 * Integer.parseInt(String.valueOf(aChars[i])));
        }
        System.out.println("ip result:"+Arrays.toString(ip));
        return ip;
    }

}
