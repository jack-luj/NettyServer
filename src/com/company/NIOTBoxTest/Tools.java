package com.company.NIOTBoxTest;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Date;

/**
 * Created by jack lu on 2016/1/14.
 */
public class Tools {
    public static int threadCount=15000;
    public static String hostIp="localhost";//121.40.157.200 //localhost/10.0.12.35
    public static int hostPort=9000;


    public static void main(String[] args) {
        // write your code here
        try{
            Tools.fileLog("���Կ�ʼ"+new Date().toLocaleString());
            for(int i=1;i<=Tools.threadCount;i++)
                //���ӷ���ˣ�����ע�ᣬ������һ���������ݣ�����ͨ����֤������ģ�
                new Tester(i).start();
        }catch (Exception e){e.printStackTrace();}
        //    System.out.println("real connectionCount>>>>>>>>>>>>>>>>:"+Tester.connectionCount);
    }


    public static void fileLog(String conent) {

        String file="E:\\test-"+threadCount +".log";
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
            out.write(conent+"\r\n");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(out != null){
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
