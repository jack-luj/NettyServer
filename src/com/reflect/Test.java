package com.reflect;

/**
 * Created by jackl on 2016/6/1.
 */

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


public class Test {

    public static String objectModifyLog(UserBean a,UserBean b)throws Exception{
        //得到类对象
        StringBuilder sb=new StringBuilder();

        Class ca = (Class) a.getClass();
        Class cb = (Class) b.getClass();
        System.out.println(ca.toString()+"<>"+cb.toString());
        if(!ca.toString().equals(cb.toString())){
            return "class error";
        }

        Field[] fs = ca.getDeclaredFields();
        for(int i = 0 ; i < fs.length; i++){
            Field f = fs[i];
            f.setAccessible(true); //设置些属性是可以访问的
            Object va = f.get(a);//得到此属性的值
            Object vb = f.get(b);//得到此属性的值
            String type=f.getType().getSimpleName();
            System.out.println(type);
            if(type.equals("Integer")||type.equals("String")){
                if(va!=vb){
                    System.err.println("value change:"+f.getName()+"\t value = "+va+" -> "+vb);
                    sb.append(f.getName()+":"+va+"->"+vb+";");
                }else{
                    System.out.println("value   same:"+f.getName()+"\t value = "+va+" -> "+vb);
                }
            }else if(type.equals("List")){
                List la=(List)va;
                List lb=(List)vb;
                if(la.size()==lb.size()&&la.containsAll(lb)){
                    System.out.println("value   same:"+f.getName()+"\t value = "+va+" -> "+vb);
                }else{
                    System.err.println("value change:"+f.getName()+"\t value = "+va+" -> "+vb);
                    sb.append(f.getName() + ":" + va + "->" + vb + ";");
                }
            }



        }
        return sb.toString();
    }



    public static void main(String[] args) throws Exception {

        UserBean bean = new UserBean();
        bean.setId(100);
        bean.setName("jack");
        bean.setAddress("武汉");
        List<String> _phoneList=new ArrayList<String>();
        _phoneList.add("12345678");
        _phoneList.add("12345679");
        bean.setPhoneList(_phoneList);

        UserBean beanb = new UserBean();
        beanb.setId(100);
        beanb.setName("jackl");
        beanb.setAddress("武汉");
        List<String> _phoneListb=new ArrayList<String>();
        _phoneListb.add("12345678");
       // _phoneListb.add("12345679a");
        beanb.setPhoneList(_phoneListb);

        String change=objectModifyLog(bean,beanb);
        System.out.println(change);

/*

        //得到类对象
        Class userCla = (Class) bean.getClass();

       */
/*
        * 得到类中的所有属性集合
        *//*

        Field[] fs = userCla.getDeclaredFields();
        for(int i = 0 ; i < fs.length; i++){
            Field f = fs[i];
            f.setAccessible(true); //设置些属性是可以访问的
            Object val = f.get(bean);//得到此属性的值

            System.out.println("name:"+f.getName()+"\t value = "+val);

    String type = f.getType().toString();//得到此属性的类型
            if (type.endsWith("String")) {
                System.out.println(f.getType()+"\t是String");
                f.set(bean,"12") ;        //给属性设值
            }else if(type.endsWith("int") || type.endsWith("Integer")){
                System.out.println(f.getType()+"\t是int");
                f.set(bean,12) ;       //给属性设值
            }else{
                System.out.println(f.getType()+"\t");
            }

        }
*/


       /*
        * 得到类中的方法
        */
      /*  Method[] methods = userCla.getMethods();
        for(int i = 0; i < methods.length; i++){
            Method method = methods[i];
            if(method.getName().startsWith("get")){
                System.out.print("methodName:"+method.getName()+"\t");
                System.out.println("value:"+method.invoke(bean));//得到get 方法的值
            }
        }*/





    }

}