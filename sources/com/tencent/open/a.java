package com.tencent.open;

import android.net.Uri;
import android.webkit.WebView;
import com.tencent.open.a.f;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import net.lingala.zip4j.util.InternalZipConstants;

/* compiled from: ProGuard */
public class a {
    protected HashMap<String, b> a;

    /* renamed from: com.tencent.open.a$a  reason: collision with other inner class name */
    /* compiled from: ProGuard */
    public static class C0007a {
        protected WeakReference<WebView> a;
        protected long b;
        protected String c;

        public C0007a(WebView webView, long j, String str) {
            WeakReference<WebView> weakReference;
            new WeakReference<>(webView);
            this.a = weakReference;
            this.b = j;
            this.c = str;
        }

        public void a() {
            StringBuilder sb;
            WebView webView = (WebView) this.a.get();
            if (webView != null) {
                new StringBuilder();
                webView.loadUrl(sb.append("javascript:window.JsBridge&&JsBridge.callback(").append(this.b).append(",{'r':1,'result':'no such method'})").toString());
            }
        }

        public void a(Object obj) {
            StringBuilder sb;
            StringBuilder sb2;
            Object obj2 = obj;
            WebView webView = (WebView) this.a.get();
            if (webView != null) {
                String str = "'undefined'";
                if (obj2 instanceof String) {
                    String replace = ((String) obj2).replace("\\", "\\\\").replace("'", "\\'");
                    new StringBuilder();
                    str = sb2.append("'").append(replace).append("'").toString();
                } else if ((obj2 instanceof Number) || (obj2 instanceof Long) || (obj2 instanceof Integer) || (obj2 instanceof Double) || (obj2 instanceof Float)) {
                    str = obj2.toString();
                } else if (obj2 instanceof Boolean) {
                    str = obj2.toString();
                }
                new StringBuilder();
                webView.loadUrl(sb.append("javascript:window.JsBridge&&JsBridge.callback(").append(this.b).append(",{'r':0,'result':").append(str).append("});").toString());
            }
        }

        public void a(String str) {
            StringBuilder sb;
            String str2 = str;
            WebView webView = (WebView) this.a.get();
            if (webView != null) {
                new StringBuilder();
                webView.loadUrl(sb.append("javascript:").append(str2).toString());
            }
        }
    }

    /* compiled from: ProGuard */
    public static class b {
        public b() {
        }

