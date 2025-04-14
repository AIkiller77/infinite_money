package com.tencent.open.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.tencent.connect.common.Constants;
import com.tencent.open.SocialConstants;
import com.tencent.open.a.f;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class k {
    private static String a = "";
    private static String b = "";
    private static String c = "";
    private static String d = "";
    private static int e = -1;
    private static String f;
    private static String g = BinTools.hex;

    /* compiled from: ProGuard */
    public static class a {
        public String a;
        public long b;
        public long c;

        public a(String str, int i) {
            this.a = str;
            this.b = (long) i;
            if (this.a != null) {
                this.c = (long) this.a.length();
            }
        }
    }

    private static char a(int i) {
        int i2 = i & 15;
        return i2 < 10 ? (char) (48 + i2) : (char) (97 + (i2 - 10));
    }

    public static Bundle a(String str) {
        Bundle bundle;
        String str2 = str;
        new Bundle();
        Bundle bundle2 = bundle;
        if (str2 != null) {
            try {
                String[] split = str2.split("&");
                int length = split.length;
                for (int i = 0; i < length; i++) {
                    String[] split2 = split[i].split("=");
                    if (split2.length == 2) {
                        bundle2.putString(URLDecoder.decode(split2[0]), URLDecoder.decode(split2[1]));
                    }
                }
            } catch (Exception e2) {
                Exception exc = e2;
                bundle2 = null;
            }
        }
        return bundle2;
    }

    public static Bundle a(String str, String str2, String str3, String str4, String str5, String str6) {
        return a(str, str3, str4, str2, str5, str6, "", "", "", "", "", "");
    }

    public static Bundle a(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9) {
        Bundle bundle;
        new Bundle();
        Bundle bundle2 = bundle;
        bundle2.putString(Constants.PARAM_PLATFORM, "1");
        bundle2.putString("result", str);
        bundle2.putString("code", str2);
        bundle2.putString("tmcost", str3);
        bundle2.putString("rate", str4);
        bundle2.putString("cmd", str5);
        bundle2.putString("uin", str6);
        bundle2.putString("appid", str7);
        bundle2.putString("share_type", str8);
        bundle2.putString("detail", str9);
        bundle2.putString("os_ver", Build.VERSION.RELEASE);
        bundle2.putString("network", com.tencent.open.b.a.a(e.a()));
        bundle2.putString("apn", com.tencent.open.b.a.b(e.a()));
        bundle2.putString("model_name", Build.MODEL);
        bundle2.putString("sdk_ver", Constants.SDK_VERSION);
        bundle2.putString("packagename", e.b());
        bundle2.putString("app_ver", d(e.a(), e.b()));
        return bundle2;
    }

    public static Bundle a(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12) {
        Bundle bundle;
        new Bundle();
        Bundle bundle2 = bundle;
        bundle2.putString("openid", str);
        bundle2.putString("report_type", str2);
        bundle2.putString("act_type", str3);
        bundle2.putString("via", str4);
        bundle2.putString("app_id", str5);
        bundle2.putString("result", str6);
        bundle2.putString(SocialConstants.PARAM_TYPE, str7);
        bundle2.putString("login_status", str8);
        bundle2.putString("need_user_auth", str9);
        bundle2.putString("to_uin", str10);
        bundle2.putString("call_source", str11);
        bundle2.putString("to_type", str12);
        return bundle2;
    }

    public static String a() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces != null && networkInterfaces.hasMoreElements()) {
                Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                while (true) {
                    if (inetAddresses.hasMoreElements()) {
                        InetAddress nextElement = inetAddresses.nextElement();
                        if (!nextElement.isLoopbackAddress()) {
                            return nextElement.getHostAddress().toString();
                        }
                    }
                }
            }
        } catch (SocketException e2) {
            f.a("openSDK_LOG.Util", "getUserIp SocketException ", e2);
        }
        return "";
    }

    public static final String a(Context context) {
        CharSequence applicationLabel;
        Context context2 = context;
        if (context2 == null || (applicationLabel = context2.getPackageManager().getApplicationLabel(context2.getApplicationInfo())) == null) {
            return null;
        }
        return applicationLabel.toString();
    }

    public static final String a(String str, int i, String str2, String str3) {
        StringBuilder sb;
        StringBuilder sb2;
        String str4 = str;
        int i2 = i;
        String str5 = str2;
        String str6 = str3;
        if (TextUtils.isEmpty(str4)) {
            return "";
        }
        String str7 = "UTF-8";
        if (!TextUtils.isEmpty(str5)) {
            str7 = str5;
        }
        try {
            if (str4.getBytes(str7).length <= i2) {
                return str4;
            }
            int i3 = 0;
            for (int i4 = 0; i4 < str4.length(); i4++) {
                int length = str4.substring(i4, i4 + 1).getBytes(str7).length;
                if (i3 + length > i2) {
                    String substring = str4.substring(0, i4);
                    if (!TextUtils.isEmpty(str6)) {
                        new StringBuilder();
                        substring = sb2.append(substring).append(str6).toString();
                    }
                    return substring;
                }
                i3 += length;
            }
            return str4;
        } catch (Exception e2) {
            new StringBuilder();
            f.e("openSDK_LOG.Util", sb.append("Util.subString has exception: ").append(e2.getMessage()).toString());
            return str4;
        }
    }

    public static String a(byte[] bArr) {
        StringBuilder sb;
        StringBuilder sb2;
        byte[] bArr2 = bArr;
        if (bArr2 == null) {
            return null;
        }
        new StringBuilder(bArr2.length * 2);
        StringBuilder sb3 = sb;
        for (int i = 0; i < bArr2.length; i++) {
            String num = Integer.toString(bArr2[i] & 255, 16);
            if (num.length() == 1) {
                new StringBuilder();
                num = sb2.append("0").append(num).toString();
            }
            StringBuilder append = sb3.append(num);
        }
        return sb3.toString();
    }

    public static JSONObject a(JSONObject jSONObject, String str) {
        StringBuilder sb;
        JSONObject jSONObject2;
        JSONObject jSONObject3 = jSONObject;
        String str2 = str;
        if (jSONObject3 == null) {
            new JSONObject();
            jSONObject3 = jSONObject2;
        }
        if (str2 != null) {
            String[] split = str2.split("&");
            int length = split.length;
            for (int i = 0; i < length; i++) {
                String[] split2 = split[i].split("=");
                if (split2.length == 2) {
                    try {
                        split2[0] = URLDecoder.decode(split2[0]);
                        split2[1] = URLDecoder.decode(split2[1]);
                    } catch (Exception e2) {
                        Exception exc = e2;
                    }
                    try {
                        JSONObject put = jSONObject3.put(split2[0], split2[1]);
                    } catch (JSONException e3) {
                        new StringBuilder();
                        f.e("openSDK_LOG.Util", sb.append("decodeUrlToJson has exception: ").append(e3.getMessage()).toString());
                    }
                }
            }
        }
        return jSONObject3;
    }

    private static void a(Context context, String str, String str2, String str3) {
        Intent intent;
        ComponentName componentName;
        new Intent();
        Intent intent2 = intent;
        new ComponentName(str, str2);
        Intent component = intent2.setComponent(componentName);
        Intent action = intent2.setAction("android.intent.action.VIEW");
        Intent addFlags = intent2.addFlags(1073741824);
        Intent addFlags2 = intent2.addFlags(268435456);
        Intent data = intent2.setData(Uri.parse(str3));
        context.startActivity(intent2);
    }

    public static boolean a(Context context, String str) {
        Context context2 = context;
        String str2 = str;
        try {
            if (f(context2)) {
                a(context2, "com.tencent.mtt", "com.tencent.mtt.MainActivity", str2);
            } else {
                a(context2, "com.android.browser", "com.android.browser.BrowserActivity", str2);
            }
        } catch (Exception e2) {
            Exception exc = e2;
            if (0 != 0) {
                try {
                    a(context2, "com.android.browser", "com.android.browser.BrowserActivity", str2);
                } catch (Exception e3) {
                    Exception exc2 = e3;
                    try {
                        a(context2, "com.google.android.browser", "com.android.browser.BrowserActivity", str2);
                    } catch (Exception e4) {
                        Exception exc3 = e4;
                        try {
                            a(context2, "com.android.chrome", "com.google.android.apps.chrome.Main", str2);
                        } catch (Exception e5) {
                            Exception exc4 = e5;
                            return false;
                        }
                    }
                }
            } else {
                try {
                    a(context2, "com.google.android.browser", "com.android.browser.BrowserActivity", str2);
                } catch (Exception e6) {
                    Exception exc5 = e6;
                    try {
                        a(context2, "com.android.chrome", "com.google.android.apps.chrome.Main", str2);
                    } catch (Exception e7) {
                        Exception exc6 = e7;
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static boolean a(Context context, boolean z) {
        Context context2 = context;
        boolean z2 = z;
        if (d(context2) && h.a(context2, Constants.PACKAGE_QQ_PAD) != null) {
            return true;
        }
        if (!z2) {
            return h.c(context2, "4.1") >= 0 || h.a(context2, Constants.PACKAGE_TIM) != null;
        }
        return h.c(context2, "4.1") >= 0 || h.a(context2, Constants.PACKAGE_TIM) != null;
    }

    public static Bundle b(String str) {
        Bundle bundle;
        URL url;
        try {
            new URL(str.replace("auth://", "http://"));
            URL url2 = url;
            Bundle a2 = a(url2.getQuery());
            a2.putAll(a(url2.getRef()));
            return a2;
        } catch (MalformedURLException e2) {
            MalformedURLException malformedURLException = e2;
            new Bundle();
            return bundle;
        }
    }

    public static void b(Context context, String str) {
        StringBuilder sb;
        StringBuilder sb2;
        Context context2 = context;
        String str2 = str;
        if (null != context2) {
            try {
                PackageInfo packageInfo = context2.getPackageManager().getPackageInfo(str2, 0);
                b = packageInfo.versionName;
                a = b.substring(0, b.lastIndexOf(46));
                d = b.substring(b.lastIndexOf(46) + 1, b.length());
                e = packageInfo.versionCode;
            } catch (PackageManager.NameNotFoundException e2) {
                new StringBuilder();
                f.e("openSDK_LOG.Util", sb2.append("getPackageInfo has exception: ").append(e2.getMessage()).toString());
            } catch (Exception e3) {
                new StringBuilder();
                f.e("openSDK_LOG.Util", sb.append("getPackageInfo has exception: ").append(e3.getMessage()).toString());
            }
        }
    }

    public static boolean b() {
        File file = null;
        if (Environment.getExternalStorageState().equals("mounted")) {
            file = Environment.getExternalStorageDirectory();
        }
        return file != null;
    }

    public static boolean b(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null) {
            return true;
        }
        NetworkInfo[] allNetworkInfo = connectivityManager.getAllNetworkInfo();
        if (allNetworkInfo != null) {
            for (int i = 0; i < allNetworkInfo.length; i++) {
                if (allNetworkInfo[i].isConnectedOrConnecting()) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String c(Context context) {
        Criteria criteria;
        StringBuilder sb;
        Context context2 = context;
        if (null == context2) {
            return "";
        }
        try {
            LocationManager locationManager = (LocationManager) context2.getSystemService("location");
            new Criteria();
            Criteria criteria2 = criteria;
            criteria2.setCostAllowed(false);
            criteria2.setAccuracy(2);
            String bestProvider = locationManager.getBestProvider(criteria2, true);
            if (bestProvider != null) {
                Location lastKnownLocation = locationManager.getLastKnownLocation(bestProvider);
                if (null == lastKnownLocation) {
                    return "";
                }
                double latitude = lastKnownLocation.getLatitude();
                double longitude = lastKnownLocation.getLongitude();
                new StringBuilder();
                f = sb.append(latitude).append("*").append(longitude).toString();
                return f;
            }
        } catch (Exception e2) {
            f.b("openSDK_LOG.Util", "getLocation>>>", e2);
        }
        return "";
    }

    public static String c(Context context, String str) {
        Context context2 = context;
        String str2 = str;
        if (null == context2) {
            return "";
        }
        b(context2, str2);
        return b;
    }

    public static JSONObject c(String str) {
        JSONObject jSONObject;
        URL url;
        try {
            new URL(str.replace("auth://", "http://"));
            URL url2 = url;
            JSONObject a2 = a((JSONObject) null, url2.getQuery());
            JSONObject a3 = a(a2, url2.getRef());
            return a2;
        } catch (MalformedURLException e2) {
            MalformedURLException malformedURLException = e2;
            new JSONObject();
            return jSONObject;
        }
    }

    public static String d(Context context, String str) {
        Context context2 = context;
        String str2 = str;
        if (null == context2) {
            return "";
        }
        b(context2, str2);
        return a;
    }

    public static JSONObject d(String str) throws JSONException {
        JSONObject jSONObject;
        StringBuilder sb;
        String str2 = str;
        if (str2.equals("false")) {
            str2 = "{value : false}";
        }
        if (str2.equals("true")) {
            str2 = "{value : true}";
        }
        if (str2.contains("allback(")) {
            str2 = str2.replaceFirst("[\\s\\S]*allback\\(([\\s\\S]*)\\);[^\\)]*\\z", "$1").trim();
        }
        if (str2.contains("online[0]=")) {
            new StringBuilder();
            str2 = sb.append("{online:").append(str2.charAt(str2.length() - 2)).append("}").toString();
        }
        new JSONObject(str2);
        return jSONObject;
    }

    public static boolean d(Context context) {
        double d2 = 0.0d;
        try {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            d2 = Math.sqrt(Math.pow((double) (((float) displayMetrics.widthPixels) / displayMetrics.xdpi), 2.0d) + Math.pow((double) (((float) displayMetrics.heightPixels) / displayMetrics.ydpi), 2.0d));
        } catch (Throwable th) {
            Throwable th2 = th;
        }
        return d2 > 6.5d;
    }

    public static String e(Context context, String str) {
        Context context2 = context;
        String str2 = str;
        if (null == context2) {
            return "";
        }
        c = d(context2, str2);
        return c;
    }

    public static boolean e(Context context) {
        Context context2 = context;
        return h.c(context2, "5.9.5") >= 0 || h.a(context2, Constants.PACKAGE_TIM) != null;
    }

    public static boolean e(String str) {
        String str2 = str;
        return str2 == null || str2.length() == 0;
    }

    public static String f(String str) {
        StringBuilder sb;
        StringBuilder sb2;
        String str2 = str;
        String str3 = str2;
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(i(str2));
            byte[] digest = instance.digest();
            if (digest != null) {
                new StringBuilder();
                StringBuilder sb3 = sb2;
                byte[] bArr = digest;
                int length = bArr.length;
                for (int i = 0; i < length; i++) {
                    byte b2 = bArr[i];
                    StringBuilder append = sb3.append(a(b2 >>> 4));
                    StringBuilder append2 = sb3.append(a((int) b2));
                }
                str3 = sb3.toString();
            }
        } catch (NoSuchAlgorithmException e2) {
            new StringBuilder();
            f.e("openSDK_LOG.Util", sb.append("encrypt has exception: ").append(e2.getMessage()).toString());
        }
        return str3;
    }

    private static boolean f(Context context) {
        Signature[] signatureArr;
        StringBuilder sb;
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo("com.tencent.mtt", 64);
            String str = packageInfo.versionName;
            if (h.a(str, "4.3") >= 0 && !str.startsWith("4.4") && (signatureArr = packageInfo.signatures) != null) {
                try {
                    MessageDigest instance = MessageDigest.getInstance("MD5");
                    instance.update(signatureArr[0].toByteArray());
                    String a2 = a(instance.digest());
                    instance.reset();
                    Object obj = "d8391a394d4a179e6fe7bdb8a301258b";
                    if (a2.equals("d8391a394d4a179e6fe7bdb8a301258b")) {
                        return true;
                    }
                } catch (NoSuchAlgorithmException e2) {
                    NoSuchAlgorithmException noSuchAlgorithmException = e2;
                    new StringBuilder();
                    f.e("openSDK_LOG.Util", sb.append("isQQBrowerAvailable has exception: ").append(noSuchAlgorithmException.getMessage()).toString());
                }
            }
            return false;
        } catch (PackageManager.NameNotFoundException e3) {
            PackageManager.NameNotFoundException nameNotFoundException = e3;
            return false;
        }
    }

    public static boolean f(Context context, String str) {
        Context context2 = context;
        String str2 = str;
        boolean z = true;
        if (d(context2) && h.a(context2, Constants.PACKAGE_QQ_PAD) != null) {
            z = false;
        }
        if (z && h.a(context2, Constants.PACKAGE_TIM) != null) {
            z = false;
        }
        if (z) {
            z = h.c(context2, str2) < 0;
        }
        return z;
    }

    public static boolean g(Context context, String str) {
        Context context2 = context;
        String str2 = str;
        boolean z = true;
        if (d(context2) && h.a(context2, Constants.PACKAGE_QQ_PAD) != null) {
            z = false;
        }
        if (z && h.a(context2, Constants.PACKAGE_TIM) != null) {
            z = false;
        }
        if (z) {
            z = h.c(context2, str2) < 0;
        }
        return z;
    }

    public static final boolean g(String str) {
        String str2 = str;
        if (str2 == null) {
            return false;
        }
        return str2.startsWith("http://") || str2.startsWith("https://");
    }

    public static boolean h(String str) {
        File file;
        String str2 = str;
        if (str2 == null) {
            return false;
        }
        new File(str2);
        File file2 = file;
        return file2 != null && file2.exists();
    }

    public static byte[] i(String str) {
        try {
            return str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e2) {
            UnsupportedEncodingException unsupportedEncodingException = e2;
            return null;
        }
    }
}
