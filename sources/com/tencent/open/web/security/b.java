package com.tencent.open.web.security;

import android.net.Uri;
import android.text.TextUtils;
import android.webkit.WebView;
import com.tencent.open.a;
import com.tencent.open.a.f;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.lingala.zip4j.util.InternalZipConstants;

/* compiled from: ProGuard */
public class b extends a {
    public b() {
    }

    public void a(String str, String str2, List<String> list, a.C0007a aVar) {
        StringBuilder sb;
        String str3 = str;
        String str4 = str2;
        List<String> list2 = list;
        a.C0007a aVar2 = aVar;
        new StringBuilder();
        f.a("openSDK_LOG.SecureJsBridge", sb.append("-->getResult, objectName: ").append(str3).append(" | methodName: ").append(str4).toString());
        int size = list2.size();
        for (int i = 0; i < size; i++) {
            try {
                String str5 = list2.set(i, URLDecoder.decode(list2.get(i), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        a.b bVar = (a.b) this.a.get(str3);
        if (bVar != null) {
            f.b("openSDK_LOG.SecureJsBridge", "-->handler != null");
            bVar.call(str4, list2, aVar2);
            return;
        }
        f.b("openSDK_LOG.SecureJsBridge", "-->handler == null");
        if (aVar2 != null) {
            aVar2.a();
        }
    }

    public boolean a(WebView webView, String str) {
        StringBuilder sb;
        StringBuilder sb2;
        ArrayList arrayList;
        StringBuilder sb3;
        a.C0007a aVar;
        WebView webView2 = webView;
        String str2 = str;
        new StringBuilder();
        f.a("openSDK_LOG.SecureJsBridge", sb.append("-->canHandleUrl---url = ").append(str2).toString());
        if (str2 == null) {
            return false;
        }
        if (!Uri.parse(str2).getScheme().equals("jsbridge")) {
            return false;
        }
        new StringBuilder();
        new ArrayList(Arrays.asList(sb2.append(str2).append("/#").toString().split(InternalZipConstants.ZIP_FILE_SEPARATOR)));
        ArrayList arrayList2 = arrayList;
        if (arrayList2.size() < 7) {
            return false;
        }
        String str3 = (String) arrayList2.get(2);
        String str4 = (String) arrayList2.get(3);
        String str5 = (String) arrayList2.get(4);
        String str6 = (String) arrayList2.get(5);
        new StringBuilder();
        f.a("openSDK_LOG.SecureJsBridge", sb3.append("-->canHandleUrl, objectName: ").append(str3).append(" | methodName: ").append(str4).append(" | snStr: ").append(str5).toString());
        if (TextUtils.isEmpty(str3) || TextUtils.isEmpty(str4) || TextUtils.isEmpty(str5)) {
            return false;
        }
        try {
            new c(webView2, Long.parseLong(str5), str2, str6);
            List subList = arrayList2.subList(6, arrayList2.size() - 1);
            a(str3, str4, subList, aVar);
            return true;
        } catch (Exception e) {
            Exception exc = e;
            return false;
        }
    }
}
