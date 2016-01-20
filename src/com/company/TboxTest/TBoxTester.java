package com.company.TboxTest;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.nio.channels.Selector;
import java.util.Date;

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
            Tools.fileLog( new Date().toLocaleString()+" error:"+e);}
    }



    public void connect(int port, String host) throws Exception {
        // ���ÿͻ���NIO�߳���
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class)
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<io.netty.channel.socket.SocketChannel>() {
                        @Override
                        public void initChannel(io.netty.channel.socket.SocketChannel ch)
                                throws Exception {
                            ch.pipeline().addLast(new ClientHandler(index));
                        }
                    });
            // �����첽���Ӳ���
            ChannelFuture f = b.connect(host, port).sync();
            // �����ͻ�����·�ر�
            f.channel().closeFuture().sync();
        } finally {
            // �����˳����ͷ�NIO�߳���
            group.shutdownGracefully();
        }
    }


}
