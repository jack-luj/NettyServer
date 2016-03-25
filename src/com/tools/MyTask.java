package com.tools;

import java.util.Date;
import java.util.TimerTask;

/**
 * Created by jack lu on 2016/3/25.
 */
public class MyTask extends TimerTask {
    private String name;

    public MyTask(String name){
        this.name=name;
    }
    public void run()
    {
        System.out.println(name+"-定时任务执行"+new Date().toLocaleString());
    }
}
