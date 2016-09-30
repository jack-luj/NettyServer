package com.MQTT;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;


public class TestAliMqtt {

    private static String hostName="tcp://mqtt-test.cn-qingdao.aliyuncs.com:1883";//"tcp://iot.eclipse.org:1883";
    //private static String username= uuid ;
   // private static String password= token ;
    private static String subscribeTopic= "tricheerTest" ;
    private static String publishTopic= "tricheerTest" ;
    private static String sendMsg="message come from jackl";
    private static String clientId     = "GroupID@@@123456";

    private static String publishClient="PID_csad@@@123456";
    private static String subscribeClient="CID_davjk@@@123456";

    private static String username     = "LTAIVMjladPyyNOv";//access key
    private static String password     = "123456";//PmrbD9kYRp9XpoCFjucFjcC498Wlgm
    private static MqttClient client ;
    private static MemoryPersistence persistence;
    public static void main(String[] args) {
        //订阅消息的方法
        subscribe();
        //发布消息的类
      publish();
    }
    public static String subscribe() {
        try {
            //创建MqttClient
            client=new MqttClient(hostName,clientId);
            persistence = new MemoryPersistence();
            client.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable throwable) {
                }

                @Override
                public void messageArrived(String s, MqttMessage message) throws Exception {
                    try {
                        System.out.println(s + "从服务器收到的消息为:" + message.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
                }
            });
            MqttConnectOptions conOptions = new MqttConnectOptions();
           conOptions.setUserName(username);
           conOptions.setPassword(password.toCharArray());
           conOptions.setCleanSession(true);
           conOptions.setWill(subscribeTopic, "will msg".getBytes(), 1, true);


            client.connect(conOptions);
            client.subscribe(subscribeTopic, 1);
            boolean isSuccess =client.isConnected();
            System.out.println("连接状态:" + isSuccess);
            //client.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            return "failed";
        }
        return "success";
    }

    public static void publish(){
        try {
            MqttTopic topic = client.getTopic(publishTopic);
            MessageObject messageObject=new MessageObject(1,7654321,"/d/lv8918/123456",5,2,"OK","");
            //System.out.println(messageObject.toJson());
            sendMsg=messageObject.toJson();
            System.out.println("发送的消息内容为:" + sendMsg);
            MqttMessage message = new MqttMessage(sendMsg.getBytes());
            message.setQos(1);
            while(true){
                MqttDeliveryToken token = topic.publish(message);
                while (!token.isComplete()){
                    token.waitForCompletion(5000);
                }
           }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}