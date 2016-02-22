package com.study;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.Date;

/**
 * Created by jack lu on 2016/2/22.
 */
public class ScrollBarDemobak extends Frame {
    Panel textPanel;
    ScrollPane scrollPane;
    Scrollbar scrollBar = new Scrollbar();


    public ScrollBarDemobak() {
        super();
        setTitle("ScrollBar Demo");
        setSize(800, 600);
        setLocation(100, 100);

       // textArea.setFont(new Font("", Font.PLAIN, 18));

        //scrollBar.setOrientation(Scrollbar.HORIZONTAL);
        //scrollBar.setSize(10, 10);

        ScrollPane scrollPane = new ScrollPane();
     //   scrollPane.add(scrollBar);


        //scrollPane.setWheelScrollingEnabled(false);


        this.add(new DrawPanel());




        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent arg0) {
                System.exit(0);
            }
        });

       // textArea.insert("测试插入",this.getHeight()-50);
      //  textArea.set
    }


    public void paint(Graphics g){

        System.out.println(new Date().toLocaleString() + " 重绘..." + this.getWidth() + ":" + this.getHeight());
        //  textArea.insert("测试插入",this.getHeight()-100);
    }

    public static String buildTxt(int lineLength){
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<lineLength;i++){
            sb.append(new Date().toLocaleString()).append("-").append(i+1).append("\r\n");
        }
        return sb.toString();
    }

    public static void main(String[] args){
        ScrollBarDemobak sd=new ScrollBarDemobak();
        sd.show();
    }

    class DrawPanel extends Panel {

        public void paintComponent(Graphics g) {
            super.paint(g);

            Graphics2D g2 = (Graphics2D) g;

            // 画矩形

            double leftX = 100;

            double topY = 100;

            double width = 200;

            double height = 150;

            Rectangle2D rect = new Rectangle2D.Double(leftX, topY, width, height);

            g2.draw(rect);

            // 画rect的内切椭圆

            Ellipse2D ellipse = new Ellipse2D.Double();

            ellipse.setFrame(rect);

            g2.draw(ellipse);

            // 画一条对角线

            g2.draw(new Line2D.Double(leftX, topY, leftX + width, topY + height));

            // 画一个同心的圆

            double centerX = rect.getCenterX();

            double centerY = rect.getCenterY();

            double radius = 150;

            Ellipse2D circle = new Ellipse2D.Double();

            circle.setFrameFromCenter(centerX, centerY, centerX + radius, centerY

                    + radius);

            g2.draw(circle);

        }

    }

}
