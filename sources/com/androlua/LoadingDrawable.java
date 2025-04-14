package com.androlua;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.TypedValue;

public class LoadingDrawable extends Drawable {
    private final DisplayMetrics a;
    private int b = 0;
    private int c = 0;
    private int d = 0;
    private int e = 0;
    private int f = 3;
    private int g = 1;
    private Paint h;
    private int i;

    public LoadingDrawable(Context context) {
        this.a = context.getResources().getDisplayMetrics();
        this.h = new Paint();
        this.h.setStyle(Paint.Style.STROKE);
        this.h.setAntiAlias(true);
        this.h.setStrokeWidth((float) a(8.0f));
        this.h.setColor(-2004318072);
    }

    private int a(float f2) {
        return (int) TypedValue.applyDimension(1, f2, this.a);
    }

    public void draw(Canvas canvas) {
        int i2;
        int i3;
        Rect rect = new Rect(getBounds());
        int min = (int) ((float) Math.min(rect.right, rect.bottom));
        rect.right = min;
        rect.bottom = min;
        canvas.save();
        canvas.translate((float) ((rect.right - min) / 2), (float) ((rect.bottom - min) / 2));
        float f2 = (float) min;
        float f3 = 0.15f * f2;
        float f4 = f2 * 0.85f;
        RectF rectF = new RectF(f3, f3, f4, f4);
        if (this.b >= 360 && this.i == 0) {
            this.g = 8;
            this.f = -6;
        } else if (this.b <= 6) {
            this.f = 6;
            this.g = 2;
        }
        if (this.b < 360 || this.i == 0) {
            if (this.i == 0) {
                this.b += this.f;
                i2 = this.c;
                i3 = this.g;
            } else {
                this.b += this.f * 2;
                i2 = this.c;
                i3 = this.g * 2;
            }
            this.c = i2 + i3;
            this.c %= 360;
        }
        canvas.drawArc(rectF, (float) this.c, (float) this.b, false, this.h);
        if (this.b >= 360) {
            this.f = -6;
            this.g = 8;
            if (this.i == 1) {
                Path path = new Path();
                path.moveTo(((float) rect.right) * 0.3f, ((float) rect.bottom) * 0.5f);
                path.lineTo(((float) rect.right) * 0.45f, ((float) rect.bottom) * 0.7f);
                path.lineTo(((float) rect.right) * 0.75f, ((float) rect.bottom) * 0.4f);
                canvas.drawPath(path, this.h);
            } else if (this.i == -1) {
                Canvas canvas2 = canvas;
                canvas2.drawLine((float) (rect.right / 2), ((float) rect.bottom) * 0.25f, (float) (rect.right / 2), ((float) rect.bottom) * 0.65f, this.h);
                canvas2.drawLine((float) (rect.right / 2), ((float) rect.bottom) * 0.7f, (float) (rect.right / 2), ((float) rect.bottom) * 0.75f, this.h);
            }
        }
        canvas.restore();
        invalidateSelf();
    }

    public int getOpacity() {
        return 0;
    }

    public void setAlpha(int i2) {
        this.h.setAlpha(i2);
    }

    public void setColor(int i2) {
        this.h.setColor(i2);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.h.setColorFilter(colorFilter);
    }

    public void setState(int i2) {
        this.i = i2;
    }

    public void setStrokeWidth(float f2) {
        this.h.setStrokeWidth(f2);
    }
}
