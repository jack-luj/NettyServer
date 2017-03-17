package com.iov;

import java.lang.management.ManagementFactory;

/**
 * Created by jackl on 2017/3/8.
 */
public class JvmInfo {
    public static void main(String[] args){

        while (true) {
            String name = ManagementFactory.getRuntimeMXBean().getName();
            System.out.println(ManagementFactory.getThreadMXBean().getThreadCount());
            System.out.println("最大堆内存:" + Runtime.getRuntime().maxMemory() / 1024 + "M");
            long used = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getUsed();
            long max = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getMax();
            double r = 100.0 * used / max;
            System.out.println("内存使用比:" + used + "/" + max + "/%");
            System.out.println("内存使用比:" + r + "%");
            System.out.println(name);
            try{
            Thread.sleep(2000l);
        }catch (InterruptedException e){}
        }
    }
}
