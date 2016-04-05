package com.study.mouse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by Administrator on 2016/3/31.
 */
public class MouseShow extends JPanel implements MouseListener,MouseMotionListener,KeyListener{
    private static Color bColor=Color.black;//统一背景色
    private static Color fColor=Color.green;//前景色
    private int fontSize=16;
    private int r=5;//点周围圆圈半径
    private int nearRange=8;//点选中范围
    public static void main(String[] args) {
        MouseShow panel = new MouseShow();
        panel.setBackground(bColor);
        JFrame frame = new JFrame();
        frame.setSize(1000, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setTitle("MouseShow");
        frame.setContentPane(panel);
        panel.requestFocus();//panel获得焦点 以便接收键盘事件
    }
    public MouseShow(){

        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);
     }

    private List<Point> pointList=new ArrayList<Point>();
    private Point selectedStartPoint;
    private Point selectedEndPoint;
    public void addPoint(int x,int y){
        Point p=new Point(x,y);
        pointList.add(p);
    }
    public void clearPoints(){
        pointList.clear();
    }

    /**
     * 绘制原始Point
     * @param g
     */
    public void paintPoint(Graphics g) {
        g.setColor(fColor);
        g.setFont(new Font("", Font.ROMAN_BASELINE, fontSize));
        for(int i=0;i<pointList.size();i++){
           Point p=pointList.get(i);
            g.drawOval((int)p.getX()-r,(int)p.getY()-r,2*r,2*r);
        }
    }

    /**
     * 绘制选中点红圈
     * @param g
     */
    public void paintSelectedPoint(Graphics g) {

        g.setColor(Color.red);
        g.setFont(new Font("", Font.ROMAN_BASELINE, fontSize));
        for(int i=0;i<selectedPointList.size();i++){
            Point p=selectedPointList.get(i);
            g.drawOval((int)p.getX()-2*r,(int)p.getY()-2*r,4*r,4*r);
        }
        // 1 repaint();  死循环调用
    }

    /**
     * 绘制Point之间的连线
     * @param g
     */
    public void paintLine(Graphics g) {
            if(pointList.size()>1){  //0,1,2  ->0-1 0-2 1-2
            for(int i=0;i<pointList.size()-1;i++){
                for(int j=i+1;j<pointList.size();j++){
                    Point p1=pointList.get(i);
                    Point p2=pointList.get(j);
                    //System.out.println(i+"---"+j);
                    g.drawLine((int)p1.getX(),(int)p1.getY(),(int)p2.getX(),(int)p2.getY());
                }
            }
        }
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintPoint(g);
        paintLine(g);
        paintRectangle(g);
        paintSelectedPoint(g);
    }

    /**
     * 绘制矩形选中框
     * @param g
     */
    public void paintRectangle(Graphics g) {
            g.setColor(Color.blue);
            if(selectedStartPoint!=null && selectedEndPoint!=null){
            int minX=Math.min(selectedStartPoint.x,selectedEndPoint.x);
            int maxX=Math.max(selectedStartPoint.x,selectedEndPoint.x);
            int minY=Math.min(selectedStartPoint.y,selectedEndPoint.y);
            int maxY=Math.max(selectedStartPoint.y, selectedEndPoint.y);
            //System.out.println("paintRetancle " + selectedStartPoint.x + "," + selectedStartPoint.y + "," + selectedEndPoint.x + "," + selectedEndPoint.y);
            g.drawRect(minX, minY, maxX - minX, maxY - minY);

            repaint();
        }

    }

    /**
     * 返回鼠标附近被点中的Point
     * @param x
     * @param y
     * @return
     */
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

    /**
     * 判断鼠标点击是否在指定Point周围
     * @param point
     * @param x
     * @param y
     * @return
     */
    public boolean isNearPoint(Point point,int x,int y){
        boolean re=false;
        if(Math.abs(x-point.getX())<=nearRange && Math.abs(y-point.getY())<=nearRange){
            re=true;
        }
        return re;
    }

    /**
     * 通过选中区域起始点和结束点得到选中的点集合
     */
    public void getSelectedPoints(){
        selectedPointList.clear();
        int minX=Math.min(selectedStartPoint.x,selectedEndPoint.x);
        int maxX=Math.max(selectedStartPoint.x,selectedEndPoint.x);
        int minY=Math.min(selectedStartPoint.y,selectedEndPoint.y);
        int maxY=Math.max(selectedStartPoint.y, selectedEndPoint.y);
        for(int i=0;i<pointList.size();i++){
            Point p=pointList.get(i);
            if(minX<=p.x && p.x<= maxX && minY<=p.y && p.y<= maxY){
               selectedPointList.add(p);
            }
        }
        System.out.println("getSelectedPoints>>>"+ selectedPointList.size());

    }

