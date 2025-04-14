package com.tencent.open.utils;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import com.tencent.connect.common.Constants;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class f {
    private static Map<String, f> a;
    private static String b = null;
    /* access modifiers changed from: private */
    public Context c = null;
    private String d = null;
    private JSONObject e = null;
    private long f = 0;
    private int g = 0;
    private boolean h = true;

    static {
        Map map;
        new HashMap();
        a = Collections.synchronizedMap(map);
    }

    private f(Context context, String str) {
        this.c = context.getApplicationContext();
        this.d = str;
        a();
        b();
    }

    static /* synthetic */ int a(f fVar, int i) {
        int i2 = i;
        int i3 = i2;
        fVar.g = i3;
        return i2;
    }

    /* JADX INFO: finally extract failed */
    public static f a(Context context, String str) {
        f fVar;
        Context context2 = context;
        String str2 = str;
        Map<String, f> map = a;
        Map<String, f> map2 = map;
        synchronized (map) {
            try {
                com.tencent.open.a.f.a("openSDK_LOG.OpenConfig", "getInstance begin");
                if (null != str2) {
                    b = str2;
                }
                if (null == str2) {
                    str2 = null != b ? b : "0";
                }
                f fVar2 = a.get(str2);
                if (null == fVar2) {
                    new f(context2, str2);
                    fVar2 = fVar;
                    f put = a.put(str2, fVar2);
                }
                com.tencent.open.a.f.a("openSDK_LOG.OpenConfig", "getInstance end");
                f fVar3 = fVar2;
                return fVar3;
            } catch (Throwable th) {
                Throwable th2 = th;
                Map<String, f> map3 = map2;
                throw th2;
            }
        }
    }

    private void a() {
        JSONObject jSONObject;
        JSONObject jSONObject2;
        try {
            new JSONObject(c("com.tencent.open.config.json"));
            this.e = jSONObject2;
        } catch (JSONException e2) {
            JSONException jSONException = e2;
            new JSONObject();
            this.e = jSONObject;
        }
    }

    private void a(String str, String str2) {
        String str3;
        OutputStreamWriter outputStreamWriter;
        StringBuilder sb;
        String str4 = str;
        String str5 = str2;
        try {
            if (null != this.d) {
                new StringBuilder();
                str3 = sb.append(str4).append(".").append(this.d).toString();
            } else {
                str3 = str4;
            }
            new OutputStreamWriter(this.c.openFileOutput(str3, 0), Charset.forName("UTF-8"));
            OutputStreamWriter outputStreamWriter2 = outputStreamWriter;
            outputStreamWriter2.write(str5);
            outputStreamWriter2.flush();
            outputStreamWriter2.close();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public void a(JSONObject jSONObject) {
        JSONObject jSONObject2 = jSONObject;
        d("cgi back, do update");
        this.e = jSONObject2;
        a("com.tencent.open.config.json", jSONObject2.toString());
        this.f = SystemClock.elapsedRealtime();
    }

    private void b() {
        Bundle bundle;
        AnonymousClass1 r6;
        if (0 != this.g) {
            d("update thread is running, return");
            return;
        }
        this.g = 1;
        new Bundle();
        Bundle bundle2 = bundle;
        bundle2.putString("appid", this.d);
        bundle2.putString("appid_for_getting_config", this.d);
        bundle2.putString("status_os", Build.VERSION.RELEASE);
        bundle2.putString("status_machine", Build.MODEL);
        bundle2.putString("status_version", Build.VERSION.SDK);
        bundle2.putString("sdkv", Constants.SDK_VERSION);
        bundle2.putString("sdkp", "a");
        final Bundle bundle3 = bundle2;
        new Thread(this) {
            final /* synthetic */ f b;

            {
                this.b = r6;
            }

            public void run() {
                try {
                    this.b.a(k.d(HttpUtils.openUrl2(this.b.c, "http://cgi.connect.qq.com/qqconnectopen/openapi/policy_conf", Constants.HTTP_GET, bundle3).a));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                int a2 = f.a(this.b, 0);
            }
        };
        r6.start();
    }

    private String c(String str) {
        FileInputStream open;
        BufferedReader bufferedReader;
        Reader reader;
        StringBuffer stringBuffer;
        String str2;
        StringBuilder sb;
        String str3 = str;
        String str4 = "";
        try {
            if (null != this.d) {
                new StringBuilder();
                str2 = sb.append(str3).append(".").append(this.d).toString();
            } else {
                str2 = str3;
            }
            open = this.c.openFileInput(str2);
        } catch (FileNotFoundException e2) {
            FileNotFoundException fileNotFoundException = e2;
            try {
                open = this.c.getAssets().open(str3);
            } catch (IOException e3) {
                e3.printStackTrace();
                return str4;
            }
        }
        new InputStreamReader(open, Charset.forName("UTF-8"));
        new BufferedReader(reader);
        BufferedReader bufferedReader2 = bufferedReader;
        new StringBuffer();
        StringBuffer stringBuffer2 = stringBuffer;
        while (true) {
            try {
                String readLine = bufferedReader2.readLine();
                String str5 = readLine;
                if (null == readLine) {
                    break;
                }
                StringBuffer append = stringBuffer2.append(str5);
            } catch (IOException e4) {
                e4.printStackTrace();
                try {
                    open.close();
                    bufferedReader2.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                try {
                    open.close();
                    bufferedReader2.close();
                } catch (IOException e6) {
                    e6.printStackTrace();
                }
                throw th2;
            }
        }
        str4 = stringBuffer2.toString();
        try {
            open.close();
            bufferedReader2.close();
        } catch (IOException e7) {
            e7.printStackTrace();
        }
        return str4;
    }

    private void c() {
        int optInt = this.e.optInt("Common_frequency");
        if (0 == optInt) {
            optInt = 1;
        }
        if (SystemClock.elapsedRealtime() - this.f >= ((long) (optInt * 3600000))) {
            b();
        }
    }

    private void d(String str) {
        StringBuilder sb;
        String str2 = str;
        if (this.h) {
            new StringBuilder();
            com.tencent.open.a.f.a("openSDK_LOG.OpenConfig", sb.append(str2).append("; appid: ").append(this.d).toString());
        }
    }

    public int a(String str) {
        StringBuilder sb;
        String str2 = str;
        new StringBuilder();
        d(sb.append("get ").append(str2).toString());
        c();
        return this.e.optInt(str2);
    }

    public boolean b(String str) {
        StringBuilder sb;
        String str2 = str;
        new StringBuilder();
        d(sb.append("get ").append(str2).toString());
        c();
        Object opt = this.e.opt(str2);
        if (null == opt) {
            return false;
        }
        if (opt instanceof Integer) {
            return !opt.equals(0);
        } else if (opt instanceof Boolean) {
            return ((Boolean) opt).booleanValue();
        } else {
            return false;
        }
    }
}
