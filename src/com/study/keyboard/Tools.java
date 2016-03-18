package com.study.keyboard;

/**
 * Created by jack lu on 2016/3/18.
 */

import java.io.*;

/**
 * Created by jack lu on 2016/3/7.
 */
public class Tools {

    public static int getRandom4int(){
        return (int)(Math.random()*9000)+1000;
    }
    public static int getNoBetween(int a,int b){
        return (int)(a+Math.random()*(b-a+1));
    }
    public static String cutString(String s,int length){
        if (s.length()>length){
            s=s.substring(0,length-1);
        }
        return s;
    }
    public static String getWidthNo(int a,int b,int length){
        String s=getNoBetween(a,b)+"";
        while (s.length()<length){
            s="0"+s;
        }
        return s;
    }
    public static void writeTxt1(String content){
        String filename="d:\\sql.txt";
        try{
            FileWriter fileWriter=new FileWriter(filename);
            fileWriter.write(content);
            fileWriter.flush();
            fileWriter.close();
        }catch (IOException e){e.printStackTrace();}
    }

    public static void writeTxt(String content) {
        System.out.println(content);
        String file="d:\\sql.txt";
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file, true)));
            out.write(content+"\r\n");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
