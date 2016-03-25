package com.tools;

import com.db.everyDay.VirtualThreadCar;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by jack lu on 2016/3/25.
 */
public class TimeTaskTest {
    public static  void main(String[] args){
        for (int i = 0; i <5 ; i++) {
            Timer timerA=new Timer();
          //  timerA.scheduleAtFixedRate(new MyTask("定时器"+i),new Date(),2*1000);
        }
      //  timer.schedule(new MyTask(),5*1000,3*1000);

    try{
    FileReader fr=new FileReader("d:\\db\\trip_id.txt");
    BufferedReader br=new BufferedReader(fr);
    String line="";
    String[] arrs=null;
        int i=0;
    while ((line=br.readLine())!=null) {
        i++;
        arrs=line.split(",");
        System.out.println(i+"-"+arrs[0] + " : " + arrs[1]);
    }
    br.close();
    fr.close();
    }catch (IOException e){

    }

    }





}
