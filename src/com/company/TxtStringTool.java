package com.company;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/**
 * Created by luj on 2015/11/25.
 */
public class TxtStringTool {
    public static void main(String[] args) {
        String filename1="d:\\txt1.txt";
        String filename2="d:\\txt2.txt";
        String content1=buildTxt(35);
        String[] arry1=content1.split("\r\n");
        String filterContent=filterTxt(arry1, 20);
        writeTxt(filename1,content1);
        writeTxt(filename2,filterContent);
    }
    public static String filterTxt(String[] arry,int maxLineLength){
        StringBuilder sb=new StringBuilder();
        if(arry.length<=maxLineLength){
            for(int i=0;i<arry.length;i++){
                sb.append(arry[i]).append("\r\n");
            }
        }else{
            int min=maxLineLength;
            int max=arry.length;
            for(int i=max-min;i<arry.length;i++){
                sb.append(arry[i]).append("\r\n");
            }
        }
        return sb.toString();

    }
    public static void writeTxt(String fileName,String content){
        try{
        FileWriter fileWriter=new FileWriter(fileName);
        fileWriter.write(content);
        fileWriter.flush();
        fileWriter.close();
        }catch (IOException e){e.printStackTrace();}
    }

    public static String buildTxt(int lineLength){
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<lineLength;i++){
            sb.append(new Date().toLocaleString()).append("-").append(i+1).append("\r\n");
        }
        return sb.toString();

    }





}
