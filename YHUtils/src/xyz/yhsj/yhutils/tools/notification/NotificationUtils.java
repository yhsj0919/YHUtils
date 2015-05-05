package xyz.yhsj.yhutils.tools.notification;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class NotificationUtils {

	/**
	 * 发送不会重复的通知
	 * 
	 * @param context
	 * @param title
	 *            标题
	 * @param message
	 *            消息
	 * @param SmallIconId
	 *            图标
	 * @param activity
	 *            要启动的类
	 * @param extras
	 *            传递的参数
	 */
	@SuppressLint("NewApi")
	public static void sendNotification(Context context, String title,
			String message, int SmallIconId, Class<?> activity, Bundle extras) {

		Intent mIntent = new Intent(context, activity);
		mIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		
		if (extras!=null) {
			mIntent.putExtras(extras);
		}
		
		

		int requestCode = (int) System.currentTimeMillis();

		PendingIntent mContentIntent = PendingIntent.getActivity(context,
				requestCode, mIntent, 0);

		Notification mNotification = new Notification.Builder(context)
				.setContentTitle(title).setSmallIcon(SmallIconId)
				.setContentIntent(mContentIntent).setContentText(message)
				.build();
		mNotification.flags |= Notification.FLAG_AUTO_CANCEL;
		mNotification.defaults = Notification.DEFAULT_ALL;

		NotificationManager mNotificationManager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);

		mNotificationManager.notify(requestCode, mNotification);
	}

	/**
	 * 不会被清除的通知
	 * 
	 * @param context
	 * @param title
	 * @param info
	 */
	@SuppressWarnings("deprecation")
	public static void showNotification(Activity context, String title, String info,
			int SmallIconId) {
		NotificationManager barmanager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		Notification notice = new Notification(SmallIconId, title,
				System.currentTimeMillis());

		notice.flags |= Notification.FLAG_ONGOING_EVENT;

		// 将此通知放到通知栏的"Ongoing"即"正在运行"组中
		notice.flags |= Notification.FLAG_NO_CLEAR;

		// 表明在点击了通知栏中的"清除通知"后，此通知不清除，经常与FLAG_ONGOING_EVENT一起使用
		notice.flags |= Notification.FLAG_SHOW_LIGHTS;

		Intent appIntent = new Intent(Intent.ACTION_MAIN);
		// appIntent.setAction(Intent.ACTION_MAIN);
		appIntent.addCategory(Intent.CATEGORY_LAUNCHER);

		appIntent.setComponent(new ComponentName(context.getPackageName(),
				context.getPackageName() + "." + context.getLocalClassName()));

		appIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
				| Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);// 关键的一步，设置启动模式

		PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
				appIntent, 0);

		notice.setLatestEventInfo(context, title, info, contentIntent);

		barmanager.notify(0, notice);
	}

	/**
	 * 清除所有通知
	 * 
	 * @param context
	 */
	public static void cancelNotification(Context context) {

		NotificationManager notificationManager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		notificationManager.cancelAll();
	}

}
