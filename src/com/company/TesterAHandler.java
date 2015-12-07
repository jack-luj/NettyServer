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
    //String heartBeatReq="23 23 00 20 01 55 D2 0F E7 26 01 00 00 00 00 00 00 00 00 00 00 11 00 00 00 00 00 00 00 00 00 00 00 55 BE E2 58 29 ";
    String heartBeatReq="AA 55 00 50 FF AF BF 05 16 03 49 4E 43 41 52 31 30 30 30 31 38 36 31 39 39 37 00 00 00 00 5D 31 38 30 00 4C 46 56 33 41 32 38 4B 38 46 33 30 33 38 36 35 33 00 56 31 2E 36 31 2E 30 30 00 56 31 2E 30 2E 30 00 56 33 2E 31 36 2E 35 37 00 FF 00 11 17  ";
    String checkStr="23 23 00 39 01 56 04 BF DA 11 01 31 32 33 34 35 36 37 38 39 30 31 32 33 34 35 01 00 00 00 01 00 00 56 1E 16 3D 08 31 32 33 34 35 36 31 32 33 34 35 36 56 04 BF DA 01 01 01 01 01 01 01 01 73 ";
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
        for(int i=0;i<50;i++)
        ctx.writeAndFlush(DataTool.getByteBuf(checkStr));
        //(1)发送注册请求
         }
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        ByteBuf receive = (ByteBuf) msg;
        String hexStr= DataTool.getStringFromByteBuf(receive);
        logger.info(threadName + " receive:" + hexStr);
    /*    try{
            Thread.sleep(5000);
        }catch (InterruptedException e){e.printStackTrace();}
        ctx.writeAndFlush(DataTool.getByteBuf(heartBeatReq));*/
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
