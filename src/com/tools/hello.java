package com.tools;

import java.io.IOException;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by jack lu on 2016/1/27.
 */
public class hello{
    public static void main(String[] args){
        String ipv4="121.23.36.58";
        String ipv6="1111:0000:0000:3333:5555:0000:0000:1";//-> fe80::bc83:1be6:2415:2d68

        System.out.println(handleIp(ipv4));
        System.out.println("简化格式:" + handleIp(ipv6));


    }


    public static String handleIp(String originalIpStr){
        String re="";
        try{
        if(originalIpStr!=null){
            InetAddress inetAddress=InetAddress.getByName(originalIpStr);
            String originalIp=inetAddress.getHostAddress();

            if(inetAddress instanceof Inet6Address){//v6
                String ip=originalIp;
                originalIp = originalIp.replaceFirst("0:0:0:0:0:0:0:", "::");
                if(originalIp.length()==ip.length()) {
                    originalIp = originalIp.replaceFirst(":0:0:0:0:0:0:", "::");
                }
                if(originalIp.length()==ip.length()) {
                    originalIp = originalIp.replaceFirst(":0:0:0:0:0:", "::");
                }
                if(originalIp.length()==ip.length()) {
                    originalIp = originalIp.replaceFirst(":0:0:0:0:", "::");
                }
                if(originalIp.length()==ip.length()) {
                    originalIp = originalIp.replaceFirst(":0:0:0:", "::");
                }
                if(originalIp.length()==ip.length()){
                    originalIp=originalIp.replaceFirst(":0:0:","::");
                }
                if(originalIp.length()==ip.length()){
                    originalIp=originalIp.replaceFirst(":0:","::");
                }
            }
            re=originalIp;
        }
        }catch (UnknownHostException e){
        e.printStackTrace();
        }
        return re;
    }
}