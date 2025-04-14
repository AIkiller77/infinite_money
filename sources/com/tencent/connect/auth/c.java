package com.tencent.connect.auth;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.widget.Toast;
import com.tencent.connect.a.a;
import com.tencent.connect.common.BaseApi;
import com.tencent.open.a.f;
import com.tencent.open.utils.e;
import com.tencent.tauth.IUiListener;
import java.io.File;
import java.util.Iterator;

/* compiled from: ProGuard */
public class c {
    private AuthAgent a;
    private QQToken b;

    private c(String str, Context context) {
        QQToken qQToken;
        AuthAgent authAgent;
        f.c("openSDK_LOG.QQAuth", "new QQAuth() --start");
        new QQToken(str);
        this.b = qQToken;
        new AuthAgent(this.b);
        this.a = authAgent;
        a.c(context, this.b);
        f.c("openSDK_LOG.QQAuth", "new QQAuth() --end");
    }

    private int a(Activity activity, Fragment fragment, String str, IUiListener iUiListener, String str2) {
        return a(activity, fragment, str, iUiListener, str2, false);
    }

    private int a(Activity activity, Fragment fragment, String str, IUiListener iUiListener, String str2, boolean z) {
        File file;
        StringBuilder sb;
        Activity activity2 = activity;
        Fragment fragment2 = fragment;
        String str3 = str;
        IUiListener iUiListener2 = iUiListener;
        String str4 = str2;
        boolean z2 = z;
        String packageName = activity2.getApplicationContext().getPackageName();
        String str5 = null;
        try {
            Iterator<ApplicationInfo> it = activity2.getPackageManager().getInstalledApplications(128).iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                ApplicationInfo next = it.next();
                if (packageName.equals(next.packageName)) {
                    str5 = next.sourceDir;
                    break;
                }
            }
            if (str5 != null) {
                new File(str5);
                String a2 = com.tencent.open.utils.a.a(file);
                if (!TextUtils.isEmpty(a2)) {
                    new StringBuilder();
                    f.a("openSDK_LOG.QQAuth", sb.append("-->login channelId: ").append(a2).toString());
                    return a(activity2, str3, iUiListener2, a2, a2, "");
                }
            }
        } catch (Throwable th) {
            Throwable th2 = th;
            f.b("openSDK_LOG.QQAuth", "-->login get channel id exception.", th2);
            th2.printStackTrace();
        }
        f.b("openSDK_LOG.QQAuth", "-->login channelId is null ");
        BaseApi.isOEM = false;
        return this.a.doLogin(activity2, str3, iUiListener2, false, fragment2, z2);
    }

    public static c a(String str, Context context) {
        ComponentName componentName;
        ComponentName componentName2;
        c cVar;
        String str2 = str;
        Context context2 = context;
        e.a(context2.getApplicationContext());
        f.c("openSDK_LOG.QQAuth", "QQAuth -- createInstance() --start");
        try {
            PackageManager packageManager = context2.getPackageManager();
            new ComponentName(context2.getPackageName(), "com.tencent.tauth.AuthActivity");
            ActivityInfo activityInfo = packageManager.getActivityInfo(componentName, 0);
            new ComponentName(context2.getPackageName(), "com.tencent.connect.common.AssistActivity");
            ActivityInfo activityInfo2 = packageManager.getActivityInfo(componentName2, 0);
            new c(str2, context2);
            f.c("openSDK_LOG.QQAuth", "QQAuth -- createInstance()  --end");
            return cVar;
        } catch (PackageManager.NameNotFoundException e) {
            f.b("openSDK_LOG.QQAuth", "createInstance() error --end", e);
            Toast.makeText(context2.getApplicationContext(), "请参照文档在Androidmanifest.xml加上AuthActivity和AssitActivity的定义 ", 1).show();
            return null;
        }
    }

    public int a(Activity activity, String str, IUiListener iUiListener) {
        f.c("openSDK_LOG.QQAuth", "login()");
        return a(activity, str, iUiListener, "");
    }

    public int a(Activity activity, String str, IUiListener iUiListener, String str2) {
        StringBuilder sb;
        Activity activity2 = activity;
        new StringBuilder();
        f.c("openSDK_LOG.QQAuth", sb.append("-->login activity: ").append(activity2).toString());
        return a(activity2, (Fragment) null, str, iUiListener, str2);
    }

    @Deprecated
    public int a(Activity activity, String str, IUiListener iUiListener, String str2, String str3, String str4) {
        Activity activity2 = activity;
        String str5 = str;
        IUiListener iUiListener2 = iUiListener;
        String str6 = str2;
        String str7 = str3;
        String str8 = str4;
        f.c("openSDK_LOG.QQAuth", "loginWithOEM");
        BaseApi.isOEM = true;
        if (str6.equals("")) {
            str6 = "null";
        }
        if (str7.equals("")) {
            str7 = "null";
        }
        if (str8.equals("")) {
            str8 = "null";
        }
        BaseApi.installChannel = str7;
        BaseApi.registerChannel = str6;
        BaseApi.businessId = str8;
        return this.a.doLogin(activity2, str5, iUiListener2);
    }

    public int a(Activity activity, String str, IUiListener iUiListener, boolean z) {
        f.c("openSDK_LOG.QQAuth", "login()");
        return a(activity, (Fragment) null, str, iUiListener, "", z);
    }

    public int a(Fragment fragment, String str, IUiListener iUiListener, String str2) {
        StringBuilder sb;
        Fragment fragment2 = fragment;
        FragmentActivity activity = fragment2.getActivity();
        new StringBuilder();
        f.c("openSDK_LOG.QQAuth", sb.append("-->login activity: ").append(activity).toString());
        return a((Activity) activity, fragment2, str, iUiListener, str2);
    }

    public int a(Fragment fragment, String str, IUiListener iUiListener, String str2, boolean z) {
        StringBuilder sb;
        Fragment fragment2 = fragment;
        FragmentActivity activity = fragment2.getActivity();
        new StringBuilder();
        f.c("openSDK_LOG.QQAuth", sb.append("-->login activity: ").append(activity).toString());
        return a((Activity) activity, fragment2, str, iUiListener, str2, z);
    }

    public void a() {
        this.a.a((IUiListener) null);
    }

    public void a(Context context, String str) {
        f.a("openSDK_LOG.QQAuth", "setOpenId() --start");
        this.b.setOpenId(str);
        a.d(context, this.b);
        f.a("openSDK_LOG.QQAuth", "setOpenId() --end");
    }

    public void a(IUiListener iUiListener) {
        this.a.b(iUiListener);
    }

    public void a(String str, String str2) {
        StringBuilder sb;
        String str3 = str2;
        new StringBuilder();
        f.a("openSDK_LOG.QQAuth", sb.append("setAccessToken(), validTimeInSecond = ").append(str3).append("").toString());
        this.b.setAccessToken(str, str3);
    }

    public int b(Activity activity, String str, IUiListener iUiListener) {
        f.c("openSDK_LOG.QQAuth", "reAuth()");
        return this.a.doLogin(activity, str, iUiListener, true, (Fragment) null);
    }

    public QQToken b() {
        return this.b;
    }

    public boolean c() {
        StringBuilder sb;
        new StringBuilder();
        f.a("openSDK_LOG.QQAuth", sb.append("isSessionValid(), result = ").append(this.b.isSessionValid() ? "true" : "false").append("").toString());
        return this.b.isSessionValid();
    }
}
