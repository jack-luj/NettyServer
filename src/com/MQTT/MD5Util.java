package com.MQTT;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

    /**
     * MD5 16Œª¥Û–¥
     * @param sourceStr
     * @return
     */
    private static String MD5To16UpperCase(String sourceStr) {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(sourceStr.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString().substring(8, 24).toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        }
        return result;
    }


    public static void main(String[] args) {
        System.out.println(MD5Util.MD5To16UpperCase("12345678919912345678919991234"));
       }
}