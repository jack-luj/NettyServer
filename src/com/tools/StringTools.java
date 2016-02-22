package com.tools;

/**
 * Created by jack lu on 2016/2/18.
 */
public class StringTools {
    public static void main(String[] args){
        //"" ""
        //"1" 1
        //"1,2" 1 2
        String ss=null;


        int i = 170;
        System.out.println(getBitsFromLong(170l));
    }

    public  static char[] getBitsFromLong(long value){
        //4字节转二进制
        char[] array=new char[32];
        for (int j = 31; j >= 0; j--)
        {
            if (((1 << j) & value) != 0)
            {
                array[31-j]='1';
            }
            else
            {
                array[31-j]='0';
            }
        }
        return array;
    }

    public static char[] getBitsFromInteger(int value){
        char[] array=new char[16];
        for (int j = 15; j >= 0; j--)
        {
            if (((1 << j) & value) != 0)
            {
                array[15-j]='1';
            }
            else
            {
                array[15-j]='0';
            }
        }
        return array;
    }

    static void printInt(int i)
    {
        System.out.print( i+"-binary:");
            for (int j = 15; j >= 0; j--)
        {
            if (((1 << j) & i) != 0)
            {
                System.out.print("1");
            }
            else
            {
                System.out.print("0");
            }
        }
    }

    public static char[] getBitsFromShort(short a){
        //取包含8个数字的数组
        String binStr=getBinaryStrFromByte((byte)a);
        return binStr.toCharArray();
    }
    public static char[] getBitsFromByte(Byte a){
        //取包含8个数字的数组
        String binStr=getBinaryStrFromByte(a);
        return binStr.toCharArray();
    }
    public  static String getBinaryStrFromByte(byte b)
    {
        //将byte转换层二进制字符串 (byte)170  ->> 10101010
        String result ="";
        byte a = b;
        for (int i = 0; i < 8; i++)
        {
            byte c=a;
            a=(byte)(a>>1);
            a=(byte)(a<<1);
            if(a==c){
                result="0"+result;
            }else{
                result="1"+result;
            }
            a=(byte)(a>>1);
        }
        return result;
    }
}
