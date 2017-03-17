package com.iov;

import io.netty.buffer.ByteBuf;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import static io.netty.buffer.Unpooled.buffer;

/**
 * Created by jackl on 2017/3/10.
 */
class UDPClient{
    public static void main(String[] args)throws IOException {
        System.out.println("最大堆"+Runtime.getRuntime().maxMemory()/1024/1024+"M");
        DatagramSocket client = new DatagramSocket();

        String sendStr = "29 29 80 00 28 0C 3A 3B 2A 08 06 01 18 21 00 02 23 20 31 11 40 15 70 00 00 00 00 38 00 00 00 FF FC 56 80 00 1E 00 00 00 00 00 00 70 0D";
        byte[] sendBuf;
        sendBuf =getBytesFromHexString(sendStr);


        InetAddress addr = InetAddress.getByName("127.0.0.1");
        int port = 9001;
        DatagramPacket sendPacket
                = new DatagramPacket(sendBuf ,sendBuf.length , addr , port);
        client.send(sendPacket);
        System.out.println("发出:"+sendStr);
        byte[] recvBuf = new byte[100];
        DatagramPacket recvPacket
                = new DatagramPacket(recvBuf , recvBuf.length);
        client.receive(recvPacket);
        String recvStr = bytes2hex(recvPacket.getData(),recvPacket.getLength());
        System.out.println("收到:" + recvStr);
        client.close();
    }

    public   static byte[] getBytesFromHexString(String hexString){
      return getBytesFromByteBuf(getByteBuf(hexString));
    }


    public  static String bytes2hex(byte[] bArray,int maxLength) {
        //字节数据转16进制字符串
        int min=Math.min(bArray.length,maxLength);
        StringBuffer sb = new StringBuffer(min);
        String sTemp;
        for (int i = 0; i < min; i++) {
            sTemp = Integer.toHexString(0xFF & bArray[i]);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase());
        }
        return getSpaceHex(sb.toString());
    }

    public  static String getSpaceHex(String str){
        //将不带空格的16进制字符串加上空格
        String re="";
        String regex = "(.{2})";
        re = str.replaceAll (regex, "$1 ");
        return re;
    }
    public static ByteBuf getByteBuf(String str){
        //根据16进制字符串得到ByteBuf对象(netty)
        ByteBuf bb=buffer(1024);

        String[] command=str.split(" ");
        byte[] abc=new byte[command.length];
        for(int i=0;i<command.length;i++){
            abc[i]=Integer.valueOf(command[i],16).byteValue();
        }
        bb.writeBytes(abc);
        return bb;
    }
    public   static byte[] getBytesFromByteBuf(ByteBuf buf){
        //基于netty
        byte[] result = new byte[buf.readableBytes()];
        buf.readBytes(result, 0, buf.readableBytes());
        return result;
    }
}