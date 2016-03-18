package com.study.keyboard;

import javafx.scene.input.KeyCode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;

/**
 * Created by jack lu on 2016/3/18.
 */
public class KeyboardDemo extends JFrame {
    private Color bColor=Color.black;
    private Color fColor=Color.green;

    public static void main(String[] args) {
        KeyboardDemo frame = new KeyboardDemo();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }


    public KeyboardDemo() {


        setTitle("KeyboardDemo");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        // 将 panel 加到 frame
        JTextField  jTextField=new JTextField(30);
        jTextField.setBackground(bColor);
        jTextField.setForeground(fColor);
        jTextField.setSize(200, 30);
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
        Thread t1 = new Thread(panel,"一");
        contentPane.add(panel, BorderLayout.CENTER);
        t1.start();
        jTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyPressed(KeyEvent e) {
               if(e.isControlDown()&&e.getKeyCode()== KeyEvent.VK_DOWN){
                   panel.sleep=panel.sleep-10<10?10:panel.sleep-10;
               }
               else if(e.isControlDown()&&e.getKeyCode()==KeyEvent.VK_UP){
                   panel.sleep=panel.sleep+10<10?10:panel.sleep+10;
                }

                if(e.isControlDown()&&e.getKeyCode()== KeyEvent.VK_LEFT){
                    panel.count=panel.count-5<10?10:panel.count-5;
                }
                else if(e.isControlDown()&&e.getKeyCode()==KeyEvent.VK_RIGHT){
                    panel.count=panel.count+5<10?10:panel.count+5;
                }



                else if(e.isAltDown()&&e.getKeyCode()==KeyEvent.VK_R){
                    panel.fColor=Color.red;
                    fColor=Color.red;
                    jTextField.setForeground(fColor);
                    jLabel.setForeground(fColor);
                }
                else if(e.isAltDown()&&e.getKeyCode()==KeyEvent.VK_G){
                    panel.fColor=Color.green;
                    fColor=Color.green;
                    jTextField.setForeground(fColor);
                    jLabel.setForeground(fColor);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                String txt=jTextField.getText();
                jLabel.setText(txt);
                panel.txt=txt;

            }
        });

    }

    public static final int DEFAULT_WIDTH = 800;
    public static final int DEFAULT_HEIGHT = 600;

    class DrawPanel extends JPanel implements Runnable{
        private String txt="";
        private int sleep=100;
        private int count=15;
        private Color fColor=Color.green;
        public void paintComponent(Graphics g,double leftX,  double topY ) {
            super.paintComponent(g);
            g.setColor(fColor);
            g.setFont(new Font("", Font.ROMAN_BASELINE, 16));
            g.drawString("这是最后一行文本 当前字体行高:" + g.getFontMetrics().getHeight() + "  窗体宽度:" + this.getWidth()+ "  字条个数:" + count+" 延时："+sleep, 0, this.getHeight() - 5);
            for(int i=0;i<count;i++){
               drawTxtArray(txt, g, Tools.getNoBetween(10, this.getWidth() / 10) * 10 - 100, Tools.getNoBetween(5, 60) * 10);
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
            g.setFont(new Font("", Font.ROMAN_BASELINE, com.db.Tools.getNoBetween(14,40)));
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


        @Override
        public void run() {
            while (true){
                try{
                    Thread.sleep(sleep);
                }catch (InterruptedException ee){

                }
              repaint();
            }
        }
    }}

