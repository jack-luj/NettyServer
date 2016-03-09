package com.study;

/**
 * Created by Administrator on 2016/3/9.
 */
public class Pentagram {
    private int startX,startY;
    private double rotate;
    private int x1,x2,x3,x4,x5;
    private int y1,y2,y3,y4,y5;
    private int r;
    public Pentagram(int startX, int startY, int r, double rotate){
        this.startX=startX;
        this.startY=startY;
        this.r=r;
        this.rotate=rotate;
    }
    private void math() {
        x1=r+startX;
        y1=0+startY;
        x2=(int)(Math.sin(angleToRadian(54))*Math.sin(angleToRadian(36))*2*r)+r+startX;
        y2=(int)(Math.cos(angleToRadian(54))*Math.sin(angleToRadian(36))*2*r)+startY;
        x3=(int)((int)(Math.sin(angleToRadian(72))*2*r)*Math.sin(angleToRadian(18)))+r+startX;
        y3=(int)(Math.cos(angleToRadian(18))*(int)(Math.sin(angleToRadian(72))*2*r))+startY;
        x4=r-(int)((int)(Math.sin(angleToRadian(72))*2*r)*Math.sin(angleToRadian(18)))+startX;
        y4=(int)(Math.cos(angleToRadian(18))*(int)(Math.sin(angleToRadian(72))*2*r))+startY;
        x5=r-(int)(Math.sin(angleToRadian(54))*Math.sin(angleToRadian(36))*2*r)+startX;
        y5=(int)(Math.cos(angleToRadian(54))*Math.sin(angleToRadian(36))*2*r)+startY;
    }
    double angleToRadian(double angle){
        //½Ç¶È×ª»¡¶È
        return Math.PI*angle/180;
    }
}
