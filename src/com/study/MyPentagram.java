package com.study;

import javax.swing.*;
import java.awt.*;

/**
 * Created by jack lu on 2016/3/8.
 */
public class MyPentagram extends JPanel {

    private static final long serialVersionUID = 1L;

    private JFrame frame = null;

    private int r =150; // 外顶点外接圆半径

    private int startX;
    private int startY;
    private double rotate;
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

    private Point pa;
    private Point pb;
    private Point pc;
    private Point pd;
    private Point pe;


    public MyPentagram(int startX, int startY) {
        this.startX=startX;
        this.startY=startY;
      //  this.math();
        frame = new JFrame("五角星");
        frame.getContentPane().add(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,300);
        frame.setVisible(true);
    }

    private void math() {
        int c = 360 / 5; // 弧对应角度 1等分
        pa=new Point(r+startX,0+startY);
        pb=new Point((int)(Math.sin(angleToRadian(54))*Math.sin(angleToRadian(36))*2*r)+r+startX,(int)(Math.cos(angleToRadian(54))*Math.sin(angleToRadian(36))*2*r)+startY);
        pc=new Point((int)((int)(Math.sin(angleToRadian(c))*2*r)*Math.sin(angleToRadian(18)))+r+startX,(int)(Math.cos(angleToRadian(18))*(int)(Math.sin(angleToRadian(c))*2*r))+startY);
        pd=new Point(r-(int)((int)(Math.sin(angleToRadian(c))*2*r)*Math.sin(angleToRadian(18)))+startX,(int)(Math.cos(angleToRadian(18))*(int)(Math.sin(angleToRadian(c))*2*r))+startY);
        pe=new Point(r-(int)(Math.sin(angleToRadian(54))*Math.sin(angleToRadian(36))*2*r)+startX,(int)(Math.cos(angleToRadian(54))*Math.sin(angleToRadian(36))*2*r)+startY);
    }
    double angleToRadian(double angle){
        //角度转弧度
        return Math.PI*angle/180;
    }

    public void paint(Graphics g) {

        startX=5;
        startY=5;
        r=(Math.min(this.getWidth(),this.getHeight())-10)/2;
        Graphics2D g2=(Graphics2D) g;
        rotate=rotate+0.1;
        g2.rotate(rotate,startX+r*1.0,(startY+r)*1.0);
        math();
        super.paint(g);
        g.setColor(Color.RED);


        g.drawOval(startX, startY, 2 * r, 2 * r);
        g.setColor(Color.RED);
        g.drawLine(pa.getX(), pa.getY(), pc.getX(), pc.getY());
        g.drawLine(pb.getX(), pb.getY(), pd.getX(), pd.getY());
        g.drawLine(pc.getX(), pc.getY(), pe.getX(), pe.getY());
        g.drawLine(pd.getX(), pd.getY(), pa.getX(), pa.getY());
        g.drawLine(pe.getX(), pe.getY(), pb.getX(), pb.getY());

        }
    public void repaint(int value) {
        System.out.println(">>repaint");
        super.repaint();
    }
    public static void main(String[] args) {
        new MyPentagram(100,100);
    }

    class Point{
        public Point(int x,int y){
            this.x=x;
            this.y=y;
        }

        int x;
        int y;

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }
}
