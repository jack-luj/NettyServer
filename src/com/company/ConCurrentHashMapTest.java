package com.company;

import java.util.HashMap;

/**
 * Created by Administrator on 2016/1/17.
 */
public class ConCurrentHashMapTest {

    public static final HashMap<String, String> firstHashMap=new HashMap<String, String>();

                public static void main(String[] args) throws InterruptedException {

                //�߳�һ
                Thread t1=new Thread(){
                        public void run() {
                                for(int i=0;i<250;i++){
                                        firstHashMap.put(String.valueOf(i), String.valueOf(i));
                                    }
                            }
                    };

                //�̶߳�
                Thread t2=new Thread(){
                        public void run() {
                                for(int j=250;j<500;j++){
                                        firstHashMap.put(String.valueOf(j), String.valueOf(j));
                                    }
                            }
                   };

                t1.start();
                t2.start();

                //���߳�����1���ӣ��Ա�t1��t2�����߳̽�firstHashMap��װ��ϡ�
                Thread.currentThread().sleep(1000);

                for(int l=0;l<500;l++){
                        //���key��value��ͬ��˵���������߳�put�Ĺ����г����쳣��
                        if(!String.valueOf(l).equals(firstHashMap.get(String.valueOf(l)))){
                                System.err.println(String.valueOf(l)+":"+firstHashMap.get(String.valueOf(l)));
                            }
                    }

            }
}
