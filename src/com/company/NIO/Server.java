package com.company.NIO;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * ���ݽ��ն˿�.<br>
 * ������com.hp.acquire.port����ָ����TCP�˿�,�ѽ��յ������ݿ���ת�浽�ڲ��������,���������������������ӳ���ȡ������
 * ���д���.<br>
 * ���ݽ��ն˿�Ҳ���������Դ�������Խ��л���У�鹤��,��Դ�����Ͳ����������ݻᱻ����.
 */
public class Server {
    // ���ڼ������Channel״̬��Selector
      private int _acquirePort=1234;

    // ��־
    private Logger _logger;

    private Selector selector = null;

    public void init() throws IOException {
        this._logger = LoggerFactory.getLogger(Server.class);
        selector = Selector.open();
        // ͨ��open��������һ��δ�󶨵�ServerSocketChannelʵ��
        ServerSocketChannel server = ServerSocketChannel.open();
        InetSocketAddress isa = new InetSocketAddress(_acquirePort);
        // ����ServerSocketChannel�󶨵�ָ��IP��ַ
        server.socket().bind(isa);
        // ����ServerSocket�Է�������ʽ����
        server.configureBlocking(false);
        // ��serverע�ᵽָ��Selector����
        server.register(selector, SelectionKey.OP_ACCEPT);
        // ����׼��ִ�ж�ȡ���ݵ�ByteBuffer
        ByteBuffer buff = ByteBuffer.allocate(1024);
        while (selector.select() > 0) {
            // ���δ���selector�ϵ�ÿ����ѡ���SelectionKey
            Set<SelectionKey> sks=selector.selectedKeys();
            Iterator keys = sks.iterator();
            while (keys.hasNext()){
                SelectionKey sk=(SelectionKey)keys.next();
                // ��selector�ϵ���ѡ��Key����ɾ�����ڴ����SelectionKey
                keys.remove();
                // ���sk��Ӧ��ͨ�������ͻ��˵���������
                if (sk.isAcceptable()) {
                    // ����accept�����������ӣ������������˶�Ӧ��SocketChannel
                    SocketChannel sc = server.accept();
                    // ���ò��÷�����ģʽ
                    sc.configureBlocking(false);
                    // ����SocketChannelҲע�ᵽselector
                    sc.register(selector, SelectionKey.OP_READ);
                }
                // ���sk��Ӧ��ͨ����������Ҫ��ȡ
                if (sk.isReadable()) {
                    // ��ȡ��SelectionKey��Ӧ��Channel����Channel���пɶ�������
                    SocketChannel sc = (SocketChannel) sk.channel();
                    // ��ʼ��ȡ����

                    try {
                        while (sc.read(buff) > 0) {
                            buff.flip();
                            this._logger.info("content" + buff);
                            sc.write(buff);
                            if (buff.hasRemaining()) {
                                buff.compact();
                            } else {
                                buff.clear();
                            }
                        }
                        // ��ӡ�Ӹ�sk��Ӧ��Channel���ȡ��������
                        this._logger.info("accpect content" + buff);
                    }
                    // �����׽����sk��Ӧ��Channel�������쳣����������Channel
                    // ��Ӧ��Client���������⣬���Դ�Selector��ȡ��sk��ע��
                    catch (IOException ex) {
                        // ��Selector��ɾ��ָ����SelectionKey
                        sk.cancel();
                        if (sk.channel() != null) {
                            sk.channel().close();
                        }
                    }
                }
            }
        }
    }
public  static  void main(String[] args){
    try{
        new Server().init();
    }catch (Exception e){
        e.printStackTrace();
    }
}
}
