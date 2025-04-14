package com.tencent.open.web.security;

import android.webkit.WebView;
import com.tencent.open.a;
import com.tencent.open.a.f;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class c extends a.C0007a {
    private String d;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public c(WebView webView, long j, String str, String str2) {
        super(webView, j, str);
        this.d = str2;
    }

    private void b(String str) {
        StringBuffer stringBuffer;
        StringBuilder sb;
        String str2 = str;
        WebView webView = (WebView) this.a.get();
        if (webView != null) {
            new StringBuffer("javascript:");
            StringBuffer stringBuffer2 = stringBuffer;
            StringBuffer append = stringBuffer2.append("if(!!").append(this.d).append("){");
            StringBuffer append2 = stringBuffer2.append(this.d);
            StringBuffer append3 = stringBuffer2.append("(");
            StringBuffer append4 = stringBuffer2.append(str2);
            StringBuffer append5 = stringBuffer2.append(")}");
            String stringBuffer3 = stringBuffer2.toString();
            new StringBuilder();
            f.a("openSDK_LOG.SecureJsListener", sb.append("-->callback, callback: ").append(stringBuffer3).toString());
            webView.loadUrl(stringBuffer3);
        }
    }

    public void a() {
        f.b("openSDK_LOG.SecureJsListener", "-->onNoMatchMethod...");
    }

    public void a(Object obj) {
        StringBuilder sb;
        new StringBuilder();
        f.a("openSDK_LOG.SecureJsListener", sb.append("-->onComplete, result: ").append(obj).toString());
    }

    public void a(String str) {
        StringBuilder sb;
        JSONObject jSONObject;
        String str2 = str;
        new StringBuilder();
        f.a("openSDK_LOG.SecureJsListener", sb.append("-->onCustomCallback, js: ").append(str2).toString());
        new JSONObject();
        JSONObject jSONObject2 = jSONObject;
        int i = 0;
        if (!com.tencent.open.c.c.a) {
            i = -4;
        }
        try {
            JSONObject put = jSONObject2.put("result", i);
            JSONObject put2 = jSONObject2.put("sn", this.b);
            JSONObject put3 = jSONObject2.put("data", str2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        b(jSONObject2.toString());
    }
}
