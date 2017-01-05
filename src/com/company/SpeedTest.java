package com.company;

import java.util.Random;

/**
 * Created by jackl on 2016/12/26.
 */
public class SpeedTest {

    public static void main(String[] args){
        System.out.println();
        float[] data=new float[20];
        Random rand = new Random();

        for (int i = 0; i <data.length ; i++) {
            data[i] = rand.nextFloat()*130-65;
            System.out.print(data[i]+" ");
        }
        System.out.println("");
        SpeedTest ss=new SpeedTest();
        System.out.println("急加速次数："+ss.calcSpeed( data,1));
        System.out.println("急减速次数：" + ss.calcSpeed(data, 2));
        System.out.println("急转弯次数："+ss.calcSpeed( data,3));
    }


    /**
     * 计算急加速 急减速 急转弯值
     * @param vals
     * @param mode 1判断急加速（+）  2判断急减速（-）  3判断急转弯（+ -）
     * @return
     */
    public int calcSpeed(float[] vals,int mode){
        int count=0;
        float baseValA=9.8f*3;
        float baseValB=-9.8f*3;
        if(vals!=null) {
            for (int i = 0; i <vals.length; i++) {
                if(vals[i]==66.07){//无效值0xffff*0.02-65
                    continue;
                }
                if(mode==1){
                    if(vals[i]>=baseValA){
                        count++;
                    }
                }else  if(mode==2){
                    if(vals[i]<=baseValB){
                        count++;
                    }
                }else  if(mode==3){
                    if(vals[i]>=baseValA||vals[i]<=baseValB){
                        count++;
                    }
                }

            }
        }
        return count;
    }
}
