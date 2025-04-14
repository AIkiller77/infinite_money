package com.tencent.connect.share;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Base64;
import com.tencent.connect.a.a;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.Constants;
import com.tencent.open.TDialog;
import com.tencent.open.a.f;
import com.tencent.open.b.d;
import com.tencent.open.utils.e;
import com.tencent.open.utils.k;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Set;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class QzonePublish extends BaseApi {
    public static final String HULIAN_CALL_BACK = "hulian_call_back";
    public static final String HULIAN_EXTRA_SCENE = "hulian_extra_scene";
    public static final String PUBLISH_TO_QZONE_APP_NAME = "appName";
    public static final String PUBLISH_TO_QZONE_EXTMAP = "extMap";
    public static final String PUBLISH_TO_QZONE_IMAGE_URL = "imageUrl";
    public static final String PUBLISH_TO_QZONE_KEY_TYPE = "req_type";
    public static final String PUBLISH_TO_QZONE_SUMMARY = "summary";
    public static final int PUBLISH_TO_QZONE_TYPE_PUBLISHMOOD = 3;
    public static final int PUBLISH_TO_QZONE_TYPE_PUBLISHVIDEO = 4;
    public static final String PUBLISH_TO_QZONE_VIDEO_DURATION = "videoDuration";
    public static final String PUBLISH_TO_QZONE_VIDEO_PATH = "videoPath";
    public static final String PUBLISH_TO_QZONE_VIDEO_SIZE = "videoSize";

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public QzonePublish(Context context, QQToken qQToken) {
        super(qQToken);
        Context context2 = context;
    }

    /* access modifiers changed from: private */
    public void b(Activity activity, Bundle bundle, IUiListener iUiListener) {
        StringBuffer stringBuffer;
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        Intent intent;
        StringBuilder sb4;
        StringBuilder sb5;
        StringBuilder sb6;
        StringBuilder sb7;
        StringBuilder sb8;
        StringBuilder sb9;
        StringBuilder sb10;
        StringBuilder sb11;
        StringBuffer stringBuffer2;
        StringBuilder sb12;
        JSONObject jSONObject;
        Activity activity2 = activity;
        Bundle bundle2 = bundle;
        IUiListener iUiListener2 = iUiListener;
        f.c("openSDK_LOG.QzonePublish", "doPublishToQzone() --start");
        new StringBuffer("mqqapi://qzone/publish?src_type=app&version=1&file_type=news");
        StringBuffer stringBuffer3 = stringBuffer;
        ArrayList<String> stringArrayList = bundle2.getStringArrayList("imageUrl");
        String string = bundle2.getString("summary");
        int i = bundle2.getInt("req_type", 3);
        String string2 = bundle2.getString("appName");
        String string3 = bundle2.getString(PUBLISH_TO_QZONE_VIDEO_PATH);
        int i2 = bundle2.getInt(PUBLISH_TO_QZONE_VIDEO_DURATION);
        long j = bundle2.getLong(PUBLISH_TO_QZONE_VIDEO_SIZE);
        String str = "";
        try {
            Bundle bundle3 = bundle2.getBundle("extMap");
            if (bundle3 != null) {
                Set<String> keySet = bundle3.keySet();
                new JSONObject();
                JSONObject jSONObject2 = jSONObject;
                for (String str2 : keySet) {
                    if (!TextUtils.isEmpty(bundle3.getString(str2))) {
                        JSONObject put = jSONObject2.put(str2, bundle3.getString(str2));
                    }
                }
                if (jSONObject2.length() > 0) {
                    str = jSONObject2.toString();
                }
            }
        } catch (Exception e) {
            f.b("openSDK_LOG.QzonePublish", "publishToQzone()  --error parse extmap", e);
        }
        String appId = this.b.getAppId();
        String openId = this.b.getOpenId();
        new StringBuilder();
        f.a("openSDK_LOG.QzonePublish", sb.append("openId:").append(openId).toString());
        String str3 = "";
        if (3 == i && stringArrayList != null) {
            str3 = "7";
            new StringBuffer();
            StringBuffer stringBuffer4 = stringBuffer2;
            int size = stringArrayList.size();
            for (int i3 = 0; i3 < size; i3++) {
                StringBuffer append = stringBuffer4.append(URLEncoder.encode(stringArrayList.get(i3)));
                if (i3 != size - 1) {
                    StringBuffer append2 = stringBuffer4.append(";");
                }
            }
            new StringBuilder();
            StringBuffer append3 = stringBuffer3.append(sb12.append("&image_url=").append(Base64.encodeToString(k.i(stringBuffer4.toString()), 2)).toString());
        }
        if (4 == i) {
            str3 = Constants.VIA_SHARE_TYPE_PUBLISHVIDEO;
            new StringBuilder();
            StringBuffer append4 = stringBuffer3.append(sb9.append("&videoPath=").append(Base64.encodeToString(k.i(string3), 2)).toString());
            new StringBuilder();
            StringBuffer append5 = stringBuffer3.append(sb10.append("&videoDuration=").append(Base64.encodeToString(k.i(String.valueOf(i2)), 2)).toString());
            new StringBuilder();
            StringBuffer append6 = stringBuffer3.append(sb11.append("&videoSize=").append(Base64.encodeToString(k.i(String.valueOf(j)), 2)).toString());
        }
        if (!TextUtils.isEmpty(string)) {
            new StringBuilder();
            StringBuffer append7 = stringBuffer3.append(sb8.append("&description=").append(Base64.encodeToString(k.i(string), 2)).toString());
        }
        if (!TextUtils.isEmpty(appId)) {
            new StringBuilder();
            StringBuffer append8 = stringBuffer3.append(sb7.append("&share_id=").append(appId).toString());
        }
        if (!TextUtils.isEmpty(string2)) {
            new StringBuilder();
            StringBuffer append9 = stringBuffer3.append(sb6.append("&app_name=").append(Base64.encodeToString(k.i(string2), 2)).toString());
        }
        if (!k.e(openId)) {
            new StringBuilder();
            StringBuffer append10 = stringBuffer3.append(sb5.append("&open_id=").append(Base64.encodeToString(k.i(openId), 2)).toString());
        }
        if (!TextUtils.isEmpty(str)) {
            new StringBuilder();
            StringBuffer append11 = stringBuffer3.append(sb4.append("&share_qzone_ext_str=").append(Base64.encodeToString(k.i(str), 2)).toString());
        }
        new StringBuilder();
        StringBuffer append12 = stringBuffer3.append(sb2.append("&req_type=").append(Base64.encodeToString(k.i(String.valueOf(i)), 2)).toString());
        new StringBuilder();
        f.a("openSDK_LOG.QzonePublish", sb3.append("doPublishToQzone, url: ").append(stringBuffer3.toString()).toString());
        a.a(e.a(), this.b, "requireApi", "shareToNativeQQ");
        new Intent("android.intent.action.VIEW");
        Intent intent2 = intent;
        Intent data = intent2.setData(Uri.parse(stringBuffer3.toString()));
        Intent putExtra = intent2.putExtra("pkg_name", activity2.getPackageName());
        if (a(intent2)) {
            a(activity2, Constants.REQUEST_QZONE_SHARE, intent2, false);
            d.a().a(0, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.b.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "hasActivityForIntent success");
            d.a().a(this.b.getOpenId(), this.b.getAppId(), Constants.VIA_SHARE_TO_QZONE, Constants.VIA_REPORT_TYPE_SHARE_TO_QZONE, "3", "1", str3, "0", "1", "0");
        } else {
            f.e("openSDK_LOG.QzonePublish", "doPublishToQzone() target activity not found");
            d.a().a(1, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.b.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "hasActivityForIntent fail");
            d.a().a(this.b.getOpenId(), this.b.getAppId(), Constants.VIA_SHARE_TO_QZONE, Constants.VIA_REPORT_TYPE_SHARE_TO_QZONE, "3", "1", str3, "0", "1", "0");
        }
        f.c("openSDK_LOG", "doPublishToQzone() --end");
    }

    public void publishToQzone(Activity activity, Bundle bundle, IUiListener iUiListener) {
        StringBuilder sb;
        UiError uiError;
        MediaPlayer mediaPlayer;
        MediaPlayer.OnPreparedListener onPreparedListener;
        MediaPlayer.OnErrorListener onErrorListener;
        UiError uiError2;
        UiError uiError3;
        UiError uiError4;
        TDialog tDialog;
        UiError uiError5;
        Activity activity2 = activity;
        Bundle bundle2 = bundle;
        IUiListener iUiListener2 = iUiListener;
        f.c("openSDK_LOG.QzonePublish", "publishToQzone() -- start");
        if (bundle2 == null) {
            new UiError(-6, Constants.MSG_PARAM_NULL_ERROR, (String) null);
            iUiListener2.onError(uiError5);
            f.e("openSDK_LOG.QzonePublish", "-->publishToQzone, params is null");
            d.a().a(1, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.b.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, Constants.MSG_PARAM_NULL_ERROR);
        } else if (!k.e((Context) activity2)) {
            new UiError(-15, Constants.MSG_PARAM_VERSION_TOO_LOW, (String) null);
            iUiListener2.onError(uiError4);
            f.e("openSDK_LOG.QzonePublish", "-->publishToQzone, this is not support below qq 5.9.5");
            d.a().a(1, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.b.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "publicToQzone, this is not support below qq 5.9.5");
            new TDialog(activity2, "", a(""), (IUiListener) null, this.b);
            tDialog.show();
        } else {
            String a = k.a((Context) activity2);
            if (a == null) {
                a = bundle2.getString("appName");
            } else if (a.length() > 20) {
                new StringBuilder();
                a = sb.append(a.substring(0, 20)).append("...").toString();
            }
            if (!TextUtils.isEmpty(a)) {
                bundle2.putString("appName", a);
            }
            int i = bundle2.getInt("req_type");
            if (i == 3) {
                ArrayList<String> stringArrayList = bundle2.getStringArrayList("imageUrl");
                if (stringArrayList != null && stringArrayList.size() > 0) {
                    int i2 = 0;
                    while (i2 < stringArrayList.size()) {
                        if (!k.h(stringArrayList.get(i2))) {
                            String remove = stringArrayList.remove(i2);
                            i2--;
                        }
                        i2++;
                    }
                    bundle2.putStringArrayList("imageUrl", stringArrayList);
                }
                b(activity2, bundle2, iUiListener2);
                f.c("openSDK_LOG.QzonePublish", "publishToQzone() --end");
            } else if (i == 4) {
                String string = bundle2.getString(PUBLISH_TO_QZONE_VIDEO_PATH);
                if (!k.h(string)) {
                    f.e("openSDK_LOG.QzonePublish", "publishToQzone() video url invalid");
                    new UiError(-5, Constants.MSG_PUBLISH_VIDEO_ERROR, (String) null);
                    iUiListener2.onError(uiError3);
                    return;
                }
                new MediaPlayer();
                MediaPlayer mediaPlayer2 = mediaPlayer;
                final String str = string;
                final Bundle bundle3 = bundle2;
                final Activity activity3 = activity2;
                final IUiListener iUiListener3 = iUiListener2;
                new MediaPlayer.OnPreparedListener(this) {
                    final /* synthetic */ QzonePublish e;

                    {
                        this.e = r9;
                    }

                    public void onPrepared(MediaPlayer mediaPlayer) {
                        File file;
                        new File(str);
                        long length = file.length();
                        int duration = mediaPlayer.getDuration();
                        bundle3.putString(QzonePublish.PUBLISH_TO_QZONE_VIDEO_PATH, str);
                        bundle3.putInt(QzonePublish.PUBLISH_TO_QZONE_VIDEO_DURATION, duration);
                        bundle3.putLong(QzonePublish.PUBLISH_TO_QZONE_VIDEO_SIZE, length);
                        this.e.b(activity3, bundle3, iUiListener3);
                        f.c("openSDK_LOG.QzonePublish", "publishToQzone() --end");
                    }
                };
                mediaPlayer2.setOnPreparedListener(onPreparedListener);
                final IUiListener iUiListener4 = iUiListener2;
                new MediaPlayer.OnErrorListener(this) {
                    final /* synthetic */ QzonePublish b;

                    {
                        this.b = r6;
                    }

                    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                        UiError uiError;
                        MediaPlayer mediaPlayer2 = mediaPlayer;
                        int i3 = i;
                        int i4 = i2;
                        f.e("openSDK_LOG.QzonePublish", "publishToQzone() mediaplayer onError()");
                        new UiError(-5, Constants.MSG_PUBLISH_VIDEO_ERROR, (String) null);
                        iUiListener4.onError(uiError);
                        return false;
                    }
                };
                mediaPlayer2.setOnErrorListener(onErrorListener);
                try {
                    mediaPlayer2.setDataSource(string);
                    mediaPlayer2.prepareAsync();
                } catch (Exception e) {
                    Exception exc = e;
                    f.e("openSDK_LOG.QzonePublish", "publishToQzone() exception(s) occurred when preparing mediaplayer");
                    new UiError(-5, Constants.MSG_PUBLISH_VIDEO_ERROR, (String) null);
                    iUiListener2.onError(uiError2);
                }
            } else {
                new UiError(-5, Constants.MSG_SHARE_TYPE_ERROR, (String) null);
                iUiListener2.onError(uiError);
                f.e("openSDK_LOG.QzonePublish", "publishToQzone() error--end请选择支持的分享类型");
                d.a().a(1, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.b.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "publishToQzone() 请选择支持的分享类型");
            }
        }
    }
}
