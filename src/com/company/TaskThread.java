package com.company;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by luj on 2015/11/4.
 */
public class TaskThread implements Runnable {
    private String jobName = "";

    public TaskThread(String jobName) {
        super();
        this.jobName = jobName;
    }

    @Override
    public void run() {
        System.out.println(new Date().toString()+"execute " + jobName);
    }

    public static void main(String[] args) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(10);

        long initialDelay1 = 1;
        long period1 = 1;
        // �����ڿ�ʼ1����֮��ÿ��1����ִ��һ��job1
        service.scheduleAtFixedRate(
                new TaskThread("job1"), initialDelay1,
                period1, TimeUnit.SECONDS);

        service.schedule(
                new TaskThread("ont-shot"), 0, TimeUnit.SECONDS);

        long initialDelay2 = 1;
        long delay2 = 1;
        // �����ڿ�ʼ2����֮��ÿ��2����ִ��һ��job2
        service.scheduleWithFixedDelay(
                new TaskThread("job2"), initialDelay2,
                delay2, TimeUnit.SECONDS);
    }
}
