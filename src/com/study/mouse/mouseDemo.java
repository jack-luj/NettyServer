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
            this.add(panel);

        this.addWindowStateListener(new WindowStateListener() {//最大化时重新初始化数组
            public void windowStateChanged(WindowEvent e) {
                System.out.println("state changed");
                panel.repaint();
            }
        });
            this.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int x = e.getX();
                    int y = e.getY();
                    int z = e.getButton();
                    int count = e.getClickCount();
                    // panel.startX = 10;
                    //  panel.startY = 15;
                    //   panel.txt = "click X:" + x + " Y:" + y;
                    if (z == 1) {//左键单击
                        panel.addPoint(x + offsetX, y + offsetY);

                    } else if (z == 3) {//右键单击
                        Label center = panel.getCenter();
                        int addX = x - center.getX();
                        int addY = y - center.getY();
                        panel.refreshPoints(addX, addY);

                    }
                    if (z == 2 && count == 1) {//中键单击
                        if (panel.pointList.size() > 0) {
                            panel.pointList.remove(panel.pointList.size() - 1);
                        }

                    }
                    if (z == 2 && count == 2) {//中键双击
                        if (panel.pointList.size() > 0) {

                            Label l = panel.pointList.get(0);
                            l.setLocation(l.getX() + Tools.getNoBetween(10, 30), l.getY() + Tools.getNoBetween(10, 20));
                        }
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
        private int r=5;
        private java.util.List<Label> pointList=new ArrayList<Label>();
        Container contentPane = getContentPane();
        public DrawPanel(){

        }
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(fColor);
            g.setFont(new Font("", Font.ROMAN_BASELINE, fontSize));
            g.drawString(txt, startX, startY);
            paintPoint(g);
            paintLine(g);
        }

        /**
         * 计算中心点
         * @return
         */
        public Label getCenter(){
            Label center=new Label();
            center.setLocation(0, 0);
            int xTotal=0;
            int yTotal=0;
            for (int i = 0; i < pointList.size(); i++) {
                Label p=pointList.get(i);
                xTotal=xTotal+p.getX();
                yTotal=yTotal+p.getY();
            }
            if(pointList.size()>0){
                center=new Label();
                center.setLocation(xTotal/pointList.size(),yTotal/pointList.size());
            }
            return center;
        }

        /**
         * 增加一个点
         * @param x
         * @param y
         */
        public void addPoint(int x,int y){
            Label p=new Label();
            p.setLocation(x,y);
            p.setText("O");
            p.setAlignment(Label.CENTER);
            p.setSize(5, 5);

            p.setForeground(Color.red);
            contentPane.add(p);

            p.addMouseMotionListener(new MouseMotionListener() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    System.out.println(">>>>>>>>>>>>>>>>.");
                    p.setLocation(e.getX(), e.getY());
                    repaint();
                }

                @Override
                public void mouseMoved(MouseEvent e) {
                }
            });
            pointList.add(p);
        }


        public void paintPoint(Graphics g) {
            g.setColor(fColor);
            g.setFont(new Font("", Font.ROMAN_BASELINE, fontSize));
            for(int i=0;i<pointList.size();i++){
                Label p=pointList.get(i);
                g.drawOval((int)p.getX(),(int)p.getY(),2*r,2*r);
            }
        }

        public void paintLine(Graphics g) {
            g.setColor(fColor);
            g.setFont(new Font("", Font.ROMAN_BASELINE, fontSize));
            if(pointList.size()>1){
                for(int i=0;i<pointList.size()-1;i++){
                    for(int j=i;j<pointList.size();j++){
                        Label p1=pointList.get(i);
                        Label p2=pointList.get(j);
                        g.drawLine((int)p1.getX()+r,(int)p1.getY()+r,(int)p2.getX()+r,(int)p2.getY()+r);
                    }
                }
            }
        }
        public void refreshPoints(int addX,int addY){
            java.util.List<Label> newList=new ArrayList<Label>();
            for(int i=0;i<pointList.size();i++){
                Label p=pointList.get(i);
                p.setLocation(p.getX() + addX, p.getY() + addY);
                newList.add(p);
            }
            pointList.clear();
            pointList=newList;
        }

        public void paint(Graphics g) {
            paintComponent(g);
        }

    }

}
