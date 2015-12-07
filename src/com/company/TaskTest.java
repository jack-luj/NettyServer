package com.company;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 任务调度研究
 * Created by luj on 2015/12/4.
 */
public class TaskTest implements Runnable {
    private String jobName = "";

    public TaskTest(String jobName) {
        super();
        this.jobName = jobName;
    }

    @Override
    public void run() {

        System.out.println("now is :" + jobName + "-" + new Date().toLocaleString());
        try{
            Thread.sleep(3000);
        }catch (InterruptedException ee){}



    }

    public static void main(String[] args) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(10);

        long initialDelay1 = 1;
        long period1 = 1;
        // 从现在开始1秒钟之后，每隔1秒钟执行一次job1
        service.scheduleAtFixedRate(
                new TaskTest("job1"), initialDelay1,
                period1, TimeUnit.SECONDS);
/*
        long initialDelay2 = 1;
        long delay2 = 1;
        // 从现在开始2秒钟之后，每隔2秒钟执行一次job2
                service.scheduleWithFixedDelay(
                new TaskTest("job2"), initialDelay2,
                delay2, TimeUnit.SECONDS);
                */
    }
}