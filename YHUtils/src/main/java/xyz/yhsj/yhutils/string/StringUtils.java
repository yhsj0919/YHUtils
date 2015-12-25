package xyz.yhsj.yhutils.string;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.annotation.SuppressLint;

/**
 * @author LOVE
 */
public final class StringUtils {

    /**
     * 判断字符串是否为null或""
     *
     * @param str
     * @return 为空或null返回true，否则返回false
     */
    public static boolean isEmpty(String str) {
        return (str == null || str.trim().length() == 0);
    }

    /**
     * 获取字符串长度
     */
    public static int length(CharSequence str) {
        return str == null ? 0 : str.length();
    }


    /**
     * utf-8编码
     */
    public static String utf8Encode(String str) {
        if (!isEmpty(str) && str.getBytes().length != str.length()) {
            try {
                return URLEncoder.encode(str, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("UnsupportedEncodingException occurred. ", e);
            }
        }
        return str;
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
     * @param count
     * @param string
     * @return
     */
    public static int chinese_2_StringLenth(String string, int count) {
        int result = 0;
        int displayWidth = count * 2;
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

    /**
     * Unicode字符转为汉字
     *
     * @param ori
     * @return
     */
    public static String Unicode_2_String(String ori) {
        char aChar;
        int len = ori.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len; ) {
            aChar = ori.charAt(x++);
            if (aChar == '\\') {
                aChar = ori.charAt(x++);
                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = ori.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException(
                                        "Malformed   \\uxxxx   encoding.");
                        }
                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';
                    else if (aChar == 'n')
                        aChar = '\n';
                    else if (aChar == 'f')
                        aChar = '\f';
                    outBuffer.append(aChar);
                }
            } else
                outBuffer.append(aChar);

        }
        return outBuffer.toString();
    }

    /**
     * 全角转半角
     *
     * @param s
     * @return
     */
    public static String full_2_Half(String s) {
        if (isEmpty(s)) {
            return s;
        }

        char[] source = s.toCharArray();
        for (int i = 0; i < source.length; i++) {
            if (source[i] == 12288) {
                source[i] = ' ';
                // } else if (source[i] == 12290) {
                // source[i] = '.';
            } else if (source[i] >= 65281 && source[i] <= 65374) {
                source[i] = (char) (source[i] - 65248);
            } else {
                source[i] = source[i];
            }
        }
        return new String(source);
    }

    /**
     * 半角转全角
     *
     * @param s
     * @return
     */
    public static String half_2_Full(String s) {
        if (isEmpty(s)) {
            return s;
        }

        char[] source = s.toCharArray();
        for (int i = 0; i < source.length; i++) {
            if (source[i] == ' ') {
                source[i] = (char) 12288;
                // } else if (source[i] == '.') {
                // source[i] = (char)12290;
            } else if (source[i] >= 33 && source[i] <= 126) {
                source[i] = (char) (source[i] + 65248);
            } else {
                source[i] = source[i];
            }
        }
        return new String(source);
    }

    /**
     * 数据库字符转义
     *
     * @param keyWord
     * @return
     */
    public static String sqliteEscape(String keyWord) {
        keyWord = keyWord.replace("/", "//");
        keyWord = keyWord.replace("'", "''");
        keyWord = keyWord.replace("[", "/[");
        keyWord = keyWord.replace("]", "/]");
        keyWord = keyWord.replace("%", "/%");
        keyWord = keyWord.replace("&", "/&");
        keyWord = keyWord.replace("_", "/_");
        keyWord = keyWord.replace("(", "/(");
        keyWord = keyWord.replace(")", "/)");
        return keyWord;
    }
}
