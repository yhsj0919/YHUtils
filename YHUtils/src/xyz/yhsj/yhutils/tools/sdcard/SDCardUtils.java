package xyz.yhsj.yhutils.tools.sdcard;

import java.io.File;

import xyz.yhsj.yhutils.util.LogUtils;
import android.os.Environment;
import android.os.StatFs;

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
	 * 获取SD卡的剩余容量 单位byte
	 */
	public static long getSDCardAllSize() {
		if (isSDCardEnable()) {
			StatFs stat = new StatFs(getSDCardPath());
			// 获取空闲的数据块的数量
			@SuppressWarnings("deprecation")
			long availableBlocks = (long) stat.getAvailableBlocks() - 4;
			// 获取单个数据块的大小（byte）
			@SuppressWarnings("deprecation")
			long freeBlocks = stat.getAvailableBlocks();
			long result = freeBlocks * availableBlocks;
			LogUtils.i("当前内存卡的容量：" + result);
			return result;
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
	public boolean isSDCardSizeOverflow() {
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
