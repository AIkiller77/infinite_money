package com.tencent.open.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import com.tencent.connect.common.Constants;
import com.tencent.open.a.f;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;

/* compiled from: ProGuard */
public class h {
    public static int a(String str) {
        String str2 = str;
        if ("shareToQQ".equals(str2)) {
            return Constants.REQUEST_QQ_SHARE;
        }
        if ("shareToQzone".equals(str2)) {
            return Constants.REQUEST_QZONE_SHARE;
        }
        if ("addToQQFavorites".equals(str2)) {
            return Constants.REQUEST_QQ_FAVORITES;
        }
        if ("sendToMyComputer".equals(str2)) {
            return Constants.REQUEST_SEND_TO_MY_COMPUTER;
        }
        if ("shareToTroopBar".equals(str2)) {
            return Constants.REQUEST_SHARE_TO_TROOP_BAR;
        }
        if ("action_login".equals(str2)) {
            return Constants.REQUEST_LOGIN;
        }
        if ("action_request".equals(str2)) {
            return Constants.REQUEST_API;
        }
        return -1;
    }

    public static int a(String str, String str2) {
        String str3 = str;
        String str4 = str2;
        if (str3 == null && str4 == null) {
            return 0;
        }
        if (str3 != null && str4 == null) {
            return 1;
        }
        if (str3 == null && str4 != null) {
            return -1;
        }
        String[] split = str3.split("\\.");
        String[] split2 = str4.split("\\.");
        int i = 0;
        while (i < split.length && i < split2.length) {
            try {
                int parseInt = Integer.parseInt(split[i]);
                int parseInt2 = Integer.parseInt(split2[i]);
                if (parseInt < parseInt2) {
                    return -1;
                }
                if (parseInt > parseInt2) {
                    return 1;
                }
                i++;
            } catch (NumberFormatException e) {
                NumberFormatException numberFormatException = e;
                return str3.compareTo(str4);
            }
        }
        if (split.length > i) {
            return 1;
        }
        return split2.length > i ? -1 : 0;
    }

    private static long a(InputStream inputStream, OutputStream outputStream) throws IOException {
        StringBuilder sb;
        InputStream inputStream2 = inputStream;
        OutputStream outputStream2 = outputStream;
        long j = 0;
        byte[] bArr = new byte[8192];
        while (true) {
            int read = inputStream2.read(bArr, 0, bArr.length);
            int i = read;
            if (read != -1) {
                outputStream2.write(bArr, 0, i);
                j += (long) i;
            } else {
                new StringBuilder();
                f.c("openSDK_LOG.SystemUtils", sb.append("-->copy, copyed size is: ").append(j).toString());
                return j;
            }
        }
    }

    public static String a(int i) {
        int i2 = i;
        if (i2 == 10103) {
            return "shareToQQ";
        }
        if (i2 == 10104) {
            return "shareToQzone";
        }
        if (i2 == 10105) {
            return "addToQQFavorites";
        }
        if (i2 == 10106) {
            return "sendToMyComputer";
        }
        if (i2 == 10107) {
            return "shareToTroopBar";
        }
        if (i2 == 11101) {
            return "action_login";
        }
        if (i2 == 10100) {
            return "action_request";
        }
        return null;
    }

    public static String a(Context context) {
        Context context2 = context;
        return context2.getApplicationInfo().loadLabel(context2.getPackageManager()).toString();
    }

