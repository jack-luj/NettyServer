package com.study.mouse;

import javax.swing.*;
import java.awt.*;

import java.awt.event.*;
import java.util.ArrayList;


/**
 * Created by jack lu on 2016/3/29.
 */
public class MouseDemo extends JFrame {
    private Color bColor=Color.black;//统一背景色
    private Color fColor=Color.green;//前景色
    public static final int DEFAULT_WIDTH = 800;
    public static final int DEFAULT_HEIGHT = 600;
    DrawPanel panel = new DrawPanel();
    private int offsetX=-13;
    private int offsetY=-43;

    int lastX=offsetX;
    int lastY=offsetY;
    public static void main(String[] args) {
        MouseDemo frame = new MouseDemo();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    public MouseDemo(){
        setTitle("MouseDemo");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        Container contentPane = getContentPane();
        panel.setBackground(bColor);
        contentPane.add(panel);

            this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                int z = e.getButton();
                int count = e.getClickCount();
                panel.startX = 10;
                panel.startY = 15;
                panel.txt = "lastX:" + lastX + " lastY:" + lastY + "   X:" + x + " Y:" + y;
                if (z == 1) {//左键单击
                    panel.addPoint(x + offsetX, y + offsetY);
                } else if (z == 3) {//右键单击
                    int addX = x - lastX;
                    int addY = y - lastY;
                    lastX = x;
                    lastY = y;
                    panel.refreshPoints(addX, addY);
                  /*  for(int k=0;k<20;k++){
                        panel.refreshPoints(addX/20,addY);
                        panel.repaint();
                        try{
                            Thread.sleep(5);
                        }catch (InterruptedException eee){eee.printStackTrace();}}*/
                }
                if (z == 2) {//中键单击
                    panel.pointList.remove(panel.pointList.size() - 1);
                }
                panel.repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }
    class DrawPanel extends JPanel{
        private String txt="hello";
        private int fontSize=16;
        private int startX=0;
        private int startY=0;
        private int r=7;
        private java.util.List<Point> pointList=new ArrayList<Point>();

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(fColor);
            g.setFont(new Font("", Font.ROMAN_BASELINE, fontSize));
            g.drawString(txt, startX, startY);
            paintPoint(g);
            paintLine(g);

        }

        public void addPoint(int x,int y){
            pointList.add(new Point(x, y));
            JLabel jLabel=new JLabel("o");
            jLabel.setLocation(100, 100);
            jLabel.setSize(50, 30);
            jLabel.setVisible(true);
            this.add(jLabel);
        }
        public void remove(){

        }


        public void paintPoint(Graphics g) {

            g.setColor(fColor);
            g.setFont(new Font("", Font.ROMAN_BASELINE, fontSize));
            for(int i=0;i<pointList.size();i++){
                Point p=pointList.get(i);
                g.drawOval((int)p.getX(),(int)p.getY(),2*r,2*r);
            }
        }

        public void paintLine(Graphics g) {
            g.setColor(fColor);
            g.setFont(new Font("", Font.ROMAN_BASELINE, fontSize));
            if(pointList.size()>1){

                for(int i=0;i<pointList.size()-1;i++){
                    for(int j=i;j<pointList.size();j++){
                        Point p1=pointList.get(i);
                        Point p2=pointList.get(j);

                        g.drawLine((int)p1.getX()+r,(int)p1.getY()+r,(int)p2.getX()+r,(int)p2.getY()+r);
                    }

                }
            }
        }
        public void refreshPoints(int addX,int addY){
            java.util.List<Point> newList=new ArrayList<Point>();
            for(int i=0;i<pointList.size();i++){
                Point p=pointList.get(i);
                p.setLocation(p.getX() + addX, p.getY() + addY);
                p.setText("" + i);
                p.setVisible(true);
                newList.add(p);


            }
            pointList.clear();
            pointList=newList;
        }

        public void paint(Graphics g) {
            paintComponent(g);
        }

    }
    class Point extends JLabel{
        private int x;
        private int y;
        public Point(int x,int y){
            this.x=x;
            this.y=y;
        }

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
