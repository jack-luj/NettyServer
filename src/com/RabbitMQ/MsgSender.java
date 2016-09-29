package com.RabbitMQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 接收车牌参数，匹配标签，产生消息
 * Created by jackl on 2016/8/30.
 */
public class MsgSender {
    private static final String EXCHANGE_NAME = "direct_logs";
    private static final String HOST_NAME = "localhost";

    List<Msg> msgList;
    private  String licensePlate;
    public MsgSender(String licensePlate){
        this.licensePlate=licensePlate;
    }

    private  List<Msg> generateMsg(){
        msgList=new ArrayList<Msg>();
        //todo 匹配标签
        msgList.add(new Msg("0","给0的消息:"+licensePlate));
        //msgList.add(new Msg("1","车牌:"+licensePlate+":蓝色:新客户|将要续保:保养工时费6折"));
        msgList.add(new Msg("1","车牌:"+licensePlate+":蓝色:新客户|新客户办理会员优惠:保养工时费6折"));

        return msgList;
    }
    private void send(){
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(HOST_NAME);
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, "direct");

            for (Msg msg:msgList) {
                System.out.println(msg.getTarget() + ":" + msg.getMessage());
                String severity = msg.getTarget();
                String message = msg.getMessage();//
                channel.basicPublish(EXCHANGE_NAME, severity, null, message.getBytes("UTF-8"));
                System.out.println(" [x] Sent '" + severity + "':'" + message + "'");
            }
            channel.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    class Msg{
        private String target;
        private String message;
        public Msg(String target,String message){
            this.target=target;
            this.message=message;
        }
        public String getTarget() {
            return target;
        }
        public void setTarget(String target) {
            this.target = target;
        }
        public String getMessage() {
            return message;
        }
        public void setMessage(String message) {
            this.message = message;
        }
    }

    public static void main(String[] args){
        MsgSender msgSender=new MsgSender("鄂A99899");
        msgSender.generateMsg();
        msgSender.send();
    }

}
