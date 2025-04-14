package com.tencent.open;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.tencent.connect.common.Constants;
import com.tencent.open.a;
import com.tencent.open.a.f;
import com.tencent.open.c.a;
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
public class c extends b implements a.C0009a {
    static Toast c = null;
    private String d;
    private IUiListener e;
    /* access modifiers changed from: private */
    public C0010c f;
    private Handler g;
    private a h;
    /* access modifiers changed from: private */
    public b i;
    /* access modifiers changed from: private */
    public WeakReference<Context> j;
    private int k;

    /* compiled from: ProGuard */
    private class a extends WebViewClient {
        final /* synthetic */ c a;

        private a(c cVar) {
            this.a = cVar;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ a(c cVar, AnonymousClass1 r7) {
            this(cVar);
            AnonymousClass1 r2 = r7;
        }

        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            this.a.i.setVisibility(0);
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            StringBuilder sb;
            String str2 = str;
            new StringBuilder();
            f.a("openSDK_LOG.PKDialog", sb.append("Webview loading URL: ").append(str2).toString());
            super.onPageStarted(webView, str2, bitmap);
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            UiError uiError;
            int i2 = i;
            String str3 = str;
            String str4 = str2;
            super.onReceivedError(webView, i2, str3, str4);
            new UiError(i2, str3, str4);
            this.a.f.onError(uiError);
            if (!(this.a.j == null || this.a.j.get() == null)) {
                Toast.makeText((Context) this.a.j.get(), "网络连接异常或系统错误", 0).show();
            }
            this.a.dismiss();
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            StringBuilder sb;
            WebView webView2 = webView;
            String str2 = str;
            new StringBuilder();
            f.a("openSDK_LOG.PKDialog", sb.append("Redirect URL: ").append(str2).toString());
            if (str2.startsWith(g.a().a((Context) this.a.j.get(), "auth://tauth.qq.com/"))) {
                this.a.f.onComplete(k.c(str2));
                this.a.dismiss();
                return true;
            } else if (str2.startsWith(Constants.CANCEL_URI)) {
                this.a.f.onCancel();
                this.a.dismiss();
                return true;
            } else if (!str2.startsWith(Constants.CLOSE_URI)) {
                return false;
            } else {
                this.a.dismiss();
                return true;
            }
        }
    }

    /* compiled from: ProGuard */
    private class b extends a.b {
        final /* synthetic */ c a;

        private b(c cVar) {
            this.a = cVar;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ b(c cVar, AnonymousClass1 r7) {
            this(cVar);
            AnonymousClass1 r2 = r7;
        }
    }

    /* renamed from: com.tencent.open.c$c  reason: collision with other inner class name */
    /* compiled from: ProGuard */
    private static class C0010c implements IUiListener {
        String a;
        String b;
        private WeakReference<Context> c;
        private String d;
        private IUiListener e;

