package xyz.yhsj.yhutils.tools.io;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.util.EncodingUtils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;

/**
 * Asset 工具类<br>
 * 
 */
@SuppressWarnings("deprecation")
public class AssetUtils {

	/**
	 * 打开Asset下的文件
	 * 
	 * @param fileName
	 *            文件名
	 * @return
	 */
	public static InputStream openAssetFile(Context context, String fileName) {
		AssetManager am = context.getAssets();
		InputStream is = null;
		try {
			is = am.open(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return is;
	}

	/**
	 * 从assets 文件夹中读取文本数据
	 */
	public static String getTextFromAssets(final Context context,
			String fileName) {
		String result = "";
		try {
			InputStream in = context.getResources().getAssets().open(fileName);
			// 获取文件的字节数
			int lenght = in.available();
			// 创建byte数组
			byte[] buffer = new byte[lenght];
			// 将文件中的数据读到byte数组中
			in.read(buffer);
			result = EncodingUtils.getString(buffer, "UTF-8");
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 从assets 文件夹中读取图片
	 */
	public static Drawable loadImageFromAsserts(final Context ctx,
			String fileName) {
		try {
			InputStream is = ctx.getResources().getAssets().open(fileName);
			return Drawable.createFromStream(is, null);
		} catch (IOException e) {
			if (e != null) {
				e.printStackTrace();
			}
		} catch (OutOfMemoryError e) {
			if (e != null) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			if (e != null) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
