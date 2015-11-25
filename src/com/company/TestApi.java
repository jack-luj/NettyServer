package com.company;

import java.io.*;
import java.net.*;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map;

/**
 * Created by luj on 2015/11/20.
 */
public class TestApi {

    public static final String BOUNDARYSTR = "aifudao7816510d1hq";
    public static final String BOUNDARY = "--" + BOUNDARYSTR + "\r\n";
    public void Send(String sim, String content) {
    //    String ADD_URL = "http://10.0.12.146:8080/api/message/filePush?sourceId=1&resourceFrom=1&targetType=1&targetId=1&resourceTo=1&funType=1&pType=1&contentType=1&textContent=1&messageNums=1&cleanFlag=1";
        String ADD_URL = "http://10.0.12.146:8080/api/message/filePush?targetId=1&funType=2";
        try {
            //建立连接
            URL url = new URL(ADD_URL);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Content-Type", "multipart/form-data;charset=utf-8");
            connection.connect();

            //POST请求
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");

            out.flush();
            out.close();
            //读取响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String lines;
            StringBuffer sb = new StringBuffer("");
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes(), "utf-8");
                sb.append(lines);
            }
            System.out.println(sb.toString());
            reader.close();
            // 断开连接
            connection.disconnect();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void main(String[] args)
    {
      // new TestApi().Send("","");
     new TestApi().upload();
    }
    public static void upload(){
        try{
            String boundary = "Boundary-b1ed-4060-99b9-fca7ff59c113"; //Could be any string
            String Enter = "\r\n";
            String vvv=java.net.URLEncoder.encode("车辆报警信息: 当前位置:经度:114.12109375E,纬度30.25390625N;速度:156.7km/h;方向:234;安全气囊报警触发,背扣安全带数量:2,碰撞速度:100km/h;车辆防盗报警触发", "utf-8");
            URL url = new URL("http://10.0.12.146:8080/api/message/filePush?targetId=1&resourceTo=1&funType=2&pType=9&textContent="+vvv);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            conn.setUseCaches(false);
            conn.setInstanceFollowRedirects(true);
            conn.setRequestProperty("Content-Type","multipart/form-data;boundary=" + boundary);
            conn.connect();
            DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
            //part 1
            String part1 =  "--" + boundary + Enter
                    + "Content-Type: application/octet-stream" + Enter
                    + "Content-Disposition: form-data; filename=\""+""+"\"; name=\"file\"" + Enter + Enter;
            //part 2
            String part2 = Enter
                    + "--" + boundary + Enter
                    + "Content-Type: text/plain" + Enter
                    + "Content-Disposition: form-data; name=\"dataFormat\"" + Enter + Enter
                    + "hk" + Enter
                    + "--" + boundary + "--";
                    dos.writeBytes(part1);
            dos.writeBytes(part2);
            dos.flush();
            dos.close();
            System.out.println("status code: "+conn.getResponseCode());
            conn.disconnect();

        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
