package android.widget;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;

class RoundRectDrawable extends Drawable {
    private float a;
    private final Paint b;
    private final RectF c;
    private final Rect d;
    private float e;
    private boolean f = false;
    private boolean g = true;

    public RoundRectDrawable(int i, float f2) {
        this.a = f2;
        this.b = new Paint(5);
        this.b.setColor(i);
        this.c = new RectF();
        this.d = new Rect();
    }

    private void a(Rect rect) {
        if (rect == null) {
            rect = getBounds();
        }
        this.c.set((float) rect.left, (float) rect.top, (float) rect.right, (float) rect.bottom);
        this.d.set(rect);
        if (this.f) {
            float a2 = RoundRectDrawableWithShadow.a(this.e, this.a, this.g);
            this.d.inset((int) Math.ceil((double) RoundRectDrawableWithShadow.b(this.e, this.a, this.g)), (int) Math.ceil((double) a2));
            this.c.set(this.d);
        }
    }

    /* access modifiers changed from: package-private */
    public float a() {
        return this.e;
    }

    /* access modifiers changed from: package-private */
    public void a(float f2) {
        if (f2 != this.a) {
            this.a = f2;
            a((Rect) null);
            invalidateSelf();
        }
    }

    /* access modifiers changed from: package-private */
    public void a(float f2, boolean z, boolean z2) {
        if (f2 != this.e || this.f != z || this.g != z2) {
            this.e = f2;
            this.f = z;
            this.g = z2;
            a((Rect) null);
            invalidateSelf();
        }
    }

    public void draw(Canvas canvas) {
        canvas.drawRoundRect(this.c, this.a, this.a, this.b);
    }

    public int getOpacity() {
        return -3;
    }

    public void getOutline(Outline outline) {
        if (Build.VERSION.SDK_INT >= 21) {
            outline.setRoundRect(this.d, this.a);
        }
    }

    public float getRadius() {
        return this.a;
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        a(rect);
    }

    public void setAlpha(int i) {
    }

    public void setColor(int i) {
        this.b.setColor(i);
        invalidateSelf();
    }

    public void setColorFilter(ColorFilter colorFilter) {
    }
}
