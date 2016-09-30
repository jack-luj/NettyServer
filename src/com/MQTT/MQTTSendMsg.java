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
        final  String subscribeTopic= "tricheerTest/d/lv8919" ;
        /**
         * MQTT��ClientID��һ������������ɣ�ProducerID@@@DeviceID
         * ����ProducerID��MQ����̨������
         * DeviceID��Ӧ�÷����ã��������豸��ŵȣ���ҪΨһ���������˾ܾ��ظ���ClientID����
         */
        final String clientId ="PID_csad@@@123456";//PID_csad@@@123456
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
                     *��Ϣ���͵�ĳ������Topic�����ж������Topic���豸�����յ������Ϣ��
                     * ��ѭMQTT�ķ������Ĺ淶��TopicҲ�����Ƕ༶Topic���˴������˷��͵�����topic
                     */
                    sampleClient.publish(topic, message);
                    /**
                     * �������P2P��Ϣ������Topic�����ǡ�p2p��,����topic��Ŀ���ClientID
                     * �˴����õ�����topic��Ҫ�ǽ��շ���ClientID
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