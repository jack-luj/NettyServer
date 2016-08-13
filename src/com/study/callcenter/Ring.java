package com.study.callcenter;
import com.company.DataTool;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.xml.bind.DatatypeConverter;
import java.awt.*;
import java.awt.event.*;
import java.security.MessageDigest;
import java.security.SecureRandom;


@SuppressWarnings("serial")
public class Ring extends JFrame{

    private JPanel panel = new JPanel();
    JButton bt1= new JButton("生成URL");
    //JButton bt2= new JButton("调用CRM");
    JButton bt2= new JButton("调用CRM");
    JLabel  lab1=new JLabel("电话号码");
    JTextField caller=new JTextField("13500000001");
    JLabel  lab2=new JLabel("呼叫编号");
    JTextField callid=new JTextField("20160813000001");
    JLabel  lab3=new JLabel("弹屏日期");
    JTextField sdate=new JTextField("20160813");
    JLabel  lab10=new JLabel("弹屏时间");
    JTextField stime=new JTextField("11:06:01");
    JLabel  lab4=new JLabel("省份");
    JTextField pr=new JTextField("湖北省");
    JLabel  lab5=new JLabel("城市");
    JTextField city=new JTextField("武汉市");
    JLabel  lab6=new JLabel("通话类型");
    JTextField dir=new JTextField("callin");
    JLabel  lab7=new JLabel("客服工号");
    JTextField uid=new JTextField("9612");
    JLabel  lab8=new JLabel("IVR数据");
    JTextField info=new JTextField("2");
    JLabel  lab9=new JLabel("token");
    JTextField token=new JTextField("");
    JTextField url=new JTextField("");
    String baseUrl="http://localhost:41234/mge/operation/doorToDoor/order";

    public Ring()
    {
        this.setTitle("模拟呼叫中心");



        panel.setLayout(null);
        lab1.setBounds(100, 60, 100, 20);   panel.add(lab1);
        caller.setBounds(200, 60, 250, 20);  panel.add(caller);
        lab2.setBounds(100, 80, 100, 20);   panel.add(lab2);
        callid.setBounds(200, 80, 250, 20);  panel.add(callid);
        lab3.setBounds(100, 100, 100, 20);   panel.add(lab3);
        sdate.setBounds(200, 100, 250, 20);  panel.add(sdate);
        lab10.setBounds(100, 120, 100, 20);   panel.add(lab10);
        stime.setBounds(200, 120, 250, 20);  panel.add(stime);
        lab4.setBounds(100, 140, 100, 20);   panel.add(lab4);
        pr.setBounds(200, 140, 250, 20);  panel.add(pr);
        lab5.setBounds(100, 160, 100, 20);   panel.add(lab5);
        city.setBounds(200, 160, 250, 20);  panel.add(city);
        lab6.setBounds(100, 180, 100, 20);   panel.add(lab6);
        dir.setBounds(200, 180, 250, 20);  panel.add(dir);

        lab7.setBounds(100, 200, 100, 20);   panel.add(lab7);
        uid.setBounds(200, 200, 250, 20);  panel.add(uid);

        lab8.setBounds(100, 220, 100, 20);   panel.add(lab8);
        info.setBounds(200, 220, 250, 20);  panel.add(info);
        lab9.setBounds(100, 240, 100, 20);   panel.add(lab9);
        token.setBounds(200, 240, 250, 20);  panel.add(token);

        url.setBounds(50, 300, 450, 20);   panel.add(url);
        bt1.setBounds(200, 350, 100, 30);   panel.add(bt1);
        bt2.setBounds(300, 350, 100, 30);   panel.add(bt2);






        bt1.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        //jEditorPane.setContentType("text/html");
                        if (java.awt.Desktop.isDesktopSupported()) {
                            try {
                                encode();
                            } catch (Exception ee) {
                                ee.printStackTrace();
                            }
                        }
                    }
                });

         bt2.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        //jEditorPane.setContentType("text/html");
                        if(url.getText().trim().length()==0){
                            encode();
                        }

                        if (java.awt.Desktop.isDesktopSupported()) {
                            try {
                                // 创建一个URI实例
                                java.net.URI uri = java.net.URI.create(url.getText());
                                // 获取当前系统桌面扩展
                                java.awt.Desktop dp = java.awt.Desktop.getDesktop();
                                // 判断系统桌面是否支持要执行的功能
                                if (dp.isSupported(java.awt.Desktop.Action.BROWSE)) {
                                    // 获取系统默认浏览器打开链接
                                    dp.browse(uri);
                                }
                            } catch (Exception ee) {
                                ee.printStackTrace();
                            }
                        }
                    }
                });

        this.add(panel,0);
        this.setSize(600, 600);
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    public static void main(String[]args)
    {
        new Ring();
    }

    public  void encode(){

        try{
            String des_str=des("1R4U5ST9", uid.getText().trim());
            String v = md5(des_str + callid.getText().trim());
            token.setText(v);

            StringBuilder sb=new StringBuilder();
            sb.append(baseUrl);
            sb.append("?cmd=popup_callin&channel=cc");
            sb.append("&caller=");sb.append(caller.getText().trim());
            sb.append("&callid=");sb.append(callid.getText().trim());
            sb.append("&sdate=");sb.append(sdate.getText().trim());
            sb.append("&stime=");sb.append(stime.getText().trim());
            sb.append("&pr=");sb.append(pr.getText().trim());
            sb.append("&city=");sb.append(city.getText().trim());
            sb.append("&dir=");sb.append(dir.getText().trim());
            sb.append("&uid=");sb.append(uid.getText().trim());
            sb.append("&info=");sb.append(info.getText().trim());
            sb.append("&token=");sb.append(token.getText().trim());


            url.setText(sb.toString());

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public  String des(String key, String src) throws Exception {
        byte[] IV = new byte[] {0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01};
        SecureRandom sr = new SecureRandom();
        DESKeySpec ks = new DESKeySpec(key.getBytes("UTF-8"));
        SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
        SecretKey sk = skf.generateSecret(ks);
        Cipher cip = Cipher.getInstance("DES/CBC/PKCS5Padding");//Cipher.getInstance("DES");
        IvParameterSpec iv2 = new IvParameterSpec(IV);
        cip.init(Cipher.ENCRYPT_MODE, sk, iv2);//IV的方式
        //cip.init(Cipher.ENCRYPT_MODE, sk, sr);//没有传递IV
        String dest = DataTool.bytes2hex(cip.doFinal(src.getBytes("UTF-8")));
        return dest.replace(" ", "");
    }

    public  String md5(String inbuf) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(inbuf.getBytes());
            byte[] buf = md5.digest();
            return DatatypeConverter.printHexBinary(buf);
        }
        catch(Exception ex){
            return null;
        }
    }

}