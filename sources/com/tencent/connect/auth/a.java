package com.tencent.connect.auth;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.tencent.connect.auth.b;
import com.tencent.connect.common.Constants;
import com.tencent.open.SocialConstants;
import com.tencent.open.a;
import com.tencent.open.a.f;
import com.tencent.open.b.g;
import com.tencent.open.utils.HttpUtils;
import com.tencent.open.utils.k;
import com.tencent.open.web.security.JniInterface;
import com.tencent.open.web.security.SecureJsInterface;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class a extends Dialog {
    /* access modifiers changed from: private */
    public String a;
    /* access modifiers changed from: private */
    public b b;
    private IUiListener c;
    /* access modifiers changed from: private */
    public Handler d;
    private FrameLayout e;
    private LinearLayout f;
    /* access modifiers changed from: private */
    public FrameLayout g;
    private ProgressBar h;
    private String i;
    /* access modifiers changed from: private */
    public com.tencent.open.c.c j;
    /* access modifiers changed from: private */
    public Context k;
    /* access modifiers changed from: private */
    public com.tencent.open.web.security.b l;
    /* access modifiers changed from: private */
    public boolean m = false;
    /* access modifiers changed from: private */
    public int n;
    /* access modifiers changed from: private */
    public String o;
    private String p;
    /* access modifiers changed from: private */

    /* renamed from: q  reason: collision with root package name */
    public long f28q = 0;
    /* access modifiers changed from: private */
    public long r = 30000;
    /* access modifiers changed from: private */
    public HashMap<String, Runnable> s;

    /* renamed from: com.tencent.connect.auth.a$a  reason: collision with other inner class name */
    /* compiled from: ProGuard */
    private class C0006a extends WebViewClient {
        final /* synthetic */ a a;

        private C0006a(a aVar) {
            this.a = aVar;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ C0006a(a aVar, AnonymousClass1 r7) {
            this(aVar);
            AnonymousClass1 r2 = r7;
        }

        public void onPageFinished(WebView webView, String str) {
            StringBuilder sb;
            String str2 = str;
            super.onPageFinished(webView, str2);
            new StringBuilder();
            f.a("openSDK_LOG.AuthDialog", sb.append("-->onPageFinished, url: ").append(str2).toString());
            this.a.g.setVisibility(8);
            if (null != this.a.j) {
                this.a.j.setVisibility(0);
            }
            if (!TextUtils.isEmpty(str2)) {
                this.a.d.removeCallbacks((Runnable) this.a.s.remove(str2));
            }
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            StringBuilder sb;
            Runnable runnable;
            String str2 = str;
            new StringBuilder();
            f.a("openSDK_LOG.AuthDialog", sb.append("-->onPageStarted, url: ").append(str2).toString());
            super.onPageStarted(webView, str2, bitmap);
            this.a.g.setVisibility(0);
            long a2 = a.a(this.a, SystemClock.elapsedRealtime());
            if (!TextUtils.isEmpty(this.a.o)) {
                this.a.d.removeCallbacks((Runnable) this.a.s.remove(this.a.o));
            }
            String c = a.c(this.a, str2);
            new d(this.a, this.a.o);
            Runnable runnable2 = runnable;
            Object put = this.a.s.put(str2, runnable2);
            boolean postDelayed = this.a.d.postDelayed(runnable2, 120000);
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            StringBuilder sb;
            UiError uiError;
            Runnable runnable;
            UiError uiError2;
            int i2 = i;
            String str3 = str;
            String str4 = str2;
            super.onReceivedError(webView, i2, str3, str4);
            new StringBuilder();
            f.c("openSDK_LOG.AuthDialog", sb.append("-->onReceivedError, errorCode: ").append(i2).append(" | description: ").append(str3).toString());
            if (!k.b(this.a.k)) {
                new UiError(9001, "当前网络不可用，请稍后重试！", str4);
                this.a.b.onError(uiError2);
                this.a.dismiss();
            } else if (!this.a.o.startsWith("http://qzs.qq.com/open/mobile/login/qzsjump.html?")) {
                long elapsedRealtime = SystemClock.elapsedRealtime() - this.a.f28q;
                if (this.a.n >= 1 || elapsedRealtime >= this.a.r) {
                    this.a.j.loadUrl(this.a.a());
                    return;
                }
                int m = a.m(this.a);
                new Runnable(this) {
                    final /* synthetic */ C0006a a;

                    {
                        this.a = r5;
                    }

                    public void run() {
                        this.a.a.j.loadUrl(this.a.a.o);
                    }
                };
                boolean postDelayed = this.a.d.postDelayed(runnable, 500);
            } else {
                new UiError(i2, str3, str4);
                this.a.b.onError(uiError);
                this.a.dismiss();
            }
        }

        @TargetApi(8)
        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            StringBuilder sb;
            AlertDialog.Builder builder;
            DialogInterface.OnClickListener onClickListener;
            DialogInterface.OnClickListener onClickListener2;
            WebView webView2 = webView;
            SslErrorHandler sslErrorHandler2 = sslErrorHandler;
            new StringBuilder();
            f.e("openSDK_LOG.AuthDialog", sb.append("-->onReceivedSslError ").append(sslError.getPrimaryError()).append("请求不合法，请检查手机安全设置，如系统时间、代理等").toString());
            CharSequence charSequence = "The SSL certificate is invalid,do you countinue?";
            CharSequence charSequence2 = "yes";
            CharSequence charSequence3 = "no";
            if (Locale.getDefault().getLanguage().equals("zh")) {
                charSequence = "ssl证书无效，是否继续访问？";
                charSequence2 = "是";
                charSequence3 = "否";
            }
            new AlertDialog.Builder(this.a.k);
            AlertDialog.Builder builder2 = builder;
            AlertDialog.Builder message = builder2.setMessage(charSequence);
            final SslErrorHandler sslErrorHandler3 = sslErrorHandler2;
            new DialogInterface.OnClickListener(this) {
                final /* synthetic */ C0006a b;

                {
                    this.b = r6;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    DialogInterface dialogInterface2 = dialogInterface;
                    int i2 = i;
                    sslErrorHandler3.proceed();
                }
            };
            AlertDialog.Builder positiveButton = builder2.setPositiveButton(charSequence2, onClickListener);
            final SslErrorHandler sslErrorHandler4 = sslErrorHandler2;
            new DialogInterface.OnClickListener(this) {
                final /* synthetic */ C0006a b;

                {
                    this.b = r6;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    DialogInterface dialogInterface2 = dialogInterface;
                    int i2 = i;
                    sslErrorHandler4.cancel();
                    this.b.a.dismiss();
                }
            };
            AlertDialog.Builder negativeButton = builder2.setNegativeButton(charSequence3, onClickListener2);
            builder2.create().show();
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            StringBuilder sb;
            Intent intent;
            WebView webView2 = webView;
            String str2 = str;
            new StringBuilder();
            f.a("openSDK_LOG.AuthDialog", sb.append("-->Redirect URL: ").append(str2).toString());
            if (str2.startsWith("auth://browser")) {
                JSONObject c = k.c(str2);
                boolean a2 = a.a(this.a, this.a.e());
                if (!this.a.m) {
                    if (null != c.optString("fail_cb", (String) null)) {
                        this.a.a(c.optString("fail_cb"), "");
                    } else if (c.optInt("fall_to_wv") == 1) {
                        String a3 = a.a(this.a, (Object) this.a.a.indexOf("?") > -1 ? "&" : "?");
                        String a4 = a.a(this.a, (Object) "browser_error=1");
                        this.a.j.loadUrl(this.a.a);
                    } else {
                        String optString = c.optString("redir", (String) null);
                        if (null != optString) {
                            this.a.j.loadUrl(optString);
                        }
                    }
                }
                return true;
            } else if (str2.startsWith("auth://tauth.qq.com/")) {
                this.a.b.onComplete(k.c(str2));
                this.a.dismiss();
                return true;
            } else if (str2.startsWith(Constants.CANCEL_URI)) {
                this.a.b.onCancel();
                this.a.dismiss();
                return true;
            } else if (str2.startsWith(Constants.CLOSE_URI)) {
                this.a.dismiss();
                return true;
            } else if (str2.startsWith(Constants.DOWNLOAD_URI) || str2.endsWith(".apk")) {
                try {
                    new Intent("android.intent.action.VIEW", str2.startsWith(Constants.DOWNLOAD_URI) ? Uri.parse(Uri.decode(str2.substring(Constants.DOWNLOAD_URI.length()))) : Uri.parse(Uri.decode(str2)));
                    Intent intent2 = intent;
                    Intent addFlags = intent2.addFlags(268435456);
                    this.a.k.startActivity(intent2);
                } catch (Exception e) {
                    f.b("openSDK_LOG.AuthDialog", "-->start download activity exception, e: ", e);
                }
                return true;
            } else if (str2.startsWith("auth://progress")) {
                try {
                    List<String> pathSegments = Uri.parse(str2).getPathSegments();
                    if (pathSegments.isEmpty()) {
                        return true;
                    }
                    int intValue = Integer.valueOf(pathSegments.get(0)).intValue();
                    if (intValue == 0) {
                        this.a.g.setVisibility(8);
                        this.a.j.setVisibility(0);
                    } else if (intValue == 1) {
                        this.a.g.setVisibility(0);
                    }
                    return true;
                } catch (Exception e2) {
                    Exception exc = e2;
                    return true;
                }
            } else if (str2.startsWith("auth://onLoginSubmit")) {
                try {
                    List<String> pathSegments2 = Uri.parse(str2).getPathSegments();
                    if (!pathSegments2.isEmpty()) {
                        String b = a.b(this.a, pathSegments2.get(0));
                    }
                } catch (Exception e3) {
                    Exception exc2 = e3;
                }
                return true;
            } else if (this.a.l.a(this.a.j, str2)) {
                return true;
            } else {
                f.c("openSDK_LOG.AuthDialog", "-->Redirect URL: return false");
                return false;
            }
        }
    }

    /* compiled from: ProGuard */
    private class b implements IUiListener {
        String a;
        String b;
        final /* synthetic */ a c;
        private String d;
        private IUiListener e;

        public b(a aVar, String str, String str2, String str3, IUiListener iUiListener) {
            this.c = aVar;
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
            g a2 = g.a();
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
            g a2 = g.a();
            new StringBuilder();
            a2.a(sb.append(this.d).append("_H5").toString(), SystemClock.elapsedRealtime(), 0, 0, uiError2.errorCode, str2, false);
            String a3 = this.c.a(str2);
            if (this.e != null) {
                this.e.onError(uiError2);
                this.e = null;
            }
        }
    }

    /* compiled from: ProGuard */
    private class c extends Handler {
        final /* synthetic */ a a;
        private b b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public c(a aVar, b bVar, Looper looper) {
            super(looper);
            this.a = aVar;
            this.b = bVar;
        }

        public void handleMessage(Message message) {
            Message message2 = message;
            switch (message2.what) {
                case 1:
                    this.b.a((String) message2.obj);
                    return;
                case 2:
                    this.b.onCancel();
                    return;
                case 3:
                    a.b(this.a.k, (String) message2.obj);
                    return;
                default:
                    return;
            }
        }
    }

    /* compiled from: ProGuard */
    class d implements Runnable {
        String a = "";
        final /* synthetic */ a b;

        public d(a aVar, String str) {
            this.b = aVar;
            this.a = str;
        }

        public void run() {
            StringBuilder sb;
            UiError uiError;
            new StringBuilder();
            f.a("openSDK_LOG.AuthDialog", sb.append("-->timeoutUrl: ").append(this.a).append(" | mRetryUrl: ").append(this.b.o).toString());
            if (this.a.equals(this.b.o)) {
                new UiError(9002, "请求页面超时，请稍后重试！", this.b.o);
                this.b.b.onError(uiError);
                this.b.dismiss();
            }
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public a(android.content.Context r16, java.lang.String r17, java.lang.String r18, com.tencent.tauth.IUiListener r19, com.tencent.connect.auth.QQToken r20) {
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
            r6.f28q = r7
            r6 = r0
            r7 = 30000(0x7530, double:1.4822E-319)
            r6.r = r7
            r6 = r0
            r7 = r1
            r6.k = r7
            r6 = r0
            r7 = r3
            r6.a = r7
            r6 = r0
            com.tencent.connect.auth.a$b r7 = new com.tencent.connect.auth.a$b
            r14 = r7
            r7 = r14
            r8 = r14
            r9 = r0
            r10 = r2
            r11 = r3
            r12 = r5
            java.lang.String r12 = r12.getAppId()
            r13 = r4
            r8.<init>(r9, r10, r11, r12, r13)
            r6.b = r7
            r6 = r0
            com.tencent.connect.auth.a$c r7 = new com.tencent.connect.auth.a$c
            r14 = r7
            r7 = r14
            r8 = r14
            r9 = r0
            r10 = r0
            com.tencent.connect.auth.a$b r10 = r10.b
            r11 = r1
            android.os.Looper r11 = r11.getMainLooper()
            r8.<init>(r9, r10, r11)
            r6.d = r7
            r6 = r0
            r7 = r4
            r6.c = r7
            r6 = r0
            r7 = r2
            r6.i = r7
            r6 = r0
            com.tencent.open.web.security.b r7 = new com.tencent.open.web.security.b
            r14 = r7
            r7 = r14
            r8 = r14
            r8.<init>()
            r6.l = r7
            r6 = r0
            android.view.Window r6 = r6.getWindow()
            r7 = 32
            r6.setSoftInputMode(r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.connect.auth.a.<init>(android.content.Context, java.lang.String, java.lang.String, com.tencent.tauth.IUiListener, com.tencent.connect.auth.QQToken):void");
    }

    static /* synthetic */ long a(a aVar, long j2) {
        long j3 = j2;
        long j4 = j3;
        aVar.f28q = j4;
        return j3;
    }

    /* access modifiers changed from: private */
    public String a() {
        StringBuilder sb;
        String substring = this.a.substring(this.a.indexOf("?") + 1);
        new StringBuilder();
        f.c("openSDK_LOG.AuthDialog", "-->generateDownloadUrl, url: http://qzs.qq.com/open/mobile/login/qzsjump.html?");
        return sb.append("http://qzs.qq.com/open/mobile/login/qzsjump.html?").append(substring).toString();
    }

    static /* synthetic */ String a(a aVar, Object obj) {
        StringBuilder sb;
        new StringBuilder();
        StringBuilder sb2 = sb;
        a aVar2 = aVar;
        a aVar3 = aVar2;
        String sb3 = sb2.append(aVar2.a).append(obj).toString();
        aVar3.a = sb3;
        return sb3;
    }

    /* access modifiers changed from: private */
    public String a(String str) {
        StringBuilder sb;
        new StringBuilder(str);
        StringBuilder sb2 = sb;
        if (!TextUtils.isEmpty(this.p) && this.p.length() >= 4) {
            StringBuilder append = sb2.append("_u_").append(this.p.substring(this.p.length() - 4));
        }
        return sb2.toString();
    }

    static /* synthetic */ boolean a(a aVar, boolean z) {
        boolean z2 = z;
        boolean z3 = z2;
        aVar.m = z3;
        return z2;
    }

    static /* synthetic */ String b(a aVar, String str) {
        String str2 = str;
        String str3 = str2;
        aVar.p = str3;
        return str2;
    }

    private void b() {
        FrameLayout.LayoutParams layoutParams;
        com.tencent.open.c.c cVar;
        FrameLayout frameLayout;
        c();
        new FrameLayout.LayoutParams(-1, -1);
        FrameLayout.LayoutParams layoutParams2 = layoutParams;
        new com.tencent.open.c.c(this.k);
        this.j = cVar;
        if (Build.VERSION.SDK_INT >= 11) {
            this.j.setLayerType(1, (Paint) null);
        }
        this.j.setLayoutParams(layoutParams2);
        new FrameLayout(this.k);
        this.e = frameLayout;
        layoutParams2.gravity = 17;
        this.e.setLayoutParams(layoutParams2);
        this.e.addView(this.j);
        this.e.addView(this.g);
        setContentView(this.e);
    }

    /* access modifiers changed from: private */
    public static void b(Context context, String str) {
        Context context2 = context;
        try {
            JSONObject d2 = k.d(str);
            int i2 = d2.getInt(SocialConstants.PARAM_TYPE);
            Toast.makeText(context2.getApplicationContext(), d2.getString("msg"), i2).show();
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    static /* synthetic */ String c(a aVar, String str) {
        String str2 = str;
        String str3 = str2;
        aVar.o = str3;
        return str2;
    }

    private void c() {
        ProgressBar progressBar;
        ViewGroup.LayoutParams layoutParams;
        LinearLayout linearLayout;
        FrameLayout.LayoutParams layoutParams2;
        FrameLayout frameLayout;
        FrameLayout.LayoutParams layoutParams3;
        LinearLayout.LayoutParams layoutParams4;
        TextView textView;
        new ProgressBar(this.k);
        this.h = progressBar;
        new LinearLayout.LayoutParams(-2, -2);
        this.h.setLayoutParams(layoutParams);
        new LinearLayout(this.k);
        this.f = linearLayout;
        TextView textView2 = null;
        if (this.i.equals("action_login")) {
            new LinearLayout.LayoutParams(-2, -2);
            LinearLayout.LayoutParams layoutParams5 = layoutParams4;
            layoutParams5.gravity = 16;
            layoutParams5.leftMargin = 5;
            new TextView(this.k);
            textView2 = textView;
            if (Locale.getDefault().getLanguage().equals("zh")) {
                textView2.setText("登录中...");
            } else {
                textView2.setText("Logging in...");
            }
            textView2.setTextColor(Color.rgb(255, 255, 255));
            textView2.setTextSize(18.0f);
            textView2.setLayoutParams(layoutParams5);
        }
        new FrameLayout.LayoutParams(-2, -2);
        FrameLayout.LayoutParams layoutParams6 = layoutParams2;
        layoutParams6.gravity = 17;
        this.f.setLayoutParams(layoutParams6);
        this.f.addView(this.h);
        if (textView2 != null) {
            this.f.addView(textView2);
        }
        new FrameLayout(this.k);
        this.g = frameLayout;
        new FrameLayout.LayoutParams(-1, -2);
        FrameLayout.LayoutParams layoutParams7 = layoutParams3;
        layoutParams7.leftMargin = 80;
        layoutParams7.rightMargin = 80;
        layoutParams7.topMargin = 40;
        layoutParams7.bottomMargin = 40;
        layoutParams7.gravity = 17;
        this.g.setLayoutParams(layoutParams7);
        this.g.setBackgroundResource(17301504);
        this.g.addView(this.f);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private void d() {
        WebViewClient webViewClient;
        WebChromeClient webChromeClient;
        View.OnLongClickListener onLongClickListener;
        View.OnTouchListener onTouchListener;
        StringBuilder sb;
        a.b bVar;
        DialogInterface.OnDismissListener onDismissListener;
        this.j.setVerticalScrollBarEnabled(false);
        this.j.setHorizontalScrollBarEnabled(false);
        new C0006a(this, (AnonymousClass1) null);
        this.j.setWebViewClient(webViewClient);
        new WebChromeClient();
        this.j.setWebChromeClient(webChromeClient);
        this.j.clearFormData();
        this.j.clearSslPreferences();
        new View.OnLongClickListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r5;
            }

            public boolean onLongClick(View view) {
                View view2 = view;
                return true;
            }
        };
        this.j.setOnLongClickListener(onLongClickListener);
        new View.OnTouchListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r5;
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                View view2 = view;
                switch (motionEvent.getAction()) {
                    case 0:
                    case 1:
                        if (!view2.hasFocus()) {
                            boolean requestFocus = view2.requestFocus();
                            break;
                        }
                        break;
                }
                return false;
            }
        };
        this.j.setOnTouchListener(onTouchListener);
        WebSettings settings = this.j.getSettings();
        settings.setSavePassword(false);
        settings.setSaveFormData(false);
        settings.setCacheMode(-1);
        settings.setNeedInitialFocus(false);
        settings.setBuiltInZoomControls(true);
        settings.setSupportZoom(true);
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        settings.setJavaScriptEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setDatabasePath(this.k.getDir("databases", 0).getPath());
        settings.setDomStorageEnabled(true);
        new StringBuilder();
        f.a("openSDK_LOG.AuthDialog", sb.append("-->mUrl : ").append(this.a).toString());
        this.o = this.a;
        this.j.loadUrl(this.a);
        this.j.setVisibility(4);
        this.j.getSettings().setSavePassword(false);
        new SecureJsInterface();
        this.l.a(bVar, "SecureJsInterface");
        SecureJsInterface.isPWDEdit = false;
        new DialogInterface.OnDismissListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r5;
            }

            public void onDismiss(DialogInterface dialogInterface) {
                DialogInterface dialogInterface2 = dialogInterface;
                try {
                    if (JniInterface.isJniOk) {
                        boolean clearAllPWD = JniInterface.clearAllPWD();
                    }
                } catch (Exception e) {
                    Exception exc = e;
                }
            }
        };
        super.setOnDismissListener(onDismissListener);
    }

    /* access modifiers changed from: private */
    public boolean e() {
        b.a aVar;
        StringBuilder sb;
        b a2 = b.a();
        String c2 = a2.c();
        new b.a();
        b.a aVar2 = aVar;
        aVar2.a = this.c;
        aVar2.b = this;
        aVar2.c = c2;
        String a3 = a2.a(aVar2);
        String substring = this.a.substring(0, this.a.indexOf("?"));
        Bundle b2 = k.b(this.a);
        b2.putString("token_key", c2);
        b2.putString("serial", a3);
        b2.putString("browser", "1");
        new StringBuilder();
        this.a = sb.append(substring).append("?").append(HttpUtils.encodeUrl(b2)).toString();
        return k.a(this.k, this.a);
    }

    static /* synthetic */ int m(a aVar) {
        a aVar2 = aVar;
        int i2 = aVar2.n;
        int i3 = i2 + 1;
        aVar2.n = i3;
        return i2;
    }

    public void a(String str, String str2) {
        StringBuilder sb;
        new StringBuilder();
        String sb2 = sb.append("javascript:").append(str).append("(").append(str2).append(");void(").append(System.currentTimeMillis()).append(");").toString();
        this.j.loadUrl(sb2);
    }

    public void dismiss() {
        this.s.clear();
        this.d.removeCallbacksAndMessages((Object) null);
        if (isShowing()) {
            super.dismiss();
        }
        if (this.j != null) {
            this.j.destroy();
            this.j = null;
        }
    }

    public void onBackPressed() {
        if (!this.m) {
            this.b.onCancel();
        }
        super.onBackPressed();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        HashMap<String, Runnable> hashMap;
        boolean requestWindowFeature = requestWindowFeature(1);
        super.onCreate(bundle);
        b();
        d();
        new HashMap<>();
        this.s = hashMap;
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
    }
}
