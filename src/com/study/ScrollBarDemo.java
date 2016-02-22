package com.study;
import java.awt.*;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.Date;
import java.util.Vector;

import javax.swing.*;



public class ScrollBarDemo {


    public static void main(String[] args) {
        DrawFrame frame = new DrawFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.show();
    }

}

/**
 一个frame中包含一个panel用来画图
 */

class DrawFrame extends JFrame {
    private static String txt=buildTxt(3);
    public DrawFrame() {
        setTitle("DrawDemo");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        // 将 panel 加到 frame
        DrawPanel panel = new DrawPanel();
        Container contentPane = getContentPane();


        Scrollbar scrollBar = new Scrollbar();
        scrollBar.setOrientation(Scrollbar.VERTICAL);
        contentPane.add(panel, BorderLayout.CENTER);

        scrollBar.addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                System.out.println(scrollBar.getValue());
                panel.repaint();
            }
        });




        contentPane.add(scrollBar, BorderLayout.EAST);

    }

    public static final int DEFAULT_WIDTH = 800;
    public static final int DEFAULT_HEIGHT = 600;

    /**
     27
     用来显示矩形和椭圆的panel
     28
     */

    class DrawPanel extends JPanel {

        double leftX = 10;
        double topY = 10;
        double width = 200;
        double height = 150;

        public void paintComponent(Graphics g,double leftX,  double topY , double width,  double height) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            // 画矩形

            g.getFontMetrics().getHeight();
            //把字符串toCharArray() 换成char数组，放到方法里获取长度



            //System.out.println("Panel宽度:"+this.getWidth()+"|"+ g.getFontMetrics().getLineMetrics());
            g2.setFont(new Font("",12,15));
            g2.drawString(txt, (int) leftX + 10, (int) topY + 80);

            g2.drawString("这是\r\n最后一行", (int) leftX + 10, (int) topY + 540);
        }


        public void paint(Graphics g){

            System.out.println(new Date().toLocaleString() + " 重绘..." + this.getWidth() + ":" + this.getHeight());
            paintComponent(g, 10 + (this.getWidth() - this.getWidth()), 10 + (this.getHeight() - this.getHeight()), 200, 150);
        }



    }

    public static String buildTxt(int lineLength){
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<lineLength;i++){
            sb.append(new Date().toLocaleString()).append("-").append(i+1).append("\r\n");
        }
        return sb.toString();
    }
}

