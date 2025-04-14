package com.tencent.connect.share;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Base64;
import com.tencent.connect.a.a;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.Constants;
import com.tencent.connect.common.UIListenerManager;
import com.tencent.open.TDialog;
import com.tencent.open.a.f;
import com.tencent.open.b.d;
import com.tencent.open.utils.b;
import com.tencent.open.utils.c;
import com.tencent.open.utils.e;
import com.tencent.open.utils.k;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import java.io.File;
import java.util.ArrayList;

/* compiled from: ProGuard */
public class QQShare extends BaseApi {
    public static final int QQ_SHARE_SUMMARY_MAX_LENGTH = 512;
    public static final int QQ_SHARE_TITLE_MAX_LENGTH = 128;
    public static final String SHARE_TO_QQ_APP_NAME = "appName";
    public static final String SHARE_TO_QQ_ARK_INFO = "share_to_qq_ark_info";
    public static final String SHARE_TO_QQ_AUDIO_URL = "audio_url";
    public static final String SHARE_TO_QQ_EXT_INT = "cflag";
    public static final String SHARE_TO_QQ_EXT_STR = "share_qq_ext_str";
    public static final int SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN = 1;
    public static final int SHARE_TO_QQ_FLAG_QZONE_ITEM_HIDE = 2;
    public static final String SHARE_TO_QQ_IMAGE_LOCAL_URL = "imageLocalUrl";
    public static final String SHARE_TO_QQ_IMAGE_URL = "imageUrl";
    public static final String SHARE_TO_QQ_KEY_TYPE = "req_type";
    public static final String SHARE_TO_QQ_SITE = "site";
    public static final String SHARE_TO_QQ_SUMMARY = "summary";
    public static final String SHARE_TO_QQ_TARGET_URL = "targetUrl";
    public static final String SHARE_TO_QQ_TITLE = "title";
    public static final int SHARE_TO_QQ_TYPE_APP = 6;
    public static final int SHARE_TO_QQ_TYPE_AUDIO = 2;
    public static final int SHARE_TO_QQ_TYPE_DEFAULT = 1;
    public static final int SHARE_TO_QQ_TYPE_IMAGE = 5;
    public String mViaShareQQType = "";

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public QQShare(Context context, QQToken qQToken) {
        super(qQToken);
        Context context2 = context;
    }

