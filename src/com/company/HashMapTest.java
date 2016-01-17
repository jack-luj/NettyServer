package com.company;

import java.util.HashMap;

/**
 * Created by Administrator on 2016/1/17.
 */
public class HashMapTest {

    public static final java.util.HashMap<String, String> firstHashMap=new java.util.HashMap<String, String>();

                public static void main(String[] args) throws InterruptedException {

                //线程一
                Thread t1=new Thread(){
                        public void run() {
                                for(int i=0;i<250;i++){
                                        firstHashMap.put(String.valueOf(i), String.valueOf(i));
                                    }
                            }
                    };

                //线程二
                Thread t2=new Thread(){
                        public void run() {
                                for(int j=250;j<500;j++){
                                        firstHashMap.put(String.valueOf(j), String.valueOf(j));
                                    }
                            }
                   };

                t1.start();
                t2.start();

                //主线程休眠1秒钟，以便t1和t2两个线程将firstHashMap填装完毕。
                Thread.currentThread().sleep(1000);

                for(int l=0;l<500;l++){
                        //如果key和value不同，说明在两个线程put的过程中出现异常。
                        if(!String.valueOf(l).equals(firstHashMap.get(String.valueOf(l)))){
                                System.err.println(String.valueOf(l)+":"+firstHashMap.get(String.valueOf(l)));
                            }
                    }

            }
}
