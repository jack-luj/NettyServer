package com.study;

import javax.swing.*;
import java.awt.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;
import java.util.Vector;

/**
 * Created by jack lu on 2016/3/3.
 */
public class DrawingDemo extends JFrame {

    public static void main(String[] args) {
        DrawingDemo frame = new DrawingDemo();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }


    public DrawingDemo() {
        setTitle("DrawingDemo");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        // 将 panel 加到 frame
        JButton jButton1=new JButton("绘圆形");
        jButton1.setSize(200,30);
        JButton jButton2=new JButton("绘矩形");
        jButton2.setSize(200,30);
        JButton jButton3=new JButton("绘椭圆");
        jButton3.setSize(200, 30);


        DrawPanel panel = new DrawPanel();
        Container contentPane = getContentPane();



        Container buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(jButton1);
        buttonPanel.add(jButton2);
        buttonPanel.add(jButton3);
        contentPane.add(buttonPanel,BorderLayout.NORTH);
        contentPane.add(panel,BorderLayout.CENTER);

        jButton1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
            @Override
            public void mousePressed(MouseEvent e) {        }
            @Override
            public void mouseReleased(MouseEvent e) {        }
            @Override
            public void mouseEntered(MouseEvent e) {        }
            @Override
            public void mouseExited(MouseEvent e) {         }
        });

    }

    public static final int DEFAULT_WIDTH = 800;
    public static final int DEFAULT_HEIGHT = 600;


    class DrawPanel extends JPanel {
        private int scrollBarValue=0;

        public void paintComponent(Graphics g,double leftX,  double topY ) {
            super.paintComponent(g);
            g.setFont(new Font("", Font.ROMAN_BASELINE, 16));
            g.drawRect(20, 20, 500, 300);
            g.setColor(new Color(200,150,100));
            g.fill3DRect(20,20,500,300,false);


            g.drawString("这是最后一行文本 当前字体行高:" + g.getFontMetrics().getHeight() + "  窗体宽度:" + this.getWidth(), 0, this.getHeight() - 5);
        }

        // 字符串切割,实现字符串自动换行


        /**
         * 写字符串数组到指定起始坐标
         * @param texts 文本数组
         * @param g Graphics
         * @param x 起始坐标x
         * @param y 起始坐标y
         */
        public void drawTxtArray(String[] texts,Graphics g,int x,int y){
            for(int i=0;i<texts.length;i++){
                if(y+g.getFontMetrics().getHeight()*i<=this.getHeight()-g.getFontMetrics().getHeight()){
                    //确保文本不会绘制到最后一行的保留区域
                    g.drawString(texts[i], 0, y + g.getFontMetrics().getHeight()*i);
                }

            }
        }

        /**
         * 自动调用重绘方法
         * @param g Graphics
         */
        public void paint(Graphics g) {

            System.out.println(new Date().toLocaleString() + " 重绘..." + this.getWidth() + ":" + this.getHeight()+" value:"+scrollBarValue);
            paintComponent(g, 0, 10 - scrollBarValue * 5);
        }





        /**
         * 重载的repaint方法 使用scrollBarValue控制绘制坐标
         * @param value scrollBarValue
         */
        public void repaint(int value) {

            scrollBarValue=value;
            super.repaint();
        }

    }


}