    public static String a(Context context, String str) {
        try {
            return context.getPackageManager().getPackageInfo(str, 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            PackageManager.NameNotFoundException nameNotFoundException = e;
            return null;
        }
    }

    public static boolean a(Context context, Intent intent) {
        Context context2 = context;
        Intent intent2 = intent;
        if (context2 == null || intent2 == null) {
            return false;
        }
        return context2.getPackageManager().queryIntentActivities(intent2, 0).size() != 0;
    }

    public static boolean a(Context context, String str, String str2) {
        String str3 = str;
        String str4 = str2;
        f.a("openSDK_LOG.SystemUtils", "OpenUi, validateAppSignatureForPackage");
        try {
            Signature[] signatureArr = context.getPackageManager().getPackageInfo(str3, 64).signatures;
            int length = signatureArr.length;
            for (int i = 0; i < length; i++) {
                if (k.f(signatureArr[i].toCharsString()).equals(str4)) {
                    return true;
                }
            }
            return false;
        } catch (PackageManager.NameNotFoundException e) {
            PackageManager.NameNotFoundException nameNotFoundException = e;
            return false;
        }
    }

    @SuppressLint({"SdCardPath"})
    public static boolean a(String str, String str2, int i) {
        StringBuilder sb;
        File file;
        StringBuilder sb2;
        String str3 = str;
        String str4 = str2;
        int i2 = i;
        new StringBuilder();
        f.c("openSDK_LOG.SystemUtils", sb.append("-->extractSecureLib, libName: ").append(str3).toString());
        Context a = e.a();
        if (a == null) {
            f.c("openSDK_LOG.SystemUtils", "-->extractSecureLib, global context is null. ");
            return false;
        }
        SharedPreferences sharedPreferences = a.getSharedPreferences("secure_lib", 0);
        new File(a.getFilesDir(), str4);
        File file2 = file;
        if (!file2.exists()) {
            File parentFile = file2.getParentFile();
            if (parentFile != null && parentFile.mkdirs()) {
                try {
                    boolean createNewFile = file2.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            int i3 = sharedPreferences.getInt("version", 0);
            new StringBuilder();
            f.c("openSDK_LOG.SystemUtils", sb2.append("-->extractSecureLib, libVersion: ").append(i2).append(" | oldVersion: ").append(i3).toString());
            if (i2 == i3) {
                return true;
            }
        }
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            inputStream = a.getAssets().open(str3);
            fileOutputStream = a.openFileOutput(str4, 0);
            long a2 = a(inputStream, (OutputStream) fileOutputStream);
            SharedPreferences.Editor edit = sharedPreferences.edit();
            SharedPreferences.Editor putInt = edit.putInt("version", i2);
            boolean commit = edit.commit();
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e2) {
                    IOException iOException = e2;
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e3) {
                    IOException iOException2 = e3;
                }
            }
            return true;
        } catch (Exception e4) {
            f.b("openSDK_LOG.SystemUtils", "-->extractSecureLib, when copy lib execption.", e4);
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e5) {
                    IOException iOException3 = e5;
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e6) {
                    IOException iOException4 = e6;
                }
            }
            return false;
        } catch (Throwable th) {
            Throwable th2 = th;
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e7) {
                    IOException iOException5 = e7;
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e8) {
                    IOException iOException6 = e8;
                }
            }
            throw th2;
        }
    }

    public static String b(Context context, String str) {
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        Context context2 = context;
        String str2 = str;
        f.a("openSDK_LOG.SystemUtils", "OpenUi, getSignValidString");
        String str3 = "";
        try {
            String packageName = context2.getPackageName();
            Signature[] signatureArr = context2.getPackageManager().getPackageInfo(packageName, 64).signatures;
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(signatureArr[0].toByteArray());
            String a = k.a(instance.digest());
            instance.reset();
            new StringBuilder();
            f.a("openSDK_LOG.SystemUtils", sb.append("-->sign: ").append(a).toString());
            new StringBuilder();
            instance.update(k.i(sb2.append(packageName).append("_").append(a).append("_").append(str2).append("").toString()));
            str3 = k.a(instance.digest());
            instance.reset();
            new StringBuilder();
            f.a("openSDK_LOG.SystemUtils", sb3.append("-->signEncryped: ").append(str3).toString());
        } catch (Exception e) {
            Exception exc = e;
            exc.printStackTrace();
            f.b("openSDK_LOG.SystemUtils", "OpenUi, getSignValidString error", exc);
        }
        return str3;
    }

    public static int c(Context context, String str) {
        return a(a(context, "com.tencent.mobileqq"), str);
    }

    public static int d(Context context, String str) {
        return a(a(context, Constants.PACKAGE_TIM), str);
    }
}
