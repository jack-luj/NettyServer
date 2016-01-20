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
    public static int threadCount=3000;
    public static String hostIp="localhost";//121.40.157.200 //localhost
    public static int hostPort=9000;
    public static void main(String[] args) {
        try{
            Tools.fileLog("���Կ�ʼ" + new Date().toLocaleString());
            for(int i=1;i<=Tools.threadCount;i++)
                //���ӷ���ˣ�����ע�ᣬ������һ���������ݣ�����ͨ����֤������ģ�
                new TBoxTester(i).start();
        }catch (Exception e){e.printStackTrace();}
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

    public static ByteBuf getByteBuf(String str){
        //����16�����ַ����õ�ByteBuf����(netty)
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
        //����netty
        byte[] result = new byte[buf.readableBytes()];
        buf.readBytes(result, 0, buf.readableBytes());
        return result;
    }

    public  static String  getRegSuccessStr(int k){
        //����ע�ᱨ��
        Long vin=10000000000000000l+k;//12345678919991234l+k;
        Long sn=100000000000l+k;//123456789199l+k
        String start="23 23 00 4C 00 56 1E 16 3D 13 01 33 35 35 30 36 35 30 35 33 33 31 31 30 30 31 00 00 00 00 0C 00 00 56 1E 16 3D ";//��ͷ��size
        //String[] replace={"31","32","33","34","35","36","37","38","39"};
        //����ע��У�������γɷ������ݰ�
        StringBuilder sb=new StringBuilder();
        sb.append(start);
     /*   for (int j = 0; j <17 ; j++) {
     //�������vin
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
        byte[] bodyData=getBytesFromByteBuffer(bb);//������checkSum���ֽ�����
        ByteBuffer re= ByteBuffer.allocate(1024);
        re.put(bodyData);
        re.put(getCheckSum(bodyData));
        re.flip();
        return bytes2hex(getBytesFromByteBuffer(re));
        //String aaa="23 23 00 4C 00 56 1E 16 3D 13 01 33 35 35 30 36 35 30 35 33 33 31 31 30 30 31 00 00 00 00 0C 00 00 56 1E 16 3D 31 32 33 34 35 36 37 38 39 31 39 39 31 32 33 34 35 36 37 38 39 31 39 39 39 31 32 33 34 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 6B ";
        // return aaa;
    }
    public  static byte getCheckSum(byte[] bytes){
        //���ֽ�����������������
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
        //����16�����ַ����õ�buffer
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
         * ��һ�������Ľ��ͣ��ǵ�һ��Ҫ����Ϊ1
         *  signum of the number (-1 for negative, 0 for zero, 1 for positive).
         */
        BigInteger bigInteger = new BigInteger(1, bytes);
        return getSpaceHex(bigInteger.toString(16));
    }
    public  static String getSpaceHex(String str){
        //�������ո��16�����ַ������Ͽո�
        String re="";
        String regex = "(.{2})";
        re = str.replaceAll (regex, "$1 ");
        return re;
    }
    public  static String getSpaceHexFromLong(Long vin){
        //������12345678919991234ת����31 32 33 34 35 36 37 38 39 31 39 39 39 31 32 33 34
        String str=String.valueOf(vin);
        String re="";
        String regex = "(.{1})";
        re = str.replaceAll (regex, "3$1 ");
        return re;
    }
}
