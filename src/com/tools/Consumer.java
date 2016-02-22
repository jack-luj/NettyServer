package com.tools;

import java.util.Date;
import java.util.concurrent.BlockingQueue;

/**
 * Created by jack lu on 2016/2/19.
 */
public class Consumer implements Runnable{

    protected BlockingQueue queue = null;

    public Consumer(BlockingQueue queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            System.out.println(new Date().toLocaleString()+":"+queue.take());
            System.out.println(new Date().toLocaleString()+":"+queue.take());
            System.out.println(new Date().toLocaleString()+":"+queue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
