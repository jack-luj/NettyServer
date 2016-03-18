package com.study.keyboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;

/**
 * Created by jack lu on 2016/3/18.
 */
public class KeyboardDemo extends JFrame {
    public static void main(String[] args) {
        KeyboardDemo frame = new KeyboardDemo();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }


    public KeyboardDemo() {
        Color bColor=Color.black;
        Color fColor=Color.green;

        setTitle("KeyboardDemo");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        // 将 panel 加到 frame

        JTextField  jTextField=new JTextField(30);
        jTextField.setBackground(bColor);
        jTextField.setForeground(fColor);
        jTextField.setSize(200,30);
        JLabel  jLabel=new JLabel("");

        jLabel.setForeground(fColor);
        jTextField.setSize(200,30);

        DrawPanel panel = new DrawPanel();
        Container contentPane = getContentPane();

        Container buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(jTextField);
        buttonPanel.add(jLabel);


        buttonPanel.setBackground(bColor);
        contentPane.add(buttonPanel, BorderLayout.NORTH);
        panel.setBackground(bColor);
        contentPane.add(panel, BorderLayout.CENTER);

        jTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                String txt=jTextField.getText();
                jLabel.setText(txt);
                panel.txt=txt;
               panel.repaint();
            }
        });

    }

    public static final int DEFAULT_WIDTH = 800;
    public static final int DEFAULT_HEIGHT = 600;


    class DrawPanel extends JPanel {
        private String txt="";
        private int sleep=1000;
        public void paintComponent(Graphics g,double leftX,  double topY ) {
            super.paintComponent(g);
            g.setColor(Color.green);
            g.setFont(new Font("", Font.ROMAN_BASELINE, 16));
            g.drawString("这是最后一行文本 当前字体行高:" + g.getFontMetrics().getHeight() + "  窗体宽度:" + this.getWidth(), 0, this.getHeight() - 5);
            for(int i=0;i<15;i++){
               drawTxtArray(txt, g, Tools.getNoBetween(10,this.getWidth()/10)*10-100, Tools.getNoBetween(5,60)*10);
           }
        }

        // 字符串切割,实现字符串自动换行
        /**
         * 写字符串到指定起始坐标
         * @param text 文本
         * @param g Graphics
         * @param x 起始坐标x
         * @param y 起始坐标y
         */
        public void drawTxtArray(String text,Graphics g,int x,int y){
            char[] chars=text.toCharArray();
            for(int i=0;i<chars.length;i++){
            if(y+g.getFontMetrics().getHeight()*i<=this.getHeight()-g.getFontMetrics().getHeight()){
                    g.drawString(String.valueOf(chars[i]), x, y + g.getFontMetrics().getHeight()*i);
           }
        }
        }

        /**
         * 自动调用重绘方法
         * @param g Graphics
         */
        public void paint(Graphics g) {
            System.out.println(new Date().toLocaleString() + " 重绘..." + this.getWidth() + ":" + this.getHeight() );
            paintComponent(g, 0, 10);
        }


    }}

