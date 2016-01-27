package com.company.TboxTest;

import io.netty.buffer.ByteBuf;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Date;

import static io.netty.buffer.Unpooled.buffer;

/**
 * Created by jack lu on 2016/1/18.
 */
public class Tools {
    public static int threadCount=1000;
    public static String hostIp="localhost"; //localhost
    public static int hostPort=9000;
    public static void main(String[] args) {
        try{
            Tools.fileLog( new Date().toLocaleString()+"测试开始，模拟用户数:" +threadCount);
            for(int i=1;i<=Tools.threadCount;i++)
                //连接服务端，进行注册，并发送一条测试数据（可以通过验证并保存的）
                new TBoxTester(i).start();
        }catch (Exception e){e.printStackTrace();}
    }


    public static void fileLog(String conent) {
        System.out.println(conent);
        String file="E:\\test-"+hostIp+"-"+threadCount +".log";
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

    public  static byte[] getBytesFromByteBuf(ByteBuf buf){
        //基于netty
        byte[] result = new byte[buf.readableBytes()];
        buf.readBytes(result, 0, buf.readableBytes());
        return result;
    }

    public  static String  getRegSuccessStr(int k){
        //生成注册报文
        Long vin=10000000000000000l+k;//12345678919991234l+k;
        Long sn=100000000000l+k;//123456789199l+k
        String start="23 23 00 4C 00 56 1E 16 3D 13 01 33 35 35 30 36 35 30 35 33 33 31 31 30 30 31 00 00 00 00 0C 00 00 56 1E 16 3D ";//包头和size
        //String[] replace={"31","32","33","34","35","36","37","38","39"};
        //根据注册校验结果，形成返回数据包
        StringBuilder sb=new StringBuilder();
        sb.append(start);
     /*   for (int j = 0; j <17 ; j++) {
     //随机生成vin
            int max=8;
            int min=0;
            Random random = new Random();
            int s = random.nextInt(max)%(max-min+1) + min;
            sb.append(replace[s]+" ");
        }*/
        sb.append(getSpaceHexFromLong(sn));
        sb.append(getSpaceHexFromLong(vin));

        ByteBuffer bb= ByteBuffer.allocate(1024);
        String byteString=sb.toString();
        String[] command=byteString.split(" ");
        byte[] abc=new byte[command.length];
        for(int i=0;i<command.length;i++){
            abc[i]=Integer.valueOf(command[i],16).byteValue();
        }
        bb.put(abc);
        for(int i=0;i<15;i++)
            bb.put(Integer.valueOf("00", 16).byteValue());
        bb.flip();
        byte[] bodyData=getBytesFromByteBuffer(bb);//不包含checkSum的字节数组
        ByteBuffer re= ByteBuffer.allocate(1024);
        re.put(bodyData);
        re.put(getCheckSum(bodyData));
        re.flip();
        return bytes2hex(getBytesFromByteBuffer(re));
        //String aaa="23 23 00 4C 00 56 1E 16 3D 13 01 33 35 35 30 36 35 30 35 33 33 31 31 30 30 31 00 00 00 00 0C 00 00 56 1E 16 3D 31 32 33 34 35 36 37 38 39 31 39 39 31 32 33 34 35 36 37 38 39 31 39 39 39 31 32 33 34 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 6B ";
        // return aaa;
    }
    public  static byte getCheckSum(byte[] bytes){
        //将字节数组进行异或操作求和
        byte sum=bytes[0];
        for(int i=1;i<bytes.length;i++){
            sum^=bytes[i];
        }
        return sum;
    }
    public  static byte[] getBytesFromByteBuffer(ByteBuffer buff){
        byte[] result = new byte[buff.remaining()];
        if (buff.remaining() > 0) {
            buff.get(result, 0, buff.remaining());
        }
        return result;
    }
    public  static ByteBuffer getByteBuffer(String str){
        //根据16进制字符串得到buffer
        ByteBuffer bb= ByteBuffer.allocate(1024);
        String[] command=str.split(" ");
        byte[] abc=new byte[command.length];
        for(int i=0;i<command.length;i++){
            abc[i]=Integer.valueOf(command[i],16).byteValue();
        }
        bb.put(abc);
        bb.flip();
        return bb;
    }


    public  static String bytes2hex(byte[] bytes)
    {
        /**
         * 第一个参数的解释，记得一定要设置为1
         *  signum of the number (-1 for negative, 0 for zero, 1 for positive).
         */
        BigInteger bigInteger = new BigInteger(1, bytes);
        return getSpaceHex(bigInteger.toString(16));
    }
    public  static String getSpaceHex(String str){
        //将不带空格的16进制字符串加上空格
        String re="";
        String regex = "(.{2})";
        re = str.replaceAll (regex, "$1 ");
        return re;
    }
    public  static String getSpaceHexFromLong(Long vin){
        //将数字12345678919991234转换成31 32 33 34 35 36 37 38 39 31 39 39 39 31 32 33 34
        String str=String.valueOf(vin);
        String re="";
        String regex = "(.{1})";
        re = str.replaceAll (regex, "3$1 ");
        return re;
    }
}
