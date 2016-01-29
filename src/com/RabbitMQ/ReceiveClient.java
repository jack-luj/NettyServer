package com.RabbitMQ;

/**
 * Created by jack lu on 2016/1/29.
 */
import com.rabbitmq.client.*;

import java.io.IOException;

public class ReceiveClient {
    public static void main(String[] args){

        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("jackl");
        factory.setPassword("123456");
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        try{
            Connection conn = factory.newConnection();
            Channel channel = conn.createChannel();
            String queueName="";
            boolean autoAck = false;
            channel.basicConsume(queueName, autoAck, "myConsumerTag",
                    new DefaultConsumer(channel) {
                        @Override
                        public void handleDelivery(String consumerTag,
                                                   Envelope envelope,
                                                   AMQP.BasicProperties properties,
                                                   byte[] body)
                                throws IOException
                        {
                            String routingKey  ="jack";
                            String contentType = properties.getContentType();
                            long deliveryTag = envelope.getDeliveryTag();
                            // (process the message components here ...)
                            channel.basicAck(deliveryTag, false);
                        }
                    });
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
