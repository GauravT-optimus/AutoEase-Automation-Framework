/**
 * Project Name:PAF_HC
 * File Name:TimeUtils.java
 * Package Name:com.hp.utility
 * Date:Sep 7, 201310:33:19 AM
 * Copyright (c) 2013, alterhu2020@gmail.com All Rights Reserved.
 *
*/

package com.autoease.framework.utilities;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * ClassName:TimeUtils 
 * Function: TODO ADD FUNCTION. 
 * Reason:	 TODO ADD REASON. 
 * Date:     Sep 7, 2013 10:33:19 AM 
 * @author   huchan

 * @since    JDK 1.6
 * @see 	 
 * @version $Revision: 1.0 $
 */
public class TimeUtils {

	
	   public static Logger logger=Logger.getLogger(TimeUtils.class);
	
	
		/** 
		* @Title: getCurrentTime 
		* @Description: TODO
		* @author alterhu2020@gmail.com
		* @param @param date
		* @param @return    
		* @return String    return type
		* @throws 
		*/ 
		
		public static String getCurrentTime(Date date){
			String currenttime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
			logger.debug("Get current running time is :"+currenttime);
			return currenttime;
		}
		
		/** 
		* @Title: howManySeconds 
		* @Description: update in 2014/08/03
		* @author alterhu2020@gmail.com
		* @param @param starttime
		* @param @param endtime
		* @param @return    
		* @return String    return type
		* @throws 
		*/ 
		
		public static String howManySeconds(long startseconds) {
			long endseconds =System.nanoTime();
			logger.debug("the start time is:" + startseconds
					+ ",the end time is:" + endseconds);
			long millonseconds = endseconds - startseconds;
			String totalseconds = String.valueOf(millonseconds /1000 )+" Second(s)";
		    logger.debug("the total took time is:"+totalseconds);
			return totalseconds;

		}
		/** 
		* @Title: howManyMinutes 
		* @Description: TODO
		* @author alterhu2020@gmail.com
		* @param @param starttime
		* @param @param endtime
		* @param @return    
		* @return String    return type
		* @throws 
		*/ 
		
		public static String howManyMinutes(String starttime,String endtime) {
			long startseconds = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(
					starttime, new ParsePosition(0)).getTime();
			long endseconds =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(
					endtime, new ParsePosition(0)).getTime();
			
			logger.debug("the start time is:" + starttime
					+ ",the end time is:" + endtime);
			long millonseconds = endseconds - startseconds;
			String totalminutes = String.valueOf(millonseconds / (1000 * 60))+" Minute(s)";
		    logger.debug("the total took time is:"+totalminutes);
			return totalminutes;

		}
		
		 /** 
		* @Title: getMondayOfThisWeek 
		* @Description: TODO
		* @author alterhu2020@gmail.com
		* @param @return    
		* @return String    return type
		* @throws 
		*/ 
		
		public static String getMondayOfThisWeek() {
		  Calendar c = Calendar.getInstance();
		  int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
		  if (day_of_week == 0)
		     day_of_week = 7;
		  c.add(Calendar.DATE, -day_of_week + 1);
		  return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
		 }
	
		 /** 
		* @Title: getSundayOfThisWeek 
		* @Description: TODO
		* @author alterhu2020@gmail.com
		* @param @return    
		* @return String    return type
		* @throws 
		*/ 
		
		public static String getSundayOfThisWeek() {
		  Calendar c = Calendar.getInstance();
		  int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
		  if (day_of_week == 0)
		   day_of_week = 7;
		  c.add(Calendar.DATE, -day_of_week + 7);
		  return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
		 }

		 
	
		/** 
		* @Title: getCurrentWeekDays 
		* @Description: TODO
		* @author alterhu2020@gmail.com
		* @param @return    
		* @return List<String>    return type
		* @throws 
		*/ 
		
		public static List<String> getCurrentWeekDays()
		{
			 List<String> currentweek=new ArrayList<String>();
			 for(int kindex=1;kindex<=7;kindex++)
			 {
			     Calendar c = Calendar.getInstance();
			     int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
			     if (day_of_week == 0)
			         day_of_week = 7;
				 c.add(Calendar.DATE, -day_of_week+kindex);
				 String currentday=new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
				 currentweek.add(currentday);
			 }
			return currentweek;
		}
		
	
		/** 
		* @Title: getCurrentMonthday 
		* @Description: TODO
		* @author alterhu2020@gmail.com
		* @param @return    
		* @return List<String>    return type
		* @throws 
		*/ 
		
		public static List<String> getCurrentMonthday()
		{
			List<String> currentmonth=new ArrayList<String>();
			Calendar c=Calendar.getInstance();
			int totalday=c.getActualMaximum(Calendar.DAY_OF_MONTH);
			System.out.println("current month total day is :"+totalday);
			c.set(Calendar.DAY_OF_MONTH, 1);
			System.out.println("first day is :"+new SimpleDateFormat("yyyy-MM-dd").format(c.getTime()));
			//currentmonth.add(new SimpleDateFormat("yyyy-MM-dd").format(c.getTime()));
			for(int index=1;index<=totalday;index++)
			{
				c.set(Calendar.DAY_OF_MONTH, index);
				currentmonth.add(new SimpleDateFormat("yyyy-MM-dd").format(c.getTime()));
			}
			return currentmonth;
		}
	
		/** 
		* @Title: getFirstdayofMonth 
		* @Description: TODO
		* @author alterhu2020@gmail.com
		* @param @return    
		* @return String    return type
		* @throws 
		*/ 
		
		public static String getFirstdayofMonth()
		{
		  Calendar c=Calendar.getInstance();
		  c.set(Calendar.DAY_OF_MONTH, 1);
		  return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
			
		}
	
		/** 
		* @Title: getLastdayofMonth 
		* @Description: TODO
		* @author alterhu2020@gmail.com
		* @param @return    
		* @return String    return type
		* @throws 
		*/ 
		
		public static String getLastdayofMonth()
		{
			Calendar c=Calendar.getInstance();
			int totalday=c.getActualMaximum(Calendar.DAY_OF_MONTH);
			c.set(Calendar.DAY_OF_MONTH, totalday);
			System.out.println(c.getTime());
			return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
		}
		
}

