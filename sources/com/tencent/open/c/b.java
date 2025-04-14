package com.tencent.open.c;

import android.content.Context;
import android.webkit.WebSettings;
import android.webkit.WebView;
import java.lang.reflect.Method;

/* compiled from: ProGuard */
public class b extends WebView {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public b(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        WebSettings settings = getSettings();
        if (null != settings) {
            try {
                Method method = settings.getClass().getMethod("removeJavascriptInterface", new Class[]{String.class});
                if (method != null) {
                    Object invoke = method.invoke(this, new Object[]{"searchBoxJavaBridge_"});
                }
            } catch (Exception e) {
                Exception exc = e;
            }
        }
    }
}
