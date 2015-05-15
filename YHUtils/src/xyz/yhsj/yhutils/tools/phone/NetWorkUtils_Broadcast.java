package xyz.yhsj.yhutils.tools.phone;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

/**
 * 用于实时监测网络变化的工具类，用广播用，使用时请注意注册广播和设置监听器
 * 
 * Created by LOVE on 2015/5/5 005.
 */
public class NetWorkUtils_Broadcast {

	private static NetWorkUtils_Broadcast netWork_broadcast;

	private NetworkBroadcastReceiver networkBroadcastReceiver;

	private Context context;

	private OnNetChengeListener listener;

	private NetWorkUtils_Broadcast(Context context) {
		networkBroadcastReceiver = new NetworkBroadcastReceiver();
		this.context = context;
	}

	/**
	 * 得到单例对象
	 *
	 * @param context
	 * @return
	 */
	public static synchronized NetWorkUtils_Broadcast getInstance(Context context) {
		if (netWork_broadcast == null) {
			netWork_broadcast = new NetWorkUtils_Broadcast(context);
		}
		return netWork_broadcast;
	}

	/**
	 * 注册网络状态监听广播
	 */
	public void registerNetworkReceiver() {
		IntentFilter filter = new IntentFilter(
				ConnectivityManager.CONNECTIVITY_ACTION);
		context.registerReceiver(networkBroadcastReceiver, filter);
	}

	/**
	 * 解除网络状态广播监听
	 */
	public void unRegisterNetworkReceiver() {
		context.unregisterReceiver(networkBroadcastReceiver);
	}

	/**
	 * 设置网络状态变化监听器
	 *
	 * @param listener
	 */
	public void setListener(OnNetChengeListener listener) {
		this.listener = listener;
	}

	/**
	 * 网络状态改变回调接口
	 */
	public static interface OnNetChengeListener {
		void OnNetChenged(String info);
	}

	/**
	 * 网络监听广播
	 */
	class NetworkBroadcastReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {

			if (listener != null) {
				listener.OnNetChenged(NetWorkUtils.getNetworkTypeName(context));
			}
		}
	}

}
