package xyz.yhsj.yhutils.tools.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import xyz.yhsj.yhutils.util.LogUtils;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;

/**
 * APP 工具类<br>
 */
public class AppUtils {

	/**
	 * 获得APP的名称
	 * 
	 * @param context
	 * @return
	 */
	public static String getAppName(Context context) {
		if (context == null) {
			return null;
		}
		try {
			PackageManager packageManager = context.getPackageManager();
			PackageInfo packageInfo = packageManager.getPackageInfo(
					context.getPackageName(), 0);
			int labelRes = packageInfo.applicationInfo.labelRes;
			String appName = context.getResources().getString(labelRes);
			LogUtils.i("APP名字：" + appName);
			return appName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取应用程序版本名称号
	 */
	public static String getVersionName(Context context) {
		if (context == null) {
			LogUtils.e("getVersionName  context为空");
			return null;
		}
		try {
			PackageManager packageManager = context.getPackageManager();
			PackageInfo packageInfo = packageManager.getPackageInfo(
					context.getPackageName(), 0);
			String versionCode = packageInfo.versionName;
			LogUtils.i("APP版本：" + versionCode);
			return packageInfo.versionName;

		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取应用程序包名
	 */
	public static String getPackageName(Context context) {
		if (context == null) {
			LogUtils.e("getPackageName  context为空");
			return null;
		}
		String pkgName = context.getPackageName();
		LogUtils.i("APP包名：" + pkgName);
		return pkgName;
	}

	/**
	 * 获取本机语言
	 * 
	 * @return
	 */
	public static String getLanguage() {
		Locale locale = Locale.getDefault();
		String languageCode = locale.getLanguage();
		if (TextUtils.isEmpty(languageCode)) {
			languageCode = "";
		}
		return languageCode;
	}

	/**
	 * 获取城市编号
	 * 
	 * @return
	 */
	public static String getCountry() {
		Locale locale = Locale.getDefault();
		String countryCode = locale.getCountry();
		if (TextUtils.isEmpty(countryCode)) {
			countryCode = "";
		}
		return countryCode;
	}

	/**
	 * 启动APK的默认Activity
	 * 
	 * @param activity
	 * @param packageName
	 */
	public static void startApkActivity(final Context activity,
			String packageName) {
		PackageManager pm = activity.getPackageManager();
		PackageInfo pi;
		try {
			pi = pm.getPackageInfo(packageName, 0);
			Intent intent = new Intent(Intent.ACTION_MAIN, null);
			intent.addCategory(Intent.CATEGORY_LAUNCHER);
			intent.setPackage(pi.packageName);

			List<ResolveInfo> apps = pm.queryIntentActivities(intent, 0);

			ResolveInfo ri = apps.iterator().next();
			if (ri != null) {
				String className = ri.activityInfo.name;
				intent.setComponent(new ComponentName(packageName, className));
				activity.startActivity(intent);
			}
		} catch (NameNotFoundException e) {
			LogUtils.e("startActivity", e);
		}
	}

	/**
	 * 获取应用程序下所有Activity
	 * 
	 * @param activity
	 * @return
	 */
	public static ArrayList<String> getActivities(Context activity) {
		ArrayList<String> result = new ArrayList<String>();
		Intent intent = new Intent(Intent.ACTION_MAIN, null);
		intent.setPackage(activity.getPackageName());
		for (ResolveInfo info : activity.getPackageManager()
				.queryIntentActivities(intent, 0)) {
			result.add(info.activityInfo.name);
		}
		return result;
	}

	/**
	 * 检查有没有应用程序来接受处理你发出的intent
	 * 
	 * @param context
	 * @param action
	 * @return
	 */
	public static boolean isIntentAvailable(Context context, String action) {
		final PackageManager packageManager = context.getPackageManager();
		final Intent intent = new Intent(action);
		List<ResolveInfo> list = packageManager.queryIntentActivities(intent,
				PackageManager.MATCH_DEFAULT_ONLY);
		return list.size() > 0;
	}

	/**
	 * 获取所有app的安装路径
	 * 
	 * @param context
	 * @return ArrayList<HashMap<String, String>> package:包名,url:地址
	 */
	public static ArrayList<HashMap<String, String>> getAllInstelAppUrl(
			Context context) {

		ArrayList<HashMap<String, String>> appInfos = new ArrayList<HashMap<String, String>>();

		PackageManager pm = context.getPackageManager();

		for (ApplicationInfo app : pm.getInstalledApplications(0)) {

			HashMap<String, String> appInfo = new HashMap<String, String>();
			appInfo.put("package", app.packageName);
			appInfo.put("url", app.sourceDir);
			appInfos.add(appInfo);
			LogUtils.d("package: " + app.packageName + ", sourceDir: "
					+ app.sourceDir);
		}

		return appInfos;
	}

}
