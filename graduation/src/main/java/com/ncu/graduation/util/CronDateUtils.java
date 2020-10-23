package com.ncu.graduation.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.Data;

/**
 * @author ：grh
 * @date ：Created in 2020/4/8 0:12
 * @description：Cron和Date互相转化
 * @modified By：
 * @version: 0.0.1
 */

@Data
public class CronDateUtils {
  private static final String CRON_DATE_FORMAT = "ss mm HH dd MM ? yyyy";

  /***
   *
   * @param date 时间
   * @return  cron类型的日期
   */
  public static String getCron(final Date date){
    SimpleDateFormat sdf = new SimpleDateFormat(CRON_DATE_FORMAT);
    String formatTimeStr = "";
    if (date != null) {
      formatTimeStr = sdf.format(date);
    }
    return formatTimeStr;
  }

  /***
   *
   * @param cron Quartz cron的类型的日期
   * @return  Date日期
   */

  public static Date getDate(final String cron) {
    if(cron == null) {
      return null;
    }

    SimpleDateFormat sdf = new SimpleDateFormat(CRON_DATE_FORMAT);
    Date date = null;
    try {
      date = sdf.parse(cron);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return date;
  }


}
