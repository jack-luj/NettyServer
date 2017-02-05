package com.MQTT;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.io.IOException;
import java.util.Date;

/**
 * Created by jackl on 2016/9/30.
 */
public class MQTTSendMsg {
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
        final  String subscribeTopic= "tricheerTest/d/lv8919" ;
        /**
         * MQTT的ClientID，一般由两部分组成，ProducerID@@@DeviceID
         * 其中ProducerID在MQ控制台里申请
         * DeviceID由应用方设置，可能是设备编号等，需要唯一，否则服务端拒绝重复的ClientID连接
         */
        final String clientId ="PID_csad@@@123456";//PID_csad@@@123456
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
            connOpts.setServerURIs(new String[]{broker});
            connOpts.setPassword(sign.toCharArray());
            System.out.println(acessKey + ":" + sign);
            connOpts.setCleanSession(false);
            connOpts.setKeepAliveInterval(100);
            sampleClient.setCallback(new MqttCallback() {
                public void connectionLost(Throwable throwable) {
                    System.out.println("mqtt connection lost");
                    throwable.printStackTrace();
                    while (!sampleClient.isConnected()) {
                        try {
                            sampleClient.connect(connOpts);
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
                    System.out.println("receive message:" + topic + "------" + new String(mqttMessage.getPayload()));
                }

                public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
                //    System.out.println("deliveryComplete:" + iMqttDeliveryToken.getMessageId());
                }
            });
            sampleClient.connect(connOpts);
           //  sampleClient.subscribe(subscribeTopic);
            for (int i = 0; i < 10; i++) {
                try {
                    String scontent = "now "+new Date().toLocaleString()+i;
                    final MqttMessage message = new MqttMessage(scontent.getBytes());
                    message.setQos(1);
                    System.out.println(i+" pushed at "+new Date()+" "+ scontent);
                    /**
                     *消息发送到某个主题Topic，所有订阅这个Topic的设备都能收到这个消息。
                     * 遵循MQTT的发布订阅规范，Topic也可以是多级Topic。此处设置了发送到二级topic
                     */
                    sampleClient.publish(topic, message);
                    /**
                     * 如果发送P2P消息，二级Topic必须是“p2p”,三级topic是目标的ClientID
                     * 此处设置的三级topic需要是接收方的ClientID
                     */
                /*    String p2pTopic =topic+"/p2p/CID_mqttdelay3@@@DEVICEID_001";
                    sampleClient.publish(p2pTopic,message);*/
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception me) {
            me.printStackTrace();
        }
    }
}