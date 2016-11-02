package com.obdGenerator;

import java.io.*;
import java.util.Date;

/**
 * Created by jack lu on 2016/3/7.
 */
public class Tools {
    private String logPath="";
   private String filename="do-not-exe-me";
    public static int getRandom4int(){
        return (int)(Math.random()*9000)+1000;
    }
    public static int getNoBetween(int a,int b){
        return (int)(a+Math.random()*(b-a+1));
    }


    public Tools(String logPath,String filename){
        this.logPath=logPath;
        this.filename=filename;
    }


    public  String cutString(String s,int length){
      if (s.length()>length){
         s=s.substring(0,length-1);
      }
        return s;
    }
    public  String getWidthNo(int a,int b,int length){
        String s=getNoBetween(a,b)+"";
        while (s.length()<length){
            s="0"+s;
        }
        return s;
    }

    /**
     * 返回n年前的时间
     * @param beforeYears
     * @return
     */
    public  Date getDateBefore(int beforeYears){
        long now=new Date().getTime();
        Date targetDate=new Date(now-1000*60*60*24*365);//365天前
        return targetDate;
    }

    public   void writeGloablTxt(String content){
        content=DateUtil.format(new Date())+" "+content;
        System.out.println(content);
        String file=logPath+"do-not-exe-me.txt";
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

    public  void writeCarTxt(String content) {
        System.out.println(content);
        String file=logPath+ this.getFilename() +"car.sql";
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

    public  void writeDriveTxt(String content) {
        System.out.println(content);
        String file=logPath+ this.getFilename() +"drive.sql";
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
    public  void writeLocationTxt(String content) {
        System.out.println(content);
        String file=logPath+ this.getFilename() +"location.sql";
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
    public   void writeTripTxt(String content){
        System.out.println(content);
        String file=logPath+"trip_id.txt";
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

    public   void writeObdTxt(String content){
        System.out.println(">"+content);
        String file=logPath+"obd_id.txt";
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

    public  void writeDetailTxt(String content) {
        System.out.println(content);
        String file=logPath+ this.getFilename() +"detail.sql";
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

    public  void writeConditionTxt(String content) {
        System.out.println(content);
        String file=logPath+ this.getFilename() +"condition.sql";
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
