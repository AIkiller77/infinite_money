package com.tencent.open;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.widget.Toast;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.Constants;
import com.tencent.open.a.f;
import com.tencent.open.b.d;
import com.tencent.open.utils.HttpUtils;
import com.tencent.open.utils.k;
import com.tencent.tauth.IUiListener;

/* compiled from: ProGuard */
public class SocialOperation extends BaseApi {
    public static final String GAME_FRIEND_ADD_MESSAGE = "add_msg";
    public static final String GAME_FRIEND_LABEL = "friend_label";
    public static final String GAME_FRIEND_OPENID = "fopen_id";
    public static final String GAME_SIGNATURE = "signature";
    public static final String GAME_UNION_ID = "unionid";
    public static final String GAME_UNION_NAME = "union_name";
    public static final String GAME_ZONE_ID = "zoneid";

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SocialOperation(QQToken qQToken) {
        super(qQToken);
    }

    private void a(Activity activity) {
        a(activity, "");
    }

    private void a(Activity activity, String str) {
        TDialog tDialog;
        new TDialog(activity, "", a(str), (IUiListener) null, this.b);
        tDialog.show();
    }

    public void bindQQGroup(Activity activity, Bundle bundle) {
        StringBuffer stringBuffer;
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        StringBuilder sb4;
        StringBuilder sb5;
        StringBuilder sb6;
        StringBuilder sb7;
        Intent intent;
        StringBuilder sb8;
        Activity activity2 = activity;
        Bundle bundle2 = bundle;
        f.c("openSDK_LOG.GameAppOperation", "-->bindQQGroup()  -- start");
        if (null == activity2) {
            f.e("openSDK_LOG.GameAppOperation", "-->bindQQGroup, activity is empty.");
            d.a().a(this.b.getOpenId(), this.b.getAppId(), Constants.VIA_BIND_GROUP, "18", "18", "1");
        } else if (bundle2 == null) {
            Toast.makeText(activity2, "Bundle参数为空", 0).show();
            f.e("openSDK_LOG.GameAppOperation", "-->bindQQGroup, params is empty.");
            d.a().a(this.b.getOpenId(), this.b.getAppId(), Constants.VIA_BIND_GROUP, "18", "18", "1");
        } else {
            String a = k.a((Context) activity2);
            new StringBuffer("mqqapi://gamesdk/bind_group?src_type=app&version=1");
            StringBuffer stringBuffer2 = stringBuffer;
            if (!TextUtils.isEmpty(a)) {
                new StringBuilder();
                StringBuffer append = stringBuffer2.append(sb8.append("&app_name=").append(Base64.encodeToString(k.i(a), 2)).toString());
            }
            String string = bundle2.getString(GAME_UNION_ID);
            if (TextUtils.isEmpty(string)) {
                Toast.makeText(activity2, "游戏公会ID为空", 0).show();
                f.e("openSDK_LOG.GameAppOperation", "-->bindQQGroup, game union id is empty.");
                d.a().a(this.b.getOpenId(), this.b.getAppId(), Constants.VIA_BIND_GROUP, "18", "18", "1");
                return;
            }
            new StringBuilder();
            StringBuffer append2 = stringBuffer2.append(sb.append("&unionid=").append(Base64.encodeToString(k.i(string), 2)).toString());
            String string2 = bundle2.getString(GAME_UNION_NAME);
            if (TextUtils.isEmpty(string2)) {
                Toast.makeText(activity2, "游戏公会名称为空", 0).show();
                f.e("openSDK_LOG.GameAppOperation", "-->bindQQGroup, game union name is empty.");
                d.a().a(this.b.getOpenId(), this.b.getAppId(), Constants.VIA_BIND_GROUP, "18", "18", "1");
                return;
            }
            new StringBuilder();
            StringBuffer append3 = stringBuffer2.append(sb2.append("&union_name=").append(Base64.encodeToString(k.i(string2), 2)).toString());
            String string3 = bundle2.getString(GAME_ZONE_ID);
            if (TextUtils.isEmpty(string3)) {
                Toast.makeText(activity2, "游戏区域ID为空", 0).show();
                f.e("openSDK_LOG.GameAppOperation", "-->bindQQGroup, game zone id  is empty.");
                d.a().a(this.b.getOpenId(), this.b.getAppId(), Constants.VIA_BIND_GROUP, "18", "18", "1");
                return;
            }
            new StringBuilder();
            StringBuffer append4 = stringBuffer2.append(sb3.append("&zoneid=").append(Base64.encodeToString(k.i(string3), 2)).toString());
            String string4 = bundle2.getString(GAME_SIGNATURE);
            if (TextUtils.isEmpty(string4)) {
                Toast.makeText(activity2, "游戏签名为空", 0).show();
                f.e("openSDK_LOG.GameAppOperation", "-->bindQQGroup, game signature is empty.");
                d.a().a(this.b.getOpenId(), this.b.getAppId(), Constants.VIA_BIND_GROUP, "18", "18", "1");
                return;
            }
            new StringBuilder();
            StringBuffer append5 = stringBuffer2.append(sb4.append("&signature=").append(Base64.encodeToString(k.i(string4), 2)).toString());
            String openId = this.b.getOpenId();
            if (!TextUtils.isEmpty(openId)) {
                new StringBuilder();
                StringBuffer append6 = stringBuffer2.append(sb5.append("&openid=").append(Base64.encodeToString(k.i(openId), 2)).toString());
                Bundle b = b();
                for (String str : b.keySet()) {
                    b.putString(str, Base64.encodeToString(k.i(b.getString(str)), 2));
                }
                new StringBuilder();
                StringBuffer append7 = stringBuffer2.append(sb6.append("&").append(HttpUtils.encodeUrl(b)).toString());
                new StringBuilder();
                f.a("openSDK_LOG.GameAppOperation", sb7.append("-->bindQQGroup, url: ").append(stringBuffer2.toString()).toString());
                new Intent("android.intent.action.VIEW");
                Intent intent2 = intent;
                Intent data = intent2.setData(Uri.parse(stringBuffer2.toString()));
                if (!a(intent2) || k.f(activity2, "5.1.0")) {
                    f.d("openSDK_LOG.GameAppOperation", "-->bind group, there is no activity, show download page.");
                    d.a().a(this.b.getOpenId(), this.b.getAppId(), Constants.VIA_BIND_GROUP, "18", "18", "1");
                    a(activity2);
                } else {
                    f.c("openSDK_LOG.GameAppOperation", "-->bingQQGroup target activity found, qqver > 5.1.0");
                    try {
                        activity2.startActivity(intent2);
                        d.a().a(this.b.getOpenId(), this.b.getAppId(), Constants.VIA_BIND_GROUP, "18", "18", "0");
                    } catch (Exception e) {
                        f.b("openSDK_LOG.GameAppOperation", "-->bind group, start activity exception.", e);
                        d.a().a(this.b.getOpenId(), this.b.getAppId(), Constants.VIA_BIND_GROUP, "18", "18", "1");
                        a(activity2);
                    }
                }
                f.c("openSDK_LOG.GameAppOperation", "-->bindQQGroup()  -- end");
                return;
            }
            Toast.makeText(activity2, "Openid为空", 0).show();
            f.e("openSDK_LOG.GameAppOperation", "-->bindQQGroup, openid is empty.");
            d.a().a(this.b.getOpenId(), this.b.getAppId(), Constants.VIA_BIND_GROUP, "18", "18", "1");
        }
    }

