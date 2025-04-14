package com.tencent.connect.share;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.tencent.open.a.f;
import com.tencent.open.utils.c;
import com.tencent.open.utils.k;
import com.tencent.qq.widget.R;
import java.util.ArrayList;

/* compiled from: ProGuard */
public class a {
    public static final int a(BitmapFactory.Options options, int i, int i2) {
        int i3;
        int b = b(options, i, i2);
        if (b <= 8) {
            int i4 = 1;
            while (true) {
                i3 = i4;
                if (i3 >= b) {
                    break;
                }
                i4 = i3 << 1;
            }
        } else {
            i3 = ((b + 7) / 8) * 8;
        }
        return i3;
    }

    private static Bitmap a(Bitmap bitmap, int i) {
        Matrix matrix;
        Bitmap bitmap2 = bitmap;
        int i2 = i;
        new Matrix();
        Matrix matrix2 = matrix;
        int width = bitmap2.getWidth();
        int height = bitmap2.getHeight();
        float f = ((float) i2) / ((float) (width > height ? width : height));
        boolean postScale = matrix2.postScale(f, f);
        return Bitmap.createBitmap(bitmap2, 0, 0, bitmap2.getWidth(), bitmap2.getHeight(), matrix2, true);
    }

    public static final Bitmap a(String str, int i) {
        BitmapFactory.Options options;
        String str2 = str;
        int i2 = i;
        if (TextUtils.isEmpty(str2)) {
            return null;
        }
        new BitmapFactory.Options();
        BitmapFactory.Options options2 = options;
        options2.inJustDecodeBounds = true;
        try {
            Bitmap decodeFile = BitmapFactory.decodeFile(str2, options2);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
        int i3 = options2.outWidth;
        int i4 = options2.outHeight;
        if (options2.mCancel || options2.outWidth == -1 || options2.outHeight == -1) {
            return null;
        }
        int i5 = i3 > i4 ? i3 : i4;
        options2.inPreferredConfig = Bitmap.Config.RGB_565;
        if (i5 > i2) {
            options2.inSampleSize = a(options2, -1, i2 * i2);
        }
        options2.inJustDecodeBounds = false;
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeFile(str2, options2);
        } catch (OutOfMemoryError e2) {
            e2.printStackTrace();
        }
        if (bitmap == null) {
            return null;
        }
        int i6 = options2.outWidth;
        int i7 = options2.outHeight;
        return (i6 > i7 ? i6 : i7) > i2 ? a(bitmap, i2) : bitmap;
    }

