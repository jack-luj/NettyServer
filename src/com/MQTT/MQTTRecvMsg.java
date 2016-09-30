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
         * ����MQTT�Ľ���㣬�����Ӧ�����ڻ���ѡ����ʵ�region����֧�ֿ�Region����
         */
        final String broker ="tcp://mqtt-test.cn-qingdao.aliyuncs.com:1883";
        /**
         * ���ð����Ƶ�AccessKey�����ڼ�Ȩ
         */
        final String acessKey ="LTAIVMjladPyyNOv";
        /**
         * ���ð����Ƶ�SecretKey�����ڼ�Ȩ
         */
        final String secretKey ="PmrbD9kYRp9XpoCFjucFjcC498Wlgm";
        /**
         * ����Ϣʹ�õ�һ��Topic����Ҫ����MQ����̨������
         */
        final String topic ="tricheerTest/d/lv8918";
        /**
         * MQTT��ClientID��һ����2������ɣ�ConsumerID@@@DeviceID
         * ����ConsumerID��MQ����̨������
         * DeviceID��Ӧ�÷����ã��������豸��ŵȣ���ҪΨһ���������˾ܾ��ظ���ClientID����
         */
        final String clientId ="CID_davjk@@@123456";
        String sign;
        MemoryPersistence persistence = new MemoryPersistence();
        try {
            final MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
            final MqttConnectOptions connOpts = new MqttConnectOptions();
            System.out.println("Connecting to broker: " + broker);
            /**
             * ����ǩ������ǩ����ΪMQTT��password��
             * ǩ���ļ��㷽�����ο�������MacSignature����һ��������ClientID��ǰ�벿�֣���Producer ID����Consumer ID
             * �ڶ������������Ƶ�SecretKey
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
             * ���ö��ķ����ĵ�Topic���ϣ��˴���ѭMQTT�Ķ��Ĺ��򣬿�����һ��Topic������Topic������P2P��Ϣ��
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