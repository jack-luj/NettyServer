package com.study;
import java.awt.*;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
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

class DrawFrame extends JFrame {
    private static String txt=buildTxt(100);
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
                panel.repaint(scrollBar.getValue());
            }
        });

        this.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                scrollBar.setValue(scrollBar.getValue()+e.getWheelRotation());
                panel.repaint(scrollBar.getValue());
            }
        });

        contentPane.add(scrollBar, BorderLayout.EAST);
    }

    public static final int DEFAULT_WIDTH = 800;
    public static final int DEFAULT_HEIGHT = 600;


    class DrawPanel extends JPanel {
        private int scrollBarValue=0;

        public void paintComponent(Graphics g,double leftX,  double topY , double width,  double height) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            // 画矩形

            g.getFontMetrics().getHeight();
            //把字符串toCharArray() 换成char数组，放到方法里获取长度

            //System.out.println("Panel宽度:"+this.getWidth()+"|"+ g.getFontMetrics().getLineMetrics());
            g2.setFont(new Font("", 12, 15));

            String[] str=format(txt,this.getWidth()-6,g);
            drawTxtArray(str,g2,10,(int) topY + 20);

            g2.drawString("这是\r\n最后一行"+this.getWidth(),  10,  this.getHeight()-20>0?this.getHeight()-20:0);
        }

        // 字符串切割,实现字符串自动换行
        public  String[] format(String text, int maxWidth, Graphics g) {
            String[] result = null;
            Vector tempR = new Vector();
            int lines = 0;
            int len = text.length();
            int index0 = 0;
            int index1 = 0;
            boolean wrap;
            while (true) {
                int widthes = 0;
                for (index0 = index1; index1 < len; index1++) {
                    widthes = g.getFontMetrics().charWidth(text.charAt(index1)) + widthes;
                    if (widthes > maxWidth) {
                        break;
                    }
                }
                lines++;
                tempR.addElement(text.substring(index0, index1));
                if (index1 >= len) {
                    break;
                }
            }
            result = new String[lines];
            tempR.copyInto(result);
            return result;
        }

        public void drawTxtArray(String[] texts,Graphics2D g,int x,int y){
            for(int i=0;i<texts.length;i++){
                g.drawString(texts[i], 10, y + 20*i);
            }
        }

        public void paint(Graphics g){
            System.out.println(new Date().toLocaleString() + " 重绘..." + this.getWidth() + ":" + this.getHeight()+" value:"+scrollBarValue);
            paintComponent(g, 10 + (this.getWidth() - this.getWidth()), 10-scrollBarValue*5 + (this.getHeight() - this.getHeight()), 200, 150);
        }

        public void repaint(int value) {
            scrollBarValue=value;
            super.repaint();
        }

    }

    public static String buildTxt(int lineLength){
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<lineLength;i++){
            sb.append(new Date().getTime()).append("-").append("t");
        }
        return sb.toString();
    }
}

