package com.tencent.connect.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.tencent.open.SocialConstants;
import com.tencent.open.a.f;
import com.tencent.open.b.d;
import com.tencent.open.utils.k;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class AssistActivity extends Activity {
    public static final String EXTRA_INTENT = "openSDK_LOG.AssistActivity.ExtraIntent";
    protected boolean a = false;
    protected Handler b;
    private boolean c = false;
    private String d;

    public AssistActivity() {
        Handler handler;
        new Handler(this) {
            final /* synthetic */ AssistActivity a;

            {
                this.a = r5;
            }

            public void handleMessage(Message message) {
                switch (message.what) {
                    case 0:
                        if (!this.a.isFinishing()) {
                            f.d("openSDK_LOG.AssistActivity", "-->finish by timeout");
                            this.a.finish();
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        };
        this.b = handler;
    }

    private void a(Bundle bundle) {
        UiError uiError;
        Bundle bundle2 = bundle;
        String string = bundle2.getString("viaShareType");
        String string2 = bundle2.getString("callbackAction");
        String string3 = bundle2.getString(SocialConstants.PARAM_URL);
        String string4 = bundle2.getString("openId");
        String string5 = bundle2.getString("appId");
        String str = "";
        String str2 = "";
        if ("shareToQQ".equals(string2)) {
            str = Constants.VIA_SHARE_TO_QQ;
            str2 = Constants.VIA_REPORT_TYPE_SHARE_TO_QQ;
        } else if ("shareToQzone".equals(string2)) {
            str = Constants.VIA_SHARE_TO_QZONE;
            str2 = Constants.VIA_REPORT_TYPE_SHARE_TO_QZONE;
        }
        if (!k.a((Context) this, string3)) {
            IUiListener listnerWithAction = UIListenerManager.getInstance().getListnerWithAction(string2);
            if (null != listnerWithAction) {
                new UiError(-6, Constants.MSG_OPEN_BROWSER_ERROR, (String) null);
                listnerWithAction.onError(uiError);
            }
            d.a().a(string4, string5, str, str2, "3", "1", string, "0", "2", "0");
            finish();
        } else {
            d.a().a(string4, string5, str, str2, "3", "0", string, "0", "2", "0");
        }
        getIntent().removeExtra("shareH5");
    }

    public static Intent getAssistActivityIntent(Context context) {
        Intent intent;
        new Intent(context, AssistActivity.class);
        return intent;
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        StringBuilder sb;
        int i3 = i;
        int i4 = i2;
        Intent intent2 = intent;
        new StringBuilder();
        f.c("openSDK_LOG.AssistActivity", sb.append("--onActivityResult--requestCode: ").append(i3).append(" | resultCode: ").append(i4).append("data = null ? ").append(intent2 == null).toString());
        super.onActivityResult(i3, i4, intent2);
        if (i3 != 0) {
            if (intent2 != null) {
                Intent putExtra = intent2.putExtra(Constants.KEY_ACTION, "action_login");
            }
            setResultData(i3, intent2);
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        StringBuilder sb;
        Bundle bundle2 = bundle;
        boolean requestWindowFeature = requestWindowFeature(1);
        super.onCreate(bundle2);
        setRequestedOrientation(3);
        f.b("openSDK_LOG.AssistActivity", "--onCreate--");
        if (getIntent() == null) {
            f.e("openSDK_LOG.AssistActivity", "-->onCreate--getIntent() returns null");
            finish();
        }
        Intent intent = (Intent) getIntent().getParcelableExtra(EXTRA_INTENT);
        int intExtra = intent == null ? 0 : intent.getIntExtra(Constants.KEY_REQUEST_CODE, 0);
        this.d = intent == null ? "" : intent.getStringExtra("appid");
        Bundle bundleExtra = getIntent().getBundleExtra("h5_share_data");
        if (bundle2 != null) {
            this.c = bundle2.getBoolean("RESTART_FLAG");
            this.a = bundle2.getBoolean("RESUME_FLAG", false);
        }
        if (this.c) {
            f.b("openSDK_LOG.AssistActivity", "is restart");
        } else if (bundleExtra != null) {
            f.d("openSDK_LOG.AssistActivity", "--onCreate--h5 bundle not null, will open browser");
            a(bundleExtra);
        } else if (intent != null) {
            new StringBuilder();
            f.c("openSDK_LOG.AssistActivity", sb.append("--onCreate--activityIntent not null, will start activity, reqcode = ").append(intExtra).toString());
            startActivityForResult(intent, intExtra);
        } else {
            f.e("openSDK_LOG.AssistActivity", "--onCreate--activityIntent is null");
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        f.b("openSDK_LOG.AssistActivity", "-->onDestroy");
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        Intent intent2 = intent;
        f.c("openSDK_LOG.AssistActivity", "--onNewIntent");
        super.onNewIntent(intent2);
        Intent putExtra = intent2.putExtra(Constants.KEY_ACTION, "action_share");
        setResult(-1, intent2);
        if (!isFinishing()) {
            f.c("openSDK_LOG.AssistActivity", "--onNewIntent--activity not finished, finish now");
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        f.b("openSDK_LOG.AssistActivity", "-->onPause");
        this.b.removeMessages(0);
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        f.b("openSDK_LOG.AssistActivity", "-->onResume");
        super.onResume();
        Intent intent = getIntent();
        if (!intent.getBooleanExtra("is_login", false)) {
            if (!intent.getBooleanExtra("is_qq_mobile_share", false) && this.c && !isFinishing()) {
                finish();
            }
            if (this.a) {
                boolean sendMessage = this.b.sendMessage(this.b.obtainMessage(0));
                return;
            }
            this.a = true;
        }
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        Bundle bundle2 = bundle;
        f.b("openSDK_LOG.AssistActivity", "--onSaveInstanceState--");
        bundle2.putBoolean("RESTART_FLAG", true);
        bundle2.putBoolean("RESUME_FLAG", this.a);
        super.onSaveInstanceState(bundle2);
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        f.b("openSDK_LOG.AssistActivity", "-->onStart");
        super.onStart();
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        f.b("openSDK_LOG.AssistActivity", "-->onStop");
        super.onStop();
    }

    public void setResultData(int i, Intent intent) {
        StringBuilder sb;
        JSONObject jSONObject;
        int i2 = i;
        Intent intent2 = intent;
        if (intent2 == null) {
            f.d("openSDK_LOG.AssistActivity", "--setResultData--intent is null, setResult ACTIVITY_CANCEL");
            setResult(0);
            if (i2 == 11101) {
                d.a().a("", this.d, "2", "1", "7", "2");
                return;
            }
            return;
        }
        try {
            String stringExtra = intent2.getStringExtra(Constants.KEY_RESPONSE);
            new StringBuilder();
            f.b("openSDK_LOG.AssistActivity", sb.append("--setResultDataForLogin-- ").append(stringExtra).toString());
            if (!TextUtils.isEmpty(stringExtra)) {
                new JSONObject(stringExtra);
                JSONObject jSONObject2 = jSONObject;
                String optString = jSONObject2.optString("openid");
                String optString2 = jSONObject2.optString(Constants.PARAM_ACCESS_TOKEN);
                if (TextUtils.isEmpty(optString) || TextUtils.isEmpty(optString2)) {
                    f.d("openSDK_LOG.AssistActivity", "--setResultData--openid or token is empty, setResult ACTIVITY_CANCEL");
                    setResult(0, intent2);
                    d.a().a("", this.d, "2", "1", "7", "1");
                } else {
                    f.c("openSDK_LOG.AssistActivity", "--setResultData--openid and token not empty, setResult ACTIVITY_OK");
                    setResult(-1, intent2);
                    d.a().a(optString, this.d, "2", "1", "7", "0");
                }
            } else {
                f.d("openSDK_LOG.AssistActivity", "--setResultData--response is empty, setResult ACTIVITY_OK");
                setResult(-1, intent2);
            }
        } catch (Exception e) {
            f.e("openSDK_LOG.AssistActivity", "--setResultData--parse response failed");
            e.printStackTrace();
        }
    }
}
