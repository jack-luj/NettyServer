package com.company;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;


import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

/**
 * Created by luj on 2015/10/26.
 */
public class TesterAHandler extends ChannelHandlerAdapter {
    String heartBeatReq="23 23 00 20 01 55 D2 0F E7 26 01 00 00 00 00 00 00 00 00 00 00 11 00 00 00 00 00 00 00 00 00 00 00 55 BE E2 58 29 ";
    private static final Logger logger = Logger
            .getLogger(TesterAHandler.class.getName());
    private String threadName;
    private String regStr;
    private Timer timer;

    public TesterAHandler(String threadName,String regStr){
        this.threadName=threadName;
        this.regStr=regStr;
    }
    @Override
    public void channelActive(ChannelHandlerContext ctx) {

        ctx.writeAndFlush(DataTool.getByteBuf(regStr));
        //(1)发送注册请求
         }
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        ByteBuf receive = (ByteBuf) msg;
        String hexStr= DataTool.getStringFromByteBuf(receive);
        logger.info(threadName + " receive:" + hexStr);
        try{
            Thread.sleep(5000);
        }catch (InterruptedException e){e.printStackTrace();}
        ctx.writeAndFlush(DataTool.getByteBuf(heartBeatReq));
        //(1)发送心跳请求
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx){
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // 释放资源
        cause.printStackTrace();
        ctx.close();
    }





}