    /* JADX WARNING: type inference failed for: r7v23, types: [java.io.FileOutputStream] */
    /* JADX WARNING: type inference failed for: r7v24, types: [java.io.FileOutputStream] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected static final java.lang.String a(android.graphics.Bitmap r12, java.lang.String r13, java.lang.String r14) {
        /*
            r0 = r12
            r1 = r13
            r2 = r14
            java.io.File r7 = new java.io.File
            r11 = r7
            r7 = r11
            r8 = r11
            r9 = r1
            r8.<init>(r9)
            r3 = r7
            r7 = r3
            boolean r7 = r7.exists()
            if (r7 != 0) goto L_0x0019
            r7 = r3
            boolean r7 = r7.mkdirs()
        L_0x0019:
            java.lang.StringBuffer r7 = new java.lang.StringBuffer
            r11 = r7
            r7 = r11
            r8 = r11
            r9 = r1
            r8.<init>(r9)
            r8 = r2
            java.lang.StringBuffer r7 = r7.append(r8)
            java.lang.String r7 = r7.toString()
            r4 = r7
            java.io.File r7 = new java.io.File
            r11 = r7
            r7 = r11
            r8 = r11
            r9 = r4
            r8.<init>(r9)
            r3 = r7
            r7 = r3
            boolean r7 = r7.exists()
            if (r7 == 0) goto L_0x0042
            r7 = r3
            boolean r7 = r7.delete()
        L_0x0042:
            r7 = r0
            if (r7 == 0) goto L_0x0072
            r7 = 0
            r5 = r7
            java.io.FileOutputStream r7 = new java.io.FileOutputStream     // Catch:{ FileNotFoundException -> 0x006c, IOException -> 0x0075 }
            r11 = r7
            r7 = r11
            r8 = r11
            r9 = r3
            r8.<init>(r9)     // Catch:{ FileNotFoundException -> 0x006c, IOException -> 0x0075 }
            r5 = r7
            r7 = r0
            android.graphics.Bitmap$CompressFormat r8 = android.graphics.Bitmap.CompressFormat.JPEG     // Catch:{ FileNotFoundException -> 0x006c, IOException -> 0x0075 }
            r9 = 80
            r10 = r5
            boolean r7 = r7.compress(r8, r9, r10)     // Catch:{ FileNotFoundException -> 0x006c, IOException -> 0x0075 }
            r7 = r5
            r7.flush()     // Catch:{ FileNotFoundException -> 0x006c, IOException -> 0x0075 }
            r7 = r5
            r7.close()     // Catch:{ FileNotFoundException -> 0x006c, IOException -> 0x0075 }
            r7 = r0
            r7.recycle()     // Catch:{ FileNotFoundException -> 0x006c, IOException -> 0x0075 }
            r7 = 0
            r0 = r7
            r7 = r4
            r0 = r7
        L_0x006b:
            return r0
        L_0x006c:
            r7 = move-exception
            r6 = r7
            r7 = r6
            r7.printStackTrace()
        L_0x0072:
            r7 = 0
            r0 = r7
            goto L_0x006b
        L_0x0075:
            r7 = move-exception
            r6 = r7
            r7 = r6
            r7.printStackTrace()
            goto L_0x0072
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.connect.share.a.a(android.graphics.Bitmap, java.lang.String, java.lang.String):java.lang.String");
    }

    public static final void a(Context context, String str, c cVar) {
        Handler handler;
        Thread thread;
        Runnable runnable;
        Context context2 = context;
        String str2 = str;
        c cVar2 = cVar;
        f.b("openSDK_LOG.AsynScaleCompressImage", "scaleCompressImage");
        if (TextUtils.isEmpty(str2)) {
            cVar2.a(1, (String) null);
        } else if (!k.b()) {
            cVar2.a(2, (String) null);
        } else {
            final c cVar3 = cVar2;
            new Handler(context2.getMainLooper()) {
                public void handleMessage(Message message) {
                    Message message2 = message;
                    switch (message2.what) {
                        case R.styleable.AppCompatTheme_autoCompleteTextViewStyle /*101*/:
                            cVar3.a(0, (String) message2.obj);
                            return;
                        case R.styleable.AppCompatTheme_buttonStyle /*102*/:
                            cVar3.a(message2.arg1, (String) null);
                            return;
                        default:
                            super.handleMessage(message2);
                            return;
                    }
                }
            };
            final String str3 = str2;
            final Handler handler2 = handler;
            new Runnable() {
                public void run() {
                    StringBuilder sb;
                    StringBuilder sb2;
                    String a2;
                    StringBuilder sb3;
                    Bitmap a3 = a.a(str3, 140);
                    if (a3 != null) {
                        new StringBuilder();
                        String sb4 = sb.append(Environment.getExternalStorageDirectory()).append("/tmp/").toString();
                        String f = k.f(str3);
                        new StringBuilder();
                        String sb5 = sb2.append("share2qq_temp").append(f).append(".jpg").toString();
                        if (!a.b(str3, 140, 140)) {
                            f.b("openSDK_LOG.AsynScaleCompressImage", "not out of bound,not compress!");
                            a2 = str3;
                        } else {
                            f.b("openSDK_LOG.AsynScaleCompressImage", "out of bound,compress!");
                            a2 = a.a(a3, sb4, sb5);
                        }
                        new StringBuilder();
                        f.b("openSDK_LOG.AsynScaleCompressImage", sb3.append("-->destFilePath: ").append(a2).toString());
                        if (a2 != null) {
                            Message obtainMessage = handler2.obtainMessage(R.styleable.AppCompatTheme_autoCompleteTextViewStyle);
                            obtainMessage.obj = a2;
                            boolean sendMessage = handler2.sendMessage(obtainMessage);
                            return;
                        }
                    }
                    Message obtainMessage2 = handler2.obtainMessage(R.styleable.AppCompatTheme_buttonStyle);
                    obtainMessage2.arg1 = 3;
                    boolean sendMessage2 = handler2.sendMessage(obtainMessage2);
                }
            };
            new Thread(runnable);
            thread.start();
        }
    }

    public static final void a(Context context, ArrayList<String> arrayList, c cVar) {
        Handler handler;
        Thread thread;
        Runnable runnable;
        Context context2 = context;
        ArrayList<String> arrayList2 = arrayList;
        c cVar2 = cVar;
        f.b("openSDK_LOG.AsynScaleCompressImage", "batchScaleCompressImage");
        if (arrayList2 == null) {
            cVar2.a(1, (String) null);
            return;
        }
        final c cVar3 = cVar2;
        new Handler(context2.getMainLooper()) {
            public void handleMessage(Message message) {
                Message message2 = message;
                switch (message2.what) {
                    case R.styleable.AppCompatTheme_autoCompleteTextViewStyle /*101*/:
                        cVar3.a(0, message2.getData().getStringArrayList("images"));
                        return;
                    default:
                        super.handleMessage(message2);
                        return;
                }
            }
        };
        final ArrayList<String> arrayList3 = arrayList2;
        final Handler handler2 = handler;
        new Runnable() {
            public void run() {
                Bundle bundle;
                Bitmap a2;
                StringBuilder sb;
                StringBuilder sb2;
                String a3;
                for (int i = 0; i < arrayList3.size(); i++) {
                    String str = (String) arrayList3.get(i);
                    if (!k.g(str) && k.h(str) && (a2 = a.a(str, 10000)) != null) {
                        new StringBuilder();
                        String sb3 = sb.append(Environment.getExternalStorageDirectory()).append("/tmp/").toString();
                        String f = k.f(str);
                        new StringBuilder();
                        String sb4 = sb2.append("share2qzone_temp").append(f).append(".jpg").toString();
                        if (!a.b(str, 640, 10000)) {
                            f.b("openSDK_LOG.AsynScaleCompressImage", "not out of bound,not compress!");
                            a3 = str;
                        } else {
                            f.b("openSDK_LOG.AsynScaleCompressImage", "out of bound, compress!");
                            a3 = a.a(a2, sb3, sb4);
                        }
                        if (a3 != null) {
                            Object obj = arrayList3.set(i, a3);
                        }
                    }
                }
                Message obtainMessage = handler2.obtainMessage(R.styleable.AppCompatTheme_autoCompleteTextViewStyle);
                new Bundle();
                Bundle bundle2 = bundle;
                bundle2.putStringArrayList("images", arrayList3);
                obtainMessage.setData(bundle2);
                boolean sendMessage = handler2.sendMessage(obtainMessage);
            }
        };
        new Thread(runnable);
        thread.start();
    }

    private static int b(BitmapFactory.Options options, int i, int i2) {
        BitmapFactory.Options options2 = options;
        int i3 = i;
        int i4 = i2;
        double d = (double) options2.outWidth;
        double d2 = (double) options2.outHeight;
        int ceil = i4 == -1 ? 1 : (int) Math.ceil(Math.sqrt((d * d2) / ((double) i4)));
        int min = i3 == -1 ? 128 : (int) Math.min(Math.floor(d / ((double) i3)), Math.floor(d2 / ((double) i3)));
        if (min < ceil) {
            return ceil;
        }
        if (i4 == -1 && i3 == -1) {
            return 1;
        }
        return i3 == -1 ? ceil : min;
    }

    /* access modifiers changed from: private */
    public static final boolean b(String str, int i, int i2) {
        BitmapFactory.Options options;
        StringBuilder sb;
        String str2 = str;
        int i3 = i;
        int i4 = i2;
        if (TextUtils.isEmpty(str2)) {
            return false;
        }
        new BitmapFactory.Options();
        BitmapFactory.Options options2 = options;
        options2.inJustDecodeBounds = true;
        try {
            Bitmap decodeFile = BitmapFactory.decodeFile(str2, options2);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
        int i5 = options2.outWidth;
        int i6 = options2.outHeight;
        if (options2.mCancel || options2.outWidth == -1 || options2.outHeight == -1) {
            return false;
        }
        int i7 = i5 > i6 ? i5 : i6;
        int i8 = i5 < i6 ? i5 : i6;
        new StringBuilder();
        f.b("openSDK_LOG.AsynScaleCompressImage", sb.append("longSide=").append(i7).append("shortSide=").append(i8).toString());
        options2.inPreferredConfig = Bitmap.Config.RGB_565;
        return i7 > i4 || i8 > i3;
    }
}
