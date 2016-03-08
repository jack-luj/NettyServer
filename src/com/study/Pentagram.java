package com.study;

import javax.swing.*;
import java.awt.*;

/**
 * Created by jack lu on 2016/3/8.
 */
public class Pentagram extends JPanel {

    private static final long serialVersionUID = 1L;

    private JFrame frame = null;

    private int r =250; // 外顶点外接圆半径

    private Point pa;
    private Point pb;
    private Point pc;
    private Point pd;
    private Point pe;


    public Pentagram() {
        this.math();
        frame = new JFrame("五角星");
        frame.getContentPane().add(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,600);
        frame.setLocation(200, 200);

        frame.setVisible(true);
    }

    private void math() {
        pa=new Point(r,0);
        pb=new Point(487,172);
        pc=new Point(396,452);
        pd=new Point(103,452);
        pe=new Point(12,172);
    }


    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.RED);


        g.drawOval(0, 0, 2 * r, 2 * r);


        g.setColor(Color.BLACK);
        g.drawLine(pa.getX(), pa.getY(), pc.getX(), pc.getY());
        g.drawLine(pb.getX(), pb.getY(), pd.getX(), pd.getY());
        g.drawLine(pc.getX(), pc.getY(), pe.getX(), pe.getY());
        g.drawLine(pd.getX(), pd.getY(), pa.getX(), pa.getY());
        g.drawLine(pe.getX(), pe.getY(), pb.getX(), pb.getY());





    }

    public static void main(String[] args) {
        new Pentagram();
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
