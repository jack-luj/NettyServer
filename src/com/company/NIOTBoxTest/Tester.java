package com.company.NIOTBoxTest;

import java.io.IOException;
import java.math.BigInteger;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;

/**
 * Created by luj on 2015/9/21.
 */
public class Tester extends Thread{
    private Selector selector;
    private int index;
    private boolean hasSendData=false;


    public Tester(int i){
        this.index=i;
    }

    public synchronized void run()
    {
        try{
        initClient(Tools.hostIp,Tools.hostPort);
        listen();
        }catch (Exception e){e.printStackTrace();
            Tools.fileLog( new Date().toLocaleString()+" "+index+" error:"+e);}
    }

    /**
     * 获得一个Socket通道，并对该通道做一些初始化的工作
     * @param ip 连接的服务器的ip
     * @param port  连接的服务器的端口号
     * @throws IOException
     */

    public void initClient(String ip,int port) throws IOException {

            // 获得一个Socket通道
            SocketChannel channel = SocketChannel.open();
            // 设置通道为非阻塞
            channel.configureBlocking(false);
            // 获得一个通道管理器
            this.selector = Selector.open();

            // 客户端连接服务器,其实方法执行并没有实现连接，需要在listen（）方法中调
            //用channel.finishConnect();才能完成连接
            channel.connect(new InetSocketAddress(ip, port));
            //将通道管理器和该通道绑定，并为该通道注册SelectionKey.OP_CONNECT事件。
            channel.register(selector, SelectionKey.OP_CONNECT);
    }

    /**
     * 采用轮询的方式监听selector上是否有需要处理的事件，如果有，则进行处理
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    public void listen() throws IOException {
        // 轮询访问selector
        while (true) {
            selector.select();
            // 获得selector中选中的项的迭代器
            Iterator ite = this.selector.selectedKeys().iterator();
            while (ite.hasNext()) {
                SelectionKey key = (SelectionKey) ite.next();
                // 删除已选的key,以防重复处理
                ite.remove();
                // 连接事件发生
                if (key.isConnectable()) {
                    SocketChannel channel = (SocketChannel) key
                            .channel();
                    // 如果正在连接，则完成连接
                    if(channel.isConnectionPending()){
                        channel.finishConnect();
                    }

                    // 设置成非阻塞
                    channel.configureBlocking(false);
                    //在这里可以给服务端发送信息哦
                   String sendS=getRegSuccessStr(index);
                    //Tools.fileLog(new Date().toLocaleString() + " - " + index + " 发送注册报文");
                   //channel.write(getByteBuffer(sendS));
                    //在和服务端连接成功之后，为了可以接收到服务端的信息，需要给通道设置读的权限。
                    channel.register(this.selector, SelectionKey.OP_READ);

                    // 获得了可读的事件
                } else if (key.isReadable()) {
                    read(key);
                }

            }

        }
    }
    /**
     * 处理读取服务端发来的信息 的事件
     * @param key
     * @throws IOException
     */
    public void read(SelectionKey key) throws IOException{
        SocketChannel channel = (SocketChannel) key.channel();
        // 创建读取的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        channel.read(buffer);
        buffer.flip();
        byte[] receiveData = getBytesFromByteBuffer(buffer);
        String receiveDataHexString=bytes2hex(receiveData);
        if(receiveDataHexString!=null&&receiveDataHexString.length()>1) {
            Tools.fileLog(new Date().toLocaleString() + " - " + index + " receive:" + receiveDataHexString);
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

                    Tools.fileLog(new Date().toLocaleString()+ " - " +index+" 实时数据发送完毕" );
                    System.out.println(new Date().toLocaleString()+ " - " +index+" send realTime Data" );
                    channel.write(getByteBuffer(sendStr));
                    hasSendData = true;
                  }

            }
        }
    }
    public  String  getRegSuccessStr(int k){
        //生成注册报文
        Long vin=10000000000000000l+k;//12345678919991234l+k;
        Long sn=100000000000l+k;//123456789199l+k
        String start="23 23 00 4C 00 56 1E 16 3D 13 01 33 35 35 30 36 35 30 35 33 33 31 31 30 30 31 00 00 00 00 0C 00 00 56 1E 16 3D ";//包头和size
        //String[] replace={"31","32","33","34","35","36","37","38","39"};
        //根据注册校验结果，形成返回数据包
        StringBuilder sb=new StringBuilder();
        sb.append(start);
     /*   for (int j = 0; j <17 ; j++) {
     //随机生成vin
            int max=8;
            int min=0;
            Random random = new Random();
            int s = random.nextInt(max)%(max-min+1) + min;
            sb.append(replace[s]+" ");
        }*/
        sb.append(getSpaceHexFromLong(sn));
        sb.append(getSpaceHexFromLong(vin));

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
       //String aaa="23 23 00 4C 00 56 1E 16 3D 13 01 33 35 35 30 36 35 30 35 33 33 31 31 30 30 31 00 00 00 00 0C 00 00 56 1E 16 3D 31 32 33 34 35 36 37 38 39 31 39 39 31 32 33 34 35 36 37 38 39 31 39 39 39 31 32 33 34 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 6B ";
       // return aaa;
    }
    public  byte getCheckSum(byte[] bytes){
        //将字节数组进行异或操作求和
        byte sum=bytes[0];
        for(int i=1;i<bytes.length;i++){
            sum^=bytes[i];
        }
        return sum;
    }


    public  byte[] getBytesFromByteBuffer(ByteBuffer buff){
        byte[] result = new byte[buff.remaining()];
        if (buff.remaining() > 0) {
            buff.get(result, 0, buff.remaining());
        }
        return result;
    }
    public  ByteBuffer getByteBuffer(String str){
        //根据16进制字符串得到buffer
        ByteBuffer bb= ByteBuffer.allocate(1024);
        String[] command=str.split(" ");
        byte[] abc=new byte[command.length];
        for(int i=0;i<command.length;i++){
            abc[i]=Integer.valueOf(command[i],16).byteValue();
        }
        bb.put(abc);
        bb.flip();
        return bb;
    }


    public  String bytes2hex(byte[] bytes)
    {
        /**
         * 第一个参数的解释，记得一定要设置为1
         *  signum of the number (-1 for negative, 0 for zero, 1 for positive).
         */
        BigInteger bigInteger = new BigInteger(1, bytes);
        return getSpaceHex(bigInteger.toString(16));
    }
    public  String getSpaceHex(String str){
        //将不带空格的16进制字符串加上空格
        String re="";
        String regex = "(.{2})";
        re = str.replaceAll (regex, "$1 ");
        return re;
    }
    public  String getSpaceHexFromLong(Long vin){
        //将数字12345678919991234转换成31 32 33 34 35 36 37 38 39 31 39 39 39 31 32 33 34
        String str=String.valueOf(vin);
        String re="";
        String regex = "(.{1})";
        re = str.replaceAll (regex, "3$1 ");
        return re;
    }



}
