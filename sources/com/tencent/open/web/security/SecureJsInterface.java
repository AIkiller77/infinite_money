package com.tencent.open.web.security;

import com.tencent.open.a;
import com.tencent.open.a.f;

/* compiled from: ProGuard */
public class SecureJsInterface extends a.b {
    public static boolean isPWDEdit = false;
    private String a;

    public SecureJsInterface() {
    }

    public void clearAllEdit() {
        StringBuilder sb;
        Throwable th;
        f.c("openSDK_LOG.SecureJsInterface", "-->clear all edit.");
        try {
            boolean clearAllPWD = JniInterface.clearAllPWD();
        } catch (Exception e) {
            Exception exc = e;
            new StringBuilder();
            f.e("openSDK_LOG.SecureJsInterface", sb.append("-->clear all edit exception: ").append(exc.getMessage()).toString());
            Throwable th2 = th;
            new RuntimeException(exc);
            throw th2;
        }
    }

    public void curPosFromJS(String str) {
        StringBuilder sb;
        StringBuilder sb2;
        Throwable th;
        String str2 = str;
        new StringBuilder();
        f.b("openSDK_LOG.SecureJsInterface", sb.append("-->curPosFromJS: ").append(str2).toString());
        int i = -1;
        try {
            i = Integer.parseInt(str2);
        } catch (NumberFormatException e) {
            f.b("openSDK_LOG.SecureJsInterface", "-->curPosFromJS number format exception.", e);
        }
        if (i < 0) {
            Throwable th2 = th;
            new RuntimeException("position is illegal.");
            throw th2;
        }
        if (!a.c) {
        }
        if (!a.b) {
            this.a = a.a;
            boolean insetTextToArray = JniInterface.insetTextToArray(i, this.a, this.a.length());
            new StringBuilder();
            f.a("openSDK_LOG.SecureJsInterface", sb2.append("curPosFromJS mKey: ").append(this.a).toString());
        } else if (Boolean.valueOf(JniInterface.BackSpaceChar(a.b, i)).booleanValue()) {
            a.b = false;
        }
    }

    public boolean customCallback() {
        return true;
    }

    public String getMD5FromNative() {
        StringBuilder sb;
        Throwable th;
        StringBuilder sb2;
        f.c("openSDK_LOG.SecureJsInterface", "-->get md5 form native");
        Object obj = "";
        try {
            String pWDKeyToMD5 = JniInterface.getPWDKeyToMD5((String) null);
            new StringBuilder();
            f.a("openSDK_LOG.SecureJsInterface", sb2.append("-->getMD5FromNative, MD5= ").append(pWDKeyToMD5).toString());
            return pWDKeyToMD5;
        } catch (Exception e) {
            Exception exc = e;
            new StringBuilder();
            f.e("openSDK_LOG.SecureJsInterface", sb.append("-->get md5 form native exception: ").append(exc.getMessage()).toString());
            Throwable th2 = th;
            new RuntimeException(exc);
            throw th2;
        }
    }

    public void isPasswordEdit(String str) {
        StringBuilder sb;
        StringBuilder sb2;
        Throwable th;
        String str2 = str;
        new StringBuilder();
        f.c("openSDK_LOG.SecureJsInterface", sb.append("-->is pswd edit, flag: ").append(str2).toString());
        int i = -1;
        try {
            i = Integer.parseInt(str2);
        } catch (Exception e) {
            new StringBuilder();
            f.e("openSDK_LOG.SecureJsInterface", sb2.append("-->is pswd edit exception: ").append(e.getMessage()).toString());
        }
        if (i != 0 && i != 1) {
            Throwable th2 = th;
            new RuntimeException("is pswd edit flag is illegal.");
            throw th2;
        } else if (i == 0) {
            isPWDEdit = false;
        } else if (i == 1) {
            isPWDEdit = true;
        }
    }
}
