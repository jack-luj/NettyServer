package com.company.TboxTest;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import io.netty.channel.socket.SocketChannel;

/**
 * Created by luj on 2015/9/21.
 */
public class TBoxTester extends Thread{
    private int index;
    public TBoxTester(int i){
        this.index=i;
    }

    public synchronized void run()
    {
        try{
            connect(Tools.hostPort, String.valueOf(Tools.hostIp));
            }catch (Exception e){e.printStackTrace();
            Tools.fileLog( new Date().toLocaleString()+" - 【"+index+"】 error:"+e);}
    }

    private ScheduledExecutorService executor = Executors
            .newScheduledThreadPool(1);
    EventLoopGroup group = new NioEventLoopGroup();

    public void connect(int port, String host) throws Exception {
        // 配置客户端NIO线程组
        Tools.fileLog(new Date().toLocaleString()+" - 【"+index+"】 正在连接服务器 " + host + ":" + port);
        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch)
                                throws Exception {
                            ch.pipeline().addLast(new ClientHandler(index));
                        }
                    });
            // 发起异步连接操作
            ChannelFuture future = b.connect(
                    new InetSocketAddress(host, port)).sync();
            future.channel().closeFuture().sync();
        } finally {
            // 所有资源释放完成之后，清空资源，再次发起重连操作
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        try {
                            System.out.println(new Date().toLocaleString()+" - 【"+index+"】发起重连");
                            connect(port, host);// 发起重连操作
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }




}
