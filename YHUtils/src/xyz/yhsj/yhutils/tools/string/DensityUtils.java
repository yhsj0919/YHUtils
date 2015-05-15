package xyz.yhsj.yhutils.tools.string;

import xyz.yhsj.yhutils.util.LogUtils;
import android.content.Context;
import android.util.TypedValue;

/**
 * 单位转换 工具类<br>
 *
 */
public class DensityUtils {

	/**
	 * dp转px
	 * 
	 */
	public static int dp2px(Context context, float dpVal) {
		int result = (int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, dpVal, context.getResources()
						.getDisplayMetrics());
		LogUtils.i("dp-->px：" + result);
		return result;
	}

	/**
	 * sp转px
	 */
	public static int sp2px(Context context, float spVal) {
		int result = (int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_SP, spVal, context.getResources()
						.getDisplayMetrics());
		LogUtils.i("sp-->px：" + result);
		return result;
	}

	/**
	 * px转dp
	 * 
	 */
	public static int px2dp(Context context, float pxVal) {
		final float scale = context.getResources().getDisplayMetrics().density;
		int result = (int) (pxVal / scale);
		LogUtils.i("px-->dp：" + result);
		return result;
	}

	/**
	 * px转sp
	 */
	public static float px2sp(Context context, float pxVal) {
		int result = (int) (pxVal / context.getResources().getDisplayMetrics().scaledDensity);
		LogUtils.i("px-->sp：" + result);
		return result;
	}

}
