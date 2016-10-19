package com.bri;

import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import jxl.Sheet;
import jxl.Workbook;

import javax.swing.*;
import java.io.*;
import java.sql.ResultSet;
import java.util.Date;

/**
 * Created by jackl on 2016/8/29.
 */
public class LogReader extends Thread{
    private JTextArea msg;
    Session sess;
    String cmd;
    String imei;
    String imeiHex;
    int watchType;
    public LogReader(Session sess, String cmd, JTextArea msg,int watchType,String imei){
        this.sess=sess;
        this.cmd=cmd;
        this.msg=msg;
        this.watchType=watchType;
        this.imei=imei;
    }

    @Override
    public void run() {
        System.out.println((new Date().toLocaleString() + " 数据接收开始>>>>>>>>>>\n"+cmd));
        read();
    }

    public void  read(){
        try {
            if(imei!=null) {
                imeiHex = parseByte2HexStr(imei.getBytes());
                System.out.println("imeiHex:"+imeiHex);
            }
            sess.execCommand(cmd);
            System.out.println("Here is some information from remote server:");
            InputStream stdout = new StreamGobbler(sess.getStdout());
            BufferedReader br = new BufferedReader(new InputStreamReader(stdout));
            while (true) {
                String line = br.readLine();
                if (filter(line) ==true) {
                    msg.append(line + "\n");
                    // System.out.println("LINE:" + line);
                }

            }
        } catch (IOException ee) {
            ee.printStackTrace(System.err);
        }
    }


    /**
     * 逐行过滤
     * @param str 文本
     * @return
     */
    public boolean  filter(String str){
        boolean re=false;
      if(str!=null){
          System.out.println(str);
          if(str.indexOf(imeiHex)>-1){//imei匹配
              re= true;
          }
           if(watchType==0){
                   re= true;
           }
          if(watchType==1){//远程控制
              if(str.indexOf("RemoteControlPreconditionResp")>-1||str.indexOf("RemoteControlAck")>-1||str.indexOf("RemoteControlRst")>-1)
                  re= true;
          }
           if(watchType==2){//实时数据
               if(str.indexOf("RealTimeReportMes")>-1)
                   re= true;
           }
          if(watchType==3){//故障数据
              if(str.indexOf("FailureMessage")>-1)
                  re= true;
          }
          if(watchType==4){//报警数据
              if(str.indexOf("WarningMessage")>-1)
                  re= true;
          }
          if(watchType==5){//注册数据
              if(str.indexOf("Register")>-1||str.indexOf("resister")>-1||str.indexOf("exceptionCaught")>-1)
                  re= true;
          }
        }


        return re;
    }


    public  String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase()+" ");
        }
        return sb.toString();
    }

}
