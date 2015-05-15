package xyz.yhsj.yhutils.tools.io;

import java.io.File;

import xyz.yhsj.yhutils.util.LogUtils;
import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.text.format.Formatter;

/**
 * 内存卡 工具类<br>
 *
 */
public class SDCardUtils {

	/**
	 * 判断SDCard是否可用
	 * 
	 */
	public static boolean isSDCardEnable() {
		boolean result = Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED);
		if (result)
			LogUtils.i("当前内存卡有效");
		else
			LogUtils.i("当前内存卡无效");

		return result;

	}

	/**
	 * 获取SD卡路径
	 * 
	 * @return
	 */
	public static String getSDCardPath() {
		String path = Environment.getExternalStorageDirectory()
				.getAbsolutePath() + File.separator;
		LogUtils.i("当前内存卡路径" + path);
		return path;
	}

	/**
	 * 获取外部存储设备的总空间大小
	 */
	public static long getSDCardTotalSize(Context context) {
		if (isSDCardEnable()) {
			// 得到一个外部存储设备的目录/通过getPath得到路径
			File path = Environment.getExternalStorageDirectory();
			// 文件系统的帮助类，传入一个路径可以得到路径的信息
			StatFs stat = new StatFs(path.getPath());
			// 得到该存储空间每一块存储空间的大小
			long blockSize = stat.getBlockSize();
			// 得到空间总个数
			long totalBlocks = stat.getBlockCount();

			// 得到总空间大小
			long totalSize = totalBlocks * blockSize;

			// String totalStr = Formatter.formatFileSize(context, totalSize);

			return totalSize;
		}
		return 0;
	}

	/**
	 * 获取外部存储设备的剩余空间大小
	 */
	public static long getSDCardAvailSize(Context context) {
		if (isSDCardEnable()) {
			// 得到一个外部存储设备的目录/通过getPath得到路径
			File path = Environment.getExternalStorageDirectory();
			// 文件系统的帮助类，传入一个路径可以得到路径的信息
			StatFs stat = new StatFs(path.getPath());
			// 得到该存储空间每一块存储空间的大小
			long blockSize = stat.getBlockSize();
			// 得到可用的空间个数
			long availableBlocks = stat.getAvailableBlocks();
			// 得到可用空间大小
			long availSize = availableBlocks * blockSize;
			// String availStr = Formatter.formatFileSize(context, availSize);
			return availSize;
		}
		return 0;
	}

	/**
	 * 获取内部存储设备的总空间大小
	 * 
	 * @param context
	 * @return
	 */
	public static long getRomSpaceTotalSize(Context context) {
		if (isSDCardEnable()) {
			// 得到一个内部存储设备的目录/通过getPath得到路径
			File path = Environment.getDataDirectory();
			// 文件系统的帮助类，传入一个路径可以得到路径的信息
			StatFs stat = new StatFs(path.getPath());
			// 得到该存储空间每一块存储空间的大小
			long blockSize = stat.getBlockSize();
			// 得到空间总个数
			long totalBlocks = stat.getBlockCount();
			// 得到总空间大小
			long totalSize = totalBlocks * blockSize;

			// String totalStr = Formatter.formatFileSize(context, totalSize);

			return totalSize;
		}
		return 0;
	}

	/**
	 * 获取内部存储设备的剩余空间大小
	 * 
	 * @param context
	 * @return
	 */
	public static long getRomSpaceAvailSize(Context context) {
		if (isSDCardEnable()) {
			// 得到一个内部存储设备的目录/通过getPath得到路径
			File path = Environment.getDataDirectory();
			// 文件系统的帮助类，传入一个路径可以得到路径的信息
			StatFs stat = new StatFs(path.getPath());
			// 得到该存储空间每一块存储空间的大小
			long blockSize = stat.getBlockSize();
			// 得到可用的空间个数
			long availableBlocks = stat.getAvailableBlocks();
			// 得到可用空间大小
			long availSize = availableBlocks * blockSize;

			// String availStr = Formatter.formatFileSize(context, availSize);
			return availSize;
		}
		return 0;
	}

	/**
	 * 获取系统存储路径
	 * 
	 * @return
	 */
	public static String getRootDirectoryPath() {
		String path = Environment.getRootDirectory().getAbsolutePath();
		LogUtils.i("当前存储路径：" + path);
		return path;
	}

	/**
	 * 判断SDCard是否已满
	 * 
	 * @return
	 */
	@SuppressWarnings({ "deprecation", "unused" })
	public static boolean isSDCardSizeOverflow() {
		boolean result = false;
		// 取得SDCard当前的状态
		String sDcString = android.os.Environment.getExternalStorageState();

		if (sDcString.equals(android.os.Environment.MEDIA_MOUNTED)) {

			// 取得sdcard文件路径
			File pathFile = android.os.Environment
					.getExternalStorageDirectory();
			android.os.StatFs statfs = new android.os.StatFs(pathFile.getPath());

			// 获取SDCard上BLOCK总数
			long nTotalBlocks = statfs.getBlockCount();

			// 获取SDCard上每个block的SIZE
			long nBlocSize = statfs.getBlockSize();

			// 获取可供程序使用的Block的数量
			long nAvailaBlock = statfs.getAvailableBlocks();

			// 获取剩下的所有Block的数量(包括预留的一般程序无法使用的块)
			long nFreeBlock = statfs.getFreeBlocks();

			// 计算SDCard 总容量大小MB
			long nSDTotalSize = nTotalBlocks * nBlocSize / 1024 / 1024;

			// 计算 SDCard 剩余大小MB
			long nSDFreeSize = nAvailaBlock * nBlocSize / 1024 / 1024;
			if (nSDFreeSize <= 1) {
				result = true;
			}
		}// end of if
			// end of func
		return result;
	}

}