    private void b(Activity activity, Bundle bundle, IUiListener iUiListener) {
        StringBuilder sb;
        c cVar;
        b bVar;
        c cVar2;
        UiError uiError;
        Activity activity2 = activity;
        Bundle bundle2 = bundle;
        IUiListener iUiListener2 = iUiListener;
        f.c("openSDK_LOG.QQShare", "shareToMobileQQ() -- start.");
        String string = bundle2.getString("imageUrl");
        String string2 = bundle2.getString("title");
        String string3 = bundle2.getString("summary");
        new StringBuilder();
        f.a("openSDK_LOG.QQShare", sb.append("shareToMobileQQ -- imageUrl: ").append(string).toString());
        if (TextUtils.isEmpty(string)) {
            c(activity2, bundle2, iUiListener2);
        } else if (!k.g(string)) {
            bundle2.putString("imageUrl", (String) null);
            if (k.f(activity2, "4.3.0")) {
                f.b("openSDK_LOG.QQShare", "shareToMobileQQ -- QQ Version is < 4.3.0 ");
                c(activity2, bundle2, iUiListener2);
            } else {
                f.b("openSDK_LOG.QQShare", "shareToMobileQQ -- QQ Version is > 4.3.0 ");
                final Bundle bundle3 = bundle2;
                final String str = string2;
                final String str2 = string3;
                final IUiListener iUiListener3 = iUiListener2;
                final Activity activity3 = activity2;
                new c(this) {
                    final /* synthetic */ QQShare f;

                    {
                        this.f = r10;
                    }

                    public void a(int i, String str) {
                        UiError uiError;
                        String str2 = str;
                        if (i == 0) {
                            bundle3.putString("imageLocalUrl", str2);
                        } else if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
                            if (iUiListener3 != null) {
                                new UiError(-6, Constants.MSG_SHARE_GETIMG_ERROR, (String) null);
                                iUiListener3.onError(uiError);
                                f.e("openSDK_LOG.QQShare", "shareToMobileQQ -- error: 获取分享图片失败!");
                            }
                            d.a().a(1, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.f.b.getAppId(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, Constants.MSG_SHARE_GETIMG_ERROR);
                            return;
                        }
                        this.f.c(activity3, bundle3, iUiListener3);
                    }

                    public void a(int i, ArrayList<String> arrayList) {
                    }
                };
                a.a((Context) activity2, string, cVar);
            }
        } else if (TextUtils.isEmpty(string2) && TextUtils.isEmpty(string3)) {
            if (iUiListener2 != null) {
                new UiError(-6, Constants.MSG_SHARE_NOSD_ERROR, (String) null);
                iUiListener2.onError(uiError);
                f.e("openSDK_LOG.QQShare", Constants.MSG_SHARE_NOSD_ERROR);
            }
            d.a().a(1, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.b.getAppId(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, Constants.MSG_SHARE_NOSD_ERROR);
            return;
        } else if (!k.f(activity2, "4.3.0")) {
            c(activity2, bundle2, iUiListener2);
        } else {
            new b(activity2);
            final Bundle bundle4 = bundle2;
            final String str3 = string2;
            final String str4 = string3;
            final IUiListener iUiListener4 = iUiListener2;
            final Activity activity4 = activity2;
            new c(this) {
                final /* synthetic */ QQShare f;

                {
                    this.f = r10;
                }

                public void a(int i, String str) {
                    UiError uiError;
                    String str2 = str;
                    if (i == 0) {
                        bundle4.putString("imageLocalUrl", str2);
                    } else if (TextUtils.isEmpty(str3) && TextUtils.isEmpty(str4)) {
                        if (iUiListener4 != null) {
                            new UiError(-6, Constants.MSG_SHARE_GETIMG_ERROR, (String) null);
                            iUiListener4.onError(uiError);
                            f.e("openSDK_LOG.QQShare", "shareToMobileQQ -- error: 获取分享图片失败!");
                        }
                        d.a().a(1, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.f.b.getAppId(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, Constants.MSG_SHARE_GETIMG_ERROR);
                        return;
                    }
                    this.f.c(activity4, bundle4, iUiListener4);
                }

                public void a(int i, ArrayList<String> arrayList) {
                }
            };
            bVar.a(string, cVar2);
        }
        f.c("openSDK_LOG.QQShare", "shareToMobileQQ() -- end");
    }

    /* access modifiers changed from: private */
    public void c(Activity activity, Bundle bundle, IUiListener iUiListener) {
        StringBuffer stringBuffer;
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        StringBuilder sb4;
        Intent intent;
        StringBuilder sb5;
        StringBuilder sb6;
        StringBuilder sb7;
        StringBuilder sb8;
        StringBuilder sb9;
        StringBuilder sb10;
        StringBuilder sb11;
        StringBuilder sb12;
        StringBuilder sb13;
        StringBuilder sb14;
        StringBuilder sb15;
        StringBuilder sb16;
        Activity activity2 = activity;
        Bundle bundle2 = bundle;
        IUiListener iUiListener2 = iUiListener;
        f.c("openSDK_LOG.QQShare", "doShareToQQ() -- start");
        new StringBuffer("mqqapi://share/to_fri?src_type=app&version=1&file_type=news");
        StringBuffer stringBuffer2 = stringBuffer;
        String string = bundle2.getString("imageUrl");
        String string2 = bundle2.getString("title");
        String string3 = bundle2.getString("summary");
        String string4 = bundle2.getString("targetUrl");
        String string5 = bundle2.getString("audio_url");
        int i = bundle2.getInt("req_type", 1);
        String string6 = bundle2.getString(SHARE_TO_QQ_ARK_INFO);
        int i2 = bundle2.getInt("cflag", 0);
        String string7 = bundle2.getString("share_qq_ext_str");
        String a = k.a((Context) activity2);
        if (a == null) {
            a = bundle2.getString("appName");
        }
        String string8 = bundle2.getString("imageLocalUrl");
        String appId = this.b.getAppId();
        String openId = this.b.getOpenId();
        new StringBuilder();
        f.a("openSDK_LOG.QQShare", sb.append("doShareToQQ -- openid: ").append(openId).toString());
        if (!TextUtils.isEmpty(string)) {
            new StringBuilder();
            StringBuffer append = stringBuffer2.append(sb16.append("&image_url=").append(Base64.encodeToString(k.i(string), 2)).toString());
        }
        if (!TextUtils.isEmpty(string8)) {
            new StringBuilder();
            StringBuffer append2 = stringBuffer2.append(sb15.append("&file_data=").append(Base64.encodeToString(k.i(string8), 2)).toString());
        }
        if (!TextUtils.isEmpty(string2)) {
            new StringBuilder();
            StringBuffer append3 = stringBuffer2.append(sb14.append("&title=").append(Base64.encodeToString(k.i(string2), 2)).toString());
        }
        if (!TextUtils.isEmpty(string3)) {
            new StringBuilder();
            StringBuffer append4 = stringBuffer2.append(sb13.append("&description=").append(Base64.encodeToString(k.i(string3), 2)).toString());
        }
        if (!TextUtils.isEmpty(appId)) {
            new StringBuilder();
            StringBuffer append5 = stringBuffer2.append(sb12.append("&share_id=").append(appId).toString());
        }
        if (!TextUtils.isEmpty(string4)) {
            new StringBuilder();
            StringBuffer append6 = stringBuffer2.append(sb11.append("&url=").append(Base64.encodeToString(k.i(string4), 2)).toString());
        }
        if (!TextUtils.isEmpty(a)) {
            String str = a;
            if (a.length() > 20) {
                new StringBuilder();
                str = sb10.append(a.substring(0, 20)).append("...").toString();
            }
            new StringBuilder();
            StringBuffer append7 = stringBuffer2.append(sb9.append("&app_name=").append(Base64.encodeToString(k.i(str), 2)).toString());
        }
        if (!TextUtils.isEmpty(openId)) {
            new StringBuilder();
            StringBuffer append8 = stringBuffer2.append(sb8.append("&open_id=").append(Base64.encodeToString(k.i(openId), 2)).toString());
        }
        if (!TextUtils.isEmpty(string5)) {
            new StringBuilder();
            StringBuffer append9 = stringBuffer2.append(sb7.append("&audioUrl=").append(Base64.encodeToString(k.i(string5), 2)).toString());
        }
        new StringBuilder();
        StringBuffer append10 = stringBuffer2.append(sb2.append("&req_type=").append(Base64.encodeToString(k.i(String.valueOf(i)), 2)).toString());
        if (!TextUtils.isEmpty(string6)) {
            new StringBuilder();
            StringBuffer append11 = stringBuffer2.append(sb6.append("&share_to_qq_ark_info=").append(Base64.encodeToString(k.i(string6), 2)).toString());
        }
        if (!TextUtils.isEmpty(string7)) {
            new StringBuilder();
            StringBuffer append12 = stringBuffer2.append(sb5.append("&share_qq_ext_str=").append(Base64.encodeToString(k.i(string7), 2)).toString());
        }
        new StringBuilder();
        StringBuffer append13 = stringBuffer2.append(sb3.append("&cflag=").append(Base64.encodeToString(k.i(String.valueOf(i2)), 2)).toString());
        new StringBuilder();
        f.a("openSDK_LOG.QQShare", sb4.append("doShareToQQ -- url: ").append(stringBuffer2.toString()).toString());
        a.a(e.a(), this.b, "requireApi", "shareToNativeQQ");
        new Intent("android.intent.action.VIEW");
        Intent intent2 = intent;
        Intent data = intent2.setData(Uri.parse(stringBuffer2.toString()));
        Intent putExtra = intent2.putExtra("pkg_name", activity2.getPackageName());
        if (k.f(activity2, "4.6.0")) {
            f.c("openSDK_LOG.QQShare", "doShareToQQ, qqver below 4.6.");
            if (a(intent2)) {
                Object listenerWithRequestcode = UIListenerManager.getInstance().setListenerWithRequestcode(Constants.REQUEST_OLD_SHARE, iUiListener2);
                a(activity2, intent2, (int) Constants.REQUEST_OLD_SHARE);
            }
        } else {
            f.c("openSDK_LOG.QQShare", "doShareToQQ, qqver greater than 4.6.");
            if (null != UIListenerManager.getInstance().setListnerWithAction("shareToQQ", iUiListener2)) {
                f.c("openSDK_LOG.QQShare", "doShareToQQ, last listener is not null, cancel it.");
            }
            if (a(intent2)) {
                a(activity2, Constants.REQUEST_QQ_SHARE, intent2, true);
            }
        }
        String str2 = Constants.VIA_REPORT_TYPE_SHARE_TO_QQ;
        if (i2 == 1) {
            str2 = Constants.VIA_REPORT_TYPE_SHARE_TO_QZONE;
        }
        if (a(intent2)) {
            d.a().a(this.b.getOpenId(), this.b.getAppId(), Constants.VIA_SHARE_TO_QQ, str2, "3", "0", this.mViaShareQQType, "0", "1", "0");
            d.a().a(0, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.b.getAppId(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "");
        } else {
            d.a().a(this.b.getOpenId(), this.b.getAppId(), Constants.VIA_SHARE_TO_QQ, str2, "3", "1", this.mViaShareQQType, "0", "1", "0");
            d.a().a(1, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.b.getAppId(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "hasActivityForIntent fail");
        }
        f.c("openSDK_LOG.QQShare", "doShareToQQ() --end");
    }

    public void releaseResource() {
    }

    public void shareToQQ(Activity activity, Bundle bundle, IUiListener iUiListener) {
        StringBuilder sb;
        UiError uiError;
        TDialog tDialog;
        File file;
        UiError uiError2;
        UiError uiError3;
        UiError uiError4;
        UiError uiError5;
        UiError uiError6;
        UiError uiError7;
        UiError uiError8;
        Activity activity2 = activity;
        Bundle bundle2 = bundle;
        IUiListener iUiListener2 = iUiListener;
        f.c("openSDK_LOG.QQShare", "shareToQQ() -- start.");
        String string = bundle2.getString("imageUrl");
        String string2 = bundle2.getString("title");
        String string3 = bundle2.getString("summary");
        String string4 = bundle2.getString("targetUrl");
        String string5 = bundle2.getString("imageLocalUrl");
        int i = bundle2.getInt("req_type", 1);
        new StringBuilder();
        f.c("openSDK_LOG.QQShare", sb.append("shareToQQ -- type: ").append(i).toString());
        switch (i) {
            case 1:
                this.mViaShareQQType = "1";
                break;
            case 2:
                this.mViaShareQQType = "3";
                break;
            case 5:
                this.mViaShareQQType = "2";
                break;
            case 6:
                this.mViaShareQQType = "4";
                break;
        }
        if (i == 6) {
            if (k.f(activity2, "5.0.0")) {
                new UiError(-15, Constants.MSG_PARAM_APPSHARE_TOO_LOW, (String) null);
                iUiListener2.onError(uiError8);
                f.e("openSDK_LOG.QQShare", "shareToQQ, app share is not support below qq5.0.");
                d.a().a(1, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.b.getAppId(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "shareToQQ, app share is not support below qq5.0.");
                return;
            }
            Object[] objArr = new Object[2];
            objArr[0] = this.b.getAppId();
            Object[] objArr2 = objArr;
            objArr2[1] = "mqq";
            string4 = String.format("http://fusion.qq.com/cgi-bin/qzapps/unified_jump?appid=%1$s&from=%2$s&isOpenAppID=1", objArr2);
            bundle2.putString("targetUrl", string4);
        }
        if (k.b() || !k.f(activity2, "4.5.0")) {
            if (i == 5) {
                if (k.f(activity2, "4.3.0")) {
                    new UiError(-6, Constants.MSG_PARAM_QQ_VERSION_ERROR, (String) null);
                    iUiListener2.onError(uiError6);
                    f.e("openSDK_LOG.QQShare", "shareToQQ, version below 4.3 is not support.");
                    d.a().a(1, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.b.getAppId(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "shareToQQ, version below 4.3 is not support.");
                    return;
                } else if (!k.h(string5)) {
                    new UiError(-6, Constants.MSG_PARAM_IMAGE_URL_FORMAT_ERROR, (String) null);
                    iUiListener2.onError(uiError5);
                    f.e("openSDK_LOG.QQShare", "shareToQQ -- error: 非法的图片地址!");
                    d.a().a(1, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.b.getAppId(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, Constants.MSG_PARAM_IMAGE_URL_FORMAT_ERROR);
                    return;
                }
            }
            if (i != 5) {
                if (TextUtils.isEmpty(string4) || (!string4.startsWith("http://") && !string4.startsWith("https://"))) {
                    new UiError(-6, Constants.MSG_PARAM_ERROR, (String) null);
                    iUiListener2.onError(uiError3);
                    f.e("openSDK_LOG.QQShare", "shareToQQ, targetUrl is empty or illegal..");
                    d.a().a(1, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.b.getAppId(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "shareToQQ, targetUrl is empty or illegal..");
                    return;
                } else if (TextUtils.isEmpty(string2)) {
                    new UiError(-6, Constants.MSG_PARAM_TITLE_NULL_ERROR, (String) null);
                    iUiListener2.onError(uiError4);
                    f.e("openSDK_LOG.QQShare", "shareToQQ, title is empty.");
                    d.a().a(1, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.b.getAppId(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "shareToQQ, title is empty.");
                    return;
                }
            }
            if (!TextUtils.isEmpty(string) && !string.startsWith("http://") && !string.startsWith("https://")) {
                new File(string);
                if (!file.exists()) {
                    new UiError(-6, Constants.MSG_PARAM_IMAGE_URL_FORMAT_ERROR, (String) null);
                    iUiListener2.onError(uiError2);
                    f.e("openSDK_LOG.QQShare", "shareToQQ, image url is emprty or illegal.");
                    d.a().a(1, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.b.getAppId(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "shareToQQ, image url is emprty or illegal.");
                    return;
                }
            }
            if (!TextUtils.isEmpty(string2) && string2.length() > 128) {
                bundle2.putString("title", k.a(string2, 128, (String) null, (String) null));
            }
            if (!TextUtils.isEmpty(string3) && string3.length() > 512) {
                bundle2.putString("summary", k.a(string3, 512, (String) null, (String) null));
            }
            if (k.a((Context) activity2, bundle2.getInt("cflag", 0) == 1)) {
                f.c("openSDK_LOG.QQShare", "shareToQQ, support share");
                b(activity2, bundle2, iUiListener2);
            } else {
                try {
                    f.d("openSDK_LOG.QQShare", "shareToQQ, don't support share, will show download dialog");
                    new TDialog(activity2, "", a(""), (IUiListener) null, this.b);
                    tDialog.show();
                } catch (RuntimeException e) {
                    RuntimeException runtimeException = e;
                    f.b("openSDK_LOG.QQShare", " shareToQQ, TDialog.show not in main thread", runtimeException);
                    runtimeException.printStackTrace();
                    new UiError(-6, Constants.MSG_NOT_CALL_ON_MAIN_THREAD, (String) null);
                    iUiListener2.onError(uiError);
                }
            }
            f.c("openSDK_LOG.QQShare", "shareToQQ() -- end.");
            return;
        }
        new UiError(-6, Constants.MSG_SHARE_NOSD_ERROR, (String) null);
        iUiListener2.onError(uiError7);
        f.e("openSDK_LOG.QQShare", "shareToQQ sdcard is null--end");
        d.a().a(1, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.b.getAppId(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "shareToQQ sdcard is null");
    }
}