        public C0010c(Context context, String str, String str2, String str3, IUiListener iUiListener) {
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
    private class d extends Handler {
        final /* synthetic */ c a;
        private C0010c b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public d(c cVar, C0010c cVar2, Looper looper) {
            super(looper);
            this.a = cVar;
            this.b = cVar2;
        }

        public void handleMessage(Message message) {
            StringBuilder sb;
            Message message2 = message;
            new StringBuilder();
            f.b("openSDK_LOG.PKDialog", sb.append("msg = ").append(message2.what).toString());
            switch (message2.what) {
                case 1:
                    this.b.a((String) message2.obj);
                    return;
                case 2:
                    this.b.onCancel();
                    return;
                case 3:
                    if (this.a.j != null && this.a.j.get() != null) {
                        c.c((Context) this.a.j.get(), (String) message2.obj);
                        return;
                    }
                    return;
                case 5:
                    if (this.a.j != null && this.a.j.get() != null) {
                        c.d((Context) this.a.j.get(), (String) message2.obj);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public c(android.content.Context r16, java.lang.String r17, java.lang.String r18, com.tencent.tauth.IUiListener r19, com.tencent.connect.auth.QQToken r20) {
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
            java.lang.ref.WeakReference r7 = new java.lang.ref.WeakReference
            r14 = r7
            r7 = r14
            r8 = r14
            r9 = r1
            r8.<init>(r9)
            r6.j = r7
            r6 = r0
            r7 = r3
            r6.d = r7
            r6 = r0
            com.tencent.open.c$c r7 = new com.tencent.open.c$c
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
            r6.f = r7
            r6 = r0
            com.tencent.open.c$d r7 = new com.tencent.open.c$d
            r14 = r7
            r7 = r14
            r8 = r14
            r9 = r0
            r10 = r0
            com.tencent.open.c$c r10 = r10.f
            r11 = r1
            android.os.Looper r11 = r11.getMainLooper()
            r8.<init>(r9, r10, r11)
            r6.g = r7
            r6 = r0
            r7 = r4
            r6.e = r7
            r6 = r0
            r7 = 1127809024(0x43390000, float:185.0)
            r8 = r1
            android.content.res.Resources r8 = r8.getResources()
            android.util.DisplayMetrics r8 = r8.getDisplayMetrics()
            float r8 = r8.density
            float r7 = r7 * r8
            int r7 = java.lang.Math.round(r7)
            r6.k = r7
            java.lang.String r6 = "openSDK_LOG.PKDialog"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r14 = r7
            r7 = r14
            r8 = r14
            r8.<init>()
            java.lang.String r8 = "density="
            java.lang.StringBuilder r7 = r7.append(r8)
            r8 = r1
            android.content.res.Resources r8 = r8.getResources()
            android.util.DisplayMetrics r8 = r8.getDisplayMetrics()
            float r8 = r8.density
            java.lang.StringBuilder r7 = r7.append(r8)
            java.lang.String r8 = "; webviewHeight="
            java.lang.StringBuilder r7 = r7.append(r8)
            r8 = r0
            int r8 = r8.k
            java.lang.StringBuilder r7 = r7.append(r8)
            java.lang.String r7 = r7.toString()
            com.tencent.open.a.f.e(r6, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.open.c.<init>(android.content.Context, java.lang.String, java.lang.String, com.tencent.tauth.IUiListener, com.tencent.connect.auth.QQToken):void");
    }

    private void b() {
        com.tencent.open.c.a aVar;
        ViewGroup.LayoutParams layoutParams;
        b bVar;
        RelativeLayout.LayoutParams layoutParams2;
        Object obj;
        new com.tencent.open.c.a((Context) this.j.get());
        this.h = aVar;
        this.h.setBackgroundColor(1711276032);
        new RelativeLayout.LayoutParams(-1, -1);
        this.h.setLayoutParams(layoutParams);
        new b((Context) this.j.get());
        this.i = bVar;
        this.i.setBackgroundColor(0);
        this.i.setBackgroundDrawable((Drawable) null);
        if (Build.VERSION.SDK_INT >= 11) {
            Class<View> cls = View.class;
            try {
                Class[] clsArr = new Class[2];
                clsArr[0] = Integer.TYPE;
                Class[] clsArr2 = clsArr;
                clsArr2[1] = Paint.class;
                b bVar2 = this.i;
                Object[] objArr = new Object[2];
                objArr[0] = 1;
                Object[] objArr2 = objArr;
                new Paint();
                objArr2[1] = obj;
                Object invoke = cls.getMethod("setLayerType", clsArr2).invoke(bVar2, objArr2);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        new RelativeLayout.LayoutParams(-1, this.k);
        RelativeLayout.LayoutParams layoutParams3 = layoutParams2;
        layoutParams3.addRule(13, -1);
        this.i.setLayoutParams(layoutParams3);
        this.h.addView(this.i);
        this.h.a(this);
        setContentView(this.h);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private void c() {
        WebViewClient webViewClient;
        a.b bVar;
        this.i.setVerticalScrollBarEnabled(false);
        this.i.setHorizontalScrollBarEnabled(false);
        new a(this, (AnonymousClass1) null);
        this.i.setWebViewClient(webViewClient);
        this.i.setWebChromeClient(this.b);
        this.i.clearFormData();
        WebSettings settings = this.i.getSettings();
        if (settings != null) {
            settings.setSavePassword(false);
            settings.setSaveFormData(false);
            settings.setCacheMode(-1);
            settings.setNeedInitialFocus(false);
            settings.setBuiltInZoomControls(true);
            settings.setSupportZoom(true);
            settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
            settings.setJavaScriptEnabled(true);
            if (!(this.j == null || this.j.get() == null)) {
                settings.setDatabaseEnabled(true);
                settings.setDatabasePath(((Context) this.j.get()).getApplicationContext().getDir("databases", 0).getPath());
            }
            settings.setDomStorageEnabled(true);
            new b(this, (AnonymousClass1) null);
            this.a.a(bVar, "sdk_js_if");
            this.i.clearView();
            this.i.loadUrl(this.d);
            this.i.getSettings().setSavePassword(false);
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
                if (c == null) {
                    c = Toast.makeText(context2, string, 0);
                } else {
                    c.setView(c.getView());
                    c.setText(string);
                    c.setDuration(0);
                }
                c.show();
            } else if (i2 == 1) {
                if (c == null) {
                    c = Toast.makeText(context2, string, 1);
                } else {
                    c.setView(c.getView());
                    c.setText(string);
                    c.setDuration(1);
                }
                c.show();
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public static void d(Context context, String str) {
        String str2 = str;
        if (context != null && str2 != null) {
            try {
                JSONObject d2 = k.d(str2);
                int i2 = d2.getInt(AuthActivity.ACTION_KEY);
                String string = d2.getString("msg");
                if (i2 == 1) {
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
    }

    public void a() {
        this.i.getLayoutParams().height = this.k;
        f.e("openSDK_LOG.PKDialog", "onKeyboardHidden keyboard hide");
    }

    public void a(int i2) {
        int i3 = i2;
        if (!(this.j == null || this.j.get() == null)) {
            if (i3 >= this.k || 2 != ((Context) this.j.get()).getResources().getConfiguration().orientation) {
                this.i.getLayoutParams().height = this.k;
            } else {
                this.i.getLayoutParams().height = i3;
            }
        }
        f.e("openSDK_LOG.PKDialog", "onKeyboardShown keyboard show");
    }

    /* access modifiers changed from: protected */
    public void a(String str) {
        String str2 = str;
        f.b("openSDK_LOG.PKDialog", "--onConsoleMessage--");
        try {
            boolean a2 = this.a.a((WebView) this.i, str2);
        } catch (Exception e2) {
            Exception exc = e2;
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        boolean requestWindowFeature = requestWindowFeature(1);
        super.onCreate(bundle);
        getWindow().setSoftInputMode(16);
        getWindow().setSoftInputMode(1);
        b();
        c();
    }
}
