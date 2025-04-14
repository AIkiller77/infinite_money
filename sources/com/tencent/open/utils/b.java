package com.tencent.open.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import com.tencent.open.a.f;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/* compiled from: ProGuard */
public class b {
    /* access modifiers changed from: private */
    public static String c;
    /* access modifiers changed from: private */
    public String a;
    /* access modifiers changed from: private */
    public c b;
    /* access modifiers changed from: private */
    public long d;
    /* access modifiers changed from: private */
    public Handler e;
    private Runnable f;

    public b(Activity activity) {
        Runnable runnable;
        Handler handler;
        new Runnable(this) {
            final /* synthetic */ b a;

            {
                this.a = r5;
            }

            public void run() {
                StringBuilder sb;
                StringBuilder sb2;
                File file;
                StringBuilder sb3;
                StringBuilder sb4;
                f.a("AsynLoadImg", "saveFileRunnable:");
                String f = k.f(this.a.a);
                new StringBuilder();
                String sb5 = sb.append("share_qq_").append(f).append(".jpg").toString();
                new StringBuilder();
                String sb6 = sb2.append(b.c).append(sb5).toString();
                new File(sb6);
                File file2 = file;
                Message obtainMessage = this.a.e.obtainMessage();
                if (file2.exists()) {
                    obtainMessage.arg1 = 0;
                    obtainMessage.obj = sb6;
                    new StringBuilder();
                    f.a("AsynLoadImg", sb4.append("file exists: time:").append(System.currentTimeMillis() - this.a.d).toString());
                } else {
                    boolean z = false;
                    Bitmap a2 = b.a(this.a.a);
                    if (a2 != null) {
                        z = this.a.a(a2, sb5);
                    } else {
                        f.a("AsynLoadImg", "saveFileRunnable:get bmp fail---");
                    }
                    if (z) {
                        obtainMessage.arg1 = 0;
                        obtainMessage.obj = sb6;
                    } else {
                        obtainMessage.arg1 = 1;
                    }
                    new StringBuilder();
                    f.a("AsynLoadImg", sb3.append("file not exists: download time:").append(System.currentTimeMillis() - this.a.d).toString());
                }
                boolean sendMessage = this.a.e.sendMessage(obtainMessage);
            }
        };
        this.f = runnable;
        new Handler(this, activity.getMainLooper()) {
            final /* synthetic */ b a;

            {
                this.a = r6;
            }

            public void handleMessage(Message message) {
                StringBuilder sb;
                Message message2 = message;
                new StringBuilder();
                f.a("AsynLoadImg", sb.append("handleMessage:").append(message2.arg1).toString());
                if (message2.arg1 == 0) {
                    this.a.b.a(message2.arg1, (String) message2.obj);
                } else {
                    this.a.b.a(message2.arg1, (String) null);
                }
            }
        };
        this.e = handler;
    }

    public static Bitmap a(String str) {
        StringBuilder sb;
        URL url;
        StringBuilder sb2;
        String str2 = str;
        new StringBuilder();
        f.a("AsynLoadImg", sb.append("getbitmap:").append(str2).toString());
        try {
            new URL(str2);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();
            InputStream inputStream = httpURLConnection.getInputStream();
            Bitmap decodeStream = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
            new StringBuilder();
            f.a("AsynLoadImg", sb2.append("image download finished.").append(str2).toString());
            return decodeStream;
        } catch (OutOfMemoryError e2) {
            e2.printStackTrace();
            f.a("AsynLoadImg", "getbitmap bmp fail---");
            return null;
        } catch (IOException e3) {
            e3.printStackTrace();
            f.a("AsynLoadImg", "getbitmap bmp fail---");
            return null;
        }
    }

