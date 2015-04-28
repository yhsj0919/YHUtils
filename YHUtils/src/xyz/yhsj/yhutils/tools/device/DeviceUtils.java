package xyz.yhsj.yhutils.tools.device;

import xyz.yhsj.yhutils.util.LogUtils;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;

/**
 * 获取手机信息工具类<br>
 * 需要"android.permission.READ_PHONE_STATE"权限
 *
 */
public class DeviceUtils {

	/**
	 * 获取应用程序的IMEI号
	 */
	public static String getIMEI(Context context) {
		if (context == null) {
			LogUtils.e("getIMEI  context为空");
		}
		TelephonyManager telecomManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		String imei = telecomManager.getDeviceId();
		LogUtils.i("IMEI标识：" + imei);
		return imei;
	}

	/**
	 * 获取设备的系统版本号
	 */
	public static int getDeviceSDK() {
		int sdk = android.os.Build.VERSION.SDK_INT;
		LogUtils.i("设备版本：" + sdk);
		return sdk;
	}

	/**
	 * 获取设备的型号
	 */
	public static String getDeviceName() {
		String model = android.os.Build.MODEL;
		LogUtils.i("设备型号：" + model);
		return model;
	}

	/**
	 * 得到本机Ip地址
	 * 需要"android.permission.ACCESS_WIFI_STATE"权限
	 * @param ctx
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getIp(Context ctx) {
		return Formatter.formatIpAddress(((WifiManager) ctx
				.getSystemService(Context.WIFI_SERVICE)).getConnectionInfo()
				.getIpAddress());
	}
}
