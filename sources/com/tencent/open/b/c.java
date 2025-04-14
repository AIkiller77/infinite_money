package com.tencent.open.b;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.tencent.open.a.f;
import com.tencent.open.utils.e;
import java.util.Locale;

/* compiled from: ProGuard */
public class c {
    static String a = null;
    static String b = null;
    static String c = null;
    private static String d;
    private static String e = null;

    public static String a() {
        try {
            Context a2 = e.a();
            if (null == a2) {
                return "";
            }
            WifiManager wifiManager = (WifiManager) a2.getSystemService("wifi");
            if (wifiManager == null) {
                return "";
            }
            WifiInfo connectionInfo = wifiManager.getConnectionInfo();
            return connectionInfo == null ? "" : connectionInfo.getMacAddress();
        } catch (SecurityException e2) {
            f.b("openSDK_LOG.MobileInfoUtil", "getLocalMacAddress>>>", e2);
            return "";
        }
    }

    public static String a(Context context) {
        StringBuilder sb;
        Context context2 = context;
        if (!TextUtils.isEmpty(d)) {
            return d;
        }
        if (null == context2) {
            return "";
        }
        d = "";
        WindowManager windowManager = (WindowManager) context2.getSystemService("window");
        if (null != windowManager) {
            int width = windowManager.getDefaultDisplay().getWidth();
            int height = windowManager.getDefaultDisplay().getHeight();
            new StringBuilder();
            d = sb.append(width).append("x").append(height).toString();
        }
        return d;
    }

    public static String b() {
        return Locale.getDefault().getLanguage();
    }

    public static String b(Context context) {
        Context context2 = context;
        if (a != null && a.length() > 0) {
            return a;
        }
        if (null == context2) {
            return "";
        }
        try {
            a = ((TelephonyManager) context2.getSystemService("phone")).getDeviceId();
            return a;
        } catch (Exception e2) {
            Exception exc = e2;
            return "";
        }
    }

    public static String c(Context context) {
        Context context2 = context;
        if (b != null && b.length() > 0) {
            return b;
        }
        if (null == context2) {
            return "";
        }
        try {
            b = ((TelephonyManager) context2.getSystemService("phone")).getSimSerialNumber();
            return b;
        } catch (Exception e2) {
            Exception exc = e2;
            return "";
        }
    }

    public static String d(Context context) {
        Context context2 = context;
        if (c != null && c.length() > 0) {
            return c;
        }
        if (null == context2) {
            return "";
        }
        try {
            c = Settings.Secure.getString(context2.getContentResolver(), "android_id");
            return c;
        } catch (Exception e2) {
            Exception exc = e2;
            return "";
        }
    }

    public static String e(Context context) {
        DisplayMetrics displayMetrics;
        StringBuilder sb;
        Context context2 = context;
        try {
            if (e == null) {
                WindowManager windowManager = (WindowManager) context2.getSystemService("window");
                new DisplayMetrics();
                DisplayMetrics displayMetrics2 = displayMetrics;
                windowManager.getDefaultDisplay().getMetrics(displayMetrics2);
                new StringBuilder();
                StringBuilder sb2 = sb;
                StringBuilder append = sb2.append("imei=").append(b(context2)).append('&');
                StringBuilder append2 = sb2.append("model=").append(Build.MODEL).append('&');
                StringBuilder append3 = sb2.append("os=").append(Build.VERSION.RELEASE).append('&');
                StringBuilder append4 = sb2.append("apilevel=").append(Build.VERSION.SDK_INT).append('&');
                String b2 = a.b(context2);
                StringBuilder append5 = sb2.append("network=").append(b2 == null ? "" : b2).append('&');
                StringBuilder append6 = sb2.append("sdcard=").append(Environment.getExternalStorageState().equals("mounted") ? 1 : 0).append('&');
                StringBuilder append7 = sb2.append("display=").append(displayMetrics2.widthPixels).append('*').append(displayMetrics2.heightPixels).append('&');
                StringBuilder append8 = sb2.append("manu=").append(Build.MANUFACTURER).append("&");
                StringBuilder append9 = sb2.append("wifi=").append(a.e(context2));
                e = sb2.toString();
            }
            return e;
        } catch (Exception e2) {
            Exception exc = e2;
            return null;
        }
    }
}
