package com.tencent.connect.a;

import android.content.Context;
import android.text.TextUtils;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.Constants;
import com.tencent.open.b.d;
import com.tencent.open.utils.f;
import java.lang.reflect.Method;

/* compiled from: ProGuard */
public class a {
    private static Class<?> a = null;
    private static Class<?> b = null;
    private static Method c = null;
    private static Method d = null;
    private static Method e = null;
    private static Method f = null;
    private static boolean g = false;

    public static void a(Context context, QQToken qQToken, String str, String... strArr) {
        Context context2 = context;
        QQToken qQToken2 = qQToken;
        String str2 = str;
        String[] strArr2 = strArr;
        if (g) {
            b(context2, qQToken2);
            try {
                Method method = d;
                Class<?> cls = b;
                Object[] objArr = new Object[3];
                objArr[0] = context2;
                Object[] objArr2 = objArr;
                objArr2[1] = str2;
                Object[] objArr3 = objArr2;
                objArr3[2] = strArr2;
                Object invoke = method.invoke(cls, objArr3);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static boolean a(Context context, QQToken qQToken) {
        return f.a(context, qQToken.getAppId()).b("Common_ta_enable");
    }

    public static void b(Context context, QQToken qQToken) {
        try {
            if (a(context, qQToken)) {
                Object invoke = f.invoke(a, new Object[]{true});
                return;
            }
            Object invoke2 = f.invoke(a, new Object[]{false});
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void c(Context context, QQToken qQToken) {
        StringBuilder sb;
        Context context2 = context;
        QQToken qQToken2 = qQToken;
        String appId = qQToken2.getAppId();
        new StringBuilder();
        String sb2 = sb.append("Aqc").append(appId).toString();
        try {
            a = Class.forName("com.tencent.stat.StatConfig");
            b = Class.forName("com.tencent.stat.StatService");
            Class<?> cls = b;
            Class[] clsArr = new Class[2];
            clsArr[0] = Context.class;
            Class[] clsArr2 = clsArr;
            clsArr2[1] = String.class;
            c = cls.getMethod("reportQQ", clsArr2);
            Class<?> cls2 = b;
            Class[] clsArr3 = new Class[3];
            clsArr3[0] = Context.class;
            Class[] clsArr4 = clsArr3;
            clsArr4[1] = String.class;
            Class[] clsArr5 = clsArr4;
            clsArr5[2] = String[].class;
            d = cls2.getMethod("trackCustomEvent", clsArr5);
            Class<?> cls3 = b;
            Class[] clsArr6 = new Class[2];
            clsArr6[0] = Context.class;
            Class[] clsArr7 = clsArr6;
            clsArr7[1] = Integer.TYPE;
            e = cls3.getMethod("commitEvents", clsArr7);
            f = a.getMethod("setEnableStatService", new Class[]{Boolean.TYPE});
            b(context2, qQToken2);
            Object[] objArr = {false};
            Object invoke = a.getMethod("setAutoExceptionCaught", new Class[]{Boolean.TYPE}).invoke(a, objArr);
            Object[] objArr2 = {true};
            Object invoke2 = a.getMethod("setEnableSmartReporting", new Class[]{Boolean.TYPE}).invoke(a, objArr2);
            Object[] objArr3 = {1440};
            Object invoke3 = a.getMethod("setSendPeriodMinutes", new Class[]{Integer.TYPE}).invoke(a, objArr3);
            Class<?> cls4 = Class.forName("com.tencent.stat.StatReportStrategy");
            Object[] objArr4 = {cls4.getField("PERIOD").get((Object) null)};
            Object invoke4 = a.getMethod("setStatSendStrategy", new Class[]{cls4}).invoke(a, objArr4);
            Class<?> cls5 = b;
            Class[] clsArr8 = new Class[3];
            clsArr8[0] = Context.class;
            Class[] clsArr9 = clsArr8;
            clsArr9[1] = String.class;
            Class[] clsArr10 = clsArr9;
            clsArr10[2] = String.class;
            Method method = cls5.getMethod("startStatService", clsArr10);
            Class<?> cls6 = b;
            Object[] objArr5 = new Object[3];
            objArr5[0] = context2;
            Object[] objArr6 = objArr5;
            objArr6[1] = sb2;
            Object[] objArr7 = objArr6;
            objArr7[2] = Class.forName("com.tencent.stat.common.StatConstants").getField("VERSION").get((Object) null);
            Object invoke5 = method.invoke(cls6, objArr7);
            g = true;
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void d(Context context, QQToken qQToken) {
        Context context2 = context;
        QQToken qQToken2 = qQToken;
        if (!TextUtils.isEmpty(qQToken2.getOpenId())) {
            d.a().a(qQToken2.getOpenId(), qQToken2.getAppId(), "2", "1", Constants.VIA_REPORT_TYPE_SHARE_TO_QZONE, "0", "0", "0");
        }
        if (g) {
            b(context2, qQToken2);
            if (null != qQToken2.getOpenId()) {
                try {
                    Method method = c;
                    Class<?> cls = b;
                    Object[] objArr = new Object[2];
                    objArr[0] = context2;
                    Object[] objArr2 = objArr;
                    objArr2[1] = qQToken2.getOpenId();
                    Object invoke = method.invoke(cls, objArr2);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }
}
