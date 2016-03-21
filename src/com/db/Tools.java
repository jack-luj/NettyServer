package com.db;

import java.io.*;

/**
 * Created by jack lu on 2016/3/7.
 */
public class Tools {
   private String filename="sql";
    public static int getRandom4int(){
        return (int)(Math.random()*9000)+1000;
    }
    public static int getNoBetween(int a,int b){
        return (int)(a+Math.random()*(b-a+1));
    }

    public Tools(String filename){
    this.filename=filename;
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
    public  static void writeGloablTxt(String content){

        String file="d:\\db\\sql.txt";
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

    public  void writeTxt(String content) {
        System.out.println(content);
        String file="d:\\db\\"+ this.getFilename() +".txt";
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


    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
