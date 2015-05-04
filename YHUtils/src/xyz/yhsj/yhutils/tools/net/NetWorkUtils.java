package xyz.yhsj.yhutils.tools.net;

import xyz.yhsj.yhutils.util.LogUtils;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.telephony.TelephonyManager;

/**
 * 网络 工具类<Br>
 * 内部已经封装了打印功能,只需要把DEBUG参数改为true即可<br>
 * 如果需要更换tag可以直接更改,默认为KEZHUANG
 * 
 * @author KEZHUANG
 *
 */
public class NetWorkUtils {
	/**
	 * 接受网络状态的广播Action
	 */
	public static final String NET_BROADCAST_ACTION = "com.network.state.action";

	public static final String NET_STATE_NAME = "network_state";

	/**
	 * 实时更新网络状态<br>
	 * -1为网络无连接<br>
	 * 1为WIFI<br>
	 * 2为移动网络<br>
	 */
	public static int CURRENT_NETWORK_STATE = -1;

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
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		if (cm == null) {
			LogUtils.i("当前网络----->不可用");
			return false;
		}
		boolean isWifi = cm.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI;
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
	 * 开启服务,实时监听网络变化 需要自己在清单文件中配置服务<br>
	 * 然后把对应的Action传入<br>
	 * 服务类:android.develop.utils.net.NetService
	 */
	public static void startNetService(Context context, String action) {
		// 注册广播
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(NET_BROADCAST_ACTION);
		context.registerReceiver(mReceiver, intentFilter);
		// 开启服务
		Intent intent = new Intent();
		LogUtils.d("开启网络监听服务");
		intent.setAction(action);
		context.bindService(intent, new ServiceConnection() {

			@Override
			public void onServiceDisconnected(ComponentName name) {

			}

			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {

			}
		}, Context.BIND_AUTO_CREATE);
	}

	// 接受服务上发过来的广播
	private static BroadcastReceiver mReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			if (intent != null) {
				CURRENT_NETWORK_STATE = Integer.parseInt((String) intent
						.getExtras().get(NET_STATE_NAME));
				switch (CURRENT_NETWORK_STATE) {
				case -1:
					LogUtils.d("网络更改为 无网络  CURRENT_NETWORK_STATE ="
							+ CURRENT_NETWORK_STATE);
					break;
				case 1:
					LogUtils.d("网络更改为 WIFI网络  CURRENT_NETWORK_STATE="
							+ CURRENT_NETWORK_STATE);
					break;
				case 2:
					LogUtils.d("网络更改为 移动网络  CURRENT_NETWORK_STATE ="
							+ CURRENT_NETWORK_STATE);
					break;

				default:
					break;
				}
			}
		}

	};

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
