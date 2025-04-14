package com.tencent.open.web.security;

import android.content.Context;
import com.tencent.connect.auth.AuthAgent;
import com.tencent.open.a.f;
import com.tencent.open.utils.e;
import java.io.File;
import net.lingala.zip4j.util.InternalZipConstants;

/* compiled from: ProGuard */
public class JniInterface {
    public static boolean isJniOk = false;

    public JniInterface() {
    }

    public static native boolean BackSpaceChar(boolean z, int i);

    public static native boolean clearAllPWD();

    public static native String getPWDKeyToMD5(String str);

    public static native boolean insetTextToArray(int i, String str, int i2);

    public static void loadSo() {
        StringBuilder sb;
        StringBuilder sb2;
        File file;
        StringBuilder sb3;
        StringBuilder sb4;
        StringBuilder sb5;
        StringBuilder sb6;
        if (!isJniOk) {
            try {
                Context a = e.a();
                if (a != null) {
                    new StringBuilder();
                    new File(sb3.append(a.getFilesDir().toString()).append(InternalZipConstants.ZIP_FILE_SEPARATOR).append(AuthAgent.SECURE_LIB_NAME).toString());
                    if (file.exists()) {
                        new StringBuilder();
                        System.load(sb5.append(a.getFilesDir().toString()).append(InternalZipConstants.ZIP_FILE_SEPARATOR).append(AuthAgent.SECURE_LIB_NAME).toString());
                        isJniOk = true;
                        new StringBuilder();
                        f.c("openSDK_LOG.JniInterface", sb6.append("-->load lib success:").append(AuthAgent.SECURE_LIB_NAME).toString());
                    } else {
                        new StringBuilder();
                        f.c("openSDK_LOG.JniInterface", sb4.append("-->fail, because so is not exists:").append(AuthAgent.SECURE_LIB_NAME).toString());
                    }
                } else {
                    new StringBuilder();
                    f.c("openSDK_LOG.JniInterface", sb2.append("-->load lib fail, because context is null:").append(AuthAgent.SECURE_LIB_NAME).toString());
                }
            } catch (Throwable th) {
                new StringBuilder();
                f.b("openSDK_LOG.JniInterface", sb.append("-->load lib error:").append(AuthAgent.SECURE_LIB_NAME).toString(), th);
            }
        }
    }
}
