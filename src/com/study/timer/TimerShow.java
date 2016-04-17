package com.study.timer;

import javax.swing.*;


import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by Administrator on 2016/4/17.
 */
public class TimerShow extends JPanel implements Runnable{

    private static Color bColor=Color.black;//统一背景色
    private static Color fColor=Color.RED;//前景色
    private Date deadDate=new Date();
    private Date currentDate=new Date();
    private int fontSize=30;
    long delay=300;//闪烁间隔
    public static void main(String[] args) {
        TimerShow panel = new TimerShow();
        panel.setBackground(bColor);
        panel.deadDate=new Date(new Date().getTime()+10*1000);//倒计时10s
        JFrame frame = new JFrame();
        frame.setSize(1000, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setTitle("TimerShow");
        frame.setContentPane(panel);
        Thread thread=new Thread(panel);
        thread.start();

    }
public TimerShow(){

}
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        painText(g);

    }
    public void repaint(){
        super.repaint();
        getXY();
    }
    private String showTxt="";
    private String countText="";
    private int x=100;
    private int y=100;
    public void painText(Graphics g) {
        g.setColor(fColor);
        g.setFont(new Font("", Font.ROMAN_BASELINE, fontSize));
        String deadLineStr=formatDate(deadDate);
        g.drawString("结束时间:"+deadLineStr,x,y-50);
        showTxt=formatDate(currentDate);
        g.drawString("现在时间:"+showTxt,x,y);
        long count=deadDate.getTime()-currentDate.getTime()<0?0:deadDate.getTime()-currentDate.getTime();
        countText=formatCountDate(count);
        //g.drawString("倒计时:"+countText,x+100,y+100);
        g.setFont(new Font("", Font.TRUETYPE_FONT, fontSize+50));
        g.drawString("倒计时 "+countText,x-150,y+100);
    }

    public void getXY() {
        int width=this.getWidth();
        int height=this.getHeight();
        x=(width-450)/2;
        y=height/2;
     }
    String format="yyyy-MM-dd HH:mm:ss.SSS";
    public String formatDate(Date date){
        return new SimpleDateFormat(format).format(date);
    }
    String countFormat="HH:mm:ss.SSS";
    public String formatCountDate(long t){
        Date date=new Date(t);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(countFormat);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+0"));

        return simpleDateFormat.format(date);
    }

    @Override
    public void run() {

        while (currentDate.getTime()<deadDate.getTime()) {
            try{
            Thread.sleep(1);
            currentDate = new Date();
            repaint();
            }catch (InterruptedException e){e.printStackTrace();}
        }
        this.requestFocus();

        try{
            for (int i=0;i<10;i++){
                fColor=Color.black;
                java.awt.Toolkit tk = java.awt.Toolkit.getDefaultToolkit();
                // 发出当前系统最简单的声音
                tk.beep();
                repaint();
                Thread.sleep(delay);
                fColor=Color.RED;
                repaint();
                Thread.sleep(delay);
            }
        }catch (InterruptedException e){e.printStackTrace();}
        //System.exit(0);
    }


}
