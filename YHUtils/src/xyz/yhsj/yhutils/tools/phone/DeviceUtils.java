package xyz.yhsj.yhutils.tools.phone;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;

import com.lidroid.xutils.util.LogUtils;

/**
 * 获取手机信息工具类<br>
 * 需要"android.permission.READ_PHONE_STATE"权限
 *
 */
public class DeviceUtils {

	/**
	 * 获取手机的IMEI号
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
	 * 获取手机IESI号
	 */
	public static String getIESI(Context context) {
		if (context == null) {
			LogUtils.e("getIESI  context为空");
		}
		TelephonyManager telecomManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		String imsi = telecomManager.getSubscriberId();
		LogUtils.i("IESI标识：" + imsi);
		return imsi;
	}

	/**
	 * 获取手机号码
	 */
	public static String getTelNumber(Context context) {
		if (context == null) {
			LogUtils.e("getTelNumber  context为空");
		}
		TelephonyManager telecomManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		String numer = telecomManager.getLine1Number(); // 手机号码，有的可得，有的不可得
		LogUtils.i("手机号码：" + numer);
		return numer;
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
	 * 获取手机品牌
	 */
	public static String getBRAND() {
		String brand = android.os.Build.BRAND;
		LogUtils.i("手机品牌：" + brand);
		return brand;
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
	 * 得到本机Ip地址 需要"android.permission.ACCESS_WIFI_STATE"权限
	 * 
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
