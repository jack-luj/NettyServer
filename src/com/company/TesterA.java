package com.company;

import com.phei.netty.basic.TimeClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Random;

/**
 * Created by luj on 2015/10/26.
 */
public class TesterA extends Thread{
    //客户端并发请求测试，连接 >注册 >心跳
    public String threadName;
    private String regStr;

    public TesterA(String name,String regStr){
        this.threadName=name;
        this.regStr=regStr;
    }
    public synchronized void run()
    {
        try{
            connect(9000,"127.0.0.1");
        }catch (Exception e){e.printStackTrace();}
    }

    public void connect(int port, String host) throws Exception {
        // 配置客户端NIO线程组
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch)
                                throws Exception {
                            ch.pipeline().addLast(new TesterAHandler(threadName,regStr));
                        }
                    });

            // 发起异步连接操作
            ChannelFuture f = b.connect(host, port).sync();

            // 当代客户端链路关闭
            f.channel().closeFuture().sync();
        } finally {
            // 优雅退出，释放NIO线程组
            group.shutdownGracefully();
        }
    }





    static void printInt(String s, int i)
    {
        System.out.println(s + ", int:" + i + ",binary:");
        System.out.print("   ");
        for (int j = 31; j >= 0; j--)
        {
            if (((1 << j) & i) != 0)
            {
                System.out.print("1");
            }
            else
            {
                System.out.print("0");
            }
        }
    }

    public  static void main(String[] args) {
        // write your code here
        String registerSuccess="23 23 00 4C 00 56 1E 16 3D 13 01 33 35 35 30 36 35 30 35 33 33 31 31 30 30 31 00 00 00 00 0C 00 00 56 1E 16 3D 31 32 33 34 35 36 37 38 39 31 39 39 31 32 33 34 35 36 37 38 39 31 39 39 39 31 32 33 34 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 6B ";
        String[]  sss =new String[10];
     /*   for(int i=0;i<10;i++){
            sss[i]=getRegSuccessStr();
            System.out.println( sss[i]);
        }*/
        for(int i=0;i<1;i++){

            new TesterA("Thread-"+i,registerSuccess).start();
        }

    }


    /////////////////////////////
    public  static String  getRegSuccessStr(){
        String start="23 23 00 4D 01 55 D2 0F E7 13 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 55 BE E2 58 31 32 33 34 35 36 37 38 39 31 39 39 ";//包头和size
        String[] replace={"31","32","33","34","35","36","37","38","39"};
        //根据注册校验结果，形成返回数据包
        StringBuilder sb=new StringBuilder();
        sb.append(start);
        //随机生成vin
        for (int j = 0; j <17 ; j++) {
            int max=8;
            int min=0;
            Random random = new Random();
            int s = random.nextInt(max)%(max-min+1) + min;
            sb.append(replace[s]+" ");
        }
        ByteBuffer bb= ByteBuffer.allocate(1024);
        String byteString=sb.toString();
        String[] command=byteString.split(" ");
        byte[] abc=new byte[command.length];
        for(int i=0;i<command.length;i++){
            abc[i]=Integer.valueOf(command[i],16).byteValue();
        }
        bb.put(abc);
        for(int i=0;i<15;i++)
            bb.put(Integer.valueOf("00", 16).byteValue());
        bb.flip();
        byte[] bodyData=getBytesFromByteBuffer(bb);//不包含checkSum的字节数组
        ByteBuffer re= ByteBuffer.allocate(1024);
        re.put(bodyData);
        re.put(getCheckSum(bodyData));
        re.flip();
        return bytes2hex(getBytesFromByteBuffer(re));
    }
    public  static byte getCheckSum(byte[] bytes){
        //将字节数组进行异或操作求和
        byte sum=bytes[0];
        for(int i=1;i<bytes.length;i++){
            sum^=bytes[i];
        }
        return sum;
    }
    public  static byte[] getBytesFromByteBuffer(ByteBuffer buff){
        byte[] result = new byte[buff.remaining()];
        if (buff.remaining() > 0) {
            buff.get(result, 0, buff.remaining());
        }
        return result;
    }
    public  static String bytes2hex(byte[] bytes)
    {
        /**
         * 第一个参数的解释，记得一定要设置为1
         *  signum of the number (-1 for negative, 0 for zero, 1 for positive).
         */
        BigInteger bigInteger = new BigInteger(1, bytes);
        return getSpaceHex(bigInteger.toString(16));
    }
    public  static String getSpaceHex(String str){
        //将不带空格的16进制字符串加上空格
        String re="";
        String regex = "(.{2})";
        re = str.replaceAll (regex, "$1 ");
        return re;
    }
    /////////////////////
    //////////////////////////////
}
