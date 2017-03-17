package com.iov;

import java.util.Date;

/**
 * Created by jackl on 2017/3/6.
 */
public class WorkMan implements Runnable{
    private int max;

    public WorkMan(int max) {
        this.max = max;
    }

    @Override
    public void run() {
        for(int i=1;i<=max;i++){
            try{
            Thread.sleep(1000l);}
            catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println(new Date().toLocaleString()+" ["+Thread.currentThread().getName()+"]- "+i);
        }

    }
}
