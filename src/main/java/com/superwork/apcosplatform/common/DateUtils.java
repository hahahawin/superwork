package com.superwork.apcosplatform.common;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日期操作工具类
 * @author Chenbing(Jack.Chen)
 * @version 1.0
 * @createTime 2012 4:14:09 PM
 * @Email wwwchenbing@gmail.com
 */
public class DateUtils {
	public static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:ss");

	private static Calendar calendar = Calendar.getInstance();

	// 每天的毫秒数: 86400000
	private final static int dayMilliseconds = 86400000;

	
	
	/**

     * 将Date日期转换成XMLGregorianCalendar

     * @param date

     * @return

     */

	public static XMLGregorianCalendar convertToXMLGregorianCalendar(Date date){
	
		XMLGregorianCalendar xmlGregorianCalendar = null;
		
		GregorianCalendar c = new GregorianCalendar(); 
		
		c.setTime(date);
		
		try{
		
		     xmlGregorianCalendar= DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
		
		}catch (DatatypeConfigurationException e) {
		
			e.printStackTrace();
			
			System.out.println("org.util.DataFormat中formatDateToXMLGregorianCalendar方法将Date日期转换成XMLGregorianCalendar异常");
		
		}
		
		return xmlGregorianCalendar;
	
	}
	 
	/**
	
	 * 将XMLGregorianCalendar转换成Date类型
	
	 * @param cal
	
	 * @return
	
	 * @throws Exception
	
	 */
	
	public static Date convertToDate(XMLGregorianCalendar cal)throws Exception{
	
	      GregorianCalendar ca =cal.toGregorianCalendar();
	
	      return ca.getTime();
	
	}

	
	/**
	 * 得到日期的年
	 * 
	 * @param date
	 * @return Long
	 */
	static public Long getYearFromDate(Date date) {
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		return new Long(year);
	}

	/**
	 * 得到日期的月
	 * 
	 * @param date
	 * @return Long
	 */
	static public Long getMonthFromDate(Date date) {
		calendar.setTime(date);
		int month = calendar.get(Calendar.MONTH) + 1;
		return new Long(month);
	}

	/**
	 * 得到日期的日
	 * 
	 * @param date
	 * @return Long
	 */
	static public Long getDayFromDate(Date date) {
		calendar.setTime(date);
		int day = calendar.get(Calendar.DATE);
		return new Long(day);
	}

