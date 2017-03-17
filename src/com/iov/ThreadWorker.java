package com.iov;

import java.util.Date;

/**
 * Created by jackl on 2017/3/6.
 */
public class ThreadWorker {


    public void work(){
        for (int i = 1; i <6 ; i++) {
            WorkMan workMan=new WorkMan(5);
            Thread _workThread=new Thread(workMan);
            _workThread.setName("worker"+i);
            _workThread.start();
        }

        for (int i = 0; i < 10; i++) {
            try{
                Thread.sleep(1000l);}
            catch (InterruptedException e){
                e.printStackTrace();
            }

            System.out.println(new Date().toLocaleString()+" ["+Thread.currentThread().getName()+"] 当前线程数:" + Thread.activeCount());
        }

    }

    public static void main(String[] args){
        System.out.println("---------------start-----------");
        ThreadWorker threadWorker =new ThreadWorker();
        threadWorker.work();
        System.out.println("----------------end------------");
    }
}
