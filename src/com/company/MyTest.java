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
    // ������run�����в�����쳣��Ȼ���Զ��巽���׳��쳣
    //private static Throwable exception;
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String result = "";
        ExecutorService executor = Executors.newSingleThreadExecutor();
        FutureTask<String> future =
                new FutureTask<String>(new Callable<String>() {//ʹ��Callable�ӿ���Ϊ�������
                    public String call() {
                        //����������������ִ�У�����ķ���ֵ����ΪString������Ϊ��������
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
        //���������������κ�����
        for(int i=0;i<8;i++){
            try {
                Thread.sleep(2000);
                System.out.println(new Date().toLocaleString());
            } catch (InterruptedException e){}
        }

        try {
           // result = future.get(5000, TimeUnit.MILLISECONDS); //ȡ�ý����ͬʱ���ó�ʱִ��ʱ��Ϊ5�롣ͬ��������future.get()��������ִ�г�ʱʱ��ȡ�ý��
            result = future.get();
        } catch (InterruptedException e) {
            //System.out.println("�����Ѿ�ȡ��");
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