    public void a(String str, c cVar) {
        StringBuilder sb;
        Thread thread;
        String str2 = str;
        c cVar2 = cVar;
        f.a("AsynLoadImg", "--save---");
        if (str2 == null || str2.equals("")) {
            cVar2.a(1, (String) null);
        } else if (!k.b()) {
            cVar2.a(2, (String) null);
        } else {
            new StringBuilder();
            c = sb.append(Environment.getExternalStorageDirectory()).append("/tmp/").toString();
            this.d = System.currentTimeMillis();
            this.a = str2;
            this.b = cVar2;
            new Thread(this.f);
            thread.start();
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v3, resolved type: java.io.OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v35, resolved type: java.io.BufferedOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v38, resolved type: java.io.BufferedOutputStream} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(android.graphics.Bitmap r17, java.lang.String r18) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r2 = r18
            java.lang.String r10 = c
            r3 = r10
            r10 = 0
            r4 = r10
            java.io.File r10 = new java.io.File     // Catch:{ IOException -> 0x0091 }
            r15 = r10
            r10 = r15
            r11 = r15
            r12 = r3
            r11.<init>(r12)     // Catch:{ IOException -> 0x0091 }
            r5 = r10
            r10 = r5
            boolean r10 = r10.exists()     // Catch:{ IOException -> 0x0091 }
            if (r10 != 0) goto L_0x0021
            r10 = r5
            boolean r10 = r10.mkdir()     // Catch:{ IOException -> 0x0091 }
        L_0x0021:
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0091 }
            r15 = r10
            r10 = r15
            r11 = r15
            r11.<init>()     // Catch:{ IOException -> 0x0091 }
            r11 = r3
            java.lang.StringBuilder r10 = r10.append(r11)     // Catch:{ IOException -> 0x0091 }
            r11 = r2
            java.lang.StringBuilder r10 = r10.append(r11)     // Catch:{ IOException -> 0x0091 }
            java.lang.String r10 = r10.toString()     // Catch:{ IOException -> 0x0091 }
            r6 = r10
            java.lang.String r10 = "AsynLoadImg"
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0091 }
            r15 = r11
            r11 = r15
            r12 = r15
            r12.<init>()     // Catch:{ IOException -> 0x0091 }
            java.lang.String r12 = "saveFile:"
            java.lang.StringBuilder r11 = r11.append(r12)     // Catch:{ IOException -> 0x0091 }
            r12 = r2
            java.lang.StringBuilder r11 = r11.append(r12)     // Catch:{ IOException -> 0x0091 }
            java.lang.String r11 = r11.toString()     // Catch:{ IOException -> 0x0091 }
            com.tencent.open.a.f.a(r10, r11)     // Catch:{ IOException -> 0x0091 }
            java.io.File r10 = new java.io.File     // Catch:{ IOException -> 0x0091 }
            r15 = r10
            r10 = r15
            r11 = r15
            r12 = r6
            r11.<init>(r12)     // Catch:{ IOException -> 0x0091 }
            r7 = r10
            java.io.BufferedOutputStream r10 = new java.io.BufferedOutputStream     // Catch:{ IOException -> 0x0091 }
            r15 = r10
            r10 = r15
            r11 = r15
            java.io.FileOutputStream r12 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0091 }
            r15 = r12
            r12 = r15
            r13 = r15
            r14 = r7
            r13.<init>(r14)     // Catch:{ IOException -> 0x0091 }
            r11.<init>(r12)     // Catch:{ IOException -> 0x0091 }
            r4 = r10
            r10 = r1
            android.graphics.Bitmap$CompressFormat r11 = android.graphics.Bitmap.CompressFormat.JPEG     // Catch:{ IOException -> 0x0091 }
            r12 = 80
            r13 = r4
            boolean r10 = r10.compress(r11, r12, r13)     // Catch:{ IOException -> 0x0091 }
            r10 = r4
            r10.flush()     // Catch:{ IOException -> 0x0091 }
            r10 = r4
            if (r10 == 0) goto L_0x0087
            r10 = r4
            r10.close()     // Catch:{ IOException -> 0x008a }
        L_0x0085:
            r10 = 0
            r4 = r10
        L_0x0087:
            r10 = 1
            r0 = r10
        L_0x0089:
            return r0
        L_0x008a:
            r10 = move-exception
            r5 = r10
            r10 = r5
            r10.printStackTrace()
            goto L_0x0085
        L_0x0091:
            r10 = move-exception
            r5 = r10
            r10 = r5
            r10.printStackTrace()     // Catch:{ all -> 0x00b4 }
            java.lang.String r10 = "AsynLoadImg"
            java.lang.String r11 = "saveFile bmp fail---"
            r12 = r5
            com.tencent.open.a.f.b(r10, r11, r12)     // Catch:{ all -> 0x00b4 }
            r10 = 0
            r6 = r10
            r10 = r4
            if (r10 == 0) goto L_0x00aa
            r10 = r4
            r10.close()     // Catch:{ IOException -> 0x00ad }
        L_0x00a8:
            r10 = 0
            r4 = r10
        L_0x00aa:
            r10 = r6
            r0 = r10
            goto L_0x0089
        L_0x00ad:
            r10 = move-exception
            r7 = r10
            r10 = r7
            r10.printStackTrace()
            goto L_0x00a8
        L_0x00b4:
            r10 = move-exception
            r8 = r10
            r10 = r4
            if (r10 == 0) goto L_0x00bf
            r10 = r4
            r10.close()     // Catch:{ IOException -> 0x00c1 }
        L_0x00bd:
            r10 = 0
            r4 = r10
        L_0x00bf:
            r10 = r8
            throw r10
        L_0x00c1:
            r10 = move-exception
            r9 = r10
            r10 = r9
            r10.printStackTrace()
            goto L_0x00bd
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.open.utils.b.a(android.graphics.Bitmap, java.lang.String):boolean");
    }
}
