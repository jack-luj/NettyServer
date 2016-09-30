package com.MQTT;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.io.IOException;

/**
 * Created by jackl on 2016/9/30.
 */
public class MQTTRecvMsg {
    public static void main(String[] args) throws IOException {
        /**
         * 设置MQTT的接入点，请根据应用所在环境选择合适的region，不支持跨Region访问
         */
        final String broker ="tcp://mqtt-test.cn-qingdao.aliyuncs.com:1883";
        /**
         * 设置阿里云的AccessKey，用于鉴权
         */
        final String acessKey ="LTAIVMjladPyyNOv";
        /**
         * 设置阿里云的SecretKey，用于鉴权
         */
        final String secretKey ="PmrbD9kYRp9XpoCFjucFjcC498Wlgm";
        /**
         * 发消息使用的一级Topic，需要先在MQ控制台里申请
         */
        final String topic ="tricheerTest/d/lv8918";
        /**
         * MQTT的ClientID，一般由2部分组成，ConsumerID@@@DeviceID
         * 其中ConsumerID在MQ控制台里申请
         * DeviceID由应用方设置，可能是设备编号等，需要唯一，否则服务端拒绝重复的ClientID连接
         */
        final String clientId ="CID_davjk@@@123456";
        String sign;
        MemoryPersistence persistence = new MemoryPersistence();
        try {
            final MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
            final MqttConnectOptions connOpts = new MqttConnectOptions();
            System.out.println("Connecting to broker: " + broker);
            /**
             * 计算签名，将签名作为MQTT的password。
             * 签名的计算方法，参考工具类MacSignature，第一个参数是ClientID的前半部分，即Producer ID或者Consumer ID
             * 第二个参数阿里云的SecretKey
             */
            sign = MacSignature.macSignature(clientId.split("@@@")[0], secretKey);
            connOpts.setUserName(acessKey);
            connOpts.setServerURIs(new String[] { broker });
            connOpts.setPassword(sign.toCharArray());
            connOpts.setCleanSession(false);
            connOpts.setKeepAliveInterval(100);
            sampleClient.setCallback(new MqttCallback() {
                public void connectionLost(Throwable throwable) {
                    System.out.println("mqtt connection lost");
                    throwable.printStackTrace();
                    while(!sampleClient.isConnected()){
                        try {
                            sampleClient.connect(connOpts);
                            sampleClient.subscribe(topic,1);
                        } catch (MqttException e) {
                            e.printStackTrace();
                        }
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
                    System.out.println("messageArrived:" + topic + "------" + new String(mqttMessage.getPayload()));
                }
                public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
                    System.out.println("deliveryComplete:" + iMqttDeliveryToken.getMessageId());
                }
            });
            sampleClient.connect(connOpts);
            /**
             * 设置订阅方订阅的Topic集合，此处遵循MQTT的订阅规则，可以是一级Topic，二级Topic或者是P2P消息，
             */
            final String p2ptopic = topic+"/p2p/";
            final String[] topicFilters=new String[]{topic+"/notice/",p2ptopic};

            sampleClient.subscribe(topic);
           // Thread.sleep(Integer.MAX_VALUE);
        } catch (Exception me) {
            me.printStackTrace();
        }
    }
}