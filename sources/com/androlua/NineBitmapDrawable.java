package com.androlua;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import com.a.a.a.a.a.a.a;

public class NineBitmapDrawable extends Drawable implements LuaGcable {
    private Paint a;
    private Bitmap b;
    private int c;
    private int d;
    private int e;
    private int f;
    private Rect g;
    private Rect h;
    private Rect i;
    private Rect j;
    private Rect k;
    private Rect l;
    private Rect m;
    private Rect n;
    private Rect o;
    private boolean p;

    public NineBitmapDrawable(Bitmap bitmap) {
        int i2;
        int i3;
        int i4;
        int i5;
        this.a = new Paint();
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int i6 = 0;
        while (true) {
            if (i6 >= width) {
                i2 = 0;
                break;
            } else if (bitmap.getPixel(i6, 0) == -16777216) {
                i2 = i6;
                break;
            } else {
                i6++;
            }
        }
        if (i2 == 0 || i2 == width - 1) {
            throw new IllegalArgumentException("not found x1");
        }
        int i7 = i2;
        while (true) {
            if (i7 >= width) {
                i3 = 0;
                break;
            } else if (bitmap.getPixel(i7, 0) != -16777216) {
                i3 = width - i7;
                break;
            } else {
                i7++;
            }
        }
        if (i3 == 0 || i3 == 1) {
            throw new IllegalArgumentException("not found x2");
        }
        int i8 = 0;
        while (true) {
            if (i8 >= height) {
                i4 = 0;
                break;
            } else if (bitmap.getPixel(0, i8) == -16777216) {
                i4 = i8;
                break;
            } else {
                i8++;
            }
        }
        if (i4 == 0 || i4 == height - 1) {
            throw new IllegalArgumentException("not found y1");
        }
        int i9 = i4;
        while (true) {
            if (i9 >= height) {
                i5 = 0;
                break;
            } else if (bitmap.getPixel(0, i9) != -16777216) {
                i5 = height - i9;
                break;
            } else {
                i9++;
            }
        }
        if (i5 == 0 || i5 == 1) {
            throw new IllegalArgumentException("not found y2");
        }
        a(bitmap, i2, i4, i3, i5);
    }

    public NineBitmapDrawable(Bitmap bitmap, int i2, int i3, int i4, int i5) {
        this.a = new Paint();
        a(bitmap, i2, i3, i4, i5);
    }

    public NineBitmapDrawable(String str) {
        this(LuaBitmap.getLocalBitmap(str));
    }

    private void a(Bitmap bitmap, int i2, int i3, int i4, int i5) {
        this.b = bitmap;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        this.c = i2;
        this.d = i3;
        this.e = i4;
        this.f = i5;
        int i6 = width - i4;
        int i7 = height - i5;
        this.g = new Rect(1, 1, i2, i3);
        this.h = new Rect(i2, 1, i6, i3);
        int i8 = width - 1;
        this.i = new Rect(i6, 1, i8, i3);
        this.j = new Rect(1, i3, i2, i7);
        this.k = new Rect(i2, i3, i6, i7);
        this.l = new Rect(i6, i3, i8, i7);
        int i9 = height - 1;
        this.m = new Rect(1, i7, i2, i9);
        this.n = new Rect(i2, i7, i6, i9);
        this.o = new Rect(i6, i7, i8, i9);
    }

    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        int i2 = bounds.right;
        int i3 = bounds.bottom;
        Rect rect = new Rect(0, 0, this.c, this.d);
        Rect rect2 = new Rect(this.c, 0, i2 - this.e, this.d);
        Rect rect3 = new Rect(i2 - this.e, 0, i2, this.d);
        Rect rect4 = new Rect(0, this.d, this.c, i3 - this.f);
        Rect rect5 = new Rect(this.c, this.d, i2 - this.e, i3 - this.f);
        Rect rect6 = new Rect(i2 - this.e, this.d, i2, i3 - this.f);
        Rect rect7 = new Rect(0, i3 - this.f, this.c, i3);
        Rect rect8 = new Rect(this.c, i3 - this.f, i2 - this.e, i3);
        Rect rect9 = new Rect(i2 - this.e, i3 - this.f, i2, i3);
        canvas.drawBitmap(this.b, this.g, rect, this.a);
        canvas.drawBitmap(this.b, this.h, rect2, this.a);
        canvas.drawBitmap(this.b, this.i, rect3, this.a);
        canvas.drawBitmap(this.b, this.j, rect4, this.a);
        canvas.drawBitmap(this.b, this.k, rect5, this.a);
        canvas.drawBitmap(this.b, this.l, rect6, this.a);
        canvas.drawBitmap(this.b, this.m, rect7, this.a);
        canvas.drawBitmap(this.b, this.n, rect8, this.a);
        canvas.drawBitmap(this.b, this.o, rect9, this.a);
    }

    public void gc() {
        try {
            this.b.recycle();
            this.p = true;
        } catch (Exception e2) {
            a.a(e2);
        }
    }

    public int getOpacity() {
        return 0;
    }

    public boolean isGc() {
        return this.p;
    }

    public void setAlpha(int i2) {
        this.a.setAlpha(i2);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.a.setColorFilter(colorFilter);
    }
}
