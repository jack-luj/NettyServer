package com.MQTT;


/**
 * Created by jackl on 2016/9/26.
 */
public class MessageObject {
    private int version;
    private int id;
    private String from;
    private int code;
    private int type;
    private String msg;
    private String bodyContent;

    private String message="";
    public MessageObject(int version,int id,String from,int code,int type,String msg,String bodyContent){
        message="{head : {version :"+version+",id :"+id+",from :"+from+",code :"+code+",type :"+type+",msg :"+msg+"},body : {"+bodyContent+"}}";
    }
    public String toJson(){
        return message;
    }
    public static void main(String[] args){
        MessageObject messageObject=new MessageObject(1,7654321,"/d/lv8918/123456",5,2,"OK","");
        System.out.println(messageObject.toJson());
    }
}
