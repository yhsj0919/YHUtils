package xyz.yhsj.yhutils.tools.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.annotation.SuppressLint;

/**
 * @author LOVE
 *
 */
/**
 * @author LOVE
 *
 */
/**
 * @author LOVE
 *
 */
public final class StringUtils {

	/**
	 * 判断字符串是否为null或""
	 * 
	 * @param string
	 * @return 为空或null返回false，否则返回true
	 */
	@SuppressLint("NewApi")
	public static boolean isNull(String string) {
		if (string == null && "".equals(string)) {
			return false;
		}
		return true;
	}

	public static String join(String[] array, String sep) {
		if (array == null) {
			return null;
		}

		int arraySize = array.length;
		int sepSize = 0;
		if (sep != null && !sep.equals("")) {
			sepSize = sep.length();
		}

		int bufSize = (arraySize == 0 ? 0 : ((array[0] == null ? 16 : array[0]
				.length()) + sepSize) * arraySize);
		StringBuilder buf = new StringBuilder(bufSize);

		for (int i = 0; i < arraySize; i++) {
			if (i > 0) {
				buf.append(sep);
			}
			if (array[i] != null) {
				buf.append(array[i]);
			}
		}
		return buf.toString();
	}

	/**
	 * 转换数组为json格式
	 * 
	 * @param array
	 *            (不允许为空)
	 * @return
	 */
	public static String jsonJoin(String[] array) {
		int arraySize = array.length;
		int bufSize = arraySize * (array[0].length() + 3);
		StringBuilder buf = new StringBuilder(bufSize);
		for (int i = 0; i < arraySize; i++) {
			if (i > 0) {
				buf.append(',');
			}

			buf.append('"');
			buf.append(array[i]);
			buf.append('"');
		}
		return buf.toString();
	}

	/**
	 * 将长字符从截取剩下的用...代替
	 * 
	 * @param input
	 * @param count
	 * @return
	 */
	public static String cutString(String input, int count) {
		return cutString(input, count, null);
	}

	/**
	 * 将长字符从截取剩下的用more代替,more为空则用省略号代替
	 * 
	 * @param input
	 * @param count
	 * @param more
	 * @return
	 */
	public static String cutString(String input, int count, String more) {
		String resultString = "";
		if (input != null) {
			if (more == null) {
				more = "...";
			}
			if (input.length() > count) {
				resultString = input.substring(0, count) + more;
			} else {
				resultString = input;
			}
		}
		return resultString;
	}

	/**
	 * 获得指定中文长度对应的字符串长度，用于截取显示文字，一个中文等于两个英文
	 * 
	 * @param chineseLengthForDisplay
	 * @param content
	 * @return
	 */
	public static int chineseWidth2StringLenth(int chineseLengthForDisplay,
			String string) {
		int result = 0;
		int displayWidth = chineseLengthForDisplay * 2;
		if (string != null) {
			for (char chr : string.toCharArray()) {
				// 中文
				if (chr >= 0x4e00 && chr <= 0x9fbb) {
					displayWidth -= 2;
				} else {
					// 英文
					displayWidth -= 1;
				}
				if (displayWidth <= 0) {
					break;
				}
				result++;
			}
		}
		return result;
	}

	/**
	 * 检测字符串中是否包含汉字
	 * 
	 * @param sequence
	 * @return
	 */
	public static boolean checkChinese(String sequence) {
		final String format = "[\\u4E00-\\u9FA5\\uF900-\\uFA2D]";
		boolean result = false;
		Pattern pattern = Pattern.compile(format);
		Matcher matcher = pattern.matcher(sequence);
		result = matcher.find();
		return result;
	}

}