    public void makeFriend(Activity activity, Bundle bundle) {
        StringBuilder sb;
        StringBuffer stringBuffer;
        StringBuilder sb2;
        StringBuilder sb3;
        Intent intent;
        StringBuilder sb4;
        StringBuilder sb5;
        StringBuilder sb6;
        StringBuilder sb7;
        StringBuilder sb8;
        Activity activity2 = activity;
        Bundle bundle2 = bundle;
        f.c("openSDK_LOG.GameAppOperation", "-->makeFriend()  -- start");
        if (bundle2 == null) {
            f.e("openSDK_LOG.GameAppOperation", "-->makeFriend params is null");
            d.a().a(this.b.getOpenId(), this.b.getAppId(), Constants.VIA_MAKE_FRIEND, Constants.VIA_REPORT_TYPE_MAKE_FRIEND, "18", "1");
            return;
        }
        String string = bundle2.getString(GAME_FRIEND_OPENID);
        if (TextUtils.isEmpty(string)) {
            f.e("openSDK_LOG.GameAppOperation", "-->make friend, fOpenid is empty.");
            d.a().a(this.b.getOpenId(), this.b.getAppId(), Constants.VIA_MAKE_FRIEND, Constants.VIA_REPORT_TYPE_MAKE_FRIEND, "18", "1");
            return;
        }
        String string2 = bundle2.getString(GAME_FRIEND_LABEL);
        String string3 = bundle2.getString(GAME_FRIEND_ADD_MESSAGE);
        String a = k.a((Context) activity2);
        String openId = this.b.getOpenId();
        String appId = this.b.getAppId();
        new StringBuilder();
        f.a("openSDK_LOG.GameAppOperation", sb.append("-->make friend, fOpenid: ").append(string).append(" | label: ").append(string2).append(" | message: ").append(string3).append(" | openid: ").append(openId).append(" | appid:").append(appId).toString());
        new StringBuffer("mqqapi://gamesdk/add_friend?src_type=app&version=1");
        StringBuffer stringBuffer2 = stringBuffer;
        new StringBuilder();
        StringBuffer append = stringBuffer2.append(sb2.append("&fopen_id=").append(Base64.encodeToString(k.i(string), 2)).toString());
        if (!TextUtils.isEmpty(openId)) {
            new StringBuilder();
            StringBuffer append2 = stringBuffer2.append(sb8.append("&open_id=").append(Base64.encodeToString(k.i(openId), 2)).toString());
        }
        if (!TextUtils.isEmpty(appId)) {
            new StringBuilder();
            StringBuffer append3 = stringBuffer2.append(sb7.append("&app_id=").append(appId).toString());
        }
        if (!TextUtils.isEmpty(string2)) {
            new StringBuilder();
            StringBuffer append4 = stringBuffer2.append(sb6.append("&friend_label=").append(Base64.encodeToString(k.i(string2), 2)).toString());
        }
        if (!TextUtils.isEmpty(string3)) {
            new StringBuilder();
            StringBuffer append5 = stringBuffer2.append(sb5.append("&add_msg=").append(Base64.encodeToString(k.i(string3), 2)).toString());
        }
        if (!TextUtils.isEmpty(a)) {
            new StringBuilder();
            StringBuffer append6 = stringBuffer2.append(sb4.append("&app_name=").append(Base64.encodeToString(k.i(a), 2)).toString());
        }
        new StringBuilder();
        f.a("openSDK_LOG.GameAppOperation", sb3.append("-->make friend, url: ").append(stringBuffer2.toString()).toString());
        new Intent("android.intent.action.VIEW");
        Intent intent2 = intent;
        Intent data = intent2.setData(Uri.parse(stringBuffer2.toString()));
        if (!a(intent2) || k.f(activity2, "5.1.0")) {
            f.d("openSDK_LOG.GameAppOperation", "-->make friend, there is no activity.");
            a(activity2);
            d.a().a(this.b.getOpenId(), this.b.getAppId(), Constants.VIA_MAKE_FRIEND, Constants.VIA_REPORT_TYPE_MAKE_FRIEND, "18", "1");
        } else {
            f.c("openSDK_LOG.GameAppOperation", "-->makeFriend target activity found, qqver greater than 5.1.0");
            try {
                activity2.startActivity(intent2);
                d.a().a(this.b.getOpenId(), this.b.getAppId(), Constants.VIA_MAKE_FRIEND, Constants.VIA_REPORT_TYPE_MAKE_FRIEND, "18", "0");
            } catch (Exception e) {
                f.b("openSDK_LOG.GameAppOperation", "-->make friend, start activity exception.", e);
                a(activity2);
                d.a().a(this.b.getOpenId(), this.b.getAppId(), Constants.VIA_MAKE_FRIEND, Constants.VIA_REPORT_TYPE_MAKE_FRIEND, "18", "1");
            }
        }
        f.c("openSDK_LOG.GameAppOperation", "-->makeFriend()  -- end");
    }
}
