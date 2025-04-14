package com.tencent.connect.common;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.auth.c;
import com.tencent.open.TDialog;
import com.tencent.open.a.f;
import com.tencent.open.utils.HttpUtils;
import com.tencent.open.utils.e;
import com.tencent.open.utils.h;
import com.tencent.open.utils.k;
import com.tencent.tauth.IRequestListener;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ProGuard */
public abstract class BaseApi {
    public static String businessId = null;
    public static String installChannel = null;
    public static boolean isOEM = false;
    public static String registerChannel = null;
    protected c a;
    /* access modifiers changed from: protected */
    public QQToken b;

    /* compiled from: ProGuard */
    public class TempRequestListener implements IRequestListener {
        final /* synthetic */ BaseApi a;
        /* access modifiers changed from: private */
        public final IUiListener b;
        private final Handler c;

        public TempRequestListener(BaseApi baseApi, IUiListener iUiListener) {
            Handler handler;
            BaseApi baseApi2 = baseApi;
            this.a = baseApi2;
            this.b = iUiListener;
            final BaseApi baseApi3 = baseApi2;
            new Handler(this, e.a().getMainLooper()) {
                final /* synthetic */ TempRequestListener b;

                {
                    this.b = r7;
                }

                public void handleMessage(Message message) {
                    UiError uiError;
                    Message message2 = message;
                    if (message2.what == 0) {
                        this.b.b.onComplete(message2.obj);
                        return;
                    }
                    new UiError(message2.what, (String) message2.obj, (String) null);
                    this.b.b.onError(uiError);
                }
            };
            this.c = handler;
        }

        public void onComplete(JSONObject jSONObject) {
            Message obtainMessage = this.c.obtainMessage();
            obtainMessage.obj = jSONObject;
            obtainMessage.what = 0;
            boolean sendMessage = this.c.sendMessage(obtainMessage);
        }

        public void onConnectTimeoutException(ConnectTimeoutException connectTimeoutException) {
            Message obtainMessage = this.c.obtainMessage();
            obtainMessage.obj = connectTimeoutException.getMessage();
            obtainMessage.what = -7;
            boolean sendMessage = this.c.sendMessage(obtainMessage);
        }

        public void onHttpStatusException(HttpUtils.HttpStatusException httpStatusException) {
            Message obtainMessage = this.c.obtainMessage();
            obtainMessage.obj = httpStatusException.getMessage();
            obtainMessage.what = -9;
            boolean sendMessage = this.c.sendMessage(obtainMessage);
        }

        public void onIOException(IOException iOException) {
            Message obtainMessage = this.c.obtainMessage();
            obtainMessage.obj = iOException.getMessage();
            obtainMessage.what = -2;
            boolean sendMessage = this.c.sendMessage(obtainMessage);
        }

        public void onJSONException(JSONException jSONException) {
            Message obtainMessage = this.c.obtainMessage();
            obtainMessage.obj = jSONException.getMessage();
            obtainMessage.what = -4;
            boolean sendMessage = this.c.sendMessage(obtainMessage);
        }

        public void onMalformedURLException(MalformedURLException malformedURLException) {
            Message obtainMessage = this.c.obtainMessage();
            obtainMessage.obj = malformedURLException.getMessage();
            obtainMessage.what = -3;
            boolean sendMessage = this.c.sendMessage(obtainMessage);
        }

        public void onNetworkUnavailableException(HttpUtils.NetworkUnavailableException networkUnavailableException) {
            Message obtainMessage = this.c.obtainMessage();
            obtainMessage.obj = networkUnavailableException.getMessage();
            obtainMessage.what = -10;
            boolean sendMessage = this.c.sendMessage(obtainMessage);
        }

        public void onSocketTimeoutException(SocketTimeoutException socketTimeoutException) {
            Message obtainMessage = this.c.obtainMessage();
            obtainMessage.obj = socketTimeoutException.getMessage();
            obtainMessage.what = -8;
            boolean sendMessage = this.c.sendMessage(obtainMessage);
        }

