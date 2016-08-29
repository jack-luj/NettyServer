package com.eparking.msgPanel;

import com.company.DataTool;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.swing.*;
import javax.xml.bind.DatatypeConverter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.HashMap;


@SuppressWarnings("serial")
public class MsgPanel extends JFrame{

    private HashMap<String,String> msg;
    private JPanel panel = new JPanel();
    JButton bt1= new JButton("确定");
    Font font=new Font("", Font.ROMAN_BASELINE, 24);

    JLabel  lab1=new JLabel("识别结果");
    JTextField t1=new JTextField("");
    JLabel  lab2=new JLabel("车牌颜色");
    JTextField t2=new JTextField("");
    JLabel  lab3=new JLabel("车牌颜色");
    JTextField t3=new JTextField("");
    JLabel  lab10=new JLabel("车主姓名");
    JTextField stime=new JTextField("舒子豪");
    JLabel  lab4=new JLabel("品牌");
    JTextField pr=new JTextField("一汽奥迪");
    JLabel  lab5=new JLabel("车型");
    JTextField city=new JTextField("A6L");



    public MsgPanel(HashMap<String,String> msg)
    {
        this.msg=msg;
        this.setTitle("车辆到店提醒");

        panel.setLayout(null);
        lab1.setBounds(100, 60, 100, 30); lab1.setFont(font);
        //panel.add(lab1);
        t1.setBounds(220, 60, 250, 30);   t1.setFont(font);
        //panel.add(t1);
        lab2.setBounds(100, 90, 100, 30);  lab2.setFont(font);   panel.add(lab2);
        t2.setBounds(220, 90, 250, 30);   t2.setFont(font);    panel.add(t2);
        lab3.setBounds(100, 120, 100, 30);  lab3.setFont(font);   panel.add(lab3);
        t3.setBounds(220, 120, 250, 30);   t3.setFont(font); panel.add(t3);
        lab10.setBounds(100, 150, 100, 30); lab10.setFont(font);   panel.add(lab10);
        stime.setBounds(220, 150, 250, 30); stime.setFont(font);  panel.add(stime);
        lab4.setBounds(100, 180, 100, 30); lab4.setFont(font);   panel.add(lab4);
        pr.setBounds(220, 180, 250, 30);  pr.setFont(font); panel.add(pr);
        lab5.setBounds(100, 210, 100, 30);  lab5.setFont(font);  panel.add(lab5);
        city.setBounds(220, 210, 250, 30); city.setFont(font);  panel.add(city);
         bt1.setBounds(200, 300, 200, 30);  bt1.setFont(font);  panel.add(bt1);

        t1.setText(msg.get("result"));
        t2.setText(msg.get("plate"));
        t3.setText(msg.get("color"));

        bt1.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                    dispose();
                    }
                });

        this.add(panel,0);
        this.setSize(600, 400);
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

        Toolkit kit = Toolkit.getDefaultToolkit(); // 定义工具包
        Dimension screenSize = kit.getScreenSize(); // 获取屏幕的尺寸
        int screenWidth = screenSize.width/2; // 获取屏幕的宽
        int screenHeight = screenSize.height/2; // 获取屏幕的高
        int height = this.getHeight(); int width = this.getWidth(); setLocation(screenWidth-width/2, screenHeight-height/2);

        this.setVisible(true);
    }


    public static void main(String[]args)
    {
        HashMap<String,String> msg=new HashMap<String,String>();
        msg.put("result","成功");
        msg.put("plate","鄂A9K999");
        msg.put("color","蓝");

        new MsgPanel(msg);
    }



}