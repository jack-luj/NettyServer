package com.company;

import io.netty.buffer.ByteBuf;
import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static io.netty.buffer.Unpooled.buffer;

public class Main {
    public static Date parseStrToDate(String dateStr) {
        Date date;

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            date = (Date) dateFormat.parse(dateStr);
        } catch (Exception e) {
            return null;
        }
        return date;
    }
    public static void main(String[] args) {
        String by="23 23 00 0b 00 56 98 96 07 13 02 56 1e 16 3d 00 26";
        System.out.println(by.length());
        System.out.println(by.substring(by.length()-5,by.length()-3));

       /* byte a = (byte) 128;
        System.out.println((short) a - (short) 40);*/

     /*   String a="测试测试";
        try{
            System.out.println(java.net.URLEncoder.encode(a,"UTF-8"));
        }catch (UnsupportedEncodingException ee){}


        String sa="a";  // 0x61 0x00
        String ss="";   //0x00

*/

/*
        Calendar c=Calendar.getInstance();
        c.clear();
        c.set(Calendar.YEAR,2016);
        c.set(Calendar.MONTH, 1 - 1);
        c.set(Calendar.DAY_OF_MONTH, 4);
        c.set(Calendar.HOUR_OF_DAY,2);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND,0);
        c.set(Calendar.MILLISECOND,0);
        getHandleYearWeek(c.getTime());*/
      /*  c.set(Calendar.YEAR,2017);
        c.set(Calendar.MONTH, 1-1);
        c.set(Calendar.DAY_OF_MONTH, 1);
        getYearMonth(c.getTime());*/
    }
    /**
     * 从当前时间得到需要处理周报的year,week参数
      * @param date
     */
    public static void getHandleYearWeek(Date date){
        System.out.println(date.toLocaleString());
        date=new Date(date.getTime()-60*1000*60*24*7);//一周前的时间
        System.out.println(date.toLocaleString());
        int year=0;
        int month=0;
        int week=0;
        Calendar c=Calendar.getInstance();
        c.clear();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        year=c.get(Calendar.YEAR);
        month=c.get(Calendar.MONTH);
        week= c.get(Calendar.WEEK_OF_YEAR);
        if(month==11&&week<=1){//DECEMBER出现了week=1
        //最后一周跨年的情况特殊处理 2015-12-28取到的week_of_year是1,year是2015，归入下一年的第一周
            year+=1;
        }
        System.out.println("处理年、周参数:" + year + "--" +   "" + week);
    }



    /**
     * 从当前时间得到需要处理周报的year,month
     * @param date
     */
    public static void getHandleYearMonth(Date date){
        System.out.println(date.toLocaleString());
        int year=0;
        int month=0;
        Calendar c=Calendar.getInstance();
        c.clear();
        c.setTime(date);
        year=c.get(Calendar.YEAR);
        month= c.get(Calendar.MONTH);
        if(month==0){//当前JANUARY应该处理上一年的12月
            year=year-1;
            month=12;
        }
        System.out.println("处理年、月参数:"+year+"--"+month);
    }



/*
        Long start=new Date().getTime();
        Long now=start;
        now=new Date().getTime();
        while (now-start<=30000) {
            try {
                Thread.sleep(5000);
                System.out.println("haha");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            now=new Date().getTime();
        }




        // write your code here
        //��ack����
        ByteBuf bb=buffer(1024);
        bb.writeByte(0x23);//0
        bb.writeByte(0x23);//1
        bb.writeShort(0x4d40);//2-3
        bb.writeByte(0x00);//4
        bb.writeInt(0x55D20FE7);//5-8
        bb.writeByte(0x42);//9
        bb.writeByte(0x02);//10
        bb.writeBytes(new byte[]{0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38, 0x39, 0x30, 0x31, 0x32, 0x33, 0x34, 0x35});//11-25
        bb.writeBytes(new byte[]{0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00});//26-32
        bb.writeInt(0x55BEE258);//33-36 eventId 1438573144
        bb.writeByte(1);//37
        bb.writeByte(3);//38 ����
        //data
        bb.writeByte(1);//39
        bb.writeBytes(new byte[]{0x61, 0x62, 0x63, 0x64, 0x65, 0x66});//40-43
        bb.writeByte(2);//39
        bb.writeBytes(new byte[]{0x61});//40-43
        bb.writeByte(3);//39
        bb.writeBytes(new byte[]{0x61, 0x62, 0x63, 0x64, 0x65});//40-43
        bb.writeByte(0x5D);//checksum




        String byteStr=DataTool.getStringFromByteBuf(bb);
        System.out.println(byteStr);*/

}
