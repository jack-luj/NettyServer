package com.RabbitMQ;

/**
 * Created by jackl on 2016/8/26.
 */
import com.rabbitmq.client.*;
public class SendMessage {

    private static final String EXCHANGE_NAME = "direct_logs";

    public static void main(String[] argv) {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.exchangeDeclare(EXCHANGE_NAME, "direct");

            String severity = "0";
            String message = "发给管理员的消息";//
            channel.basicPublish(EXCHANGE_NAME, severity, null, message.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + severity + "':'" + message + "'");

    String severity2 = "1";
            String message2 = "车牌:鄂A23221:蓝色:新客户|将要续保:保养工时费6折";//

            channel.basicPublish(EXCHANGE_NAME, severity2, null, message2.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + severity2 + "':'" + message2 + "'");

            channel.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        //..
    }
}
