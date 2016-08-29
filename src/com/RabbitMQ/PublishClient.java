package com.RabbitMQ;

/**
 * Created by jack lu on 2016/1/29.
 */
import com.rabbitmq.client.*;

public class PublishClient {
    public static void main(String[] args){
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("jackl");
        factory.setPassword("123456");
        //factory.setVirtualHost("");
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        factory.setAutomaticRecoveryEnabled(true);
        factory.setNetworkRecoveryInterval(10000);

        try{
            String exchangeName="amq.direct";
            String routingKey="queueAAAA";
            Connection conn = factory.newConnection();
            Channel channel = conn.createChannel();
            channel.exchangeDeclare(exchangeName, "direct", true);
            String queueName ="queueA";
            channel.queueBind(queueName, exchangeName, routingKey);
            byte[] messageBodyBytes = "zzz  Hello, world!".getBytes();
            channel.basicPublish(exchangeName, routingKey, null, messageBodyBytes);

        }catch (Exception e){
        e.printStackTrace();
        }

    }
}
