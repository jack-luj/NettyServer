package com.RabbitMQ;

/**
 * Created by jack lu on 2016/1/29.
 */
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

public class ReceiveClient {
    public static void main(String[] args){
        ReceiveClient r=new ReceiveClient();
        r.receiveMsg();

    }


    /**
     * MQ订阅并接收消息
     */
    public void receiveMsg() {
        try {
            final String EXCHANGE_NAME = "amq.direct";
            ConnectionFactory factory = new ConnectionFactory();
            factory.setUsername("jackl");
            factory.setPassword("123456");
            factory.setHost("127.0.0.1");
            factory.setPort(5672);
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            String queueName = "queueA";
            String target="000";
            channel.queueBind(queueName, EXCHANGE_NAME, target);// 绑定token
            System.out.println("Waiting for messages send to :" + queueName);

            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                                           byte[] body) throws IOException {
                    String message = new String(body, "UTF-8");
                    System.out.println(" [x] Received '" + envelope.getRoutingKey() + "':'" + message + "'");
                }
            };
           channel.basicConsume(queueName, true, consumer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
