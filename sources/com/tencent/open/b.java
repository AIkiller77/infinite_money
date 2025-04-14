package com.tencent.open;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import com.tencent.open.a.f;

/* compiled from: ProGuard */
public abstract class b extends Dialog {
    protected a a;
    @SuppressLint({"NewApi"})
    protected final WebChromeClient b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public b(Context context, int i) {
        super(context, i);
        WebChromeClient webChromeClient;
        new WebChromeClient(this) {
            final /* synthetic */ b a;

            {
                this.a = r5;
            }

            public void onConsoleMessage(String str, int i, String str2) {
                StringBuilder sb;
                String str3 = str;
                new StringBuilder();
                f.c("openSDK_LOG.JsDialog", sb.append("WebChromeClient onConsoleMessage").append(str3).append(" -- From 222 line ").append(i).append(" of ").append(str2).toString());
                if (Build.VERSION.SDK_INT == 7) {
                    this.a.a(str3);
                }
            }

            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                StringBuilder sb;
                ConsoleMessage consoleMessage2 = consoleMessage;
                if (consoleMessage2 == null) {
                    return false;
                }
                new StringBuilder();
                f.c("openSDK_LOG.JsDialog", sb.append("WebChromeClient onConsoleMessage").append(consoleMessage2.message()).append(" -- From  111 line ").append(consoleMessage2.lineNumber()).append(" of ").append(consoleMessage2.sourceId()).toString());
                if (Build.VERSION.SDK_INT > 7) {
                    this.a.a(consoleMessage2 == null ? "" : consoleMessage2.message());
                }
                return true;
            }
        };
        this.b = webChromeClient;
    }

    /* access modifiers changed from: protected */
    public abstract void a(String str);

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        a aVar;
        super.onCreate(bundle);
        new a();
        this.a = aVar;
    }
}
