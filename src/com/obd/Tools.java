package com.obd;

import java.io.*;

/**
 * Created by jack lu on 2016/3/7.
 */
public class Tools {
   private String filename="do-not-exe-me";
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
        System.out.println(content);
        String file="d:\\db\\do-not-exe-me.txt";
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
        String file="d:\\db\\"+ this.getFilename() +"car.sql";
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
        String file="d:\\db\\"+ this.getFilename() +"drive.sql";
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
        String file="d:\\db\\"+ this.getFilename() +"location.sql";
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
    public  static void writeTripTxt(String content){
        System.out.println(content);
        String file="d:\\db\\trip_id.txt";
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

    public  static void writeObdTxt(String content){
        System.out.println(">"+content);
        String file="d:\\db\\obd_id.txt";
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
        String file="d:\\db\\"+ this.getFilename() +"detail.sql";
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
        String file="d:\\db\\"+ this.getFilename() +"condition.sql";
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
