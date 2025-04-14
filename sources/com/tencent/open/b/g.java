package com.tencent.open.b;

import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.view.PointerIconCompat;
import android.text.TextUtils;
import com.tencent.connect.common.Constants;
import com.tencent.open.utils.e;
import com.tencent.open.utils.f;
import com.tencent.open.utils.i;
import com.tencent.open.utils.k;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;
import java.util.concurrent.Executor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class g {
    protected static g a;
    protected Random b;
    protected List<Serializable> c;
    protected List<Serializable> d;
    protected HandlerThread e = null;
    protected Handler f;
    protected Executor g;
    protected Executor h;

    private g() {
        Random random;
        List list;
        List list2;
        Handler handler;
        HandlerThread handlerThread;
        new Random();
        this.b = random;
        new ArrayList();
        this.d = Collections.synchronizedList(list);
        new ArrayList();
        this.c = Collections.synchronizedList(list2);
        this.g = i.b();
        this.h = i.b();
        if (this.e == null) {
            new HandlerThread("opensdk.report.handlerthread", 10);
            this.e = handlerThread;
            this.e.start();
        }
        if (this.e.isAlive() && this.e.getLooper() != null) {
            new Handler(this, this.e.getLooper()) {
                final /* synthetic */ g a;

                {
                    this.a = r6;
                }

                public void handleMessage(Message message) {
                    Message message2 = message;
                    switch (message2.what) {
                        case 1000:
                            this.a.b();
                            break;
                        case PointerIconCompat.TYPE_CONTEXT_MENU:
                            this.a.e();
                            break;
                    }
                    super.handleMessage(message2);
                }
            };
            this.f = handler;
        }
    }

    public static synchronized g a() {
        g gVar;
        g gVar2;
        synchronized (g.class) {
            if (a == null) {
                new g();
                a = gVar2;
            }
            gVar = a;
        }
        return gVar;
    }

    /* access modifiers changed from: protected */
    public int a(int i) {
        int i2;
        if (i == 0) {
            int a2 = f.a(e.a(), (String) null).a("Common_CGIReportFrequencySuccess");
            i2 = a2 == 0 ? 10 : a2;
        } else {
            int a3 = f.a(e.a(), (String) null).a("Common_CGIReportFrequencyFailed");
            i2 = a3 == 0 ? 100 : a3;
        }
        return i2;
    }

    public void a(Bundle bundle, String str, boolean z) {
        StringBuilder sb;
        Runnable runnable;
        Bundle bundle2 = bundle;
        String str2 = str;
        boolean z2 = z;
        if (bundle2 != null) {
            new StringBuilder();
            com.tencent.open.a.f.a("openSDK_LOG.ReportManager", sb.append("-->reportVia, bundle: ").append(bundle2.toString()).toString());
            if (a("report_via", str2) || z2) {
                final Bundle bundle3 = bundle2;
                final boolean z3 = z2;
                new Runnable(this) {
                    final /* synthetic */ g c;

                    {
                        this.c = r7;
                    }

                    public void run() {
                        Bundle bundle;
                        Object obj;
                        try {
                            new Bundle();
                            Bundle bundle2 = bundle;
                            bundle2.putString("uin", Constants.DEFAULT_UIN);
                            bundle2.putString("imei", c.b(e.a()));
                            bundle2.putString("imsi", c.c(e.a()));
                            bundle2.putString("android_id", c.d(e.a()));
                            bundle2.putString("mac", c.a());
                            bundle2.putString(Constants.PARAM_PLATFORM, "1");
                            bundle2.putString("os_ver", Build.VERSION.RELEASE);
                            bundle2.putString("position", k.c(e.a()));
                            bundle2.putString("network", a.a(e.a()));
                            bundle2.putString("language", c.b());
                            bundle2.putString("resolution", c.a(e.a()));
                            bundle2.putString("apn", a.b(e.a()));
                            bundle2.putString("model_name", Build.MODEL);
                            bundle2.putString("timezone", TimeZone.getDefault().getID());
                            bundle2.putString("sdk_ver", Constants.SDK_VERSION);
                            bundle2.putString("qz_ver", k.d(e.a(), Constants.PACKAGE_QZONE));
                            bundle2.putString("qq_ver", k.c(e.a(), "com.tencent.mobileqq"));
                            bundle2.putString("qua", k.e(e.a(), e.b()));
                            bundle2.putString("packagename", e.b());
                            bundle2.putString("app_ver", k.d(e.a(), e.b()));
                            if (bundle3 != null) {
                                bundle2.putAll(bundle3);
                            }
                            new b(bundle2);
                            boolean add = this.c.d.add(obj);
                            int size = this.c.d.size();
                            int a2 = f.a(e.a(), (String) null).a("Agent_ReportTimeInterval");
                            int i = a2 == 0 ? 10000 : a2;
                            if (this.c.a("report_via", size) || z3) {
                                this.c.e();
                                this.c.f.removeMessages(PointerIconCompat.TYPE_CONTEXT_MENU);
                            } else if (!this.c.f.hasMessages(PointerIconCompat.TYPE_CONTEXT_MENU)) {
                                Message obtain = Message.obtain();
                                obtain.what = PointerIconCompat.TYPE_CONTEXT_MENU;
                                boolean sendMessageDelayed = this.c.f.sendMessageDelayed(obtain, (long) i);
                            }
                        } catch (Exception e) {
                            com.tencent.open.a.f.b("openSDK_LOG.ReportManager", "--> reporVia, exception in sub thread.", e);
                        }
                    }
                };
                this.g.execute(runnable);
            }
        }
    }

    public void a(String str, long j, long j2, long j3, int i) {
        a(str, j, j2, j3, i, "", false);
    }

    public void a(String str, long j, long j2, long j3, int i, String str2, boolean z) {
        StringBuilder sb;
        StringBuilder sb2;
        Runnable runnable;
        String str3 = str;
        long j4 = j;
        long j5 = j2;
        long j6 = j3;
        int i2 = i;
        String str4 = str2;
        boolean z2 = z;
        new StringBuilder();
        com.tencent.open.a.f.a("openSDK_LOG.ReportManager", sb.append("-->reportCgi, command: ").append(str3).append(" | startTime: ").append(j4).append(" | reqSize:").append(j5).append(" | rspSize: ").append(j6).append(" | responseCode: ").append(i2).append(" | detail: ").append(str4).toString());
        new StringBuilder();
        if (a("report_cgi", sb2.append("").append(i2).toString()) || z2) {
            final long j7 = j4;
            final String str5 = str3;
            final String str6 = str4;
            final int i3 = i2;
            final long j8 = j5;
            final long j9 = j6;
            final boolean z3 = z2;
            new Runnable(this) {
                final /* synthetic */ g h;

                {
                    this.h = r16;
                }

                public void run() {
                    Bundle bundle;
                    StringBuilder sb;
                    StringBuilder sb2;
                    StringBuilder sb3;
                    StringBuilder sb4;
                    StringBuilder sb5;
                    StringBuilder sb6;
                    Object obj;
                    try {
                        long elapsedRealtime = SystemClock.elapsedRealtime() - j7;
                        new Bundle();
                        Bundle bundle2 = bundle;
                        String a2 = a.a(e.a());
                        bundle2.putString("apn", a2);
                        bundle2.putString("appid", "1000067");
                        bundle2.putString("commandid", str5);
                        bundle2.putString("detail", str6);
                        new StringBuilder();
                        StringBuilder sb7 = sb;
                        StringBuilder append = sb7.append("network=").append(a2).append('&');
                        StringBuilder append2 = sb7.append("sdcard=").append(Environment.getExternalStorageState().equals("mounted") ? 1 : 0).append('&');
                        StringBuilder append3 = sb7.append("wifi=").append(a.e(e.a()));
                        bundle2.putString("deviceInfo", sb7.toString());
                        int a3 = 100 / this.h.a(i3);
                        if (a3 <= 0) {
                            a3 = 1;
                        } else if (a3 > 100) {
                            a3 = 100;
                        }
                        new StringBuilder();
                        bundle2.putString("frequency", sb2.append(a3).append("").toString());
                        new StringBuilder();
                        bundle2.putString("reqSize", sb3.append(j8).append("").toString());
                        new StringBuilder();
                        bundle2.putString("resultCode", sb4.append(i3).append("").toString());
                        new StringBuilder();
                        bundle2.putString("rspSize", sb5.append(j9).append("").toString());
                        new StringBuilder();
                        bundle2.putString("timeCost", sb6.append(elapsedRealtime).append("").toString());
                        bundle2.putString("uin", Constants.DEFAULT_UIN);
                        new b(bundle2);
                        boolean add = this.h.c.add(obj);
                        int size = this.h.c.size();
                        int a4 = f.a(e.a(), (String) null).a("Agent_ReportTimeInterval");
                        int i = a4 == 0 ? 10000 : a4;
                        if (this.h.a("report_cgi", size) || z3) {
                            this.h.b();
                            this.h.f.removeMessages(1000);
                        } else if (!this.h.f.hasMessages(1000)) {
                            Message obtain = Message.obtain();
                            obtain.what = 1000;
                            boolean sendMessageDelayed = this.h.f.sendMessageDelayed(obtain, (long) i);
                        }
                    } catch (Exception e2) {
                        com.tencent.open.a.f.b("openSDK_LOG.ReportManager", "--> reportCGI, exception in sub thread.", e2);
                    }
                }
            };
            this.h.execute(runnable);
        }
    }

    public void a(String str, String str2, Bundle bundle, boolean z) {
        Runnable runnable;
        final Bundle bundle2 = bundle;
        final String str3 = str;
        final boolean z2 = z;
        final String str4 = str2;
        new Runnable(this) {
            final /* synthetic */ g e;

            {
                this.e = r9;
            }

            /* JADX WARNING: Removed duplicated region for block: B:22:0x00db  */
            /* JADX WARNING: Removed duplicated region for block: B:45:0x0150 A[Catch:{ Exception -> 0x0158 }] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r14 = this;
                    r0 = r14
                    r10 = 0
                    r11 = r0
                    android.os.Bundle r11 = r8     // Catch:{ Exception -> 0x0158 }
                    if (r10 != r11) goto L_0x000f
                    java.lang.String r10 = "openSDK_LOG.ReportManager"
                    java.lang.String r11 = "-->httpRequest, params is null!"
                    com.tencent.open.a.f.e(r10, r11)     // Catch:{ Exception -> 0x0158 }
                L_0x000e:
                    return
                L_0x000f:
                    r10 = 0
                    r1 = r10
                    int r10 = com.tencent.open.b.e.a()     // Catch:{ Exception -> 0x0158 }
                    r2 = r10
                    r10 = r2
                    if (r10 != 0) goto L_0x00e4
                    r10 = 3
                L_0x001a:
                    r2 = r10
                    java.lang.String r10 = "openSDK_LOG.ReportManager"
                    java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0158 }
                    r13 = r11
                    r11 = r13
                    r12 = r13
                    r12.<init>()     // Catch:{ Exception -> 0x0158 }
                    java.lang.String r12 = "-->httpRequest, retryCount: "
                    java.lang.StringBuilder r11 = r11.append(r12)     // Catch:{ Exception -> 0x0158 }
                    r12 = r2
                    java.lang.StringBuilder r11 = r11.append(r12)     // Catch:{ Exception -> 0x0158 }
                    java.lang.String r11 = r11.toString()     // Catch:{ Exception -> 0x0158 }
                    com.tencent.open.a.f.b(r10, r11)     // Catch:{ Exception -> 0x0158 }
                    r10 = 0
                    r3 = r10
                    android.content.Context r10 = com.tencent.open.utils.e.a()     // Catch:{ Exception -> 0x0158 }
                    r11 = 0
                    r12 = r0
                    java.lang.String r12 = r9     // Catch:{ Exception -> 0x0158 }
                    org.apache.http.client.HttpClient r10 = com.tencent.open.utils.HttpUtils.getHttpClient(r10, r11, r12)     // Catch:{ Exception -> 0x0158 }
                    r4 = r10
                    r10 = 0
                    r5 = r10
                    r10 = r0
                    android.os.Bundle r10 = r8     // Catch:{ Exception -> 0x0158 }
                    java.lang.String r10 = com.tencent.open.utils.HttpUtils.encodeUrl(r10)     // Catch:{ Exception -> 0x0158 }
                    r6 = r10
                    r10 = r0
                    boolean r10 = r10     // Catch:{ Exception -> 0x0158 }
                    if (r10 == 0) goto L_0x005b
                    r10 = r6
                    java.lang.String r10 = java.net.URLEncoder.encode(r10)     // Catch:{ Exception -> 0x0158 }
                    r6 = r10
                L_0x005b:
                    r10 = r0
                    java.lang.String r10 = r11     // Catch:{ Exception -> 0x0158 }
                    java.lang.String r10 = r10.toUpperCase()     // Catch:{ Exception -> 0x0158 }
                    java.lang.String r11 = "GET"
                    boolean r10 = r10.equals(r11)     // Catch:{ Exception -> 0x0158 }
                    if (r10 == 0) goto L_0x00e7
                    java.lang.StringBuffer r10 = new java.lang.StringBuffer     // Catch:{ Exception -> 0x0158 }
                    r13 = r10
                    r10 = r13
                    r11 = r13
                    r12 = r0
                    java.lang.String r12 = r9     // Catch:{ Exception -> 0x0158 }
                    r11.<init>(r12)     // Catch:{ Exception -> 0x0158 }
                    r7 = r10
                    r10 = r7
                    r11 = r6
                    java.lang.StringBuffer r10 = r10.append(r11)     // Catch:{ Exception -> 0x0158 }
                    org.apache.http.client.methods.HttpGet r10 = new org.apache.http.client.methods.HttpGet     // Catch:{ Exception -> 0x0158 }
                    r13 = r10
                    r10 = r13
                    r11 = r13
                    r12 = r7
                    java.lang.String r12 = r12.toString()     // Catch:{ Exception -> 0x0158 }
                    r11.<init>(r12)     // Catch:{ Exception -> 0x0158 }
                    r5 = r10
                L_0x008a:
                    r10 = r5
                    java.lang.String r11 = "Accept-Encoding"
                    java.lang.String r12 = "gzip"
                    r10.addHeader(r11, r12)     // Catch:{ Exception -> 0x0158 }
                    r10 = r5
                    java.lang.String r11 = "Content-Type"
                    java.lang.String r12 = "application/x-www-form-urlencoded"
                    r10.addHeader(r11, r12)     // Catch:{ Exception -> 0x0158 }
                L_0x009a:
                    int r1 = r1 + 1
                    r10 = r4
                    r11 = r5
                    org.apache.http.HttpResponse r10 = r10.execute(r11)     // Catch:{ ConnectTimeoutException -> 0x012e, SocketTimeoutException -> 0x013c, Exception -> 0x0146 }
                    r7 = r10
                    r10 = r7
                    org.apache.http.StatusLine r10 = r10.getStatusLine()     // Catch:{ ConnectTimeoutException -> 0x012e, SocketTimeoutException -> 0x013c, Exception -> 0x0146 }
                    r8 = r10
                    r10 = r8
                    int r10 = r10.getStatusCode()     // Catch:{ ConnectTimeoutException -> 0x012e, SocketTimeoutException -> 0x013c, Exception -> 0x0146 }
                    r9 = r10
                    java.lang.String r10 = "openSDK_LOG.ReportManager"
                    java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ ConnectTimeoutException -> 0x012e, SocketTimeoutException -> 0x013c, Exception -> 0x0146 }
                    r13 = r11
                    r11 = r13
                    r12 = r13
                    r12.<init>()     // Catch:{ ConnectTimeoutException -> 0x012e, SocketTimeoutException -> 0x013c, Exception -> 0x0146 }
                    java.lang.String r12 = "-->httpRequest, statusCode: "
                    java.lang.StringBuilder r11 = r11.append(r12)     // Catch:{ ConnectTimeoutException -> 0x012e, SocketTimeoutException -> 0x013c, Exception -> 0x0146 }
                    r12 = r9
                    java.lang.StringBuilder r11 = r11.append(r12)     // Catch:{ ConnectTimeoutException -> 0x012e, SocketTimeoutException -> 0x013c, Exception -> 0x0146 }
                    java.lang.String r11 = r11.toString()     // Catch:{ ConnectTimeoutException -> 0x012e, SocketTimeoutException -> 0x013c, Exception -> 0x0146 }
                    com.tencent.open.a.f.b(r10, r11)     // Catch:{ ConnectTimeoutException -> 0x012e, SocketTimeoutException -> 0x013c, Exception -> 0x0146 }
                    r10 = r9
                    r11 = 200(0xc8, float:2.8E-43)
                    if (r10 == r11) goto L_0x0124
                    java.lang.String r10 = "openSDK_LOG.ReportManager"
                    java.lang.String r11 = "-->ReportCenter httpRequest : HttpStatuscode != 200"
                    com.tencent.open.a.f.b(r10, r11)     // Catch:{ ConnectTimeoutException -> 0x012e, SocketTimeoutException -> 0x013c, Exception -> 0x0146 }
                L_0x00d7:
                    r10 = r3
                    r11 = 1
                    if (r10 != r11) goto L_0x0150
                    java.lang.String r10 = "openSDK_LOG.ReportManager"
                    java.lang.String r11 = "-->ReportCenter httpRequest Thread request success"
                    com.tencent.open.a.f.b(r10, r11)     // Catch:{ Exception -> 0x0158 }
                L_0x00e2:
                    goto L_0x000e
                L_0x00e4:
                    r10 = r2
                    goto L_0x001a
                L_0x00e7:
                    r10 = r0
                    java.lang.String r10 = r11     // Catch:{ Exception -> 0x0158 }
                    java.lang.String r10 = r10.toUpperCase()     // Catch:{ Exception -> 0x0158 }
                    java.lang.String r11 = "POST"
                    boolean r10 = r10.equals(r11)     // Catch:{ Exception -> 0x0158 }
                    if (r10 == 0) goto L_0x011b
                    org.apache.http.client.methods.HttpPost r10 = new org.apache.http.client.methods.HttpPost     // Catch:{ Exception -> 0x0158 }
                    r13 = r10
                    r10 = r13
                    r11 = r13
                    r12 = r0
                    java.lang.String r12 = r9     // Catch:{ Exception -> 0x0158 }
                    r11.<init>(r12)     // Catch:{ Exception -> 0x0158 }
                    r7 = r10
                    r10 = r6
                    byte[] r10 = com.tencent.open.utils.k.i(r10)     // Catch:{ Exception -> 0x0158 }
                    r8 = r10
                    org.apache.http.entity.ByteArrayEntity r10 = new org.apache.http.entity.ByteArrayEntity     // Catch:{ Exception -> 0x0158 }
                    r13 = r10
                    r10 = r13
                    r11 = r13
                    r12 = r8
                    r11.<init>(r12)     // Catch:{ Exception -> 0x0158 }
                    r9 = r10
                    r10 = r7
                    r11 = r9
                    r10.setEntity(r11)     // Catch:{ Exception -> 0x0158 }
                    r10 = r7
                    r5 = r10
                    goto L_0x008a
                L_0x011b:
                    java.lang.String r10 = "openSDK_LOG.ReportManager"
                    java.lang.String r11 = "-->httpRequest unkonw request method return."
                    com.tencent.open.a.f.e(r10, r11)     // Catch:{ Exception -> 0x0158 }
                    goto L_0x000e
                L_0x0124:
                    r10 = 1
                    r3 = r10
                    java.lang.String r10 = "openSDK_LOG.ReportManager"
                    java.lang.String r11 = "-->ReportCenter httpRequest Thread success"
                    com.tencent.open.a.f.b(r10, r11)     // Catch:{ ConnectTimeoutException -> 0x012e, SocketTimeoutException -> 0x013c, Exception -> 0x0146 }
                    goto L_0x00d7
                L_0x012e:
                    r10 = move-exception
                    r7 = r10
                    java.lang.String r10 = "openSDK_LOG.ReportManager"
                    java.lang.String r11 = "-->ReportCenter httpRequest ConnectTimeoutException"
                    com.tencent.open.a.f.b(r10, r11)     // Catch:{ Exception -> 0x0158 }
                L_0x0137:
                    r10 = r1
                    r11 = r2
                    if (r10 < r11) goto L_0x009a
                    goto L_0x00d7
                L_0x013c:
                    r10 = move-exception
                    r7 = r10
                    java.lang.String r10 = "openSDK_LOG.ReportManager"
                    java.lang.String r11 = "-->ReportCenter httpRequest SocketTimeoutException"
                    com.tencent.open.a.f.b(r10, r11)     // Catch:{ Exception -> 0x0158 }
                    goto L_0x0137
                L_0x0146:
                    r10 = move-exception
                    r7 = r10
                    java.lang.String r10 = "openSDK_LOG.ReportManager"
                    java.lang.String r11 = "-->ReportCenter httpRequest Exception"
                    com.tencent.open.a.f.b(r10, r11)     // Catch:{ Exception -> 0x0158 }
                    goto L_0x00d7
                L_0x0150:
                    java.lang.String r10 = "openSDK_LOG.ReportManager"
                    java.lang.String r11 = "-->ReportCenter httpRequest Thread request failed"
                    com.tencent.open.a.f.b(r10, r11)     // Catch:{ Exception -> 0x0158 }
                    goto L_0x00e2
                L_0x0158:
                    r10 = move-exception
                    r1 = r10
                    java.lang.String r10 = "openSDK_LOG.ReportManager"
                    java.lang.String r11 = "-->httpRequest, exception in serial executor."
                    com.tencent.open.a.f.b(r10, r11)
                    goto L_0x00e2
                */
                throw new UnsupportedOperationException("Method not decompiled: com.tencent.open.b.g.AnonymousClass6.run():void");
            }
        };
        i.a(runnable);
    }

    /* access modifiers changed from: protected */
    public boolean a(String str, int i) {
        StringBuilder sb;
        String str2 = str;
        int i2 = i;
        int i3 = 0;
        if (str2.equals("report_cgi")) {
            int a2 = f.a(e.a(), (String) null).a("Common_CGIReportMaxcount");
            i3 = a2 == 0 ? 5 : a2;
        } else if (str2.equals("report_via")) {
            int a3 = f.a(e.a(), (String) null).a("Agent_ReportBatchCount");
            i3 = a3 == 0 ? 5 : a3;
        }
        new StringBuilder();
        com.tencent.open.a.f.b("openSDK_LOG.ReportManager", sb.append("-->availableCount, report: ").append(str2).append(" | dataSize: ").append(i2).append(" | maxcount: ").append(i3).toString());
        return i2 >= i3;
    }

    /* access modifiers changed from: protected */
    public boolean a(String str, String str2) {
        StringBuilder sb;
        StringBuilder sb2;
        String str3 = str;
        String str4 = str2;
        new StringBuilder();
        com.tencent.open.a.f.b("openSDK_LOG.ReportManager", sb.append("-->availableFrequency, report: ").append(str3).append(" | ext: ").append(str4).toString());
        if (TextUtils.isEmpty(str3)) {
            return false;
        }
        boolean z = false;
        int i = 100;
        if (str3.equals("report_cgi")) {
            try {
                i = a(Integer.parseInt(str4));
                z = this.b.nextInt(100) < i;
            } catch (Exception e2) {
                Exception exc = e2;
                return false;
            }
        } else if (str3.equals("report_via")) {
            i = e.a(str4);
            z = this.b.nextInt(100) < i;
        }
        new StringBuilder();
        com.tencent.open.a.f.b("openSDK_LOG.ReportManager", sb2.append("-->availableFrequency, result: ").append(z).append(" | frequency: ").append(i).toString());
        return z;
    }

    /* access modifiers changed from: protected */
    public void b() {
        Runnable runnable;
        new Runnable(this) {
            final /* synthetic */ g a;

            {
                this.a = r5;
            }

            /* JADX WARNING: Removed duplicated region for block: B:33:0x011a A[SYNTHETIC] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r19 = this;
                    r1 = r19
                    r15 = 0
                    r2 = r15
                    r15 = r1
                    com.tencent.open.b.g r15 = r15.a     // Catch:{ Exception -> 0x0133 }
                    android.os.Bundle r15 = r15.c()     // Catch:{ Exception -> 0x0133 }
                    r2 = r15
                    r15 = r2
                    if (r15 != 0) goto L_0x0010
                L_0x000f:
                    return
                L_0x0010:
                    r15 = 0
                    r3 = r15
                    android.content.Context r15 = com.tencent.open.utils.e.a()     // Catch:{ Exception -> 0x0133 }
                    r16 = 0
                    com.tencent.open.utils.f r15 = com.tencent.open.utils.f.a((android.content.Context) r15, (java.lang.String) r16)     // Catch:{ Exception -> 0x0133 }
                    java.lang.String r16 = "Common_HttpRetryCount"
                    int r15 = r15.a((java.lang.String) r16)     // Catch:{ Exception -> 0x0133 }
                    r4 = r15
                    r15 = r4
                    if (r15 != 0) goto L_0x0105
                    r15 = 3
                L_0x0027:
                    r4 = r15
                    java.lang.String r15 = "openSDK_LOG.ReportManager"
                    java.lang.StringBuilder r16 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0133 }
                    r18 = r16
                    r16 = r18
                    r17 = r18
                    r17.<init>()     // Catch:{ Exception -> 0x0133 }
                    java.lang.String r17 = "-->doReportCgi, retryCount: "
                    java.lang.StringBuilder r16 = r16.append(r17)     // Catch:{ Exception -> 0x0133 }
                    r17 = r4
                    java.lang.StringBuilder r16 = r16.append(r17)     // Catch:{ Exception -> 0x0133 }
                    java.lang.String r16 = r16.toString()     // Catch:{ Exception -> 0x0133 }
                    com.tencent.open.a.f.b(r15, r16)     // Catch:{ Exception -> 0x0133 }
                    r15 = 0
                    r5 = r15
                L_0x004a:
                    int r3 = r3 + 1
                    android.content.Context r15 = com.tencent.open.utils.e.a()     // Catch:{ ConnectTimeoutException -> 0x0108, SocketTimeoutException -> 0x011b, Exception -> 0x0127 }
                    r16 = 0
                    java.lang.String r17 = "https://wspeed.qq.com/w.cgi"
                    org.apache.http.client.HttpClient r15 = com.tencent.open.utils.HttpUtils.getHttpClient(r15, r16, r17)     // Catch:{ ConnectTimeoutException -> 0x0108, SocketTimeoutException -> 0x011b, Exception -> 0x0127 }
                    r6 = r15
                    r15 = 0
                    r7 = r15
                    org.apache.http.client.methods.HttpPost r15 = new org.apache.http.client.methods.HttpPost     // Catch:{ ConnectTimeoutException -> 0x0108, SocketTimeoutException -> 0x011b, Exception -> 0x0127 }
                    r18 = r15
                    r15 = r18
                    r16 = r18
                    java.lang.String r17 = "https://wspeed.qq.com/w.cgi"
                    r16.<init>(r17)     // Catch:{ ConnectTimeoutException -> 0x0108, SocketTimeoutException -> 0x011b, Exception -> 0x0127 }
                    r8 = r15
                    r15 = r8
                    java.lang.String r16 = "Accept-Encoding"
                    java.lang.String r17 = "gzip"
                    r15.addHeader(r16, r17)     // Catch:{ ConnectTimeoutException -> 0x0108, SocketTimeoutException -> 0x011b, Exception -> 0x0127 }
                    r15 = r8
                    java.lang.String r16 = "Content-Type"
                    java.lang.String r17 = "application/x-www-form-urlencoded"
                    r15.setHeader(r16, r17)     // Catch:{ ConnectTimeoutException -> 0x0108, SocketTimeoutException -> 0x011b, Exception -> 0x0127 }
                    r15 = r2
                    java.lang.String r15 = com.tencent.open.utils.HttpUtils.encodeUrl(r15)     // Catch:{ ConnectTimeoutException -> 0x0108, SocketTimeoutException -> 0x011b, Exception -> 0x0127 }
                    r9 = r15
                    r15 = r9
                    byte[] r15 = com.tencent.open.utils.k.i(r15)     // Catch:{ ConnectTimeoutException -> 0x0108, SocketTimeoutException -> 0x011b, Exception -> 0x0127 }
                    r10 = r15
                    org.apache.http.entity.ByteArrayEntity r15 = new org.apache.http.entity.ByteArrayEntity     // Catch:{ ConnectTimeoutException -> 0x0108, SocketTimeoutException -> 0x011b, Exception -> 0x0127 }
                    r18 = r15
                    r15 = r18
                    r16 = r18
                    r17 = r10
                    r16.<init>(r17)     // Catch:{ ConnectTimeoutException -> 0x0108, SocketTimeoutException -> 0x011b, Exception -> 0x0127 }
                    r11 = r15
                    r15 = r8
                    r16 = r11
                    r15.setEntity(r16)     // Catch:{ ConnectTimeoutException -> 0x0108, SocketTimeoutException -> 0x011b, Exception -> 0x0127 }
                    r15 = r8
                    r7 = r15
                    r15 = r6
                    r16 = r7
                    org.apache.http.HttpResponse r15 = r15.execute(r16)     // Catch:{ ConnectTimeoutException -> 0x0108, SocketTimeoutException -> 0x011b, Exception -> 0x0127 }
                    r12 = r15
                    r15 = r12
                    org.apache.http.StatusLine r15 = r15.getStatusLine()     // Catch:{ ConnectTimeoutException -> 0x0108, SocketTimeoutException -> 0x011b, Exception -> 0x0127 }
                    r13 = r15
                    r15 = r13
                    int r15 = r15.getStatusCode()     // Catch:{ ConnectTimeoutException -> 0x0108, SocketTimeoutException -> 0x011b, Exception -> 0x0127 }
                    r14 = r15
                    java.lang.String r15 = "openSDK_LOG.ReportManager"
                    java.lang.StringBuilder r16 = new java.lang.StringBuilder     // Catch:{ ConnectTimeoutException -> 0x0108, SocketTimeoutException -> 0x011b, Exception -> 0x0127 }
                    r18 = r16
                    r16 = r18
                    r17 = r18
                    r17.<init>()     // Catch:{ ConnectTimeoutException -> 0x0108, SocketTimeoutException -> 0x011b, Exception -> 0x0127 }
                    java.lang.String r17 = "-->doReportCgi, statusCode: "
                    java.lang.StringBuilder r16 = r16.append(r17)     // Catch:{ ConnectTimeoutException -> 0x0108, SocketTimeoutException -> 0x011b, Exception -> 0x0127 }
                    r17 = r14
                    java.lang.StringBuilder r16 = r16.append(r17)     // Catch:{ ConnectTimeoutException -> 0x0108, SocketTimeoutException -> 0x011b, Exception -> 0x0127 }
                    java.lang.String r16 = r16.toString()     // Catch:{ ConnectTimeoutException -> 0x0108, SocketTimeoutException -> 0x011b, Exception -> 0x0127 }
                    com.tencent.open.a.f.b(r15, r16)     // Catch:{ ConnectTimeoutException -> 0x0108, SocketTimeoutException -> 0x011b, Exception -> 0x0127 }
                    r15 = r14
                    r16 = 200(0xc8, float:2.8E-43)
                    r0 = r16
                    if (r15 != r0) goto L_0x00e1
                    com.tencent.open.b.f r15 = com.tencent.open.b.f.a()     // Catch:{ ConnectTimeoutException -> 0x0108, SocketTimeoutException -> 0x011b, Exception -> 0x0127 }
                    java.lang.String r16 = "report_cgi"
                    r15.b(r16)     // Catch:{ ConnectTimeoutException -> 0x0108, SocketTimeoutException -> 0x011b, Exception -> 0x0127 }
                    r15 = 1
                    r5 = r15
                L_0x00e1:
                    r15 = r5
                    if (r15 != 0) goto L_0x00fb
                    com.tencent.open.b.f r15 = com.tencent.open.b.f.a()     // Catch:{ Exception -> 0x0133 }
                    java.lang.String r16 = "report_cgi"
                    r17 = r1
                    r0 = r17
                    com.tencent.open.b.g r0 = r0.a     // Catch:{ Exception -> 0x0133 }
                    r17 = r0
                    r0 = r17
                    java.util.List<java.io.Serializable> r0 = r0.c     // Catch:{ Exception -> 0x0133 }
                    r17 = r0
                    r15.a(r16, r17)     // Catch:{ Exception -> 0x0133 }
                L_0x00fb:
                    r15 = r1
                    com.tencent.open.b.g r15 = r15.a     // Catch:{ Exception -> 0x0133 }
                    java.util.List<java.io.Serializable> r15 = r15.c     // Catch:{ Exception -> 0x0133 }
                    r15.clear()     // Catch:{ Exception -> 0x0133 }
                L_0x0103:
                    goto L_0x000f
                L_0x0105:
                    r15 = r4
                    goto L_0x0027
                L_0x0108:
                    r15 = move-exception
                    r6 = r15
                    java.lang.String r15 = "openSDK_LOG.ReportManager"
                    java.lang.String r16 = "-->doReportCgi, doupload exception"
                    r17 = r6
                    com.tencent.open.a.f.b(r15, r16, r17)     // Catch:{ Exception -> 0x0133 }
                L_0x0113:
                    r15 = r3
                    r16 = r4
                    r0 = r16
                    if (r15 < r0) goto L_0x004a
                    goto L_0x00e1
                L_0x011b:
                    r15 = move-exception
                    r6 = r15
                    java.lang.String r15 = "openSDK_LOG.ReportManager"
                    java.lang.String r16 = "-->doReportCgi, doupload exception"
                    r17 = r6
                    com.tencent.open.a.f.b(r15, r16, r17)     // Catch:{ Exception -> 0x0133 }
                    goto L_0x0113
                L_0x0127:
                    r15 = move-exception
                    r6 = r15
                    java.lang.String r15 = "openSDK_LOG.ReportManager"
                    java.lang.String r16 = "-->doReportCgi, doupload exception"
                    r17 = r6
                    com.tencent.open.a.f.b(r15, r16, r17)     // Catch:{ Exception -> 0x0133 }
                    goto L_0x00e1
                L_0x0133:
                    r15 = move-exception
                    r2 = r15
                    java.lang.String r15 = "openSDK_LOG.ReportManager"
                    java.lang.String r16 = "-->doReportCgi, doupload exception out."
                    r17 = r2
                    com.tencent.open.a.f.b(r15, r16, r17)
                    goto L_0x0103
                */
                throw new UnsupportedOperationException("Method not decompiled: com.tencent.open.b.g.AnonymousClass4.run():void");
            }
        };
        this.h.execute(runnable);
    }

    /* access modifiers changed from: protected */
    public Bundle c() {
        StringBuilder sb;
        Bundle bundle;
        StringBuilder sb2;
        StringBuilder sb3;
        StringBuilder sb4;
        StringBuilder sb5;
        StringBuilder sb6;
        StringBuilder sb7;
        StringBuilder sb8;
        StringBuilder sb9;
        StringBuilder sb10;
        StringBuilder sb11;
        StringBuilder sb12;
        StringBuilder sb13;
        if (this.c.size() == 0) {
            return null;
        }
        b bVar = (b) this.c.get(0);
        if (bVar == null) {
            com.tencent.open.a.f.b("openSDK_LOG.ReportManager", "-->prepareCgiData, the 0th cgireportitem is null.");
            return null;
        }
        String str = bVar.a.get("appid");
        List<Serializable> a2 = f.a().a("report_cgi");
        if (a2 != null) {
            boolean addAll = this.c.addAll(a2);
        }
        new StringBuilder();
        com.tencent.open.a.f.b("openSDK_LOG.ReportManager", sb.append("-->prepareCgiData, mCgiList size: ").append(this.c.size()).toString());
        if (this.c.size() == 0) {
            return null;
        }
        new Bundle();
        Bundle bundle2 = bundle;
        try {
            bundle2.putString("appid", str);
            bundle2.putString("releaseversion", Constants.SDK_VERSION_REPORT);
            bundle2.putString("device", Build.DEVICE);
            bundle2.putString("qua", Constants.SDK_QUA);
            bundle2.putString("key", "apn,frequency,commandid,resultcode,tmcost,reqsize,rspsize,detail,touin,deviceinfo");
            for (int i = 0; i < this.c.size(); i++) {
                b bVar2 = (b) this.c.get(i);
                new StringBuilder();
                bundle2.putString(sb3.append(i).append("_1").toString(), bVar2.a.get("apn"));
                new StringBuilder();
                bundle2.putString(sb4.append(i).append("_2").toString(), bVar2.a.get("frequency"));
                new StringBuilder();
                bundle2.putString(sb5.append(i).append("_3").toString(), bVar2.a.get("commandid"));
                new StringBuilder();
                bundle2.putString(sb6.append(i).append("_4").toString(), bVar2.a.get("resultCode"));
                new StringBuilder();
                bundle2.putString(sb7.append(i).append("_5").toString(), bVar2.a.get("timeCost"));
                new StringBuilder();
                bundle2.putString(sb8.append(i).append("_6").toString(), bVar2.a.get("reqSize"));
                new StringBuilder();
                bundle2.putString(sb9.append(i).append("_7").toString(), bVar2.a.get("rspSize"));
                new StringBuilder();
                bundle2.putString(sb10.append(i).append("_8").toString(), bVar2.a.get("detail"));
                new StringBuilder();
                bundle2.putString(sb11.append(i).append("_9").toString(), bVar2.a.get("uin"));
                new StringBuilder();
                String sb14 = sb12.append(c.e(e.a())).append("&").append(bVar2.a.get("deviceInfo")).toString();
                new StringBuilder();
                bundle2.putString(sb13.append(i).append("_10").toString(), sb14);
            }
            new StringBuilder();
            com.tencent.open.a.f.a("openSDK_LOG.ReportManager", sb2.append("-->prepareCgiData, end. params: ").append(bundle2.toString()).toString());
            return bundle2;
        } catch (Exception e2) {
            com.tencent.open.a.f.b("openSDK_LOG.ReportManager", "-->prepareCgiData, exception.", e2);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public Bundle d() {
        StringBuilder sb;
        JSONArray jSONArray;
        StringBuilder sb2;
        Bundle bundle;
        JSONObject jSONObject;
        JSONObject jSONObject2;
        List<Serializable> a2 = f.a().a("report_via");
        if (a2 != null) {
            boolean addAll = this.d.addAll(a2);
        }
        new StringBuilder();
        com.tencent.open.a.f.b("openSDK_LOG.ReportManager", sb.append("-->prepareViaData, mViaList size: ").append(this.d.size()).toString());
        if (this.d.size() == 0) {
            return null;
        }
        new JSONArray();
        JSONArray jSONArray2 = jSONArray;
        for (Serializable next : this.d) {
            new JSONObject();
            JSONObject jSONObject3 = jSONObject2;
            b bVar = (b) next;
            for (String next2 : bVar.a.keySet()) {
                try {
                    String str = bVar.a.get(next2);
                    if (null == str) {
                        str = "";
                    }
                    JSONObject put = jSONObject3.put(next2, str);
                } catch (JSONException e2) {
                    com.tencent.open.a.f.b("openSDK_LOG.ReportManager", "-->prepareViaData, put bundle to json array exception", e2);
                }
            }
            JSONArray put2 = jSONArray2.put(jSONObject3);
        }
        new StringBuilder();
        com.tencent.open.a.f.a("openSDK_LOG.ReportManager", sb2.append("-->prepareViaData, JSONArray array: ").append(jSONArray2.toString()).toString());
        new Bundle();
        Bundle bundle2 = bundle;
        new JSONObject();
        JSONObject jSONObject4 = jSONObject;
        try {
            JSONObject put3 = jSONObject4.put("data", jSONArray2);
            bundle2.putString("data", jSONObject4.toString());
            return bundle2;
        } catch (JSONException e3) {
            com.tencent.open.a.f.b("openSDK_LOG.ReportManager", "-->prepareViaData, put bundle to json array exception", e3);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public void e() {
        Runnable runnable;
        new Runnable(this) {
            final /* synthetic */ g a;

            {
                this.a = r5;
            }

            /* JADX WARNING: Code restructure failed: missing block: B:18:0x0097, code lost:
                if (android.text.TextUtils.isEmpty(r14.a) == false) goto L_0x0099;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:33:0x0121, code lost:
                r18 = move-exception;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:34:0x0122, code lost:
                r14 = r18;
                r7 = android.os.SystemClock.elapsedRealtime();
                r9 = 0;
                r11 = 0;
                r13 = -7;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:35:0x0138, code lost:
                r18 = move-exception;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:36:0x0139, code lost:
                r14 = r18;
                r7 = android.os.SystemClock.elapsedRealtime();
                r9 = 0;
                r11 = 0;
                r13 = -8;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:37:0x014f, code lost:
                r18 = move-exception;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:38:0x0150, code lost:
                r14 = r18;
                r9 = 0;
                r11 = 0;
                r13 = -4;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:39:0x0160, code lost:
                r18 = move-exception;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:40:0x0161, code lost:
                r14 = r18;
                r2.a.d.clear();
                com.tencent.open.a.f.b("openSDK_LOG.ReportManager", "doReportVia, NetworkUnavailableException.");
             */
            /* JADX WARNING: Code restructure failed: missing block: B:41:0x017d, code lost:
                r18 = move-exception;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:45:0x0195, code lost:
                r13 = java.lang.Integer.parseInt(r18.getMessage().replace(com.tencent.open.utils.HttpUtils.HttpStatusException.ERROR_INFO, ""));
             */
            /* JADX WARNING: Code restructure failed: missing block: B:46:0x019a, code lost:
                r18 = move-exception;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:47:0x019b, code lost:
                r15 = r18;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:48:0x019e, code lost:
                r18 = move-exception;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:49:0x019f, code lost:
                r9 = 0;
                r11 = 0;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:51:?, code lost:
                r13 = com.tencent.open.utils.HttpUtils.getErrorCodeFromException(r18);
             */
            /* JADX WARNING: Code restructure failed: missing block: B:52:0x01b3, code lost:
                r18 = move-exception;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:53:0x01b4, code lost:
                r14 = r18;
                r9 = 0;
                r11 = 0;
                r13 = -6;
                r5 = r4;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:60:?, code lost:
                return;
             */
            /* JADX WARNING: Failed to process nested try/catch */
            /* JADX WARNING: Removed duplicated region for block: B:33:0x0121 A[Catch:{ Exception -> 0x01e1 }, ExcHandler: ConnectTimeoutException (r18v45 'e' org.apache.http.conn.ConnectTimeoutException A[CUSTOM_DECLARE, Catch:{ Exception -> 0x01e1 }]), Splitter:B:7:0x005b] */
            /* JADX WARNING: Removed duplicated region for block: B:35:0x0138 A[Catch:{ Exception -> 0x01e1 }, ExcHandler: SocketTimeoutException (r18v40 'e' java.net.SocketTimeoutException A[CUSTOM_DECLARE, Catch:{ Exception -> 0x01e1 }]), Splitter:B:7:0x005b] */
            /* JADX WARNING: Removed duplicated region for block: B:39:0x0160 A[Catch:{ Exception -> 0x01e1 }, ExcHandler: NetworkUnavailableException (r18v31 'e' com.tencent.open.utils.HttpUtils$NetworkUnavailableException A[CUSTOM_DECLARE, Catch:{ Exception -> 0x01e1 }]), Splitter:B:7:0x005b] */
            /* JADX WARNING: Removed duplicated region for block: B:41:0x017d A[ExcHandler: HttpStatusException (r18v24 'e' com.tencent.open.utils.HttpUtils$HttpStatusException A[CUSTOM_DECLARE]), Splitter:B:7:0x005b] */
            /* JADX WARNING: Removed duplicated region for block: B:48:0x019e A[ExcHandler: IOException (r18v19 'e' java.io.IOException A[CUSTOM_DECLARE]), Splitter:B:7:0x005b] */
            /* JADX WARNING: Removed duplicated region for block: B:52:0x01b3 A[Catch:{ Exception -> 0x01e1 }, ExcHandler: Exception (r18v14 'e' java.lang.Exception A[CUSTOM_DECLARE, Catch:{ Exception -> 0x01e1 }]), Splitter:B:7:0x005b] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r30 = this;
                    r2 = r30
                    r18 = r2
                    r0 = r18
                    com.tencent.open.b.g r0 = r0.a     // Catch:{ Exception -> 0x01e1 }
                    r18 = r0
                    android.os.Bundle r18 = r18.d()     // Catch:{ Exception -> 0x01e1 }
                    r3 = r18
                    r18 = r3
                    if (r18 != 0) goto L_0x0015
                L_0x0014:
                    return
                L_0x0015:
                    java.lang.String r18 = "openSDK_LOG.ReportManager"
                    java.lang.StringBuilder r19 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01e1 }
                    r29 = r19
                    r19 = r29
                    r20 = r29
                    r20.<init>()     // Catch:{ Exception -> 0x01e1 }
                    java.lang.String r20 = "-->doReportVia, params: "
                    java.lang.StringBuilder r19 = r19.append(r20)     // Catch:{ Exception -> 0x01e1 }
                    r20 = r3
                    java.lang.String r20 = r20.toString()     // Catch:{ Exception -> 0x01e1 }
                    java.lang.StringBuilder r19 = r19.append(r20)     // Catch:{ Exception -> 0x01e1 }
                    java.lang.String r19 = r19.toString()     // Catch:{ Exception -> 0x01e1 }
                    com.tencent.open.a.f.a(r18, r19)     // Catch:{ Exception -> 0x01e1 }
                    int r18 = com.tencent.open.b.e.a()     // Catch:{ Exception -> 0x01e1 }
                    r4 = r18
                    r18 = 0
                    r5 = r18
                    r18 = 0
                    r6 = r18
                    long r18 = android.os.SystemClock.elapsedRealtime()     // Catch:{ Exception -> 0x01e1 }
                    r7 = r18
                    r18 = 0
                    r9 = r18
                    r18 = 0
                    r11 = r18
                    r18 = 0
                    r13 = r18
                L_0x0059:
                    int r5 = r5 + 1
                    android.content.Context r18 = com.tencent.open.utils.e.a()     // Catch:{ ConnectTimeoutException -> 0x0121, SocketTimeoutException -> 0x0138, JSONException -> 0x014f, NetworkUnavailableException -> 0x0160, HttpStatusException -> 0x017d, IOException -> 0x019e, Exception -> 0x01b3 }
                    java.lang.String r19 = "https://appsupport.qq.com/cgi-bin/appstage/mstats_batch_report"
                    java.lang.String r20 = "POST"
                    r21 = r3
                    com.tencent.open.utils.k$a r18 = com.tencent.open.utils.HttpUtils.openUrl2(r18, r19, r20, r21)     // Catch:{ ConnectTimeoutException -> 0x0121, SocketTimeoutException -> 0x0138, JSONException -> 0x014f, NetworkUnavailableException -> 0x0160, HttpStatusException -> 0x017d, IOException -> 0x019e, Exception -> 0x01b3 }
                    r14 = r18
                    r18 = r14
                    r0 = r18
                    java.lang.String r0 = r0.a     // Catch:{ ConnectTimeoutException -> 0x0121, SocketTimeoutException -> 0x0138, JSONException -> 0x014f, NetworkUnavailableException -> 0x0160, HttpStatusException -> 0x017d, IOException -> 0x019e, Exception -> 0x01b3 }
                    r18 = r0
                    org.json.JSONObject r18 = com.tencent.open.utils.k.d((java.lang.String) r18)     // Catch:{ ConnectTimeoutException -> 0x0121, SocketTimeoutException -> 0x0138, JSONException -> 0x014f, NetworkUnavailableException -> 0x0160, HttpStatusException -> 0x017d, IOException -> 0x019e, Exception -> 0x01b3 }
                    r15 = r18
                    r18 = 0
                    r16 = r18
                    r18 = r15
                    java.lang.String r19 = "ret"
                    int r18 = r18.getInt(r19)     // Catch:{ JSONException -> 0x0118, ConnectTimeoutException -> 0x0121, SocketTimeoutException -> 0x0138, NetworkUnavailableException -> 0x0160, HttpStatusException -> 0x017d, IOException -> 0x019e, Exception -> 0x01b3 }
                    r16 = r18
                L_0x0087:
                    r18 = r16
                    if (r18 == 0) goto L_0x0099
                    r18 = r14
                    r0 = r18
                    java.lang.String r0 = r0.a     // Catch:{ ConnectTimeoutException -> 0x0121, SocketTimeoutException -> 0x0138, JSONException -> 0x014f, NetworkUnavailableException -> 0x0160, HttpStatusException -> 0x017d, IOException -> 0x019e, Exception -> 0x01b3 }
                    r18 = r0
                    boolean r18 = android.text.TextUtils.isEmpty(r18)     // Catch:{ ConnectTimeoutException -> 0x0121, SocketTimeoutException -> 0x0138, JSONException -> 0x014f, NetworkUnavailableException -> 0x0160, HttpStatusException -> 0x017d, IOException -> 0x019e, Exception -> 0x01b3 }
                    if (r18 != 0) goto L_0x00a1
                L_0x0099:
                    r18 = 1
                    r6 = r18
                    r18 = r4
                    r5 = r18
                L_0x00a1:
                    r18 = r14
                    r0 = r18
                    long r0 = r0.b     // Catch:{ ConnectTimeoutException -> 0x0121, SocketTimeoutException -> 0x0138, JSONException -> 0x014f, NetworkUnavailableException -> 0x0160, HttpStatusException -> 0x017d, IOException -> 0x019e, Exception -> 0x01b3 }
                    r18 = r0
                    r9 = r18
                    r18 = r14
                    r0 = r18
                    long r0 = r0.c     // Catch:{ ConnectTimeoutException -> 0x0121, SocketTimeoutException -> 0x0138, JSONException -> 0x014f, NetworkUnavailableException -> 0x0160, HttpStatusException -> 0x017d, IOException -> 0x019e, Exception -> 0x01b3 }
                    r18 = r0
                    r11 = r18
                L_0x00b5:
                    r18 = r5
                    r19 = r4
                    r0 = r18
                    r1 = r19
                    if (r0 < r1) goto L_0x0059
                L_0x00bf:
                    r18 = r2
                    r0 = r18
                    com.tencent.open.b.g r0 = r0.a     // Catch:{ Exception -> 0x01e1 }
                    r18 = r0
                    java.lang.String r19 = "mapp_apptrace_sdk"
                    r20 = r7
                    r22 = r9
                    r24 = r11
                    r26 = r13
                    r27 = 0
                    r28 = 0
                    r18.a(r19, r20, r22, r24, r26, r27, r28)     // Catch:{ Exception -> 0x01e1 }
                    r18 = r6
                    if (r18 == 0) goto L_0x01c8
                    com.tencent.open.b.f r18 = com.tencent.open.b.f.a()     // Catch:{ Exception -> 0x01e1 }
                    java.lang.String r19 = "report_via"
                    r18.b(r19)     // Catch:{ Exception -> 0x01e1 }
                L_0x00e5:
                    r18 = r2
                    r0 = r18
                    com.tencent.open.b.g r0 = r0.a     // Catch:{ Exception -> 0x01e1 }
                    r18 = r0
                    r0 = r18
                    java.util.List<java.io.Serializable> r0 = r0.d     // Catch:{ Exception -> 0x01e1 }
                    r18 = r0
                    r18.clear()     // Catch:{ Exception -> 0x01e1 }
                    java.lang.String r18 = "openSDK_LOG.ReportManager"
                    java.lang.StringBuilder r19 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01e1 }
                    r29 = r19
                    r19 = r29
                    r20 = r29
                    r20.<init>()     // Catch:{ Exception -> 0x01e1 }
                    java.lang.String r20 = "-->doReportVia, uploadSuccess: "
                    java.lang.StringBuilder r19 = r19.append(r20)     // Catch:{ Exception -> 0x01e1 }
                    r20 = r6
                    java.lang.StringBuilder r19 = r19.append(r20)     // Catch:{ Exception -> 0x01e1 }
                    java.lang.String r19 = r19.toString()     // Catch:{ Exception -> 0x01e1 }
                    com.tencent.open.a.f.b(r18, r19)     // Catch:{ Exception -> 0x01e1 }
                L_0x0116:
                    goto L_0x0014
                L_0x0118:
                    r18 = move-exception
                    r17 = r18
                    r18 = -4
                    r16 = r18
                    goto L_0x0087
                L_0x0121:
                    r18 = move-exception
                    r14 = r18
                    long r18 = android.os.SystemClock.elapsedRealtime()     // Catch:{ Exception -> 0x01e1 }
                    r7 = r18
                    r18 = 0
                    r9 = r18
                    r18 = 0
                    r11 = r18
                    r18 = -7
                    r13 = r18
                    goto L_0x00b5
                L_0x0138:
                    r18 = move-exception
                    r14 = r18
                    long r18 = android.os.SystemClock.elapsedRealtime()     // Catch:{ Exception -> 0x01e1 }
                    r7 = r18
                    r18 = 0
                    r9 = r18
                    r18 = 0
                    r11 = r18
                    r18 = -8
                    r13 = r18
                    goto L_0x00b5
                L_0x014f:
                    r18 = move-exception
                    r14 = r18
                    r18 = 0
                    r9 = r18
                    r18 = 0
                    r11 = r18
                    r18 = -4
                    r13 = r18
                    goto L_0x00b5
                L_0x0160:
                    r18 = move-exception
                    r14 = r18
                    r18 = r2
                    r0 = r18
                    com.tencent.open.b.g r0 = r0.a     // Catch:{ Exception -> 0x01e1 }
                    r18 = r0
                    r0 = r18
                    java.util.List<java.io.Serializable> r0 = r0.d     // Catch:{ Exception -> 0x01e1 }
                    r18 = r0
                    r18.clear()     // Catch:{ Exception -> 0x01e1 }
                    java.lang.String r18 = "openSDK_LOG.ReportManager"
                    java.lang.String r19 = "doReportVia, NetworkUnavailableException."
                    com.tencent.open.a.f.b(r18, r19)     // Catch:{ Exception -> 0x01e1 }
                    goto L_0x0014
                L_0x017d:
                    r18 = move-exception
                    r14 = r18
                    r18 = r14
                    java.lang.String r18 = r18.getMessage()     // Catch:{ Exception -> 0x019a }
                    java.lang.String r19 = "http status code error:"
                    java.lang.String r20 = ""
                    java.lang.String r18 = r18.replace(r19, r20)     // Catch:{ Exception -> 0x019a }
                    r15 = r18
                    r18 = r15
                    int r18 = java.lang.Integer.parseInt(r18)     // Catch:{ Exception -> 0x019a }
                    r13 = r18
                L_0x0198:
                    goto L_0x00bf
                L_0x019a:
                    r18 = move-exception
                    r15 = r18
                    goto L_0x0198
                L_0x019e:
                    r18 = move-exception
                    r14 = r18
                    r18 = 0
                    r9 = r18
                    r18 = 0
                    r11 = r18
                    r18 = r14
                    int r18 = com.tencent.open.utils.HttpUtils.getErrorCodeFromException(r18)     // Catch:{ Exception -> 0x01e1 }
                    r13 = r18
                    goto L_0x00b5
                L_0x01b3:
                    r18 = move-exception
                    r14 = r18
                    r18 = 0
                    r9 = r18
                    r18 = 0
                    r11 = r18
                    r18 = -6
                    r13 = r18
                    r18 = r4
                    r5 = r18
                    goto L_0x00b5
                L_0x01c8:
                    com.tencent.open.b.f r18 = com.tencent.open.b.f.a()     // Catch:{ Exception -> 0x01e1 }
                    java.lang.String r19 = "report_via"
                    r20 = r2
                    r0 = r20
                    com.tencent.open.b.g r0 = r0.a     // Catch:{ Exception -> 0x01e1 }
                    r20 = r0
                    r0 = r20
                    java.util.List<java.io.Serializable> r0 = r0.d     // Catch:{ Exception -> 0x01e1 }
                    r20 = r0
                    r18.a(r19, r20)     // Catch:{ Exception -> 0x01e1 }
                    goto L_0x00e5
                L_0x01e1:
                    r18 = move-exception
                    r3 = r18
                    java.lang.String r18 = "openSDK_LOG.ReportManager"
                    java.lang.String r19 = "-->doReportVia, exception in serial executor."
                    r20 = r3
                    com.tencent.open.a.f.b(r18, r19, r20)
                    goto L_0x0116
                */
                throw new UnsupportedOperationException("Method not decompiled: com.tencent.open.b.g.AnonymousClass5.run():void");
            }
        };
        this.g.execute(runnable);
    }
}
