package com.tencent.connect.share;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Base64;
import android.widget.IPhotoView;
import com.tencent.connect.a.a;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.Constants;
import com.tencent.connect.common.UIListenerManager;
import com.tencent.open.TDialog;
import com.tencent.open.a.f;
import com.tencent.open.b.d;
import com.tencent.open.utils.c;
import com.tencent.open.utils.e;
import com.tencent.open.utils.h;
import com.tencent.open.utils.k;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Set;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class QzoneShare extends BaseApi {
    public static final String SHARE_TO_QQ_APP_NAME = "appName";
    public static final String SHARE_TO_QQ_AUDIO_URL = "audio_url";
    public static final String SHARE_TO_QQ_EXT_INT = "cflag";
    public static final String SHARE_TO_QQ_EXT_STR = "share_qq_ext_str";
    public static final String SHARE_TO_QQ_IMAGE_LOCAL_URL = "imageLocalUrl";
    public static final String SHARE_TO_QQ_IMAGE_URL = "imageUrl";
    public static final String SHARE_TO_QQ_SITE = "site";
    public static final String SHARE_TO_QQ_SUMMARY = "summary";
    public static final String SHARE_TO_QQ_TARGET_URL = "targetUrl";
    public static final String SHARE_TO_QQ_TITLE = "title";
    public static final String SHARE_TO_QZONE_EXTMAP = "extMap";
    public static final String SHARE_TO_QZONE_KEY_TYPE = "req_type";
    public static final int SHARE_TO_QZONE_TYPE_APP = 6;
    public static final int SHARE_TO_QZONE_TYPE_IMAGE = 5;
    public static final int SHARE_TO_QZONE_TYPE_IMAGE_TEXT = 1;
    public static final int SHARE_TO_QZONE_TYPE_NO_TYPE = 0;
    private boolean c = true;
    private boolean d = false;
    private boolean e = false;
    private boolean f = false;
    public String mViaShareQzoneType = "";

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public QzoneShare(Context context, QQToken qQToken) {
        super(qQToken);
        Context context2 = context;
    }

    /* access modifiers changed from: private */
    public void b(Activity activity, Bundle bundle, IUiListener iUiListener) {
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
        StringBuffer stringBuffer2;
        StringBuilder sb14;
        JSONObject jSONObject;
        Activity activity2 = activity;
        Bundle bundle2 = bundle;
        IUiListener iUiListener2 = iUiListener;
        f.c("openSDK_LOG.QzoneShare", "doshareToQzone() --start");
        new StringBuffer("mqqapi://share/to_qzone?src_type=app&version=1&file_type=news");
        StringBuffer stringBuffer3 = stringBuffer;
        ArrayList<String> stringArrayList = bundle2.getStringArrayList("imageUrl");
        String string = bundle2.getString("title");
        String string2 = bundle2.getString("summary");
        String string3 = bundle2.getString("targetUrl");
        String string4 = bundle2.getString("audio_url");
        int i = bundle2.getInt("req_type", 1);
        String string5 = bundle2.getString("appName");
        int i2 = bundle2.getInt("cflag", 0);
        String string6 = bundle2.getString("share_qq_ext_str");
        String str = "";
        try {
            Bundle bundle3 = bundle2.getBundle("extMap");
            if (bundle3 != null) {
                Set<String> keySet = bundle3.keySet();
                new JSONObject();
                JSONObject jSONObject2 = jSONObject;
                for (String str2 : keySet) {
                    JSONObject put = jSONObject2.put(str2, bundle3.get(str2));
                }
                if (keySet.size() > 0) {
                    str = jSONObject2.toString();
                }
            }
        } catch (Exception e2) {
            f.b("openSDK_LOG.QzoneShare", "ShareToQzone()  --error parse extmap", e2);
        }
        String appId = this.b.getAppId();
        String openId = this.b.getOpenId();
        new StringBuilder();
        f.a("openSDK_LOG.QzoneShare", sb.append("openId:").append(openId).toString());
        if (stringArrayList != null) {
            new StringBuffer();
            StringBuffer stringBuffer4 = stringBuffer2;
            int size = stringArrayList.size() > 9 ? 9 : stringArrayList.size();
            for (int i3 = 0; i3 < size; i3++) {
                StringBuffer append = stringBuffer4.append(URLEncoder.encode(stringArrayList.get(i3)));
                if (i3 != size - 1) {
                    StringBuffer append2 = stringBuffer4.append(";");
                }
            }
            new StringBuilder();
            StringBuffer append3 = stringBuffer3.append(sb14.append("&image_url=").append(Base64.encodeToString(k.i(stringBuffer4.toString()), 2)).toString());
        }
        if (!TextUtils.isEmpty(string)) {
            new StringBuilder();
            StringBuffer append4 = stringBuffer3.append(sb13.append("&title=").append(Base64.encodeToString(k.i(string), 2)).toString());
        }
        if (!TextUtils.isEmpty(string2)) {
            new StringBuilder();
            StringBuffer append5 = stringBuffer3.append(sb12.append("&description=").append(Base64.encodeToString(k.i(string2), 2)).toString());
        }
        if (!TextUtils.isEmpty(appId)) {
            new StringBuilder();
            StringBuffer append6 = stringBuffer3.append(sb11.append("&share_id=").append(appId).toString());
        }
        if (!TextUtils.isEmpty(string3)) {
            new StringBuilder();
            StringBuffer append7 = stringBuffer3.append(sb10.append("&url=").append(Base64.encodeToString(k.i(string3), 2)).toString());
        }
        if (!TextUtils.isEmpty(string5)) {
            new StringBuilder();
            StringBuffer append8 = stringBuffer3.append(sb9.append("&app_name=").append(Base64.encodeToString(k.i(string5), 2)).toString());
        }
        if (!k.e(openId)) {
            new StringBuilder();
            StringBuffer append9 = stringBuffer3.append(sb8.append("&open_id=").append(Base64.encodeToString(k.i(openId), 2)).toString());
        }
        if (!k.e(string4)) {
            new StringBuilder();
            StringBuffer append10 = stringBuffer3.append(sb7.append("&audioUrl=").append(Base64.encodeToString(k.i(string4), 2)).toString());
        }
        new StringBuilder();
        StringBuffer append11 = stringBuffer3.append(sb2.append("&req_type=").append(Base64.encodeToString(k.i(String.valueOf(i)), 2)).toString());
        if (!k.e(string6)) {
            new StringBuilder();
            StringBuffer append12 = stringBuffer3.append(sb6.append("&share_qq_ext_str=").append(Base64.encodeToString(k.i(string6), 2)).toString());
        }
        if (!TextUtils.isEmpty(str)) {
            new StringBuilder();
            StringBuffer append13 = stringBuffer3.append(sb5.append("&share_qzone_ext_str=").append(Base64.encodeToString(k.i(str), 2)).toString());
        }
        new StringBuilder();
        StringBuffer append14 = stringBuffer3.append(sb3.append("&cflag=").append(Base64.encodeToString(k.i(String.valueOf(i2)), 2)).toString());
        new StringBuilder();
        f.a("openSDK_LOG.QzoneShare", sb4.append("doshareToQzone, url: ").append(stringBuffer3.toString()).toString());
        a.a(e.a(), this.b, "requireApi", "shareToNativeQQ");
        new Intent("android.intent.action.VIEW");
        Intent intent2 = intent;
        Intent data = intent2.setData(Uri.parse(stringBuffer3.toString()));
        Intent putExtra = intent2.putExtra("pkg_name", activity2.getPackageName());
        if (k.g(activity2, "4.6.0")) {
            if (a(intent2)) {
                Object listenerWithRequestcode = UIListenerManager.getInstance().setListenerWithRequestcode(Constants.REQUEST_OLD_QZSHARE, iUiListener2);
                a(activity2, intent2, (int) Constants.REQUEST_OLD_QZSHARE);
            }
            f.c("openSDK_LOG.QzoneShare", "doShareToQzone() -- QQ Version is < 4.6.0");
        } else {
            f.c("openSDK_LOG.QzoneShare", "doShareToQzone() -- QQ Version is > 4.6.0");
            if (null != UIListenerManager.getInstance().setListnerWithAction("shareToQzone", iUiListener2)) {
                f.c("openSDK_LOG.QzoneShare", "doShareToQzone() -- do listener onCancel()");
            }
            if (a(intent2)) {
                a(activity2, Constants.REQUEST_QZONE_SHARE, intent2, false);
            }
        }
        if (a(intent2)) {
            d.a().a(this.b.getOpenId(), this.b.getAppId(), Constants.VIA_SHARE_TO_QZONE, Constants.VIA_REPORT_TYPE_SHARE_TO_QZONE, "3", "0", this.mViaShareQzoneType, "0", "1", "0");
            d.a().a(0, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.b.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "");
        } else {
            d.a().a(this.b.getOpenId(), this.b.getAppId(), Constants.VIA_SHARE_TO_QZONE, Constants.VIA_REPORT_TYPE_SHARE_TO_QZONE, "3", "1", this.mViaShareQzoneType, "0", "1", "0");
            d.a().a(1, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.b.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "hasActivityForIntent fail");
        }
        f.c("openSDK_LOG", "doShareToQzone() --end");
    }

    public void releaseResource() {
    }

    public void shareToQzone(Activity activity, Bundle bundle, IUiListener iUiListener) {
        StringBuilder sb;
        UiError uiError;
        UiError uiError2;
        UiError uiError3;
        TDialog tDialog;
        QQShare qQShare;
        UiError uiError4;
        c cVar;
        UiError uiError5;
        UiError uiError6;
        UiError uiError7;
        UiError uiError8;
        UiError uiError9;
        StringBuilder sb2;
        UiError uiError10;
        Activity activity2 = activity;
        Bundle bundle2 = bundle;
        IUiListener iUiListener2 = iUiListener;
        f.c("openSDK_LOG.QzoneShare", "shareToQzone() -- start");
        if (bundle2 == null) {
            new UiError(-6, Constants.MSG_PARAM_NULL_ERROR, (String) null);
            iUiListener2.onError(uiError10);
            f.e("openSDK_LOG.QzoneShare", "shareToQzone() params is null");
            d.a().a(1, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.b.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, Constants.MSG_PARAM_NULL_ERROR);
            return;
        }
        String string = bundle2.getString("title");
        String string2 = bundle2.getString("summary");
        String string3 = bundle2.getString("targetUrl");
        ArrayList<String> stringArrayList = bundle2.getStringArrayList("imageUrl");
        String a = k.a((Context) activity2);
        if (a == null) {
            a = bundle2.getString("appName");
        } else if (a.length() > 20) {
            new StringBuilder();
            a = sb.append(a.substring(0, 20)).append("...").toString();
        }
        int i = bundle2.getInt("req_type");
        switch (i) {
            case 1:
                this.mViaShareQzoneType = "1";
                break;
            case 5:
                this.mViaShareQzoneType = "2";
                break;
            case 6:
                this.mViaShareQzoneType = "4";
                break;
            default:
                this.mViaShareQzoneType = "1";
                break;
        }
        switch (i) {
            case 1:
                this.c = true;
                this.d = false;
                this.e = true;
                this.f = false;
                break;
            case 5:
                new UiError(-5, Constants.MSG_SHARE_TYPE_ERROR, (String) null);
                iUiListener2.onError(uiError);
                f.e("openSDK_LOG.QzoneShare", "shareToQzone() error--end请选择支持的分享类型");
                d.a().a(1, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.b.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "shareToQzone() 请选择支持的分享类型");
                return;
            case 6:
                if (!k.g(activity2, "5.0.0")) {
                    Object[] objArr = new Object[2];
                    objArr[0] = this.b.getAppId();
                    Object[] objArr2 = objArr;
                    objArr2[1] = "mqq";
                    string3 = String.format("http://fusion.qq.com/cgi-bin/qzapps/unified_jump?appid=%1$s&from=%2$s&isOpenAppID=1", objArr2);
                    bundle2.putString("targetUrl", string3);
                    break;
                } else {
                    new UiError(-15, Constants.MSG_PARAM_APPSHARE_TOO_LOW, (String) null);
                    iUiListener2.onError(uiError2);
                    f.e("openSDK_LOG.QzoneShare", "-->shareToQzone, app share is not support below qq5.0.");
                    d.a().a(1, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.b.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "shareToQzone, app share is not support below qq5.0.");
                    return;
                }
            default:
                if (!k.e(string) || !k.e(string2)) {
                    this.c = true;
                } else if (stringArrayList == null || stringArrayList.size() == 0) {
                    new StringBuilder();
                    string = sb2.append("来自").append(a).append("的分享").toString();
                    this.c = true;
                } else {
                    this.c = false;
                }
                this.d = false;
                this.e = true;
                this.f = false;
                break;
        }
        if (k.b() || !k.g(activity2, "4.5.0")) {
            if (this.c) {
                if (TextUtils.isEmpty(string3)) {
                    new UiError(-5, Constants.MSG_PARAM_TARGETURL_NULL_ERROR, (String) null);
                    iUiListener2.onError(uiError8);
                    f.e("openSDK_LOG.QzoneShare", "shareToQzone() targetUrl null error--end");
                    d.a().a(1, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.b.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, Constants.MSG_PARAM_TARGETURL_NULL_ERROR);
                    return;
                } else if (!k.g(string3)) {
                    new UiError(-5, Constants.MSG_PARAM_TARGETURL_ERROR, (String) null);
                    iUiListener2.onError(uiError7);
                    f.e("openSDK_LOG.QzoneShare", "shareToQzone() targetUrl error--end");
                    d.a().a(1, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.b.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, Constants.MSG_PARAM_TARGETURL_ERROR);
                    return;
                }
            }
            if (this.d) {
                bundle2.putString("title", "");
                bundle2.putString("summary", "");
            } else if (!this.e || !k.e(string)) {
                if (!k.e(string) && string.length() > 200) {
                    bundle2.putString("title", k.a(string, (int) IPhotoView.DEFAULT_ZOOM_DURATION, (String) null, (String) null));
                }
                if (!k.e(string2) && string2.length() > 600) {
                    bundle2.putString("summary", k.a(string2, 600, (String) null, (String) null));
                }
            } else {
                new UiError(-6, Constants.MSG_PARAM_TITLE_NULL_ERROR, (String) null);
                iUiListener2.onError(uiError3);
                f.e("openSDK_LOG.QzoneShare", "shareToQzone() title is null--end");
                d.a().a(1, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.b.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "shareToQzone() title is null");
                return;
            }
            if (!TextUtils.isEmpty(a)) {
                bundle2.putString("appName", a);
            }
            if (stringArrayList != null && (stringArrayList == null || stringArrayList.size() != 0)) {
                int i2 = 0;
                while (i2 < stringArrayList.size()) {
                    String str = stringArrayList.get(i2);
                    if (!k.g(str) && !k.h(str)) {
                        String remove = stringArrayList.remove(i2);
                        i2--;
                    }
                    i2++;
                }
                if (stringArrayList.size() == 0) {
                    new UiError(-6, Constants.MSG_PARAM_IMAGE_URL_FORMAT_ERROR, (String) null);
                    iUiListener2.onError(uiError6);
                    f.e("openSDK_LOG.QzoneShare", "shareToQzone() MSG_PARAM_IMAGE_URL_FORMAT_ERROR--end");
                    d.a().a(1, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.b.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "shareToQzone() 非法的图片地址!");
                    return;
                }
                bundle2.putStringArrayList("imageUrl", stringArrayList);
            } else if (this.f) {
                new UiError(-6, Constants.MSG_PARAM_IMAGE_ERROR, (String) null);
                iUiListener2.onError(uiError5);
                f.e("openSDK_LOG.QzoneShare", "shareToQzone() imageUrl is null -- end");
                d.a().a(1, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.b.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "shareToQzone() imageUrl is null");
                return;
            }
            if (!k.g(activity2, "4.6.0")) {
                f.c("openSDK_LOG.QzoneShare", "shareToQzone() qqver greater than 4.6.0");
                final IUiListener iUiListener3 = iUiListener2;
                final Bundle bundle3 = bundle2;
                final Activity activity3 = activity2;
                new c(this) {
                    final /* synthetic */ QzoneShare d;

                    {
                        this.d = r8;
                    }

                    public void a(int i, String str) {
                        UiError uiError;
                        int i2 = i;
                        String str2 = str;
                        new UiError(-6, Constants.MSG_PARAM_IMAGE_URL_FORMAT_ERROR, (String) null);
                        iUiListener3.onError(uiError);
                    }

                    public void a(int i, ArrayList<String> arrayList) {
                        ArrayList<String> arrayList2 = arrayList;
                        if (i == 0) {
                            bundle3.putStringArrayList("imageUrl", arrayList2);
                        }
                        this.d.b(activity3, bundle3, iUiListener3);
                    }
                };
                a.a((Context) activity2, stringArrayList, cVar);
            } else if (h.c(activity2, "4.2.0") < 0 || h.c(activity2, "4.6.0") >= 0) {
                f.d("openSDK_LOG.QzoneShare", "shareToQzone() qqver below 4.2.0, will show download dialog");
                new TDialog(activity2, "", a(""), (IUiListener) null, this.b);
                tDialog.show();
            } else {
                f.d("openSDK_LOG.QzoneShare", "shareToQzone() qqver between 4.2.0 and 4.6.0, will use qqshare");
                new QQShare(activity2, this.b);
                QQShare qQShare2 = qQShare;
                if (stringArrayList != null && stringArrayList.size() > 0) {
                    String str2 = stringArrayList.get(0);
                    if (i != 5 || k.h(str2)) {
                        bundle2.putString("imageLocalUrl", str2);
                    } else {
                        new UiError(-6, Constants.MSG_PARAM_IMAGE_URL_MUST_BE_LOCAL, (String) null);
                        iUiListener2.onError(uiError4);
                        f.e("openSDK_LOG.QzoneShare", "shareToQzone()手Q版本过低，纯图分享不支持网路图片");
                        d.a().a(1, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.b.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "shareToQzone()手Q版本过低，纯图分享不支持网路图片");
                        return;
                    }
                }
                if (!k.g(activity2, "4.5.0")) {
                    bundle2.putInt("cflag", 1);
                }
                qQShare2.shareToQQ(activity2, bundle2, iUiListener2);
            }
            f.c("openSDK_LOG.QzoneShare", "shareToQzone() --end");
            return;
        }
        new UiError(-6, Constants.MSG_SHARE_NOSD_ERROR, (String) null);
        iUiListener2.onError(uiError9);
        f.e("openSDK_LOG.QzoneShare", "shareToQzone() sdcard is null--end");
        d.a().a(1, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.b.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, Constants.MSG_SHARE_NOSD_ERROR);
    }
}
