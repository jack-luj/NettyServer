package com.RabbitMQ;

/**
 * Created by jack lu on 2016/1/29.
 */
import com.rabbitmq.client.*;

public class PublishClient {
    public static void main(String[] args){
        PublishClient p=new PublishClient();
        p.send("haha");

    }

    private static final String EXCHANGE_NAME = "amq.direct";
    private static final String HOST_NAME = "localhost";
    private void send(String message){
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(HOST_NAME);
            factory.setUsername("jackl");
            factory.setPassword("123456");
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
           // channel.exchangeDeclare(EXCHANGE_NAME, "direct");

            String target="000";
            String queueName = "queueA";

            channel.basicPublish(EXCHANGE_NAME, queueName, null, message.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + queueName + "':'" + message + "'");

            channel.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
