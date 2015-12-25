package xyz.yhsj.yhutils.phone;

import xyz.yhsj.yhutils.logutils.LogUtils;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

/**
 * 网络 工具类<Br>
 * 需要android.permission.ACCESS_NETWORK_STATE 权限
 * 
 * @author KEZHUANG
 *
 */
public class NetWorkUtils {

	/**
	 * 判断网络是否连接
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isConnected(Context context) {
		boolean bisConnFlag = false;

		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo network = connectivityManager.getActiveNetworkInfo();
		if (network != null) {
			bisConnFlag = connectivityManager.getActiveNetworkInfo()
					.isAvailable();
		}
		return bisConnFlag;
	}

	/**
	 * 判断是否是wifi连接
	 */
	public static boolean isWifi(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		if (!isConnected(context)) {
			LogUtils.i("当前网络----->不可用");
			return false;
		}
		boolean isWifi = connectivityManager.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI;
		if (isWifi)
			LogUtils.i("当前网络----->WIFI环境");
		else
			LogUtils.i("当前网络----->非WIFI环境");

		return isWifi;

	}

	/**
	 * 打开网络设置界面
	 */
	public static void openSetting(Activity activity) {
		Intent intent = null;
		// 判断手机系统的版本 即API大于10 就是3.0或以上版本
		if (android.os.Build.VERSION.SDK_INT > 10) {
			intent = new Intent(
					android.provider.Settings.ACTION_WIRELESS_SETTINGS);
		} else {
			intent = new Intent();
			ComponentName component = new ComponentName("com.android.settings",
					"com.android.settings.WirelessSettings");
			intent.setComponent(component);
			intent.setAction("android.intent.action.VIEW");
		}
		activity.startActivity(intent);
	}

	/**
	 * 获取网络类型名称
	 * 
	 * @param context
	 * @return
	 */
	public static String getNetworkTypeName(Context context) {
		if (context != null) {
			ConnectivityManager connectMgr = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			if (connectMgr != null) {
				NetworkInfo info = connectMgr.getActiveNetworkInfo();
				if (info != null) {
					switch (info.getType()) {
					case ConnectivityManager.TYPE_WIFI:
						return "WIFI";
					case ConnectivityManager.TYPE_MOBILE:
						return getNetworkTypeName(info.getSubtype());
					}
				}
			}
		}
		return getNetworkTypeName(TelephonyManager.NETWORK_TYPE_UNKNOWN);
	}

	private static String getNetworkTypeName(int type) {
		switch (type) {
		case TelephonyManager.NETWORK_TYPE_GPRS:
			return "GPRS";
		case TelephonyManager.NETWORK_TYPE_EDGE:
			return "EDGE";
		case TelephonyManager.NETWORK_TYPE_UMTS:
			return "UMTS";
		case TelephonyManager.NETWORK_TYPE_HSDPA:
			return "HSDPA";
		case TelephonyManager.NETWORK_TYPE_HSUPA:
			return "HSUPA";
		case TelephonyManager.NETWORK_TYPE_HSPA:
			return "HSPA";
		case TelephonyManager.NETWORK_TYPE_CDMA:
			return "CDMA";
		case TelephonyManager.NETWORK_TYPE_EVDO_0:
			return "CDMA - EvDo rev. 0";
		case TelephonyManager.NETWORK_TYPE_EVDO_A:
			return "CDMA - EvDo rev. A";
		case TelephonyManager.NETWORK_TYPE_EVDO_B:
			return "CDMA - EvDo rev. B";
		case TelephonyManager.NETWORK_TYPE_1xRTT:
			return "CDMA - 1xRTT";
		case TelephonyManager.NETWORK_TYPE_LTE:
			return "LTE";
		case TelephonyManager.NETWORK_TYPE_EHRPD:
			return "CDMA - eHRPD";
		case TelephonyManager.NETWORK_TYPE_IDEN:
			return "iDEN";
		case TelephonyManager.NETWORK_TYPE_HSPAP:
			return "HSPA+";
		default:
			return "UNKNOWN";
		}
	}

}
