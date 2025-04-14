package com.androlua;

import android.graphics.ColorFilter;
import android.graphics.Movie;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.a.a.a.a.a.a.a;
import com.androlua.GifDecoder;
import com.androlua.util.AsyncTaskX;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import net.lingala.zip4j.util.InternalZipConstants;

public class LuaBitmapDrawable extends Drawable implements LuaGcable, Runnable {
    public static final int CENTER = 5;
    public static final int CENTER_CROP = 6;
    public static final int CENTER_INSIDE = 7;
    public static final int FIT_CENTER = 3;
    public static final int FIT_END = 4;
    public static final int FIT_START = 2;
    public static final int FIT_XY = 1;
    public static final int MATRIX = 0;

    /* renamed from: q  reason: collision with root package name */
    private static long f23q = 604800000;
    private LuaContext a;
    private int b;
    private long c;
    private int d;
    private Movie e;
    /* access modifiers changed from: private */
    public LoadingDrawable f;
    private Drawable g;
    private NineBitmapDrawable h;
    private ColorFilter i;
    private int j;
    private int k;
    /* access modifiers changed from: private */
    public GifDecoder l;
    /* access modifiers changed from: private */
    public GifDecoder m;
    private GifDecoder.GifFrame n;
    private int o;
    private boolean p;

    public LuaBitmapDrawable(LuaContext luaContext, String str) {
        this.k = 1;
        this.a = luaContext;
        this.f = new LoadingDrawable(luaContext.getContext());
        if (str.toLowerCase().startsWith("http://") || str.toLowerCase().startsWith("https://")) {
            a(luaContext, str);
        } else {
            a(!str.startsWith(InternalZipConstants.ZIP_FILE_SEPARATOR) ? luaContext.getLuaPath(str) : str);
        }
    }

    public LuaBitmapDrawable(LuaContext luaContext, String str, Drawable drawable) {
        this(luaContext, str);
        this.g = drawable;
    }

    private void a(final LuaContext luaContext, final String str) {
        new AsyncTaskX<String, String, String>() {
            /* access modifiers changed from: protected */
            public String a(String... strArr) {
                try {
                    return LuaBitmapDrawable.getHttpBitmap(luaContext, str);
                } catch (Exception e) {
                    a.a(e);
                    return "";
                }
            }

            /* access modifiers changed from: protected */
            public void a(String str) {
                LuaBitmapDrawable.this.a(str);
            }
        }.execute((Params[]) new String[0]);
    }

