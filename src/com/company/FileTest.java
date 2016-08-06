package com.company;

/**
 * Created by jack lu on 2016/2/26.
 */
import org.apache.commons.lang3.StringEscapeUtils;

import java.io.File;
import java.util.ArrayList;

public class FileTest {
    public String FileTest(){
        return "";
    }
    private static ArrayList<String> filelist = new ArrayList<String>();

    public static void main(String[] args) throws Exception {

       // String filePath = "D://Thrift";
       // traverseFolder2(filePath);

        String a="\\xe5\\x8f\\x91\\xe7\\x94\\x9f\\xe9\\x94\\x99\\xe8\\xaf\\xaf\\xef\\xbc\\x8c\\xe8\\xaf\\xb7\\xe9\\x87\\x8d\\xe8\\xaf\\x95";



        String ccc= StringEscapeUtils.unescapeJava(a.replace("\\x", "\\u00"));
        String b=new String(ccc.getBytes("Unicode"),"UTF-8");
        System.out.println(b);
    }
    /*
     * 通过递归得到某一路径下所有的目录及其文件
     */
    public static void traverseFolder2(String path) {

        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (files.length == 0) {
                System.out.println("文件夹是空的!");
                return;
            } else {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        System.out.println("文件夹:" + file2.getAbsolutePath());
                        traverseFolder2(file2.getAbsolutePath());
                    } else {
                        System.out.println("文件:" + file2.getAbsolutePath());
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
    }
}