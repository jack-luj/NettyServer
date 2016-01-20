package com.company;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by luj on 2015/12/15.
 */
public class WeekTest {

    public static Date parseStrToDate(String dateStr) {
        Date date;

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            date = (Date) dateFormat.parse(dateStr);
        } catch (Exception e) {
            return null;
        }
        return date;
    }

    public static void main(String[] args) {
        Calendar now=Calendar.getInstance();
        //
        System.out.println(now.getFirstDayOfWeek());
        System.out.println(now.get(Calendar.YEAR));
        System.out.println(now.get(Calendar.WEEK_OF_MONTH));
        System.out.println(now.get(Calendar.MONTH));
        Date a=parseStrToDate("20150114");
        System.out.print(new WeekTest().getYearWeekStartDate(2016, 1).toLocaleString());
        System.out.print(" ~ ");
        System.out.println(new WeekTest().getYearWeekEndDate(2016, 1).toLocaleString());

        System.out.print(new WeekTest().getYearWeekStartDate(2015, 1).toLocaleString());
        System.out.print(" ~ ");
        System.out.println(new WeekTest().getYearWeekEndDate(2015, 1).toLocaleString());

       /* System.out.println(new WeekTest().getYearMonthStartDate(2015, 12).toLocaleString());
        System.out.println(new WeekTest().getYearMonthEndDate(2015, 12).toLocaleString());*/
    }

    /**
     * 某年某月的开始时间，2015 12-》2015-12-1 0:00:00 比较时用>=
     * @param year 年 20xx
     * @param month 周 1-12
     * @return
     */
    public Date getYearMonthStartDate(int year,int month){
        Calendar cc=Calendar.getInstance();
        cc.set(Calendar.YEAR, year);
        cc.set(Calendar.MONTH,month-1);
        cc.set(Calendar.DAY_OF_MONTH,1);
        cc.set(Calendar.HOUR_OF_DAY,0);
        cc.set(Calendar.MINUTE,0);
        cc.set(Calendar.SECOND,0);
        cc.set(Calendar.MILLISECOND,0);
        Date re=cc.getTime();
        return re;
    }

    /**
     * 某年某月的结束时间，2015 12-》2016-1-1 0:00:00 比较时用<
     * @param year 年 20xx
     * @param month 周 1-12
     * @return
     */
    public Date getYearMonthEndDate(int year,int month){
        Date startDate=getYearMonthStartDate(year, month);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.MONTH, 1);
        Date re=calendar.getTime();
        return re;
    }














    public Date getYearWeekStartDate(int year,int week){
        //某年的第多少周-->yyyyMMdd
        Calendar cc=Calendar.getInstance();
        cc.set(Calendar.YEAR, year);
        cc.set(Calendar.WEEK_OF_YEAR, week);
        cc.set(Calendar.DAY_OF_WEEK, 2);
        cc.set(Calendar.HOUR_OF_DAY,0);
        cc.set(Calendar.MINUTE,0);
        cc.set(Calendar.SECOND,0);
        cc.set(Calendar.MILLISECOND,0);
        Date re=cc.getTime();
        return re;
    }
    public Date getYearWeekEndDate(int year,int week){
        //某年的第多少周-->yyyyMMdd
        Date re=getYearWeekStartDate(year, week);
        re=new Date(re.getTime()+7*24*60*60*1000);
        return re;
    }



}
