package xyz.yhsj.yhutils.tools.string;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import xyz.yhsj.yhutils.util.LogUtils;

public class DateUtils {

	private final static long minute = 60 * 1000;// 1分钟
	private final static long hour = 60 * minute;// 1小时
	private final static long day = 24 * hour;// 1天
	private final static long month = 31 * day;// 月
	private final static long year = 12 * month;// 年

	/** 日期格式：yyyy-MM-dd HH:mm:ss **/
	public static final String DF_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

	/** 日期格式：yyyy-MM-dd hh:mm:ss **/
	public static final String DF_YYYY_MM_DD_h_MM_SS = "yyyy-MM-dd hh:mm:ss";

	/** 日期格式：yyyy-MM-dd HH:mm **/
	public static final String DF_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";

	/** 日期格式：yyyy-MM-dd **/
	public static final String DF_YYYY_MM_DD = "yyyy-MM-dd";

	/** 日期格式：HH:mm:ss **/
	public static final String DF_HH_MM_SS = "HH:mm:ss";

	/** 日期格式：HH:mm **/
	public static final String DF_HH_MM = "HH:mm";

	/**
	 * 将日期格式化成友好的字符串：几分钟前、几小时前、几天前、几月前、几年前、刚刚
	 * 
	 * @param date
	 * @return
	 */
	public static String formatFriendly(Date date) {
		if (date == null) {
			return null;
		}
		long diff = new Date().getTime() - date.getTime();
		long r = 0;
		if (diff > year) {
			r = (diff / year);
			return r + "年前";
		}
		if (diff > month) {
			r = (diff / month);
			return r + "个月前";
		}
		if (diff > day) {
			r = (diff / day);
			return r + "天前";
		}
		if (diff > hour) {
			r = (diff / hour);
			return r + "个小时前";
		}
		if (diff > minute) {
			r = (diff / minute);
			return r + "分钟前";
		}
		return "刚刚";
	}

	/**
	 * 获取当前时间戳
	 * 
	 * @return 时间戳
	 */
	public static long getCurrentTime() {
		Calendar calendar = Calendar.getInstance();
		return calendar.getTimeInMillis();
	}

	/**
	 * 格式化12小时制 格式：yyyy-MM-dd hh-mm-ss
	 * 
	 * @param time
	 *            时间
	 * @return
	 */
	public static String format12Time(long time) {
		return format(time, DF_YYYY_MM_DD_h_MM_SS);
	}

	/**
	 * 格式化24小时制 格式：yyyy-MM-dd HH-MM-ss
	 * 
	 * @param time
	 *            时间
	 * @return
	 */
	public static String format24Time(long time) {
		return format(time, DF_YYYY_MM_DD_HH_MM_SS);
	}

	/**
	 * 得到当前时间
	 * 
	 * @return
	 */
	public static Date getThisTime() {
		return new Date();
	}

	/**
	 * 获取系统当前日期
	 * 
	 * @return
	 */
	public static String getThisTime(String formater) {
		return format(getCurrentTime(), formater);
	}

	/**
	 * 将日期字符串转成日期
	 * 
	 * @param strDate
	 *            日期字符串
	 * @param format
	 *            格式
	 * @return
	 */
	public static Date parseDate(String strDate, String format) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		Date returnDate = null;
		try {
			returnDate = dateFormat.parse(strDate);
		} catch (ParseException e) {
			LogUtils.e("parseDate failed !");

		}
		return returnDate;

	}

	/**
	 * 验证日期是否比当前日期早
	 * 
	 * @param target1
	 *            比较时间1
	 * @param target2
	 *            比较时间2
	 * @return true 则代表target1比target2晚或等于target2，否则比target2早
	 */
	public static boolean compareDate(Date target1, Date target2) {
		boolean flag = false;
		try {
			String target1DateTime = format(target1, DF_YYYY_MM_DD_HH_MM_SS);
			String target2DateTime = format(target2, DF_YYYY_MM_DD_HH_MM_SS);
			if (target1DateTime.compareTo(target2DateTime) <= 0) {
				flag = true;
			}
		} catch (Exception e1) {
			System.out.println("比较失败，原因：" + e1.getMessage());
		}
		return flag;
	}

	/**
	 * 得到某个时间点 正数为当前时间之后,负数为当前时间之前
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @param hour
	 * @param minute
	 * @param second
	 * @param format
	 * @return 自定义
	 */
	public static String getTimePoint(int year, int month, int day, int hour,
			int minute, int second, String format) {
		Calendar c = Calendar.getInstance();

		c.add(Calendar.YEAR, year);
		c.add(Calendar.MONTH, month);
		c.add(Calendar.DAY_OF_MONTH, day);
		c.add(Calendar.HOUR_OF_DAY, hour);
		c.add(Calendar.MINUTE, minute);
		c.add(Calendar.SECOND, second);

		if (format == null) {
			return format(c.getTimeInMillis(), DF_YYYY_MM_DD_HH_MM_SS);
		}
		return format(c.getTimeInMillis(), format);
	}

	/**
	 * 格式化时间,自定义格式
	 * 
	 * @param time
	 *            时间
	 * @param pattern
	 *            格式化时间用的标签
	 * @return
	 */
	public static String format(long time, String formater) {
		SimpleDateFormat sdf = new SimpleDateFormat(formater);
		return sdf.format(new Date(time));
	}

	/**
	 * 将日期以yyyy-MM-dd HH:mm:ss格式化
	 * 
	 * @param dateL
	 *            日期
	 * @return
	 */
	public static String format(Date date, String formater) {
		SimpleDateFormat sdf = new SimpleDateFormat(formater);
		return sdf.format(date);
	}
}
