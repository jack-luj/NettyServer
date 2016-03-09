package com.study;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

/**
 * Created by jack lu on 2016/3/8.
 */
public class PentagramDemo extends JFrame {

    public static void main(String[] args) {
        PentagramDemo frame = new PentagramDemo();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    public PentagramDemo() {
        setTitle("PentagramDemo");
        setSize(800, 600);
        setBackground(Color.red);
        // 将 panel 加到 frame
        DrawPanel panel = new DrawPanel();
        Container contentPane = getContentPane();
        contentPane.add(panel, BorderLayout.CENTER);

      }


    class DrawPanel extends JPanel {

        public void paintComponent(Graphics g ) {
            this.setBackground(Color.RED);
            super.paintComponent(g);
            Graphics2D g2=(Graphics2D) g;

            Pentagram p1=new Pentagram(60,100,100,0);
            drawingPentagram(p1, g2);
            Pentagram p2=new Pentagram(300,60,40,0.2);
            drawingPentagram(p2, g2);
            Pentagram p3=new Pentagram(350,130,40,0.2);
            drawingPentagram(p3, g2);
            Pentagram p4=new Pentagram(380,210,40,0.2);
            drawingPentagram(p4, g2);
            Pentagram p5=new Pentagram(400,290,40,0.25);
            drawingPentagram(p5, g2);

           // g2.fillOval(100,300,100,100);
     //       g.drawString("这是最后一行文本 当前字体行高:" + g.getFontMetrics().getHeight()+"  窗体宽度:"+this.getWidth(), 0, this.getHeight()-5 );
        }
        public void drawingPentagram(Pentagram p,Graphics2D g){
            g.setColor(Color.yellow);
            g.rotate(p.getRotate(), (p.getStartX() + p.getR()) * 1.0, (p.getStartY() + p.getR()) * 1.0);
            // g.drawOval(p.getStartX(), p.getStartY(), p.getR() * 2, p.getR() * 2);
            g.drawLine(p.getX1(), p.getY1(), p.getX3(), p.getY3());
            g.drawLine(p.getX2(), p.getY2(), p.getX4(), p.getY4());
            g.drawLine(p.getX3(), p.getY3(), p.getX5(), p.getY5());
            g.drawLine(p.getX4(), p.getY4(), p.getX1(), p.getY1());
            g.drawLine(p.getX5(), p.getY5(), p.getX2(), p.getY2());

        }
        public void paint(Graphics g) {
           paintComponent(g );
        }

    }
}
