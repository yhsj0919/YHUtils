package xyz.yhsj.yhutils.phone;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.SystemClock;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Locale;

import xyz.yhsj.yhutils.logutils.LogUtils;


/**
 * 获取手机信息工具类<br>
 * 需要"android.permission.READ_PHONE_STATE"权限
 */
public class DeviceUtils {


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
     * 获取国家编号
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
     * 获取App包 信息版本号
     *
     * @param context
     * @return
     */
    public PackageInfo getAppInfo(Context context) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo = null;
        try {
            packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageInfo;
    }

    /**
     * 获取手机的IMEI号
     */
    public static String getIMEI(Context context) {
        if (context == null) {
            LogUtils.e("getIMEI  context为空");
        }
        TelephonyManager telecomManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
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
     * 获取本机IP地址
     *
     * @return
     */
    public static String getLocalIPAddress() {

        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface
                    .getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf
                        .getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException ex) {
            return "0.0.0.0";
        }
        return "0.0.0.0";
    }

    /**
     * 获取 MAC 地址
     * <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>
     */
    public static String getMacAddress(Context context) {
        //wifi mac地址
        WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        String mac = info.getMacAddress();

        return mac;
    }

    /**
     * 获取 开机时间
     */
    public static String getBootTimeString() {
        long ut = SystemClock.elapsedRealtime() / 1000;
        int h = (int) ((ut / 3600));
        int m = (int) ((ut / 60) % 60);

        return h + ":" + m;
    }
}
