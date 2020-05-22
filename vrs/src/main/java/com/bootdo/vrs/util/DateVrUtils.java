package com.bootdo.vrs.util;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Component
public class DateVrUtils {




    public   long getDay(Date entTime){
        Calendar calender= Calendar.getInstance();


        Calendar c1=Calendar.getInstance();
        c1.set(calender.get(Calendar.YEAR), calender.get(Calendar.MONTH)+1,calender.get(Calendar.DATE));
        Calendar c2=Calendar.getInstance();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd") ; //使用了默认的格式创建了一个日期格式化对象。
        String time = dateFormat.format(entTime); //可以把日期转换转指定格式的字符串
        String[] ds=time.split("-");
        int y=Integer.parseInt(ds[0]);
        int m=Integer.parseInt(ds[1]);
        int d=Integer.parseInt(ds[2]);


        c2.set(y,m,d);
        long t1=c1.getTimeInMillis();
        long t2=c2.getTimeInMillis();
        long days=(t2-t1)/(24*60*60*1000);



        return  days;
    }



}
