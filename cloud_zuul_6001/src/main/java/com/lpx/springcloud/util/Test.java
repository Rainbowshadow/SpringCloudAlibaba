package com.lpx.springcloud.util;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class Test {
  

    public  boolean isEffectiveDate() throws ParseException {
        String format = "HH:mm:ss";
        Date now = new Date();
        DateFormat d3 = DateFormat.getTimeInstance();
        String str3 = d3.format(now);
        Date nowTime = new SimpleDateFormat(format).parse(str3);
        Date startTime = new SimpleDateFormat(format).parse("9:00:00");
        Date endTime = new SimpleDateFormat(format).parse("22:00:00");
    	
        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }
}