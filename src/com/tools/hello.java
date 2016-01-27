package com.tools;

/**
 * Created by jack lu on 2016/1/27.
 */
public class hello{
    public static void main(String[] args){
        String who="world";
        if(args.length>0){
            who=args[0];
        }System.out.println("Hello "+who);
    }
}