        public void call(String str, List<String> list, C0007a aVar) {
            StringBuilder sb;
            Object invoke;
            StringBuilder sb2;
            String str2 = str;
            List<String> list2 = list;
            C0007a aVar2 = aVar;
            Method method = null;
            Method[] declaredMethods = getClass().getDeclaredMethods();
            int length = declaredMethods.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                Method method2 = declaredMethods[i];
                if (method2.getName().equals(str2) && method2.getParameterTypes().length == list2.size()) {
                    method = method2;
                    break;
                }
                i++;
            }
            if (method != null) {
                try {
                    switch (list2.size()) {
                        case 0:
                            invoke = method.invoke(this, new Object[0]);
                            break;
                        case 1:
                            invoke = method.invoke(this, new Object[]{list2.get(0)});
                            break;
                        case 2:
                            Object[] objArr = new Object[2];
                            objArr[0] = list2.get(0);
                            Object[] objArr2 = objArr;
                            objArr2[1] = list2.get(1);
                            invoke = method.invoke(this, objArr2);
                            break;
                        case 3:
                            Object[] objArr3 = new Object[3];
                            objArr3[0] = list2.get(0);
                            Object[] objArr4 = objArr3;
                            objArr4[1] = list2.get(1);
                            Object[] objArr5 = objArr4;
                            objArr5[2] = list2.get(2);
                            invoke = method.invoke(this, objArr5);
                            break;
                        case 4:
                            Object[] objArr6 = new Object[4];
                            objArr6[0] = list2.get(0);
                            Object[] objArr7 = objArr6;
                            objArr7[1] = list2.get(1);
                            Object[] objArr8 = objArr7;
                            objArr8[2] = list2.get(2);
                            Object[] objArr9 = objArr8;
                            objArr9[3] = list2.get(3);
                            invoke = method.invoke(this, objArr9);
                            break;
                        case 5:
                            Object[] objArr10 = new Object[5];
                            objArr10[0] = list2.get(0);
                            Object[] objArr11 = objArr10;
                            objArr11[1] = list2.get(1);
                            Object[] objArr12 = objArr11;
                            objArr12[2] = list2.get(2);
                            Object[] objArr13 = objArr12;
                            objArr13[3] = list2.get(3);
                            Object[] objArr14 = objArr13;
                            objArr14[4] = list2.get(4);
                            invoke = method.invoke(this, objArr14);
                            break;
                        default:
                            Object[] objArr15 = new Object[6];
                            objArr15[0] = list2.get(0);
                            Object[] objArr16 = objArr15;
                            objArr16[1] = list2.get(1);
                            Object[] objArr17 = objArr16;
                            objArr17[2] = list2.get(2);
                            Object[] objArr18 = objArr17;
                            objArr18[3] = list2.get(3);
                            Object[] objArr19 = objArr18;
                            objArr19[4] = list2.get(4);
                            Object[] objArr20 = objArr19;
                            objArr20[5] = list2.get(5);
                            invoke = method.invoke(this, objArr20);
                            break;
                    }
                    Class<?> returnType = method.getReturnType();
                    new StringBuilder();
                    f.b("openSDK_LOG.JsBridge", sb2.append("-->call, result: ").append(invoke).append(" | ReturnType: ").append(returnType.getName()).toString());
                    if ("void".equals(returnType.getName()) || returnType == Void.class) {
                        if (aVar2 != null) {
                            aVar2.a((Object) null);
                        }
                    } else if (aVar2 != null && customCallback()) {
                        aVar2.a(invoke != null ? invoke.toString() : null);
                    }
                } catch (Exception e) {
                    new StringBuilder();
                    f.b("openSDK_LOG.JsBridge", sb.append("-->handler call mehtod ex. targetMethod: ").append(method).toString(), e);
                    if (aVar2 != null) {
                        aVar2.a();
                    }
                }
            } else if (aVar2 != null) {
                aVar2.a();
            }
        }

        public boolean customCallback() {
            return false;
        }
    }

    public a() {
        HashMap<String, b> hashMap;
        new HashMap<>();
        this.a = hashMap;
    }

    public void a(b bVar, String str) {
        b put = this.a.put(str, bVar);
    }

    public void a(String str, String str2, List<String> list, C0007a aVar) {
        StringBuilder sb;
        String str3 = str;
        String str4 = str2;
        List<String> list2 = list;
        C0007a aVar2 = aVar;
        new StringBuilder();
        f.a("openSDK_LOG.JsBridge", sb.append("getResult---objName = ").append(str3).append(" methodName = ").append(str4).toString());
        int size = list2.size();
        for (int i = 0; i < size; i++) {
            try {
                String str5 = list2.set(i, URLDecoder.decode(list2.get(i), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        b bVar = this.a.get(str3);
        if (bVar != null) {
            f.b("openSDK_LOG.JsBridge", "call----");
            bVar.call(str4, list2, aVar2);
            return;
        }
        f.b("openSDK_LOG.JsBridge", "not call----objName NOT FIND");
        if (aVar2 != null) {
            aVar2.a();
        }
    }

    public boolean a(WebView webView, String str) {
        StringBuilder sb;
        StringBuilder sb2;
        ArrayList arrayList;
        C0007a aVar;
        WebView webView2 = webView;
        String str2 = str;
        new StringBuilder();
        f.a("openSDK_LOG.JsBridge", sb.append("-->canHandleUrl---url = ").append(str2).toString());
        if (str2 == null) {
            return false;
        }
        if (!Uri.parse(str2).getScheme().equals("jsbridge")) {
            return false;
        }
        new StringBuilder();
        new ArrayList(Arrays.asList(sb2.append(str2).append("/#").toString().split(InternalZipConstants.ZIP_FILE_SEPARATOR)));
        ArrayList arrayList2 = arrayList;
        if (arrayList2.size() < 6) {
            return false;
        }
        String str3 = (String) arrayList2.get(2);
        String str4 = (String) arrayList2.get(3);
        List subList = arrayList2.subList(4, arrayList2.size() - 1);
        new C0007a(webView2, 4, str2);
        String url = webView2.getUrl();
        a(str3, str4, subList, aVar);
        return true;
    }
}