    /* access modifiers changed from: private */
    public void a(final String str) {
        try {
            this.l = new GifDecoder((InputStream) new FileInputStream(str), (GifDecoder.GifAction) new GifDecoder.GifAction() {
                public void parseOk(boolean z, int i) {
                    if (!z && i < 0) {
                        LuaBitmapDrawable.this.b(str);
                    } else if (z && LuaBitmapDrawable.this.m == null && LuaBitmapDrawable.this.l.getFrameCount() > 1) {
                        GifDecoder unused = LuaBitmapDrawable.this.m = LuaBitmapDrawable.this.l;
                    }
                }
            });
            this.l.start();
        } catch (Exception e2) {
            a.a(e2);
            b(str);
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0036 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b(java.lang.String r5) {
        /*
            r4 = this;
            boolean r0 = r5.isEmpty()
            r1 = 1000(0x3e8, double:4.94E-321)
            if (r0 == 0) goto L_0x0019
            android.os.Handler r5 = new android.os.Handler
            r5.<init>()
            com.androlua.LuaBitmapDrawable$3 r0 = new com.androlua.LuaBitmapDrawable$3
            r0.<init>()
            r5.postDelayed(r0, r1)
            r4.invalidateSelf()
            return
        L_0x0019:
            android.graphics.Movie r0 = r4.e
            if (r0 == 0) goto L_0x002e
            android.graphics.Movie r5 = r4.e
            int r5 = r5.duration()
            r4.b = r5
            int r5 = r4.b
            if (r5 != 0) goto L_0x0048
            r5 = 1000(0x3e8, float:1.401E-42)
            r4.b = r5
            goto L_0x0048
        L_0x002e:
            com.androlua.NineBitmapDrawable r0 = new com.androlua.NineBitmapDrawable     // Catch:{ Exception -> 0x0036 }
            r0.<init>((java.lang.String) r5)     // Catch:{ Exception -> 0x0036 }
            r4.h = r0     // Catch:{ Exception -> 0x0036 }
            goto L_0x0048
        L_0x0036:
            android.graphics.drawable.BitmapDrawable r0 = new android.graphics.drawable.BitmapDrawable     // Catch:{ Exception -> 0x0044 }
            com.androlua.LuaContext r3 = r4.a     // Catch:{ Exception -> 0x0044 }
            android.graphics.Bitmap r5 = com.androlua.LuaBitmap.getLocalBitmap(r3, r5)     // Catch:{ Exception -> 0x0044 }
            r0.<init>(r5)     // Catch:{ Exception -> 0x0044 }
            r4.g = r0     // Catch:{ Exception -> 0x0044 }
            goto L_0x0048
        L_0x0044:
            r5 = move-exception
            com.a.a.a.a.a.a.a.a(r5)
        L_0x0048:
            android.graphics.Movie r5 = r4.e
            if (r5 != 0) goto L_0x0061
            android.graphics.drawable.Drawable r5 = r4.g
            if (r5 != 0) goto L_0x0061
            com.androlua.NineBitmapDrawable r5 = r4.h
            if (r5 != 0) goto L_0x0061
            android.os.Handler r5 = new android.os.Handler
            r5.<init>()
            com.androlua.LuaBitmapDrawable$4 r0 = new com.androlua.LuaBitmapDrawable$4
            r0.<init>()
            r5.postDelayed(r0, r1)
        L_0x0061:
            r4.invalidateSelf()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.androlua.LuaBitmapDrawable.b(java.lang.String):void");
    }

    public static long getCacheTime() {
        return f23q;
    }

    public static String getHttpBitmap(LuaContext luaContext, String str) {
        String str2 = luaContext.getLuaExtDir("cache") + InternalZipConstants.ZIP_FILE_SEPARATOR + str.hashCode();
        File file = new File(str2);
        if (file.exists() && System.currentTimeMillis() - file.lastModified() < f23q) {
            return str2;
        }
        new File(str2).delete();
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        httpURLConnection.setConnectTimeout(120000);
        httpURLConnection.setDoInput(true);
        httpURLConnection.connect();
        InputStream inputStream = httpURLConnection.getInputStream();
        FileOutputStream fileOutputStream = new FileOutputStream(str2);
        if (!LuaUtil.copyFile(inputStream, (OutputStream) fileOutputStream)) {
            fileOutputStream.close();
            inputStream.close();
            new File(str2).delete();
            throw new RuntimeException("LoadHttpBitmap Error.");
        }
        fileOutputStream.close();
        inputStream.close();
        return str2;
    }

    public static void setCacheTime(long j2) {
        f23q = j2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x00aa  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x00b1  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0152  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x015d  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x01dc  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x01e3  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void draw(android.graphics.Canvas r11) {
        /*
            r10 = this;
            int r0 = r10.j
            r11.drawColor(r0)
            com.androlua.GifDecoder r0 = r10.m
            r1 = 0
            r3 = 1
            if (r0 == 0) goto L_0x00d2
            long r4 = java.lang.System.currentTimeMillis()
            long r6 = r10.c
            int r0 = (r6 > r1 ? 1 : (r6 == r1 ? 0 : -1))
            if (r0 == 0) goto L_0x003e
            com.androlua.GifDecoder$GifFrame r0 = r10.n
            if (r0 != 0) goto L_0x001b
            goto L_0x003e
        L_0x001b:
            long r0 = r10.c
            long r6 = r4 - r0
            int r0 = r10.o
            long r0 = (long) r0
            int r2 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
            if (r2 <= 0) goto L_0x004e
            com.androlua.GifDecoder r0 = r10.m
            com.androlua.GifDecoder$GifFrame r0 = r0.next()
            r10.n = r0
            com.androlua.GifDecoder$GifFrame r0 = r10.n
            int r0 = r0.delay
            r10.o = r0
            long r0 = r10.c
            int r2 = r10.o
            long r6 = (long) r2
            long r8 = r0 + r6
            r10.c = r8
            goto L_0x001b
        L_0x003e:
            com.androlua.GifDecoder r0 = r10.m
            com.androlua.GifDecoder$GifFrame r0 = r0.next()
            r10.n = r0
            com.androlua.GifDecoder$GifFrame r0 = r10.n
            int r0 = r0.delay
            r10.o = r0
            r10.c = r4
        L_0x004e:
            com.androlua.GifDecoder$GifFrame r0 = r10.n
            if (r0 == 0) goto L_0x00ce
            android.graphics.Rect r0 = r10.getBounds()
            android.graphics.drawable.BitmapDrawable r1 = new android.graphics.drawable.BitmapDrawable
            com.androlua.GifDecoder$GifFrame r2 = r10.n
            android.graphics.Bitmap r2 = r2.image
            r1.<init>(r2)
            int r2 = r1.getIntrinsicWidth()
            int r4 = r1.getIntrinsicHeight()
            int r5 = r10.k
            if (r5 != r3) goto L_0x0082
            int r3 = r0.right
            int r5 = r0.left
            int r3 = r3 - r5
            float r3 = (float) r3
            float r2 = (float) r2
            float r3 = r3 / r2
            int r5 = r0.bottom
            int r6 = r0.top
            int r5 = r5 - r6
            float r5 = (float) r5
            float r4 = (float) r4
            float r5 = r5 / r4
            float r2 = r2 * r3
            int r2 = (int) r2
            float r4 = r4 * r5
        L_0x0080:
            int r4 = (int) r4
            goto L_0x00a0
        L_0x0082:
            int r3 = r10.k
            if (r3 == 0) goto L_0x00a0
            int r3 = r0.bottom
            int r5 = r0.top
            int r3 = r3 - r5
            float r3 = (float) r3
            float r4 = (float) r4
            float r3 = r3 / r4
            int r5 = r0.right
            int r6 = r0.left
            int r5 = r5 - r6
            float r5 = (float) r5
            float r2 = (float) r2
            float r5 = r5 / r2
            float r3 = java.lang.Math.min(r3, r5)
            float r2 = r2 * r3
            int r2 = (int) r2
            float r4 = r4 * r3
            goto L_0x0080
        L_0x00a0:
            int r3 = r0.left
            int r5 = r0.top
            int r6 = r10.k
            switch(r6) {
                case 3: goto L_0x00b1;
                case 4: goto L_0x00aa;
                default: goto L_0x00a9;
            }
        L_0x00a9:
            goto L_0x00c1
        L_0x00aa:
            int r5 = r0.bottom
            int r0 = r0.top
            int r5 = r5 - r0
            int r5 = r5 - r4
            goto L_0x00c1
        L_0x00b1:
            int r3 = r0.right
            int r5 = r0.left
            int r3 = r3 - r5
            int r3 = r3 - r2
            int r3 = r3 / 2
            int r5 = r0.bottom
            int r0 = r0.top
            int r5 = r5 - r0
            int r5 = r5 - r4
            int r5 = r5 / 2
        L_0x00c1:
            android.graphics.Rect r0 = new android.graphics.Rect
            int r2 = r2 + r3
            int r4 = r4 + r5
            r0.<init>(r3, r5, r2, r4)
            r1.setBounds(r0)
            r1.draw(r11)
        L_0x00ce:
            r10.invalidateSelf()
            return
        L_0x00d2:
            android.graphics.Movie r0 = r10.e
            if (r0 == 0) goto L_0x0185
            long r4 = java.lang.System.currentTimeMillis()
            long r6 = r10.c
            int r0 = (r6 > r1 ? 1 : (r6 == r1 ? 0 : -1))
            if (r0 != 0) goto L_0x00e2
            r10.c = r4
        L_0x00e2:
            long r0 = r10.c
            long r6 = r4 - r0
            int r0 = r10.b
            long r0 = (long) r0
            long r6 = r6 % r0
            int r0 = (int) r6
            r10.d = r0
            android.graphics.Movie r0 = r10.e
            int r1 = r10.d
            r0.setTime(r1)
            android.graphics.Rect r0 = r10.getBounds()
            r11.save()
            android.graphics.Movie r1 = r10.e
            int r1 = r1.width()
            android.graphics.Movie r2 = r10.e
            int r2 = r2.height()
            r4 = 1065353216(0x3f800000, float:1.0)
            int r5 = r10.k
            if (r5 != r3) goto L_0x0127
            int r3 = r0.right
            int r5 = r0.left
            int r3 = r3 - r5
            float r3 = (float) r3
            float r1 = (float) r1
            float r3 = r3 / r1
            int r5 = r0.bottom
            int r6 = r0.top
            int r5 = r5 - r6
            float r5 = (float) r5
            float r2 = (float) r2
            float r5 = r5 / r2
            r11.scale(r3, r5)
            float r1 = r1 * r3
            int r1 = (int) r1
            float r2 = r2 * r5
        L_0x0125:
            int r2 = (int) r2
            goto L_0x0148
        L_0x0127:
            int r3 = r10.k
            if (r3 == 0) goto L_0x0148
            int r3 = r0.bottom
            int r4 = r0.top
            int r3 = r3 - r4
            float r3 = (float) r3
            float r2 = (float) r2
            float r3 = r3 / r2
            int r4 = r0.right
            int r5 = r0.left
            int r4 = r4 - r5
            float r4 = (float) r4
            float r1 = (float) r1
            float r4 = r4 / r1
            float r4 = java.lang.Math.min(r3, r4)
            r11.scale(r4, r4)
            float r1 = r1 * r4
            int r1 = (int) r1
            float r2 = r2 * r4
            goto L_0x0125
        L_0x0148:
            int r3 = r0.left
            int r5 = r0.top
            int r6 = r10.k
            switch(r6) {
                case 3: goto L_0x015d;
                case 4: goto L_0x0152;
                default: goto L_0x0151;
            }
        L_0x0151:
            goto L_0x0174
        L_0x0152:
            int r1 = r0.bottom
            int r0 = r0.top
            int r1 = r1 - r0
            float r0 = (float) r1
            float r1 = (float) r2
            float r1 = r1 / r4
            float r0 = r0 - r1
            int r5 = (int) r0
            goto L_0x0174
        L_0x015d:
            int r3 = r0.right
            int r5 = r0.left
            int r3 = r3 - r5
            int r3 = r3 - r1
            float r1 = (float) r3
            float r1 = r1 / r4
            r3 = 1073741824(0x40000000, float:2.0)
            float r1 = r1 / r3
            int r1 = (int) r1
            int r5 = r0.bottom
            int r0 = r0.top
            int r5 = r5 - r0
            int r5 = r5 - r2
            float r0 = (float) r5
            float r0 = r0 / r4
            float r0 = r0 / r3
            int r5 = (int) r0
            r3 = r1
        L_0x0174:
            android.graphics.Movie r0 = r10.e
            float r1 = (float) r3
            float r2 = (float) r5
            android.graphics.Paint r3 = new android.graphics.Paint
            r3.<init>()
            r0.draw(r11, r1, r2, r3)
            r11.restore()
            goto L_0x00ce
        L_0x0185:
            android.graphics.drawable.Drawable r0 = r10.g
            if (r0 == 0) goto L_0x0205
            android.graphics.Rect r0 = r10.getBounds()
            android.graphics.drawable.Drawable r1 = r10.g
            int r1 = r1.getIntrinsicWidth()
            android.graphics.drawable.Drawable r2 = r10.g
            int r2 = r2.getIntrinsicHeight()
            int r4 = r10.k
            if (r4 != r3) goto L_0x01b4
            int r3 = r0.right
            int r4 = r0.left
            int r3 = r3 - r4
            float r3 = (float) r3
            float r1 = (float) r1
            float r3 = r3 / r1
            int r4 = r0.bottom
            int r5 = r0.top
            int r4 = r4 - r5
            float r4 = (float) r4
            float r2 = (float) r2
            float r4 = r4 / r2
            float r1 = r1 * r3
            int r1 = (int) r1
            float r2 = r2 * r4
        L_0x01b2:
            int r2 = (int) r2
            goto L_0x01d2
        L_0x01b4:
            int r3 = r10.k
            if (r3 == 0) goto L_0x01d2
            int r3 = r0.bottom
            int r4 = r0.top
            int r3 = r3 - r4
            float r3 = (float) r3
            float r2 = (float) r2
            float r3 = r3 / r2
            int r4 = r0.right
            int r5 = r0.left
            int r4 = r4 - r5
            float r4 = (float) r4
            float r1 = (float) r1
            float r4 = r4 / r1
            float r3 = java.lang.Math.min(r3, r4)
            float r1 = r1 * r3
            int r1 = (int) r1
            float r2 = r2 * r3
            goto L_0x01b2
        L_0x01d2:
            int r3 = r0.left
            int r4 = r0.top
            int r5 = r10.k
            switch(r5) {
                case 3: goto L_0x01e3;
                case 4: goto L_0x01dc;
                default: goto L_0x01db;
            }
        L_0x01db:
            goto L_0x01f3
        L_0x01dc:
            int r4 = r0.bottom
            int r0 = r0.top
            int r4 = r4 - r0
            int r4 = r4 - r2
            goto L_0x01f3
        L_0x01e3:
            int r3 = r0.right
            int r4 = r0.left
            int r3 = r3 - r4
            int r3 = r3 - r1
            int r3 = r3 / 2
            int r4 = r0.bottom
            int r0 = r0.top
            int r4 = r4 - r0
            int r4 = r4 - r2
            int r4 = r4 / 2
        L_0x01f3:
            android.graphics.drawable.Drawable r0 = r10.g
            android.graphics.Rect r5 = new android.graphics.Rect
            int r1 = r1 + r3
            int r2 = r2 + r4
            r5.<init>(r3, r4, r1, r2)
            r0.setBounds(r5)
            android.graphics.drawable.Drawable r0 = r10.g
            r0.draw(r11)
            return
        L_0x0205:
            com.androlua.NineBitmapDrawable r0 = r10.h
            if (r0 == 0) goto L_0x0218
            com.androlua.NineBitmapDrawable r0 = r10.h
            android.graphics.Rect r1 = r10.getBounds()
            r0.setBounds(r1)
            com.androlua.NineBitmapDrawable r0 = r10.h
            r0.draw(r11)
            return
        L_0x0218:
            com.androlua.LoadingDrawable r0 = r10.f
            if (r0 == 0) goto L_0x022c
            com.androlua.LoadingDrawable r0 = r10.f
            android.graphics.Rect r1 = r10.getBounds()
            r0.setBounds(r1)
            com.androlua.LoadingDrawable r0 = r10.f
            r0.draw(r11)
            goto L_0x00ce
        L_0x022c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.androlua.LuaBitmapDrawable.draw(android.graphics.Canvas):void");
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        if (this.m != null) {
            this.m.free();
        }
    }

    public void gc() {
        if (this.m != null) {
            this.m.free();
        }
        if (this.g != null && (this.g instanceof BitmapDrawable)) {
            ((BitmapDrawable) this.g).getBitmap().recycle();
        }
        if (this.h != null) {
            this.h.gc();
        }
        this.m = null;
        this.g = null;
        this.h = null;
        this.f.setState(-1);
        this.p = true;
    }

    public int getIntrinsicHeight() {
        if (this.e != null) {
            return this.e.height();
        }
        if (this.g != null) {
            this.g.getIntrinsicHeight();
        } else if (this.h != null) {
            this.h.getIntrinsicHeight();
        }
        return super.getIntrinsicHeight();
    }

    public int getIntrinsicWidth() {
        if (this.e != null) {
            return this.e.width();
        }
        if (this.g != null) {
            this.g.getIntrinsicWidth();
        } else if (this.h != null) {
            this.h.getIntrinsicWidth();
        }
        return super.getIntrinsicWidth();
    }

    public int getOpacity() {
        return 0;
    }

    public boolean isGc() {
        return this.p;
    }

    public void run() {
        invalidateSelf();
    }

    public void setAlpha(int i2) {
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.i = colorFilter;
    }

    public void setFillColor(int i2) {
        if (i2 != this.j) {
            this.j = i2;
        }
    }

    public void setScaleType(int i2) {
        if (this.k != i2) {
            this.k = i2;
            invalidateSelf();
        }
    }
}
