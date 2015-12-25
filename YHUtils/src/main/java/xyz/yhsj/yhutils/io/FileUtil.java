package xyz.yhsj.yhutils.io;

import java.text.DecimalFormat;

/**
 * Created by wangkuan on 15/10/13.
 */
public class FileUtil {

    /**
     * 获取文件的大小
     *
     * @param fileS
     * @return
     */
    public static String FormetFileSize(long fileS) {//转换文件大小
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "0K";
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "K";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }

    public static String FormatFileSizeByMB(long fileS) {//转换文件大小


        return String.format("%.2fM", (double) fileS / 1048576);
    }
}
