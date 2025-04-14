package com.tencent.open;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.auth.c;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.Constants;
import com.tencent.connect.common.UIListenerManager;
import com.tencent.open.a.f;
import com.tencent.open.c.b;
import com.tencent.open.utils.HttpUtils;
import com.tencent.open.utils.e;
import com.tencent.open.utils.g;
import com.tencent.open.utils.h;
import com.tencent.open.utils.k;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class SocialApiIml extends BaseApi {
    /* access modifiers changed from: private */
    public Activity c;

    /* compiled from: ProGuard */
    private class a implements IUiListener {
        final /* synthetic */ SocialApiIml a;
        private IUiListener b;
        private String c;
        private String d;
        private Bundle e;
        private Activity f;

        a(SocialApiIml socialApiIml, Activity activity, IUiListener iUiListener, String str, String str2, Bundle bundle) {
            Activity activity2 = activity;
            this.a = socialApiIml;
            this.b = iUiListener;
            this.c = str;
            this.d = str2;
            this.e = bundle;
        }

        public void onCancel() {
            this.b.onCancel();
        }

        public void onComplete(Object obj) {
            String str = null;
            try {
                str = ((JSONObject) obj).getString(SocialConstants.PARAM_ENCRY_EOKEN);
            } catch (JSONException e2) {
                JSONException jSONException = e2;
                jSONException.printStackTrace();
                f.b("openSDK_LOG.SocialApiIml", "OpenApi, EncrytokenListener() onComplete error", jSONException);
            }
            this.e.putString("encrytoken", str);
            this.a.a((Context) this.a.c, this.c, this.e, this.d, this.b);
            if (TextUtils.isEmpty(str)) {
                f.b("openSDK_LOG.SocialApiIml", "The token get from qq or qzone is empty. Write temp token to localstorage.");
                this.a.writeEncryToken(this.f);
            }
        }

        public void onError(UiError uiError) {
            StringBuilder sb;
            UiError uiError2 = uiError;
            new StringBuilder();
            f.b("openSDK_LOG.SocialApiIml", sb.append("OpenApi, EncryptTokenListener() onError").append(uiError2.errorMessage).toString());
            this.b.onError(uiError2);
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SocialApiIml(QQToken qQToken) {
        super(qQToken);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SocialApiIml(c cVar, QQToken qQToken) {
        super(cVar, qQToken);
    }

    private void a(Activity activity, Intent intent, String str, Bundle bundle, IUiListener iUiListener) {
        StringBuilder sb;
        Intent intent2 = intent;
        String str2 = str;
        new StringBuilder();
        f.c("openSDK_LOG.SocialApiIml", sb.append("-->handleIntentWithAgent action = ").append(str2).toString());
        Intent putExtra = intent2.putExtra(Constants.KEY_ACTION, str2);
        Intent putExtra2 = intent2.putExtra(Constants.KEY_PARAMS, bundle);
        Object listenerWithRequestcode = UIListenerManager.getInstance().setListenerWithRequestcode(Constants.REQUEST_SOCIAL_API, iUiListener);
        a(activity, intent2, (int) Constants.REQUEST_SOCIAL_API);
    }

    private void a(Activity activity, Intent intent, String str, Bundle bundle, String str2, IUiListener iUiListener, boolean z) {
        StringBuilder sb;
        Activity activity2 = activity;
        Intent intent2 = intent;
        String str3 = str;
        Bundle bundle2 = bundle;
        String str4 = str2;
        IUiListener iUiListener2 = iUiListener;
        boolean z2 = z;
        new StringBuilder();
        f.c("openSDK_LOG.SocialApiIml", sb.append("-->handleIntent action = ").append(str3).append(", activityIntent = null ? ").append(intent2 == null).toString());
        if (intent2 != null) {
            a(activity2, intent2, str3, bundle2, iUiListener2);
            return;
        }
        if (z2 || com.tencent.open.utils.f.a(e.a(), this.b.getAppId()).b("C_LoginH5")) {
            a(activity2, str3, bundle2, str4, iUiListener2);
        } else {
            a(activity2, bundle2, iUiListener2);
        }
    }

    private void a(Activity activity, String str, Bundle bundle, IUiListener iUiListener) {
        Activity activity2 = activity;
        String str2 = str;
        Bundle bundle2 = bundle;
        IUiListener iUiListener2 = iUiListener;
        this.c = activity2;
        Intent c2 = c(SocialConstants.ACTIVITY_FRIEND_CHOOSER);
        if (c2 == null) {
            f.c("openSDK_LOG.SocialApiIml", "--askgift--friend chooser not found");
            c2 = c(SocialConstants.ACTIVITY_ASK_GIFT);
        }
        bundle2.putAll(b());
        if (SocialConstants.ACTION_ASK.equals(str2)) {
            bundle2.putString(SocialConstants.PARAM_TYPE, SocialConstants.TYPE_REQUEST);
        } else if (SocialConstants.ACTION_GIFT.equals(str2)) {
            bundle2.putString(SocialConstants.PARAM_TYPE, SocialConstants.TYPE_FREEGIFT);
        }
        a(activity2, c2, str2, bundle2, g.a().a(e.a(), "http://qzs.qq.com/open/mobile/request/sdk_request.html?"), iUiListener2, false);
    }

    private void a(Activity activity, String str, Bundle bundle, String str2, IUiListener iUiListener) {
        StringBuilder sb;
        a aVar;
        StringBuilder sb2;
        JSONObject jSONObject;
        Activity activity2 = activity;
        String str3 = str;
        new StringBuilder();
        f.c("openSDK_LOG.SocialApiIml", sb.append("-->handleIntentWithH5 action = ").append(str3).toString());
        Intent b = b("com.tencent.open.agent.AgentActivity");
        new a(this, activity2, iUiListener, str3, str2, bundle);
        a aVar2 = aVar;
        Intent b2 = b("com.tencent.open.agent.EncryTokenActivity");
        if (b2 == null || b == null || b.getComponent() == null || b2.getComponent() == null || !b.getComponent().getPackageName().equals(b2.getComponent().getPackageName())) {
            f.c("openSDK_LOG.SocialApiIml", "-->handleIntentWithH5--token activity not found");
            new StringBuilder();
            String f = k.f(sb2.append("tencent&sdk&qazxc***14969%%").append(this.b.getAccessToken()).append(this.b.getAppId()).append(this.b.getOpenId()).append("qzone3.4").toString());
            new JSONObject();
            JSONObject jSONObject2 = jSONObject;
            try {
                JSONObject put = jSONObject2.put(SocialConstants.PARAM_ENCRY_EOKEN, f);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            aVar2.onComplete(jSONObject2);
            return;
        }
        Intent putExtra = b2.putExtra("oauth_consumer_key", this.b.getAppId());
        Intent putExtra2 = b2.putExtra("openid", this.b.getOpenId());
        Intent putExtra3 = b2.putExtra(Constants.PARAM_ACCESS_TOKEN, this.b.getAccessToken());
        Intent putExtra4 = b2.putExtra(Constants.KEY_ACTION, SocialConstants.ACTION_CHECK_TOKEN);
        if (a(b2)) {
            f.c("openSDK_LOG.SocialApiIml", "-->handleIntentWithH5--found token activity");
            Object listenerWithRequestcode = UIListenerManager.getInstance().setListenerWithRequestcode(Constants.REQUEST_SOCIAL_H5, aVar2);
            a(activity2, b2, (int) Constants.REQUEST_SOCIAL_H5);
        }
    }

    /* access modifiers changed from: private */
    public void a(Context context, String str, Bundle bundle, String str2, IUiListener iUiListener) {
        StringBuilder sb;
        c cVar;
        TDialog tDialog;
        String str3 = str;
        Bundle bundle2 = bundle;
        String str4 = str2;
        IUiListener iUiListener2 = iUiListener;
        f.a("openSDK_LOG.SocialApiIml", "OpenUi, showDialog --start");
        CookieSyncManager createInstance = CookieSyncManager.createInstance(context);
        bundle2.putString("oauth_consumer_key", this.b.getAppId());
        if (this.b.isSessionValid()) {
            bundle2.putString(Constants.PARAM_ACCESS_TOKEN, this.b.getAccessToken());
        }
        String openId = this.b.getOpenId();
        if (openId != null) {
            bundle2.putString("openid", openId);
        }
        try {
            bundle2.putString(Constants.PARAM_PLATFORM_ID, e.a().getSharedPreferences(Constants.PREFERENCE_PF, 0).getString(Constants.PARAM_PLATFORM_ID, Constants.DEFAULT_PF));
        } catch (Exception e) {
            e.printStackTrace();
            bundle2.putString(Constants.PARAM_PLATFORM_ID, Constants.DEFAULT_PF);
        }
        new StringBuilder();
        StringBuilder sb2 = sb;
        StringBuilder append = sb2.append(str4);
        StringBuilder append2 = sb2.append(HttpUtils.encodeUrl(bundle2));
        String sb3 = sb2.toString();
        f.b("openSDK_LOG.SocialApiIml", "OpenUi, showDialog TDialog");
        if (SocialConstants.ACTION_CHALLENGE.equals(str3) || SocialConstants.ACTION_BRAG.equals(str3)) {
            f.b("openSDK_LOG.SocialApiIml", "OpenUi, showDialog PKDialog");
            new c(this.c, str3, sb3, iUiListener2, this.b);
            cVar.show();
            return;
        }
        new TDialog(this.c, str3, sb3, iUiListener2, this.b);
        tDialog.show();
    }

    public void ask(Activity activity, Bundle bundle, IUiListener iUiListener) {
        a(activity, SocialConstants.ACTION_ASK, bundle, iUiListener);
    }

    /* access modifiers changed from: protected */
    public Intent b(String str) {
        Intent intent;
        Intent intent2;
        Intent intent3;
        String str2 = str;
        new Intent();
        Intent intent4 = intent;
        Intent className = intent4.setClassName(Constants.PACKAGE_QZONE, str2);
        new Intent();
        Intent intent5 = intent2;
        Intent className2 = intent5.setClassName("com.tencent.mobileqq", str2);
        new Intent();
        Intent intent6 = intent3;
        Intent className3 = intent6.setClassName(Constants.PACKAGE_QQ_PAD, str2);
        if (k.d(e.a()) && h.a(e.a(), intent6)) {
            return intent6;
        }
        if (h.a(e.a(), intent5) && h.c(e.a(), "4.7") >= 0) {
            return intent5;
        }
        if (!h.a(e.a(), intent4) || h.a(h.a(e.a(), Constants.PACKAGE_QZONE), "4.2") < 0) {
            return null;
        }
        return h.a(e.a(), intent4.getComponent().getPackageName(), Constants.SIGNATRUE_QZONE) ? intent4 : null;
    }

    public void gift(Activity activity, Bundle bundle, IUiListener iUiListener) {
        a(activity, SocialConstants.ACTION_GIFT, bundle, iUiListener);
    }

    public void invite(Activity activity, Bundle bundle, IUiListener iUiListener) {
        Activity activity2 = activity;
        Bundle bundle2 = bundle;
        IUiListener iUiListener2 = iUiListener;
        this.c = activity2;
        Intent c2 = c(SocialConstants.ACTIVITY_FRIEND_CHOOSER);
        if (c2 == null) {
            f.c("openSDK_LOG.SocialApiIml", "--invite--friend chooser not found");
            c2 = c(SocialConstants.ACTIVITY_INVITE);
        }
        bundle2.putAll(b());
        a(activity2, c2, SocialConstants.ACTION_INVITE, bundle2, g.a().a(e.a(), "http://qzs.qq.com/open/mobile/invite/sdk_invite.html?"), iUiListener2, false);
    }

    public void story(Activity activity, Bundle bundle, IUiListener iUiListener) {
        Activity activity2 = activity;
        Bundle bundle2 = bundle;
        this.c = activity2;
        Intent c2 = c(SocialConstants.ACTIVITY_STORY);
        bundle2.putAll(b());
        a(activity2, c2, SocialConstants.ACTION_STORY, bundle2, g.a().a(e.a(), "http://qzs.qq.com/open/mobile/sendstory/sdk_sendstory_v1.3.html?"), iUiListener, false);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    public void writeEncryToken(Context context) {
        b bVar;
        StringBuilder sb;
        StringBuilder sb2;
        Context context2 = context;
        String str = "tencent&sdk&qazxc***14969%%";
        String accessToken = this.b.getAccessToken();
        String appId = this.b.getAppId();
        String openId = this.b.getOpenId();
        String str2 = "qzone3.4";
        String str3 = null;
        if (accessToken != null && accessToken.length() > 0 && appId != null && appId.length() > 0 && openId != null && openId.length() > 0) {
            new StringBuilder();
            str3 = k.f(sb2.append(str).append(accessToken).append(appId).append(openId).append(str2).toString());
        }
        new b(context2);
        b bVar2 = bVar;
        WebSettings settings = bVar2.getSettings();
        settings.setDomStorageEnabled(true);
        settings.setJavaScriptEnabled(true);
        settings.setDatabaseEnabled(true);
        new StringBuilder();
        String sb3 = sb.append("<!DOCTYPE HTML><html lang=\"en-US\"><head><meta charset=\"UTF-8\"><title>localStorage Test</title><script type=\"text/javascript\">document.domain = 'qq.com';localStorage[\"").append(this.b.getOpenId()).append("_").append(this.b.getAppId()).append("\"]=\"").append(str3).append("\";</script></head><body></body></html>").toString();
        String a2 = g.a().a(context2, "http://qzs.qq.com");
        bVar2.loadDataWithBaseURL(a2, sb3, "text/html", "utf-8", a2);
    }
}
