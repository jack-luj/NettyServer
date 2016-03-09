package com.study;

/**
 * Created by Administrator on 2016/3/9.
 */
public class Pentagram {
    private int startX,startY;
    private double rotate;
    private int x1,x2,x3,x4,x5;
    private int y1,y2,y3,y4,y5;

    private int x1_,x2_,x3_,x4_,x5_;
    private int y1_,y2_,y3_,y4_,y5_;

    private int r;
    public Pentagram(int startX, int startY, int r, double rotate){
        this.startX=startX;
        this.startY=startY;
        this.r=r;
        this.rotate=rotate;
        math();
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

        y1_=y2_=y2;


    }
    double angleToRadian(double angle){
        //½Ç¶È×ª»¡¶È
        return Math.PI*angle/180;
    }

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public double getRotate() {
        return rotate;
    }

    public void setRotate(double rotate) {
        this.rotate = rotate;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getX3() {
        return x3;
    }

    public void setX3(int x3) {
        this.x3 = x3;
    }

    public int getX4() {
        return x4;
    }

    public void setX4(int x4) {
        this.x4 = x4;
    }

    public int getX5() {
        return x5;
    }

    public void setX5(int x5) {
        this.x5 = x5;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public int getY3() {
        return y3;
    }

    public void setY3(int y3) {
        this.y3 = y3;
    }

    public int getY4() {
        return y4;
    }

    public void setY4(int y4) {
        this.y4 = y4;
    }

    public int getY5() {
        return y5;
    }

    public void setY5(int y5) {
        this.y5 = y5;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }
}
