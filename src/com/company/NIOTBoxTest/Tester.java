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
     * ���һ��Socketͨ�������Ը�ͨ����һЩ��ʼ���Ĺ���
     * @param ip ���ӵķ�������ip
     * @param port  ���ӵķ������Ķ˿ں�
     * @throws IOException
     */

    public void initClient(String ip,int port) throws IOException {

            // ���һ��Socketͨ��
            SocketChannel channel = SocketChannel.open();
            // ����ͨ��Ϊ������
            channel.configureBlocking(false);
            // ���һ��ͨ��������
            this.selector = Selector.open();

            // �ͻ������ӷ�����,��ʵ����ִ�в�û��ʵ�����ӣ���Ҫ��listen���������е�
            //��channel.finishConnect();�����������
            channel.connect(new InetSocketAddress(ip, port));
            //��ͨ���������͸�ͨ���󶨣���Ϊ��ͨ��ע��SelectionKey.OP_CONNECT�¼���
            channel.register(selector, SelectionKey.OP_CONNECT);
    }

    /**
     * ������ѯ�ķ�ʽ����selector���Ƿ�����Ҫ������¼�������У�����д���
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    public void listen() throws IOException {
        // ��ѯ����selector
        while (true) {
            selector.select();
            // ���selector��ѡ�е���ĵ�����
            Iterator ite = this.selector.selectedKeys().iterator();
            while (ite.hasNext()) {
                SelectionKey key = (SelectionKey) ite.next();
                // ɾ����ѡ��key,�Է��ظ�����
                ite.remove();
                // �����¼�����
                if (key.isConnectable()) {
                    SocketChannel channel = (SocketChannel) key
                            .channel();
                    // ����������ӣ����������
                    if(channel.isConnectionPending()){
                        channel.finishConnect();
                    }

                    // ���óɷ�����
                    channel.configureBlocking(false);
                    //��������Ը�����˷�����ϢŶ
                   String sendS=getRegSuccessStr(index);
                    //Tools.fileLog(new Date().toLocaleString() + " - " + index + " ����ע�ᱨ��");
                   //channel.write(getByteBuffer(sendS));
                    //�ںͷ�������ӳɹ�֮��Ϊ�˿��Խ��յ�����˵���Ϣ����Ҫ��ͨ�����ö���Ȩ�ޡ�
                    channel.register(this.selector, SelectionKey.OP_READ);

                    // ����˿ɶ����¼�
                } else if (key.isReadable()) {
                    read(key);
                }

            }

        }
    }
    /**
     * �����ȡ����˷�������Ϣ ���¼�
     * @param key
     * @throws IOException
     */
    public void read(SelectionKey key) throws IOException{
        SocketChannel channel = (SocketChannel) key.channel();
        // ������ȡ�Ļ�����
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
                //    Tools.fileLog("ע���� thread" + index + " receive:" + receiveDataHexString.length() + ":" + receiveDataHexString.substring(receiveDataHexString.length() - 6, receiveDataHexString.length() - 4));
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Tools.fileLog(new Date().toLocaleString() + " " + e.getMessage());
                    }

                    Tools.fileLog(new Date().toLocaleString()+ " - " +index+" ʵʱ���ݷ������" );
                    System.out.println(new Date().toLocaleString()+ " - " +index+" send realTime Data" );
                    channel.write(getByteBuffer(sendStr));
                    hasSendData = true;
                  }

            }
        }
    }
    public  String  getRegSuccessStr(int k){
        //����ע�ᱨ��
        Long vin=10000000000000000l+k;//12345678919991234l+k;
        Long sn=100000000000l+k;//123456789199l+k
        String start="23 23 00 4C 00 56 1E 16 3D 13 01 33 35 35 30 36 35 30 35 33 33 31 31 30 30 31 00 00 00 00 0C 00 00 56 1E 16 3D ";//��ͷ��size
        //String[] replace={"31","32","33","34","35","36","37","38","39"};
        //����ע��У�������γɷ������ݰ�
        StringBuilder sb=new StringBuilder();
        sb.append(start);
     /*   for (int j = 0; j <17 ; j++) {
     //�������vin
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
        byte[] bodyData=getBytesFromByteBuffer(bb);//������checkSum���ֽ�����
        ByteBuffer re= ByteBuffer.allocate(1024);
        re.put(bodyData);
        re.put(getCheckSum(bodyData));
        re.flip();
        return bytes2hex(getBytesFromByteBuffer(re));
       //String aaa="23 23 00 4C 00 56 1E 16 3D 13 01 33 35 35 30 36 35 30 35 33 33 31 31 30 30 31 00 00 00 00 0C 00 00 56 1E 16 3D 31 32 33 34 35 36 37 38 39 31 39 39 31 32 33 34 35 36 37 38 39 31 39 39 39 31 32 33 34 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 6B ";
       // return aaa;
    }
    public  byte getCheckSum(byte[] bytes){
        //���ֽ�����������������
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
        //����16�����ַ����õ�buffer
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
         * ��һ�������Ľ��ͣ��ǵ�һ��Ҫ����Ϊ1
         *  signum of the number (-1 for negative, 0 for zero, 1 for positive).
         */
        BigInteger bigInteger = new BigInteger(1, bytes);
        return getSpaceHex(bigInteger.toString(16));
    }
    public  String getSpaceHex(String str){
        //�������ո��16�����ַ������Ͽո�
        String re="";
        String regex = "(.{2})";
        re = str.replaceAll (regex, "$1 ");
        return re;
    }
    public  String getSpaceHexFromLong(Long vin){
        //������12345678919991234ת����31 32 33 34 35 36 37 38 39 31 39 39 39 31 32 33 34
        String str=String.valueOf(vin);
        String re="";
        String regex = "(.{1})";
        re = str.replaceAll (regex, "3$1 ");
        return re;
    }



}
