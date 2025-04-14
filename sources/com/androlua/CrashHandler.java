package com.androlua;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import com.a.a.a.a.a.a.a;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class CrashHandler implements Thread.UncaughtExceptionHandler {
    public static final String TAG = "CrashHandler";
    private static CrashHandler b = new CrashHandler();
    private Thread.UncaughtExceptionHandler a;
    private Context c;
    private Map<String, String> d = new LinkedHashMap();
    private DateFormat e = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

    private CrashHandler() {
    }

    private boolean a(Throwable th) {
        if (th == null) {
            return false;
        }
        collectDeviceInfo(this.c);
        b(th);
        return true;
    }

    private String b(Throwable th) {
        StringBuffer stringBuffer = new StringBuffer();
        for (Map.Entry next : this.d.entrySet()) {
            stringBuffer.append(((String) next.getKey()) + "=" + ((String) next.getValue()) + "\n");
        }
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        do {
            a.a(th, printWriter);
            th = th.getCause();
        } while (th != null);
        printWriter.close();
        stringBuffer.append(stringWriter.toString());
        try {
            long currentTimeMillis = System.currentTimeMillis();
            String str = "crash-" + this.e.format(new Date()) + "-" + currentTimeMillis + ".log";
            if (Environment.getExternalStorageState().equals("mounted")) {
                File file = new File("/sdcard/androlua/crash/");
                if (!file.exists()) {
                    file.mkdirs();
                }
                FileOutputStream fileOutputStream = new FileOutputStream("/sdcard/androlua/crash/" + str);
                fileOutputStream.write(stringBuffer.toString().getBytes());
                Log.e("crash", stringBuffer.toString());
                fileOutputStream.close();
            }
            return str;
        } catch (Exception e2) {
            Log.e(TAG, "an error occured while writing file...", e2);
            return null;
        }
    }

    public static CrashHandler getInstance() {
        return b;
    }

    public void collectDeviceInfo(Context context) {
        Map<String, String> map;
        String name;
        String obj;
        Map<String, String> map2;
        String name2;
        String obj2;
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 1);
            if (packageInfo != null) {
                this.d.put("versionName", packageInfo.versionName == null ? "null" : packageInfo.versionName);
                this.d.put("versionCode", packageInfo.versionCode + "");
            }
        } catch (PackageManager.NameNotFoundException e2) {
            Log.e(TAG, "an error occured when collect package info", e2);
        }
        for (Field field : Build.class.getDeclaredFields()) {
            try {
                field.setAccessible(true);
                Object obj3 = field.get((Object) null);
                if (obj3 instanceof String[]) {
                    map2 = this.d;
                    name2 = field.getName();
                    obj2 = Arrays.toString((String[]) obj3);
                } else {
                    map2 = this.d;
                    name2 = field.getName();
                    obj2 = obj3.toString();
                }
                map2.put(name2, obj2);
                Log.d(TAG, field.getName() + " : " + field.get((Object) null));
            } catch (Exception e3) {
                Log.e(TAG, "an error occured when collect crash info", e3);
            }
        }
        for (Field field2 : Build.VERSION.class.getDeclaredFields()) {
            try {
                field2.setAccessible(true);
                Object obj4 = field2.get((Object) null);
                if (obj4 instanceof String[]) {
                    map = this.d;
                    name = field2.getName();
                    obj = Arrays.toString((String[]) obj4);
                } else {
                    map = this.d;
                    name = field2.getName();
                    obj = obj4.toString();
                }
                map.put(name, obj);
                Log.d(TAG, field2.getName() + " : " + field2.get((Object) null));
            } catch (Exception e4) {
                Log.e(TAG, "an error occured when collect crash info", e4);
            }
        }
    }

    public void init(Context context) {
        this.c = context;
        this.a = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    public void uncaughtException(Thread thread, Throwable th) {
        if (!a(th) && this.a != null) {
            this.a.uncaughtException(thread, th);
        }
    }
}
