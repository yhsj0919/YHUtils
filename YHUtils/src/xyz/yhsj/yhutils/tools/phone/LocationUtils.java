package xyz.yhsj.yhutils.tools.phone;

import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

public class LocationUtils {

	/**
	 * 是否允许模拟定位
	 * 
	 * @param context
	 * @return
	 */
	public static boolean allow_mock_location(Context context) {

		boolean isOpen = Settings.Secure.getInt(context.getContentResolver(),
				Settings.Secure.ALLOW_MOCK_LOCATION, 0) != 0;
		return isOpen;

	}

	/**
	 * 判断GPS是否打开
	 * 
	 * @return
	 */
	public static boolean isGpsOPen(Context context) {
		LocationManager locationManager = (LocationManager) context
				.getSystemService(Context.LOCATION_SERVICE);
		// 通过GPS卫星定位，定位级别可以精确到街（通过24颗卫星定位，在室外和空旷的地方定位准确、速度快）
		boolean isGpsOkay = locationManager
				.isProviderEnabled(LocationManager.GPS_PROVIDER);
		if (isGpsOkay) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 强制打开GPS
	 * 
	 * @param context
	 */
	public static void forceOpenGPS(Context context) {
		// 4.0++
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
			Intent intent = new Intent("android.location.GPS_ENABLED_CHANGE");
			intent.putExtra("enabled", true);
			context.sendBroadcast(intent);

			String provider = Settings.Secure.getString(
					context.getContentResolver(),
					Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
			if (!provider.contains("gps")) { // if gps is disabled
				final Intent poke = new Intent();
				poke.setClassName("com.android.settings",
						"com.android.settings.widget.SettingsAppWidgetProvider");
				poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
				poke.setData(Uri.parse("3"));
				context.sendBroadcast(poke);
			}
		} else {
			Intent GPSIntent = new Intent();
			GPSIntent.setClassName("com.android.settings",
					"com.android.settings.widget.SettingsAppWidgetProvider");
			GPSIntent.addCategory("android.intent.category.ALTERNATIVE");
			GPSIntent.setData(Uri.parse("custom:3"));
			try {
				PendingIntent.getBroadcast(context, 0, GPSIntent, 0).send();
			} catch (CanceledException e) {
			}
		}
	}

	/**
	 * 跳转到系统设置页面让用户自己打开GPS
	 * 
	 * @param context
	 */
	public static void openGpsSetting(Context context) {
		try {
			context.startActivity(new Intent(
					Settings.ACTION_LOCATION_SOURCE_SETTINGS));
		} catch (Exception e) {
			context.startActivity(new Intent(Settings.ACTION_SETTINGS));
		}
	}

}
