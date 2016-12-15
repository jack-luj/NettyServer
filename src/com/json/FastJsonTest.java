package com.json;

import com.alibaba.fastjson.JSON;

/**
 * Created by jackl on 2016/11/14.
 */
public class FastJsonTest {

    public static void main(String[] args){


        String[] jsons=new String[7];
        String[] desc=new String[7];
        jsons[0]="{\"head\":{\"version\":1,\"id\":1,\"from\":\"\\/d\\/lv8918\\/868516020035370\",\"code\":1,\"type\":1,\"msg\":\"\"},\"body\":{\"imei\":\"868516020035370\"}}";
        desc[0]="1-req";

        jsons[1]="{\"head\":{\"version\":1,\"id\":1478769939902,\"from\":\"\\/d\\/lv8918\\/868516020035370\",\"code\":101,\"type\":2,\"msg\":\"\"},\"body\":{\"model\":\"lv8918\",\"odmModel\":\"M8 Plus\",\"brandName\":\"Tricheer\",\"imei\":\"868516020035370\",\"imsi\":\"460063502074381\",\"hwver\":\"LLAM089A\",\"swver\":\"LLA0031_2141_0.0.5\",\"odmSwver\":\"LLA0031.1.1_M089\",\"wifimac\":\"00:11:22:33:44:55\",\"btmac\":\"00:23:B1:76:21:72\",\"vendor\":\"Tricheer\",\"localDate\":\"2016-11-10 17:25:40\",\"timezone\":\"GMT+08:00\",\"project\":\"LCT_LV8918_ZDK\"}}";
        desc[1]="101-req";

        jsons[2]="{\"head\":{\"version\":1,\"id\":\"1478769939902\",\"from\":\"/s/lv8918\",\"code\":101,\"type\":1,\"msg\":\"\"},\"body\":{}}";
        desc[2]="101-res";

        jsons[3]="{\"head\":{\"version\":1,\"id\":\"1478770076724\",\"from\":\"/s/lv8918\",\"code\":103,\"type\":1,\"msg\":\"\"},\"body\":{}}";
        desc[3]="103-req";

        jsons[4]="{\"head\":{\"version\":1,\"id\":1478770076724,\"from\":\"\\/d\\/lv8918\\/868516020035370\",\"code\":103,\"type\":2,\"msg\":\"\"},\"body\":{\"imei\":\"868516020035370\",\"longitude\":0,\"latitude\":0}}";
        desc[4]="103-res";


        jsons[5]="{\"head\":{\"version\":1,\"id\":\"1477643470974\",\"from\":\"/s/lv8918\",\"code\":106,\"type\":1,\"msg\":\"\"},\"body\":{\"operation\":\"NAVIGATE\",\"param\":{\"dest\":\"114.360734, 30.541093\"}}}";
        desc[5]="106-req";

        jsons[6]="{\"head\":{\"version\":1,\"id\":1477643470974,\"from\":\"\\/d\\/lv8918\\/868516020035370\",\"code\":106,\"type\":2,\"msg\":\"\"},\"body\":{\"operate\":\"NAVIGATE\",\"result\":\"OK\"}}";
        desc[6]="106-res";

        //String jsonString = JSON.toJSONString(user);
        //String jsonString="{\"head\":{\"version\":1,\"id\":\"1478770076724\",\"from\":\"/s/lv8918\",\"code\":103,\"type\":1,\"msg\":\"\"},\"body\":{}}";
      //  String jsonString="{\"head\":{\"version\":1,\"id\":1478770076724,\"from\":\"\\/d\\/lv8918\\/868516020035370\",\"code\":103,\"type\":2,\"msg\":\"\"},\"body\":{\"imei\":\"868516020035370\",\"longitude\":0,\"latitude\":0}}";
      //  System.out.println(">>"+jsonString);
      //  System.out.println("=================================================");
       // MsgBean bean = JSON.parseObject(jsonString, MsgBean.class);
       // String _jsonString = JSON.toJSONString(bean);
       // System.out.println(">>"+_jsonString);


        for (int i = 0; i < jsons.length; i++) {
            System.out.println(desc[i]+">>"+jsons[i]);
            System.out.println("=================================================");
            MsgBean bean = JSON.parseObject(jsons[i], MsgBean.class);
            //bean.getBody(()
            String _jsonString = JSON.toJSONString(bean);
            System.out.println(desc[i]+"=>"+_jsonString+"\n");


        }
    }




}
