package com.study;

/**
 * Created by Administrator on 2016/3/5.
 */
import java.awt.*;
import javax.swing.*;

public class WuJiaoXing extends JPanel {

    private static final long serialVersionUID = 1L;

    private JFrame frame = null;

    private int r =250; // 外顶点外接圆半径

    private int[] x = new int[5]; // 5个X外顶点坐标

    private int[] y = new int[5]; // 5个Y外顶点坐标

    private int[] x_ = new int[5]; // 5个X内顶点坐标

    private int[] y_ = new int[5]; // 5个Y内顶点坐标

    public WuJiaoXing() {
        this.math();
        frame = new JFrame("五角星");
        frame.getContentPane().add(this);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,600);
        frame.setLocation(200, 200);

        frame.setVisible(true);
    }

    private void math() {
        int c = 360 / 5; // 角度
        for (int i = 0; i < 5; i++) {
            x[i] = (int) (Math.cos(i * c * Math.PI / 30 - Math.PI / 2) * (r) + r);
            y[i] = (int) (Math.sin(i * c * Math.PI / 30 - Math.PI / 2) * (r) + r);
            System.out.println("角度" +(i * c * Math.PI / 30 - Math.PI / 2)*180/Math.PI+" "+x[i]+","+y[i]);
        }

      //  System.out.println(">>"+Math.sin(Math.PI *30/180));
        int r_ = (int) (r * Math.sin(18 * Math.PI / 180) / Math
                .sin(126 * Math.PI / 180)); // 内顶点外接圆半径
        for (int i = 0; i < 5; i++) {
            x_[i] = (int) (Math.cos((i * c + 18) * Math.PI / 30 - Math.PI / 2)
                    * (r_) + r);
            y_[i] = (int) (Math.sin((i * c + 18) * Math.PI / 30 - Math.PI / 2)
                    * (r_) + r);
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.RED);
        // g.setBackground(Color.RED);
        // 填充
        int[] x1 = { x[0], x[2], x_[2] };
        int[] y1 = { y[0], y[2], y_[2] };
        int[] x2 = { x[1], x[3], x_[3] };
        int[] y2 = { y[1], y[3], y_[3] };
        int[] x3 = { x[2], x[4], x_[4] };
        int[] y3 = { y[2], y[4], y_[4] };
       // g.fillPolygon(x1, y1, 3);
        //g.fillPolygon(x2, y2, 3);
        //g.fillPolygon(x3, y3, 3);
        System.out.println("B点X坐标="+x[1]/2);
        double l=Math.sin(36*Math.PI/180)*2*r;//弧长
        int xx=(int)(Math.sin(54 * Math.PI / 180)*Math.sin(36*Math.PI/180)*2*r);
        System.out.println("计算xx="+xx);
        int yy=(int)(Math.cos(54 * Math.PI / 180)*Math.sin(36*Math.PI/180)*2*r);
        System.out.println("计算yy="+yy);
        //绘圆
        g.setColor(Color.blue);
        g.drawOval(0, 0, 2 * r, 2 * r);
      //  g.drawRect(0, 0, 2 * r, 2 * r);
        g.drawLine(r, 0, r, 2*r);
        g.drawLine(0, r,2*r, r);


        g.drawLine(x[0], y[0], x[1], y[1]);
        g.drawLine(x[1], y[1], x[2], y[2]);
       g.drawLine(x[2], y[2], x[3], y[3]);
       g.drawLine(x[3], y[3], x[4], y[4]);
        g.drawLine(x[4], y[4], x[0], y[0]);

        // 描边
         g.setColor(Color.BLACK);
         g.drawLine(x[0], y[0], x[2], y[2]);
         g.drawLine(x[0], y[0], x[3], y[3]);
         g.drawLine(x[1], y[1], x[3], y[3]);
         g.drawLine(x[1], y[1], x[4], y[4]);
         g.drawLine(x[2], y[2], x[4], y[4]);
         g.drawLine(x[2], y[2], x[0], y[0]);




    }

    public static void main(String[] args) {
        new WuJiaoXing();
    }

    class Point{
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