	/**
	 * 从年和月得到日期
	 * 
	 * @param year
	 * @param month
	 * @return Date
	 */
	static public Date getDateFromYearMonth(Long year, Long month) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = month.toString();
		if (month.longValue() < 10)
			dateStr = "0" + dateStr;
		dateStr = year.toString() + "-" + dateStr + "-01";
		try {
			Date date = dateFormat.parse(dateStr);
			return date;
		} catch (ParseException e) {
			throw new IllegalArgumentException("Could not parse date: "
					+ e.getMessage());
		}
	}

	/**
	 * 从年月日得到日期
	 * 
	 * @param year
	 * @param month
	 * @return Date
	 */
	static public Date getDateFromYearMonthDay(Long year, Long month, Long day) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = month.toString();
		String dayStr = day.toString();
		if (month.longValue() < 10)
			dateStr = "0" + dateStr;
		if (day.longValue() < 10)
			dayStr = "0" + dayStr;
		dateStr = year.toString() + "-" + dateStr + "-" + dayStr;
		try {
			Date date = dateFormat.parse(dateStr);
			return date;
		} catch (ParseException e) {
			throw new IllegalArgumentException("Could not parse date: "
					+ e.getMessage());
		}
	}

	/**
	 * 得到两个日期间的天数
	 * 
	 * @param firstDate
	 * @param lastDate
	 * @return int
	 */
	static public int getDaysOfDates(Date firstDate, Date lastDate) {
		return (int) ((lastDate.getTime() - firstDate.getTime()) / dayMilliseconds);
	}

	/**
	 * 日期加指定月数
	 * 
	 * @param date
	 * @param months
	 * @return Date
	 */
	static public Date addMonths(Date date, int months) {
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, months);
		return calendar.getTime();
	}

	/**
	 * 日期加指定天数
	 * 
	 * @param date
	 * @param days
	 * @return Date
	 */
	static public Date addDays(Date date, int days) {
		calendar.setTime(date);
		calendar.add(Calendar.DATE, days);
		return calendar.getTime();
	}

	/**
	 * 得到时间间的花费时间
	 * 
	 * @param startDate
	 * @param endDate
	 * @return String
	 */
	static public String calculateTimesStringOfDatesBetween(Date startDate,
			Date endDate) {

		long costTime = (endDate.getTime() - startDate.getTime()) / 1000;
		String costTimeStr = null;

		if (costTime < 60)
			costTimeStr = costTime + "秒";
		else if (costTime < 3600)
			costTimeStr = (long) (costTime / 60) + "分" + costTime % 60 + "秒";
		else
			costTimeStr = (long) (costTime / 3600)
					+ "小时"
					+ (long) ((costTime - ((long) (costTime / 3600)) * 3600) / 60)
					+ "分" + costTime % 60 + "秒";
		return costTimeStr;
	}

	/**
	 * 格式化日期：yyyy-MM-dd
	 * 
	 * @param date
	 * @return String
	 */
	static public String formatDate(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(date);
	}

	/**
	 * 格式化日期：yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 * @return String
	 */
	static public String formatDateTime(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String datet="";
		if(date!=null){
			datet=dateFormat.format(date);
		}
		return datet;
	}

	/**
	 * 按日期格式定义格式化日期
	 * 
	 * @param date
	 * @param format
	 * @return String
	 */
	static public String formatDateTime(Date date, String format) {
		if ("DATETIME".equals(format)) {
			format = "yyyy-MM-dd HH:mm:ss";
		} else if ("DATETIME".equals(format)) {
			format = "yyyy-MM-dd";
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}
	
	/**
	 * @description
	 * @param dateStr
	 * @return}
	 */
	static public Date getDateTFromString(String dateStr) {
		if(dateStr != null && dateStr.length()==10){
			dateStr = dateStr + " 00:00:00";
		}
		if(dateStr != null && dateStr.length()==13){
			dateStr = dateStr + ":00:00";
		}
		if(dateStr != null && dateStr.length()==16){
			dateStr = dateStr + ":00";
		}
		return getDateFromString(dateStr, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * @description
	 * @param dateStr
	 * @return}
	 */
	static public Date getDateTimeFromString(String dateStr) {
		return getDateFromString(dateStr, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * @description
	 * @param dateStr
	 * @return}
	 */
	static public Date getDateFromString(String dateStr) {
		return getDateFromString(dateStr, "yyyy-MM-dd");
	}

	/**
	 * 从日期格式串返回日期
	 * 
	 * @param dateStr
	 *            日期格式串
	 * @param format
	 *            日期格式定义
	 * @return
	 */
	static public Date getDateFromString(String dateStr, String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		try {
			// 处理js传入的日期类型字符串格式 如2008-09-02T21:22:12
			if (dateStr.indexOf('T') > 0) {
				dateStr = dateStr.replace('T', ' ');
			}
			if (dateStr.trim().equals(""))
				return null;
			return dateFormat.parse(dateStr);
		} catch (ParseException e) {
			throw new IllegalArgumentException("Could not parse date: "
					+ e.getMessage());
		}
	}

	/**
	 * @description
	 * @param dateStr
	 * @param sign
	 * @param format
	 * @return}
	 */
	static public Date getDateFromAllString(String dateStr, String sign,
			String format) {
		if (dateStr.trim().equals(""))
			return null;
		int i = dateStr.indexOf(sign);
		StringBuffer sb = new StringBuffer(dateStr.substring(0, i));
		String formatStr = sb.append(" ").append(
				dateStr.substring(i + 1, dateStr.length())).toString();
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		try {
			if (dateStr.trim().equals(""))
				return null;
			return dateFormat.parse(formatStr);
		} catch (ParseException e) {
			throw new IllegalArgumentException("Could not parse date: "
					+ e.getMessage());
		}
	}

	/**
	 * 生成用于文件名称的日期字符串
	 * 
	 * @param date
	 * @return
	 */
	static public String formatDateTimeForFileName(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd-HH-mm-ss");
		return dateFormat.format(date);
	}

	/**
	 * 返回时间段,暂定为20--20
	 * 
	 * @param date
	 * @return Date[] size=2,begin and end date
	 */
	static public Date[] getScopeDate(Date date) {
		Date[] row = new Date[2];
		Long y = getYearFromDate(date);
		Long m = getMonthFromDate(date);
		Date date1 = getDateFromYearMonthDay(y, new Long(m.longValue() - 1),
				new Long(20));
		Date date2 = getDateFromYearMonthDay(y, m, new Long(20));
		row[0] = date1;
		row[1] = date2;
		return row;
	}
	
	public static Date getCurrentTime(){
		return new Date();
	}
	public static String getCurrentTimeFormat(String format){
		return DateUtils.formatDateTime(new Date(), format);
	}
	
	//判断指定年份是否为闰年
	public static  boolean isLeapYear(Integer year){
		return  ((year % 100 == 0) && (year % 400 == 0)) || ((year % 100 != 0) && (year % 4 == 0));
	}

	//获取指定月份的天数
	public static int daysOfMonth(Integer year,int month){
		if(month == 1 || month==3 || month==5 ||month==7 ||month==8 ||month==10 ||month==12){
			return 31;
		}
		if(month == 4 || month==6 || month==9 ||month==11){
			return 30;
		}
		if(month == 2 && isLeapYear(year)){
			return 29;
		}
		if(month == 2 && !isLeapYear(year)){
			return 28;
		}
		return 0;
	} 
	
	public static int getLastDateOfMonth(Integer year,int month){
		return daysOfMonth(year, month);
	}
	  /**获取是否是两个日期之间的时间
	   *  time3是否在   time1~time2之间
	   *  @return  false(不在) true（在）
	   * */
	  public static boolean DateTimeCompare(String time1,String time2 ,String time3) { 
		      DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		      Date d1;
		      Date d2;
		      Date d3;
				try {
					d1 = df.parse(time1);
					d2 = df.parse(time2);
					d3 = df.parse(time3);
					 return (d3.getTime() - d1.getTime()>0&&d2.getTime() - d3.getTime()>0);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return false;
	  }
	  /**获取是否是两个日期之间的时间
	   *  time3是否在   time1~time2之间
	   *  @return  false(不在) true（在）
	   * */
	  public static boolean TimeCompare(String time1,String time2 ,String time3) { 
		      DateFormat df = new SimpleDateFormat("HH:mm:ss");
		      Date d1;
		      Date d2;
		      Date d3;
				try {
					d1 = df.parse(time1);
					d2 = df.parse(time2);
					d3 = df.parse(time3);
					 return (d3.getTime() - d1.getTime()>0&&d2.getTime() - d3.getTime()>0);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				return false;
	  }
	  /**
		 * 取得系统当前日期,并指定格式化类型
		 * @param dateFormatStr
		 * @return
		 */
		public static String getDateStr(String dateFormatStr) {
			SimpleDateFormat sdf=new SimpleDateFormat();
			sdf.applyPattern(dateFormatStr);
			return sdf.format(new Date());
		}
		
		/**
	     * 返回自己生成的流水号flowno,生成原理为yyyymmddhhmmss+豪秒+"11"
	     * 
	     * @return 生成的流水号字符串
	     */
	    public static String getCurrentAllTime() {
	    	return String.format("%1$tY%1$tm%1$td%1$tH%1$tM%1$tS%1$tL11", System.currentTimeMillis());
	    }
	  
	  public static String getDateCode(String prevStr){
		  Calendar nowtime = new GregorianCalendar();  
		  String strDateTime = String.format("%04d", nowtime.get(Calendar.YEAR))+  
				  String.format("%02d", nowtime.get(Calendar.MONTH)+1)+
				  String.format("%02d", nowtime.get(Calendar.DATE))+ 
	              String.format("%02d", nowtime.get(Calendar.HOUR))+ 
	              String.format("%02d", nowtime.get(Calendar.MINUTE))+ 
	              String.format("%02d", nowtime.get(Calendar.SECOND))+ 
	              String.format("%03d", nowtime.get(Calendar.MILLISECOND));
		  return prevStr == null ? strDateTime : prevStr + strDateTime;
	  }
	  
	/**
	 * 日期转星期
	 * @param datetime
	 * @return
	 */
	public static String dateToWeek(String datetime) {
	    SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
	    String[] weekDays = { "sunday", "monday", "tuesday", "wednesday", "thursday", "friday", "saturday" };
	    Calendar cal = Calendar.getInstance(); // 获得一个日历
	    Date datet = null;
	    try {
	        datet = f.parse(datetime);
	        cal.setTime(datet);
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
	    int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。
	    if (w < 0)
	        w = 0;
	    return weekDays[w];
	}
	
	/*两个日期之间相差的秒数*/
	public static int calLastedTime(Date s1Date,Date s2Date) {
		long a=s1Date.getTime();
	    long b=s2Date.getTime();
	    int c=(int)((a-b)/1000);
	    return c;
	}
	  
	  public static void main(String[] args) {
		  //System.out.println("*********"+TimeCompare("01:01:01", "05:05:05", "06:03:03"));
		  //System.out.println(getDateCode("KJ"));//课件编号
		  System.out.println(DateUtils.dateToWeek("2018-03-06"));
	  }
}
