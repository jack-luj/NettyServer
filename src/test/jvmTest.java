package test;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

/**
 * Created by jackl on 2017/2/8.
 */
public class JvmTest {

    public static void main(String[] args){

        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage memoryUsage = memoryMXBean.getHeapMemoryUsage(); //椎内存使用情况
        long totalMemorySize = memoryUsage.getInit(); //初始的总内存
        long maxMemorySize = memoryUsage.getMax(); //最大可用内存
        long usedMemorySize = memoryUsage.getUsed(); //已使用的内存

        System.out.println("totalMemorySize:"+totalMemorySize);
        System.out.println("maxMemorySize:"+maxMemorySize);
        System.out.println("usedMemorySize:"+usedMemorySize);
        System.out.println("==========================================");
        Runtime rt = Runtime.getRuntime();
        long rtotalMemorySize = rt.totalMemory(); //初始的总内存
        long rmaxMemorySiz = rt.maxMemory(); //最大可用内存
        long rfreeMemorySize = rt.freeMemory(); //当前可用内存

        System.out.println("rtotalMemorySize:"+rtotalMemorySize);
        System.out.println("rmaxMemorySize:"+rmaxMemorySiz);
        System.out.println("rfreeMemorySize:"+rfreeMemorySize);
    }
}
