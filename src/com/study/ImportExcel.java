package com.study;
import java.io.File;
import jxl.*;
/**
 * Created by jackl on 2016/8/29.
 */
public class ImportExcel {

    void read(){
        try{
            Workbook workbook = Workbook.getWorkbook(new File("C:\\Users\\ocr\\source.xls"));
            Sheet sheet = workbook.getSheet(0);
            for (int i = 0; i < 20; i++) {
                for (int j = 0; j < 40; j++) {
                    Cell a = sheet.getCell(j, i);
                    String str = a.getContents();
                    System.out.print(str+"    ");
                }
                System.out.println();
            }
        }catch (Exception e){e.printStackTrace();}
    }

    public static void main(String[] args){
        System.out.println("import excel data start -------------");
        ImportExcel importExcel=new ImportExcel();
        importExcel.read();
        System.out.println("import excel data end   -------------");
    }
}
