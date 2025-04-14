package com.tencent.tauth;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.tencent.connect.common.AssistActivity;
import com.tencent.connect.common.Constants;
import com.tencent.connect.common.UIListenerManager;
import com.tencent.open.a.f;
import com.tencent.open.utils.h;
import com.tencent.open.utils.k;

/* compiled from: ProGuard */
public class AuthActivity extends Activity {
    public static final String ACTION_KEY = "action";
    public static final String ACTION_SHARE_PRIZE = "sharePrize";
    private static int a = 0;

    public AuthActivity() {
    }

    private void a(Uri uri) {
        StringBuilder sb;
        Intent intent;
        Bundle bundle;
        Uri uri2 = uri;
        f.c("openSDK_LOG.AuthActivity", "-->handleActionUri--start");
        if (null == uri2 || null == uri2.toString() || uri2.toString().equals("")) {
            f.d("openSDK_LOG.AuthActivity", "-->handleActionUri, uri invalid");
            finish();
            return;
        }
        String uri3 = uri2.toString();
        Bundle a2 = k.a(uri3.substring(uri3.indexOf("#") + 1));
        if (null == a2) {
            f.d("openSDK_LOG.AuthActivity", "-->handleActionUri, bundle is null");
            finish();
            return;
        }
        String string = a2.getString(ACTION_KEY);
        new StringBuilder();
        f.c("openSDK_LOG.AuthActivity", sb.append("-->handleActionUri, action: ").append(string).toString());
        if (null == string) {
            finish();
        } else if (string.equals("shareToQQ") || string.equals("shareToQzone") || string.equals("sendToMyComputer") || string.equals("shareToTroopBar")) {
            if (string.equals("shareToQzone") && h.a((Context) this, "com.tencent.mobileqq") != null && h.c(this, "5.2.0") < 0) {
                a++;
                if (a == 2) {
                    a = 0;
                    finish();
                    return;
                }
            }
            f.c("openSDK_LOG.AuthActivity", "-->handleActionUri, most share action, start assistactivity");
            new Intent(this, AssistActivity.class);
            Intent intent2 = intent;
            Intent putExtras = intent2.putExtras(a2);
            Intent flags = intent2.setFlags(603979776);
            startActivity(intent2);
            finish();
        } else if (string.equals("addToQQFavorites")) {
            Intent intent3 = getIntent();
            Intent putExtras2 = intent3.putExtras(a2);
            Intent putExtra = intent3.putExtra(Constants.KEY_ACTION, "action_share");
            IUiListener listnerWithAction = UIListenerManager.getInstance().getListnerWithAction(string);
            if (listnerWithAction != null) {
                UIListenerManager.getInstance().handleDataToListener(intent3, listnerWithAction);
            }
            finish();
        } else if (string.equals(ACTION_SHARE_PRIZE)) {
            Intent launchIntentForPackage = getPackageManager().getLaunchIntentForPackage(getPackageName());
            String str = "";
            try {
                str = k.d(a2.getString("response")).getString("activityid");
            } catch (Exception e) {
                f.b("openSDK_LOG.AuthActivity", "sharePrize parseJson has exception.", e);
            }
            if (!TextUtils.isEmpty(str)) {
                Intent putExtra2 = launchIntentForPackage.putExtra(ACTION_SHARE_PRIZE, true);
                new Bundle();
                Bundle bundle2 = bundle;
                bundle2.putString("activityid", str);
                Intent putExtras3 = launchIntentForPackage.putExtras(bundle2);
            }
            startActivity(launchIntentForPackage);
            finish();
        } else {
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        StringBuilder sb;
        StringBuilder sb2;
        super.onCreate(bundle);
        if (null == getIntent()) {
            f.d("openSDK_LOG.AuthActivity", "-->onCreate, getIntent() return null");
            finish();
            return;
        }
        Uri uri = null;
        try {
            uri = getIntent().getData();
        } catch (Exception e) {
            new StringBuilder();
            f.e("openSDK_LOG.AuthActivity", sb.append("-->onCreate, getIntent().getData() has exception! ").append(e.getMessage()).toString());
        }
        new StringBuilder();
        f.a("openSDK_LOG.AuthActivity", sb2.append("-->onCreate, uri: ").append(uri).toString());
        a(uri);
    }
}
