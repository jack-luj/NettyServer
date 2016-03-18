package com.study.keyboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;

/**
 * Created by jack lu on 2016/3/18.
 */
public class KeyboardDemo extends JFrame {
    private Color bColor=Color.black;//统一背景色
    private Color fColor=Color.green;//前景色
    DrawPanel panel = new DrawPanel();
    public static void main(String[] args) {
        KeyboardDemo frame = new KeyboardDemo();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void initXY(){//初始化数组 起始X Y 以及speed
        panel.startX=new int[panel.count];
        panel.startY=new int[panel.count];
        panel.speeds=new int[panel.count];
        for(int i=0;i<panel.count;i++)
        {
            panel.startX[i]=Tools.getNoBetween(10, this.getWidth() / 10) * 10 - 50;
            panel.startY[i]=50;
            panel.speeds[i]=Tools.getNoBetween(5, 50);//速度差值,越大列间速度差越大
        }
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
        initXY();

        this.addWindowStateListener(new WindowStateListener() {//最大化时重新初始化数组
            public void windowStateChanged(WindowEvent e) {
                System.out.println("state changed");
                if (e.getNewState() == JFrame.MAXIMIZED_BOTH) {
                    initXY();
                }
            }
        });

        jTextField.addKeyListener(new KeyListener() {//处理键盘事件，变颜色，速度，文字列数
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
                    initXY();
                }
                else if(e.isControlDown()&&e.getKeyCode()==KeyEvent.VK_RIGHT){
                    panel.count=panel.count+5<10?10:panel.count+5;
                    initXY();
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
                else if(e.isAltDown()&&e.getKeyCode()==KeyEvent.VK_Y){
                    panel.fColor=Color.yellow;
                    fColor=Color.yellow;
                    jTextField.setForeground(fColor);
                    jLabel.setForeground(fColor);
                }
                else if(e.isAltDown()&&e.getKeyCode()==KeyEvent.VK_3){
                    jTextField.setText("▲");
                }
                else if(e.isAltDown()&&e.getKeyCode()==KeyEvent.VK_4){
                    jTextField.setText("☆");
                }
                else if(e.isAltDown()&&e.getKeyCode()==KeyEvent.VK_5){
                    jTextField.setText("★");
                }
                else if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
                    jTextField.setText("");
                }
                else if(e.isControlDown()&&e.getKeyCode()==KeyEvent.VK_P){
                    if(panel.isSuspend==0){
                        t1.suspend();
                        panel.isSuspend=1;
                    }else{
                        t1.resume();
                        panel.isSuspend=0;
                    }

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
        private int isSuspend=0;
        private String txt="";
        private int sleep=100;
        private int count=15;
        private Color fColor=Color.green;
        private int[] speeds;
        private int[] startX;
        private int[] startY;
        public void paintComponent(Graphics g,double leftX,  double topY ) {
            super.paintComponent(g);
            g.setColor(fColor);
            g.setFont(new Font("", Font.ROMAN_BASELINE, 16));
            g.drawString("这是最后一行文本 当前字体行高:" + g.getFontMetrics().getHeight() + "  窗体宽度:" + this.getWidth()+ "  字条个数:" + count+" 延时："+sleep, 0, this.getHeight() - 5);
            if(txt.trim().length()==0){
            return;
            }
            for(int i=0;i<count;i++){
               drawTxtArray(txt, g, startX[i], startY[i],i);
           }
        }
        // 字符串切割,实现字符串自动换行
        /**
         * 写字符串到指定起始坐标 竖着写
         * @param text 文本
         * @param g Graphics
         * @param x 起始坐标x
         * @param y 起始坐标y
         */
        public void drawTxtArray(String text,Graphics g,int x,int y,int index){
            g.setFont(new Font("", Font.ROMAN_BASELINE, Tools.getNoBetween(14,40)));
            char[] chars=text.toCharArray();
            for(int i=0;i<chars.length;i++){
            if(y+g.getFontMetrics().getHeight()*i<=this.getHeight()-g.getFontMetrics().getHeight()){
                    g.drawString(String.valueOf(chars[i]), x, y + g.getFontMetrics().getHeight()*i);
                }
                }
            startY[index]=startY[index]+speeds[index];
            if(startY[index]>this.getHeight()){
                startY[index]=50;
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
            while (true){//Panel一直处于重绘状态，通过外部类来改变Panel的各项参数
                try{
                    Thread.sleep(sleep);
                    repaint();
                }catch (InterruptedException ee){

                }

            }
        }
    }}