        public void onUnknowException(Exception exc) {
            Message obtainMessage = this.c.obtainMessage();
            obtainMessage.obj = exc.getMessage();
            obtainMessage.what = -6;
            boolean sendMessage = this.c.sendMessage(obtainMessage);
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public BaseApi(QQToken qQToken) {
        this((c) null, qQToken);
    }

    public BaseApi(c cVar, QQToken qQToken) {
        this.a = cVar;
        this.b = qQToken;
    }

    private Intent a(Activity activity, Intent intent) {
        Intent intent2;
        new Intent(activity.getApplicationContext(), AssistActivity.class);
        Intent intent3 = intent2;
        Intent putExtra = intent3.putExtra("is_login", true);
        Intent putExtra2 = intent3.putExtra(AssistActivity.EXTRA_INTENT, intent);
        return intent3;
    }

    /* access modifiers changed from: protected */
    public Bundle a() {
        Bundle bundle;
        StringBuilder sb;
        new Bundle();
        Bundle bundle2 = bundle;
        bundle2.putString("format", "json");
        bundle2.putString("status_os", Build.VERSION.RELEASE);
        bundle2.putString("status_machine", Build.MODEL);
        bundle2.putString("status_version", Build.VERSION.SDK);
        bundle2.putString("sdkv", Constants.SDK_VERSION);
        bundle2.putString("sdkp", "a");
        if (this.b != null && this.b.isSessionValid()) {
            bundle2.putString(Constants.PARAM_ACCESS_TOKEN, this.b.getAccessToken());
            bundle2.putString("oauth_consumer_key", this.b.getAppId());
            bundle2.putString("openid", this.b.getOpenId());
            bundle2.putString("appid_for_getting_config", this.b.getAppId());
        }
        SharedPreferences sharedPreferences = e.a().getSharedPreferences(Constants.PREFERENCE_PF, 0);
        if (isOEM) {
            new StringBuilder();
            bundle2.putString(Constants.PARAM_PLATFORM_ID, sb.append("desktop_m_qq-").append(installChannel).append("-").append("android").append("-").append(registerChannel).append("-").append(businessId).toString());
        } else {
            bundle2.putString(Constants.PARAM_PLATFORM_ID, sharedPreferences.getString(Constants.PARAM_PLATFORM_ID, Constants.DEFAULT_PF));
        }
        return bundle2;
    }

    /* access modifiers changed from: protected */
    public String a(String str) {
        StringBuilder sb;
        String str2 = str;
        Bundle a2 = a();
        new StringBuilder();
        StringBuilder sb2 = sb;
        if (!TextUtils.isEmpty(str2)) {
            a2.putString("need_version", str2);
        }
        StringBuilder append = sb2.append("http://openmobile.qq.com/oauth2.0/m_jump_by_version?");
        StringBuilder append2 = sb2.append(HttpUtils.encodeUrl(a2));
        return sb2.toString();
    }

    /* access modifiers changed from: protected */
    public void a(Activity activity, int i, Intent intent, boolean z) {
        Intent intent2;
        Activity activity2 = activity;
        int i2 = i;
        Intent intent3 = intent;
        new Intent(activity2.getApplicationContext(), AssistActivity.class);
        Intent intent4 = intent2;
        if (z) {
            Intent putExtra = intent4.putExtra("is_qq_mobile_share", true);
        }
        Intent putExtra2 = intent4.putExtra(AssistActivity.EXTRA_INTENT, intent3);
        activity2.startActivityForResult(intent4, i2);
    }

    /* access modifiers changed from: protected */
    public void a(Activity activity, Intent intent, int i) {
        Activity activity2 = activity;
        Intent intent2 = intent;
        int i2 = i;
        Intent putExtra = intent2.putExtra(Constants.KEY_REQUEST_CODE, i2);
        activity2.startActivityForResult(a(activity2, intent2), i2);
    }

    /* access modifiers changed from: protected */
    public void a(Activity activity, Bundle bundle, IUiListener iUiListener) {
        StringBuilder sb;
        TDialog tDialog;
        IUiListener iUiListener2 = iUiListener;
        f.c("openSDK_LOG.BaseApi", "--handleDownloadLastestQQ");
        new StringBuilder();
        StringBuilder sb2 = sb;
        StringBuilder append = sb2.append("http://qzs.qq.com/open/mobile/login/qzsjump.html?");
        StringBuilder append2 = sb2.append(HttpUtils.encodeUrl(bundle));
        String sb3 = sb2.toString();
        new TDialog(activity, "", sb3, (IUiListener) null, this.b);
        tDialog.show();
    }

    /* access modifiers changed from: protected */
    public void a(Fragment fragment, Intent intent, int i) {
        Fragment fragment2 = fragment;
        Intent intent2 = intent;
        int i2 = i;
        Intent putExtra = intent2.putExtra(Constants.KEY_REQUEST_CODE, i2);
        fragment2.startActivityForResult(a(fragment2.getActivity(), intent2), i2);
    }

    /* access modifiers changed from: protected */
    public boolean a(Intent intent) {
        Intent intent2 = intent;
        if (intent2 != null) {
            return h.a(e.a(), intent2);
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public Intent b(String str) {
        Intent intent;
        String str2 = str;
        new Intent();
        Intent intent2 = intent;
        if (k.d(e.a())) {
            Intent className = intent2.setClassName(Constants.PACKAGE_QQ_PAD, str2);
            if (h.a(e.a(), intent2)) {
                return intent2;
            }
        }
        Intent className2 = intent2.setClassName("com.tencent.mobileqq", str2);
        if (h.a(e.a(), intent2)) {
            return intent2;
        }
        Intent className3 = intent2.setClassName(Constants.PACKAGE_TIM, str2);
        if (h.a(e.a(), intent2)) {
            return intent2;
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public Bundle b() {
        Bundle bundle;
        StringBuilder sb;
        new Bundle();
        Bundle bundle2 = bundle;
        bundle2.putString("appid", this.b.getAppId());
        if (this.b.isSessionValid()) {
            bundle2.putString(Constants.PARAM_KEY_STR, this.b.getAccessToken());
            bundle2.putString(Constants.PARAM_KEY_TYPE, "0x80");
        }
        String openId = this.b.getOpenId();
        if (openId != null) {
            bundle2.putString("hopenid", openId);
        }
        bundle2.putString(Constants.PARAM_PLATFORM, "androidqz");
        SharedPreferences sharedPreferences = e.a().getSharedPreferences(Constants.PREFERENCE_PF, 0);
        if (isOEM) {
            new StringBuilder();
            bundle2.putString(Constants.PARAM_PLATFORM_ID, sb.append("desktop_m_qq-").append(installChannel).append("-").append("android").append("-").append(registerChannel).append("-").append(businessId).toString());
        } else {
            bundle2.putString(Constants.PARAM_PLATFORM_ID, sharedPreferences.getString(Constants.PARAM_PLATFORM_ID, Constants.DEFAULT_PF));
            bundle2.putString(Constants.PARAM_PLATFORM_ID, Constants.DEFAULT_PF);
        }
        bundle2.putString("sdkv", Constants.SDK_VERSION);
        bundle2.putString("sdkp", "a");
        return bundle2;
    }

    /* access modifiers changed from: protected */
    public Intent c(String str) {
        Intent intent;
        new Intent();
        Intent intent2 = intent;
        Intent b2 = b(str);
        if (b2 == null) {
            intent2 = null;
        } else if (b2.getComponent() != null) {
            Intent className = intent2.setClassName(b2.getComponent().getPackageName(), "com.tencent.open.agent.AgentActivity");
        } else {
            intent2 = null;
        }
        return intent2;
    }

    public void releaseResource() {
    }
}
