package com.company;

/**
 * Created by jack lu on 2016/2/26.
 */
import java.io.File;
import java.util.ArrayList;

public class FileTest {
    private static ArrayList<String> filelist = new ArrayList<String>();

    public static void main(String[] args) throws Exception {

        String filePath = "D://Thrift";
        traverseFolder2(filePath);
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