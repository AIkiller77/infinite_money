package com.tencent.tauth;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Base64;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.auth.c;
import com.tencent.connect.common.Constants;
import com.tencent.connect.common.UIListenerManager;
import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzonePublish;
import com.tencent.connect.share.QzoneShare;
import com.tencent.open.SocialApi;
import com.tencent.open.SocialOperation;
import com.tencent.open.a.f;
import com.tencent.open.b.d;
import com.tencent.open.utils.HttpUtils;
import com.tencent.open.utils.e;
import com.tencent.open.utils.h;
import com.tencent.open.utils.k;
import java.io.IOException;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class Tencent {
    public static final int REQUEST_LOGIN = 10001;
    private static Tencent b;
    private final c a;

    private Tencent(String str, Context context) {
        this.a = c.a(str, context);
    }

    private static boolean a(Context context, String str) {
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        ComponentName componentName;
        StringBuilder sb4;
        StringBuilder sb5;
        ComponentName componentName2;
        Context context2 = context;
        String str2 = str;
        try {
            new ComponentName(context2.getPackageName(), "com.tencent.tauth.AuthActivity");
            ActivityInfo activityInfo = context2.getPackageManager().getActivityInfo(componentName, 0);
            try {
                new ComponentName(context2.getPackageName(), "com.tencent.connect.common.AssistActivity");
                ActivityInfo activityInfo2 = context2.getPackageManager().getActivityInfo(componentName2, 0);
                return true;
            } catch (PackageManager.NameNotFoundException e) {
                PackageManager.NameNotFoundException nameNotFoundException = e;
                new StringBuilder();
                new StringBuilder();
                f.e("openSDK_LOG.Tencent", sb5.append("AndroidManifest.xml 没有检测到com.tencent.connect.common.AssistActivity\n").append(sb4.append("没有在AndroidManifest.xml中检测到com.tencent.connect.common.AssistActivity,请加上com.tencent.connect.common.AssistActivity,详细信息请查看官网文档.").append("\n配置示例如下: \n<activity\n     android:name=\"com.tencent.connect.common.AssistActivity\"\n     android:screenOrientation=\"behind\"\n     android:theme=\"@android:style/Theme.Translucent.NoTitleBar\"\n     android:configChanges=\"orientation|keyboardHidden\">\n</activity>").toString()).toString());
                return false;
            }
        } catch (PackageManager.NameNotFoundException e2) {
            PackageManager.NameNotFoundException nameNotFoundException2 = e2;
            new StringBuilder();
            String sb6 = sb.append("没有在AndroidManifest.xml中检测到com.tencent.tauth.AuthActivity,请加上com.tencent.tauth.AuthActivity,并配置<data android:scheme=\"tencent").append(str2).append("\" />,详细信息请查看官网文档.").toString();
            new StringBuilder();
            new StringBuilder();
            f.e("openSDK_LOG.Tencent", sb3.append("AndroidManifest.xml 没有检测到com.tencent.tauth.AuthActivity").append(sb2.append(sb6).append("\n配置示例如下: \n<activity\n     android:name=\"com.tencent.tauth.AuthActivity\"\n     android:noHistory=\"true\"\n     android:launchMode=\"singleTask\">\n<intent-filter>\n    <action android:name=\"android.intent.action.VIEW\" />\n    <category android:name=\"android.intent.category.DEFAULT\" />\n    <category android:name=\"android.intent.category.BROWSABLE\" />\n    <data android:scheme=\"tencent").append(str2).append("\" />\n").append("</intent-filter>\n").append("</activity>").toString()).toString());
            return false;
        }
    }

    public static synchronized Tencent createInstance(String str, Context context) {
        StringBuilder sb;
        Tencent tencent;
        Tencent tencent2;
        Tencent tencent3;
        String str2 = str;
        Context context2 = context;
        synchronized (Tencent.class) {
            e.a(context2.getApplicationContext());
            new StringBuilder();
            f.c("openSDK_LOG.Tencent", sb.append("createInstance()  -- start, appId = ").append(str2).toString());
            if (b == null) {
                new Tencent(str2, context2);
                b = tencent3;
            } else {
                if (!str2.equals(b.getAppId())) {
                    b.logout(context2);
                    new Tencent(str2, context2);
                    b = tencent;
                }
            }
            if (!a(context2, str2)) {
                tencent2 = null;
            } else {
                com.tencent.open.utils.f a2 = com.tencent.open.utils.f.a(context2, str2);
                f.c("openSDK_LOG.Tencent", "createInstance()  -- end");
                tencent2 = b;
            }
        }
        return tencent2;
    }

    public static void handleResultData(Intent intent, IUiListener iUiListener) {
        StringBuilder sb;
        Intent intent2 = intent;
        IUiListener iUiListener2 = iUiListener;
        new StringBuilder();
        f.c("openSDK_LOG.Tencent", sb.append("handleResultData() data = null ? ").append(intent2 == null).append(", listener = null ? ").append(iUiListener2 == null).toString());
        UIListenerManager.getInstance().handleDataToListener(intent2, iUiListener2);
    }

    public static boolean onActivityResultData(int i, int i2, Intent intent, IUiListener iUiListener) {
        StringBuilder sb;
        int i3 = i;
        int i4 = i2;
        Intent intent2 = intent;
        IUiListener iUiListener2 = iUiListener;
        new StringBuilder();
        f.c("openSDK_LOG.Tencent", sb.append("onActivityResultData() reqcode = ").append(i3).append(", resultcode = ").append(i4).append(", data = null ? ").append(intent2 == null).append(", listener = null ? ").append(iUiListener2 == null).toString());
        return UIListenerManager.getInstance().onActivityResult(i3, i4, intent2, iUiListener2);
    }

    public int ask(Activity activity, Bundle bundle, IUiListener iUiListener) {
        SocialApi socialApi;
        f.c("openSDK_LOG.Tencent", "ask()");
        new SocialApi(this.a.b());
        socialApi.ask(activity, bundle, iUiListener);
        return 0;
    }

    public void bindQQGroup(Activity activity, Bundle bundle) {
        SocialOperation socialOperation;
        new SocialOperation(getQQToken());
        socialOperation.bindQQGroup(activity, bundle);
    }

    public void checkLogin(IUiListener iUiListener) {
        f.c("openSDK_LOG.Tencent", "checkLogin()");
        this.a.a(iUiListener);
    }

    public boolean checkSessionValid(String str) {
        StringBuilder sb;
        JSONObject loadSession = this.a.b().loadSession(str);
        if (!(loadSession == null || loadSession.length() == 0)) {
            try {
                String string = loadSession.getString(Constants.PARAM_ACCESS_TOKEN);
                String string2 = loadSession.getString(Constants.PARAM_EXPIRES_IN);
                String string3 = loadSession.getString("openid");
                String string4 = loadSession.getString(Constants.PARAM_EXPIRES_TIME);
                if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(string2) && !TextUtils.isEmpty(string3) && !TextUtils.isEmpty(string4)) {
                    if (System.currentTimeMillis() < Long.parseLong(string4)) {
                        return true;
                    }
                }
            } catch (Exception e) {
                new StringBuilder();
                f.c("QQToken", sb.append("checkSessionValid ").append(e.toString()).toString());
                return false;
            }
        }
        return false;
    }

    public String getAccessToken() {
        return this.a.b().getAccessToken();
    }

    public String getAppId() {
        return this.a.b().getAppId();
    }

    public long getExpiresIn() {
        return this.a.b().getExpireTimeInSecond();
    }

    public String getOpenId() {
        return this.a.b().getOpenId();
    }

    public QQToken getQQToken() {
        return this.a.b();
    }

    public int gift(Activity activity, Bundle bundle, IUiListener iUiListener) {
        SocialApi socialApi;
        f.c("openSDK_LOG.Tencent", "gift()");
        new SocialApi(this.a.b());
        socialApi.gift(activity, bundle, iUiListener);
        return 0;
    }

    @Deprecated
    public void handleLoginData(Intent intent, IUiListener iUiListener) {
        StringBuilder sb;
        Intent intent2 = intent;
        IUiListener iUiListener2 = iUiListener;
        new StringBuilder();
        f.c("openSDK_LOG.Tencent", sb.append("handleLoginData() data = null ? ").append(intent2 == null).append(", listener = null ? ").append(iUiListener2 == null).toString());
        UIListenerManager.getInstance().handleDataToListener(intent2, iUiListener2);
    }

    public void initSessionCache(JSONObject jSONObject) {
        StringBuilder sb;
        JSONObject jSONObject2 = jSONObject;
        try {
            String string = jSONObject2.getString(Constants.PARAM_ACCESS_TOKEN);
            String string2 = jSONObject2.getString(Constants.PARAM_EXPIRES_IN);
            String string3 = jSONObject2.getString("openid");
            if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(string2) && !TextUtils.isEmpty(string3)) {
                setAccessToken(string, string2);
                setOpenId(string3);
            }
        } catch (Exception e) {
            new StringBuilder();
            f.c("QQToken", sb.append("initSessionCache ").append(e.toString()).toString());
        }
    }

    public int invite(Activity activity, Bundle bundle, IUiListener iUiListener) {
        SocialApi socialApi;
        f.c("openSDK_LOG.Tencent", "invite()");
        new SocialApi(this.a.b());
        socialApi.invite(activity, bundle, iUiListener);
        return 0;
    }

    public boolean isQQInstalled(Context context) {
        List<PackageInfo> installedPackages = context.getPackageManager().getInstalledPackages(0);
        if (installedPackages != null) {
            for (int i = 0; i < installedPackages.size(); i++) {
                if (installedPackages.get(i).packageName.equals("com.tencent.mobileqq")) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isReady() {
        return isSessionValid() && getOpenId() != null;
    }

    public boolean isSessionValid() {
        return this.a.c();
    }

    public boolean isSupportSSOLogin(Activity activity) {
        Activity activity2 = activity;
        if (k.d((Context) activity2) && h.a((Context) activity2, Constants.PACKAGE_QQ_PAD) != null) {
            return true;
        }
        return h.c(activity2, "4.1") >= 0 || h.d(activity2, "1.1") >= 0;
    }

    public boolean joinQQGroup(Activity activity, String str) {
        Intent intent;
        StringBuffer stringBuffer;
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        Activity activity2 = activity;
        f.c("openSDK_LOG.Tencent", "joinQQGroup()");
        new Intent();
        Intent intent2 = intent;
        String openId = this.a.b().getOpenId();
        String appId = this.a.b().getAppId();
        new StringBuilder();
        new StringBuffer(sb.append("mqqopensdkapi://bizAgent/qm/qr?url=http%3A%2F%2Fqm.qq.com%2Fcgi-bin%2Fqm%2Fqr%3Ffrom%3Dapp%26p%3Dandroid%26k%3D").append(str).toString());
        StringBuffer stringBuffer2 = stringBuffer;
        if (!TextUtils.isEmpty(openId)) {
            new StringBuilder();
            StringBuffer append = stringBuffer2.append(sb3.append("&openid=").append(Base64.encodeToString(k.i(openId), 2)).toString());
        }
        if (!TextUtils.isEmpty(appId)) {
            new StringBuilder();
            StringBuffer append2 = stringBuffer2.append(sb2.append("&appid=").append(appId).toString());
        }
        Intent data = intent2.setData(Uri.parse(stringBuffer2.toString()));
        try {
            activity2.startActivity(intent2);
            d.a().a(this.a.b().getOpenId(), this.a.b().getAppId(), Constants.VIA_JOIN_GROUP, Constants.VIA_REPORT_TYPE_JOININ_GROUP, "18", "0");
            return true;
        } catch (Exception e) {
            Exception exc = e;
            d.a().a(this.a.b().getOpenId(), this.a.b().getAppId(), Constants.VIA_JOIN_GROUP, Constants.VIA_REPORT_TYPE_JOININ_GROUP, "18", "1");
            return false;
        }
    }

    public JSONObject loadSession(String str) {
        return this.a.b().loadSession(str);
    }

    public int login(Activity activity, String str, IUiListener iUiListener) {
        StringBuilder sb;
        String str2 = str;
        new StringBuilder();
        f.c("openSDK_LOG.Tencent", sb.append("login() with activity, scope is ").append(str2).toString());
        return this.a.a(activity, str2, iUiListener);
    }

    public int login(Activity activity, String str, IUiListener iUiListener, boolean z) {
        StringBuilder sb;
        String str2 = str;
        new StringBuilder();
        f.c("openSDK_LOG.Tencent", sb.append("login() with activity, scope is ").append(str2).toString());
        return this.a.a(activity, str2, iUiListener, z);
    }

    public int login(Fragment fragment, String str, IUiListener iUiListener) {
        StringBuilder sb;
        String str2 = str;
        new StringBuilder();
        f.c("openSDK_LOG.Tencent", sb.append("login() with fragment, scope is ").append(str2).toString());
        return this.a.a(fragment, str2, iUiListener, "");
    }

    public int login(Fragment fragment, String str, IUiListener iUiListener, boolean z) {
        StringBuilder sb;
        String str2 = str;
        new StringBuilder();
        f.c("openSDK_LOG.Tencent", sb.append("login() with fragment, scope is ").append(str2).toString());
        return this.a.a(fragment, str2, iUiListener, "", z);
    }

    public int loginServerSide(Activity activity, String str, IUiListener iUiListener) {
        StringBuilder sb;
        StringBuilder sb2;
        String str2 = str;
        new StringBuilder();
        f.c("openSDK_LOG.Tencent", sb.append("loginServerSide() with activity, scope = ").append(str2).append(",server_side").toString());
        new StringBuilder();
        return this.a.a(activity, sb2.append(str2).append(",server_side").toString(), iUiListener);
    }

    public int loginServerSide(Fragment fragment, String str, IUiListener iUiListener) {
        StringBuilder sb;
        StringBuilder sb2;
        String str2 = str;
        new StringBuilder();
        f.c("openSDK_LOG.Tencent", sb.append("loginServerSide() with fragment, scope = ").append(str2).append(",server_side").toString());
        new StringBuilder();
        return this.a.a(fragment, sb2.append(str2).append(",server_side").toString(), iUiListener, "");
    }

    public int loginWithOEM(Activity activity, String str, IUiListener iUiListener, String str2, String str3, String str4) {
        StringBuilder sb;
        String str5 = str;
        new StringBuilder();
        f.c("openSDK_LOG.Tencent", sb.append("loginWithOEM() with activity, scope = ").append(str5).toString());
        return this.a.a(activity, str5, iUiListener, str2, str3, str4);
    }

    public void logout(Context context) {
        Context context2 = context;
        f.c("openSDK_LOG.Tencent", "logout()");
        this.a.b().setAccessToken((String) null, "0");
        this.a.b().setOpenId((String) null);
    }

    public void makeFriend(Activity activity, Bundle bundle) {
        SocialOperation socialOperation;
        new SocialOperation(getQQToken());
        socialOperation.makeFriend(activity, bundle);
    }

    public boolean onActivityResult(int i, int i2, Intent intent) {
        int i3 = i;
        int i4 = i2;
        Intent intent2 = intent;
        f.c("openSDK_LOG.Tencent", "onActivityResult() deprecated, will do nothing");
        return false;
    }

    public void publishToQzone(Activity activity, Bundle bundle, IUiListener iUiListener) {
        QzonePublish qzonePublish;
        Activity activity2 = activity;
        f.c("openSDK_LOG.Tencent", "publishToQzone()");
        new QzonePublish(activity2, this.a.b());
        qzonePublish.publishToQzone(activity2, bundle, iUiListener);
    }

    public int reAuth(Activity activity, String str, IUiListener iUiListener) {
        StringBuilder sb;
        String str2 = str;
        new StringBuilder();
        f.c("openSDK_LOG.Tencent", sb.append("reAuth() with activity, scope = ").append(str2).toString());
        return this.a.b(activity, str2, iUiListener);
    }

    public void releaseResource() {
    }

    public void reportDAU() {
        this.a.a();
    }

    public JSONObject request(String str, Bundle bundle, String str2) throws IOException, JSONException, HttpUtils.NetworkUnavailableException, HttpUtils.HttpStatusException {
        f.c("openSDK_LOG.Tencent", "request()");
        return HttpUtils.request(this.a.b(), e.a(), str, bundle, str2);
    }

    public void requestAsync(String str, Bundle bundle, String str2, IRequestListener iRequestListener, Object obj) {
        Object obj2 = obj;
        f.c("openSDK_LOG.Tencent", "requestAsync()");
        HttpUtils.requestAsync(this.a.b(), e.a(), str, bundle, str2, iRequestListener);
    }

    public void saveSession(JSONObject jSONObject) {
        this.a.b().saveSession(jSONObject);
    }

    public void setAccessToken(String str, String str2) {
        StringBuilder sb;
        String str3 = str2;
        new StringBuilder();
        f.a("openSDK_LOG.Tencent", sb.append("setAccessToken(), expiresIn = ").append(str3).append("").toString());
        this.a.a(str, str3);
    }

    public void setOpenId(String str) {
        f.a("openSDK_LOG.Tencent", "setOpenId() --start");
        this.a.a(e.a(), str);
        f.a("openSDK_LOG.Tencent", "setOpenId() --end");
    }

    public void shareToQQ(Activity activity, Bundle bundle, IUiListener iUiListener) {
        QQShare qQShare;
        Activity activity2 = activity;
        f.c("openSDK_LOG.Tencent", "shareToQQ()");
        new QQShare(activity2, this.a.b());
        qQShare.shareToQQ(activity2, bundle, iUiListener);
    }

    public void shareToQzone(Activity activity, Bundle bundle, IUiListener iUiListener) {
        QzoneShare qzoneShare;
        Activity activity2 = activity;
        f.c("openSDK_LOG.Tencent", "shareToQzone()");
        new QzoneShare(activity2, this.a.b());
        qzoneShare.shareToQzone(activity2, bundle, iUiListener);
    }

    public int story(Activity activity, Bundle bundle, IUiListener iUiListener) {
        SocialApi socialApi;
        f.c("openSDK_LOG.Tencent", "story()");
        new SocialApi(this.a.b());
        socialApi.story(activity, bundle, iUiListener);
        return 0;
    }
}
