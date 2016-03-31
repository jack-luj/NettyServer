package com.study.mouse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by Administrator on 2016/3/31.
 */
public class MouseShow extends JPanel implements MouseListener,MouseMotionListener{
    private static Color bColor=Color.black;//统一背景色
    private static Color fColor=Color.green;//前景色
    private int fontSize=16;
    private int r=5;//点周围圆圈半径
    private int nearRange=8;//点选中范围
    public static void main(String[] args) {
        MouseShow panel = new MouseShow();
        panel.setBackground(bColor);
        JFrame frame = new JFrame();
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setTitle("MouseShow");
        frame.setContentPane(panel);
    }
    public MouseShow(){
        setPreferredSize(new Dimension(450, 450));
        addMouseListener(this);
        addMouseMotionListener(this);
     }

    private List<Point> pointList=new ArrayList<Point>();
    public void addPoint(int x,int y){
        Point p=new Point(x,y);
        pointList.add(p);
    }
    public void clearPoints(){
        pointList.clear();
    }

    public void paintPoint(Graphics g) {
        g.setColor(fColor);
        g.setFont(new Font("", Font.ROMAN_BASELINE, fontSize));
        for(int i=0;i<pointList.size();i++){
           Point p=pointList.get(i);
            g.drawOval((int)p.getX()-r,(int)p.getY()-r,2*r,2*r);
        }
    }

    public void paintSelectedPoint(List<Point> selectedPoints) {
        Graphics g=this.getGraphics();
        g.setColor(Color.red);
        g.setFont(new Font("", Font.ROMAN_BASELINE, fontSize));
        for(int i=0;i<selectedPoints.size();i++){
            Point p=selectedPoints.get(i);
            g.drawOval((int)p.getX()-r,(int)p.getY()-r,3*r,3*r);
        }
        repaint();
    }

    public void paintLine(Graphics g) {
        g.setColor(fColor);
        g.setFont(new Font("", Font.ROMAN_BASELINE, fontSize));
        if(pointList.size()>1){
            for(int i=0;i<pointList.size()-1;i++){
                for(int j=i;j<pointList.size();j++){
                    Point p1=pointList.get(i);
                    Point p2=pointList.get(j);
                    g.drawLine((int)p1.getX(),(int)p1.getY(),(int)p2.getX(),(int)p2.getY());
                }
            }
        }
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(fColor);
        g.setFont(new Font("", Font.ROMAN_BASELINE, fontSize));
        paintPoint(g);
        paintLine(g);
    }

    public Point getClickPoint(int x,int y){
     Point re=null;
        for(int i=0;i<pointList.size();i++){
            Point p=pointList.get(i);
            if(isNearPoint(p,x,y)){
                 return p;
            }
        }
        return re;
    }

    public boolean isNearPoint(Point point,int x,int y){
        boolean re=false;
        if(Math.abs(x-point.getX())<=nearRange && Math.abs(y-point.getY())<=nearRange){
            re=true;
        }
        return re;
    }

    private Point clickPoint;

    @Override
    public void mouseClicked(MouseEvent e) {
       addPoint(e.getX(),e.getY());
       repaint(1000);
        System.out.println("mouseClicked:"+e.getX()+","+e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        List<Point> selectedPoints=new ArrayList<Point>();
        System.out.println("mousePressed:"+e.getX()+","+e.getY());
        clickPoint=getClickPoint(e.getX(),e.getY());
        if(clickPoint!=null){
            System.out.println(">>>>>>>>>>>>>>>>>点中的 " + clickPoint.getX() + "," + clickPoint.getY());
            selectedPoints.add(clickPoint);
            paintSelectedPoint(selectedPoints);
            repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("mouseReleased:"+e.getX()+","+e.getY());
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("mouseEntered:"+e.getX()+","+e.getY());
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("mouseExited:"+e.getX()+","+e.getY());
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        System.out.println("mouseDragged:"+e.getX()+","+e.getY());
        if(clickPoint!=null){
            clickPoint.move(e.getX(),e.getY());
            repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        System.out.println("mouseMoved:"+e.getX()+","+e.getY());
    }
}
