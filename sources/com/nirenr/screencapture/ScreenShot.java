package com.nirenr.screencapture;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.display.VirtualDisplay;
import android.media.Image;
import android.media.ImageReader;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;
import com.a.a.a.a.a.a.a;
import com.androlua.LuaAccessibilityService;
import java.nio.ByteBuffer;

@TargetApi(21)
public class ScreenShot {
    /* access modifiers changed from: private */
    public static LuaAccessibilityService a = null;
    public static String appName = "";
    /* access modifiers changed from: private */
    public static ScreenCaptureListener b;
    private static Intent c;
    private static ScreenShot h;
    public static Bitmap mScreenCaptureBitmap;
    private final Context d;
    private final VirtualDisplay.Callback e;
    /* access modifiers changed from: private */
    public ScreenCaptureListener f;
    /* access modifiers changed from: private */
    public Image g;
    private MediaProjection i;
    private VirtualDisplay j;
    private ImageReader k;
    private int l;
    private int m;
    private int n;

    public class SaveTask extends AsyncTask<Image, Void, Bitmap> {
        public SaveTask() {
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Bitmap doInBackground(Image... imageArr) {
            if (imageArr == null || imageArr.length < 1 || imageArr[0] == null) {
                return null;
            }
            Image image = imageArr[0];
            int width = image.getWidth();
            int height = image.getHeight();
            Image.Plane[] planes = image.getPlanes();
            ByteBuffer buffer = planes[0].getBuffer();
            int pixelStride = planes[0].getPixelStride();
            Bitmap createBitmap = Bitmap.createBitmap(((planes[0].getRowStride() - (pixelStride * width)) / pixelStride) + width, height, Bitmap.Config.ARGB_8888);
            createBitmap.copyPixelsFromBuffer(buffer);
            Bitmap createBitmap2 = Bitmap.createBitmap(createBitmap, 0, 0, width, height);
            image.close();
            Image unused = ScreenShot.this.g = null;
            if (ScreenShot.this.f != null) {
                ScreenShot.this.f.onScreenCaptureDone(createBitmap2);
                ScreenCaptureListener unused2 = ScreenShot.this.f = null;
            }
            return null;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (bitmap != null) {
                ScreenShot.this.setScreenCaptureBitmap(bitmap);
                Log.e("ryze", "获取图片成功");
            }
        }
    }

    public ScreenShot(Context context, VirtualDisplay.Callback callback) {
        this.d = context;
        this.e = callback;
        c();
        if (c == null) {
            Intent intent = new Intent(this.d, ScreenCaptureActivity.class);
            intent.setFlags(268435456);
            this.d.startActivity(intent);
            return;
        }
        startVirtual();
    }

