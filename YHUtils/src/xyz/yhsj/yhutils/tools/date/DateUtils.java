package xyz.yhsj.yhutils.tools.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

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
	 * 格式化12小时制<br>
	 * 格式：yyyy-MM-dd hh-MM-ss
	 * 
	 * @param time
	 *            时间
	 * @return
	 */
	public static String format12Time(long time) {
		return format(time, "yyyy-MM-dd hh:MM:ss");
	}

	/**
	 * 格式化24小时制<br>
	 * 格式：yyyy-MM-dd HH-MM-ss
	 * 
	 * @param time
	 *            时间
	 * @return
	 */
	public static String format24Time(long time) {
		return format(time, "yyyy-MM-dd HH:MM:ss");
	}

	/**
	 * 得到当前时间yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 */
	public static String getThisTime() {
		Calendar c = Calendar.getInstance();
		return format(c.getTimeInMillis(), "yyyy-MM-dd HH:MM:ss");
	}

	/**
	 * 得到某个时间点 正数为某个时间之后,负数为某个时间之前
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
			return format(c.getTimeInMillis(), "yyyy-MM-dd HH:MM:ss");
		}
		return format(c.getTimeInMillis(), format);
	}

	/**
	 * 格式化时间,自定义标签
	 * 
	 * @param time
	 *            时间
	 * @param pattern
	 *            格式化时间用的标签
	 * @return
	 */
	public static String format(long time, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(new Date(time));
	}

	/**
	 * 获取当前天
	 * 
	 * @return 天
	 */
	@SuppressWarnings("static-access")
	public static int getCurrentDay() {
		Calendar calendar = Calendar.getInstance();
		return calendar.DAY_OF_MONTH;
	}

	/**
	 * 获取当前月
	 * 
	 * @return 月
	 */
	@SuppressWarnings("static-access")
	public static int getCurrentMonth() {
		Calendar calendar = Calendar.getInstance();
		return calendar.MONTH;
	}

	/**
	 * 获取当前年
	 * 
	 * @return 年
	 */
	@SuppressWarnings("static-access")
	public static int getCurrentYear() {
		Calendar calendar = Calendar.getInstance();
		return calendar.YEAR;
	}
}
