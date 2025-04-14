package com.tencent.open;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.Constants;
import com.tencent.open.a;
import com.tencent.open.a.f;
import com.tencent.open.c.b;
import com.tencent.open.utils.g;
import com.tencent.open.utils.k;
import com.tencent.tauth.AuthActivity;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import java.lang.ref.WeakReference;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class TDialog extends b {
    static final FrameLayout.LayoutParams c;
    static Toast d = null;
    private static WeakReference<ProgressDialog> f;
    /* access modifiers changed from: private */
    public WeakReference<Context> e;
    private String g;
    /* access modifiers changed from: private */
    public OnTimeListener h;
    private IUiListener i;
    private FrameLayout j;
    /* access modifiers changed from: private */
    public b k;
    /* access modifiers changed from: private */
    public Handler l;
    private boolean m = false;
    private QQToken n = null;

    /* compiled from: ProGuard */
    private class FbWebViewClient extends WebViewClient {
        final /* synthetic */ TDialog a;

        private FbWebViewClient(TDialog tDialog) {
            this.a = tDialog;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ FbWebViewClient(TDialog tDialog, AnonymousClass1 r7) {
            this(tDialog);
            AnonymousClass1 r2 = r7;
        }

        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            this.a.k.setVisibility(0);
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            StringBuilder sb;
            String str2 = str;
            new StringBuilder();
            f.a("openSDK_LOG.TDialog", sb.append("Webview loading URL: ").append(str2).toString());
            super.onPageStarted(webView, str2, bitmap);
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            UiError uiError;
            int i2 = i;
            String str3 = str;
            String str4 = str2;
            super.onReceivedError(webView, i2, str3, str4);
            new UiError(i2, str3, str4);
            this.a.h.onError(uiError);
            if (!(this.a.e == null || this.a.e.get() == null)) {
                Toast.makeText((Context) this.a.e.get(), "网络连接异常或系统错误", 0).show();
            }
            this.a.dismiss();
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            StringBuilder sb;
            Intent intent;
            WebView webView2 = webView;
            String str2 = str;
            new StringBuilder();
            f.a("openSDK_LOG.TDialog", sb.append("Redirect URL: ").append(str2).toString());
            if (str2.startsWith(g.a().a((Context) this.a.e.get(), "auth://tauth.qq.com/"))) {
                this.a.h.onComplete(k.c(str2));
                if (this.a.isShowing()) {
                    this.a.dismiss();
                }
                return true;
            } else if (str2.startsWith(Constants.CANCEL_URI)) {
                this.a.h.onCancel();
                if (this.a.isShowing()) {
                    this.a.dismiss();
                }
                return true;
            } else if (str2.startsWith(Constants.CLOSE_URI)) {
                if (this.a.isShowing()) {
                    this.a.dismiss();
                }
                return true;
            } else if (!str2.startsWith(Constants.DOWNLOAD_URI) && !str2.endsWith(".apk")) {
                return str2.startsWith("auth://progress");
            } else {
                try {
                    new Intent("android.intent.action.VIEW", str2.startsWith(Constants.DOWNLOAD_URI) ? Uri.parse(Uri.decode(str2.substring(Constants.DOWNLOAD_URI.length()))) : Uri.parse(Uri.decode(str2)));
                    Intent intent2 = intent;
                    Intent addFlags = intent2.addFlags(268435456);
                    if (!(this.a.e == null || this.a.e.get() == null)) {
                        ((Context) this.a.e.get()).startActivity(intent2);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            }
        }
    }

    /* compiled from: ProGuard */
    private class JsListener extends a.b {
        final /* synthetic */ TDialog a;

        private JsListener(TDialog tDialog) {
            this.a = tDialog;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ JsListener(TDialog tDialog, AnonymousClass1 r7) {
            this(tDialog);
            AnonymousClass1 r2 = r7;
        }

        public void onAddShare(String str) {
            f.b("openSDK_LOG.TDialog", "JsListener onAddShare");
            onComplete(str);
        }

        public void onCancel(String str) {
            StringBuilder sb;
            String str2 = str;
            new StringBuilder();
            f.e("openSDK_LOG.TDialog", sb.append("JsListener onCancel --msg = ").append(str2).toString());
            this.a.l.obtainMessage(2, str2).sendToTarget();
            this.a.dismiss();
        }

        public void onCancelAddShare(String str) {
            StringBuilder sb;
            new StringBuilder();
            f.e("openSDK_LOG.TDialog", sb.append("JsListener onCancelAddShare").append(str).toString());
            onCancel("cancel");
        }

        public void onCancelInvite() {
            f.e("openSDK_LOG.TDialog", "JsListener onCancelInvite");
            onCancel("");
        }

        public void onCancelLogin() {
            onCancel("");
        }

        public void onComplete(String str) {
            StringBuilder sb;
            String str2 = str;
            this.a.l.obtainMessage(1, str2).sendToTarget();
            new StringBuilder();
            f.e("openSDK_LOG.TDialog", sb.append("JsListener onComplete").append(str2).toString());
            this.a.dismiss();
        }

        public void onInvite(String str) {
            onComplete(str);
        }

        public void onLoad(String str) {
            this.a.l.obtainMessage(4, str).sendToTarget();
        }

        public void showMsg(String str) {
            this.a.l.obtainMessage(3, str).sendToTarget();
        }
    }

    /* compiled from: ProGuard */
    private static class OnTimeListener implements IUiListener {
        String a;
        String b;
        private WeakReference<Context> c;
        private String d;
        private IUiListener e;

        public OnTimeListener(Context context, String str, String str2, String str3, IUiListener iUiListener) {
            WeakReference<Context> weakReference;
            new WeakReference<>(context);
            this.c = weakReference;
            this.d = str;
            this.a = str2;
            this.b = str3;
            this.e = iUiListener;
        }

        /* access modifiers changed from: private */
        public void a(String str) {
            UiError uiError;
            String str2 = str;
            try {
                onComplete(k.d(str2));
            } catch (JSONException e2) {
                e2.printStackTrace();
                new UiError(-4, Constants.MSG_JSON_ERROR, str2);
                onError(uiError);
            }
        }

        public void onCancel() {
            if (this.e != null) {
                this.e.onCancel();
                this.e = null;
            }
        }

        public void onComplete(Object obj) {
            StringBuilder sb;
            JSONObject jSONObject = (JSONObject) obj;
            com.tencent.open.b.g a2 = com.tencent.open.b.g.a();
            new StringBuilder();
            a2.a(sb.append(this.d).append("_H5").toString(), SystemClock.elapsedRealtime(), 0, 0, jSONObject.optInt("ret", -6), this.a, false);
            if (this.e != null) {
                this.e.onComplete(jSONObject);
                this.e = null;
            }
        }

        public void onError(UiError uiError) {
            String str;
            StringBuilder sb;
            StringBuilder sb2;
            UiError uiError2 = uiError;
            if (uiError2.errorMessage != null) {
                new StringBuilder();
                str = sb2.append(uiError2.errorMessage).append(this.a).toString();
            } else {
                str = this.a;
            }
            String str2 = str;
            com.tencent.open.b.g a2 = com.tencent.open.b.g.a();
            new StringBuilder();
            a2.a(sb.append(this.d).append("_H5").toString(), SystemClock.elapsedRealtime(), 0, 0, uiError2.errorCode, str2, false);
            if (this.e != null) {
                this.e.onError(uiError2);
                this.e = null;
            }
        }
    }

    /* compiled from: ProGuard */
    private class THandler extends Handler {
        final /* synthetic */ TDialog a;
        private OnTimeListener b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public THandler(TDialog tDialog, OnTimeListener onTimeListener, Looper looper) {
            super(looper);
            this.a = tDialog;
            this.b = onTimeListener;
        }

        public void handleMessage(Message message) {
            StringBuilder sb;
            Message message2 = message;
            new StringBuilder();
            f.b("openSDK_LOG.TDialog", sb.append("--handleMessage--msg.WHAT = ").append(message2.what).toString());
            switch (message2.what) {
                case 1:
                    this.b.a((String) message2.obj);
                    return;
                case 2:
                    this.b.onCancel();
                    return;
                case 3:
                    if (this.a.e != null && this.a.e.get() != null) {
                        TDialog.c((Context) this.a.e.get(), (String) message2.obj);
                        return;
                    }
                    return;
                case 5:
                    if (this.a.e != null && this.a.e.get() != null) {
                        TDialog.d((Context) this.a.e.get(), (String) message2.obj);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    static {
        FrameLayout.LayoutParams layoutParams;
        new FrameLayout.LayoutParams(-1, -1);
        c = layoutParams;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public TDialog(android.content.Context r16, java.lang.String r17, java.lang.String r18, com.tencent.tauth.IUiListener r19, com.tencent.connect.auth.QQToken r20) {
        /*
            r15 = this;
            r0 = r15
            r1 = r16
            r2 = r17
            r3 = r18
            r4 = r19
            r5 = r20
            r6 = r0
            r7 = r1
            r8 = 16973840(0x1030010, float:2.4060945E-38)
            r6.<init>(r7, r8)
            r6 = r0
            r7 = 0
            r6.m = r7
            r6 = r0
            r7 = 0
            r6.n = r7
            r6 = r0
            java.lang.ref.WeakReference r7 = new java.lang.ref.WeakReference
            r14 = r7
            r7 = r14
            r8 = r14
            r9 = r1
            r8.<init>(r9)
            r6.e = r7
            r6 = r0
            r7 = r3
            r6.g = r7
            r6 = r0
            com.tencent.open.TDialog$OnTimeListener r7 = new com.tencent.open.TDialog$OnTimeListener
            r14 = r7
            r7 = r14
            r8 = r14
            r9 = r1
            r10 = r2
            r11 = r3
            r12 = r5
            java.lang.String r12 = r12.getAppId()
            r13 = r4
            r8.<init>(r9, r10, r11, r12, r13)
            r6.h = r7
            r6 = r0
            com.tencent.open.TDialog$THandler r7 = new com.tencent.open.TDialog$THandler
            r14 = r7
            r7 = r14
            r8 = r14
            r9 = r0
            r10 = r0
            com.tencent.open.TDialog$OnTimeListener r10 = r10.h
            r11 = r1
            android.os.Looper r11 = r11.getMainLooper()
            r8.<init>(r9, r10, r11)
            r6.l = r7
            r6 = r0
            r7 = r4
            r6.i = r7
            r6 = r0
            r7 = r5
            r6.n = r7
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.open.TDialog.<init>(android.content.Context, java.lang.String, java.lang.String, com.tencent.tauth.IUiListener, com.tencent.connect.auth.QQToken):void");
    }

    private void a() {
        TextView textView;
        FrameLayout.LayoutParams layoutParams;
        b bVar;
        FrameLayout frameLayout;
        new TextView((Context) this.e.get());
        textView.setText("test");
        new FrameLayout.LayoutParams(-1, -1);
        FrameLayout.LayoutParams layoutParams2 = layoutParams;
        new b((Context) this.e.get());
        this.k = bVar;
        this.k.setLayoutParams(layoutParams2);
        new FrameLayout((Context) this.e.get());
        this.j = frameLayout;
        layoutParams2.gravity = 17;
        this.j.setLayoutParams(layoutParams2);
        this.j.addView(this.k);
        setContentView(this.j);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private void b() {
        WebViewClient webViewClient;
        a.b bVar;
        this.k.setVerticalScrollBarEnabled(false);
        this.k.setHorizontalScrollBarEnabled(false);
        new FbWebViewClient(this, (AnonymousClass1) null);
        this.k.setWebViewClient(webViewClient);
        this.k.setWebChromeClient(this.b);
        this.k.clearFormData();
        WebSettings settings = this.k.getSettings();
        if (settings != null) {
            settings.setSavePassword(false);
            settings.setSaveFormData(false);
            settings.setCacheMode(-1);
            settings.setNeedInitialFocus(false);
            settings.setBuiltInZoomControls(true);
            settings.setSupportZoom(true);
            settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
            settings.setJavaScriptEnabled(true);
            if (!(this.e == null || this.e.get() == null)) {
                settings.setDatabaseEnabled(true);
                settings.setDatabasePath(((Context) this.e.get()).getApplicationContext().getDir("databases", 0).getPath());
            }
            settings.setDomStorageEnabled(true);
            new JsListener(this, (AnonymousClass1) null);
            this.a.a(bVar, "sdk_js_if");
            this.k.loadUrl(this.g);
            this.k.setLayoutParams(c);
            this.k.setVisibility(4);
            this.k.getSettings().setSavePassword(false);
        }
    }

    /* access modifiers changed from: private */
    public static void c(Context context, String str) {
        Context context2 = context;
        try {
            JSONObject d2 = k.d(str);
            int i2 = d2.getInt(SocialConstants.PARAM_TYPE);
            String string = d2.getString("msg");
            if (i2 == 0) {
                if (d == null) {
                    d = Toast.makeText(context2, string, 0);
                } else {
                    d.setView(d.getView());
                    d.setText(string);
                    d.setDuration(0);
                }
                d.show();
            } else if (i2 == 1) {
                if (d == null) {
                    d = Toast.makeText(context2, string, 1);
                } else {
                    d.setView(d.getView());
                    d.setText(string);
                    d.setDuration(1);
                }
                d.show();
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public static void d(Context context, String str) {
        ProgressDialog progressDialog;
        WeakReference<ProgressDialog> weakReference;
        Context context2 = context;
        String str2 = str;
        if (context2 != null && str2 != null) {
            try {
                JSONObject d2 = k.d(str2);
                int i2 = d2.getInt(AuthActivity.ACTION_KEY);
                String string = d2.getString("msg");
                if (i2 == 1) {
                    if (f == null || f.get() == null) {
                        new ProgressDialog(context2);
                        ProgressDialog progressDialog2 = progressDialog;
                        progressDialog2.setMessage(string);
                        new WeakReference(progressDialog2);
                        f = weakReference;
                        progressDialog2.show();
                    } else {
                        ((ProgressDialog) f.get()).setMessage(string);
                        if (!((ProgressDialog) f.get()).isShowing()) {
                            ((ProgressDialog) f.get()).show();
                        }
                    }
                } else if (i2 == 0) {
                    if (f != null) {
                        if (f.get() != null && ((ProgressDialog) f.get()).isShowing()) {
                            ((ProgressDialog) f.get()).dismiss();
                            f = null;
                        }
                    }
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void a(String str) {
        String str2 = str;
        f.b("openSDK_LOG.TDialog", "--onConsoleMessage--");
        try {
            boolean a = this.a.a((WebView) this.k, str2);
        } catch (Exception e2) {
            Exception exc = e2;
        }
    }

    public void onBackPressed() {
        if (this.h != null) {
            this.h.onCancel();
        }
        super.onBackPressed();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        boolean requestWindowFeature = requestWindowFeature(1);
        super.onCreate(bundle);
        a();
        b();
    }
}
