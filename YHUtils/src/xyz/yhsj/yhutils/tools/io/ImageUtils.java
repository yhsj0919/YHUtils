package xyz.yhsj.yhutils.tools.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.widget.ImageView;

public class ImageUtils {

	/**
	 * 渐变显示图片
	 * 
	 * @param context
	 * @param imageView
	 * @param bitmap
	 */
	@SuppressWarnings("deprecation")
	public static void setImageBitmap(Context context, ImageView imageView,
			Bitmap bitmap) {
		// Use TransitionDrawable to fade in.
		final TransitionDrawable td = new TransitionDrawable(new Drawable[] {
				new ColorDrawable(android.R.color.transparent),
				new BitmapDrawable(context.getResources(), bitmap) });
		// noinspection deprecation
		// imageView.setBackgroundDrawable(imageView.getDrawable());
		imageView.setImageDrawable(td);
		td.startTransition(200);
	}

	/**
	 * 保存图片
	 * 
	 * @param filePath
	 *            文件路径+文件名
	 * @param content
	 *            文件内容
	 * @throws IOException
	 */
	public static void saveAsJPEG(Bitmap bitmap, String filePath)
			throws IOException {
		FileOutputStream fos = null;

		try {
			File file = new File(filePath);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			fos = new FileOutputStream(file);
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
			fos.flush();
		} finally {
			if (fos != null) {
				fos.close();
			}
		}
	}

	/**
	 * 保存图片
	 * 
	 * @param filePath
	 *            文件路径+文件名
	 * @param content
	 *            文件内容
	 * @throws IOException
	 */
	public static void saveAsPNG(Bitmap bitmap, String filePath)
			throws IOException {
		FileOutputStream fos = null;

		try {
			File file = new File(filePath);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			fos = new FileOutputStream(file);
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
			fos.flush();
		} finally {
			if (fos != null) {
				fos.close();
			}
		}
	}
}