    /**
     * 计算中心点
     * @return
     */
    public Point getCenter(List<Point> selectedPointList){
        Point center=new Point();
        center.setLocation(0, 0);
        int xTotal=0;
        int yTotal=0;
        for (int i = 0; i < selectedPointList.size(); i++) {
            Point p=selectedPointList.get(i);
            xTotal=xTotal+p.x;
            yTotal=yTotal+p.y;
        }
        if(selectedPointList.size()>0){
            center=new Point();
            center.setLocation(xTotal/selectedPointList.size(),yTotal/selectedPointList.size());
        }
        return center;
    }

    /**
     * 移动指定点
     * @param addX
     * @param addY
     * @param selectedPointList
     */
    public void movePoints(int addX,int addY,List<Point> selectedPointList){
        for(int i=0;i<selectedPointList.size();i++){//遍历移动点坐标
            Point p=selectedPointList.get(i);
            movePoint(p, p.x + addX, p.y + addY);
        }
    }

    public void movePoint(Point p,int x,int y){
        p.move(x,y);

    }

    public void repaint(){
        super.repaint();
    }

    public void movePointsGracefully(int addX,int addY,List<Point> selectedPointList){
        List<Point> newList=new ArrayList<Point>();
        for(int j=0;j<1000;j++){
            for(int i=0;i<selectedPointList.size();i++){
                Point p=selectedPointList.get(i);
                //p.setLocation(p.getX() + addX/50, p.getY() + addY/50);
                p.setLocation(p.getX() + 1,p.getY());
                newList.add(p);
            }
            repaint();
            try{Thread.sleep(10);}catch (InterruptedException e){e.printStackTrace();}
        }
    }

    private Point clickPoint;
    private List<Point> selectedPointList=new ArrayList<Point>();
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("mouseClicked:"+e.getX()+","+e.getY());
        int x = e.getX();
        int y = e.getY();
        int z = e.getButton();
        int c=e.getClickCount();
        if (z == 1 && c==1) {//左键单击，新增点
            addPoint(e.getX(), e.getY());
        }else if (z == 3 ) {//右键单击，移动选中点
            //selectedPointList=pointList;
            Point center = getCenter(selectedPointList);//移动前中心点
            int addX = x - center.x;
            int addY = y - center.y;//计算移动差值
            movePoints(addX, addY, selectedPointList);
            selectedStartPoint=null;
            selectedEndPoint=null;
        }
        repaint();
    }

    private int selectStartX;
    private int selectStartY;
    private int selectEndX;
    private int selectEndY;
    private int mousePressedButton=0;
    @Override
    public void mousePressed(MouseEvent e) {
        mousePressedButton=e.getButton();
        List<Point> selectedPoints=new ArrayList<Point>();
        System.out.println("mousePressed:"+e.getX()+","+e.getY());
        clickPoint=getClickPoint(e.getX(),e.getY());//判断是否点中了已有的Point（矩形框范围r）
        if(clickPoint!=null){
            //选中点
            System.out.println(">>>>>>>>>>>>>>>>>点中的 " + clickPoint.getX() + "," + clickPoint.getY());
            selectedPoints.add(clickPoint);
            repaint();
        }else{
            //选中区域
            selectStartX=e.getX();
            selectStartY=e.getY();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //清空拖动按钮 和选中区域
        mousePressedButton=0;
        selectedStartPoint=null;
        selectedEndPoint=null;
    }

    @Override
    public void mouseEntered(MouseEvent e) {    }
    @Override
    public void mouseExited(MouseEvent e) {  }
    @Override
    public void mouseDragged(MouseEvent e) {
        if(mousePressedButton!=1){//只有左键才做拖动,mouseDragged无法取得鼠标键，只能通过mousePressed和mouseReleased得到button
            return;
        }
        if(clickPoint!=null){
            //已经选中点 拖动点操作
            clickPoint.move(e.getX(),e.getY());
            repaint();
        }else{
            //选中区域
            selectEndX=e.getX();
            selectEndY=e.getY();
            selectedStartPoint=new Point(selectStartX, selectStartY);//选中区域起始点
            selectedEndPoint=new  Point(selectEndX, selectEndY);//选中区域结束点
            getSelectedPoints();//计算选中区域的选中点
            repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) { }
    @Override
    public void keyTyped(KeyEvent e) { }
    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("keyPressed");
        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Z) {//回退，从后往前删除选中点
            selectedPointList.clear();
            if (pointList.size() > 0) {
                pointList.remove(pointList.size() - 1);
            }
        }
        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_A) {//全选全部点
            selectedPointList.clear();
          for(int i=0;i<pointList.size();i++){
              selectedPointList.add(pointList.get(i));
          }
        }
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {//取消所有点选中
            selectedPointList.clear();
        }
        repaint();
    }
    @Override
    public void keyReleased(KeyEvent e) {

    }
}
