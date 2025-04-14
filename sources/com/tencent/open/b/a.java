package com.tencent.open.b;

import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import com.tencent.open.a.f;

/* compiled from: ProGuard */
public class a {
    protected static final Uri a = Uri.parse("content://telephony/carriers/preferapn");

    public static String a(Context context) {
        Context context2 = context;
        int d = d(context2);
        if (d == 2) {
            return "wifi";
        }
        if (d == 1) {
            return "cmwap";
        }
        if (d == 4) {
            return "cmnet";
        }
        if (d == 16) {
            return "uniwap";
        }
        if (d == 8) {
            return "uninet";
        }
        if (d == 64) {
            return "wap";
        }
        if (d == 32) {
            return "net";
        }
        if (d == 512) {
            return "ctwap";
        }
        if (d == 256) {
            return "ctnet";
        }
        if (d == 2048) {
            return "3gnet";
        }
        if (d == 1024) {
            return "3gwap";
        }
        String b = b(context2);
        return (b == null || b.length() == 0) ? "none" : b;
    }

    public static String b(Context context) {
        StringBuilder sb;
        StringBuilder sb2;
        try {
            Cursor query = context.getContentResolver().query(a, (String[]) null, (String) null, (String[]) null, (String) null);
            if (query == null) {
                return null;
            }
            boolean moveToFirst = query.moveToFirst();
            if (query.isAfterLast()) {
                if (query != null) {
                    query.close();
                }
                return null;
            }
            String string = query.getString(query.getColumnIndex("apn"));
            if (query != null) {
                query.close();
            }
            return string;
        } catch (SecurityException e) {
            new StringBuilder();
            f.e("openSDK_LOG.APNUtil", sb2.append("getApn has exception: ").append(e.getMessage()).toString());
            return "";
        } catch (Exception e2) {
            new StringBuilder();
            f.e("openSDK_LOG.APNUtil", sb.append("getApn has exception: ").append(e2.getMessage()).toString());
            return "";
        }
    }

    public static String c(Context context) {
        StringBuilder sb;
        try {
            Cursor query = context.getContentResolver().query(a, (String[]) null, (String) null, (String[]) null, (String) null);
            if (query == null) {
                return null;
            }
            boolean moveToFirst = query.moveToFirst();
            if (query.isAfterLast()) {
                if (query != null) {
                    query.close();
                }
                return null;
            }
            String string = query.getString(query.getColumnIndex("proxy"));
            if (query != null) {
                query.close();
            }
            return string;
        } catch (SecurityException e) {
            new StringBuilder();
            f.e("openSDK_LOG.APNUtil", sb.append("getApnProxy has exception: ").append(e.getMessage()).toString());
            return "";
        }
    }

    public static int d(Context context) {
        StringBuilder sb;
        Context context2 = context;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context2.getSystemService("connectivity");
            if (connectivityManager == null) {
                return 128;
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return 128;
            }
            if (activeNetworkInfo.getTypeName().toUpperCase().equals("WIFI")) {
                return 2;
            }
            String lowerCase = activeNetworkInfo.getExtraInfo().toLowerCase();
            if (lowerCase.startsWith("cmwap")) {
                return 1;
            }
            if (lowerCase.startsWith("cmnet") || lowerCase.startsWith("epc.tmobile.com")) {
                return 4;
            }
            if (lowerCase.startsWith("uniwap")) {
                return 16;
            }
            if (lowerCase.startsWith("uninet")) {
                return 8;
            }
            if (lowerCase.startsWith("wap")) {
                return 64;
            }
            if (lowerCase.startsWith("net")) {
                return 32;
            }
            if (lowerCase.startsWith("ctwap")) {
                return 512;
            }
            if (lowerCase.startsWith("ctnet")) {
                return 256;
            }
            if (lowerCase.startsWith("3gwap")) {
                return 1024;
            }
            if (lowerCase.startsWith("3gnet")) {
                return 2048;
            }
            if (lowerCase.startsWith("#777")) {
                String c = c(context2);
                return (c == null || c.length() <= 0) ? 256 : 512;
            }
            return 128;
        } catch (Exception e) {
            new StringBuilder();
            f.e("openSDK_LOG.APNUtil", sb.append("getMProxyType has exception: ").append(e.getMessage()).toString());
        }
    }

    public static String e(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null) {
            return "MOBILE";
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null ? activeNetworkInfo.getTypeName() : "MOBILE";
    }
}
