package com.company;

import io.netty.buffer.ByteBuf;

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

    try{
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
    Date date = (Date) dateFormat.parse(String.valueOf(20120331));
    System.out.println(date.toLocaleString());
    }   catch (Exception e) {


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
}}
}
