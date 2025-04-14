package android.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.os.Build;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GifView extends View {
    private int a;
    private Movie b;
    private long c;
    private int d;
    private float e;
    private float f;
    private float g;
    private int h;
    private int i;
    private volatile boolean j;
    private boolean k;
    private String l;

    public GifView(Context context) {
        this(context, (AttributeSet) null);
    }

    public GifView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public GifView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.k = true;
        a(context, attributeSet, i2);
    }

    @SuppressLint({"NewApi"})
    private void a() {
        if (!this.k) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 16) {
            postInvalidateOnAnimation();
        } else {
            invalidate();
        }
    }

    @SuppressLint({"NewApi"})
    private void a(Context context, AttributeSet attributeSet, int i2) {
        this.a = -1;
        this.j = false;
        if (this.a != -1) {
            this.b = Movie.decodeStream(getResources().openRawResource(this.a));
        }
    }

    private void a(Canvas canvas) {
        this.b.setTime(this.d);
        canvas.save(1);
        canvas.scale(this.g, this.g);
        this.b.draw(canvas, this.e / this.g, this.f / this.g);
        canvas.restore();
    }

    private void b() {
        long uptimeMillis = SystemClock.uptimeMillis();
        if (this.c == 0) {
            this.c = uptimeMillis;
        }
        int duration = this.b.duration();
        if (duration == 0) {
            duration = 1000;
        }
        this.d = (int) ((uptimeMillis - this.c) % ((long) duration));
    }

    public String getGifPath() {
        return this.l;
    }

    public int getGifResource() {
        return this.a;
    }

    public boolean isPaused() {
        return this.j;
    }

    public boolean isPlaying() {
        return !this.j;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (this.b == null) {
            return;
        }
        if (!this.j) {
            b();
            a(canvas);
            a();
            return;
        }
        a(canvas);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        this.e = ((float) (getWidth() - this.h)) / 2.0f;
        this.f = ((float) (getHeight() - this.i)) / 2.0f;
        this.k = getVisibility() == 0;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0018, code lost:
        r5 = android.view.View.MeasureSpec.getSize(r5);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onMeasure(int r5, int r6) {
        /*
            r4 = this;
            android.graphics.Movie r0 = r4.b
            if (r0 == 0) goto L_0x0054
            android.graphics.Movie r0 = r4.b
            int r0 = r0.width()
            android.graphics.Movie r1 = r4.b
            int r1 = r1.height()
            int r2 = android.view.View.MeasureSpec.getMode(r5)
            r3 = 1065353216(0x3f800000, float:1.0)
            if (r2 == 0) goto L_0x0023
            int r5 = android.view.View.MeasureSpec.getSize(r5)
            if (r0 <= r5) goto L_0x0023
            float r2 = (float) r0
            float r5 = (float) r5
            float r5 = r2 / r5
            goto L_0x0025
        L_0x0023:
            r5 = 1065353216(0x3f800000, float:1.0)
        L_0x0025:
            int r2 = android.view.View.MeasureSpec.getMode(r6)
            if (r2 == 0) goto L_0x0036
            int r6 = android.view.View.MeasureSpec.getSize(r6)
            if (r1 <= r6) goto L_0x0036
            float r2 = (float) r1
            float r6 = (float) r6
            float r6 = r2 / r6
            goto L_0x0038
        L_0x0036:
            r6 = 1065353216(0x3f800000, float:1.0)
        L_0x0038:
            float r5 = java.lang.Math.max(r5, r6)
            float r3 = r3 / r5
            r4.g = r3
            float r5 = (float) r0
            float r6 = r4.g
            float r5 = r5 * r6
            int r5 = (int) r5
            r4.h = r5
            float r5 = (float) r1
            float r6 = r4.g
            float r5 = r5 * r6
            int r5 = (int) r5
            r4.i = r5
            int r5 = r4.h
            int r6 = r4.i
            goto L_0x005c
        L_0x0054:
            int r5 = r4.getSuggestedMinimumWidth()
            int r6 = r4.getSuggestedMinimumHeight()
        L_0x005c:
            r4.setMeasuredDimension(r5, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.widget.GifView.onMeasure(int, int):void");
    }

    @SuppressLint({"NewApi"})
    public void onScreenStateChanged(int i2) {
        super.onScreenStateChanged(i2);
        boolean z = true;
        if (i2 != 1) {
            z = false;
        }
        this.k = z;
        a();
    }

    /* access modifiers changed from: protected */
    @SuppressLint({"NewApi"})
    public void onVisibilityChanged(View view, int i2) {
        super.onVisibilityChanged(view, i2);
        this.k = i2 == 0;
        a();
    }

    /* access modifiers changed from: protected */
    public void onWindowVisibilityChanged(int i2) {
        super.onWindowVisibilityChanged(i2);
        this.k = i2 == 0;
        a();
    }

    public void pause() {
        if (!this.j) {
            this.j = true;
            invalidate();
        }
    }

    public void play() {
        if (this.j) {
            this.j = false;
            this.c = SystemClock.uptimeMillis() - ((long) this.d);
            invalidate();
        }
    }

    public void setGifPath(String str) {
        this.l = str;
        try {
            this.b = Movie.decodeStream(new FileInputStream(str));
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        }
        requestLayout();
    }

    public void setGifResource(int i2) {
        this.a = i2;
        this.b = Movie.decodeStream(getResources().openRawResource(this.a));
        requestLayout();
    }
}
