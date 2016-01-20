/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.company.TboxTest;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.Date;
import java.util.logging.Logger;

/**
 * @author lilinfeng
 * @date 2014年2月14日
 * @version 1.0
 */
public class ClientHandler extends ChannelHandlerAdapter {

    public int index;
    private boolean hasSendData=false;
    public ClientHandler(int i){
        this.index=i;
    }

    private static final Logger logger = Logger
            .getLogger(ClientHandler.class.getName());


    /**
     * Creates a client-side handler.
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        String sendS=Tools.getRegSuccessStr(index);
        ctx.channel().writeAndFlush(Tools.getByteBuf(sendS));
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        byte[] receiveData = Tools.getBytesFromByteBuf(buf);
        String receiveDataHexString=Tools.bytes2hex(receiveData);
        if(receiveDataHexString!=null&&receiveDataHexString.length()>1) {
            System.out.println(new Date().toLocaleString() + " thread " + index + " receive:" + receiveDataHexString);
            if (receiveDataHexString.length() == 51 && receiveDataHexString.substring(receiveDataHexString.length() - 6, receiveDataHexString.length() - 4).equals("00")) {
                String sendStr = "23 23 00 39 00 56 04 BF DA 22 01 31 32 33 34 35 36 37 38 39 30 31 32 33 34 35 01 00 00 00 01 00 00 00 00 00 1E 41 00 00 72 1F 06 1F 00 EA 00 63 00 7B 00 86 04 D2 64 65 66 67 0F 41 43 0F 96 ";
                if (hasSendData == false) {
                    //    Tools.fileLog("注册结果 thread" + index + " receive:" + receiveDataHexString.length() + ":" + receiveDataHexString.substring(receiveDataHexString.length() - 6, receiveDataHexString.length() - 4));
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Tools.fileLog(new Date().toLocaleString() + " " + e.getMessage());
                    }
                    System.out.println(new Date().toLocaleString() + " thread " + index + "    send:" + sendStr);
                    ctx.writeAndFlush(Tools.getByteBuf(sendStr));
                    hasSendData = true;
                    Tools.fileLog("实时数据发送完毕 - " + index + " - " + new Date().toLocaleString());
                }

            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // 释放资源
        logger.warning("Unexpected exception from downstream : "
                + cause.getMessage());
        ctx.close();
    }
}
