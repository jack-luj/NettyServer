package com.company;

import java.util.Date;

/**
 * Created by Administrator on 2016/1/17.
 */
public class HashMapTimeTest {
    public static void main(String[] args) throws InterruptedException {
        java.util.concurrent.ConcurrentHashMap<String, String> firstHashMap=new java.util.concurrent.ConcurrentHashMap<String, String>();

        long t1=new Date().getTime();
        for(int l=0;l<500000;l++){
            firstHashMap.put(String.valueOf(l),"data"+l);
        }
        for(int l=0;l<500000;l++){
            //如果key和value不同，说明在两个线程put的过程中出现异常。
           // System.out.println(String.valueOf(l) + ":" + firstHashMap.get(String.valueOf(l)));
        }
        long t2=new Date().getTime();
        System.out.println("java.util.HashMap:"+(t2-t1)+"ms");

    }
}
