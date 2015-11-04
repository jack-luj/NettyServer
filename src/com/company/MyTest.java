package com.company;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
public class MyTest {
    // 接收在run方法中捕获的异常，然后自定义方法抛出异常
    //private static Throwable exception;
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String result = "";
        ExecutorService executor = Executors.newSingleThreadExecutor();
        FutureTask<String> future =
                new FutureTask<String>(new Callable<String>() {//使用Callable接口作为构造参数
                    public String call() {
                        //真正的任务在这里执行，这里的返回值类型为String，可以为任意类型
                        try {
                            Thread.sleep(10000);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            //exception = e;
                            //e.printStackTrace();
                        }
                        return "11111";
                    }});
        executor.execute(future);
        //在这里可以做别的任何事情
        for(int i=0;i<8;i++){
            try {
                Thread.sleep(2000);
                System.out.println(new Date().toLocaleString());
            } catch (InterruptedException e){}
        }

        try {
           // result = future.get(5000, TimeUnit.MILLISECONDS); //取得结果，同时设置超时执行时间为5秒。同样可以用future.get()，不设置执行超时时间取得结果
            result = future.get();
        } catch (InterruptedException e) {
            //System.out.println("任务已经取消");
            future.cancel(true);
        } catch (ExecutionException e) {
            future.cancel(true);
        }
       /* catch (TimeoutException e) {
            future.cancel(true);
        } */
        finally {
            executor.shutdown();
        }
        System.out.println("result:"+result);
    }

 /* public void throwException() throws FileNotFoundException, IOException {
         if (exception instanceof FileNotFoundException)
             throw (FileNotFoundException) exception;
         if (exception instanceof IOException)
             throw (IOException) exception;
     }*/

}