    private void c() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) this.d.getSystemService("window")).getDefaultDisplay().getRealMetrics(displayMetrics);
        this.n = displayMetrics.densityDpi;
        this.l = displayMetrics.widthPixels;
        this.m = displayMetrics.heightPixels;
        d();
    }

    private void d() {
        this.k = ImageReader.newInstance(this.l, this.m, 1, 1);
    }

    private MediaProjectionManager e() {
        return (MediaProjectionManager) this.d.getSystemService("media_projection");
    }

    private void f() {
        if (this.i == null) {
            setUpMediaProjection();
        }
        if (this.i != null && this.j == null) {
            try {
                this.j = this.i.createVirtualDisplay("screen-mirror", this.l, this.m, this.n, 16, this.k.getSurface(), this.e, (Handler) null);
            } catch (Exception e2) {
                a.a(e2);
            }
        }
    }

    /* access modifiers changed from: private */
    public void g() {
        if (this.g == null) {
            this.g = this.k.acquireLatestImage();
            if (this.g != null) {
                new SaveTask().execute(new Image[]{this.g});
            } else if (this.f != null) {
                this.f.onScreenCaptureDone((Bitmap) null);
                this.f = null;
            }
        }
    }

    public static void getResultData(LuaAccessibilityService luaAccessibilityService) {
        if (luaAccessibilityService != null && c == null) {
            Intent intent = new Intent(luaAccessibilityService, ScreenCaptureActivity.class);
            intent.setFlags(268435456);
            luaAccessibilityService.startActivity(intent);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:59:0x010a  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x010f  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x011b  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0120  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x0125  */
    /* JADX WARNING: Removed duplicated region for block: B:76:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void getScreenCaptureBitmap(com.androlua.LuaAccessibilityService r13, com.nirenr.screencapture.ScreenCaptureListener r14) {
        /*
            if (r13 != 0) goto L_0x0003
            return
        L_0x0003:
            a = r13
            b = r14
            r0 = 0
            android.content.Intent r1 = c     // Catch:{ Exception -> 0x00f5, all -> 0x00ef }
            if (r1 != 0) goto L_0x001f
            android.content.Intent r14 = new android.content.Intent     // Catch:{ Exception -> 0x00f5, all -> 0x00ef }
            java.lang.Class<com.nirenr.screencapture.ScreenCaptureActivity> r1 = com.nirenr.screencapture.ScreenCaptureActivity.class
            r14.<init>(r13, r1)     // Catch:{ Exception -> 0x00f5, all -> 0x00ef }
            r1 = 268435456(0x10000000, float:2.5243549E-29)
            r14.setFlags(r1)     // Catch:{ Exception -> 0x00f5, all -> 0x00ef }
            r13.startActivity(r14)     // Catch:{ Exception -> 0x00f5, all -> 0x00ef }
            r13 = r0
            r1 = r13
            goto L_0x00d0
        L_0x001f:
            java.lang.String r1 = "window"
            java.lang.Object r1 = r13.getSystemService(r1)     // Catch:{ Exception -> 0x00f5, all -> 0x00ef }
            android.view.WindowManager r1 = (android.view.WindowManager) r1     // Catch:{ Exception -> 0x00f5, all -> 0x00ef }
            android.util.DisplayMetrics r2 = new android.util.DisplayMetrics     // Catch:{ Exception -> 0x00f5, all -> 0x00ef }
            r2.<init>()     // Catch:{ Exception -> 0x00f5, all -> 0x00ef }
            if (r1 == 0) goto L_0x003c
            android.view.Display r1 = r1.getDefaultDisplay()     // Catch:{ Exception -> 0x00f5, all -> 0x00ef }
            r1.getRealMetrics(r2)     // Catch:{ Exception -> 0x00f5, all -> 0x00ef }
            int r1 = r2.densityDpi     // Catch:{ Exception -> 0x00f5, all -> 0x00ef }
            int r3 = r2.widthPixels     // Catch:{ Exception -> 0x00f5, all -> 0x00ef }
            int r2 = r2.heightPixels     // Catch:{ Exception -> 0x00f5, all -> 0x00ef }
            goto L_0x0048
        L_0x003c:
            int r2 = r13.getHeight()     // Catch:{ Exception -> 0x00f5, all -> 0x00ef }
            int r3 = r13.getWidth()     // Catch:{ Exception -> 0x00f5, all -> 0x00ef }
            int r1 = r13.getDensity()     // Catch:{ Exception -> 0x00f5, all -> 0x00ef }
        L_0x0048:
            r8 = r1
            r7 = r2
            r6 = r3
            r1 = 1
            android.media.ImageReader r1 = android.media.ImageReader.newInstance(r6, r7, r1, r1)     // Catch:{ Exception -> 0x00f5, all -> 0x00ef }
            java.lang.String r2 = "media_projection"
            java.lang.Object r13 = r13.getSystemService(r2)     // Catch:{ Exception -> 0x00ea, all -> 0x00e5 }
            android.media.projection.MediaProjectionManager r13 = (android.media.projection.MediaProjectionManager) r13     // Catch:{ Exception -> 0x00ea, all -> 0x00e5 }
            r2 = -1
            android.content.Intent r3 = c     // Catch:{ Exception -> 0x00ea, all -> 0x00e5 }
            android.media.projection.MediaProjection r13 = r13.getMediaProjection(r2, r3)     // Catch:{ Exception -> 0x00ea, all -> 0x00e5 }
            java.lang.String r5 = "screen-mirror"
            r9 = 16
            android.view.Surface r10 = r1.getSurface()     // Catch:{ Exception -> 0x00e2, all -> 0x00df }
            r11 = 0
            r12 = 0
            r4 = r13
            android.hardware.display.VirtualDisplay r2 = r4.createVirtualDisplay(r5, r6, r7, r8, r9, r10, r11, r12)     // Catch:{ Exception -> 0x00e2, all -> 0x00df }
            android.media.Image r3 = r1.acquireLatestImage()     // Catch:{ Exception -> 0x00dd }
            r4 = 0
            r5 = r3
            r3 = 0
        L_0x0075:
            r6 = 40
            if (r3 >= r6) goto L_0x008d
            r5 = 5
            java.lang.Thread.sleep(r5)     // Catch:{ InterruptedException -> 0x007f }
            goto L_0x0083
        L_0x007f:
            r5 = move-exception
            com.a.a.a.a.a.a.a.a(r5)     // Catch:{ Exception -> 0x00dd }
        L_0x0083:
            android.media.Image r5 = r1.acquireLatestImage()     // Catch:{ Exception -> 0x00dd }
            if (r5 == 0) goto L_0x008a
            goto L_0x008d
        L_0x008a:
            int r3 = r3 + 1
            goto L_0x0075
        L_0x008d:
            if (r5 != 0) goto L_0x0095
            java.lang.String r3 = "请重试"
            r14.onScreenCaptureError(r3)     // Catch:{ Exception -> 0x00dd }
            goto L_0x00cb
        L_0x0095:
            int r3 = r5.getWidth()     // Catch:{ Exception -> 0x00dd }
            int r6 = r5.getHeight()     // Catch:{ Exception -> 0x00dd }
            android.media.Image$Plane[] r7 = r5.getPlanes()     // Catch:{ Exception -> 0x00dd }
            r8 = r7[r4]     // Catch:{ Exception -> 0x00dd }
            java.nio.ByteBuffer r8 = r8.getBuffer()     // Catch:{ Exception -> 0x00dd }
            r9 = r7[r4]     // Catch:{ Exception -> 0x00dd }
            int r9 = r9.getPixelStride()     // Catch:{ Exception -> 0x00dd }
            r7 = r7[r4]     // Catch:{ Exception -> 0x00dd }
            int r7 = r7.getRowStride()     // Catch:{ Exception -> 0x00dd }
            int r10 = r9 * r3
            int r7 = r7 - r10
            int r7 = r7 / r9
            int r7 = r7 + r3
            android.graphics.Bitmap$Config r9 = android.graphics.Bitmap.Config.ARGB_4444     // Catch:{ Exception -> 0x00dd }
            android.graphics.Bitmap r7 = android.graphics.Bitmap.createBitmap(r7, r6, r9)     // Catch:{ Exception -> 0x00dd }
            r7.copyPixelsFromBuffer(r8)     // Catch:{ Exception -> 0x00dd }
            android.graphics.Bitmap r3 = android.graphics.Bitmap.createBitmap(r7, r4, r4, r3, r6)     // Catch:{ Exception -> 0x00dd }
            r5.close()     // Catch:{ Exception -> 0x00dd }
            r14.onScreenCaptureDone(r3)     // Catch:{ Exception -> 0x00dd }
        L_0x00cb:
            a = r0     // Catch:{ Exception -> 0x00dd }
            b = r0     // Catch:{ Exception -> 0x00dd }
            r0 = r2
        L_0x00d0:
            if (r0 == 0) goto L_0x00d5
            r0.release()
        L_0x00d5:
            if (r1 == 0) goto L_0x00da
            r1.close()
        L_0x00da:
            if (r13 == 0) goto L_0x0117
            goto L_0x0114
        L_0x00dd:
            r14 = move-exception
            goto L_0x00fa
        L_0x00df:
            r14 = move-exception
            r2 = r0
            goto L_0x0119
        L_0x00e2:
            r14 = move-exception
            r2 = r0
            goto L_0x00fa
        L_0x00e5:
            r13 = move-exception
            r14 = r13
            r13 = r0
            r2 = r13
            goto L_0x0119
        L_0x00ea:
            r13 = move-exception
            r14 = r13
            r13 = r0
            r2 = r13
            goto L_0x00fa
        L_0x00ef:
            r13 = move-exception
            r14 = r13
            r13 = r0
            r1 = r13
            r2 = r1
            goto L_0x0119
        L_0x00f5:
            r13 = move-exception
            r14 = r13
            r13 = r0
            r1 = r13
            r2 = r1
        L_0x00fa:
            com.a.a.a.a.a.a.a.a(r14)     // Catch:{ all -> 0x0118 }
            com.nirenr.screencapture.ScreenCaptureListener r14 = b     // Catch:{ all -> 0x0118 }
            java.lang.String r3 = "请重试"
            r14.onScreenCaptureError(r3)     // Catch:{ all -> 0x0118 }
            a = r0     // Catch:{ all -> 0x0118 }
            b = r0     // Catch:{ all -> 0x0118 }
            if (r2 == 0) goto L_0x010d
            r2.release()
        L_0x010d:
            if (r1 == 0) goto L_0x0112
            r1.close()
        L_0x0112:
            if (r13 == 0) goto L_0x0117
        L_0x0114:
            r13.stop()
        L_0x0117:
            return
        L_0x0118:
            r14 = move-exception
        L_0x0119:
            if (r2 == 0) goto L_0x011e
            r2.release()
        L_0x011e:
            if (r1 == 0) goto L_0x0123
            r1.close()
        L_0x0123:
            if (r13 == 0) goto L_0x0128
            r13.stop()
        L_0x0128:
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nirenr.screencapture.ScreenShot.getScreenCaptureBitmap(com.androlua.LuaAccessibilityService, com.nirenr.screencapture.ScreenCaptureListener):void");
    }

    private Bitmap h() {
        if (this.k == null) {
            return null;
        }
        this.g = this.k.acquireLatestImage();
        if (this.g == null) {
            return null;
        }
        int width = this.g.getWidth();
        int height = this.g.getHeight();
        Image.Plane[] planes = this.g.getPlanes();
        ByteBuffer buffer = planes[0].getBuffer();
        int pixelStride = planes[0].getPixelStride();
        Bitmap createBitmap = Bitmap.createBitmap(((planes[0].getRowStride() - (pixelStride * width)) / pixelStride) + width, height, Bitmap.Config.ARGB_8888);
        createBitmap.copyPixelsFromBuffer(buffer);
        Bitmap createBitmap2 = Bitmap.createBitmap(createBitmap, 0, 0, width, height);
        this.g.close();
        this.g = null;
        return createBitmap2;
    }

    private void i() {
        if (this.i != null) {
            this.i.stop();
            this.i = null;
        }
    }

    private void j() {
        if (this.j != null) {
            this.j.release();
            this.j = null;
        }
    }

    private void k() {
        if (this.k != null) {
            this.k.close();
        }
        this.k = null;
    }

    public static void setResultData(Intent intent) {
        if (intent == null) {
            if (a != null) {
                Toast.makeText(a, "未获得权限", 0).show();
            }
            if (b != null) {
                b.onScreenCaptureError("未获得权限");
                return;
            }
            return;
        }
        c = intent;
        if (a != null) {
            a.getHandler().postDelayed(new Runnable() {
                public void run() {
                    ScreenShot.getScreenCaptureBitmap(ScreenShot.a, ScreenShot.b);
                }
            }, 500);
        }
    }

    public Bitmap getScreenShot() {
        return h();
    }

    public void reSize() {
        j();
        k();
        c();
        startVirtual();
    }

    public void release() {
        j();
        i();
        k();
        h = null;
    }

    public void setScreenCaptureBitmap(Bitmap bitmap) {
        mScreenCaptureBitmap = bitmap;
    }

    public void setUpMediaProjection() {
        if (this.i == null) {
            if (c == null) {
                Intent intent = new Intent(this.d, ScreenCaptureActivity.class);
                intent.setFlags(268435456);
                this.d.startActivity(intent);
                return;
            }
            this.i = e().getMediaProjection(-1, c);
        }
    }

    public void startScreenShot() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                ScreenShot.this.startVirtual();
            }
        }, 5);
        handler.postDelayed(new Runnable() {
            public void run() {
                ScreenShot.this.g();
            }
        }, 100);
    }

    public void startScreenShot(ScreenCaptureListener screenCaptureListener) {
        if (this.f == null) {
            this.f = screenCaptureListener;
            startScreenShot();
        }
    }

    public void startVirtual() {
        if (this.i == null) {
            setUpMediaProjection();
        }
        f();
    }
}
