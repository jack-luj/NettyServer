package com.company;

import io.netty.buffer.ByteBuf;

import static io.netty.buffer.Unpooled.buffer;

/**
 * Created by luj on 2015/10/26.
 */
public class DataTool {

    public  static String bytes2hex(byte[] bArray) {
        //�ֽ�����ת16�����ַ���
        StringBuffer sb = new StringBuffer(bArray.length);
        String sTemp;
        for (int i = 0; i < bArray.length; i++) {
            sTemp = Integer.toHexString(0xFF & bArray[i]);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase());
        }
        return getSpaceHex(sb.toString());
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
    public static String getStringFromByteBuf(ByteBuf buf){

        return bytes2hex(getBytesFromByteBuf(buf));

    }
    public  static String getSpaceHex(String str){
        //�������ո��16�����ַ������Ͽո�
        String re="";
        String regex = "(.{2})";
        re = str.replaceAll (regex, "$1 ");
        return re;
    }
}
