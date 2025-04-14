package com.androlua.util;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.GestureDescription;
import android.annotation.TargetApi;
import android.graphics.Path;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.ViewConfiguration;
import android.widget.IPhotoView;

public class GlobalActionAutomator {
    private AccessibilityService a;
    private ScreenMetrics b;
    private Handler c;

    @TargetApi(24)
    public GlobalActionAutomator(AccessibilityService accessibilityService, Handler handler) {
        this.a = accessibilityService;
        this.c = handler;
    }

    private Path a(int[][] iArr) {
        Path path = new Path();
        path.moveTo((float) b(iArr[0][0]), (float) c(iArr[0][1]));
        for (int i = 1; i < iArr.length; i++) {
            int[] iArr2 = iArr[i];
            path.lineTo((float) b(iArr2[0]), (float) c(iArr2[1]));
        }
        return path;
    }

    /* access modifiers changed from: private */
    public void a() {
        Looper myLooper = Looper.myLooper();
        if (myLooper != null) {
            myLooper.quit();
        }
    }

    @TargetApi(16)
    private boolean a(int i) {
        if (this.a == null) {
            return false;
        }
        return this.a.performGlobalAction(i);
    }

    private boolean a(GestureDescription gestureDescription) {
        final VolatileDispose volatileDispose = new VolatileDispose();
        Log.i("GlobalActionAutomator", "dispatchGesture");
        return this.a.dispatchGesture(gestureDescription, new AccessibilityService.GestureResultCallback() {
            public void onCancelled(GestureDescription gestureDescription) {
                Log.i("GlobalActionAutomator", "onCancelled");
                volatileDispose.setAndNotify(false);
            }

            public void onCompleted(GestureDescription gestureDescription) {
                Log.i("GlobalActionAutomator", "onCompleted");
                volatileDispose.setAndNotify(true);
            }
        }, this.c);
    }

    private int b(int i) {
        return this.b == null ? i : this.b.scaleX(i);
    }

    private void b() {
        if (Looper.myLooper() == null) {
            Looper.prepare();
        }
    }

    private boolean b(GestureDescription gestureDescription) {
        b();
        final VolatileBox volatileBox = new VolatileBox(false);
        this.a.dispatchGesture(gestureDescription, new AccessibilityService.GestureResultCallback() {
            public void onCancelled(GestureDescription gestureDescription) {
                volatileBox.set(false);
                GlobalActionAutomator.this.a();
            }

            public void onCompleted(GestureDescription gestureDescription) {
                volatileBox.set(true);
                GlobalActionAutomator.this.a();
            }
        }, new Handler(Looper.myLooper()));
        Looper.loop();
        return ((Boolean) volatileBox.get()).booleanValue();
    }

    private int c(int i) {
        return this.b == null ? i : this.b.scaleY(i);
    }

    public boolean back() {
        return a(1);
    }

    public boolean click(int i, int i2) {
        return press(i, i2, ViewConfiguration.getTapTimeout());
    }

    public boolean gesture(long j, long j2, Path path) {
        return gestures(new GestureDescription.StrokeDescription(path, j, j2));
    }

    public boolean gesture(long j, long j2, int[]... iArr) {
        return gestures(new GestureDescription.StrokeDescription(a(iArr), j, j2));
    }

    public void gestureAsync(long j, long j2, int[]... iArr) {
        gesturesAsync(new GestureDescription.StrokeDescription(a(iArr), j, j2));
    }

    public boolean gestures(GestureDescription.StrokeDescription... strokeDescriptionArr) {
        if (this.a == null) {
            return false;
        }
        GestureDescription.Builder builder = new GestureDescription.Builder();
        for (GestureDescription.StrokeDescription addStroke : strokeDescriptionArr) {
            builder.addStroke(addStroke);
        }
        return this.c == null ? b(builder.build()) : a(builder.build());
    }

    public void gesturesAsync(GestureDescription.StrokeDescription... strokeDescriptionArr) {
        if (this.a != null) {
            GestureDescription.Builder builder = new GestureDescription.Builder();
            for (GestureDescription.StrokeDescription addStroke : strokeDescriptionArr) {
                builder.addStroke(addStroke);
            }
            this.a.dispatchGesture(builder.build(), (AccessibilityService.GestureResultCallback) null, (Handler) null);
        }
    }

    public boolean home() {
        return a(2);
    }

    public boolean longClick(int i, int i2) {
        return gesture(0, (long) (ViewConfiguration.getLongPressTimeout() + IPhotoView.DEFAULT_ZOOM_DURATION), new int[]{i, i2});
    }

    public boolean notifications() {
        return a(4);
    }

    public boolean powerDialog() {
        return a(6);
    }

    public boolean press(int i, int i2, int i3) {
        return gesture(0, (long) i3, new int[]{i, i2});
    }

    public boolean quickSettings() {
        return a(5);
    }

    public boolean recents() {
        return a(3);
    }

    public void setScreenMetrics(ScreenMetrics screenMetrics) {
        this.b = screenMetrics;
    }

    public void setService(AccessibilityService accessibilityService) {
        this.a = accessibilityService;
    }

    public boolean splitScreen() {
        return a(7);
    }

    public boolean swipe(int i, int i2, int i3, int i4, int i5) {
        return gesture(0, (long) i5, new int[]{i, i2}, new int[]{i3, i4});
    }
}
