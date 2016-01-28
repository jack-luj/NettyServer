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
    public static int threadCount=10000;
    public static String hostIp="localhost";//localhost/10.0.12.35
    public static int hostPort=9000;


    public static void main(String[] args) {
        // write your code here
        try{
            Tools.fileLog("测试开始"+new Date().toLocaleString());
            for(int i=1;i<=Tools.threadCount;i++)
                //连接服务端，进行注册，并发送一条测试数据（可以通过验证并保存的）
                new Tester(i).start();
        }catch (Exception e){e.printStackTrace();}
        //    System.out.println("real connectionCount>>>>>>>>>>>>>>>>:"+Tester.connectionCount);
    }


    public static void fileLog(String conent) {
        System.out.println(conent);
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
